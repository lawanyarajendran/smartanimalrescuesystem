const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "login.html";
}

fetchReports();

function fetchReports() {

    fetch("http://localhost:8080/reports", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
        .then(response => response.json())
        .then(data => {

            const container =
                document.getElementById("reportsContainer");

            container.innerHTML = "";

            data.forEach(report => {

                let buttonHtml = "";

                if (report.rescueStatus === "PENDING") {

                    buttonHtml = `
                        <button onclick="updateStatus(${report.id}, 'ACCEPTED')">
                            Accept Rescue
                        </button>
                    `;
                }

                else if (report.rescueStatus === "ACCEPTED") {

                    buttonHtml = `
                        <button onclick="updateStatus(${report.id}, 'RESCUED')">
                            Mark Rescued
                        </button>
                    `;
                }

                container.innerHTML += `

                <div class="report-card">

                    <img
                        src="${report.imageUrl && report.imageUrl.trim() !== ''
                    ? report.imageUrl
                    : 'images/bg.jpeg'}"
                        alt="Animal Image"
                        class="animal-image"
                    >

                    <div class="report-content">

                        <h3>${report.animalName}</h3>

                        <p>
                            <strong>Location:</strong>
                            ${report.location}
                        </p>

                        <p>
                            <strong>Injury:</strong>
                            ${report.injuryDescription}
                        </p>

                        <p>
                            <strong>Reporter:</strong>
                            ${report.reporterName}
                        </p>

                        <p>
                            <strong>Status:</strong>

                            <span class="status ${report.rescueStatus.toLowerCase()}">
                                ${report.rescueStatus}
                            </span>
                        </p>

                        ${buttonHtml}

                    </div>

                </div>
                `;
            });
        })

        .catch(error => {

            console.error(error);
            alert("Error loading reports");
        });
}

function updateStatus(id, newStatus) {

    fetch("http://localhost:8080/reports", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })

        .then(response => response.json())

        .then(data => {

            const report = data.find(r => r.id === id);

            if (!report) {
                alert("Report not found");
                return;
            }

            // Update status
            report.rescueStatus = newStatus;

            return fetch(`http://localhost:8080/report/${id}`, {

                method: "PUT",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token
                },

                body: JSON.stringify(report)
            });
        })

        .then(response => {

            if (!response.ok) {
                throw new Error("Update failed");
            }

            return response.json();
        })

        .then(() => {

            alert("Status updated successfully!");

            // Reload reports immediately
            fetchReports();
        })

        .catch(error => {

            console.error(error);
            alert("Failed to update status");
        });
}

function goBack() {
    window.location.href = "dashboard.html";
}