// Basic validation for password matching
const name = document.getElementById("name");
const email = document.getElementById("email");
const username = document.getElementById("username");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirm-password");

function showPassword(){
    if(password.type === "password" && confirmPassword.type === "password")
    {
        password.type = "text";
        confirmPassword.type = "text";
    }
    else
    {
        password.type = "password";
        confirmPassword.type = "password";
    }
}
function validatePassword() {
  const passwordValue = password.value;
  const confirmPasswordValue = confirmPassword.value;

  // Trim whitespace from name
  const trimmedName = name.value.trim();
  console.log(trimmedName);
  // Password length validation
  if (passwordValue.length < 8 || passwordValue.length > 15) {
    displayErrorMessage("Password must be between 8 and 15 characters long.");
    return false;
  }

  // Alphanumeric validation
  const alphanumericRegex = /^[a-zA-Z0-9]+$/; // Allow only letters and numbers
  if (!alphanumericRegex.test(passwordValue)) {
    displayErrorMessage("Password must contain only letters and numbers.");
    return false;
  }

  // Password matching validation
  if (passwordValue !== confirmPasswordValue) {
    displayErrorMessage("Password and confirmed password should be same!");
    return false;
  }

  return true; // All validations passed
}

function displayErrorMessage(message) {
  // Create the error message element
  const errorMessage = document.createElement("span");
  errorMessage.textContent = message;
  errorMessage.classList.add("error-message");

  // Clear any existing error messages
  const existingErrorMessage = document.querySelector(".error-message");
  if (existingErrorMessage) {
    existingErrorMessage.remove();
  }

  // Append the error message before the signup button
  password.form.insertBefore(errorMessage, password.form.querySelector("button"));
}

// Form submission event listener
const form = document.querySelector("form");
form.addEventListener("submit", (event) => {
  if (!validatePassword()) {
    event.preventDefault(); // Prevent form submission if validation fails
  }
});
