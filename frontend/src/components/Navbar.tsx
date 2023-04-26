import { Navbar, Nav, Stack, Button } from "react-bootstrap";
import { FaBook } from "react-icons/fa";
import { Link } from "react-router-dom";
import { useAuthentication } from "../AuthenticationContext";

function MyNavbar() {
  const { user, signOut } = useAuthentication();
  return (
    <Navbar bg="light" className="px-3 shadow" expand="lg" sticky="top">
      <Link to="/" className="navbar-brand brand">
        <FaBook /> Keittokirja
      </Link>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
          <Link to="/" className="nav-link">
            Etusivu
          </Link>
          <Link to="/search" className="nav-link" id="search-link">
            Haku
          </Link>
          {user !== "loading" && user ? (
            <>
              <Link to="/recipes/create" className="nav-link" id="new-recipe-link">
                Uusi resepti
              </Link>
              <Link to="/profile/settings" className="nav-link" id="edit-profile-link">
                Asetukset
              </Link>
            </>
          ) : null}
        </Nav>
        <Nav>
          {user !== "loading" && user ? (
            <Stack direction="horizontal" gap={2}>
              <Link to={`/profile/${user.profileId}`} id="profile-link">
                {user.firstName} {user.lastName}
              </Link>
              <Button onClick={signOut}>Kirjaudu ulos</Button>
            </Stack>
          ) : (
            <Link to="/signin">
              <Button>Kirjaudu sisään</Button>
            </Link>
          )}
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}

export default MyNavbar;
