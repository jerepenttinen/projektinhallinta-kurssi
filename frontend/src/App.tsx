import React from 'react';
import {Navigate, Routes, Route} from "react-router-dom";
import SignupPage from './pages/SignupPage';
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar";
import { Button } from "react-bootstrap";
import MainPage from "./pages/MainPage";
import SearchPage from "./pages/SearchPage";
import RecipePage from "./pages/RecipePage";
import ProfileSettingsPage from "./pages/ProfileSettingsPage"


function App() {
  return (
    <div className="bg-light h-100 min-vh-100">
      <Navbar right={() => (<Button>Kirjaudu sisään</Button>)} />
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/search" element={<SearchPage />} />
        <Route path="/recipes" element={<RecipePage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/profile/settings" element={<ProfileSettingsPage />} />
      </Routes>
    </div>
  );
}

export default App;
