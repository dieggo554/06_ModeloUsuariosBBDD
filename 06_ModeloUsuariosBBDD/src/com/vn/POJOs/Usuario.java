/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.POJOs;

import com.vn.DAO.IDaoUsuario;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author pc
 */
public class Usuario {

    int id, edad;
    String nombre, email, password;

    public Usuario(String email, String password, String nombre, int edad) {
        this.edad = edad;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Usuario(int id, String email, String password, String nombre, int edad) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.edad != other.edad) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.edad;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.password);
        return hash;
    }
}
