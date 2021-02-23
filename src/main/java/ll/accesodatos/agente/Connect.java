package ll.accesodatos.agente;

import java.sql.*;

public class Connect {

    private static Connect _Instancia;

    private Connect() {
    }

    public static Connect Instancia() {
        if (_Instancia == null) {
            _Instancia = new Connect();
        }
        return _Instancia;
    }

    String Conexion = "jdbc:sqlserver://localhost:1433;databaseName=BDMultiservicios;user=sa;password=Paulps01**";
    String ConexionAsistencia = "jdbc:sqlserver://localhost:1433;databaseName=BDAsistencia;user=sa;password=Paulps01**";
    String ConecOSE = "https://demo-ose.nubefact.com/ol-ti-itcpe/billService?wsdl";
    String sURL = "jdbc:mysql://comunidadllacuabamba.com:3306/admsop_BDComunidadCampesina";
    //String Conexion = "jdbc:sqlserver://localhost;instanceName=MSSQLSERVER1;databaseName=BDComunidadCampesina;user=sa;password=Paulps01**";
    //String ConexionAsistencia = "jdbc:sqlserver://localhost;instanceName=MSSQLSERVER1;databaseName=BDComunidadCampesina;user=sa;password=Paulps01**";
    //String Conexion = "jdbc:sqlserver://25.20.250.48:1433;databaseName=BDComunidadCampesina;user=sa;password=Desarrollo123**";
    //String ConexionAsistencia= "jdbc:sqlserver://25.20.250.48:1433;databaseName=BDAsistencia;user=sa;password=Desarrollo123**";
    //String ConecOSE = "https://ose.nubefact.com/ol-ti-itcpe/billService?wsdl";
    //String ConexionAsistencia = "jdbc:sqlserver://svrbd:1433;databaseName=BDAsistencia;user=sa;password=Desarrollo123**";
    //String Conexion = "jdbc:sqlserver://mssql-20809-0.cloudclusters.net:20809;databaseName=BDComunidadCampesina;user=userDeveloper;password=Paulps01**";

    public Connection getConnectBD() throws Exception {

        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(Conexion);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
        return cn;
    }

    public Connection getConnectAsistencia() throws Exception {

        Connection cn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(ConexionAsistencia);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
        return cn;
    }

    public Connection getConnectMySQL() throws Exception {
        Connection cn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(sURL, "admsop_UserFact", "123456");
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
        return cn;
    }

    public String getConnectOSE() throws Exception {
        String cn = null;

        try {
            cn = ConecOSE;
        } catch (Exception e) {
            throw e;
        }
        return cn;
    }

}
