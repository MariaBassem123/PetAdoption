import React, { useState, useEffect } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import axios from 'axios';

const ApplicationList = ({user}) => {
    const [applications, setApplications] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await axios.get('http://localhost:8088/applications/getAllByShelter', {
              params: {
                shelterID: user.shelterId,
              },
            });
            console.log(response.data)
            setApplications(response.data);
          } catch (error) {
            console.error('Error fetching applications:', error.message);
          }
        };
    
        fetchData();
      }, []);

      const handleAccept = async (application) => {
        console.log(`Application ${application} accepted`);
        const shelterID = application.pet.shelterId; 
        const adopterID =application.adopter.adopterId ; 
        const petID = application.pet.petId;
        try {
            const response = await axios.put('http://localhost:8088/applications/acceptApplication', null, {
                params: {
                  shelterID: shelterID,
                  adopterID: adopterID,
                  petID: petID,
                },});
            
            if (response.status === 201) {
              console.log('Application accepted successfully');
            } else {
              console.error('Unexpected response:', response);
            }
          } catch (error) {
            console.error('Error accepting application:', error);
          }
      };
    
      const handleReject = async (application) => {
         const shelterID = application.pet.shelterId; 
         const adopterID =application.adopter.adopterId ; 
         const petID = application.pet.petId; 
         try {
             const response = await axios.put('http://localhost:8088/applications/rejectApplication', null, {
                 params: {
                   shelterID: shelterID,
                   adopterID: adopterID,
                   petID: petID,
                 },});
             
             if (response.status === 201) {
               console.log('Application rejected successfully');
             } else {
               console.error('Unexpected response:', response);
             }
           } catch (error) {
             console.error('Error rejecting application:', error);
           }
      };
    
      return (
        <div>
        <Typography variant="h5" gutterBottom>
            Application List
          </Typography>
        <div style={{ display: 'flex', flexWrap: 'wrap', gap: '16px' }}>
          {applications.map((application) => (
            <Paper key={application.id} elevation={3} style={{ marginBottom: '16px', padding: '16px', width: 'calc(33.33% - 16px)' }}>
              <ListItem style={{ padding: '0px' }}>
                <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                  <div>
                    <ListItemText
                      style={{ marginRight: '20px' }}
                      primary={application.pet.name}
                      secondary={
                        <React.Fragment>
                          <Typography variant="body1">{application.pet.species}</Typography>
                          <Typography variant="body1">{application.pet.breed}</Typography>
                          <Typography variant="body1">{application.pet.birthDate}</Typography>
                          <Typography variant="body1">{application.pet.gender ? 'Male' : 'Female'}</Typography>
                        </React.Fragment>
                      }
                    />
                    <ListItemText
                      style={{ marginRight: '20px' }}
                      primary={
                        <Typography variant="subtitle1" color="primary">Adopter Details</Typography>
                      }
                      secondary={
                        <React.Fragment>
                          <Typography variant="body2">Name: {application.adopter.name}</Typography>
                          <Typography variant="body2">Email: {application.adopter.email}</Typography>
                          <Typography variant="body2">Phone: {application.adopter.phoneNumber}</Typography>
                        </React.Fragment>
                      }
                    />
                  </div>
                </div>
              </ListItem>
              <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                <Button variant="contained" color="primary" onClick={() => handleAccept(application)}>
                  Accept
                </Button>
                <Button
                  variant="contained"
                  style={{ backgroundColor: '#DC143C', marginLeft: '8px', color: '#fff' }}
                  onClick={() => handleReject(application)}
                >
                  Reject
                </Button>
              </div>
            </Paper>
          ))}
        </div>
        </div>
      );
      
      

    };
    
    
    export default ApplicationList;