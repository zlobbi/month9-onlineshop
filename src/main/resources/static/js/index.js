// window.addEventListener('load', function () {
//
//     if(localStorage.getItem('user') !== null) {
//         const but = document.getElementsByClassName('navbar')[0];
//         but.addEventListener('click', function () {
//             fetchAuthorised('http://localhost:8080/profile', restoreUser());
//         })
//     }
//
//     function restoreUser() {
//         const userAsJSON = localStorage.getItem('user');
//         return JSON.parse(userAsJSON);
//     }
//
//     function updateOptions(options) {
//         const update = {...options};
//         update.mode = 'cors';
//         update.headers = {...options.headers};
//         update.headers['Content-Type'] = 'application/json';
//         const user = restoreUser();
//         if (user) {
//             update.headers['Authorization'] = 'Basic ' + btoa(user.username + ':' + user.password);
//         }
//         return update;
//     }
//
//     function fetchAuthorised(url, options) {
//         const settings = options || {};
//         return fetch(url, updateOptions(settings));
//     }
//
// })