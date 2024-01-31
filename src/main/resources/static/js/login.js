const username = document.getElementById("username");
const password = document.getElementById("password");
const form = document.querySelector("form");

function showPassword(){
    if(password.type === "password")
    {
        password.type = "text";
    }
    else
    {
        password.type = "password";
    }
}
// Add an event listener to the form's submit event
form.addEventListener("submit", (event) => {
  event.preventDefault(); // Prevent default form submission behavior

  // Collect form data
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  // Construct the data to send in the request
  const data = new FormData();
  data.append("username", username);
  data.append("password", password);

  // Make the POST request using Fetch API
  fetch("/authenticateUser", {
    method: "POST",
    body: data
  })
  .then(response => {
    if (response.ok) {

      console.log("Login successful!");
      window.location.href = "/URL of WebsocketApp"; // Place the URL of WebsocketAPP
    } else {
      console.error("Login failed");
      alert("Invalid username or password");
    }
  })
  .catch(error => {
    console.error("Error:", error);
  });
});

