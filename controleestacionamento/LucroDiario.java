/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleestacionamento;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PC
 */
public class LucroDiario implements Serializable{
    protected double valor;
    protected int qtdCarro, qtdMoto, qtdCaminhonete;
    
    public LucroDiario(double valor, int qtdCarro,int qtdMoto,int qtdCaminhonete) {
        this.valor          = valor;
        this.qtdCarro       = qtdCarro;
        this.qtdMoto        = qtdMoto;
        this.qtdCaminhonete = qtdCaminhonete;
    }
    
    public LucroDiario(int dia) {
        this.valor          = 0;
        this.qtdCarro       = 0;
        this.qtdMoto        = 0;
        this.qtdCaminhonete = 0;
    }
        
    public void setValor(double valor){
	this.valor = valor;		
    }
    
    public double getValor(){
	return valor;		
    } 
    
    public int qtdVeiculos(){
	return qtdCarro + qtdMoto + qtdCaminhonete;		
    } 
     
    public String imprime(){
        String s;
        s = "Valor:" + valor + "\n"+
            "Quantidade de Carros:" + qtdCarro + "\n"+
            "Quantidade de Motos:" + qtdMoto + "\n"+
            "Quantidade de Caminhonetes:" + qtdCaminhonete + "\n";
            //"Data:"  + data.getDay()+"/"+data.getMonth()+"/"+data.getYear() + "\n";
        return s;
    }
}
