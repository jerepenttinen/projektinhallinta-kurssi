import React from "react";
import { Navbar, Nav } from 'react-bootstrap';
import {FaBook} from 'react-icons/fa';

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
                        <Nav.Link className="list-item" href="#home">Etusivu</Nav.Link>
                        <Nav.Link className="list-item" href="#link">Haku</Nav.Link>
                    </Nav>
                    <Nav>
                        {right && right()}
                    </Nav>
                </Navbar.Collapse>
        </Navbar>
    );
}

export default MyNavbar;