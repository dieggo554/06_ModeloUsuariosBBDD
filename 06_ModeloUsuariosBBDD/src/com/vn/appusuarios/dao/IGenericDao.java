/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.appusuarios.dao;

import java.util.HashMap;

/** Interfaz genérica con los métodos para CRUD.
 *
 * @author diego
 * @param <T>
 */
public interface IGenericDao<T> {
    
    public abstract T crear(T  objetoNuevo) throws Exception;
    
    HashMap<Integer, T> obtenerTodos() throws Exception;
    
    T obtenerPorIndice(int index) throws Exception;
    
    T modificar(T objConDatosNuevo) throws Exception;
    
    boolean eliminar(int index) throws Exception;
}
