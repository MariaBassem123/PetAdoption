import React, { useState, useEffect } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Button from '@mui/material/Button';
import axios from 'axios';

const AdopterAdoptionList = ({ user }) => {
  const [adoption, setAdoption] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8088/adoptions/getAllByAdopter', {
        params: {
            adopterId: user.adopterId,
        },
      });
      console.log(response.data);
      setAdoption(response.data);
    } catch (error) {
      console.error('Error fetching adoption:', error.message);
    }
  };


  return (
    <div>
      <Typography variant="h5" gutterBottom>
        Adoption List
      </Typography>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '16px' }}>
        {adoption.map((application) => (
          <Paper
            elevation={3}
            style={{ marginBottom: '16px', padding: '16px', width: 'calc(33.33% - 16px)' }}
          >
            <ListItem style={{ padding: '0px' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                <div>
                  <ListItemText
                    style={{ marginRight: '20px' }}
                    primary={
                        <Typography variant="subtitle1" color="primary">
                        Pet Details
                        </Typography>
                    }
                    secondary={
                      <React.Fragment>
                      <Typography variant="body1">{application.pet.name}</Typography>
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
                      <Typography variant="subtitle1" color="primary">
                        Shelter Details
                      </Typography>
                    }
                    secondary={
                      <React.Fragment>
                        <Typography variant="body2">Name: {application.shelter.name}</Typography>
                        <Typography variant="body2">Email: {application.shelter.email}</Typography>
                        <Typography variant="body2">Phone: {application.shelter.phone_number}</Typography>
                        <Typography variant="body2">Loction: {application.shelter.location}</Typography>
                      </React.Fragment>
                    }
                  />
                  <ListItemText
                    style={{ marginRight: '20px' }}
                    primary={
                        <Typography variant="subtitle1" color="primary">
                        Date: {application.date}
                        </Typography>
                    }
                  />
                </div>
              </div>
            </ListItem>
            <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
            </div>
          </Paper>
        ))}
      </div>
    </div>
  );
};

export default AdopterAdoptionList;
