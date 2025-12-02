document.getElementById("contactForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const request = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        email: document.getElementById("email").value,
        phoneNumber: document.getElementById("phone").value,
        description: document.getElementById("description").value
    };

    const res = await fetch("/api/offer-requests", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(request)
    });

    if (res.ok) {
        alert("Tak for din besked! Vi vender tilbage snarest.");
        document.getElementById("contactForm").reset();
    } else {
        alert("Noget gik galt. Prøv igen.");
    }
});document.getElementById("contactForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const request = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        email: document.getElementById("email").value,
        phoneNumber: document.getElementById("phone").value,
        description: document.getElementById("description").value
    };

    const res = await fetch("/api/offer-requests", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(request)
    });

    if (res.ok) {
        alert("Tak for din besked! Vi vender tilbage snarest.");
        document.getElementById("contactForm").reset();
    } else {
        alert("Noget gik galt. Prøv igen.");
    }
});