import { useAuthentication } from "../AuthenticationContext";
import { Navigate } from "react-router-dom";

export function Protected(props: { children: React.ReactElement }) {
  const { user } = useAuthentication();

  if (user === "loading") {
    return null;
  }

  if (!user) {
    return <Navigate to="/signin" replace />;
  }
  return <>{props.children}</>;
}
