import {Badge, Button, Carousel, Col, Container, Form, FormControl, FormLabel, Modal, Row} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import {useLocalState} from "../../util/useLocalStorage";
import Header from "../Header";
import {forEach} from "react-bootstrap/ElementChildren";
import 'bootstrap/dist/js/bootstrap'
import button from "bootstrap/js/src/button";
import Select from "react-select/base";
import CreatableSelect from 'react-select/creatable';
import {components} from "react-select";


const PostPage = () => {

    const selectStyles = {
        control: (provided, state) => ({
            ...provided,
            background: '#fff',
            borderColor: '#9e9e9e',
            minHeight: '30px',
            height: '30px',
            minWidth: '190px',
            width: '190px',
            boxShadow: state.isFocused ? null : null,

        }),

        valueContainer: (provided, state) => ({
            ...provided,
            height: '30px',
            padding: '0 6px'
        }),

        input: (provided, state) => ({
            ...provided,
            margin: '0px',
            maxLength: 20
        }),
        indicatorSeparator: state => ({
            display: 'none',
        }),
        indicatorsContainer: (provided, state) => ({
            ...provided,
            height: '30px',
        }),
        menu: (provided, state) => ({
            ...provided,
            minWidth: '190px',
            width: '190px',
        }),
        menuList: (provided, state) => ({
            ...provided,
            minWidth: '190px',
            width: '190px',
        })
    };

    const Input = props => <components.Input {...props} maxLength={20}/>;


    const [jwt, setJwt] = useLocalState("", "jwt");

    function createCaroudelItem(pathes) {
        const items = [];
        for (let i = 0; i < pathes.length; i++) {
            items.push(i === 0 ?
                (<div className="carousel-item active"
                      style={{backgroundColor: "#d3eae4"}}>
                    <div className={"d-flex justify-content-center"}>
                        <img src={pathes[i]} style={{
                            maxWidth: 600, maxHeight: 700,
                        }} alt={"..."}/>
                    </div>
                </div>)
                : (<div className="carousel-item"
                        style={{backgroundColor: "#d3eae4"}}>
                    <div className={"d-flex justify-content-center"}>
                        <img src={pathes[i]} style={{
                            maxWidth: 600, maxHeight: 700,
                        }} alt={"..."}/>
                    </div>
                </div>)
            )
        }
        return items;
    }

    function createTag(text) {

        return (<Button className={"btn-dark btn-sm m-1"} style={{
            minHeight: 30,
            fontSize: 15,
            width: "auto",
            padding: 0,
            margin: 0,
            paddingLeft: 4,
            paddingRight: 4,
            borderRadius: 30,

        }} onClick={() => deleteTag(text)}>
            {text}
        </Button>)
    }

    function deleteTag(name) {
        setDeletedName(name);
    }



    const [avalability, setAvalability] = useState(false);
    const [files, setFiles] = useState([]);
    const [objectUrls, setObjectUrls] = useState([]);
    const [carouselItems, setCarouselItems] = useState
    (
        <div className="carousel-item active"
             style={{backgroundColor: "#d3eae4"}}>
            <div className={"d-flex justify-content-center"}>
                <img src={"images/EmptyPicture.png"} style={{
                    maxWidth: 600, maxHeight: 700,
                }} alt={"..."}/>
            </div>
        </div>
    )

    useEffect(() => {
        if (files.length !== 0) {
            setCarouselItems(null)
            setObjectUrls([]);
            for (let i = 0; i < files.length; i++) {
                objectUrls.push(URL.createObjectURL(files[i]))
            }
            setCarouselItems(createCaroudelItem(objectUrls))
            return () => objectUrls.map((objectUrl) => URL.revokeObjectURL(objectUrl));
        }
    }, [files])

    function onSelectFile(e) {
        let result = [];
        let msg = "";
        let flag = false;
        if (!e.target.files || e.target.files.length === 0) {
            return
        }
        for (let i = 0; i < e.target.files.length; i++) {
            if (e.target.files[i] === null) {
                msg = "С вашими файлами что-то не так...";
                flag = true;
                continue;
            }
            if (String(e.target.files[i].type).includes("image/", 0)) {
                result.push(e.target.files[i])
                if (result.length === 5) {
                    msg = "Можно загрузить не более 5 файлов...";
                    flag = true;
                    break;
                }
            } else {
                msg = "Загружен файл неверного типа...";
                flag = true;

            }
        }
        if (flag) {
            setMsg(msg)
        } else {
            setMsg("")
        }

        if (result.length !== 0) {
            setFiles(result);
            setAvalability(true);
            return false;
        } else {
            setAvalability(false);
            return true;
        }
    }


    const [descption, setDescription] = useState("");
    const [msg, setMsg] = useState("");
    const [enteredTag, setEnteredTag] = useState(null);
    const [deletedName, setDeletedName] = useState(null);
    const [badges, setBadges] = useState(new Array());
    const [options, setOptions] = useState([]);

    useEffect(() => {
        const response = fetch("/posts/new-post", {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${jwt}`
            },
            method: "get"
        })
            .then((response) => {
                if (response.status !== 403 && response.status !== 500) {
                    return Promise.all([response.json(), response]);
                } else {
                    return response.status;
                }
            })
            .then((response) => {
                if (response !== 403 && response !== 500) {
                    const arr = response[0];
                    const newOPtions = new Array();
                    for (let i =0; i<arr.length;i++){
                        newOPtions.push({value: arr[i],  label: arr[i]})
                    }
                    setOptions(newOPtions);
                } else {
                    if (response === 403) {
                        console.log(response)
                        return [];
                    } else {
                        console.log(response);
                        return [];
                    }
                }
            })


    }, [])
    useEffect(() => {
        if (enteredTag) {
            let list = new Array();
            for (let i = 0; i < badges.length; i++) {
                list.push(badges[i])
            }
            list.push(createTag(enteredTag))
            setBadges(list);
            setEnteredTag(null);
        }
    }, [enteredTag])
    useEffect(() => {
        if (deletedName !== null) {
            if (badges.length > 1) {
                let list = []
                for (let i = 0; i < badges.length; i++) {
                    if (badges[i].props.children !== deletedName) {
                        list.push(badges[i]);
                    } else {
                        badges[i] = null;
                    }
                }
                setBadges(list);
                setDeletedName(null);
            } else {
                setBadges(new Array());
                setDeletedName(null);
            }
        }
    }, [deletedName])

    const testOptions = [
        {value: 'красивая', label: 'красивая'},
        {value: 'любимая', label: 'любимая'}
    ]

    function addNewTag(name) {
        setInputSelect(null)
        setInputSelect(createSelect(options))
        setEnteredTag(name);
    }


    const createSelect = (hereOptions) => {
        const values = hereOptions;
        return (
            <CreatableSelect isClearable
                             options={values}
                             id="select"
                             placeholder={"Введите тэг..."}
                             maxMenuHeight={200}
                             theme={{borderRadius: 12,}} styles={selectStyles}
                             onChange={(e) => {
                                 if (e !== null) addNewTag(e.value);
                             }}
                             components={{Input}}/>
        )
    }
    const [inputSelect, setInputSelect] = useState(null);

    useEffect(()=>{
        setInputSelect(createSelect(options))
    }, [options])

    function growArea(element) {
        let height = element.scrollHeight;
        if (height <= 320) element.style.height = height + "px";
    }

    const publish = async () => {
        if (avalability) {
            const descriptionResult = descption;
            const tags = badges.map((badge) => badge.props.children);
            const filesResult = files;
            const formdata = new FormData();
            for (let i = 0; i < files.length; i++) {
                formdata.append('file' + (i + 1), files[i]);
            }
            formdata.append('description', descption);
            formdata.append('tags', tags);


            const res = await fetch("/posts/new-post", {
                headers: {
                    Authorization: `Bearer ${jwt}`
                },
                method: 'post',
                body: formdata
            }).catch((e) => console.log(e))
            window.location.href = "/my-profile";

        }
    }



    return (
        <React.Fragment>
            <div style={{backgroundColor: "#b0dacf"}} className={"Font vh-100"}>
                <Header/>
                <Container className={"d-flex justify-content-center"}
                           style={{paddingTop: 90, borderRadius: 12}}>
                    <Row style={{ borderRadius: 12, margin: 0, padding: 0}}>
                        <Col className={"d-flex justify-content-center"} style={{
                            backgroundColor: "#d3eae4",
                            margin: 0, padding: 0,
                            borderRadius: 12,
                            borderColor: "#b0dacf", borderWidth: 2, borderStyle: "solid",
                            borderRightWidth: 1
                        }}>
                            <div id="carouselExampleFade" className="carousel slide carousel-fade"
                                 data-bs-ride="carousel">
                                <div className="d-flex carousel-inner "
                                     style={{
                                         height: 700,
                                         width: 600,
                                         alignItems: "center",
                                         borderTopLeftRadius: 12,
                                         borderBottomLeftRadius: 12
                                     }}>
                                    {carouselItems}
                                </div>
                                <button className="carousel-control-prev" type="button"
                                        data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span className="visually-hidden">Previous</span>
                                </button>
                                <button className="carousel-control-next" type="button"
                                        data-bs-target="#carouselExampleFade" data-bs-slide="next">
                                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span className="visually-hidden">Next</span>
                                </button>
                            </div>
                        </Col>
                        <Col className={"d-flex justify-content-between"}
                             style={{
                                 backgroundColor: "#d3eae4",
                                 flexDirection: "column", margin: 0, padding: 10,
                                 borderRadius: 12,
                                 borderColor: "#b0dacf", borderWidth: 2, borderStyle: "solid",
                                 borderLeftWidth: 1
                             }}>
                            <Col style={{width: 400, margin: 0, padding: 0}}>
                                <Row style={{margin: 0, padding: 0}} className={"pt-5"}>
                                    <Form.Control as="textarea" maxLength={300}
                                                  style={{
                                                      minHeight: 150,
                                                      resize: "none",
                                                      margin: 0,
                                                      padding: 10,
                                                      borderRadius: 12,
                                                      fontSize: 20,

                                                  }}
                                                  onInput={(e) => growArea(e.target)}
                                                  onChange={(e) => setDescription(e.target.value)}
                                    />
                                </Row>
                                <div className={"justify-content-start align-content-center align-items-center pt-4"}>
                                    {inputSelect}
                                </div>
                                <Container className={"pt-4"}>
                                    {badges}
                                </Container>
                            </Col>

                            <Form.Group controlId="formFileMultiple" className="mb-3" style={{textAlign: "center"}}>
                                <Form.Label style={{textAlign: "center"}}>{msg}</Form.Label>
                                <Form.Control required={true} id={"file"} type="file" accept={"image/*"}
                                              formEncType={"multipart/form-data"}
                                              multiple onChange={(e) => {
                                    if (onSelectFile(e)) {
                                        document.getElementById("file").value = "";
                                    }
                                }}/>
                            </Form.Group>
                        </Col>
                    </Row>
                </Container>
                <div className={"d-flex mt-4 justify-content-center"} style={{}}>
                    <Button className={"btn w-25 btn-light"} type={"submit"}
                            onClick={() => publish()}>Опубликовать</Button>
                </div>
            </div>
        </React.Fragment>
    )


}

export default PostPage;