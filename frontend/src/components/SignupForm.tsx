import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useRef } from 'react'

const SignupForm = () => {
  const firstNameRef = useRef<HTMLInputElement>(null);
  const lastNameRef = useRef<HTMLInputElement>(null);
  const emailRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {

    event.preventDefault();

    const user = {
        firstname: firstNameRef.current?.value,
        lastname: lastNameRef.current?.value,
        email: emailRef.current?.value,
        password: passwordRef.current?.value,
    };

    const url = "localhost8000/api/users/signup";
    try{
        // const res = await axios.post(url, user);
        // const data = await res.json();
        // Tai fetch, jos ei käytetä axiosta
    } catch(err) {
        // handle error
        console.log(err);
    }
  };
  return (
    <Form className="d-grid gap-4" onSubmit={handleSubmit}>
      <h2>Rekisteröidy</h2>
      <Form.Group className="mb-3" controlId="fname">
        <Form.Control type="text" placeholder="Etunimi" ref={firstNameRef}/>
      </Form.Group>
      <Form.Group className="mb-3" controlId="lname">
        <Form.Control type="text" placeholder="Sukunimi" ref={lastNameRef}/>
      </Form.Group>
      <Form.Group className="mb-3" controlId="email">
        <Form.Control type="email" placeholder="Sähköposti" ref={emailRef}/>
      </Form.Group>

      <Form.Group className="mb-3" controlId="password">
        <Form.Control type="password" placeholder="Salasana" ref={passwordRef}/>
      </Form.Group>

      <Button variant="primary" type="submit" size="lg">
        Rekisteröidy
      </Button>
    </Form>
  );
}

export default SignupForm;