/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.DAO;

import java.util.HashMap;

/**
 *
 * @author pc
 * @param <T>
 */
public interface IGenericDao<T> {
    
    public abstract T crear(T  objetoNuevo) throws Exception;
    
    HashMap<Integer, T> obtenerTodos();
    
    T obtenerPorIndice(int index);
    
    T modificar(int index, T objConDatosNuevo) throws Exception;
    
    boolean eliminar(int index);
}