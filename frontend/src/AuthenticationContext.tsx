import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";

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

type AuthenticationContextType = {
  user: LoginUser | null;
  signIn: (credentials: SignInRequest) => void;
  signOut: () => void;
};

const AuthenticationContext = createContext<AuthenticationContextType>({
  user: null,
  signIn: () => {
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

  const fetchCurrentUser = useCallback(() => {
    fetch("/api/users/current")
      .then((res) => res.json())
      .then((user) => setUser(user as LoginUser))
      .catch(() => setUser(null));
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
      fetchCurrentUser();
    },
    [fetchCurrentUser],
  );

  const signOut = useCallback(async () => {
    await fetch("/api/signout", {
      method: "POST",
    });
    setUser(null);
  }, []);

  return (
    <AuthenticationContext.Provider value={{ user, signIn, signOut }}>
      {children}
    </AuthenticationContext.Provider>
  );
}

export const useAuthentication = () => useContext(AuthenticationContext);
