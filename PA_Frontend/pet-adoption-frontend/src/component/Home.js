import React, { useState, useEffect } from "react";
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActionArea from '@mui/material/CardActionArea';
import TextField from '@mui/material/TextField';
import { useNavigate } from 'react-router-dom';
import CardMedia from '@mui/material/CardMedia';

export default function Home() {
  const BaseUri = 'http://localhost:8088';
  const mainFeaturedPost = {
    title: 'Title of a longer featured blog Pet',
    description:
      "Multiple lines of text that form the lede, informing new readers quickly and efficiently about what's most interesting in this Pet's contents.",
    image: 'https://source.unsplash.com/random?cats',
    imageText: 'main image description',
  };

  const [featuredPests, setFeaturedPests] = useState([]);
  const navigate = useNavigate();

  const getFeaturedPests = async () => {
    try {
      const response = await fetch(`${BaseUri}/pet/getAll`);
      if (response.ok) {
        const data = await response.json();
        setFeaturedPests(data);
        console.log(data);
      } else {
        console.error('Failed to fetch pets:', response.statusText);
      }
    } catch (error) {
      console.error('Error fetching pets:', error.message);
    }
  };

  useEffect(() => {
    getFeaturedPests();
  }, []);

  const MainFeaturedPost = (props) => {
    const { post } = props;

    return (

      <Paper
        sx={{
          position: 'relative',
          backgroundColor: 'grey.800',
          color: '#fff',
          mb: 4,
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'center',
          backgroundImage: `url(${post.image})`,
        }}
      >
        {<img style={{ display: 'none' }} src={post.image} alt={post.imageText} />}
        <Box
          sx={{
            position: 'absolute',
            top: 0,
            bottom: 0,
            right: 0,
            left: 0,
            backgroundColor: 'rgba(0,0,0,.3)',
          }}
        />
        <Grid container>
          <Grid item md={6}>
            <Box
              sx={{
                position: 'relative',
                p: { xs: 3, md: 6 },
                pr: { md: 0 },
              }}
            >
              <Typography component="h1" variant="h3" color="inherit" gutterBottom>
                {post.title}
              </Typography>
              <Typography variant="h5" color="inherit" paragraph>
                {post.description}
              </Typography>
            </Box>
          </Grid>
        </Grid>
      </Paper>
    );
  };



  const FeaturedPet = (props) => {
    const { pet } = props;
    const handleImageClick = (postId) => {
      navigate(`/pet/${postId}`);
    };
    console.log(pet.img[0]);

    return (
      <Grid item xs={12} md={6}>
        <CardActionArea onClick={() => handleImageClick(pet.pet.petId)} component="div">
          <Card sx={{ display: 'flex' }}>
            <CardContent sx={{ flex: 1 }}>
              <Typography component="h2" variant="h5">
                {pet.pet.name}
              </Typography>
              <Typography variant="subtitle1" color="text.secondary">
                {pet.pet.breed}
              </Typography>
              <Typography variant="subtitle1" paragraph>
                {pet.pet.description}
              </Typography>
            </CardContent>
            <img
              src={pet.img[0]}
              style={{ objectFit: 'cover', height: '250px', width: '250px', marginBottom: '12px' }}
            />
          </Card>
        </CardActionArea>
      </Grid>
    );
  };

  return (
    <main>
      <MainFeaturedPost post={mainFeaturedPost} />

      <Box sx={{ display: 'flex', justifyContent: 'center', marginBottom: 4 }}>
        <TextField
          label="Search"
          variant="outlined"
          sx={{ width: '100%', maxWidth: '450px', marginRight: 90 }}
        />
      </Box>

      <Grid container spacing={4}>
        {featuredPests.map((pet) => (
          <FeaturedPet key={pet.pet.petId} pet={pet} />
        ))}
      </Grid>

      <hr style={{ margin: '20px 0', borderTop: '1px solid #ccc' }} />
    </main>
  );
}
