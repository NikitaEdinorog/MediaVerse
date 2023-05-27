import React, {useEffect, useState} from "react";
import {useLocalState} from "../../util/useLocalStorage";
import Header from "../Header";
import {Button, Card, Col, Container, Form, Modal, Row} from "react-bootstrap";
import Register from "../register/Register";
import GetUsernameFroJwt from "../../util/GetUsernameFroJwt";
import getMyProfile from "./getProfile";
import {Authenticate} from "../login/Authenticate";
import ProjectLogo from "../ProjectLogo";
import button from "bootstrap/js/src/button";
import modalPostData from "../post/post";
import post from "../post/post";

const MyProfile = () => {

    const [jwt, setJwt] = useLocalState("", "jwt");
    const [ProfileDTO, setProfileDTO] = useState(null);
    useEffect(() => {
        if (!ProfileDTO) {
            getMyProfile(jwt)
                .then((response) => {
                        if (response !== 403 && response !== 500) {
                            console.log(response[0]);
                            setProfileDTO(response[0]);
                        } else {
                            if (response === 403) {
                                console.log(response)
                                //setMatchingPasswordMessage(<Form.Label className={"LoginFont"}>Неверные данные</Form.Label>)
                            } else {
                                console.log(response);
                                //setMatchingPasswordMessage(<Form.Label className={"LoginFont"}>Что-то случилос... Попробуйте позже</Form.Label>)
                            }

                        }
                    }
                );
        }
    }, [])


    const [modalPostShow, setModalPostShow] = useState(false);
    const [modalPostData, setModalPostData] = useState(null);
    const [carouselItems, setCarouselItems] = useState([]);

    useEffect(() => {
        if (modalPostData) {
            const carouselItemsArr = [];
            console.log(modalPostData);
            for (let i = 0; i < modalPostData.images.length; i++) {
                carouselItemsArr.push(i === 0 ?
                    (<div className="cItem carousel-item active "
                          style={{backgroundColor: "#d3eae4"}}>
                        <div className={"d-flex justify-content-center"}>
                            <img src={modalPostData.images[i].path} style={{}} alt={"..."}
                                 className={"CarouselItemImage d-block w-100 h-100"}/>
                        </div>
                    </div>)
                    : (<div className="cItem carousel-item"
                            style={{backgroundColor: "#d3eae4"}}>
                        <div className={"d-flex justify-content-center"}>
                            <img src={modalPostData.images[i].path} className={"CarouselItemImage d-block"} style={{}}
                                 alt={"..."}/>
                        </div>
                    </div>)
                )
            }
            setCarouselItems(carouselItemsArr)
        }


    }, [modalPostData])

    const [carouselH, setCarouselH] = useState(window.innerHeight * 0.2)


    return (
        <React.Fragment>

            <div style={{backgroundColor: "#b0dacf"}} className={"vh-100"}>
                <div style={{backgroundColor: "#b0dacf"}} className={""}>


                    <Container style={{
                        paddingTop: 80,
                        backgroundColor: "#d3eae4",
                        borderBottomLeftRadius: 6,
                        borderBottomRightRadius: 6
                    }} className={""}>
                        {ProfileDTO ? (
                            <>
                                <Header userLogo={ProfileDTO.user.userImagePath}/>
                                <Row className="pt-5 Font">

                                    <Col className={"col-auto ps-4"}>
                                        <img
                                            style={{maxWidth: 500, maxHeight: 350, borderRadius: 6}}
                                            src={ProfileDTO.user.userImagePath}
                                            alt="Творение затерялось..."/>
                                    </Col>
                                    <Col className={"ps-4 pt-4"}>
                                        <div>
                                            <h3 className={"Font fw-bold"}>{ProfileDTO.user.firstName} {ProfileDTO.user.secondName}</h3>
                                            <h5 className={"Font"}> @{ProfileDTO.user.username}</h5>
                                            <p className={"Font w-50"}>{ProfileDTO.user.description}</p>
                                        </div>
                                    </Col>
                                </Row>
                                <Container className={"d-flex justify-content-center"}>
                                    <a href={"/new-post"} className={"w-50"}>
                                        <Button className={"Font btn-light w-100 mt-3 text-decoration-none"}
                                                role={"button"}>Добавить творение</Button>
                                    </a>

                                </Container>

                                <Row xs={1} sm={2} md={3} lg={4} className="justify-content-center g-1 m-3">
                                    {ProfileDTO.posts.map(post => (
                                            <Col>
                                                <Card style={{borderRadius: 12}} onClick={() => {
                                                    setModalPostData(post);
                                                    setModalPostShow(true);
                                                }}
                                                      data-bs-toggle="modal" data-bs-target="#exampleModal">
                                                    <div className="box" style={{borderRadius: 12}}>
                                                        <img src={post.images[0].path} alt={"img"}/>


                                                        {/*<Card.Img src={post.images[0].path} alt="Card image" />*/}
                                                        {/*<Card.ImgOverlay>*/}
                                                        {/*    <Card.Title>Card title</Card.Title>*/}
                                                        {/*    <Card.Text>*/}
                                                        {/*        This is a wider card with supporting text below as a natural lead-in*/}
                                                        {/*        to additional content. This content is a little bit longer.*/}
                                                        {/*    </Card.Text>*/}
                                                        {/*    <Card.Text>Last updated 3 mins ago</Card.Text>*/}
                                                        {/*</Card.ImgOverlay>*/}

                                                    </div>
                                                    <Card.ImgOverlay className={"justify-content-between"}>
                                                        <div
                                                            className={"d-flex h-100  w-100 align-items-end justify-content-center"}>
                                                            <Row className={"justify-content-between"}>
                                                                <Button className={"btn-light btn-sm mx-2"} style={{
                                                                    fontSize: 10,
                                                                    float: "left",
                                                                    width: "auto",
                                                                    padding: 0,
                                                                    margin: 0,
                                                                }}>Кол</Button>
                                                                <Button className={"btn-light btn-sm mx-2"} style={{
                                                                    fontSize: 10,
                                                                    float: "left",
                                                                    width: "auto",
                                                                    padding: 0,
                                                                    margin: 0,
                                                                }}>Нра</Button>
                                                                <Button className={"btn-light btn-sm mx-2"} style={{
                                                                    fontSize: 10,
                                                                    float: "left",
                                                                    width: "auto",
                                                                    padding: 0,
                                                                    margin: 0,
                                                                }}>Ком</Button>
                                                            </Row>
                                                        </div>
                                                    </Card.ImgOverlay>
                                                </Card>
                                            </Col>
                                            // <h3>ID: {post.id}</h3>
                                            // <h3>Text: {post.description}</h3>
                                            // <h3>Created: {post.timestamp}</h3>
                                            // <h3>Tags: </h3>
                                            // {post.tags.map(tag => (
                                            //         <div>
                                            //             <h2>{tag.name}</h2>
                                            //         </div>
                                            //     )
                                            // )
                                            // }
                                        )
                                    )}
                                </Row>


                            </>
                        ) : (
                            <div>
                                <h2>Что-то пошло не так...</h2>
                            </div>
                        )

                        }

                    </Container>
                </div>
            </div>


            {/*<Modal*/}
            {/*    show={modalPostShow} onHide={() => {*/}

            {/*    setModalPostShow(false);*/}
            {/*    setModalPostData(null);*/}
            {/*}}*/}
            {/*    size="xl"*/}

            {/*    aria-labelledby="contained-modal-title-vcenter"*/}
            {/*    centered*/}
            {/*    dialogClassName={""}*/}
            {/*    className={"Font modal-"}*/}
            {/*>*/}
            {/*    */}
            <div className="Font modal fade " id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div className="DialogWidth modal-dialog modal-dialog-centered modal-lg">
                    <div className=" modal-content bg-transparent">

                        {modalPostData ? (
                                <Row className={"d-flex CarCol justify-content-center col-auto"} style={{
                                    backgroundColor: "#d3eae4",
                                    margin: 0, padding: 0,
                                    borderRadius: 12,
                                }}>
                                    <Col className={"ps-0"}>
                                        <div id="carouselExampleFade"
                                             className="carousel slide carousel-fade  "
                                             data-bs-ride="carousel">
                                            <div className="d-flex carousel-inner"
                                                 style={{
                                                     alignItems: "center",
                                                     borderRadius: 12
                                                 }}>
                                                {carouselItems}
                                            </div>
                                            <button className="carousel-control-prev" type="button"
                                                    data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                                                            <span className="carousel-control-prev-icon"
                                                                  aria-hidden="true"></span>
                                                <span className="visually-hidden">Previous</span>
                                            </button>
                                            <button className="carousel-control-next" type="button"
                                                    data-bs-target="#carouselExampleFade" data-bs-slide="next">
                                                            <span className="carousel-control-next-icon"
                                                                  aria-hidden="true"></span>
                                                <span className="visually-hidden">Next</span>
                                            </button>
                                        </div>
                                    </Col>
                                    <Col className={""}>
                                        <div className={"d-flex mt-5"}>
                                            <img alt="not" src={ProfileDTO.user.userImagePath} width={50} height={50}
                                                 style={{borderRadius: 12}}
                                                 className="d-inline-block"/>
                                            <h4>{modalPostData.authorId}</h4>
                                        </div>
                                        <p className={"mt-4 ms-2"} style={{fontSize: 20}}>{modalPostData.description}</p>

                                        <div>
                                            {modalPostData.tags.map((tag) => {
                                                return (<Button className={"btn-dark btn-sm m-1"} style={{
                                                    minHeight: 30,
                                                    fontSize: 15,
                                                    width: "auto",
                                                    padding: 0,
                                                    margin: 0,
                                                    paddingLeft: 4,
                                                    paddingRight: 4,
                                                    borderRadius: 30,

                                                }} onClick={() => {}}>
                                                    {tag.name}
                                                </Button>)
                                            })
                                            }
                                        </div>
                                    </Col>
                                </Row>
                            ) :
                            (
                                <></>
                            )
                        }

                    </div>
                </div>
            </div>
        </React.Fragment>
    );
}

export default MyProfile;