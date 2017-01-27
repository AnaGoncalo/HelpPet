function statusChangeCallback(response) {

    if (response.status === 'connected') {

        window.location.replace("#");
    } else if (response.status === 'not_authorized') {

        document.getElementById('status').innerHTML = 'Please log ' +
                'into this app.';
    } else {

        document.getElementById('status').innerHTML = 'Please log ' +
                'into Facebook.';
    }
}

function checkLoginState() {
    FB.getLoginStatus(function (response) {
        console.log('login status on login:', response);
        statusChangeCallback(response);
    });
}
window.fbAsyncInit = function () {
    FB.init({
        appId: '1412414065446030',
        cookie: true,
        xfbml: true,
        version: 'v2.8'
    });

    FB.getLoginStatus(function (response) {
        console.log('login status on page load:', response);
        statusChangeCallback(response);
    });
};
(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id))
        return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.8&appId=1412414065446030";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


