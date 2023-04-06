import { Button, Form } from "react-bootstrap";
import { useAuthentication } from "../AuthenticationContext";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { Link } from "react-router-dom";

const signInValidator = z.object({
  email: z.string().email("Tarkista oikeinkirjoitus"),
  password: z.string(),
});

export default function SignInPage() {
  const { signIn } = useAuthentication();

  const { register, handleSubmit, formState } = useForm<
    z.infer<typeof signInValidator>
  >({
    resolver: zodResolver(signInValidator),
  });

  return (
    <div className="signupFormContainer">
      <Form
        className="vstack gap-4"
        onSubmit={handleSubmit((data) => {
          signIn(data);
        })}
      >
        <h2>Kirjaudu</h2>
        <Form.Group controlId="email">
          <Form.Control placeholder="Sähköposti" {...register("email")} />
          {formState.errors.email && (
            <p role="alert" className="text-danger">
              {formState.errors.email.message}
            </p>
          )}
        </Form.Group>

        <Form.Group controlId="password">
          <Form.Control
            type="password"
            placeholder="Salasana"
            {...register("password")}
          />
          {formState.errors.email && (
            <p role="alert" className="text-danger">
              {formState.errors.email.message}
            </p>
          )}
        </Form.Group>

        <Button variant="primary" type="submit" size="lg">
          Kirjaudu
        </Button>
      </Form>
    </div>
  );
}
