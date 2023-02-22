import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useRef } from 'react'
import LoginUser from "../core/domain/loginUser";

const env = process.env

const SignupForm = () => {

  const formInputElements = {
    usernameRef: useRef<HTMLInputElement>(null),
    firstNameRef: useRef<HTMLInputElement>(null),
    lastNameRef: useRef<HTMLInputElement>(null),
    emailRef: useRef<HTMLInputElement>(null),
    passwordRef: useRef<HTMLInputElement>(null),
  }


  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {

    event.preventDefault();

    /*TODO
       #1 check that none of the input values are null/empty/undefined
    */

    const user = new LoginUser(formInputElements);

    const url = env.REACT_APP_BACKEND_URL + "/api/users/signup";

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
      <Form.Group className="mb-3" controlId="username">
        <Form.Control type="text" placeholder="Käyttäjänimi" ref={formInputElements.usernameRef}/>
      </Form.Group>
      <Form.Group className="mb-3" controlId="fname">
        <Form.Control type="text" placeholder="Etunimi" ref={formInputElements.firstNameRef}/>
      </Form.Group>
      <Form.Group className="mb-3" controlId="lname">
        <Form.Control type="text" placeholder="Sukunimi" ref={formInputElements.lastNameRef}/>
      </Form.Group>
      <Form.Group className="mb-3" controlId="email">
        <Form.Control type="email" placeholder="Sähköposti" ref={formInputElements.emailRef}/>
      </Form.Group>

      <Form.Group className="mb-3" controlId="password">
        <Form.Control type="password" placeholder="Salasana" ref={formInputElements.passwordRef}/>
      </Form.Group>

      <Button variant="primary" type="submit" size="lg">
        Rekisteröidy
      </Button>
    </Form>
  );
}

export default SignupForm;