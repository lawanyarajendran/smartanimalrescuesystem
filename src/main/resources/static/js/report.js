document.getElementById("reportForm")
    .addEventListener("submit", function (event) {

        event.preventDefault();

        const reportData = {

            animalName: document.getElementById("animalName").value,

            location: document.getElementById("location").value,

            injuryDescription:
            document.getElementById("injuryDescription").value,

            imageUrl:
            document.getElementById("imageUrl").value,

            reporterName:
            document.getElementById("reporterName").value,

            rescueStatus: "PENDING"
        };

        fetch("http://localhost:8080/report", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(reportData)

        })

            .then(response => {

                if (!response.ok) {
                    throw new Error("Failed to submit report");
                }

                return response.json();
            })

            .then(data => {

                alert("Animal Report Submitted Successfully ✅");

                window.location.href = "dashboard.html";
            })

            .catch(error => {

                alert("Error submitting report ❌");
                console.log(error);
            });

    });