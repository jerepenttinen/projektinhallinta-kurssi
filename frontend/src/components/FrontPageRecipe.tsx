import Col from 'react-bootstrap/Col';
import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Figure from "react-bootstrap/Figure";

export interface Props{
    header?: string;

}
const FrontPageRecipe = ({ header }: Props) => {

    return(
        <Col xs={4} style={{margin: 10, marginLeft: 0}}>
            <Figure>
                <Figure.Image src='https://4.bp.blogspot.com/-ecGN1GaoT-g/Uz20paShlSI/AAAAAAAAAcc/mc7Or69LsNw/s1600/veghamp2.jpg'

                />
                <p className="fs-3 fw-bold text-center text-break">
                    Hampurilaisateria
                </p>
            </Figure>


        </Col>
    );
}

export default FrontPageRecipe;