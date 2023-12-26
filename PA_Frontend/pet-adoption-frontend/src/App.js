import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import SignUpSide from './components/signup';
import HomePage from './components/HomePage';
import PetDetailPage from './components/PetDetailPage';
import AdoptionForm from './components/AdoptionForm';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SignUpSide />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/pet/:id" element={<PetDetailPage />} />
        <Route path="/form/:id" element={<AdoptionForm />} />
      </Routes>
    </Router>
  );
}

export default App;
