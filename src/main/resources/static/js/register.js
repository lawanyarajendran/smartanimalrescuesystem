alert("register is working");
document
    .getElementById("registerForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        alert("submit working");

        const name =
            document.getElementById("name").value;

        const email =
            document.getElementById("email").value;

        const password =
            document.getElementById("password").value;

        const role =
            document.getElementById("role").value;

        try {

            const response = await fetch(
                "http://localhost:8080/auth/register",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body: JSON.stringify({
                        name: name,
                        email: email,
                        password: password,
                        role: role
                    })
                }
            );

            if (response.ok) {

                alert(
                    "Registration Successful"
                );

                window.location.href =
                    "login.html";

            } else {

                const error =
                    await response.text();

                alert(error);
            }

        } catch (error) {

            console.log(error);

            alert(
                "Something went wrong"
            );
        }
    });