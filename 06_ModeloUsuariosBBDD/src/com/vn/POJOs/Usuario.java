/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.POJOs;

import com.vn.DAO.IDaoUsuario;
import java.util.HashMap;

/**
 *
 * @author pc
 */
public class Usuario{

    int id, edad;
    String nombre, email, password;

    public Usuario(int edad, String nombre, String email, String password) {
        this.edad = edad;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Usuario(int id, int edad, String nombre, String email, String password) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario:" + "id=" + id + ", edad=" + edad + ", nombre=" + nombre + ", email=" + email + ", password=" + password;
    }

    
}
