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
public class VagaCarro extends Vaga implements Serializable{
    protected Carro carro;
    
    public VagaCarro(int posicao, Carro carro) {
        super(posicao);
        this.carro = carro;
    }
        
    public void setCarro(Carro carro){
	this.carro = carro;		
    }
     public Carro getCarro(){
	return carro;		
    } 
     
    @Override
    public String imprime(){
        String s;
        s = super.imprime();
        s += carro.imprime();
        return s;
    }
}
