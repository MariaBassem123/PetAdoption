import React, { useState, useEffect } from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Backdrop from '@mui/material/Backdrop';
import Divider from '@mui/material/Divider';
import axios from 'axios';
import { useNavigate, useParams  } from 'react-router-dom';

export default function ShelterManagement( {user} ) {
  const MANAGE_SHELTER_URL = 'http://localhost:8088/manageShelter';
  let shelterId = user.shelterId; // user.shelterId
  const [staffMembers, setStaffMembers] = useState([]);
  const [shelterDetails, setShelterDetails] = useState('');

  useEffect(() => {
    const fetchShelterDetails = async () => {
      try {
        const shelterData = await getShelterByShelterId(shelterId);
        setShelterDetails(shelterData);

        // Call the function to fetch staff members
        const staffData = await fetchStaffMembers(shelterId);
        // console.log("staff members ", staffMembers);
      } catch (error) {
        console.error('Error fetching shelter details:', error);
        // Handle error as needed
      }
    };

    // Call the function to fetch shelter details
    fetchShelterDetails();
  }, [shelterId]);
  const fetchStaffMembers = async (shelterId) => {
    try {
      const response = await axios.get(`${MANAGE_SHELTER_URL}/getAllStaffs/${shelterId}`);
      setStaffMembers(response.data);
    } catch (error) {
      console.error('Error fetching staff members:', error);
      // Handle error as needed
    }
  };

  const getShelterByShelterId = async (shelterId) => {
    try {
      const response = await axios.get(`${MANAGE_SHELTER_URL}/getShelter/${shelterId}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching shelter:', error);
      throw error; // You can handle the error based on your application's needs
    }
  };
  

  const handleShelterDetailsChange = (field, value) => {
    setShelterDetails((prevDetails) => ({
      ...prevDetails,
      [field]: value,
    }));
  };

  const handleSubmit = () => {
    console.log('Submitting Shelter Details:', shelterDetails);
    // Implement logic to save shelter details to a backend API or perform necessary actions
    // Use Axios to send a PUT request to your backend
    // update email

    axios.put(`${MANAGE_SHELTER_URL}/updateShelterEmail/${shelterDetails.shelterId}`, null, {
      params: {
        newEmail: shelterDetails.email,
      }
    })
      .then((response) => {
        console.log('Update email successful:', response.data);
        // You can handle success, update UI, etc.
      })
      .catch((error) => {
        console.error('Error updating shelter email:', error);
        // Handle error, show error message, etc.
      });
    
   
// ------------------- Separator ----------------------------------
    // update phone number
    console.log(shelterDetails.phone_number);
    axios.put(`${MANAGE_SHELTER_URL}/updateShelterPhoneNumber/${shelterDetails.shelterId}`, null, {
    params: {
      newPhoneNumber: shelterDetails.phone_number,
    }
  })
    .then((response) => {
      console.log('Update phone number successful:', response.data);
      // You can handle success, update UI, etc.
    })
    .catch((error) => {
      console.error('Error updating shelter email:', error);
      // Handle error, show error message, etc.
    });

  //   // ------------------- Separator ----------------------------------
  //   // update location
    console.log(shelterDetails.location);
    axios.put(`${MANAGE_SHELTER_URL}/updateShelterLocation/${shelterDetails.shelterId}`, null, {
    params: {
      newLocation: shelterDetails.location,
    }
  })
    .then((response) => {
      console.log('Update location successful:', response.data);
      // You can handle success, update UI, etc.
    })
    .catch((error) => {
      console.error('Error updating shelter email:', error);
      // Handle error, show error message, etc.
    });
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
            <Typography variant="subtitle2">Shelter ID</Typography>
            <TextField
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.shelterId}
              onChange={(e) => handleShelterDetailsChange('shelterId', e.target.value)}
            />
            <Typography variant="subtitle2">Shelter Name</Typography>
            <TextField
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.name}
              onChange={(e) => handleShelterDetailsChange('name', e.target.value)}
            />
            <Typography variant="subtitle2">Shelter Email</Typography>
            <TextField
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.email}
              onChange={(e) => handleShelterDetailsChange('email', e.target.value)}
            />
            <Typography variant="subtitle2">Shelter Phone Number</Typography>
            <TextField
              variant="outlined"
              fullWidth
              margin="normal"
              value={shelterDetails.phone_number}
              onChange={(e) => handleShelterDetailsChange('phone_number', e.target.value)}
            />
            <Typography variant="subtitle2">Shelter Location</Typography>
            <TextField
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
