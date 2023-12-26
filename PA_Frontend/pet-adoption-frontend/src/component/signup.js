import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import { useState } from 'react';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const defaultTheme = createTheme();

export default function SignUpSide() {
  const BaseUri = 'http://localhost:8088'
  const [selectedRole, setSelectedRole] = useState('');
  const [showShelterInput, setShowShelterInput] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const user= {
      username: data.get('username'),
      email: data.get('email'),
      phone: data.get('phone'),
      password: data.get('password'),
      confirmPassword: data.get('confirmPassword'),
      role: data.get('role'),
    };
    console.log(user);

    const validPhone = /^\d{11}$/.test(user.phone) || user.phone === "";
    const passEqualsConfPass = (user.password === user.confirmPassword)
    const passCheck = /^.{8,16}$/.test(user.password);
    
    if (passEqualsConfPass && validPhone && passCheck  && user.role != null) {
      console.log(user.username, user.password, user.confirmPassword,user.phone, user.email, user.role);
      console.log(user);

      if(user.role==="staff")
      {
          const response = fetch(`${BaseUri}/staffs/save`, { 
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({
                shelterId:data.get('shelterId'),
                name: user.username,
                email: user.email,
                phone_number: user.phone,
                password: user.password,
                role: 1
              })
        })
        
        if((await response).ok){
            alert('Welcome '+ user.username + '! Horray!!')
        } else {
            alert("Wrong info.")
        }
    }
    else if(user.role==="adopter")
    {
        const response = fetch(`${BaseUri}/adopters/save`, { 
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              name: user.username,
              email: user.email,
              phoneNumber: user.phone,
              password: user.password
            })
      })
      
      if((await response).ok){
          alert('Welcome  '+ user.username + '! Horray!!')
      } else {
          alert("Wrong info.")
      }
  }

    } 
    else if (!validPhone) {
        alert("Phone number must be 11 digits!");
    } 
    else if (!passEqualsConfPass) {
        console.log("Password and Confirm Password are not the same")
        // Re-enter the password
        alert("Password and Confirm Password are not the same");
    } 
    else if (!passCheck) {
        alert("Password must be at least 8 characters and at most 16 character");
    }
    else if(user.role!=null){
      alert("Choose: staff/adopter");
    }

  };

  const handleRoleChange = (event) => {
    setSelectedRole(event.target.value);
    setShowShelterInput(event.target.value === 'staff');
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
              my: 10,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Typography sx={{ m: 1 }} component="h1" variant="h5">
              Welcome to PetPal
            </Typography>
            <Typography component="h1" variant="h5">
              Sign Up
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit} 
              sx={{
               width:'100%',
               mt: 1 ,display: 'flex',
               flexDirection: 'column',
               alignItems: 'center', 
               justifyContent: 'center' 
              }}>
              <TextField
                margin="normal"
                required
                fullWidth
                id="username"
                label="User Name"
                name="username"
                autoComplete="username"
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="phone"
                label="Phone Number"
                name="phone"
                autoComplete="tel"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="new-password"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="confirmPassword"
                label="Confirm Password"
                type="password"
                id="confirmPassword"
                autoComplete="new-password"
              />
              <RadioGroup
                aria-label="role"
                name="role"
                value={selectedRole}
                onChange={handleRoleChange}
                required  // Add the required attribute
                sx={{ gap: 2, flexWrap: 'wrap', flexDirection: 'row' }}
              >
                <FormControlLabel value="staff" control={<Radio />} label="Staff" />
                <FormControlLabel value="adopter" control={<Radio />} label="Adopter" />
              </RadioGroup>
              {/* Conditionally render shelter ID input based on selected role */}
              {selectedRole === 'staff' && showShelterInput && (
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="shelterId"
                  label="Shelter ID"
                  name="shelterId"
                  autoComplete="shelterId"
                />
              )}      
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Sign Up
              </Button>
              <Grid container>
                <Grid item xs>
                </Grid>
                <Grid item>
                  <Link href="#" variant="body2">
                    {"Already have an account? Sign In"}
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
