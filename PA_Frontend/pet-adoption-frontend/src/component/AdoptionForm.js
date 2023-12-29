import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { styled } from '@mui/system';
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';

const PageContainer = styled('div')({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  height: '100vh',
  backgroundImage: 'url("https://source.unsplash.com/random?dog")',
  backgroundSize: 'cover',
  backgroundPosition: 'center',
});

const FormContainer = styled(Container)({
  borderRadius: '15px',
  display: 'flex',
  flexDirection: 'column',
  border: '1px solid #ccc',
  alignItems: 'center',
  padding: '20px',
  width: '100vh',
  backgroundColor: 'rgba(255, 255, 255, 0.8)',
});

const StyledForm = styled('form')({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  width: '100%',
});

const FormField = styled(TextField)({
  width: '60%',
  marginBottom: '16px',
});

const SubmitButton = styled(Button)({
  width: '60%',
  marginTop: '16px',
  backgroundColor: '#4caf50',
  '&:hover': {
    backgroundColor: '#45a049',
  },
});

function AdoptionForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    address: '',
  });
  const [successMessage, setSuccessMessage] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // You can handle the form submission logic here

    // Show success message
    setSuccessMessage('Successfully submitted!');

    // Navigate after a delay (you can adjust the delay)
    setTimeout(() => {
      navigate('/home');
    }, 1000);
  };

  return (
    <PageContainer>
      <FormContainer>
        {successMessage && (
          <Alert severity="success" onClose={() => setSuccessMessage(null)}>
            {successMessage}
          </Alert>
        )}
        <StyledForm onSubmit={handleSubmit}>
          <FormField
            label="Your Name"
            variant="outlined"
            name="name"
            value={formData.name}
            onChange={handleChange}
          />
          <FormField
            label="Your Email"
            variant="outlined"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
          <FormField
            label="Your Phone"
            variant="outlined"
            type="tel"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
          />
          <FormField
            label="Your Address"
            variant="outlined"
            name="address"
            value={formData.address}
            onChange={handleChange}
          />
          <SubmitButton variant="contained" color="primary" type="submit">
            Confirm Adoption
          </SubmitButton>
        </StyledForm>
      </FormContainer>
    </PageContainer>
  );
}

export default AdoptionForm;
