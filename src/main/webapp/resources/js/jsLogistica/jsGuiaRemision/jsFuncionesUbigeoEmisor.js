$(function () {
    /*Obtendo la data de ubigeo*/
    ObtenerPaisesE();
    
    function ObtenerPaisesE()
    {

        var success = function (data) {

            $.each(data.Paises, function (i, item) {
                $("#idpaisesE").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
                $('#idpaisesE').trigger("refresh");
            });
            if ($("#idpaisesE option").length > 1) {
                $("#idpaisesE option:eq('01')").prop('selected', true);
            }
            $('#idpaisesE').select2();
            $('#idpaisesE').trigger('change.select2');
            $('#idpaisesE').change();
        };
        var error = function () {};
        fn_callmethod_getND("../sPais", success, error);
    }
    ;

    $("#idpaisesE").change(obtenerDepartamentosE);
    $("#iddepartamentoE").change(obtenerProvinciaE);
    $("#idprovinciasE").change(obtenerDistritoE);

    function obtenerDepartamentosE() {

        var datos = {'idPais': $("#idpaisesE").val()};

        var success = function (data) {
            $("#iddepartamentoE").html("");
            $.each(data.Departamentos, function (i, item) {
                $("#iddepartamentoE").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
            });

            if ($("#iddepartamentoE option").length > 1) {
                var codDepE = $("#codDepE").val();
                if (codDepE != 't') {              
                     $("#iddepartamentoE").val(codDepd).prop('selected', true);                    
                } else {                        
                  $("#iddepartamentoE option:eq(11)").prop('selected', true);                  
                }
            }             
            $('#iddepartamentoE').select2();
            $('#iddepartamentoE').trigger('change.select2');
            $('#iddepartamentoE').change();
        };
        var error = function () {};
        fn_callmethod_get("../sDepartamento", datos, success, error);
    };

    function obtenerProvinciaE()
    {
        var datos = {'idPais': $("#idpaisesE").val(), 'idDepartamento': $("#iddepartamentoE").val()};
       
        var success = function (data) {

            if (data.Result == 'OK') {
                $("#idprovinciasE").html("");
                $.each(data.Provincias, function (i, item) {
                    $("#idprovinciasE").append("<option value='" + item.codigo + "' >" + item.descripcion + "</option>");
                });

                if ($("#idprovinciasE option").length > 1) {
                    var idProvE = $("#codProvE").val();
                    if (idProvE != 't') {
                         
                        $("#idprovinciasE").val(idProvE).prop('selected', true);
                            
                    } else {                        
                        $("#idprovinciasE option:eq(00)").prop('selected', true);
                              
                    }
                }
                $('#idprovinciasE').select2();
                $('#idprovinciasE').trigger('change.select2');
                $('#idprovinciasE').change();

            } else {
                toast('Error', data.Message, 'warning');
            }
        };
        var error = function () { };
        fn_callmethod("../sProvincia?action=obtenerddlProvincia", datos, success, error);
    };

    function obtenerDistritoE()
    {
        var datos = {'idPais': $("#idpaisesE").val(), 'idDepartamento': $("#iddepartamentoE").val(), 'idProvincia': $('#idprovinciasE').val()};

        var success = function (data) {

            $("#iddistritosE").html("");
            $.each(data.Distritos, function (i, item) {
                $("#iddistritosE").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
            });

            if ($("#iddistritosE option").length > 1) {
                var idDistE = $("#codDistE").val();
                if (idDistE != 't') {
                    $("#iddistritosE").val(idDistE).prop('selected', true);
                } else {
                    $("#iddistritosE option:eq(00)").prop('selected', true);
                }
            }
            $('#iddistritosE').select2();
            $('#iddistritosE').trigger('change.select2');
            $('#iddistritosE').change();

        };
        var error = function () {};
        fn_callmethod_get("../sDistrito", datos, success, error);

    }
});