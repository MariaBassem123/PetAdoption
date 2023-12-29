import React from 'react';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CardActionArea from '@mui/material/CardActionArea';
import TextField from '@mui/material/TextField';
import { useNavigate } from 'react-router-dom'; // Import useNavigate

export default function Home() {
    const mainFeaturedPost = {
        title: 'Title of a longer featured blog post',
        description:
          "Multiple lines of text that form the lede, informing new readers quickly and efficiently about what's most interesting in this post's contents.",
        image: 'https://source.unsplash.com/random?cats',
        imageText: 'main image description',
      };
      
      const featuredPosts = [
        {
          id: 1, // Add an id property
          title: 'Featured post',
          date: 'Nov 12',
          description:
            'This is a wider card with supporting text below as a natural lead-in to additional content.',
          image: 'https://source.unsplash.com/random?cats',
          imageLabel: 'Image Text',
        },
        {
          id: 2, // Add an id property
          title: 'Post title',
          date: 'Nov 11',
          description:
            'This is a wider card with supporting text below as a natural lead-in to additional content.',
          image: 'https://source.unsplash.com/random?cats',
          imageLabel: 'Image Text',
        },
        {
          id: 3, // Add an id property
          title: 'Featured post',
          date: 'Nov 12',
          description:
            'This is a wider card with supporting text below as a natural lead-in to additional content.',
          image: 'https://source.unsplash.com/random?cats',
          imageLabel: 'Image Text',
        },
      ];
      

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
      const FeaturedPost = (props) => {
        const { post } = props;
        const navigate = useNavigate();
        const handleImageClick = (postId) => {
          // Handle the click event and navigate to the PetDetailPage with the postId
          navigate(`/pet/${postId}`);
        };
        return (
          <Grid item xs={12} md={6}>
            <CardActionArea onClick={() => handleImageClick(post.id)} component="div">
              <Card sx={{ display: 'flex' }}>
                <CardContent sx={{ flex: 1 }}>
                  <Typography component="h2" variant="h5">
                    {post.title}
                  </Typography>
                  <Typography variant="subtitle1" color="text.secondary">
                    {post.date}
                  </Typography>
                  <Typography variant="subtitle1" paragraph>
                    {post.description}
                  </Typography>
                </CardContent>
                <CardMedia
                  component="img"
                  sx={{ width: 160, display: { xs: 'none', sm: 'block' } }}
                  image={post.image}
                  alt={post.imageLabel}
                />
              </Card>
            </CardActionArea>
          </Grid>
        );
      };
    return(
        <main>
        <MainFeaturedPost post={mainFeaturedPost} />

        <Box sx={{ display: 'flex', justifyContent: 'center', marginBottom: 4}}>
        <TextField
          label="Search"
          variant="outlined"
          sx={{ width: '100%', maxWidth: '450px', marginRight: 90 }}
        />
      </Box>

        <Grid container spacing={4}>
          {featuredPosts.map((post) => (
            <FeaturedPost key={post.title} post={post} />
          ))}
        </Grid>

        <hr style={{ margin: '20px 0', borderTop: '1px solid #ccc' }} />

      </main>
    )
}