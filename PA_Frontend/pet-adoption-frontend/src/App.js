import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import SignUpSide from './component/signup';
import HomePage from './component/HomePage';
import PetDetailPage from './component/PetDetailPage';
import AdoptionForm from './component/AdoptionForm';
import SignInSide from './component/signin';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SignUpSide />} />
        <Route path="/signin" element={<SignInSide />} />
        <Route path="/home/:adopterData" element={<HomePage />} />
        <Route path="/pet/:id/:id" element={<PetDetailPage />} />
        <Route path="/form/:id" element={<AdoptionForm />} />
        <Route path="/signin" element={<SignInSide/>} />
      </Routes>
    </Router>
  );
}


export default App;

