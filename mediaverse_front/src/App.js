import './App.css';
import {useLocalState} from "./util/useLocalStorage";
import React from "react";
import {Route, Routes} from "react-router-dom";
import Login from "./login/login";
import Homepage from "./homepage/homepage";
import PrivateRoute from "./PrivateRoute/PrivateRoutes";
import MyProfile from "./my-profile/MyProfile";
import Register from "./register/Register";
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {

    const [jwt, setJwt] = useLocalState("", "jwt");

    return (
        <Routes>

            <Route path={"/"} element={<Homepage/>}/>
            <Route path={"sign-in"} element={<Login/>}/>
            <Route path={"my-profile"} element={ <PrivateRoute child={ <MyProfile/> }/> } />
            <Route path={"sign-up"} element={<Register/>} />

        </Routes>
    );
}

export default App;
