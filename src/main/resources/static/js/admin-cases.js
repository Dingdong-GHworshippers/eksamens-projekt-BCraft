async function loadCases() {
    const res = await fetch("/api/cases", { credentials: "include" });
    const cases = await res.json();

    const list = document.getElementById("caseList");
    list.innerHTML = "";

    cases.forEach(s => {
        const div = document.createElement("div");
        div.className = "card";
        div.innerHTML = `
            <h3>${s.title}</h3>
            <p>${s.description}</p>
            <p>Kunde: ${s.customer?.firstName || ""} ${s.customer?.lastName || ""}</p>
            <button onclick="deleteCase(${s.id})">Slet</button>
        `;
        list.appendChild(div);
    });
}

async function deleteCase(id) {
    if (!confirm("Vil du slette sagen?")) return;

    await fetch(`/api/cases/${id}`, {
        method: "DELETE",
        credentials: "include"
    });

    loadCases();
}

loadCases();