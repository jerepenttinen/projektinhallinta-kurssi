import DropImages from '../components/DropImages'
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import {useState} from 'react'


const ProfileSettingsPage = () => {
    const [image, setImage] = useState<File[]>([]);
    const [imageUrl, setImageUrl] = useState<String>();
    const onImageDropped = (buffer: Uint8Array) =>{

        console.log(buffer);
        const blob = new Blob([buffer]);
        setImageUrl(URL.createObjectURL( blob ));
        console.log(blob);
    }

    return (
      <div className="m-auto bg-white p-4" style={{width:900}}>
          <h2>Asetukset</h2>
          <Form className="">
              <div className="my-4 d-flex justify-content-between " style={{height:150}}>
                  <div style={{width:'150px', height:'150px'}}>
                      <img src={imageUrl ? imageUrl : "https://secure.gravatar.com/avatar/5586197d3539ebe07272af21926b496f?s=1920&d=mm&r=g"}
                         className="rounded-circle"
                         style={{height:'100%', width:'100%'}} >
                       </img>
                  </div>
                  <div className="w-75 h-100" style={{minHeight:'100%'}}>
                      <DropImages onImageDropped={onImageDropped}/>
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