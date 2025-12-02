async function loadMaterials() {
    const res = await fetch("/api/materials", { credentials: "include" });
    const materials = await res.json();

    const list = document.getElementById("materialsList");
    list.innerHTML = "";

    materials.forEach(m => {
        const div = document.createElement("div");
        div.className = "card";
        div.innerHTML = `
            <h3>${m.name}</h3>
            <p>Pris: ${m.pricePerUnit} kr / ${m.unit}</p>
            <button onclick="deleteMaterial(${m.id})">Slet</button>
        `;
        list.appendChild(div);
    });
}

async function deleteMaterial(id) {
    await fetch(`/api/materials/${id}`, {
        method: "DELETE",
        credentials: "include"
    });

    loadMaterials();
}

loadMaterials();