import { Routes, Route, Link } from "react-router-dom";
import SignupPage from "./pages/SignupPage";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar";
import { Button, Stack } from "react-bootstrap";
import MainPage from "./pages/MainPage";
import SearchPage from "./pages/SearchPage";
import RecipePage from "./pages/RecipePage";
import ProfileSettingsPage from "./pages/ProfileSettingsPage";
import ProfilePage from "./pages/ProfilePage";

import CreateRecipePage from "./pages/CreateRecipePage";
import SignInPage from "./pages/SignInPage";
import { useAuthentication } from "./AuthenticationContext";

function App() {
  const { user, signOut } = useAuthentication();
  return (
    <div className="bg-light h-100 min-vh-100">
      <Navbar
        right={() =>
          user ? (
            <Stack direction="horizontal" gap={2}>
              <span>
                {user.firstName} {user.lastName}
              </span>
              <Button onClick={signOut}>Kirjaudu ulos</Button>
            </Stack>
          ) : (
            <Link to="/signin">
              <Button>Kirjaudu sisään</Button>
            </Link>
          )
        }
      />
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/search" element={<SearchPage />} />
        <Route path="/recipes/:id" element={<RecipePage />} />
        <Route path="/recipes/create" element={<CreateRecipePage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/signin" element={<SignInPage />} />
        <Route path="/profile/settings" element={<ProfileSettingsPage />} />
        <Route path="/profile/:uid" element={<ProfilePage />} />
      </Routes>
    </div>
  );
}

export default App;
