import {useLocalState} from "../util/useLocalStorage";
import {Navigate} from "react-router-dom";

const PrivateRoute = ({child}) => {

    const [jwt, setJwt] = useLocalState("", "jwt");
    return jwt ? child : <Navigate to="/sign-in"/>;

};


export default PrivateRoute