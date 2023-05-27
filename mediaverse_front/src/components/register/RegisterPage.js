import {useLocalState} from "../../util/useLocalStorage";
import React, {useEffect, useState} from "react";
import {Button, Col, Container, Form, Modal} from "react-bootstrap";
import Register from "./Register";
import GetUsernameFroJwt from "../../util/GetUsernameFroJwt";
import ProjectLogo from "../ProjectLogo";

const RegisterPage = () => {




    function createLabel(text) {
        return (<Form.Label className={"LoginFont"}>{text}</Form.Label>)
    }

    const [name, setName] = useState("")
    const [nameErrorMsg, setNameErrorMsg] = useState(null)
    const [nameFlag, setNameFlag] = useState(false);
    const [jwt, setJwt] = useLocalState("","jwt")


    useEffect(() => {
        if (nameFlag) {
            if (name) {
                setNameErrorMsg(createLabel(""))
            } else {
                setNameErrorMsg(createLabel("Это поле следует заполнить..."));
            }
        }
    }, [name])

    const [secondName, setSecondName] = useState("")
    const [secondNameErrorMsg, setSecondNameErrorMsg] = useState(null)
    const [secondNameFlag, setSecondNameFlag] = useState(false);
    useEffect(() => {
        if (secondNameFlag) {
            if (secondName) {
                setSecondNameErrorMsg(createLabel(""));
            } else {
                setSecondNameErrorMsg(createLabel("Это поле следует заполнить..."));
            }
        }
    }, [secondName])

    const [usernameModal, setUsernameModal] = useState("");
    const [usernameErrorMsg, setUsernameErrorMsg] = useState(null)
    const [userNameFlag, setUserNameFlag] = useState(false);
    useEffect(() => {
        if (userNameFlag) {
            if (usernameModal) {
                setUsernameErrorMsg(createLabel(""))
            } else {
                setUsernameModal(createLabel("Это поле следует заполнить..."));
            }
        }
    }, [usernameModal])

    const [email, setEmail] = useState("")
    const [emailErrorMsg, setEmailErrorMsg] = useState(null)
    const [emailFlag, setEmailFlag] = useState(false);
    useEffect(() => {
        if (emailFlag) {
            if (email) {
                setEmailErrorMsg(createLabel(""))
            } else {
                setEmailErrorMsg(createLabel("Это поле следует заполнить..."));
            }
        }
    }, [email])

    const [password, setPassword] = useState("");
    const [passwordMessage, setPasswordMessage] = useState(null)
    const [passwordFlag, setPasswordFlag] = useState(false);
    useEffect(() => {
        if (passwordFlag) {
            if (password) {
                setPasswordMessage(createLabel(""))
            } else {
                setPasswordMessage(createLabel("Это поле следует заполнить..."));
            }
        }
    }, [password])

    const [matchingPassword, setMathingPassword] = useState("");

    function createMatchingPasswordMsg(matching) {
        return (matching ? (
            <Form.Label className={"LoginFont"}></Form.Label>
        ) : (
            <Form.Label className={"LoginFont"}>Пароли не совпадают</Form.Label>
        ))
    }

    const [matchingPasswordMessage, setMatchingPasswordMessage] = useState(createMatchingPasswordMsg(true))
    const [matchingPasswordFlag, setMatchingPasswordFlag] = useState(false);
    useEffect(() => {
        if (matchingPasswordFlag) {
            if (matchingPassword) {
                setMatchingPasswordMessage(createMatchingPasswordMsg(password === matchingPassword));
            } else {
                setMatchingPasswordMessage(createLabel("Это поле следует заполнить..."))
            }
        }
    }, [matchingPassword])

    function register(name, secongName, usernameModal, email, password, matchingPassword) {
        //check form
        if (password !== matchingPassword) return;
        if (!name) {
            setNameErrorMsg(createLabel("Это поле следует заполнить..."));
            return;
        }
        if (!secongName) {
            setSecondNameErrorMsg(createLabel("Это поле следует заполнить..."));
            return;
        }
        if (!usernameModal) {
            setUsernameErrorMsg(createLabel("Это поле следует заполнить..."));
            return;
        }
        if (!email) {
            setEmailErrorMsg(createLabel("Это поле следует заполнить..."));
            return;
        }
        if (!password) {
            setPasswordMessage(createLabel("Это поле следует заполнить..."));
            return;
        }
        if (!matchingPassword) {
            setMatchingPasswordMessage(createLabel("Это поле следует заполнить..."));
            return;
        }


        Register(name, secongName, usernameModal, email, password).then((response) => {
                if (response !== 403 && response !== 500) {
                    GetUsernameFroJwt(response);
                    setJwt(response)
                    window.location.href = "/my-profile";
                } else {
                    if (response === 403) {
                        setMatchingPasswordMessage(<Form.Label className={"LoginFont"}>Неверные данные</Form.Label>)
                    } else {
                        setMatchingPasswordMessage(<Form.Label className={"LoginFont"}>Что-то случилос... Попробуйте
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
                                        controlId="name">
                                <Form.Label className={"LoginFont LabelSize  flex-fill"}>Имя</Form.Label>
                                <Form.Control className={"Font text-center"} type={"text"}
                                              placeholder="введите ваше имя"
                                              onChange={(e) => {
                                                  setNameFlag(true);
                                                  setName(e.target.value)
                                              }}/>
                                {nameErrorMsg}
                            </Form.Group>
                            <Form.Group className={"d-flex flex-column align-items-center md-3 mb-4"}
                                        controlId="secondNane">
                                <Form.Label className={"LoginFont LabelSize  flex-fill"}>Фамилия</Form.Label>
                                <Form.Control className={"Font text-center"} type={"text"}
                                              placeholder="введите вашу фамилию"
                                              onChange={(e) => {
                                                  setSecondNameFlag(true);
                                                  setSecondName(e.target.value)
                                              }}/>
                                {secondNameErrorMsg}
                            </Form.Group>
                            <Form.Group className={"d-flex flex-column align-items-center md-3 mb-4"}
                                        controlId="username">
                                <Form.Label className={"LoginFont LabelSize  flex-fill"}>Имя вселентийца</Form.Label>
                                <Form.Control className={"Font text-center"} type={"text"}
                                              placeholder="придумайте веслентийское имя"
                                              onChange={(e) => {
                                                  setUserNameFlag(true);
                                                  setUsernameModal(e.target.value);
                                              }}/>
                                {usernameErrorMsg}
                            </Form.Group>
                            <Form.Group className={"d-flex flex-column align-items-center md-3 mb-4"}
                                        controlId="email">
                                <Form.Label className={"LoginFont LabelSize  flex-fill"}>Электронная почта</Form.Label>
                                <Form.Control className={"Font text-center"} type={"email"}
                                              placeholder="введите адрес почты"
                                              onChange={(e) => {
                                                  setEmailFlag(true);
                                                  setEmail(e.target.value);
                                              }}/>
                                {emailErrorMsg}
                            </Form.Group>
                            <Form.Group className={"d-flex flex-column align-items-center mb-4"}
                                        controlId="password">
                                <Form.Label className={"LoginFont LabelSize"}>Пароль</Form.Label>
                                <Form.Control className={"Font text-center"} type="password"
                                              placeholder="придумайте вселентийский пароль"
                                              onChange={(e) => {
                                                  setPasswordFlag(true);
                                                  setPassword(e.target.value);
                                              }}/>
                                {passwordMessage}
                            </Form.Group>
                            <Form.Group className={"d-flex flex-column align-items-center mb-4"}
                                        controlId="matchingPassword">
                                <Form.Label className={"LoginFont LabelSize"}>Повторите пароль</Form.Label>
                                <Form.Control className={"Font text-center"} type="password"
                                              placeholder="повторите вселентийский пароль"
                                              onChange={(e) => {
                                                  setMatchingPasswordFlag(true);
                                                  setMathingPassword(e.target.value);
                                              }}/>
                                {matchingPasswordMessage}
                            </Form.Group>
                            <Container className={"d-flex flex-column align-items-center"}>
                                <Button variant="light" type="button" className={"BtnFont"}
                                        onClick={() => register(name, secondName, usernameModal, email, password, matchingPassword)}>Присоединиться</Button>
                            </Container>
                        </Form>
                    </div>
                </Col>

            </div>
        </>
    );
}


export default RegisterPage;