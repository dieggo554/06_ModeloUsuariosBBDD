package com.vn.test.servicio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vn.POJOs.Usuario;
import com.vn.servicio.UsuarioServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/** Testeao de la clase UsuarioServicio
 *
 * @author diego
 */
public class TestUsuarioService {

    final Integer edad;
    final String nombre, dominio, password;
    
    /** Inicializa variables que utilizaremos repetidamente.
     *
     */
    public TestUsuarioService() {
        edad = 50;
        nombre = "Eustaquio Habichuela";
        dominio = "@correo.com";
        password = "abc123.";
    }

    /** Crea un usuario en la base de datos, luego otro en memoria con las mismas
     * propiedades y compara que sean iguales.
     * También intenta crear de nuevo el mismo usuario y comprueba que no suceda,
     * finalmente elimina el usuario.
     *
     * @throws Exception
     */
    @Test
    public void testCrearDosVeces() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        Usuario local = new Usuario("crear" + dominio, password, nombre, edad);
        Usuario usuarioBD = srv.crear(local.getEdad() + "", local.getNombre(),local.getEmail(), local.getPassword());
        assertEquals(local, usuarioBD);
        assertNull(srv.crear(local.getEdad() + "", local.getNombre(),local.getEmail(), local.getPassword()));
        srv.eliminar(usuarioBD.getId());
    }
    
    /** Testea las validaciones de nombre, email, edad y password y comprueba
     * que no se han creado los usuarios con datos erroneos.
     *
     * @throws Exception
     */
    @Test
    public void testCrearMal() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        // Campo nulo
        Usuario usuario = srv.crear(edad + "", "A", null, password);
        assertNull(usuario);

        // Nombre corto
        usuario = srv.crear(edad + "", "A", "crearMal" + dominio, password);
        assertNull(usuario);
        
        // Nombre minúscula
//        usuario = srv.crear(edad + "", "alfredo", "crearMal" + dominio, password);
//        assertNull(usuario);
        
        // Nombre email incorrecto
        usuario = srv.crear(edad + "", nombre, "crearMal", password);
        assertNull(usuario);
        
        // Nombre edad baja
        usuario = srv.crear("12", nombre, "crearMal" + dominio, password);
        assertNull(usuario);
        
        // Nombre NaN
        usuario = srv.crear("LS", nombre, "crearMal" + dominio, password);
        assertNull(usuario);
        
        // Pass corta 
        usuario = srv.crear("12", nombre, "crearMal" + dominio, "123");
        assertNull(usuario);
    }
    
    /** Crea un usuario, recoge el id y lo busca para comprobar que el resultado
     * sean igual al creado, luego elimina el usuario.
     *
     * @throws Exception
     */
    @Test
    public void testLeerUsuarioPorId() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        Usuario usuario = srv.crear(edad + "", nombre, "leerId" + dominio, password);
        Integer id = usuario.getId();
        assertEquals(usuario, srv.leer(id));
        srv.eliminar(usuario.getId());
    }
    
    /** Crea un usuario, luego lo busca por email y los compara, finalmente
     * elimina el ususario.
     *
     * @throws Exception
     */
    @Test
    public void testLeerUsuarioPorEmail() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        Usuario usuario = srv.crear(edad + "", nombre, "leerEmail" + dominio, password);
        assertEquals(usuario, srv.leer("leerEmail" + dominio));
        srv.eliminar(usuario.getId());
    }
    
    /** Crea un usuario, lo elimina y comprueba que el método se ha ejecutado
     * correctamente luego lo busca para comprobar que no existe.
     *
     * @throws Exception
     */
    @Test
    public void testEliminarPorId() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        Usuario usuario = srv.crear(edad + "", nombre, "eliminarId" + dominio, password);
        Integer id = usuario.getId();
        assertNotNull(srv.leer("eliminarId" + dominio));
        Boolean eliminado = srv.eliminar(id);
        assertTrue(eliminado);
        assertNull(srv.leer(dominio));
    }
    
    /** Crea usuario y lo modifica, luego comprueba que dos parámetros se han
     * modificado, finalmente elimina el usuario.
     *
     * @throws Exception
     */
    @Test
    public void testModificarPorId() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        Integer id;
        Usuario usuario = srv.crear(edad + "", nombre, "modificarIdOriginal" + dominio, password);
        id = usuario.getId();
        Usuario usuarioMod = srv.modificar(id, "modificarIdModificado" + dominio, "12345", "Juan", edad);
        assertEquals("Juan", usuarioMod.getNombre());
        assertEquals("12345", usuarioMod.getPassword());
        srv.eliminar(usuario.getId());
    }

    /**Crea usuario y lo modifica, luego comprueba que dos parámetros se han
     * modificado, finalmente elimina el usuario.
     *
     * @throws Exception
     */
    @Test
    public void testModificarObjecto() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        Integer id;
        Usuario usuario = srv.crear(edad + "", nombre, "modificarObjOriginal" + dominio, password);
        id = usuario.getId();
        Usuario usuarioMod = new Usuario(id, "modificarObjModificado" + dominio, "12345", "Juan", edad);
        usuario = srv.modificar(usuarioMod);
        assertEquals("Juan", usuarioMod.getNombre());
        assertEquals("12345", usuarioMod.getPassword());
        srv.eliminar(usuario.getId());
    }
    
    /** Crea usuario y lo itenta modifica, luego comprueba que dos parámetros
     * no se han modificado, finalmente elimina el usuario.
     *
     * @throws Exception
     */
    @Test
    public void testModificarPorIdMal() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        Integer id;
        Usuario usuario = srv.crear(edad + "", nombre, "original" + dominio, password);
        id = usuario.getId();
        srv.modificar(id, "modificado" + dominio, "12345", ""
                + "p", edad);
        assertEquals(nombre, usuario.getNombre());
        assertEquals(password, usuario.getPassword());
        srv.eliminar(usuario.getId());
    }

    /** Crea 10 usuarios, luego pide todos los elementos, los recorre mostrandolos
     * y los elimina
     *
     * @throws Exception
     */
    @Test
    public void testLeerUsuarios() throws Exception {
        UsuarioServicio srv = new UsuarioServicio();
        Usuario us1 = srv.crear("51" , nombre, "djalsdjk" + dominio, password);
        Usuario us2 = srv.crear("52", nombre, "asladjlefuiweli" + dominio, password);
        
        HashMap<Integer, Usuario> usuarios = srv.leerTodos();
        
        if (usuarios.containsValue(us1)) {
            srv.eliminar(us1.getId());
        } else {
            fail("No se ha encontrado el usuario");
        }
        if (usuarios.containsValue(us2)) {
            srv.eliminar(us2.getId());
        } else {
            fail("No se ha encontrado el usuario");
        }
    }
}
