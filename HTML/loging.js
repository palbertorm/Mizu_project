$(document).ready(function () {
    $('#Entrar').click(function () {
        $('#Entrar').attr("disabled", true);
        fetch("http://localhost:8080/validarLoging?usuario=" + document.getElementById("usuario").value+"&password="+document.getElementById("password").value)
        .then((resp) => resp.json())
        .then(function (data) {
        var negocio = data;
        window.location = 'home.html?loging='+negocio.correo;
        });
    });
});