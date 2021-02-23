/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$.getJSON("../sPais",
        function (data) {
            $.each(data.Paises, function (i, item) {
                $("#idpaises").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
                $('#idpaises').trigger("chosen:updated");
            });
        });
$(function () {

    $("#idpaises").change(function () {
        jQuery("#idprovincias_chosen option[value='00']").attr('selected', true);
        jQuery("#iddistritos_chosen option[value='00']").attr('selected', true);
 
        $.getJSON("../sDepartamento", {
            idPais: $(this).val()
        },
                function (data) {
                    $("#iddepartamento").html("");
                    $("#iddepartamento").append("<option value='00' selected>Seleccione Departamento</option> ");

                    $.each(data.Departamentos, function (i, item2) {

                        $("#iddepartamento").append("<option value='" + item2.codigo + "'>" + item2.descripcion + "</option>");
                        $('#iddepartamento').trigger("chosen:updated");
                    });

                });
        $("#iddepartamento").change();

    });

    $("#iddepartamento").change(function () {

        $.getJSON("../sProvincia", {
            idPais: $("#idpaises").val(),
            idDepartamento: $(this).val()
        },
                function (data) {
                    $("#idprovincias").html("");
                    $("#idprovincias").append("<option value='00' selected>Seleccione Provincia</option>");
                    $.each(data.Provincias, function (i, item3) {
                        $("#idprovincias").append("<option value='" + item3.codigo + "'>" + item3.descripcion + "</option>");
                        $('#idprovincias').trigger("chosen:updated");

                    });
                });
        $("#idprovincias").change();
    });

    $("#idprovincias").change(function () {

        $.getJSON("../sDistrito", {
            idPais: $("#idpaises").val(),
            idDepartamento: $("#iddepartamento").val(),
            idProvincia: $(this).val()
        },
                function (data) {
                    $("#iddistritos").html("");
                    $("#iddistritos").append("<option value='00' selected>Seleccione Distrito</option>");
                    $.each(data.Distritos, function (i, item4) {
                        $("#iddistritos").append("<option value='" + item4.codigo + "'>" + item4.descripcion + "</option>");
                        $('#iddistritos').trigger("chosen:updated");
                    });
                });

    });

});


//function ejecutarservlet(form)
//{
//    fn_callmethod(base_url + "../sPersonaNatural?action=registar", $(form).serialize(), success, error);
//
//
//    var success = function (res) {
//        if (res) {
//
//        }
//    };
//    var error = function () {
//    };
//}
