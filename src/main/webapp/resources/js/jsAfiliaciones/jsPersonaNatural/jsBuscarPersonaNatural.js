/*
$("#txtPersonaNatural").keypress(function (event) {
    if (event.which == 13) {
        $("#btnBuscarPersona").click();
        event.preventDefault();
    }

});*/

$("#btnBuscarPersona").click(function () {

    if ($("#form-buscar").valid() === true) {
        //    optradio
        var data = {'txtPersonaNatural': $("#txtPersonaNatural").val(), 'TipoBusqueda': $('input:radio[name=optradio]:checked').val()};

        var success = function (res) {

            if (res.Result === 'OK')
            {
              
                $('#tbl_PersonaNatural').DataTable({
                    destroy: true,
                    "columnDefs": [
                        {"className": "text-center", "targets": "_all"}

                    ],
                    "aaData": res.lista,
                    //rowId: 'idbien', //asigna el id a la fila
                    "columns": [
                        {"data": "nroDocumento"}, //, "visible": false},
                        {"data": "nombres"},
                        {
                            "data": null,
                            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                                $(nTd).html("<a href='javascript:;'  class='btn btn-green btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                            }}
                    ],
                    createdRow: function (row, data, indice) {
                        $(row).find("td:eq(2)").attr('data-id', data.idPersona);
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
    fn_callmethod("../sPersonaNatural?action=buscarPersonaNaturalModal", data, success, error);
    //fn_callmethod("../sPersonaNatural?action=buscarPersona", data, success, error);

});

var otablePerNat = $('#tbl_PersonaNatural').DataTable();
otablePerNat.on("click", ".select", function () {
    fn_Select(this);
});

var fn_Select = function (elem) {

    var par = $(elem).parent().parent();
    var td_nombre = par.children("td:nth-child(2)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

   
    
    if(TipoBusqueda=='AP'){
       
    $("#txtIdPersona").val(id_persona);
    $("#txtNomPersona").val(td_nombre.html());
    $("#txtNombrePersona").val(td_nombre.html());
    }
   else if(TipoBusqueda=='IN')
   {  
    
    $("#NombrePersonaIntegrante").val(td_nombre.html());
    $("#NomIntegrante").val(td_nombre.html());
    $("#idIntegrante").val(id_persona);     
   }    
    $('#modalBuscar').modal('toggle');
    
};



/*************************************Validacion*********************************/

    
      $("#form-buscar").validate({
        rules: {
            txtPersonaNatural: {
                required: true
            }
        },
        messages: {
            txtPersonaNatural: {
                required: "Debe Ingresar un Parametro de Busqueda"
            }
        }
    });