import React, { useState } from 'react';
import { useParams, useLocation, Link } from 'react-router-dom';
import { styled } from '@mui/system';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import { useNavigate } from 'react-router-dom';

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
  cursor: 'pointer',
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
  const location = useLocation();
  const pet = location.state?.pet;

  console.log(pet)
  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  const navigate = useNavigate();
  const handleButtonClick = (id) => {
    // Handle the click event and navigate to the PetDetailPage with the postId
    navigate(`/form/${id}`);
  };

  const handleImageClick = () => {
    // Handle image click to navigate to the next image
    setCurrentImageIndex((prevIndex) => (prevIndex + 1) % pet.img.length);
  };

  return (
    <Container style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', height: '100vh' }}>
      <RootContainer>
        <ContentContainer>
          <PetDetails>
            <Typography variant="h4" gutterBottom>
              <BoldText>{pet.pet.name}</BoldText>
            </Typography>
            <Typography variant="body1" paragraph>
              <BoldText>Species:</BoldText> {pet.pet.species} | <BoldText>Breed:</BoldText> {pet.pet.breed} |{' '}
              <BoldText>Age:</BoldText> {pet.pet.age} | <BoldText>Gender:</BoldText> {pet.pet.gender} |{' '}
              <BoldText>Health Status:</BoldText> {pet.pet.healthStatus}
            </Typography>
            <Typography variant="body1" paragraph>
              <BoldText>Behavior:</BoldText> {pet.pet.behavior}
            </Typography>
            <DescriptionTypography variant="body1" paragraph>
              <BoldText>Description:</BoldText> {pet.pet.description}
            </DescriptionTypography>
            </PetDetails>
          {pet.img.length > 0 ? (
            <PetImage
              component="img"
              alt={pet.pet.name}
              height="140"
              image={pet.img[currentImageIndex]}
              onClick={handleImageClick}
            />
          ) : (
            <PetImage
              component="img"
              alt={pet.pet.name}
              height="140"
              image='https://t4.ftcdn.net/jpg/04/17/87/67/360_F_417876741_pofg19rDWTv6ZmgQ8qTOgVMJ0H3N2uPh.jpg'
              onClick={handleImageClick}
            />
          )}
        </ContentContainer>
        <AdoptButton variant="contained" color="primary" onClick={() => handleButtonClick(pet.pet.petId)}>
          Adopt the Pet
        </AdoptButton>
      </RootContainer>
    </Container>
  );
}

export default PetDetailPage;
