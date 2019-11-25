/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.DAO;

import com.vn.POJOs.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Equipo 1
 */
public class UsuarioDAO implements IDaoUsuario{

    Connection con;
    
    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    
    @Override
    public Usuario obtenerPorEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario modificarPorEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarPorEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario crear(Usuario objetoNuevo) throws Exception {
        String sqlQuery = "INSERT INTO Usuario()";
        PreparedStatement sentenciaSQL = con.prepareStatement(sqlQuery);
        sentenciaSQL.setString(1, busca);//sustituir el primer interrogante por el contenido del nombre
        ResultSet resultado = sentenciaSQL.executeQuery();
    }

    @Override
    public HashMap<Integer, Usuario> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario obtenerPorIndice(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario modificar(int index, Usuario objConDatosNuevo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Usuario objEliminable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
