import jwt from 'jwt-decode'
import jwtDecode from "jwt-decode";

function GetUsernameFroJwt(jwt){
    const decoded = jwtDecode(jwt);
    return decoded.sub;
}

export default GetUsernameFroJwt;