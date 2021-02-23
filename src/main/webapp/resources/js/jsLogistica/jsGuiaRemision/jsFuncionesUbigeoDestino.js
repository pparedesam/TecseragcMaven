$(function () {
    /*Obtendo la data de ubigeo*/
    ObtenerPaisesD();
    
    function ObtenerPaisesD()
    {

        var success = function (data) {

            $.each(data.Paises, function (i, item) {
                $("#idpaisesD").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
                $('#idpaisesD').trigger("refresh");
            });
            if ($("#idpaisesD option").length > 1) {
                $("#idpaisesD option:eq('01')").prop('selected', true);
            }
            $('#idpaisesD').select2();
            $('#idpaisesD').trigger('change.select2');
            $('#idpaisesD').change();
        };
        var error = function () {};
        fn_callmethod_getND("../sPais", success, error);
    }
    ;

    $("#idpaisesD").change(obtenerDepartamentosD);
    $("#iddepartamentoD").change(obtenerProvinciaD);
    $("#idprovinciasD").change(obtenerDistritoD);

    function obtenerDepartamentosD() {

        var datos = {'idPais': $("#idpaisesD").val()};

        var success = function (data) {
            $("#iddepartamentoD").html("");
            $.each(data.Departamentos, function (i, item) {
                $("#iddepartamentoD").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
            });

            if ($("#iddepartamentoD option").length > 1) {
                var codDepD = $("#codDepD").val();
                if (codDepD != 't') {              
                     $("#iddepartamentoD").val(codDepd).prop('selected', true);                    
                } else {                        
                  $("#iddepartamentoD option:eq(11)").prop('selected', true);                  
                }
            }             
            $('#iddepartamentoD').select2();
            $('#iddepartamentoD').trigger('change.select2');
            $('#iddepartamentoD').change();
        };
        var error = function () {};
        fn_callmethod_get("../sDepartamento", datos, success, error);
    };

    function obtenerProvinciaD()
    {
        var datos = {'idPais': $("#idpaisesD").val(), 'idDepartamento': $("#iddepartamentoD").val()};
       
        var success = function (data) {

            if (data.Result == 'OK') {
                $("#idprovinciasD").html("");
                $.each(data.Provincias, function (i, item) {
                    $("#idprovinciasD").append("<option value='" + item.codigo + "' >" + item.descripcion + "</option>");
                });

                if ($("#idprovinciasD option").length > 1) {
                    var idProvD = $("#codProvD").val();
                    if (idProvD != 't') {
                         
                        $("#idprovinciasD").val(idProvD).prop('selected', true);
                            
                    } else {                        
                        $("#idprovinciasD option:eq(00)").prop('selected', true);
                              
                    }
                }
                $('#idprovinciasD').select2();
                $('#idprovinciasD').trigger('change.select2');
                $('#idprovinciasD').change();

            } else {
                toast('Error', data.Message, 'warning');
            }
        };
        var error = function () { };
        fn_callmethod("../sProvincia?action=obtenerddlProvincia", datos, success, error);
    };

    function obtenerDistritoD()
    {
        var datos = {'idPais': $("#idpaisesD").val(), 'idDepartamento': $("#iddepartamentoD").val(), 'idProvincia': $('#idprovinciasD').val()};

        var success = function (data) {

            $("#iddistritosD").html("");
            $.each(data.Distritos, function (i, item) {
                $("#iddistritosD").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
            });

            if ($("#iddistritosD option").length > 1) {
                var idDistD = $("#codDistD").val();
                if (idDistD != 't') {
                    $("#iddistritosD").val(idDistD).prop('selected', true);
                } else {
                    $("#iddistritosD option:eq(00)").prop('selected', true);
                }
            }
            $('#iddistritosD').select2();
            $('#iddistritosD').trigger('change.select2');
            $('#iddistritosD').change();

        };
        var error = function () {};
        fn_callmethod_get("../sDistrito", datos, success, error);

    }
});