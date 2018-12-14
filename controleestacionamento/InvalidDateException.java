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
public class InvalidDateException extends Exception {
    public InvalidDateException(String msg){ 
        super(msg); 
    } 
    public InvalidDateException(String msg, Throwable cause){ 
        super(msg, cause); 
    }
} 