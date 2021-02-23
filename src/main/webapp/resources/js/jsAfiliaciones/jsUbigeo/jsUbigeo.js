$("#form-registrar").submit(function (event) {
    event.preventDefault();
    var validator = $("#form-registrar").valid();

    if (validator)
    {
        registrarUbigeo();
    } else
        toast('warning', "Usted debe ingresar correctamente los datos requeridos", 'warning');


});

function registrarUbigeo()
{
                 
    var data = {         
        'txtDescripcion': $("#txtDescripcion").val(),'txtAccion' : $("#txtAccion").val(),'txtUbigeo' : $("#txtUbigeo").val(),
        'txtNivel' : $("#txtIdNivel").val(),'txtCodigo' : $("#txtCodigo").val()
    };
    
    var success = function (res) {

      
        if (res.Result === 'ok')
        {
            toast('success',res.Message,'Exito!');
            setTimeout(function () {
                    window.location.reload(1);
                }, 1000);
        }
        
        else if (res.Result === 'error')
        {
          toast('error', res.Message, 'Error!');
        }
      
    };

    var error = function (res){
        toast('error',"Error Inesperado, Refresque la Pagina",'Error');
    };


    bootbox.confirm("\u00BFEst\u00e1 seguro que desea registrar un ubigeo?\u003F", function (result) {
        if (result) {
            fn_callmethod("../sUbigeo?action=registrar", data, success, error);
        }
    });
    };

    
function btnlimpiar()
{   
    $("#txtUbigeo").val('');
    $("#txtNivel").val('PAIS');
    $("#txtDescripcion").val('');
    $("#txtAccion").val('registrar');
    $("#txtCodigo").val('');
   
   
    activaTab('tab-BuscarUbigeo');
};