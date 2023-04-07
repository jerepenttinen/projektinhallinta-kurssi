import { Button, Form } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useAuthentication } from "../AuthenticationContext";
import "./SignupPage.css";
import { Link } from "react-router-dom";

const signUpValidator = z.object({
  username: z.string().min(1, "Täytä käyttäjänimi"),
  firstname: z.string().min(1, "Täytä etunimi"),
  lastname: z.string().min(1, "Täytä sukunimi"),
  email: z.string().email("Tarkista oikeinkirjoitus"),
  password: z.string().min(8, "Salasanan tulee olla vähintään 8 merkkiä pitkä"),
});

const SignupPage = () => {
  const { signUp } = useAuthentication();

  const { register, handleSubmit, formState, setError } = useForm<
    z.infer<typeof signUpValidator>
  >({
    resolver: zodResolver(signUpValidator),
  });

  return (
    <Form
      className="vstack py-4 gap-4 narrow-container"
      onSubmit={handleSubmit(async (data) => {
        try {
          await signUp(data);
        } catch (e: unknown) {
          if (e instanceof Error) {
            setError("root", { type: "custom", message: e.message });
          }
        }
      })}
    >
      <h2>Rekisteröidy</h2>
      <Form.Group controlId="username">
        <Form.Control
          type="text"
          placeholder="Käyttäjänimi"
          {...register("username")}
        />
        {formState.errors.username && (
          <span role="alert" className="text-danger">
            {formState.errors.username.message}
          </span>
        )}
      </Form.Group>
      <Form.Group controlId="firstname">
        <Form.Control
          type="text"
          placeholder="Etunimi"
          {...register("firstname")}
        />
        {formState.errors.firstname && (
          <span role="alert" className="text-danger">
            {formState.errors.firstname.message}
          </span>
        )}
      </Form.Group>
      <Form.Group controlId="lastname">
        <Form.Control
          type="text"
          placeholder="Sukunimi"
          {...register("lastname")}
        />
        {formState.errors.lastname && (
          <span role="alert" className="text-danger">
            {formState.errors.lastname.message}
          </span>
        )}
      </Form.Group>
      <Form.Group controlId="email">
        <Form.Control
          type="email"
          placeholder="Sähköposti"
          {...register("email")}
        />
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
        {formState.errors.password && (
          <span role="alert" className="text-danger">
            {formState.errors.password.message}
          </span>
        )}
      </Form.Group>

      {formState.errors.root && (
        <span role="alert" className="text-danger">
          {formState.errors.root.message}
        </span>
      )}
      <Button variant="primary" type="submit" size="lg">
        Rekisteröidy
      </Button>
      <Link to="/signin">Kirjaudu sisään</Link>
    </Form>
  );
};

export default SignupPage;
