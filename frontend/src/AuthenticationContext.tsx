import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import { useNavigate } from "react-router-dom";

type LoginUser = {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  profileId: string;
  email: string;
};

type SignInRequest = {
  email: string;
  password: string;
};

type SignUpRequest = {
  username: string;
  firstname: string;
  lastname: string;
  email: string;
  password: string;
};

type AuthenticationContextType = {
  user: LoginUser | null;
  signIn: (credentials: SignInRequest) => Promise<void>;
  signUp: (signUpRequest: SignUpRequest) => Promise<void>;
  signOut: () => Promise<void>;
};

const AuthenticationContext = createContext<AuthenticationContextType>({
  user: null,
  signIn: () => {
    throw new Error("Function not implemented.");
  },
  signUp: () => {
    throw new Error("Function not implemented.");
  },
  signOut: () => {
    throw new Error("Function not implemented.");
  },
});

export function AuthenticationProvider({
  children,
}: {
  children: React.ReactElement;
}) {
  const [user, setUser] = useState<LoginUser | null>(null);
  const navigate = useNavigate();

  const fetchCurrentUser = useCallback(async () => {
    try {
      const res = await fetch("/api/users/current");
      const user = (await res.json()) as LoginUser;
      setUser(user);
      return user;
    } catch (e: unknown) {
      setUser(null);
      return null;
    }
  }, []);

  useEffect(() => {
    // Get the user on load if they are signed in from the previous session
    fetchCurrentUser();
  }, [fetchCurrentUser]);

  const signIn = useCallback(
    async (credentials: SignInRequest) => {
      await fetch("/api/signin", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(credentials),
      });
      if ((await fetchCurrentUser()) !== null) {
        navigate("/");
      }
    },
    [fetchCurrentUser, navigate],
  );

  const signOut = useCallback(async () => {
    await fetch("/api/signout", {
      method: "POST",
    });
    setUser(null);
  }, []);

  const signUp = useCallback(
    async (signUp: SignUpRequest) => {
      const res = await fetch("/api/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(signUp),
      });

      if (res.ok) {
        navigate("/signin");
      }
    },
    [navigate],
  );

  return (
    <AuthenticationContext.Provider value={{ user, signIn, signUp, signOut }}>
      {children}
    </AuthenticationContext.Provider>
  );
}

export const useAuthentication = () => useContext(AuthenticationContext);
