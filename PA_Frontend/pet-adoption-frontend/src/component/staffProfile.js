import React, { useState, useEffect } from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Backdrop from '@mui/material/Backdrop';
import signInService from '../services/signInService';

export default function StaffProfile({user}) {
  
  const [shelterDetails, setShelterDetails] = useState('');

  useEffect(() => {
    const fetchShelterDetails = async () => {
      try {
        const response = await signInService.getShelterByID(user.shelterId);
        setShelterDetails(response.data);
        console.log("Shelter detail:", response.data);
      } catch (error) {
        console.error("Error fetching shelter details:", error);
      }
    };

    fetchShelterDetails();
  }, [user.shelterId]);

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
            backgroundSize: 'cover',
          }}
        />
      </Backdrop>
      <Grid container spacing={4} justifyContent="center" alignItems="center" sx={{ position: 'relative', zIndex: (theme) => theme.zIndex.drawer + 2 }}>
        <Grid item xs={12} md={6}>
          <Paper elevation={3} sx={{ padding: 4, textAlign: 'center', borderRadius: 8, backgroundColor: 'rgba(255, 255, 255, 0.8)' }}>
            <Typography variant="h6" gutterBottom>
              Staff Profile
            </Typography>
            <Typography variant="subtitle2" align="left">Name</Typography>
            <BoxItem  value={user.name} />
            <Typography variant="subtitle2" align="left">Email</Typography>
            <BoxItem  value={user.email} />
            <Typography variant="subtitle2" align="left">Phone Number</Typography>
            <BoxItem value={user.phone_number} />
          </Paper>
        </Grid>

        <Grid item xs={12} md={6}>
          <Paper elevation={3} sx={{ padding: 4, textAlign: 'center', borderRadius: 8, backgroundColor: 'rgba(255, 255, 255, 0.8)' }}>
            <Typography variant="h6" gutterBottom>
              Shelter Details
            </Typography>
            <Typography variant="subtitle2" align="left">Shelter Name</Typography>
            <BoxItem value={shelterDetails.name} />
            <Typography variant="subtitle2" align="left">Shelter Email</Typography>
            <BoxItem value={shelterDetails.email} />
            <Typography variant="subtitle2" align="left">Shelter Phone Number</Typography>
            <BoxItem value={shelterDetails.phone_number} />
            <Typography variant="subtitle2" align="left">Shelter Location</Typography>
            <BoxItem value={shelterDetails.location} />
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
