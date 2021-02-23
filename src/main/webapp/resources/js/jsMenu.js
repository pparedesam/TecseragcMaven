$('#cambioClave').click(function (event) {
    $("#modalCambiarClave").modal();
});

$('#frmCambiarClave').submit(function (event) {
    event.preventDefault();
    var validator = $("#frmCambiarClave").valid();
    if (validator)
    {
        var txtClaveNue = $("#txtClaveNue").val();
        var txtClaveConfNue = $("#txtClaveConfNue").val();
        if (txtClaveNue === txtClaveConfNue) {
            cambiarClave();
            $('#modalCambiarClave').modal('toggle');
            clearmodalContrasenia();
        } else {
            toast('warning', "La Contraseña Nueva No Coinciden", 'warning');
        }
    } else {
        toast('warning', "DEBE INGRESAR CORRECTAMENTE LOS DATOS", 'warning');
    }

});
function cambiarClave() {
    var data = {'claveactual': $("#txtClaveAct").val(), 'clavenueva': $("#txtClaveNue").val()};

    var success = function (res) {
        if (res.Result == 'OK') {
            if (res.resultado == true) {
                toast('success', 'Cambio de Contraseña Exitoso', 'Exito!');
            } else {
                toast('error', 'Verifique si la Contraseña Actual esta Correcta', 'Error!');
            }
        } else {
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (res) {
        toast('error', res.Message, 'Error!');
    };
    bootbox.confirm("\u00BFEst\u00e1 Seguro que Desea Cambiar su Contraseña\u003F", function (result) {
        if (result) {
            fn_callmethod("sPrincipal?action=cambiarClave", data, success, error);
        }
    });


}
;


function clearmodalContrasenia() {
    $("#txtClaveAct").val("");
    $("#txtClaveNue").val("");
    $("#txtClaveConfNue").val("");
    ;

}



$("#frmCambiarClave").validate({
    rules: {
        txtClaveAct: {required: true, },
        txtClaveNue: {required: true, minlength: 6, maxlength: 16},
        txtClaveConfNue: {required: true}
    },
    messages:
            {
                txtClaveAct: {required: "Ingrese la Contraseña Actual"},
                txtClaveNue: {required: "Ingrese la Contraseña Nueva", minlength: "La contraseña nueva debe tener almenos 6 caracteres"},
                txtClaveConfNue: {required: "Ingrese la Contraseña Nueva Para Confirmar"},
            }
});
