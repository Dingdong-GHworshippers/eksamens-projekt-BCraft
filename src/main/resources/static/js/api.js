const API_BASE = "http://localhost:8080/api";

async function apiGet(url) {
    const res = await fetch(API_BASE + url, {
        credentials: "include"
    });
    return res.json();
}

async function apiPost(url, data) {
    const res = await fetch(API_BASE + url, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
        credentials: "include"
    });
    return res.json();
}

async function apiDelete(url) {
    return fetch(API_BASE + url, {
        method: "DELETE",
        credentials: "include"
    });
}

async function apiPut(url, data) {
    const res = await fetch(API_BASE + url, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
        credentials: "include"
    });
    return res.json();
}