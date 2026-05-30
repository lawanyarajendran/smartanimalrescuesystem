const token = localStorage.getItem("token");

console.log("TOKEN:", token);

if (!token || token === "null") {
    window.location.href = "login.html";
}

document.getElementById("welcomeText")
    .innerText = "Welcome 👋";

function logout() {

    localStorage.removeItem("token");

    window.location.href = "login.html";
}