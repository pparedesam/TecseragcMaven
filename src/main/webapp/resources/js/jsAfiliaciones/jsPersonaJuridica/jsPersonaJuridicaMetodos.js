var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tbl_PersonaJuridica");

    oTable.on("click", ".edit", function () {
        fn_Edit(this);
    });


});

//var tabName = $("#txtVariable").val() != "tabRegistrarPersona" ? $("#txtVariable").val() : "tab_BuscarPersona";
var varible = $("#txtVariable").val();
if (varible !== undefined)
    TabSelec(varible);

function TabSelec(idtab)
{    
    $('#tabs a[href="#' + idtab + '"]').tab('show');
    $("#tabs a").click(function () {
        $("[id*=" + idtab + "]").val($(this).attr("href").replace("#", ""));
    });
    $("#txtVariable").val(idtab);
}
;
