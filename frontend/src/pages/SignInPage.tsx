import { useRef } from "react";
import { Button, Form } from "react-bootstrap";
import { useAuthentication } from "../AuthenticationContext";

export default function SignInPage() {
  const formInputElements = {
    emailRef: useRef<HTMLInputElement>(null),
    passwordRef: useRef<HTMLInputElement>(null),
  };

  const { signIn } = useAuthentication();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    signIn({
      email: formInputElements.emailRef.current!.value,
      password: formInputElements.passwordRef.current!.value,
    });
  };

  return (
    <div className="signupFormContainer">
      <Form className="vstack gap-4" onSubmit={handleSubmit}>
        <h2>Kirjaudu</h2>
        <Form.Group controlId="email">
          <Form.Control
            type="email"
            placeholder="Sähköposti"
            ref={formInputElements.emailRef}
          />
        </Form.Group>

        <Form.Group controlId="password">
          <Form.Control
            type="password"
            placeholder="Salasana"
            ref={formInputElements.passwordRef}
          />
        </Form.Group>

        <Button variant="primary" type="submit" size="lg">
          Kirjaudu
        </Button>
      </Form>
    </div>
  );
}
