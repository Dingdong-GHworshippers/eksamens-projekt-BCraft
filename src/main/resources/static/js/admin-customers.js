async function loadCustomers() {
    const res = await fetch("/api/customers", { credentials: "include" });
    const customers = await res.json();

    const list = document.getElementById("customerList");
    list.innerHTML = "";

    customers.forEach(c => {
        const div = document.createElement("div");
        div.className = "card";
        div.innerHTML = `
            <h3>${c.firstName} ${c.lastName}</h3>
            <p>Email: ${c.email}</p>
            <p>Telefon: ${c.phoneNumber}</p>
            <button onclick="deleteCustomer(${c.id})">Slet</button>
        `;
        list.appendChild(div);
    });
}

async function deleteCustomer(id) {
    if (!confirm("Vil du slette kunden?")) return;

    await fetch(`/api/customers/${id}`, {
        method: "DELETE",
        credentials: "include"
    });

    loadCustomers();
}

loadCustomers();