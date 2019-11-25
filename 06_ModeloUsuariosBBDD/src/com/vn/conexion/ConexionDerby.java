/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/** static final de los elementos necesarios para hacer la conexion (url, usuario, contraseña)
 *
 * @author alberto
 */
public class ConexionDerby {

    private static final String URL = "jdbc:derby://localhost:1527/06_ModeloUsuariosBBDD";
    private static final String USER = "root";
    private static final String PASS = "abc123.";
    private static boolean driversCargados = false;
    
//    public Connection ConexionDerby(){
//        
//    }
    
    public static Connection getConexion(){
        Connection con =null;
        try{
            if (!driversCargados) {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            driversCargados = true;
            }
            con = DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception ex) {
            System.out.println("No se ha cargado DerbyDB");
        }
        return con;
    }
}
