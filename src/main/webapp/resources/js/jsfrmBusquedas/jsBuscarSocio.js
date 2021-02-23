 
$("#txtSocio").keypress(function (event) {
    if (event.which == 13) {
        $("#btnBuscarSocio").click();
        event.preventDefault();
    }

});

$("#btnBuscarSocio").click(function () {

    if ($("#form-buscar").valid() === true) {
        //    optradio
        var data = {'txtSocio': $("#txtSocio").val(), 'tipoBusqueda': $('input:radio[name=optradio]:checked').val(),'tipoSocio' :$("#cboTipoPersona").val() };


        var success = function (res) {

            if (res.Result === 'OK'){                
            
                $('#tbl_Socio').DataTable({
                    destroy: true,
                    "columnDefs": [
                        {"className": "text-center", "targets": "_all"}

                    ],
                    "aaData": res.Socio,
                    //rowId: 'idbien', //asigna el id a la fila
                    "columns": [
                        {"data": "NroDocIdentidad"}, //, "visible": false},
                        {"data": "Nombres"},
                        {
                            "data": null,
                            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                                $(nTd).html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                            }}
                    ],
                    createdRow: function (row, data, indice) {
                        $(row).find("td:eq(2)").attr('data-id', data.IdPersona);
                    }
                });
            } else if (res.Result === 'error')
            {
                toast('error', res.Message, 'Error!');
            }

        };

        var error = function (res) {
            toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
        };
    }
    fn_callmethod("../sSocio?action=ObtenerSocioxNombrexNroDoc", data, success, error);

});

var otableSocio= $('#tbl_Socio').DataTable();
otableSocio.on("click", ".select", function () {
    fn_Select(this);
});

//var fn_Select = function (elem) {
//
//    var par = $(elem).parent().parent();
//    var td_nroDoc = par.children("td:nth-child(1)");
//    var td_nombre = par.children("td:nth-child(2)");
//    var tdbuttons = par.children("td:nth-child(3)");
//    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
//  
//    var data = {'nombre':td_nombre.html(),'idPersona':id_persona,'nroDoc':td_nroDoc.html()};
//    AddRow(data);
//    $('#modalBuscar').modal('toggle');
//    
//};

/*************************************Validacion*********************************/

