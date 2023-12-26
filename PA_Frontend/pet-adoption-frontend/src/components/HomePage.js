import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button'; // Change Link to Button
import Toolbar from '@mui/material/Toolbar';
import Home from './Home';
import Profile from './Profile';

const sections = [
  { title: 'Home', component: <Home /> },
  { title: 'Profile', component: <Profile /> },
];

const defaultTheme = createTheme();

const Header = (props) => {
  const { sections, title, selectedSection, setSelectedSection } = props;

  
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
      </Toolbar>
    </React.Fragment>
  );
};

export default function HomePage() {
  const [selectedSection, setSelectedSection] = React.useState(0);

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
}
