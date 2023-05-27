import QueryBack from "../../util/QueryBack";
import {useLocalState} from "../../util/useLocalStorage";


function  getMyProfile(jwt) {
    return QueryBack("/my-profile", "get", jwt, null, null)
        .then((response) => {
            if (response !== 403 && response !== 500) {
                return response;

            } else {
                return response;
            }
        })
        .catch((msg) => {
            console.log("catch")
            return msg})
}

export default getMyProfile;


