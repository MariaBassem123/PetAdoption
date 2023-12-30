package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.PetAdoptionSystemApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.Date;
import java.text.SimpleDateFormat;


@RestController
@RequestMapping("/database")
@CrossOrigin()
public class BackupController {

    @PostMapping("/update")
    public ResponseEntity<String> systemUpdate() {
        System.out.println("Received backup request.");

        try {
            // Getting path to the JAR file being executed dynamically
            CodeSource codeSource = PetAdoptionSystemApplication.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            // Creating Database Constraints
            String dbName = "pet_adoption_system";
            String dbUser = "root";
            String dbPass = "11112002";

            // Creating Path Constraints for folder saving using Paths
            Path folderPath = Paths.get(jarDir, "backup");

            // Creating Folder if it does not exist
            File f1 = folderPath.toFile();
            f1.mkdirs();  // Use mkdirs() to create parent directories if they don't exist

            // Creating Path Constraints for backup saving using Paths
            Path savePath = Paths.get(folderPath.toString(), "backup.sql");

            // Used to create a cmd command
            String[] executeCmd = {"mysqldump", "-u" + dbUser, "-p" + dbPass, dbName, "-r", savePath.toString()};

            // Executing the command here
            ProcessBuilder processBuilder = new ProcessBuilder(executeCmd);
            Process runtimeProcess = processBuilder.start();
            int processComplete = runtimeProcess.waitFor();

            // processComplete=0 if correctly executed, will contain other values if not
            if (processComplete == 0) {
                System.out.println("Backup Complete");
                return ResponseEntity.status(HttpStatus.CREATED).body("Backup Complete");
            } else {
                System.out.println("Backup Failure");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Backup Failure");
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            return new ResponseEntity<>("Error at Backup_restore: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/restore")
    public ResponseEntity<String> systemRestore(@RequestParam("fileName") String fileName) {
        try {
            CodeSource codeSource = PetAdoptionSystemApplication.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /* NOTE: Creating Database Constraints */
            String dbName = "pet_adoption_system";
            String dbUser = "root";
            String dbPass = "11112002";

            /* NOTE: Creating Path Constraints for restoring */
            String restorePath = jarDir + "\\backup" + "\\" + fileName;
            System.out.println(restorePath);
            /* NOTE: Used to create a cmd command */
            /* NOTE: Do not create a single large string, this will cause buffer locking, use string array */
            String[] executeCmd = new String[]{"mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /* NOTE: processComplete=0 if correctly executed, will contain other values if not */
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /* NOTE: processComplete=0 if correctly executed, will contain other values if not */
            if (processComplete == 0) {
                return ResponseEntity.ok("Successfully restored from SQL: " + fileName);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error at restoring");
            }
        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error at Restoredbfromsql: " + ex.getMessage());
        }
    }

    @GetMapping("/backupInfo")
    public ResponseEntity<String> getBackupInfo() {
        try {
            CodeSource codeSource = PetAdoptionSystemApplication.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            // Path to the backup folder
            String folderPath = jarDir + File.separator + "backup";
            File backupFolder = new File(folderPath);

            if (backupFolder.exists() && backupFolder.isDirectory()) {
                long lastBackupTime = backupFolder.lastModified();

                // Format the timestamp into a more readable date format
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(new Date(lastBackupTime));

                return ResponseEntity.ok("Last Backup Time: " + formattedDate);
            } else {
                return ResponseEntity.ok("No backup performed yet");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching backup info: " + e.getMessage());
        }
    }

}
