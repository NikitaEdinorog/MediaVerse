import {Col} from "react-bootstrap";
import React from "react";

const ProjectLogo = () => {
    return (
        <>

                <Col className="d-flex flex-row align-items-center">

                    <a href={"/"} className={"d-flex flex-row align-items-center text-decoration-none"}>
                    <img alt="not" src="images/MediaVerseWhiteLogo.png" width={35} height={35} style={{marginRight: 10, marginBottom: 4}}
                         className="d-inline-block"/>
                    <p className="d-inline LogoName">MEDIAVERSE</p>
                    </a>
                </Col>

        </>
    )
}

export default ProjectLogo;