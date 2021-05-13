const host = "http://192.168.1.5:8080"

export const getAllSites = (setSitesInfo) => {
    fetch('/api/v1/parser',
        {
            method: "GET",
            headers: { 'Content-Type': 'application/json;charset=utf-8' }
        })
        .then(response => response.json())
        .then(data => {
            setSitesInfo(data);
        })
        .catch(e => console.log(e));
}

export const getSiteInfo = (id, setSiteInfo) => {
    fetch(`/api/v1/parser/${id}`,
        {
            method: "GET",
            headers: { 'Content-Type': 'application/json;charset=utf-8' }
        })
        .then(response => response.json())
        .then(data => {
            setSiteInfo(data);
        })
        .catch(e => console.log(e));
}

export const deleteSite = (id) => {
    fetch(`/api/v1/parser/${id}`,
        {
            method: "DELETE",
            headers: { 'Content-Type': 'application/json;charset=utf-8' }
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(e => console.log(e));
}

//тело запроса {url : ""}
export const startParseSite = (site) => {
    fetch(`/api/v1/parser`,
        {
            method: "POST",
            headers: { 'Content-Type': 'application/json;charset=utf-8' },
            body: JSON.stringify(site)
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(e => console.log(e));
}