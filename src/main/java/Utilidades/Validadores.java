/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Zaurus
 */
public class Validadores {
     /**
     * Constructor por defecto.
     */
    public Validadores(){       
    }

    /**
     * Método para validar un número telefónico con las siguientes
     * especificaciones: Sintaxis de un número telefónico: (Lada) Local * Lada:
     * 2 o 3 dígitos * La Lada va entre paréntesis. * Las Ladas de 2 dígitos
     * son: 55, 33, 81. * Las Ladas de tres dígitos pueden contener cualquier
     * dígito. * Local: 8 o 7 dígitos, lada + local = 10 dígitos. * El número
     * local puede contener espacios o guíones
     *
     * @param tel teléfono a validar.
     * @return Verdadero o falso si se ha validado el número de teléfono.
     */
    public boolean validaTelefono(String tel){
        String patron = "(\\((81|55|33)\\)[-]?([0-9][-]?){8})|((\\([0-9]{3})\\)[-]?([0-9][-]?){7})";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(tel);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
    /**
     * Método para validar un email con las siguientes especificaciones:
     * Sintaxis de una dirección de correo electrónico: local@dominio La
     * longitud de local es de 1 hasta 20 caracteres. La longitud de dominio es
     * de 1 hasta 20 caracteres Caracteres válidos 'a'-'z', 'A'-'Z', '0'-'9', '.
     * ' y '_'. No pueden ir dos o más puntos seguidos.
     *
     * @param email email a validar.
     * @return Verdadero o falso si se ha validado el email.
     */
    public boolean validaEmail(String email){
        String patron = "^([a-zA-Z0-9_]|\\.(?:[^.])){1,20}@([a-zA-Z0-9_]|\\.(?:[^.])){1,20}$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(email);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
    /**
     * Método que valida un dato de la direccion.
     * @param datoDireccion dato de la direccion.
     * @return Verdadero o falso si se ha validado un dato de la direccion.
     */
    public boolean validaDatosDireccion(String datoDireccion){
        String patron = "^(?=.{1,50}$)[a-zA-Z0-9]+(?: [a-zA-Z0-9]+)*$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(datoDireccion);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
    /**
     * Método que valida un nombre.
     * @param nombre nombre a validar.
     * @return Verdadero o falso si se ha validado el nombre.
     */
    public boolean validaNombre(String nombre){
        String patron = "^(?=.{1,50}$)[a-zA-Z]+(?: [a-zA-Z]+)*$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(nombre);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
    /**
     * Método que valida una contraseña.
     * @param con contraseña a validar.
     * @return Verdadero o falso si se ha validado el contraseña.
     */
    public boolean validaContrasena(String con){
        String patron = "^(?i)(?=.*[a-z])(?=.*[0-9])[a-z0-9#.!@$*&_]{8,20}$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(con);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
}
