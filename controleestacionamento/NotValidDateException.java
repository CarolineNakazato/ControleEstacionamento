/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleestacionamento;

/**
 *
 * @author PC
 */
public class NotValidDateException extends Exception {
    public NotValidDateException(String msg){ 
        super(msg); 
    } 
    public NotValidDateException(String msg, Throwable cause){ 
        super(msg, cause); 
    }
} 