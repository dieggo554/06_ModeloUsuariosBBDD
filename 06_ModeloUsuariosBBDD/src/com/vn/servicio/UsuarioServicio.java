/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.servicio;

import com.vn.DAO.UsuarioDAO;
import com.vn.POJOs.Usuario;
import com.vn.conexion.ConexionDerby;
import java.util.List;

/**
 *
 * @author pc
 */
public class UsuarioServicio {

    public static Usuario crear(String edad, String nombre, String email, String contrasena) throws Exception {
        Usuario nuevo = null;
        try {
            Integer intEdad = Integer.parseInt(edad);
            if (intEdad>17 && nombre.length()>1 && email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") && contrasena.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")){
                UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
                if (dao.obtenerPorEmail(email)==null) {
                    nuevo = new Usuario(email, contrasena, nombre, intEdad);
                    return dao.crear(nuevo);
                }
                nuevo = dao.obtenerPorEmail(email);
		return nuevo;
            }
        
        }catch(Exception ex){
        }
        return nuevo;
    }

    public static Usuario leer(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Usuario leer(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Boolean eliminar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Usuario modificar(Integer id, String string, String string0, String juan, Integer edad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Usuario modificar(Usuario usuarioMod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static List<Usuario> leerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static List<Usuario> leerTodos(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
