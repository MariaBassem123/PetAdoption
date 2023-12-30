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
import MenuItem from '@mui/material/MenuItem';
import axios from 'axios';

export default function Home({user}) {
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
  const [selectedFilter, setSelectedFilter] = useState('');
  const [allPets, setAllPets] = useState([]);
  const [filteredPets, setFilteredPets] = useState([]);
  const [searchValue, setSearchValue] = useState('');
  const navigate = useNavigate();
  

  const handleSearchChange = (event) => {

    console.log(allPets);
    const searchQuery = event.target.value.toLowerCase();

    const updatedFilteredPets = allPets.filter((pet) => {
      if (selectedFilter === 'breed') {
        // Filter pets based on breed containing the search query
        console.log(pet.pet.breed.toLowerCase().includes(searchQuery))
        return pet.pet.breed.toLowerCase().includes(searchQuery);
      } else if(selectedFilter === 'species'){
        console.log(pet.pet.species.toLowerCase().includes(searchQuery))
        return pet.pet.species.toLowerCase().includes(searchQuery);
      } else if(selectedFilter === 'name'){
        console.log(pet.pet.name.toLowerCase().includes(searchQuery))
        return pet.pet.name.toLowerCase().includes(searchQuery);
      } 

    });
      
    setFilteredPets(updatedFilteredPets);
  };

    useEffect(() => {
        getFeaturedPests();
      }, []);


  const handleFilterChange = (event) => {
    setSelectedFilter(event.target.value);
    console.log(event.target.value)
  };
  const getFeaturedPests = async () => {
    console.log("role= "+user.role);
    if(user.role===0 || user.role===1){
      try {
        const response = await axios.get(`${BaseUri}/pet/getAllByShelter`, {
              params: {
                shelterID: user.shelterId,
              },
            });
        console.log(response.data);
        setAllPets(response.data); 
        setFilteredPets(response.data);
      } catch (error) {
        console.error('Error fetching pets:', error.message);
      }
    }else{
      try {
        const response = await fetch(`${BaseUri}/pet/getAll`);
        if (response.ok) {
          const data = await response.json();
          setAllPets(data); // Update the allPets state with the fetched data
          console.log(data);
          setFilteredPets(data);
        } else {
          console.error('Failed to fetch pets:', response.statusText);
        }
      } catch (error) {
        console.error('Error fetching pets:', error.message);
      }
  }
  };

  useEffect(() => {
    getFeaturedPests();
  }, [user]);

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
      if(user.role == 0 || user.role == 1)
      handleUpdateClick(pet)
      else
      navigate(`/pet/${user.adopterId}/${pet.pet.petId}`, { state: { user, pet } });
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
            {pet.img && pet.img.length > 0 ? (
              <img
                src={pet.img[0]} // Assuming the first image is the main image
                alt={pet.pet.name}
                style={{ objectFit: 'cover', height: '250px', width: '250px', marginBottom: '12px' }}
              />
            ) : (
              <img
                src="https://t4.ftcdn.net/jpg/04/17/87/67/360_F_417876741_pofg19rDWTv6ZmgQ8qTOgVMJ0H3N2uPh.jpg"
                alt="Default"
                style={{ objectFit: 'cover', height: '250px', width: '250px', marginBottom: '12px' }}
              />
            )}

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
      documents: [],
    });

              
    const handleInputChange = (field) => (event) => {
      setPetInfo({ ...petInfo, [field]: event.target.value });
    };

    const handleFileChange = (field) => (event) => {
      const files = event.target.files;
  
      // Check if files are present
      if (files.length > 0) {
      setPetInfo({ ...petInfo, documents: [...petInfo.documents, ...files] });
      }
    };
    
    
    const handleSubmit = async () => {
      try {
        // Step 1: Add the pet
        const petInfoObject = {
          shelterId: user.shelterId,
          name: petInfo.name,
          birthDate: petInfo.age,
          species: petInfo.species,
          breed: petInfo.breed,
          gender: petInfo.gender,
          healthStatus: petInfo.healthStatus,
          behavior: petInfo.behavior,
          description: petInfo.description,
        };
    
        const petInfoJSON = JSON.stringify(petInfoObject);
    
        const response = await fetch(`${BaseUri}/pet/save`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: petInfoJSON,
        });
    
        if (!response.ok) {
          console.error('Failed to add pet:', response.statusText);
          return;
        }
    
        const responseBody = await response.json();
        const petId = parseInt(responseBody);
        console.log('Pet ID:', petId);

        // Step 2: Upload documents
        if (Array.isArray(petInfo.documents)) {
          const documentUploadPromises = petInfo.documents.map(async (document) => {
            try {
              console.log(document);
              const formData = new FormData();
              formData.append('attachment', document)
              formData.append('petId', petId);
              formData.append('type', document.type);
              formData.append('shelterId', user.shelterId);

              console.log(formData);

              const documentUploadResponse = await fetch(`${BaseUri}/document/save`, {
                method: 'POST',
                body: formData,
              });

              if (documentUploadResponse.ok) {
                console.log('Document uploaded successfully!');
              } else {
                console.error('Failed to upload document:', documentUploadResponse.statusText);
              }
            } catch (documentError) {
              console.error('Error uploading document:', documentError.message);
            }
          });

          await Promise.all(documentUploadPromises);
        }

        setModalOpen(false);
        getFeaturedPests();
        } catch (error) {
        console.error('Error adding pet:', error.message);
        }
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
            label="Birth Date"
            variant="outlined"
            fullWidth
            margin="normal"
            type="date"  
            InputLabelProps={{
              shrink: true,
            }}
            value={petInfo.age}
            onChange={handleInputChange('age')}
          />
          <TextField
            select
            label="Gender"
            variant="outlined"
            fullWidth
            margin="normal"
            value={petInfo.gender}
            onChange={handleInputChange('gender')}
          >
            <MenuItem value={true}>Girl</MenuItem>
            <MenuItem value={false}>Boy</MenuItem>
          </TextField>
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
            <label style={{ marginRight: '8px' }}>Documents/Images:</label>
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
  const [selectedPet, setSelectedPet] = useState(null);
  const [isUpdateModalOpen, setUpdateModalOpen] = useState(false);

  const handleUpdateClick = (pet) => {
    setSelectedPet(pet);
    setUpdateModalOpen(true);
  };

  const UpdatePetModal = () => {
    const [updatedPetInfo, setUpdatedPetInfo] = useState({
      name: selectedPet ? selectedPet.pet.name : '',
      species: selectedPet ? selectedPet.pet.species : '',
      breed: selectedPet ? selectedPet.pet.breed : '',
      age: selectedPet ? selectedPet.pet.birthDate : '',
      gender: selectedPet ? selectedPet.pet.gender : '',
      healthStatus: selectedPet ? selectedPet.pet.healthStatus : '',
      behaviour: selectedPet ? selectedPet.pet.behaviour : '',
      description: selectedPet ? selectedPet.pet.description : '',
      documents: [],
    });
    console.log(updatedPetInfo)

    const handleInputChange = (field) => (event) => {
      setUpdatedPetInfo({ ...updatedPetInfo, [field]: event.target.value });
    };

    const handleFileChange = (field) => (event) => {
      const files = event.target.files;
  
      // Check if files are present
      if (files.length > 0) {
        setUpdatedPetInfo({ ...updatedPetInfo, documents: [...updatedPetInfo.documents, ...files] });
      }
    };
    const handleUpdateSubmit = async () => {
      try {
        // Construct the updated pet information object
        const updatedPetInfoObject = {
          petId: selectedPet.pet.petId,
          shelterId: user.shelterId,
          name: updatedPetInfo.name,
          birthDate: updatedPetInfo.age,
          species: updatedPetInfo.species,
          breed: updatedPetInfo.breed,
          gender: updatedPetInfo.gender,
          healthStatus: updatedPetInfo.healthStatus,
          behaviour: updatedPetInfo.behaviour,
          description: updatedPetInfo.description,
        };

        const updatedPetInfoJSON = JSON.stringify(updatedPetInfoObject);

        // Send a request to update the pet information
        const response = await fetch(`${BaseUri}/pet/update`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: updatedPetInfoJSON,
        });

        if (!response.ok) {
          console.error('Failed to update pet:', response.statusText);
          return;
        }

        // Step 2: Upload documents
        if (Array.isArray(updatedPetInfo.documents)) {
          const documentUploadPromises = updatedPetInfo.documents.map(async (document) => {
            try {
              console.log(document);
              const formData = new FormData();
              formData.append('attachment', document)
              formData.append('petId', selectedPet.pet.petId);
              formData.append('type', document.type);
              formData.append('shelterId', user.shelterId);

              console.log(formData);

              const documentUploadResponse = await fetch(`${BaseUri}/document/save`, {
                method: 'POST',
                body: formData,
              });

              if (documentUploadResponse.ok) {
                console.log('Document uploaded successfully!');
              } else {
                console.error('Failed to upload document:', documentUploadResponse.statusText);
              }
            } catch (documentError) {
              console.error('Error uploading document:', documentError.message);
            }
          });

          await Promise.all(documentUploadPromises);
        }

        setUpdateModalOpen(false);
        getFeaturedPests();
      } catch (error) {
        console.error('Error updating pet:', error.message);
      }
    };

    return (
      <Dialog open={isUpdateModalOpen} onClose={() => setUpdateModalOpen(false)}>
        <DialogTitle>Update Pet Information</DialogTitle>
        <DialogContent>
        <TextField
            label="Name"
            variant="outlined"
            fullWidth
            margin="normal"
            value={updatedPetInfo.name}
            onChange={handleInputChange('name')}
          />
          <TextField
            label="Species"
            variant="outlined"
            fullWidth
            margin="normal"
            value={updatedPetInfo.species}
            onChange={handleInputChange('species')}
          />
          <TextField
            label="Breed"
            variant="outlined"
            fullWidth
            margin="normal"
            value={ updatedPetInfo.breed}
            onChange={handleInputChange('breed')}
          />
          <TextField
            label="Birth Date"
            variant="outlined"
            fullWidth
            margin="normal"
            type="date"  
            InputLabelProps={{
              shrink: true,
            }}
            value={updatedPetInfo.age}
            onChange={handleInputChange('age')}
          />
          <TextField
            select
            label="Gender"
            variant="outlined"
            fullWidth
            margin="normal"
            value={updatedPetInfo.gender}
            onChange={handleInputChange('gender')}
          >
            <MenuItem value={true}>Girl</MenuItem>
            <MenuItem value={false}>Boy</MenuItem>
          </TextField>
          <TextField
            label="Health Status"
            variant="outlined"
            fullWidth
            margin="normal"
            value={updatedPetInfo.healthStatus}
            onChange={handleInputChange('healthStatus')}
          />
          <TextField
            label="Behavior"
            variant="outlined"
            fullWidth
            margin="normal"
            value={updatedPetInfo.behaviour}
            onChange={handleInputChange('behavior')}
          />
          <TextField
            label="Description"
            variant="outlined"
            fullWidth
            multiline
            rows={4}
            margin="normal"
            value={updatedPetInfo.description}
            onChange={handleInputChange('description')}
          />
          <div style={{ marginBottom: '16px' }}>
            <label style={{ marginRight: '8px' }}>Documents/Images:</label>
            <input type="file" onChange={handleFileChange('documents')} multiple />
          </div>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setUpdateModalOpen(false)}>Cancel</Button>
          <Button onClick={handleUpdateSubmit} variant="contained" color="primary">
            Update Pet
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
          onChange={handleSearchChange}

        />
        
      </Box>
      <TextField
        select
        label="Filter by"
        variant="outlined"
        fullWidth
        margin="normal"
        value={selectedFilter}
        onChange={handleFilterChange}
        sx={{
          width: '40%', // Adjust the width as needed
          left: '690px', // Add some margin between the dropdown and the input field
          top: '-103px'
        }}
      >
        <MenuItem value="species">Species</MenuItem>
        <MenuItem value="breed">Breed</MenuItem>
        <MenuItem value="name">Name</MenuItem>
      </TextField>


      <Grid container spacing={4}>
        {filteredPets.map((pet) => (
          <FeaturedPet key={pet.pet.petId} pet={pet} />
        ))}
      </Grid>
        {/* {featuredPests.map((pet) => (
          <FeaturedPet
            key={pet.pet.petId}
            pet={pet}
            onUpdateClick={() => handleUpdateClick(pet)}
          />
        ))} */}

      <UpdatePetModal />


      {(user.role === 0 || user.role === 1) && (
    <Button
      onClick={() => setModalOpen(true)}
      variant="contained"
      color="primary"
      sx={{ position: 'fixed', bottom: 16, right: 200 }}
    >
      Add Pet
    </Button>
  )}


      <AddPetModal />

      <hr style={{ margin: '20px 0', borderTop: '1px solid #ccc' }} />
    </main>
  );
}
