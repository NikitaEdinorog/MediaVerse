import './App.css';
import {useLocalState} from "./util/useLocalStorage";
import React from "react";
import {Route, Routes} from "react-router-dom";
import LoginPage from "./components/login/LoginPage";
import Homepage from "./homepage/homepage";
import PrivateRoute from "./PrivateRoute/PrivateRoutes";
import MyProfile from "./components/my-profile/MyProfile";
import RegisterPage from "./components/register/RegisterPage";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-utilities.css';
import 'bootstrap/dist/css/bootstrap-grid.css';
import './components/components.css';
import './components/login/login.css';
import PostPage from "./components/post/PostPage";

function App() {

    const [jwt, setJwt] = useLocalState("", "jwt");

    return (
        <Routes>
            <Route path={"/"} element={<Homepage/>}/>
            <Route path={"sign-in"} element={<LoginPage/>}/>
            <Route path={"my-profile"} element={ <PrivateRoute child={ <MyProfile/> }/> } />
            <Route path={"new-post"} element={ <PrivateRoute child={ <PostPage/> }/> } />
            <Route path={"sign-up"} element={<RegisterPage/>} />

        </Routes>
    );
}

export default App;
