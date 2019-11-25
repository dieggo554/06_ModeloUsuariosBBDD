/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.servicio;

import com.vn.DAO.UsuarioDAO;
import com.vn.POJOs.Usuario;
import com.vn.conexion.ConexionDerby;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author pc
 */
public class UsuarioServicio {

    Pattern patronNombre = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
                    + "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b"
                    + "\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
                    + "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]"
                    + "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]"
                    + "?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]"
                    + "?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    Pattern patronEmail = Pattern.compile(
            "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

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

    public static Usuario leer(Integer id) throws Exception {
        UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
        Usuario buscado = dao.obtenerPorIndice(id);
        return buscado;
        
    }

    public static Usuario leer(String email) throws Exception {
        UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
        Usuario buscado = dao.obtenerPorEmail(email);
        return buscado;
    }

    public static Boolean eliminar(Integer id) throws Exception{
        UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
        return dao.eliminar(id);
    }

    public static Usuario modificar(Integer id, String email, String password, String nombre, Integer edad) throws Exception{
        UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
        return dao.modificar(id, email, password, nombre, edad);
    }

    public static Usuario modificar(Usuario usuarioMod) throws Exception{
        UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
        return dao.modificar(usuarioMod);
    }

    public static HashMap<Integer, Usuario> leerTodos() throws Exception{
        UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
        return dao.obtenerTodos();
    }

    public static HashMap<Integer, Usuario> leerTodos(String nombre) throws Exception{
        UsuarioDAO dao = new UsuarioDAO(ConexionDerby.getConexion());
        return dao.obtenerTodos(nombre);
    }
}
