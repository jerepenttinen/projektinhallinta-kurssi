import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import { Stack } from "react-bootstrap";
import FrontPageRecipe from "../components/FrontPageRecipe";


const MainPage = () => {
    return(
            <Container>

                <h1 className='mt-5 mb-4'>
                    Reseptejä
                </h1>
                <Container>
                    <Stack className="flex-nowrap overflow-scroll" direction="horizontal">
                        <FrontPageRecipe/>
                        <FrontPageRecipe/>
                        <FrontPageRecipe/>
                        <FrontPageRecipe/>
                        <FrontPageRecipe/>
                        <FrontPageRecipe/>
                        <FrontPageRecipe/>
                    </Stack>
                </Container>
            </Container>
    );
}
export default MainPage;