import FileDropzone from '../components/FileDropzone'
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';


const ProfileSettingsPage = () => {
  return (
    <div className="m-auto bg-white p-4 w-50" style={{minWidth:900}}>
        <h2>Asetukset</h2>
        <Form className="">
            <div className="my-4 d-flex justify-content-between" style={{height:150}}>
                <div style={{width:'15%'}}>
                    <img src="https://secure.gravatar.com/avatar/5586197d3539ebe07272af21926b496f?s=1920&d=mm&r=g"
                       className="rounded-circle h-100"
                       style={{width:'100%', maxHeight:'100%'}} >
                     </img>
                </div>
                <div className="w-75 h-100" style={{minHeight:'100%'}}>
                    <FileDropzone/>
                </div>
            </div>
            <Form.Group className="mb-3" controlId="textArea">
                <Form.Control as="textarea" placeholder="Kuvauksen muokkaus" rows={6} />
            </Form.Group>
            <Button variant="primary" size="lg">
                Tallenna
            </Button>
        </Form>
    </div>
  );
}

export default ProfileSettingsPage;