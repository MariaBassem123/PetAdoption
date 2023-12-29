import React from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';

const ApplicationList = () => {
    const applications = [
        {
          id: 1,
          pet: {
            name: 'Buddy',
            birthDate: new Date('2020-05-15'),
            gender: true, // true for male, false for female
            species: 'Dog',
            breed: 'Golden Retriever',
            description: 'Friendly and playful dog looking for a loving home.',
            behaviour: 'Playful and affectionate',
            healthStatus: 'Vaccinated and healthy',
            image: 'https://placekitten.com/200/300', // Placeholder image URL
          },
          adopter: {
            name: 'John Doe',
            email: 'john@example.com',
            phone: '123-456-7890',
          },
        },
        {
          id: 2,
          pet: {
            name: 'Whiskers',
            birthDate: new Date('2019-08-20'),
            gender: false, // true for male, false for female
            species: 'Cat',
            breed: 'Siamese',
            description: 'Independent cat with a calm demeanor.',
            behaviour: 'Likes to relax and be pampered',
            healthStatus: 'Neutered and healthy',
            image: 'https://placekitten.com/200/301', // Placeholder image URL
          },
          adopter: {
            name: 'Jane Smith',
            email: 'jane@example.com',
            phone: '987-654-3210',
          },
        },
        // Add more dummy applications as needed
      ];
      const handleAccept = (applicationId) => {
        // Implement logic to handle acceptance
        console.log(`Application ${applicationId} accepted`);
      };
    
      const handleReject = (applicationId) => {
        // Implement logic to handle rejection
        console.log(`Application ${applicationId} rejected`);
      };
    
      return (
        <div>
          <Typography variant="h5" gutterBottom>
            Application List
          </Typography>
          <Grid container spacing={2}>
            {applications.map((application) => (
              <Grid item key={application.id} xs={12} md={6}>
                <Paper elevation={3} style={{ marginBottom: '16px', padding: '16px' }}>
                  <ListItem style={{ padding: '0px' }}>
                    <img
                      alt={application.pet.name}
                      src={application.pet.image}
                      style={{ width: '150px', height: '150px' }}
                    />
                    <ListItemText
                      style={{ marginBottom: '16px', padding: '16px' }}
                      primary={application.pet.name}
                      secondary={
                        <React.Fragment>
                          <Typography variant="body1">{application.pet.species}</Typography>
                          <Typography variant="body1">{application.pet.breed}</Typography>
                          <Typography variant="body1">{application.pet.birthDate.toDateString()}</Typography>
                          <Typography variant="body1">{application.pet.gender ? 'Male' : 'Female'}</Typography>
                        </React.Fragment>
                      }
                    />
                  </ListItem>
                  <ListItemText
                    primary={
                      <React.Fragment>
                        <Typography variant="subtitle1" color="primary">Adopter Details</Typography>
                      </React.Fragment>
                    }
                    secondary={
                      <React.Fragment>
                        <Typography variant="body2">Name: {application.adopter.name}</Typography>
                        <Typography variant="body2">Email: {application.adopter.email}</Typography>
                        <Typography variant="body2">Phone: {application.adopter.phone}</Typography>
                      </React.Fragment>
                    }
                  />
                  <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                    <Button variant="contained" color="primary" onClick={() => handleAccept(application.id)}>
                      Accept
                    </Button>
                    <Button
                      variant="contained"
                      style={{ backgroundColor: '#DC143C', marginLeft: '8px', color: '#fff' }}
                      onClick={() => handleReject(application.id)}
                    >
                      Reject
                    </Button>
                  </div>
                </Paper>
              </Grid>
            ))}
          </Grid>
        </div>
      );
    };
    
    
    export default ApplicationList;