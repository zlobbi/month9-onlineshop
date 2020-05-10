window.addEventListener('load', function () {

    function restoreUser() {
        const userAsJSON = localStorage.getItem('user');
        return JSON.parse(userAsJSON);
    }

    fetchAuthorised('http://localhost:8080/login', restoreUser());

    const but = document.getElementsByTagName('button')[0]
    but.addEventListener('click', function () {
        localStorage.clear();
    })

    function updateOptions(options) {
        const update = {...options};
        update.mode = 'cors';
        update.headers = {...options.headers};
        update.headers['Content-Type'] = 'application/json';
        const user = restoreUser();
        if (user) {
            update.headers['Authorization'] = 'Basic ' + btoa(user.username + ':' + user.password);
        }
        return update;
    }

    async function fetchAuthorised(url, options) {
        const settings = options || {};
        return await fetch(url, updateOptions(settings));
    }
})