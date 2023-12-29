import React from 'react';
import { useParams, Link } from 'react-router-dom';
import { styled } from '@mui/system';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import { useNavigate } from 'react-router-dom'; // Import useNavigate

const RootContainer = styled('div')({
    
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  border: '1px solid #ccc',
  borderRadius: '10px',
  padding: '20px',
  maxWidth: '800px',
  margin: 'auto',
  background: 'linear-gradient(to bottom, #f5f5f5, #e0e0e0)',
  
});

const ContentContainer = styled('div')({
  display: 'flex',
  flexDirection: 'row',
  justifyContent: 'space-between',
  maxWidth: '1000px',
  margin: 'auto',
});

const PetImage = styled(CardMedia)({
  maxWidth: '40%',
  height: 'auto',
  borderRadius: '10px',
});

const PetDetails = styled(CardContent)({
    
  textAlign: 'center',
  marginBottom: '20px',
});

const AdoptButton = styled(Button)({
  position: 'absolute',
  bottom: '20px',
  left: '50%',
  transform: 'translateX(-50%)',
});

const BoldText = styled('span')({
  fontWeight: 'bold',
  color: 'black',
});

const DescriptionTypography = styled(Typography)({
  maxWidth: '800px',
  margin: '0 auto 20px',
  textAlign: 'justify',
  color: '#757575',
});

function PetDetailPage() {
  const { id } = useParams();
  console.log("id: "+id)

  const petDetails = {
    
    name: 'Lulu Dog',
    species: 'Dog',
    breed: 'Unknown',
    age: '2 years',
    gender: 'Male',
    healthStatus: 'Good',
    behavior: 'Friendly',
    description:
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
    images: ['https://source.unsplash.com/random?dog'],
  };
  const navigate = useNavigate();
  const handleButtonClick = (id) => {
    // Handle the click event and navigate to the PetDetailPage with the postId
    navigate(`/form/${id}`);
  };

  return (
    
    <Container style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', height: '100vh' }}>
      <RootContainer>
        <ContentContainer>
          <PetDetails>
            <Typography variant="h4" gutterBottom>
              <BoldText>{petDetails.name}</BoldText>
            </Typography>
            <Typography variant="body1" paragraph>
              <BoldText>Species:</BoldText> {petDetails.species} | <BoldText>Breed:</BoldText> {petDetails.breed} |{' '}
              <BoldText>Age:</BoldText> {petDetails.age} | <BoldText>Gender:</BoldText> {petDetails.gender} |{' '}
              <BoldText>Health Status:</BoldText> {petDetails.healthStatus}
            </Typography>
            <Typography variant="body1" paragraph>
              <BoldText>Behavior:</BoldText> {petDetails.behavior}
            </Typography>
            <DescriptionTypography variant="body1" paragraph>
              <BoldText>Description:</BoldText> {petDetails.description}
            </DescriptionTypography>
          </PetDetails>
          <PetImage component="img" alt={petDetails.name} height="140" image={petDetails.images[0]} />
        </ContentContainer>
          <AdoptButton variant="contained" color="primary" onClick={() => handleButtonClick(id)}>
            Adopt the Pet
          </AdoptButton>
      </RootContainer>
    </Container>
  );
}

export default PetDetailPage;
