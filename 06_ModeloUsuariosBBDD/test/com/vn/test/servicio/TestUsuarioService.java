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
        Usuario usuario = UsuarioServicio.crear(edad + "", nombre, "crear" + dominio, password);
        assertEquals(new Usuario("crear" + dominio, password, nombre, edad), usuario);
        assertNull(UsuarioServicio.crear(edad + "", nombre, "crear" + dominio, password));
        UsuarioServicio.eliminar(usuario.getId());
    }
    
    /** Testea las validaciones de nombre, email, edad y password y comprueba
     * que no se han creado los usuarios con datos erroneos.
     *
     * @throws Exception
     */
    @Test
    public void testCrearMal() throws Exception {
        // Campo nulo
        Usuario usuario = UsuarioServicio.crear(edad + "", "A", null, password);
        assertNull(usuario);

        // Nombre corto
        usuario = UsuarioServicio.crear(edad + "", "A", "crearMal" + dominio, password);
        assertNull(usuario);
        
        // Nombre minúscula
        usuario = UsuarioServicio.crear(edad + "", "alfredo", "crearMal" + dominio, password);
        assertNull(usuario);
        
        // Nombre email incorrecto
        usuario = UsuarioServicio.crear(edad + "", nombre, "crearMal", password);
        assertNull(usuario);
        
        // Nombre edad baja
        usuario = UsuarioServicio.crear("12", nombre, "crearMal" + dominio, password);
        assertNull(usuario);
        
        // Nombre NaN
        usuario = UsuarioServicio.crear("LS", nombre, "crearMal" + dominio, password);
        assertNull(usuario);
        
        // Pass corta 
        usuario = UsuarioServicio.crear("12", nombre, "crearMal" + dominio, "123");
        assertNull(usuario);
    }
    
    /** Crea un usuario, recoge el id y lo busca para comprobar que el resultado
     * sean igual al creado, luego elimina el usuario.
     *
     * @throws Exception
     */
    @Test
    public void testLeerUsuarioPorId() throws Exception {
        Usuario usuario = UsuarioServicio.crear(edad + "", nombre, "leerId" + dominio, password);
        Integer id = usuario.getId();
        assertEquals(usuario, UsuarioServicio.leer(id));
        UsuarioServicio.eliminar(usuario.getId());
    }
    
    /** Crea un usuario, luego lo busca por email y los compara, finalmente
     * elimina el ususario.
     *
     * @throws Exception
     */
    @Test
    public void testLeerUsuarioPorEmail() throws Exception {
        Usuario usuario = UsuarioServicio.crear(edad + "", nombre, "leerEmail" + dominio, password);
        assertEquals(usuario, UsuarioServicio.leer("leerEmail" + dominio));
        UsuarioServicio.eliminar(usuario.getId());
    }
    
    /** Crea un usuario, lo elimina y comprueba que el método se ha ejecutado
     * correctamente luego lo busca para comprobar que no existe.
     *
     * @throws Exception
     */
    @Test
    public void testEliminarPorId() throws Exception {
        Usuario usuario = UsuarioServicio.crear(edad + "", nombre, "eliminarId" + dominio, password);
        Integer id = usuario.getId();
        Boolean eliminado = UsuarioServicio.eliminar(id);
        assertTrue(eliminado);
        assertNull(UsuarioServicio.leer(dominio));
    }
    
    /** Crea usuario y lo modifica, luego comprueba que dos parámetros se han
     * modificado, finalmente elimina el usuario.
     *
     * @throws Exception
     */
    @Test
    public void testModificarPorId() throws Exception {
        Integer id;
        Usuario usuario = UsuarioServicio.crear(edad + "", nombre, "modificarIdOriginal" + dominio, password);
        id = usuario.getId();
        Usuario usuarioMod = UsuarioServicio.modificar(id, "modificarIdModificado" + dominio, "12345", "Juan", edad);
        assertEquals("Juan", usuarioMod.getNombre());
        assertEquals("12345", usuarioMod.getPassword());
        UsuarioServicio.eliminar(usuario.getId());
    }

    /**Crea usuario y lo modifica, luego comprueba que dos parámetros se han
     * modificado, finalmente elimina el usuario.
     *
     * @throws Exception
     */
    @Test
    public void testModificarObjecto() throws Exception {
        Integer id;
        Usuario usuario = UsuarioServicio.crear(edad + "", nombre, "modificarObjOriginal" + dominio, password);
        id = usuario.getId();
        Usuario usuarioMod = new Usuario(id, "modificarObjModificado" + dominio, "12345", "Juan", edad);
        usuario = UsuarioServicio.modificar(usuarioMod);
        assertEquals("Juan", usuarioMod.getNombre());
        assertEquals("12345", usuarioMod.getPassword());
        UsuarioServicio.eliminar(usuario.getId());
    }

    /** Crea 10 usuarios, luego pide todos los elementos, los recorre mostrandolos
     * y los elimina
     *
     * @throws Exception
     */
    @Test
    public void testLeerUsuarios() throws Exception {
        for (int i = 1; i <= 10; i++) {
            UsuarioServicio.crear(i + "", i + "º - " + nombre, i + "º - " + dominio, i + "º - " + password);
        }
        HashMap<Integer, Usuario> usuarios = UsuarioServicio.leerTodos();
        for (Map.Entry<Integer, Usuario> entry : usuarios.entrySet()) {
            Integer key = entry.getKey();
            Usuario value = entry.getValue();
            System.out.println(value);
            UsuarioServicio.eliminar(key);
        }
    }
    
    /** Crea 5 usuarios con el mismo nombre, luego crea 2 con nombres diferentes
     * finalmente recorre y muestra todos los Usuarios y los elimina.
     *
     * @throws Exception
     */
    @Test
    public void testLeerUsuariosPorNombre() throws Exception {
        for (int i = 1; i <= 5; i++) {
            UsuarioServicio.crear(i + "", i + "º - " + nombre, i + "º - " + dominio, i + "º - " + password);
        }
        UsuarioServicio.crear("50", "Juancho", "emilio@aqui.si", "asdfg");
        UsuarioServicio.crear("55", "Alfredo", "alfi@dredo.no", "qwerty");
        HashMap<Integer, Usuario> usuarios = UsuarioServicio.leerTodos(nombre);
        assertEquals(7, usuarios.size());
        usuarios.entrySet().forEach((usuario) -> {
            Integer id = usuario.getKey();
            Usuario obj = usuario.getValue();
            System.out.println(obj);
            UsuarioServicio.eliminar(id);
        });
    }
}
