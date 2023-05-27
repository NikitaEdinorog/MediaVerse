
function QueryBack(url, requestMethod, jwt, requestBody, type) {

    const fetchData = {
        headers: {
            "Content-Type": (type ? type : "application/json"),
        },
        method: requestMethod,

    }

    if (jwt) {
        fetchData.headers.Authorization = `Bearer ${jwt}`;
    }

    if (requestBody) {
        fetchData.body = JSON.stringify(requestBody);
    }
    return fetch(url, fetchData)
        .then((response) => {
            if (response.status === 200 || response.status === 201) {
                console.log("200")
                return Promise.all([response.json(), response.headers]);
            } else if (response.status === 403) {
                console.log("403");
                return Promise.reject(403)
            } else {
                console.log("500")
                return Promise.reject(500)
            }
        })
        .catch((reason) => {
            console.log("Reject reason:"+reason);
            return reason
        });
}

export default QueryBack;