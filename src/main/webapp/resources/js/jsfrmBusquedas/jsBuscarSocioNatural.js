$(".simple-select").select2({
    minimumResultsForSearch: Infinity
});

$(".simple-select2").select2();

$("#txtSocioNatural").keypress(function (event) {
  
    if (event.which == 13) {
        $("#btnBuscarSocioNatural").click();
        event.preventDefault();
    }

});

$("#btnBuscarSocioNatural").click(function () {
  
    if ($("#frmBuscarSocioNatural").valid()) {
      //'tipoBusqueda': $('input:radio[name=optradio]:checked').val(),
        var data = {'txtSocioNatural': $("#txtSocioNatural").val(),            
            'tipoBusqueda' :$("#cboTipoBusqueda").val() };


        var success = function (res) {           
          
            if (res.Result == 'OK'){                

                $('#tbl_Socio').DataTable({
                    destroy: true,
                    "columnDefs": [
                        {"className": "text-center", "targets": "_all"}

                    ],
                    "aaData": res.Socio,
                    //rowId: 'idbien', //asigna el id a la fila
                    "columns": [
                        {"data": "objPersona.nroDocumento"}, //, "visible": false},
                        {"data": "objPersona.nombres"},
                        {
                            "data": null,
                            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                                $(nTd).html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                            }}
                    ],
                    createdRow: function (row, data, indice) {
                        $(row).find("td:eq(2)").attr('data-id', data.objPersona.idPersona);
                    }
                });
            } else if (res.Result == 'error')
            {
                toast('error', res.Message, 'Error!');
            }

        };

        var error = function (res) {
            toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
        };
    }
    fn_callmethod("../sSocioNatural?action=ObtenerSocioxTarjetaxNombrexNroDocxCuenta", data, success, error);

});

var otableSocio= $('#tbl_Socio').DataTable();
otableSocio.on("click", ".select", function () {
    fn_Select(this);
});
