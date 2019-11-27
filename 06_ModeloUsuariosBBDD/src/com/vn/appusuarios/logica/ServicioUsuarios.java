/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.appusuarios.logica;

import com.vn.appusuarios.dao.UsuarioDAO;
import com.vn.appusuarios.modelo.Usuario;
import com.vn.appusuarios.dao.ConexionDerby;
import com.vn.appusuarios.modelo.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pc
 */
public class ServicioUsuarios {

    private void notificarError(String message) {
        if (err != null) {
            err.mostrarError(message);
        }
    }

    private ChivatoServicios err;

    public void setChivatoListener(ChivatoServicios err) {
        this.err = err;
    }

    public final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public Usuario crear(String edad, String nombre, String email, String contrasena) {
        Usuario nuevo = null;
        boolean parseable = false;
        try {
            if (edad != null && nombre != null && email != null && contrasena != null) {
                try {
                    Integer intEdad = Integer.parseInt(edad);
                    parseable = true;

                } catch (Exception ex) {
                    System.out.println("edad tiene que ser casteable a Integer");
                }
                if (parseable && Integer.parseInt(edad) > 12 && nombre.length() > 3 && contrasena.length() > 4 && email.length() > 3 && validate(email)) {
                    UsuarioDAO dao = new UsuarioDAO();
                    Usuario usuario = null;
                    if (dao.obtenerPorEmail(email) == null) {
                        nuevo = new Usuario(email, contrasena, nombre, Integer.parseInt(edad));
                        usuario = dao.crear(nuevo);
                    } else {
                        throw new Exception("El email ya existe:" + email);
                    }
                }
            }

        } catch (Exception ex) {
            notificarError("Error en UsuariosServlets.crear(...): " + ex.getMessage());
        }
        return nuevo;
    }

    public Usuario leer(Integer id) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario buscado = null;
        try {
            buscado = dao.obtenerPorIndice(id);
        } catch (Exception ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            notificarError("Error en UsuariosServlets.leer(id): " + ex.getMessage());
        }
        return buscado;

    }

    public Usuario leer(String email) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario buscado = null;
        try {
            buscado = dao.obtenerPorEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            notificarError("Error en UsuariosServlets.leer(email): " + ex.getMessage());
        }
        return buscado;
    }

    public Boolean eliminar(Integer id) {
        UsuarioDAO dao = new UsuarioDAO();
        boolean eliminado = false;
        try {
            eliminado = dao.eliminar(id);
        } catch (Exception ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            notificarError("Error en UsuariosServlets.eliminar(id): " + ex.getMessage());
        }
        return eliminado;
    }

    public Usuario modificar(Integer id, String email, String password, String nombre, Integer edad) {

        try {
            if (edad != null && nombre != null && email != null && password != null) {
                if (edad > 12 && nombre.length() > 1 && password.length() > 4 && email.length() > 3 && validate(email)) {
                    UsuarioDAO dao = new UsuarioDAO();
                    return dao.modificar(id, email, password, nombre, edad);
                }
            }
        } catch (Exception ex) {
            notificarError("Error en UsuariosServlets.crear(...): " + ex.getMessage());
        }
        return null;
    }

    public Usuario modificar(Usuario usuarioMod) {
        try {

            if (usuarioMod.getEdad() > 12 && usuarioMod.getNombre().length() > 1 && usuarioMod.getPassword().length() > 4 && usuarioMod.getEmail().length() > 3 && validate(usuarioMod.getEmail())) {
                UsuarioDAO dao = new UsuarioDAO();
                return dao.modificar(usuarioMod);
            }
        } catch (Exception ex) {
            notificarError("Error en UsuariosServlets.crear(Usuario): " + ex.getMessage());
        }
        return null;
    }

    public HashMap<Integer, Usuario> leerTodos() {
        UsuarioDAO dao = new UsuarioDAO();
        HashMap<Integer, Usuario> todos = null;
        try {
            todos = dao.obtenerTodos();
        } catch (Exception ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            notificarError("Error en UsuariosServlets.leerTodos(): " + ex.getMessage());
        }
        return todos;
    }
}
