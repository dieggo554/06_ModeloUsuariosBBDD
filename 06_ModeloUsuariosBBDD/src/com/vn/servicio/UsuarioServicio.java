/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.servicio;

import com.vn.POJOs.Usuario;

/**
 *
 * @author pc
 */
public class UsuarioServicio {

    public static void crear(int edad, String nombre, String email, String contrasena) {
        if (edad>17 && nombre.length()>1 && email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") && contrasena.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")){
            Usuario nuevo = new Usuario(edad, nombre, email, contrasena);
            
        }
    }
    
}
