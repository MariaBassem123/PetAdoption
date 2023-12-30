import React, { useState, useEffect } from 'react';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';

export default function SystemBackup({ user }) {
  const [backupStatus, setBackupStatus] = useState('');

  const handleBackupClick = async () => {
    try {
      const response = await fetch('http://localhost:8088/database/update', {
        method: 'POST',
      });

      if (response.ok) {
        const result = await response.text();
        setBackupStatus(result);
      } else {
        console.error('Backup request failed:', response.statusText);
      }
    } catch (error) {
      console.error('Error during backup request:', error.message);
    }
  };

  const handleRestoreClick = async () => {
    try {
      const response = await fetch('http://localhost:8088/database/restore?fileName=backup.sql', {
        method: 'POST',
      });

      if (response.ok) {
        const result = await response.text();
        setBackupStatus(result);
      } else {
        console.error('Restore request failed:', response.statusText);
      }
    } catch (error) {
      console.error('Error during restore request:', error.message);
    }
  };

  useEffect(() => {
    // Fetch existing backup information when the component mounts
    const fetchBackupInfo = async () => {
      try {
        const response = await fetch('http://localhost:8088/database/backupInfo');
        if (response.ok) {
          const result = await response.text();
          setBackupStatus(result);
        } else {
          console.error('Backup info request failed:', response.statusText);
        }
      } catch (error) {
        console.error('Error during backup info request:', error.message);
      }
    };

    fetchBackupInfo();
  }, []); // Empty dependency array ensures the effect runs only once on mount

  return (
    <div>
      <Button onClick={handleBackupClick} variant="contained" color="primary">
        Backup System
      </Button>

      <Box mt={2}>
        <strong>Last Backup Status:</strong> {backupStatus || 'No backup performed yet'}
      </Box>

      <Box mt={2}>
        <Button onClick={handleRestoreClick} variant="contained" color="secondary">
          Restore System
        </Button>
      </Box>
    </div>
  );
}
