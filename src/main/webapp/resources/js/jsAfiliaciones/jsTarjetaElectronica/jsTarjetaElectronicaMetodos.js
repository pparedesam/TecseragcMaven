var oTable;

oTable = fn_iniciarDT(oTable, "tbl_TarjetaElectronica");

oTable.on("click", ".seleccion", function () {
    fn_Seleccion(this);
});
var oTable2;

oTable2 = fn_iniciarDT(oTable2, "tbl_ListaTarjetas");

$("#btnAdd").on("click", function () {
    fn_Add();
});

oTable2.on("click", ".save", function () {
    fn_save(this);
});
oTable2.on("click", ".selectrow", function () {
    fn_selectrow(this);
});
/*
oTable2.on("click", ".remove", function () {

    bootbox.confirm("\u00BFEst\u00e1 seguro de eliminar esta ocupaci&oacuten?\u003F", function (result) {
        if (result) {
            oTable2.fnDestroy();
            fn_Delete(this);
        }
    });

});*/
oTable2.on("click", ".cancel", function () {
    fn_Cancel(this);
});
oTable2.on("click", ".ban", function () {


    var elem = this;
    fn_Ban(elem);
    
    
    
});

oTable2.on('click', 'th', function () {
    $('#btnAdd').prop("disabled", false);
});




function fn_selectrow(elem) {

    var par = $(elem).parent().parent();
    var tdnro_tarjeta = par.children("td:nth-child(1)");
    var tdbuttons = par.children("td:nth-child(4)");
    var id_codigo = tdbuttons.attr("data-id") === undefined ? $("#txtidPersonax").val() : tdbuttons.attr('data-id');

    var data = {'txtUdpAleatorio': tdnro_tarjeta.html(), 'idPersona': id_codigo};

    $.session.set('datos', JSON.stringify(data));
}