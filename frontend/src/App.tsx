import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from "./components/Navbar"
import {Button} from "react-bootstrap";

function App() {
  return (
    <div className="bg-light vh-100">
        <Navbar right={() => (<Button>Kirjaudu sisään</Button>)}/>
    </div>
  );
}

export default App;
