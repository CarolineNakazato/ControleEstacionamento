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
public class VagaCaminhonete extends Vaga implements Serializable{
    protected Caminhonete caminhonete;
    
    public VagaCaminhonete(int posicao, Caminhonete caminhonete) {
        super(posicao);
        this.caminhonete = caminhonete;
    }
        
    public void setCaminhonete(Caminhonete caminhonete){
	this.caminhonete = caminhonete;		
    }
     public Caminhonete getCaminhonte(){
	return caminhonete;		
    } 
     
    @Override
    public String imprime(){
        String s;
        s = super.imprime();
        s += caminhonete.imprime();
        return s;
    }
}
