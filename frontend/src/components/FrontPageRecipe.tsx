import Col from 'react-bootstrap/Col';
import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Figure from "react-bootstrap/Figure";
import { Link } from "react-router-dom";

export interface Props{
    header?: string,
    xs?: number,

}
const FrontPageRecipe = ({ header, xs }: Props) => {
    return(
        <Col xs={xs} className="" style={{margin: 10, marginLeft: 0}}>
            <Link to='/'>
                <Figure>
                    <Figure.Image src='https://4.bp.blogspot.com/-ecGN1GaoT-g/Uz20paShlSI/AAAAAAAAAcc/mc7Or69LsNw/s1600/veghamp2.jpg'
                        rounded
                    />
                    <p className="fs-4 fw-bold text-center text-break text-dark" >
                        {header}
                    </p>
                </Figure>
            </Link>



        </Col>
    );
}

export default FrontPageRecipe;