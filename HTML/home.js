/*Alberto Moura y Manuel Gomes*/
$(document).ready(function () {
  $(".FrormNuevaPersona").hide();
  $(".FormAlertaContagios").hide();
  $(".contenedorTabla2").hide();
  var paramstr = window.location.search.substr(1);
  var paramarr = paramstr.split ("=");
  var correoNegocio = paramarr[1];
  var negocio;
  fetch("http://localhost:8080/buscarNegocio?correo=" + correoNegocio)
    .then((resp) => resp.json())
    .then(function (data) {
      negocio = data;
      $("#local").text(negocio.nombre);
      fetch("http://localhost:8080/calcularAforo?id="+negocio.id)
    .then((resp) => resp.json())
    .then(function (data) {
      if(data.length>0){ 
        for (let pers of data) {
          pers=JSON.parse(pers);
          if(pers.rol=="Empleado"){
            var fila = '<tr id="rowE' + j + '"><input type="hidden" id="idpersonaE' + j + '" value="' + pers.idpersona + '"></input><td>' + pers.nombre + '</td><td>' + pers.apellido + '</td><td>' + pers.correo + '</td><td><button type="button" name="remove" id="' + j + '" class="btn btn-danger btn_remove2">Salida</button></td></tr>'; //esto seria lo que contendria la fila
            $('#mytable2 tr:first').after(fila);     
            j++;      
          }else{
            var fila = '<tr id="rowC' + i + '"><input type="hidden" id="idpersonaC' + i + '" value="' + pers.idpersona + '"></input><td>' + pers.nombre + '</td><td>' + pers.apellido + '</td><td>' + pers.correo + '</td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove">Salida</button></td></tr>'; //esto seria lo que contendria la fila
            $('#mytable tr:first').after(fila);
            i++;
          }
        }
      }     
      actualizarAforo();
    }).catch(err => {
      reject(err);
    });
    })
  var i = 0;//contador para asignar id al boton que borrara la fila
  var j = 0;
  var k= 0;
  var nombre = "";
  var apellido = "";
  var correo = "";
  var persona;
  

  $('#adicionar').click(function () {
    correo = document.getElementById("correoentrada").value;
    fetch("http://localhost:8080/buscarPersona?correo=" + correo)
      .then((resp) => resp.json())
      .then(function (data) {
        nombre = data.nombre;
        apellido = data.apellido;
        persona = data.id;
        fetch("http://localhost:8080/crearVisita?idnegocio=" + negocio.id + "&idpersona=" + persona + "&estatus=E", {
          method: 'POST'
        })
        crearLinea();
      })
  });

  function actualizarAforo(){
    $("#adicionados").text("");
    var nFilas = $("#mytable tr").length;
    $("#adicionados").append(nFilas - 1);
    $("#adicionados").attr("value",nFilas - 1);
    $(".progress-bar").attr('style','width:'+($("#adicionados").attr("value")*100)/negocio.aforo_maximo+'%;');
    desactivar();
    if($("#adicionados").attr("value")==0){
      $(".contenedorTablaEscondite").hide();
    }else{
      $(".contenedorTablaEscondite").show();
    }
  }

  function crearLinea() {
    var fila = '<tr id="rowC' + i + '"><input type="hidden" id="idpersonaC' + i + '" value="' + persona + '"></input><td>' + nombre + '</td><td>' + apellido + '</td><td>' + correo + '</td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove">Salida</button></td></tr>'; //esto seria lo que contendria la fila
    $('#mytable tr:first').after(fila);
    $("#adicionados").text(""); //esta instruccion limpia el div adicioandos para que no se vayan acumulando
    var nFilas = $("#mytable tr").length;
    $("#adicionados").append(nFilas - 1);
    $("#adicionados").attr("value",nFilas - 1);
    actualizarAforo();
    //le resto 1 para no contar la fila del header 
    document.getElementById("correoentrada").value = "";
    document.getElementById("correoentrada").focus();
    i = i + 1;
    nombre = "";
    apellido = "";
    correo = "";
  }
  function desactivar(){
    if($("#adicionados").attr("value")==negocio.aforo_maximo){
      $('#adicionar').attr("disabled", true);
      $('#correoentrada').attr("disabled", true);
    }else{
      $('#adicionar').attr("disabled", false);
      $('#correoentrada').attr("disabled", false);
    }
  }
  $(document).on('click', '.btn_remove', function () {
    var button_id = $(this).attr("id");
    fetch("http://localhost:8080/crearVisita?idnegocio=" + negocio.id + "&idpersona=" + $("#idpersonaC" + button_id).attr("value") + "&estatus=S", {
      method: 'POST'
    })
    //cuando da click obtenemos el id del boton
    $('#rowC' + button_id + '').remove(); //borra la fila
    //limpia el para que vuelva a contar las filas de la tabla
    $("#adicionados").text("");
    var nFilas = $("#mytable tr").length;
    $("#adicionados").append(nFilas - 1);
    $("#adicionados").attr("value",nFilas - 1);
    actualizarAforo();
  });

  //funciones de la misma tabla pero para empleados
  $('#adicionar2').click(function () {
    correo = document.getElementById("correoentrada2").value;
    fetch("http://localhost:8080/buscarPersona?correo=" + correo)
      .then((resp) => resp.json())
      .then(function (data) {
        nombre = data.nombre;
        apellido = data.apellido;
        persona = data.id;
        fetch("http://localhost:8080/crearVisita?idnegocio=" + negocio.id + "&idpersona=" + persona + "&estatus=E", {
          method: 'POST'
        })
        crearLinea2();
      })
  });


  function crearLinea2() {
    var fila = '<tr id="rowE' + j + '"><input type="hidden" id="idpersonaE' + j + '" value="' + persona + '"></input><td>' + nombre + '</td><td>' + apellido + '</td><td>' + correo + '</td><td><button type="button" name="remove" id="' + j + '" class="btn btn-danger btn_remove2">Salida</button></td></tr>'; //esto seria lo que contendria la fila
    $('#mytable2 tr:first').after(fila);
    $("#adicionados2").text(""); //esta instruccion limpia el div adicioandos para que no se vayan acumulando
    var nFilas = $("#mytable2 tr").length;
    $("#adicionados2").append(nFilas - 1);
    $("#adicionados2").attr("value",nFilas - 1);
    if($("#adicionados2").attr("value")==0){
      $(".contenedorTablaEscondite2").hide();
    }else{
      $(".contenedorTablaEscondite2").show();
    }
    //le resto 1 para no contar la fila del header 
    document.getElementById("correoentrada2").value = "";
    document.getElementById("correoentrada2").focus();
    i = i + 1;
    nombre = "";
    apellido = "";
    correo = "";
  }
  $(document).on('click', '.btn_remove2', function () {
    var button_id = $(this).attr("id");
    fetch("http://localhost:8080/crearVisita?idnegocio=" + negocio.id + "&idpersona=" + $("#idpersonaE" + button_id).attr("value") + "&estatus=S", {
      method: 'POST'
    })
    //cuando da click obtenemos el id del boton
    $('#rowE' + button_id + '').remove(); //borra la fila
    //limpia el para que vuelva a contar las filas de la tabla   
    $("#adicionados2").text("");
    var nFilas = $("#mytable2 tr").length;
    $("#adicionados2").append(nFilas - 1);
    $("#adicionados2").attr("value",nFilas - 1);
    if($("#adicionados2").attr("value")==0){
      $(".contenedorTablaEscondite2").hide();
    }else{
      $(".contenedorTablaEscondite2").show();
    }
  });
  $(document).on('click', '#registrarPersona', function () {
    if ($(".FrormNuevaPersona").is(":visible")){
      $(".FrormNuevaPersona").hide();
      $(".FormAlertaContagios").hide();
      $(".ContenidoLocal").show();
      $(".contenedorTabla").show();
      $(".contenedorTabla2").hide();
    }else{                            
      $(".FrormNuevaPersona").show();
      $(".ContenidoLocal").hide();
      $(".FormAlertaContagios").hide();
    }
  });
  $(document).on('click', '#alertarcontagio', function () {
    if ($(".FormAlertaContagios").is(":visible")){
      $(".FormAlertaContagios").hide();
      $(".ContenidoLocal").show();
      $(".contenedorTabla").show();
      $(".contenedorTabla2").hide();
      $(".FrormNuevaPersona").hide();
    }else{                            
      $(".FormAlertaContagios").show();
      $(".ContenidoLocal").hide();
      $(".FrormNuevaPersona").hide();
      var elmtTable = document.getElementById('mytable3');
      var tableRows = elmtTable.getElementsByTagName('tr');
      var rowCount = tableRows.length;
      for (var x=rowCount-1; x>0; x--) {
        elmtTable.removeChild(tableRows[x]);
      }
      fetch("http://localhost:8080/llenarTablaAlerta")
        .then((resp) => resp.json())
        .then(function (data) {
          for (let pers of data) {
            pers=JSON.parse(pers);
            var fila = '<tr id="rowA' + k + '"><input type="hidden" id="idpersonaA' + k + '" value="' + pers.id + '"></input><td style="color: white;">' + pers.nombre + '</td><td style="color: white;">' + pers.apellido + '</td><td id="rowA' + k + 'correo" style="color: white;">' + pers.correo + '</td><td><button type="button" name="Alerta" id="' + k + '" class="btn btn-warning btn-covid">Alertar</button></td></tr>';
            $('#mytable3 tr:first').after(fila);
            k++;
          }
        });
    }
  });
  $(document).on('click', '#manejarAforoEmpleado', function () {
    if ($(".contenedorTabla2").is(":visible")){
      $(".FormAlertaContagios").hide();
      $(".FrormNuevaPersona").hide();
      $(".contenedorTabla2").hide();
      $(".ContenidoLocal").show();
      $(".contenedorTabla").show();

    }else{                            
      $(".FormAlertaContagios").hide();
      $(".contenedorTabla").hide();
      $(".FrormNuevaPersona").hide();
      $(".ContenidoLocal").show();
      $(".contenedorTabla2").show();
      var nFilas = $("#mytable2 tr").length;
      $("#adicionados2").text("");
      $("#adicionados2").append(nFilas - 1);
      $("#adicionados2").attr("value",nFilas - 1);
      if($("#adicionados2").attr("value")==0){
        $(".contenedorTablaEscondite2").hide();
      }else{
        $(".contenedorTablaEscondite2").show();
      }
    }
  });  
  $(document).on('click', '#manejarAforoCliente', function () {
    if ($(".contenedorTabla").is(":visible")){
      $(".FormAlertaContagios").hide();
      $(".FrormNuevaPersona").hide();
      $(".contenedorTabla2").hide();
      $(".ContenidoLocal").show();
      $(".contenedorTabla").show();

    }else{                            
      $(".FormAlertaContagios").hide();
      $(".contenedorTabla2").hide();
      $(".FrormNuevaPersona").hide();
      $(".ContenidoLocal").show();
      $(".contenedorTabla").show();
      var nFilas = $("#mytable tr").length;
      $("#adicionados").text("");
      $("#adicionados").append(nFilas - 1);
      $("#adicionados").attr("value",nFilas - 1);
      if($("#adicionados").attr("value")==0){
        $(".contenedorTablaEscondite").hide();
      }else{
        $(".contenedorTablaEscondite").show();
      }
    }
  });

  $('#RegistrarPersona').click(function () {
    fetch("http://localhost:8080/crearPersona?nombre=" + document.getElementById("NombrePersona").value + "&apellido=" + document.getElementById("ApellidoPersona").value + "&telefono=" +document.getElementById("TelefonoPersona").value + "&correo=" + document.getElementById("CorreoPersona").value + "&dni_nie_pasaporte=" + document.getElementById("PasaportePersona").value + "&usuario="+document.getElementById("UsuarioPersona").value+"&contrasena="+document.getElementById("PasswordPersona").value+"&rol="+document.getElementById("CategoriaPersona").value,{
      method: 'POST'
    })
    .then(function(){
      $(".FrormNuevaPersona").hide();
      $(".ContenidoLocal").show();
      document.getElementById("NombrePersona").value="";
      document.getElementById("ApellidoPersona").value="";
      document.getElementById("TelefonoPersona").value="";
      document.getElementById("CorreoPersona").value="";
      document.getElementById("PasaportePersona").value="";
      document.getElementById("UsuarioPersona").value="";
      document.getElementById("PasswordPersona").value="";
      document.getElementById("CategoriaPersona").value="default";
    });
  });
  $(document).on('click', '.btn-covid', function () {
    var button_id = $(this).attr("id");
    fetch("http://localhost:8080/Alertar?correo="+document.getElementById("rowA"+button_id+"correo").textContent)
    .then(function(){
      $(".FormAlertaContagios").hide();
      $(".ContenidoLocal").show(); 
      $(".contenedorTabla").show();
    });
  });
  $(document).on('click', '#dropdownMenuButton', function () {
    if ($(".dropdown-menu").is(":visible")){
      $(".dropdown-menu").hide();
    }else{
      $(".dropdown-menu").show();
    }
  });
});