$('#btnAgregarBeneficiario').click(function (event) {
    if (Object.keys(tblMiembroF).length != 0) {       
        ejecutaBeneficiario();
    } else {        
        toast('warning', 'NO EXISTEN MIEMBROS FAMILIARES REGISTRADOS', 'CUIDADO!');//Lleno la tabla Beneficiarios
    }
});

function  ejecutaBeneficiario() {

    $('#cboMFamiliarModal').empty();    
    $.each(tblMiembroF, function (key, value) {     
        $('#cboMFamiliarModal')
                .append($('<option></option>').val(value.objPersonaMiembroF.idPersona)
                        .html(value.objPersonaMiembroF.nombres));

    });   
    $('#modalBeneficiarios').modal({
        backdrop: 'static',
        keyboard: false        
    }); 
 

}


$('#frmBeneficiaro').submit(function (event) {
    event.preventDefault();
    if ($('#frmBeneficiaro').valid()) {
        bootbox.confirm("\u00BFEst\u00e1 ESTA POR REGISTRAR A UN MIEMBRO BENEFICIARIO\u003F", function (result) {
            registrarBeneficiarios();
        });

    }
});

function registrarBeneficiarios() {

    var data = {
        'idPersona': document.getElementById('txtNumDocumento').getAttribute("data-idPersona"),
        'idPersonaB': $('#cboMFamiliarModal').val(), 'idTipoBeneficio': $('#cboTipoBeneficio').val(),
        'porcentaje': $('#txtPorcentaje').val()
    };

    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message == 'ins_ok') {
                toast('success', 'SE REGISTR&Oacute; CORRECTAMENTE', 'Exito!');//Lleno la tabla Beneficiarios
                tableBeneficiarios();
            } else if(res.Message=='upd_ok') {
                toast('success', 'SE ACTUALIZ&Oacute;; CORRECTAMENTE', 'Exito!');
                tableBeneficiarios();
            }
        } else {
            toast('error', res.Message, 'ERROR');
        }

    };
    var error = function () {
        toast('error', 'ERROR, CONSULTE CON EL AREA DE TI', 'ERROR!');
    };
    fn_callmethod("../sBeneficiario?action=registrarBeneficiarios", data, success, error);
}