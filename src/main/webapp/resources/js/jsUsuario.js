$(document).ready(function () {


    $("#form-login").submit(function (event) {
        event.preventDefault();
        iniciarSesion();
    });


    $('#btnIngresar').click(function (event) {
        event.preventDefault();
        iniciarSesion();

    });

    function iniciarSesion() {

        var data = {'usuario': $('#txtUsuario').val(), 'clave': $('#txtClave').val()};

        var success = function (data) {
            if(data.Result=="ok"){
                window.location =data.message;            }
            if(data.Result=="error"){
                $('#tabla').html(data.message);
            }

        };
        var error = function (responseText, status, er)
        {
            $('#tabla').html(responseText);
        };
        fn_callmethod("sUsuario?action=iniciarSesion", data, success, error);


    }
});
