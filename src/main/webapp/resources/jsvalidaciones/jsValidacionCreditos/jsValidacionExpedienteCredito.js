$(document).ready(function () {

    $("#frmExpCredito").validate({
        rules:
                {
                    txtRazon: {
                        required: true,
                        minlength: 3
                    },
                    cboModalidad: {
                        valueSelect: "default"
                    },
                    cboTipProducto :
                    {
                         valueSelect: "default"
                    },
                    cboTipoSBS:
                    {
                       valueSelect: "default"  
                    },
                    cboDestinoSBS:
                            {
                                valueSelect: "default"  
                            },
                            cboCalificacionSBS:
                                    {valueSelect: "default" },
                    txtFechaConstitucion :
                    {
                        valueFecha : true
                      
                    }          
                },
        messages:
                {
                    txtFechaConstitucion: {valueFecha :"Error fecha no valida"},
                    cboModalidad: {valueSelect: "Seleccione una modalidad!"},
                    cboTipProducto: {valueSelect: "Seleccione un tipo producto!"},
                    cboTipoSBS: {valueSelect: "Seleccione un tipo sbs!"},
                    cboDestinoSBS : {valueSelect: "Seleccione un destino sbs!"},
                    cboCalificacionSBS :{valueSelect: "Seleccione una calificacion sbs!"}
                }

    });
});

