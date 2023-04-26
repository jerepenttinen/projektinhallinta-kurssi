import { useAuthentication } from "../AuthenticationContext";
import { Navigate } from "react-router-dom";

export function Unauthenticated(props: { children: React.ReactElement }) {
  const { user } = useAuthentication();

  if (user === "loading") {
    return null;
  }

  if (user !== null) {
    return <Navigate to="/" replace />;
  }
  return <>{props.children}</>;
}
