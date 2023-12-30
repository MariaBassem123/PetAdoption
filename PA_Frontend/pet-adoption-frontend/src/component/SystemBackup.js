import React, { useState } from 'react';
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
        console.error('Backup request failed:', response.json());
      }
    } catch (error) {
      console.error('Error during backup request:', error.message);
    }
  };

  return (
    <div>
      <Button onClick={handleBackupClick} variant="contained" color="primary">
        Backup System
      </Button>

      <Box mt={2}>
        <strong>Last Backup Status:</strong> {backupStatus || 'No backup performed yet'}
      </Box>
    </div>
  );
}
