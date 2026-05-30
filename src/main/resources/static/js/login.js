document.getElementById("loginForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        try {

            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            if (response.ok) {

                const token = await response.text();

                localStorage.setItem("token", token);

                alert("Login Successful!");

                window.location.href = "dashboard.html";

            } else {

                alert("Invalid email or password");
            }

        } catch (error) {

            console.error(error);
            alert("Something went wrong");
        }
    });