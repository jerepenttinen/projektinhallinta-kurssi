import { Routes, Route } from "react-router-dom";
import SignupPage from "./pages/SignupPage";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar";
import MainPage from "./pages/MainPage";
import SearchPage from "./pages/SearchPage";
import RecipePage from "./pages/RecipePage";
import ProfileSettingsPage from "./pages/ProfileSettingsPage";
import ProfilePage from "./pages/ProfilePage";

import CreateRecipePage from "./pages/CreateRecipePage";
import SignInPage from "./pages/SignInPage";
import { Protected } from "./components/Protected";
import { Unauthenticated } from "./components/Unauthenticated";

function App() {
  return (
    <div className="bg-light h-100 min-vh-100">
      <Navbar />
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/search" element={<SearchPage />} />
        <Route path="/recipes/:id" element={<RecipePage />} />
        <Route
          path="/recipes/create"
          element={
            <Protected>
              <CreateRecipePage />
            </Protected>
          }
        />
        <Route
          path="/signup"
          element={
            <Unauthenticated>
              <SignupPage />
            </Unauthenticated>
          }
        />
        <Route
          path="/signin"
          element={
            <Unauthenticated>
              <SignInPage />
            </Unauthenticated>
          }
        />
        <Route
          path="/profile/settings"
          element={
            <Protected>
              <ProfileSettingsPage />
            </Protected>
          }
        />
        <Route path="/profile/:uid" element={<ProfilePage />} />
      </Routes>
    </div>
  );
}

export default App;
