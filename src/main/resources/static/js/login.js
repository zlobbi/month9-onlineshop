// window.addEventListener('load', function () {
//
//     function saveUser(user) {
//         const userAsJSON = JSON.stringify(user)
//         localStorage.setItem('user', userAsJSON);
//     }
//
//     const loginForm = document.getElementById('login-form');
//     loginForm.addEventListener('submit', function () {
//         const form = loginForm;
//         const data = new FormData(form);
//         const user = Object.fromEntries(data);
//         saveUser(user);
//     });
// })