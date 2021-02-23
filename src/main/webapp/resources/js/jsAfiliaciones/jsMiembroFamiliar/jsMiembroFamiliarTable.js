var oTableMiembroFamiliar;
var id,tblMiembroF;
oTableMiembroFamiliar = fn_iniciarDT(oTableMiembroFamiliar, "tblMiembros");
oTableMiembroFamiliar.on("click", ".save", function () {
    fn_SaveMF(this);
});
oTableMiembroFamiliar.on("click", ".remove", function () {
    var elem = this;
    bootbox.confirm({
        title: "ELIMINAR MIEMBRO FAMILIAR",
        message: "ESTA SEGURO QUE DESEA ELIMINAR EL MIEMBRO FAMILIAR",
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
                oTableMiembroFamiliar.fnDestroy();
                fn_DeleteMF(elem);
            }
        }
    });
});
oTableMiembroFamiliar.on("click", ".cancel", function () {
    fn_CancelMF(this);
});
oTableMiembroFamiliar.on("click", ".edit", function () {
    fn_EditMF(this);
});

function tableMiembroFamiliar() {

    var idPersona = document.getElementById('txtNumDocumento').getAttribute("data-idPersona");
    var data = {'idPersona': idPersona, 'tipoBusqueda': 1};
    var success = function (res) {
            
        if (res.Result == 'OK') {          
            
            tblMiembroF = res.listMiembroFamiliares;
            $('#tblMiembros').DataTable({
                destroy: true,
                "columnDefs": [
                    {
                        "className": "text-center",
                        "targets": "_all"

                    }
                ],
                "aaData": res.listMiembroFamiliares,
                "columns": [
                    {"data": "objPersonaMiembroF.nroDocumento"},
                    {"data": "objPersonaMiembroF.nombres"},
                    {"data": "ObjParentesco.Parentesco"},
                    {
                        "data": "Apoderado",
                        render: function (data, type, row) {

                            return data == 1 ? "<i class='fa fa-dot-circle-o'></i>" : "<i class='fa fa-circle-o'></i>";
                        },
                        className: "dt-body-center",
                        className: "text-center"

                    },
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;' class='btn btn-Lapislazuli btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a>\n<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }
                    }

                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(4)").attr('data-id', data.objPersonaAsociada.idPersona);
                    $(row).find("td:eq(4)").attr('data-idMF', data.objPersonaMiembroF.idPersona);
                    $(row).find("td:eq(4)").attr('data-idPar', data.ObjParentesco.IdParentesco);
                }
            });
            
            tableBeneficiarios();
            if (res.listMiembroFamiliares.length == 0) {
                desabilitarBotonBeneficiario(true);
            } else {
                desabilitarBotonBeneficiario(false);
            }
        } else if (res.Result == 'error') {
            toast('error', $.trim(res.Message), 'ERROR!');
        }
    };
    var error = function () {
        toast('error', 'ERROR, CONSULTE CON EL AREA DE TI', 'ERROR!');
    };
    fn_callmethod("../sMiembroFamiliar?action=obtenerMiembrosFamiliares", data, success, error);
}
;
$("#btnAgregarMiembro").click(function (event) {

    $("#txtIdPersonaMF").val('');
    $("#txtNomPersonaMF").val('');
    $("#txtNombrePersonaMF").val('');
    var idPersona = document.getElementById('txtNumDocumento').getAttribute("data-idPersona");
    if (idPersona.length === 0) {
        bootbox.alert("NO SE HA SELECCIONADO UN SOCIO");
    } else {
        $('#btnAgregarMiembro').prop("disabled", true);
        oTableMiembroFamiliar.DataTable({
            destroy: true,
            "paging": false,
            "ordering": false,
            "info": false
        });
        $("#tblMiembros tbody").prepend(
                "<tr>" +
                "<td style='width:5%'></td>" +
                "<td style='width:15%'>" +
                "<div class='form-group' style='width:100%'>" +
                "<div class='input-group' style='width:100%'>" +
                "<input type='text' class='form-control' id='txtNomPersonaMF' name='txtNomPersonaMF' value=''>" +
                "<input type='hidden' id='txtIdPersonaMF' name='txtIdPersonaMF' value=''/>" +
                "<div class='input-group-btn'>" +
                "<a class='btn btn-Lapislazuli btn-bitbucket buscar' href='frmBusqueda/frmBuscarPersonaNatural.jsp' data-target='#modalBuscar' data-toggle='modal'>" +
                "<i class='fa fa-search'></i>" +
                "</a>" +
                "</div>" +
                "</div>" +
                "<input type='hidden' id='txtNombrePersonaMF' name='txtNombrePersonaMF' value=''/>" +
                "</div>" +
                "</td>" +
                "<td class='text-center' style='width: 10%'>" +               
                "<select class='form-control input-sm' style='width: 100%' id='cboParentesco0' name='cboParentesco0'>" +
                "</select>" +        
                "</td>" +
                "<td class='text-center' style='width: 7%'>" +
                '<input type="radio" name="idRadio">' +
                "</td>" +
                "<td class='text-center' style='width: 3%'>" +
                "<a class='btn btn-success btn-circle save' href='javascript:;' title='Guardar' ><i  class='fa fa-save'></i></a>\n" +
                "<a class='btn  btn-warning btn-circle cancel' href='javascript:;' title='Cancelar' ><i class='fa fa-ban'></i></a>" +
                "</td>" +
                "</tr>");
        comboParentesco("cboParentesco0");        
        combotable('cboParentesco0');        
    }
});
function insertarpersonafila(data) {
    $("#txtIdPersonaMF").val(data.idPersona);
    $("#txtNomPersonaMF").val(data.nombre);
}

function desabilitarBotonBeneficiario(valor) {
    document.getElementById("btnAgregarBeneficiario").disabled = valor;
}

var fn_DeleteMF = function (elem) {

    var par = $(elem).parent().parent();
    var td_Parentesco = par.children("td:nth-child(3)");
    var td_Apoderado = par.children("td:nth-child(4)");
    var tdbuttons = par.children("td:nth-child(5)");
    var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var idPersonaMF = tdbuttons.attr('data-idmf') === undefined ? 0 : tdbuttons.attr('data-idmf');
    id = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var data = {
        'IdPersona': idPersona,
        'IdPersonaMF': idPersonaMF,
        'accion': 'del'
    };
    var success = function (res) {

        if (res.Result == 'OK') {
            var par = $(elem).parent().parent();            
            actualizarDatosComboBeneficiario(idPersona,idPersonaMF);
            par.remove();
            
            toast('success', 'SE ELIMIN&AOCUTE; CORRECTAMENTE', 'Exito!');
        } else {
            toast('error', res.Message, 'Error');
        }

    };
    var error = function () {
    };
    fn_callmethod("../sMiembroFamiliar?action=eliminarMiembroFamiliar", data, success, error);
};

var fn_CancelMF = function (elem) {

    var par = $(elem).parent().parent();
    var td_Parentesco = par.children("td:nth-child(3)");
    var td_Apoderado = par.children("td:nth-child(4)");
    var tdbuttons = par.children("td:nth-child(5)");
    var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    if (idPersona == 0) {
        par.remove();
    } else {
        td_Apoderado.html((td_Apoderado.children("input[type=radio]").is(":checked") ? '<i class="fa fa-dot-circle-o"></i>' : '<i class="fa fa-circle-o"></i>'));
        td_Parentesco.html($("#cboParentesco" + idPersona + " option:selected").text());
        tdbuttons.html(
                "<a href='javascript:;'  class='btn btn-success btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a>\n" +
                "<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>"
                );
    }
    $('#btnAgregarMiembro').prop("disabled", false);
};

var fn_EditMF = function (elem) {

    var par = $(elem).parent().parent();
    var td_Parentesco = par.children("td:nth-child(3)");
    var td_Apoderado = par.children("td:nth-child(4)");
    var tdbuttons = par.children("td:nth-child(5)");
    var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var idPar = tdbuttons.attr('data-idpar') === undefined ? 0 : tdbuttons.attr('data-idpar');
    td_Parentesco.html("<select id='cboParentesco" + idPersona + "' class='form-control input-sm' style='width: 100%'></select>");
    td_Apoderado.html('<input type="radio" name="idRadio">');
    tdbuttons.html('<a class="btn btn-Lapislazuli btn-circle save"><i class="fa fa-save" title="Guardar"></i></a>\n<a class="btn  btn-warning btn-circle cancel" href="javascript:;" title="Cancelar" ><i class="fa fa-ban"></i></a>');
    
    comboParentesco("cboParentesco" + idPersona);
    
    var cbo = 'cboParentesco'+idPersona;
    combotableSetTimeout(cbo,idPar);

    $('#btnAgregarMiembro').prop("disabled", true);
};

var fn_SaveMF = function (elem) {

    var par = $(elem).parent().parent();
    var td_Parentesco = par.children("td:nth-child(3)");
    var td_Apoderado = par.children("td:nth-child(4)");
    var tdbuttons = par.children("td:nth-child(5)");
    var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var idPersonaMF = tdbuttons.attr('data-idmf') === undefined ? 0 : tdbuttons.attr('data-idmf');
    var apoderado = td_Apoderado.children("input[type=radio]").is(":checked") ? 1 : 0;
    var idParentesco = td_Parentesco.children().find('option:selected').val();
    var tipoOperacion;
    if (idPersona == 0 && idPersonaMF == 0) {
        tipoOperacion = 'ins';
        idPersona = document.getElementById('txtNumDocumento').getAttribute("data-idPersona");
        idPersonaMF = $("#txtIdPersonaMF").val();
    } else {
        tipoOperacion = 'upd';
    }

    var data = {
        'IdPersona': idPersona,
        'IdPersonaMF': idPersonaMF,
        'apoderado': apoderado,
        'idParentesco': idParentesco,
        'accion': tipoOperacion
    };
    if (idPersonaMF.length == 7) {
        if (idParentesco != "default") {
            registrarMiembroFamiliar(data);
        } else {
            bootbox.alert("SELECCIONE UN PARENTESCO");
        }
    } else {
        bootbox.alert("SELECCIONE UNA PERSONA");
    }


};

function registrarMiembroFamiliar(data) {

    var success = function (res) {
        if (res.Result == 'OK') {
            tableMiembroFamiliar();
            if (res.Message == 'ins') {
                toast('success', 'SE REGISTR&Oacute; CORRECTAMENTE', 'Exito!');
            } else {
                toast('success', 'SE ACTUALIZO CORRECTAMENTE', 'Exito!');
            }
        } else if (res.Result == 'error') {
            toast('error', res.Message, 'Error');
        }
        $('#btnAgregarMiembro').prop("disabled", false);
    }
    var error = function (res) {

        toast('error', res.Message, 'Error');
    };
    fn_callmethod("../sMiembroFamiliar?action=registrarMiembroFamiliar", data, success, error);
}

function actualizarDatosComboBeneficiario(idPersona,IdPersonaMF){
  
    for (var i = 0; i < tblMiembroF.length; i++) {      
        if (tblMiembroF[i].objPersonaAsociada.idPersona == idPersona &&
                tblMiembroF[i].objPersonaMiembroF.idPersona == IdPersonaMF) {           
                tblMiembroF.splice(i, 1); 
        }
    }  

    if (Object.keys(tblMiembroF).length== 0) {
        document.getElementById("btnAgregarBeneficiario").disabled =true;       
    }
   
}