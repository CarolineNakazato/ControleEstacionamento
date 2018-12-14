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
public class NoSpaceException extends Exception {
    public NoSpaceException(String msg){ 
        super(msg); 
    } 
    public NoSpaceException(String msg, Throwable cause){ 
        super(msg, cause); 
    }
} 
