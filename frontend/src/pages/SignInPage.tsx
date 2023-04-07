import { Button, Form } from "react-bootstrap";
import { useAuthentication } from "../AuthenticationContext";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { Link } from "react-router-dom";
import "./SignupPage.css";

const signInValidator = z.object({
  email: z.string().email("Tarkista oikeinkirjoitus"),
  password: z.string(),
});

export default function SignInPage() {
  const { signIn } = useAuthentication();

  const { register, handleSubmit, formState, setError } = useForm<
    z.infer<typeof signInValidator>
  >({
    resolver: zodResolver(signInValidator),
  });

  return (
    <Form
      className="vstack py-4 gap-4 narrow-container"
      onSubmit={handleSubmit(async (data) => {
        try {
          await signIn(data);
        } catch (e: unknown) {
          if (e instanceof Error) {
            setError("root", { type: "custom", message: e.message });
          }
        }
      })}
    >
      <h2>Kirjaudu</h2>
      <Form.Group controlId="email">
        <Form.Control placeholder="Sähköposti" {...register("email")} />
        {formState.errors.email && (
          <span role="alert" className="text-danger">
            {formState.errors.email.message}
          </span>
        )}
      </Form.Group>

      <Form.Group controlId="password">
        <Form.Control
          type="password"
          placeholder="Salasana"
          {...register("password")}
        />
        {formState.errors.email && (
          <span role="alert" className="text-danger">
            {formState.errors.email.message}
          </span>
        )}
      </Form.Group>

      {formState.errors.root && (
        <span role="alert" className="text-danger">
          {formState.errors.root.message}
        </span>
      )}

      <Button variant="primary" type="submit" size="lg">
        Kirjaudu
      </Button>

      <Link to="/signup">Rekisteröidy</Link>
    </Form>
  );
}
