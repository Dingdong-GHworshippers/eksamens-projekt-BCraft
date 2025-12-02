async function fetchCount(url) {
    const res = await fetch(url, { credentials: "include" });
    return (await res.json()).length;
}

async function loadDashboard() {
    document.getElementById("customersCount").textContent =
        "Kunder: " + await fetchCount("/api/customers");

    document.getElementById("casesCount").textContent =
        "Sager: " + await fetchCount("/api/cases");

    document.getElementById("materialsCount").textContent =
        "Materialer: " + await fetchCount("/api/materials");
}

loadDashboard();