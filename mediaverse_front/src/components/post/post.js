import QueryBack from "../../util/QueryBack";
import {useLocalState} from "../../util/useLocalStorage";


function Post(files, description, tags, jwt) {

    const requestbody = {
        "files": files,
        "description": description,
        "tags": tags,
    }

    const formdata = new FormData();
    for (let i = 0; i<files.length;i++){
        console.log(files[i]);
        formdata.append('file'+(i+1),files[i]);
    }
    formdata.append('description', description);
    formdata.append('tags',tags);


    console.log(formdata.get('file1'));

    return QueryBack("posts/new-post", "post", jwt, formdata, "multipart/form-data; boundary=somebound")
        .then((response) => {
            if (response !== 403 && response !== 500) {
                return response[1].get("status");
            } else {
                return response;
            }
        })
        .catch((msg) => {
            console.log("catch")
            return msg
        })

}


export default Post;