import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import { useNavigate } from "react-router-dom";
import { api } from "./api";
import { z } from "zod";

const loginUserValidator = z.object({
  id: z.number(),
  username: z.string(),
  firstName: z.string(),
  lastName: z.string(),
  profileId: z.number(),
  email: z.string(),
});

type LoginUser = z.infer<typeof loginUserValidator>;

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

type User = LoginUser | null | "loading";

type AuthenticationContextType = {
  user: User;
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
  const [user, setUser] = useState<User>("loading");
  const navigate = useNavigate();
  const fetchCurrentUser = useCallback(async () => {
    try {
      const res = await api.get("/api/users/current");
      const user = loginUserValidator.parse(res.data);
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
      await api.post("/api/signin", credentials).catch((e) => {
        throw new Error(e.response.data.message);
      });
      if ((await fetchCurrentUser()) !== null) {
        navigate("/");
      }
    },
    [fetchCurrentUser, navigate],
  );

  const signOut = useCallback(async () => {
    await api.post("/api/signout");
    setUser(null);
  }, []);

  const signUp = useCallback(
    async (signUp: SignUpRequest) => {
      await api.post("/api/signup", signUp).catch((e) => {
        throw new Error(e.response.data.message);
      });
      navigate("/signin");
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
