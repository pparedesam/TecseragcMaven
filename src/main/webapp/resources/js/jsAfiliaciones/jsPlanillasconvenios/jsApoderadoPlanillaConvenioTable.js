var TipoBusqueda;

var oTableA;
    oTableA = fn_iniciarDT(oTableA, "tbl_ConvenioApoderado");
    oTableA.on("click", ".add", function () {
        fn_ApAdd(this);
    });
    oTableA.on("click", ".save", function () {
        fn_Apsave(this);
    });
    
    oTableA.on("click", ".cancel", function () {
        fn_ApCancel(this);
    });
    oTableA.on("click", ".remove", function () {        
    var elem = this;
    bootbox.confirm({
        title: "ELIMINAR APODERADO",
        message: "ESTA SEGURO QUE DESEA ELIMINAR EL APODERADO",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancelar'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Confirmar'
            }
        },
        callback: function (result) {
            if (result) {
                oTableA.fnDestroy();
                fn_ApDelete(elem);
            }
        }
    });
    
        
    });

$("#btnAgregar").click(function (event) {
        
        $("#txtIdPersona").val('');
        $("#txtNomPersona").val('');
        $("#txtNombrePersona").val('');
        var convenio = $("#txtConvenio").val();
        var codigo = $("#txtConvenioId").val();

        if (convenio.length === 0 && codigo.length === 0)
        {
            bootbox.alert("No se ha seleccionado un convenio");
        } else
        {           
            $('#btnAgregar').prop("disabled", true);
            TipoBusqueda ='AP';            
            $('.alert').show().html('<strong> Para Habilitar el boton vuelva a buscar </strong>');
            $("#tbl_ConvenioApoderado tbody").prepend(
                    "<tr>" +
                    "<td style='width: 1%'>"+
                    "</td>"+
                    "<td style='width: 2%'>"+
                        "<div class='form-group' style='width:100%'>"+
                            "<input type='text' class='form-control' style='width: 100%' id='txtCodigoPlanilla'  name='txtCodigoPlanilla' value=''>"+
                        "<div>"+
                    "</td>" +
                    "<td style='width:10%'>"+
                        "<div class='form-group' style='width:100%'>"+
                            "<div class='input-group' style='width:100%'>"+
                                "<input type='text' class='form-control' id='txtNomPersona' name='txtNomPersona' value=''>"+
                                "<input type='hidden' id='txtIdPersona' name='txtIdPersona' value=''/>"+
                                "<input type='hidden' id='txtNombrePersona' name='txtNombrePersona' value=''/>"+
                                "<div class='input-group-btn'>"+
                                    "<a class='btn btn-Lapislazuli btn-bitbucket buscar' data-toggle='modal' data-target='#modalBuscar' title='Buscar'>"+
                                        "<i class='fa fa-search'></i>"+
                                    "</a>"+
                                "</div>"+
                            "</div>"+
                        "</div>"+
                    "</td>"+
                    "<td class='text-center' style='width: 5%'>"+
                        "<div class='form-group' style='width:100%'>"+
                            "<select class='form-control input-sm' id='cboTipoPlanilla0' name='cboTipoPlanilla0' style='width: 100%'>"+
                            "</select>"+
                        "</div>"+
                    "</td>" +
                    "<td class='text-center' style='width: 7%'>"+
                        $("#txtConvenio").val() + 
                    "</td>"+
                    "<td class='text-center' style='width: 3%'>" +
                    "<a class='btn btn-success btn-circle save' href='javascript:;' title='Guardar' ><i  class='fa fa-save'></i></a>\n" +
                    "<a class='btn btn-warning btn-circle cancel' href='javascript:;' title='Cancelar' ><i class='fa fa-ban'></i></a>" +
                    "</td>"+
                    "<td class='text-center'  style='width: 1%'>"+
                    "</td>"+
                    "</tr>");
            combo("../sApoderadoPlanillaConvenio?action=TipoPlanilla", "cboTipoPlanilla0");  

        }

    });

var fn_Apsave = function (elem) {

        var par = $(elem).parent().parent();
        var td_cuenta = par.children("td:nth-child(1)");
        var td_codigoPlanilla = par.children("td:nth-child(2)");
        var tdApoderado = par.children("td:nth-child(3)");
        var td_tipo = par.children("td:nth-child(4)");
        var tdConvenio = par.children("td:nth-child(5)");
        var tdbuttons = par.children("td:nth-child(6)");
        var tdbuttonsAI = par.children("td:nth-child(7)");
        var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

        

        var validar = $('#tbl_ConvenioApoderadoForm').valid();
        if (validar) {

            var data = {
                'codigo': id_codigo,
                'codigoPlanilla': td_codigoPlanilla.children().find(':input').val(),
                'idApoderado': $("#txtIdPersona").val(),
                'idTipo': td_tipo.children().find('option:selected').val(),
                'idConvenio': $("#txtConvenioId").val()
            };
            
            var success = function (res) {

                if (res.Result =='OK')
                {
                    id_codigo= $("#txtIdPersona").val();  
                    td_codigoPlanilla.html(td_codigoPlanilla.children().find(':input').val());
                    tdApoderado.html($("#txtNombrePersona").val());
                    td_tipo.html($("#cboTipoPlanilla" + id_codigo + " option:selected").text());                      
                    tdbuttons.html("<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a>");   
                    tdbuttonsAI.html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle add' title='Aregar'><i class='fa fa-plus'></i></a>");   
                    tdConvenio.attr("data-co",$("#txtConvenioId").val());
                    tdbuttons.attr("data-id",id_codigo);
                    
               
                    toast('success', 'Se registró correctamente', 'Exito!');
                    habilitarAgregarApoderado();
                }
                else{
                    toast('error', res.Message, 'Error');
                }
                    
            };
            var error = function (res) {
               
                toast('error', res.Message, 'Error');
            };

            fn_callmethod("../sApoderadoPlanillaConvenio?action=RegistroApoderado", data, success, error);

        }
    };

var fn_ApDelete = function (elem) {

    var par = $(elem).parent().parent();
    var tdConvenio = par.children("td:nth-child(5)");
    var tdbuttons = par.children("td:nth-child(6)");
    var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var id_convenio = tdConvenio.attr('data-co') === undefined ? 0 : tdConvenio.attr('data-co');

    var data = {'idApoderado': id_codigo, 'idConvenio': id_convenio};
    
    var success = function (res) {
        if (res.Result == 'OK') {
            var par = $(elem).parent().parent();
            par.remove();
            toast('success', 'Se eliminó correctamente', 'Exito!');
        } else {
                   toast('error', res.Message, 'Error');
        }

    };
    var error = function () {
    };
    fn_callmethod("../sApoderadoPlanillaConvenio?action=eliminarApoderado",data, success, error);
};

var fn_ApCancel = function (elem) {


    var par = $(elem).parent().parent();
    par.remove();
    habilitarAgregarApoderado();
  
};

var fn_ApAdd = function (elem) {

    var par = $(elem).parent().parent();
    var tdApoderado = par.children("td:nth-child(3)");
    var tdConvenio = par.children("td:nth-child(5)");
    var tdbuttons = par.children("td:nth-child(6)");
    var id_convenio =  tdConvenio.attr('data-co');
    var id_apoderado = tdbuttons.attr('data-id');    
   
    
       var DatosApoderadoConvenio ={codigoApoderado:id_apoderado,nombreApoderado :tdApoderado.html(),
           codigoConvenio:id_convenio,nombreConvenio :tdConvenio.html()
       };
       $('#btnAgregarPersonaPlanilla').prop("disabled", false);
       $.session.set('Datos',JSON.stringify(DatosApoderadoConvenio));
       GenerarTablaIntegrantePlanillaConvenio(id_convenio,id_apoderado);

};

function habilitarAgregarApoderado()
{    
    $('#btnAgregar').prop("disabled", false);
    $("#txtIdPersona").val('');
    $('.alert').show().html('');
}
 