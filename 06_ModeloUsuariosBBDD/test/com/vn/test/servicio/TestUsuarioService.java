package com.vn.test.servicio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vn.POJOs.Usuario;
import com.vn.servicio.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc
 */
public class TestUsuarioService {

    final Integer edad;
    final String nombre, email, password;
    
    public TestUsuarioService() {
        edad = 50;
        nombre = "Eustaquio Habichuela";
        email = "@corre.com";
        password = "abc123.";
    }

    @Test
    public void testCrear() {
        Usuario usuario = UsuarioServicio.crear(edad, nombre, "crear" + email, password);
        assertEquals(new Usuario(email, password, nombre, edad), usuario);
    }
    
    @Test
    public void testLeerUsuarioPorId() {
        Usuario usuario = UsuarioServicio.crear(edad, nombre, "leerId" + email, password);
        Integer id = usuario.getId();
        assertEquals(usuario, UsuarioServicio.leer(id));
    }
    
    @Test
    public void testLeerUsuarioPorEmail() {
        Usuario usuario = UsuarioServicio.crear(edad, nombre, "leerEmail" + email, password);
        assertEquals(usuario, UsuarioServicio.leer(email));
    }
    
    @Test
    public void testEliminarPorId() {
        Usuario usuario = UsuarioServicio.crear(edad, nombre, "eliminarId" + email, password);
        Integer id = usuario.getId();
        Boolean eliminado = UsuarioServicio.eliminar(id);
        assertTrue(eliminado);
        assertNull(UsuarioServicio.leer(email));
    }
    
    @Test
    public void testModificarPorId() {
        Integer id;
        Usuario usuario = UsuarioServicio.crear(edad, nombre, "modificarId" + email, password);
        id = usuario.getId();
        Usuario usuarioMod = UsuarioServicio.modificar(id, "modificarId" + email, "12345", "Juan", edad);
        assertEquals("Juan", usuarioMod.getNombre());
        assertEquals("12345", usuarioMod.getPassword());
    }

    @Test
    public void testModificarObjecto() {
        Integer id;
        Usuario usuario = UsuarioServicio.crear(edad, nombre, "modificarObj" + email, password);
        id = usuario.getId();
        Usuario usuarioMod = new Usuario(id, "modificarObj" + email, "12345", "Juan", edad);
        usuario = UsuarioServicio.modificar(usuarioMod);
        assertEquals(usuarioMod, usuario);
    }

    @Test
    public void testLeerUsuarios() {
        for (int i = 1; i <= 10; i++) {
            UsuarioServicio.crear(i, i + "º - " + nombre, i + "º - " + email, i + "º - " + password);
        }
        List<Usuario> usuarios = UsuarioServicio.leerTodos();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
    
    @Test
    public void testLeerUsuariosPorNombre() {
        for (int i = 1; i <= 5; i++) {
            UsuarioServicio.crear(i, i + "º - " + nombre, i + "º - " + email, i + "º - " + password);
        }
        UsuarioServicio.crear(50, "Juancho", "emilio@aqui.si", "asdfg");
        UsuarioServicio.crear(55, "Alfredo", "alfi@dredo.no", "qwerty");
        List<Usuario> usuarios = UsuarioServicio.leerTodos(nombre);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
