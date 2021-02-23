var oTableBeneficiario;

oTableBeneficiario = fn_iniciarDT(oTableBeneficiario, "tblBeneficiarios");

oTableBeneficiario.on("click", ".remove", function () {
    var elem = this;
    bootbox.confirm({
        title: "ELIMINAR BENEFICIARIO",
        message: "ESTA SEGURO QUE DESEA ELIMINAR EL BENEFICIARIO",
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
                fn_DeleteB(elem);
            }
        }
    });

});

var fn_DeleteB = function (elem) {

    var par = $(elem).parent().parent();    
    var tdTipoBeneficio = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(5)");
    var idTipoBeneficio = tdTipoBeneficio.attr('data-idtipo');
    var idPersona =  tdbuttons.attr('data-id');
    var idPersonaB = tdbuttons.attr('data-idb');
   
   //data-idtipo
    var data = {
        'idTipoBeneficio' :idTipoBeneficio,
        'IdPersona': idPersona,
        'IdPersonaB': idPersonaB 
    };  
       
    var success = function (res) {

        if (res.Result === 'OK') {
            var par = $(elem).parent().parent();
            par.remove();
            toast('success', 'SE ELIMIN&Oacute; CORRECTAMENTE', 'Exito!');
        } else {
            toast('error', res.Message, 'Error');
        }

    };
    var error = function () {
    };
    fn_callmethod("../sBeneficiario?action=eliminarBeneficiarios", data, success, error);
};


function tableBeneficiarios() {
    var idPersona = document.getElementById('txtNumDocumento').getAttribute("data-idPersona");
     
    var data = {'idPersona': idPersona, 'tipoBusqueda': 1};    
    var success = function (res) {        
        if (res.Result == 'OK') {          
       
            $('#tblBeneficiarios').DataTable({
                destroy: true,
                "columnDefs": [
                    {
                        "className": "text-center",
                        "targets": "_all"

                    }
                ],
                "aaData": res.listbeBeneficiarios,
                "columns": [
                    {"data": "objMiembroFamiliar.objPersonaAsociada.nroDocumento"},
                    {"data": "objMiembroFamiliar.objPersonaAsociada.nombres"},
                    {"data": "objTipoBeneficio.descripcion"},
                    {"data": "Porcentaje"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }
                    }
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(2)").attr('data-idTipo', data.objTipoBeneficio.codigo);
                    $(row).find("td:eq(4)").attr('data-id', data.objMiembroFamiliar.objPersonaMiembroF.idPersona);
                    $(row).find("td:eq(4)").attr('data-idB', data.objMiembroFamiliar.objPersonaAsociada.idPersona);                  
                }
            });

        } else if (res.Result == 'error') {
            toast('error', $.trim(res.Message), 'ERROR!');
        }
    };
    var error = function () {
        toast('error', 'ERROR, CONSULTE CON EL AREA DE TI', 'ERROR!');
    };

    fn_callmethod("../sBeneficiario?action=obtenerBeneficiarios", data, success, error);
};
