import {useLocalState} from "../util/useLocalStorage";
import {Container, Navbar, Row, Col, Button, Nav, NavDropdown, NavLink, Dropdown, NavItem} from 'react-bootstrap'
import './components.css'



const Header = ({username,userLogo}) => {

    const [jwt, setJwt] = useLocalState("", "jwt");

    function  handleSelect(eventKey) {
        alert(`selected ${eventKey}`)
    }

    return (

        <Navbar bg="dark" variant="dark" fixed="top">
            <Container className="d-flex flex-row justify-content-between">
                <Col className="d-flex flex-row align-items-center">
                    <img alt="not" src="images/MediaVerseWhiteLogo.png" width={35} height={35} style={{marginRight:10}}
                         className="d-inline-block"/>
                    <p className="d-inline LogoName">MEDIAVERSE</p>

                </Col>
                <Col>
                    <input type="search" className="form-control form-control-dark" placeholder="Search..."/>
                </Col>
                {!(jwt) ? (
                        <Col className="d-flex flex-row-reverse">
                            <Button className="btn-light mx-1 HeaderBtn ">Sign up Q</Button>
                            <Button className="btn-secondary mx-1 HeaderBtn">Sign in</Button>
                        </Col>
                    ) :
                    (
                        <Col className="d-flex flex-row-reverse">

                            <Nav pullRight className="HeaderNav">
                                <a href="/my-profile">
                                <img alt="not" src={userLogo} width={25} height={25}
                                     className="d-inline-block" id="collasible-nav-dropdown"/> </a>
                                <NavDropdown title={username} id="collasible-nav-dropdown" className="HeaderDropdown">
                                    <NavDropdown.Item className="HeaderItem" href="/my-profile">My profile</NavDropdown.Item>
                                    <NavDropdown.Item className="HeaderItem" href="#action/3.2">Another action </NavDropdown.Item>
                                    <NavDropdown.Item className="HeaderItem" href="#action/3.3">Something</NavDropdown.Item>
                                    <NavDropdown.Divider />
                                    <NavDropdown.Item className="HeaderItem" href="/">
                                        Log out
                                    </NavDropdown.Item>
                                </NavDropdown>
                            </Nav>
                        </Col>

                    )
                }


            </Container>
        </Navbar>
    )
}

export default Header;
