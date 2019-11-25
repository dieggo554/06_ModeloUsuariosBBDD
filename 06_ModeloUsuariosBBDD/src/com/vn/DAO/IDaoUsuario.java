/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.DAO;

import com.vn.POJOs.Usuario;
import java.util.HashMap;


/**
 *
 * @author pc
 */
public interface IDaoUsuario extends IGenericDao<Usuario>{
        
    public Usuario obtenerPorEmail(String email) throws Exception;
    
    public Usuario modificar(int id, String email, String password, String nombre, int age)  throws Exception;

    public boolean eliminarPorEmail(String email) throws Exception;
    
    public HashMap<Integer, Usuario> obtenerTodos(String nombre) throws Exception;
    
}
