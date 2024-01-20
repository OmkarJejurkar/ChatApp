// Basic validation for password matching
const name = document.getElementById("name");
const email = document.getElementById("email");
const username = document.getElementById("username");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirm-password");

function validatePassword() {
  if (password.value !== confirmPassword.value) {
    // Create an error message element
    const errorMessage = document.createElement("span");
    errorMessage.textContent = "Password and confirmed password should be same!";
    errorMessage.classList.add("error-message"); // Add a class for styling

    // Clear any existing error messages
    const existingErrorMessage = document.querySelector(".error-message");
    if (existingErrorMessage) {
      existingErrorMessage.remove();
    }

    // Append the error message near the password fields
    password.parentNode.insertBefore(errorMessage, password.nextSibling);
    password.form.insertBefore(errorMessage, password.form.querySelector("button"));
    return false;
  }
  return true;
}

const form = document.querySelector("form");
form.addEventListener("submit", (event) => {
  if (!validatePassword()) {
    event.preventDefault(); // Prevent form submission if passwords don't match
  }
});
