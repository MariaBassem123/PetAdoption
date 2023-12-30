package com.example.PetAdoptionSystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

@RestController
@RequestMapping("/database")
@CrossOrigin()
public class BackupController {

    private static final Logger logger = LoggerFactory.getLogger(BackupController.class);

    @PostMapping("/update")
    public ResponseEntity<String> systemUpdate() {
        logger.info("Received backup request.");

        try {
            // Getting path to the JAR file being executed
            CodeSource codeSource = BackupController.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = "D:\\CSE25\\THIRD YEAR\\First term\\Database\\Project\\PetAdoption\\PA_Backend\\PetAdoptionSystem\\target\\PetAdoptionSystem-0.0.1-SNAPSHOT.jar";
            logger.info("JAR file path: {}", jarFile.getAbsolutePath());

            // Creating Database Constraints
            String dbName = "pet_adoption_system";
            String dbUser = "root";
            String dbPass = "11112002";

            // Creating Path Constraints for folder saving
            String folderPath = jarDir + File.separator + "backup";
            logger.info("Backup folder path: {}", folderPath);

            // Creating Folder if it does not exist
            File f1 = new File(folderPath);
            f1.mkdirs();  // Use mkdirs() to create parent directories if they don't exist

            // Creating Path Constraints for backup saving
            String savePath = folderPath + File.separator + "backup.sql";

            // Used to create a cmd command
            String[] executeCmd = {"mysqldump", "-u" + dbUser, "-p" + dbPass, "--database", dbName, "-r", savePath};

            // Executing the command here
            ProcessBuilder processBuilder = new ProcessBuilder(executeCmd);
            Process runtimeProcess = processBuilder.start();
            int processComplete = runtimeProcess.waitFor();

            // processComplete=0 if correctly executed, will contain other values if not
            logger.info("Executing mysqldump command: {}", String.join(" ", executeCmd));

            if (processComplete == 0) {
                logger.info("Backup Complete");
                return ResponseEntity.status(HttpStatus.CREATED).body("Backup Complete");
            } else {
                logger.error("Backup Failure");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Backup Failure");
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            logger.error("Error at Backup_restore: {}", ex.getMessage());
            return new ResponseEntity<>("Error at Backup_restore: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
