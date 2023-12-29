import React, { useState, useEffect } from "react";
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActionArea from '@mui/material/CardActionArea';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import { useNavigate, useParams  } from 'react-router-dom';

export default function Home() {

  const { adopterData } = useParams();
  const decodedAdopterData = decodeURIComponent(adopterData);
  const parsedAdopterData = JSON.parse(atob(decodedAdopterData));

  console.log("Data:", parsedAdopterData.role);

  const BaseUri = 'http://localhost:8088';
  const mainFeaturedPost = {
    title: 'Title of a longer featured blog Pet',
    description:
      "Multiple lines of text that form the lede, informing new readers quickly and efficiently about what's most interesting in this Pet's contents.",
    image: 'https://source.unsplash.com/random?cats',
    imageText: 'main image description',
  };

  const [featuredPests, setFeaturedPests] = useState([]);
  const [isModalOpen, setModalOpen] = useState(false);
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
    const handleImageClick = (pet) => {
      navigate(`/pet/${pet.pet.petId}`,{ state: { pet } });
    };

    return (
      <Grid item xs={12} md={6}>
        <CardActionArea onClick={() => handleImageClick(pet)} component="div">
          <Card sx={{ display: 'flex' }}>
            <CardContent sx={{ flex: 1 }}>
              <Typography component="h2" variant="h5">
                {pet.pet.name}
              </Typography>
              <Typography variant="subtitle1" color="text.secondary">
                {pet.pet.species}
              </Typography>
              <Typography variant="subtitle1" color="text.secondary">
                {pet.pet.breed}
              </Typography>
              <Typography variant="subtitle1" paragraph>
                {pet.pet.description}
              </Typography>
            </CardContent>
            {/* <img // add the image and docs
              src={pet.img[0]}
              style={{ objectFit: 'cover', height: '250px', width: '250px', marginBottom: '12px' }}
            /> */}
          </Card>
        </CardActionArea>
      </Grid>
    );
  };

  const AddPetModal = () => {
    const [petInfo, setPetInfo] = useState({
      name: '',
      species: '',
      breed: '',
      age: '',
      gender: '',
      healthStatus: '',
      behavior: '',
      description: '',
      images: [],
      documents: [],
    });

    const handleInputChange = (field) => (event) => {
      setPetInfo({ ...petInfo, [field]: event.target.value });
    };

    const handleFileChange = (field) => (event) => {
      const files = event.target.files;
      setPetInfo({ ...petInfo, [field]: files });
    };

    const handleSubmit = () => {
      console.log('Pet Information:', petInfo);
      setModalOpen(false);
    };

    return (
      <Dialog open={isModalOpen} onClose={() => setModalOpen(false)}>
        <DialogTitle>Add a New Pet</DialogTitle>
        <DialogContent>
          <TextField
            label="Name"
            variant="outlined"
            fullWidth
            margin="normal"
            value={petInfo.name}
            onChange={handleInputChange('name')}
          />
          <TextField
            label="Species"
            variant="outlined"
            fullWidth
            margin="normal"
            value={petInfo.species}
            onChange={handleInputChange('species')}
          />
          <TextField
            label="Breed"
            variant="outlined"
            fullWidth
            margin="normal"
            value={petInfo.breed}
            onChange={handleInputChange('breed')}
          />
          <TextField
            label="Age"
            variant="outlined"
            fullWidth
            margin="normal"
            value={petInfo.age}
            onChange={handleInputChange('age')}
          />
          <TextField
            label="Gender"
            variant="outlined"
            fullWidth
            margin="normal"
            value={petInfo.gender}
            onChange={handleInputChange('gender')}
          />
          <TextField
            label="Health Status"
            variant="outlined"
            fullWidth
            margin="normal"
            value={petInfo.healthStatus}
            onChange={handleInputChange('healthStatus')}
          />
          <TextField
            label="Behavior"
            variant="outlined"
            fullWidth
            margin="normal"
            value={petInfo.behavior}
            onChange={handleInputChange('behavior')}
          />
          <TextField
            label="Description"
            variant="outlined"
            fullWidth
            multiline
            rows={4}
            margin="normal"
            value={petInfo.description}
            onChange={handleInputChange('description')}
          />
          <div style={{ marginBottom: '16px' }}>
            <label style={{ marginRight: '8px' }}>Images:</label>
            <input type="file" onChange={handleFileChange('images')} accept="image/*" multiple />
          </div>

          <div style={{ marginBottom: '16px' }}>
            <label style={{ marginRight: '8px' }}>Documents:</label>
            <input type="file" onChange={handleFileChange('documents')} multiple />
          </div>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setModalOpen(false)}>Cancel</Button>
          <Button onClick={handleSubmit} variant="contained" color="primary">
            Add Pet
          </Button>
        </DialogActions>
      </Dialog>
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


      {parsedAdopterData.role == 0 || parsedAdopterData.role == 1 && (
      <Button
        onClick={() => setModalOpen(true)}
        variant="contained"
        color="primary"
        sx={{ position: 'fixed', bottom: 16, right: 70 }}
      >
        Add Pet
      </Button>
     )}

      <AddPetModal />

      <hr style={{ margin: '20px 0', borderTop: '1px solid #ccc' }} />
    </main>
  );
}
