$(document).ready(function () {
    $('#Registrar').click(function () {
        $('#Registrar').attr("disabled", true);
        fetch("http://localhost:8080/crearNegocio?nombre=" + document.getElementById("Nombre").value + "&aforo_maximo=" + document.getElementById("Aforo").value + "&direccion=" +document.getElementById("Direccion").value + "&telefono=" + document.getElementById("Telefono").value + "&correo=" + document.getElementById("Correo").value + "&usuario="+document.getElementById("Usuario").value+"&password="+document.getElementById("Password").value,{
          method: 'POST'
        })
        .then(function(){
            window.location = 'login.html';
        });
    });
});