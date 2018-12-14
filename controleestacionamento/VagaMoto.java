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
public class VagaMoto extends Vaga implements Serializable{
    protected Moto moto;
    
    public VagaMoto(int posicao, Moto moto) {
        super(posicao);
        this.moto = moto;
    }
    
    public void setMoto(Moto moto){
	this.moto = moto;		
    }
     public Moto getMoto(){
	return moto;		
    } 
    
    @Override
    public String imprime(){
        String s;
        s = super.imprime();
        s += moto.imprime();
        return s;
    }
}
