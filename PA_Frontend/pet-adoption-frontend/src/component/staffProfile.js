import React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Backdrop from '@mui/material/Backdrop';

export default function StaffProfile() {
  // Dummy data (replace this with actual data)
  const staffDetails = {
    staffId: '12345',
    name: 'John Doe',
    email: 'john.doe@example.com',
    phone_number: '555-555-5555',
  };

  const shelterDetails = {
    shelterId: '56789',
    name: 'Example Shelter',
    email: 'shelter@example.com',
    phone_number: '555-555-5555',
    location: '123 Main Street, Cityville',
  };

  return (
    <Container component="main" maxWidth="md" sx={{ position: 'relative', overflow: 'hidden', minHeight: '70vh'}}>
      <CssBaseline />
      <Backdrop
        sx={{ 
          zIndex: (theme) => theme.zIndex.drawer - 1,
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
            filter: 'blur(2px)',
            backgroundSize: 'cover', // Set the background size to cover
          }}
        />
      </Backdrop>
      <Grid container spacing={4} justifyContent="center" alignItems="center" sx={{ position: 'relative', zIndex: (theme) => theme.zIndex.drawer + 2 }}>
        <Grid item xs={12} md={6}>
          <Paper elevation={3} sx={{ padding: 4, textAlign: 'center', borderRadius: 8, backgroundColor: 'rgba(255, 255, 255, 0.8)' }}>
            <Typography variant="h6" gutterBottom>
              Staff Profile
            </Typography>
            
            <BoxItem label="Staff ID" value={staffDetails.staffId} />
            <BoxItem label="Name" value={staffDetails.name} />
            <BoxItem label="Email" value={staffDetails.email} />
            <BoxItem label="Phone Number" value={staffDetails.phone_number} />
          </Paper>
        </Grid>

        <Grid item xs={12} md={6}>
          <Paper elevation={3} sx={{ padding: 4, textAlign: 'center', borderRadius: 8, backgroundColor: 'rgba(255, 255, 255, 0.8)' }}>
            <Typography variant="h6" gutterBottom>
              Shelter Details
            </Typography>
            
            <BoxItem label="Shelter ID" value={shelterDetails.shelterId} />
            <BoxItem label="Name" value={shelterDetails.name} />
            <BoxItem label="Email" value={shelterDetails.email} />
            <BoxItem label="Phone Number" value={shelterDetails.phone_number} />
            <BoxItem label="Location" value={shelterDetails.location} />
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
}

const BoxItem = ({ label, value }) => (
  <div style={{ marginBottom: 16 }}>
    <TextField
      variant="outlined"
      fullWidth
      InputProps={{ readOnly: true }}
      label={label}
      value={value}
      sx={{ color: 'black', opacity: 1, backgroundColor: 'rgba(255, 255, 255, 0.8)' }}
    />
  </div>
);
