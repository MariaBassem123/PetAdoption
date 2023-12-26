import React, { useState } from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Backdrop from '@mui/material/Backdrop';
import Divider from '@mui/material/Divider';

export default function ShelterManagement() {
  const [shelterDetails, setShelterDetails] = useState({
    shelterId: '56789',
    name: 'Example Shelter',
    email: 'shelter@example.com',
    phone_number: '555-555-5555',
    location: '123 Main Street, Cityville',
  });

  const [staffMembers, setStaffMembers] = useState([
    { staffId: 1, name: 'John Doe', email: 'john@example.com', phone_number: '555-1234' },
    { staffId: 2, name: 'Jane Smith', email: 'jane@example.com', phone_number: '555-5678' },
    { staffId: 3, name: 'Another Staff', email: 'another@example.com', phone_number: '555-4321' },
    { staffId: 4, name: 'Another Staff', email: 'another@example.com', phone_number: '555-4321' },
    { staffId: 5, name: 'Another Staff', email: 'another@example.com', phone_number: '555-4321' },

    // Add more staff members as needed
  ]);

  const handleShelterDetailsChange = (field, value) => {
    setShelterDetails((prevDetails) => ({
      ...prevDetails,
      [field]: value,
    }));
  };

  const handleSubmit = () => {
    console.log('Submitting Shelter Details:', shelterDetails);
    // Implement logic to save shelter details to a backend API or perform necessary actions
  };

  return (
    <Container component="main" maxWidth="md" sx={{ position: 'relative', overflow: 'hidden', minHeight: '70vh' }}>
      <CssBaseline />
      <Backdrop
        sx={{
          zIndex: (theme) => theme.zIndex.drawer - 1,
          position: 'fixed',
          top: 0,
          left: 0,
          width: '100%',
          height: '100%',
          filter: 'blur(2px)',
          marginTop: '115px',
        }}
        open={true}
      >
        <img
          src="https://img.freepik.com/free-vector/frame-with-dogs-vector-white-background_53876-127700.jpg"
          alt="background"
          style={{
            width: '100%',
            height: '100%',
            objectFit: 'cover',
            position: 'fixed',
            top: 0,
            left: 0,
            opacity: 0.5,
          }}
        />
      </Backdrop>
      <Grid container spacing={4} justifyContent="center" alignItems="stretch" sx={{ position: 'relative', zIndex: (theme) => theme.zIndex.drawer + 2 }}>
        <Grid item xs={12} md={6}>
          <Paper elevation={3} sx={{ padding: 4, textAlign: 'center', borderRadius: 8, backgroundColor: 'rgba(255, 255, 255, 0.8)', position: 'relative', zIndex: 1 }}>
            <Typography variant="h6" gutterBottom>
              Shelter Details
            </Typography>

            <TextField
              label="Shelter ID"
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.shelterId}
              onChange={(e) => handleShelterDetailsChange('shelterId', e.target.value)}
            />
            <TextField
              label="Name"
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.name}
              onChange={(e) => handleShelterDetailsChange('name', e.target.value)}
            />
            <TextField
              label="Email"
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.email}
              onChange={(e) => handleShelterDetailsChange('email', e.target.value)}
            />
            <TextField
              label="Phone Number"
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.phone_number}
              onChange={(e) => handleShelterDetailsChange('phone_number', e.target.value)}
            />
            <TextField
              label="Location"
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.location}
              onChange={(e) => handleShelterDetailsChange('location', e.target.value)}
            />

            {/* Save Button for Shelter Details */}
            <Button variant="contained" color="primary" onClick={handleSubmit}>
              Save
            </Button>
          </Paper>
        </Grid>

        <Grid item xs={12} md={6}>
          <Paper elevation={3} sx={{ padding: 4, textAlign: 'center', borderRadius: 8, maxHeight: 'calc(70vh - 32px)', overflowY: 'auto' }}>
            <Typography variant="h6" gutterBottom>
              Staff Members
            </Typography>
            {staffMembers.map((staff, index) => (
              <div key={staff.staffId} style={{ marginBottom: 16 }}>
                <Typography variant="subtitle1">{staff.name}</Typography>
                <Typography variant="body2" color="textSecondary">
                  {staff.email}
                </Typography>
                <Typography variant="body2" color="textSecondary">
                  {staff.phone_number}
                </Typography>
                {index !== staffMembers.length - 1 && <Divider />}
              </div>
            ))}
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
}
