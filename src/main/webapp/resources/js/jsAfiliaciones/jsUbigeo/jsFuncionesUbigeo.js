$(function () {
    /*Obtendo la data de ubigeo*/
    ObtenerPaises();
    
    function ObtenerPaises()
    {

        var success = function (data) {

            $.each(data.Paises, function (i, item) {
                $("#idpaises").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
                $('#idpaises').trigger("refresh");
            });
            if ($("#idpaises option").length > 1) {
                $("#idpaises option:eq('01')").prop('selected', true);
            }
            $('#idpaises').select2();
            $('#idpaises').trigger('change.select2');
            $('#idpaises').change();
        };
        var error = function () {};
        fn_callmethod_getND("../sPais", success, error);
    }
    ;

    $("#idpaises").change(obtenerDepartamentos);
    $("#iddepartamento").change(obtenerProvincia);
    $("#idprovincias").change(obtenerDistrito);

    function obtenerDepartamentos() {

        var datos = {'idPais': $("#idpaises").val()};

        var success = function (data) {
            $("#iddepartamento").html("");
            $.each(data.Departamentos, function (i, item) {
                $("#iddepartamento").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
            });

            if ($("#iddepartamento option").length > 1) {
                var codDep = $("#codDep").val();
                if (codDep != 't') {              
                     $("#iddepartamento").val(codDep).prop('selected', true);                    
                } else {                        
                  $("#iddepartamento option:eq(11)").prop('selected', true);                  
                }
            }             
            $('#iddepartamento').select2();
            $('#iddepartamento').trigger('change.select2');
            $('#iddepartamento').change();
        };
        var error = function () {};
        fn_callmethod_get("../sDepartamento", datos, success, error);
    };

    function obtenerProvincia()
    {
        var datos = {'idPais': $("#idpaises").val(), 'idDepartamento': $("#iddepartamento").val()};
       
        var success = function (data) {

            if (data.Result == 'OK') {
                $("#idprovincias").html("");
                $.each(data.Provincias, function (i, item) {
                    $("#idprovincias").append("<option value='" + item.codigo + "' >" + item.descripcion + "</option>");
                });

                if ($("#idprovincias option").length > 1) {
                    var idProv = $("#codProv").val();
                    if (idProv != 't') {
                         
                        $("#idprovincias").val(idProv).prop('selected', true);
                            
                    } else {                        
                        $("#idprovincias option:eq(00)").prop('selected', true);
                              
                    }
                }
                $('#idprovincias').select2();
                $('#idprovincias').trigger('change.select2');
                $('#idprovincias').change();

            } else {
                toast('Error', data.Message, 'warning');
            }
        };
        var error = function () { };
        fn_callmethod("../sProvincia?action=obtenerddlProvincia", datos, success, error);
    };

    function obtenerDistrito()
    {
        var datos = {'idPais': $("#idpaises").val(), 'idDepartamento': $("#iddepartamento").val(), 'idProvincia': $('#idprovincias').val()};

        var success = function (data) {

            $("#iddistritos").html("");
            $.each(data.Distritos, function (i, item) {
                $("#iddistritos").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
            });

            if ($("#iddistritos option").length > 1) {
                var idDist = $("#codDist").val();
                if (idDist != 't') {
                    $("#iddistritos").val(idDist).prop('selected', true);
                } else {
                    $("#iddistritos option:eq(00)").prop('selected', true);
                }
            }
            $('#iddistritos').select2();
            $('#iddistritos').trigger('change.select2');
            $('#iddistritos').change();

        };
        var error = function () {};
        fn_callmethod_get("../sDistrito", datos, success, error);

    }
});