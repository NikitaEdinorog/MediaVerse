import {useLocalState} from "../../util/useLocalStorage";
import React, {useState} from "react";
import QueryBack from "../../util/QueryBack";
import {Authenticate} from "./Authenticate";
import GetUsernameFroJwt from "../../util/GetUsernameFroJwt";
import {Button, Col, Container, Form, Modal, Row} from "react-bootstrap";
import ProjectLogo from "../ProjectLogo";

const LoginPage = ({nextUrl}) => {

    const [jwt, setJwt] = useLocalState("", "jwt")
    const [usernameModal, setUsernameModal] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState(null)

    function auth() {
        Authenticate(usernameModal, password).then((response) => {
                if (response !== 403 && response !== 500) {
                    setJwt(response)
                    window.location.href = "/my-profile";
                } else {
                    if (response === 403) {
                        setErrorMessage(<Form.Label className={"LoginFont"}>Неверные данные</Form.Label>)
                    } else {
                        setErrorMessage(<Form.Label className={"LoginFont"}>Что-то случилос... Попробуйте
                            позже</Form.Label>)
                    }

                }
            }
        );
    }

    return (
        <>
            <div className={"d-flex vh-100 justify-content-center align-items-center"}
                 style={{backgroundColor: "#b0dacf"}}>

                <Col className={"d-flex justify-content-center"}>
                    <div className={"w-50 p-5"} style={{backgroundColor: "#212529", borderRadius: 12}}>
                        <div style={{backgroundColor: "#212529"}}
                             className={"d-flex d-flex flex-column align-items-center mb-3"}>
                            <ProjectLogo/>
                        </div>

                        <Form className={""} style={{
                            backgroundColor: "#212529",
                            borderBottomRightRadius: "0.4rem",
                            borderBottomLeftRadius: "0.4rem"
                        }}>
                            <Form.Group className={"d-flex flex-column align-items-center md-3 mb-4"}
                                        controlId="formBasicEmail">
                                <Form.Label className={"LoginFont LabelSize  flex-fill"}>Имя
                                    вселентийца</Form.Label>
                                <Form.Control className={"Font text-center"} type={"text"}
                                              placeholder="вселентийское имя"
                                              onChange={(e) => setUsernameModal(e.target.value)}/>
                            </Form.Group>
                            <Form.Group className={"d-flex flex-column align-items-center mb-4"}
                                        controlId="formBasicPassword">
                                <Form.Label className={"LoginFont LabelSize"}>Пароль</Form.Label>
                                <Form.Control className={"Font text-center"} type="password"
                                              placeholder="вселентийский пароль"
                                              onChange={(e) => setPassword(e.target.value)}/>
                                {errorMessage}
                            </Form.Group>
                            <Container className={"d-flex flex-column align-items-center"}>
                                <Button variant="light" type="button" className={"BtnFont"}
                                        onClick={() => auth(usernameModal, password)}>Войти</Button>
                            </Container>
                        </Form>
                    </div>
                </Col>

            </div>
        </>
    );
}

export default LoginPage;
