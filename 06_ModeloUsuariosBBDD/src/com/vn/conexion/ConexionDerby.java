/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.conexion;

import java.sql.DriverManager;

/** static final de los elementos necesarios para hacer la conexion (url, usuario, contraseña)
 *
 * @author Equipo 1
 */
public class ConexionDerby {
    //static final 
    public ConexionDerby(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());

        } catch (Exception ex) {
            System.out.println("no se ha cargado DerbyDB");
        }
    }
}
