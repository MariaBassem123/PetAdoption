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
  const { user, pet } = location.state;

  console.log('User:', user.adopterId);
  console.log('Pet:', pet.pet.petId);
  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  const navigate = useNavigate();

  const handleButtonClick = async () => {
    try {

        const petId = pet.pet.petId;
        console.log(`petId: ${petId}`);

        console.log("shelterId:", pet.pet.shelterId);

        console.log('User:', user.adopterId);

        const applicationData = {
          petId: pet.pet.petId,
          shelterId: pet.pet.shelterId, // Assuming shelterId is directly accessible in shelterId.data
          adopterId: user.adopterId,
          status: 0
        };

        const response = await fetch('http://localhost:8088/applications/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          // If your backend expects credentials, include this line:
          // 'Authorization': 'Bearer ' + yourToken,
        },
        body: JSON.stringify(applicationData),
      });
      if (response.ok) {
        console.log('Application submitted successfully');
        alert('Application submitted successfully');
        // Handle success, e.g., show a success message or navigate to a different page
      } else {
        console.error('Failed to submit application:', response.statusText);
        alert('You submit this application before!');
        // Handle error, e.g., show an error message to the user
      }
    } catch (error) {
      console.error("Error fetching shelterId or submitting application:", error);
    }



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
        <AdoptButton variant="contained" color="primary" onClick={() => handleButtonClick()}>
          Adopt the Pet
        </AdoptButton>
      </RootContainer>
    </Container>
  );
}

export default PetDetailPage;
