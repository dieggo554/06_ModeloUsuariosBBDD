package com.vn.test.servicio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.vn.POJOs.Usuario;
import com.vn.servicio.UsuarioServicio;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc
 */
public class TestUsuarioService {
    
     @Test
     public void testCrearUsuario(Usuario nuevoUsuario) {
         UsuarioServicio.crear("Eustaquio Habichuela", "correo@corre.com", "abc123.");
         
     }
     
     @Test
     public void testLeerUsuarioPorId(Integer id) {
         
     }
     
     @Test
     public void testLeerTodosUsuario() {
         
     }
     
     @Test
     public void testLeerUsuarioPorEmail(String email) {
         
     }

     @Test
     public void testActualizarPorId(Integer id) {
         
     }
     
     @Test
     public void testActualizarPorEmail(Integer email) {
         
     }
     
     @Test
     public void testEliminarPorId(Integer id) {
         
     }
     
     @Test
     public void testEliminarPorEmail(Integer email) {
         
     }
}
