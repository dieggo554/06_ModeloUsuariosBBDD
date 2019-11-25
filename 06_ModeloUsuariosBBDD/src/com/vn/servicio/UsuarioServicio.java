/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.servicio;

import com.vn.DAO.UsuarioDAO;
import com.vn.POJOs.Usuario;
import com.vn.conexion.ConexionDerby;

/**
 *
 * @author pc
 */
public class UsuarioServicio {

   
    public static Usuario crear(String edad, String nombre, String email, String contrasena) throws Exception {
        try{
            Integer intEdad = Integer.parseInt(edad);
            
            if (intEdad>17 && nombre.length()>1 && email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") && contrasena.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")){
                UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
                if (dao.obtenerPorEmail(email)==null) {
                    Usuario nuevo = new Usuario(email, contrasena, nombre, intEdad);
                    return dao.crear(nuevo);
                }
            }
        }catch(Exception ex){
        }
        return null;
    }
    
}
