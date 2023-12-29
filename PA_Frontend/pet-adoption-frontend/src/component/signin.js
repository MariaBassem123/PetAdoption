import * as React from 'react';
import {useState} from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import signInService from '../services/signInService';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';

// TODO remove, this demo shouldn't need to reset the theme.

const defaultTheme = createTheme();

export default function SignInSide() {
  const [selectedRole, setSelectedRole] = useState('');
  
  const handleRoleChange = (event) => {
    setSelectedRole(event.target.value);
    console.log(event.target.value);
  };

  const handleSubmit =  async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });

    console.log('In Sign-in after clicking the button');
    if (!selectedRole) {
      // Display an error message or take appropriate action
      alert('Please select a role');
      // Optionally, you can display an error message to the user or prevent further processing
      return;
    }
    try {
      if (selectedRole === 'adopter') {
        console.log("In adopter signin");
        const response = await signInService.checkAdopter(data.get('email'), data.get('password'));
        console.log('Sign-in response:', response.data);
        if(response.data === 'ADOPTER_FOUND_CORRECT_PASSWORD'){
          alert('Welcome '+ data.get('email') + '! Horray!!')
        } else{
          alert("Email not found or incorrect password!")
        }
      } else if (selectedRole === 'staff') {
        console.log("In staff signin");
        const response = await signInService.checkStaff(data.get('email'), data.get('password'));
        console.log('Sign-in response:', response.data);
        if(response.data === 'STAFF_FOUND_CORRECT_PASSWORD'){
          alert('Welcome '+ data.get('email') + '! Horray!!')
        } else{
          alert("Email not found or incorrect password!")
        }
      }
    } catch (error) {
      console.error('Error during sign-in!!', error);
      // Add your error handling logic here
    }
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: 'url(https://source.unsplash.com/random?cute_pets)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 20,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Typography sx={{ m: 1 }} component="h1" variant="h5">
              Welcome back to PetPal
            </Typography>
            <Typography component="h1" variant="h5">
              Sign in
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{
                width:'100%',
                mt: 1 ,display: 'flex',
                flexDirection: 'column',
                alignItems: 'center', 
                justifyContent: 'center' 
              }}
            >
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
              />
              <RadioGroup
                aria-label="role"
                name="role"
                value={selectedRole}
                onChange={handleRoleChange}
                sx={{ gap: 2, flexWrap: 'wrap', flexDirection: 'row' }}
              >
                <FormControlLabel value="staff" control={<Radio />} label="Staff" />
                <FormControlLabel value="adopter" control={<Radio />} label="Adopter" />
              </RadioGroup>
              
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Sign In
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link href="#" variant="body2">
                    Forgot password?
                  </Link>
                </Grid>
                <Grid item>
                  <Link href="#" variant="body2">
                    {"Don't have an account? Sign Up"}
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}