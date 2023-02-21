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
       #2 use email regex pattern to verify that it matches email standard https://stackoverflow.com/questions/201323/how-can-i-validate-an-email-address-using-a-regular-expression?page=2&tab=active#answer-14075810
       regex pattern: ([-!#-'*+/-9=?A-Z^-~]+(\.[-!#-'*+/-9=?A-Z^-~]+)*|"([]!#-[^-~ \t]|(\\[\t -~]))+")@([0-9A-Za-z]([0-9A-Za-z-]{0,61}[0-9A-Za-z])?(\.[0-9A-Za-z]([0-9A-Za-z-]{0,61}[0-9A-Za-z])?)*|\[((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|IPv6:((((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){6}|::((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){5}|[0-9A-Fa-f]{0,4}::((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){4}|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):)?(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){3}|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,2}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){2}|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,3}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,4}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::)((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3})|(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3})|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,5}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3})|(((0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}):){0,6}(0|[1-9A-Fa-f][0-9A-Fa-f]{0,3}))?::)|(?!IPv6:)[0-9A-Za-z-]*[0-9A-Za-z]:[!-Z^-~]+)])
       :)
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