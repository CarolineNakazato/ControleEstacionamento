/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleestacionamento;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public abstract class Vaga implements Serializable{
    protected int posicao;
    
    public Vaga(int posicao){
        this.posicao = posicao;
    }
    
    public void setPosicao(int posicao){
	this.posicao = posicao;		
    }

    public int getPosicao(){
	return posicao;		
    } 
   
    public String imprime(){
        String s;
        s = "Posicao: " + posicao + "\n";
        return s;
    }
}
