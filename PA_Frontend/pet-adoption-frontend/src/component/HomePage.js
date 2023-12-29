import React, { useState } from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Toolbar from '@mui/material/Toolbar';
import Home from './Home';
import StaffProfile from './staffProfile';
import ShelterManagement from './Shelter_Management';
import { useNavigate, useParams  } from 'react-router-dom';
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';
import IconButton from '@mui/material/IconButton';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import ApplicationList from './ApplicationList';


const HomePage = () => {
  const { adopterData } = useParams();
  const decodedAdopterData = decodeURIComponent(adopterData);
  const parsedAdopterData = JSON.parse(atob(decodedAdopterData));

  const manager = [
    { title: 'Home', component: <Home user={parsedAdopterData}/> },
    { title: 'Shelter Management', component: <ShelterManagement user={parsedAdopterData} /> }, 
  ];
  const staff = [
    { title: 'Home', component: <Home user={parsedAdopterData} /> },
    { title: 'Profile', component: <StaffProfile user={parsedAdopterData}/> },
    { title: 'Applications', component: <ApplicationList/> },
  ];
  const adaptor = [
    { title: 'Home', component: <Home user={parsedAdopterData} /> },
  ];
  
  const defaultTheme = createTheme();
  
  const Header = (props) => {
    const { sections, title, selectedSection, setSelectedSection } = props;
    const [isNotificationOpen, setIsNotificationOpen] = useState(false);
    
    const handleNotificationClick = () => {
      setIsNotificationOpen(true);
    };

    const handleNotificationClose = () => {
      setIsNotificationOpen(false);
    };
    console.log("parseAdopter" , parsedAdopterData.role)
    return (
      <React.Fragment>
        <Toolbar sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <Typography
            component="h2"
            variant="h5"
            color="inherit"
            align="center"
            noWrap
            sx={{ flex: 1 }}
          >
            {title}
          </Typography>
          
        </Toolbar>
        <Toolbar
          component="nav"
          variant="dense"
          sx={{ justifyContent: 'space-between', overflowX: 'auto' }}
        >
          {sections.map((section, index) => (
            <Button
              key={section.title}
              color="inherit"
              variant="text"
              onClick={() => setSelectedSection(index)}
              sx={{ p: 1, flexShrink: 0, ...(selectedSection === index && { backgroundColor: '#e0e0e0' }) }}
            >
              {section.title}
            </Button>
          ))}

          {parsedAdopterData.role != 0 && parsedAdopterData.role != 1 && (
              <IconButton color="inherit" onClick={handleNotificationClick}>
                <NotificationsNoneIcon />
            </IconButton>
          )}
        </Toolbar>
        <Dialog open={isNotificationOpen} onClose={handleNotificationClose}>
        <DialogTitle>Notifications</DialogTitle>
        <DialogContent>
          <Typography>This is a cute notification!</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleNotificationClose}>Close</Button>
        </DialogActions>
      </Dialog>
      </React.Fragment>
    );
  };
  
  console.log("Data:", parsedAdopterData.role);
  let sections;
  if(parsedAdopterData.role == 0)  {
    sections = staff
  }
  else if(parsedAdopterData.role == 1)  {sections = manager}
  else {
    sections = adaptor
  }


  const [selectedSection, setSelectedSection] = useState(0);

  return (
    <ThemeProvider theme={defaultTheme}>
      <CssBaseline />
      <Container maxWidth="lg">
        <Header title="Pet Adoption" sections={sections} selectedSection={selectedSection} setSelectedSection={setSelectedSection} />
        <Box mt={2}>
          {sections[selectedSection].component}
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default HomePage;
