import { useAuthentication } from "../AuthenticationContext";
import { Navigate } from "react-router-dom";

export function Unauthenticated(props: { children: React.ReactElement }) {
  const { user } = useAuthentication();

  if (user) {
    return <Navigate to="/" replace />;
  }
  return <>{props.children}</>;
}
