$("#txtFechaCierre").keyup(function (e) {

    
    e.preventDefault();
    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
    if (key == 13) {
        var fCierre = $('#txtFechaCierre').val();
        abrirVentana("http://192.168.1.190:808/ReportServer/Pages/ReportViewer.aspx?%2fpySeguroDesgravamen%2fpyReportServiperu&rs:Command=Render&fechaFIN=" + fCierre);
    }
});

function abrirVentana(url) {
    window.open(url, "nuevo", "directories=no, location=no, menubar=no, scrollbars=yes, statusbar=no, tittlebar=no, width=800, height=600");
    }