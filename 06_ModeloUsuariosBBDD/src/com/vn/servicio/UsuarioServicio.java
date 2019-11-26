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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pc
 */
public class UsuarioServicio {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
            return matcher.find();
    }

    public static Usuario crear(String edad, String nombre, String email, String contrasena) throws Exception {
        Usuario nuevo = null;
        boolean parseable=false;
        try {
            if (edad!=null && nombre!=null && email!=null && contrasena!=null) {
                try{
                    Integer intEdad = Integer.parseInt(edad);
                    parseable=true;
                    
                }catch(Exception ex){
                    System.out.println("edad tiene que ser casteable a Integer");
                }
                if (parseable && Integer.parseInt(edad)>12 && nombre.length()>3 && contrasena.length()>4 && email.length()>3 && validate(email)){
                    UsuarioDAO dao = new UsuarioDAO();
                    if (dao.obtenerPorEmail(email)==null) {
                        nuevo = new Usuario(email, contrasena, nombre, Integer.parseInt(edad));
                        return dao.crear(nuevo);
                    }
//                    nuevo = dao.obtenerPorEmail(email);
                    return nuevo;
                }
            }

        }catch(Exception ex){
        }
        return nuevo;
    }

    public static Usuario leer(Integer id) throws Exception {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario buscado = dao.obtenerPorIndice(id);
        return buscado;
        
    }

    public static Usuario leer(String email) throws Exception {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario buscado = dao.obtenerPorEmail(email);
        return buscado;
    }

    public static Boolean eliminar(Integer id) throws Exception{
        UsuarioDAO dao = new UsuarioDAO();
        return dao.eliminar(id);
    }

    public static Usuario modificar(Integer id, String email, String password, String nombre, Integer edad) throws Exception{
        
        try {
            if (edad!=null && nombre!=null && email!=null && password!=null) {
                if (edad>12 && nombre.length()>1 && password.length()>4 && email.length()>3 && validate(email)){
                    UsuarioDAO dao = new UsuarioDAO();
                    return dao.modificar(id, email, password, nombre, edad);
                }
            }
        }catch(Exception ex){
                    
        }
        return null;
    }

    public static Usuario modificar(Usuario usuarioMod) throws Exception{
        try {
        
            if (usuarioMod.getEdad()>12 && usuarioMod.getNombre().length()>1 && usuarioMod.getPassword().length()>4 && usuarioMod.getEmail().length()>3 && validate(usuarioMod.getEmail())){
                UsuarioDAO dao = new UsuarioDAO();
                return dao.modificar(usuarioMod);
            }
        }catch(Exception ex){
        }
        return null;
    }

    public static HashMap<Integer, Usuario> leerTodos() throws Exception{
        UsuarioDAO dao = new UsuarioDAO();
        return dao.obtenerTodos();
    }

    public static HashMap<Integer, Usuario> leerTodos(String nombre) throws Exception{
        UsuarioDAO dao = new UsuarioDAO();
        return dao.obtenerTodos(nombre);
    }
}
