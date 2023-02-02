import React from "react";
import { Navbar, Nav } from 'react-bootstrap';
import {FaBook} from 'react-icons/fa';
import "./css/Navbar.css";
import { Link } from "react-router-dom";

type MyNavbarProps = {
    right?: () => React.ReactElement
};

function MyNavbar({right}: MyNavbarProps) {
    return (
        <Navbar bg="light" className="px-3 shadow" expand="lg" sticky="top" >
                <Navbar.Brand href="#home" className="brand">
                    <FaBook/> Keittokirja
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Link to="/"  className="nav-link" >Etusivu</Link>
                        <Link to="/search"  className="nav-link" >Haku</Link>
                    </Nav>
                    <Nav>
                        {right && right()}
                    </Nav>
                </Navbar.Collapse>
        </Navbar>
    );
}

export default MyNavbar;