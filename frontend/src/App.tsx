import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from "./components/Navbar"
import {Button} from "react-bootstrap";
import {Navigate, Routes, Route} from "react-router-dom";
import MainPage from './pages/MainPage';
import SearchPage from './pages/SearchPage';


function App() {
  return (
    <div className="bg-light vh-100">
        <Navbar right={() => (<Button>Kirjaudu sisään</Button>)}/>
        <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/search" element={<SearchPage />} />
        </Routes>
    </div>
  );
}

export default App;
