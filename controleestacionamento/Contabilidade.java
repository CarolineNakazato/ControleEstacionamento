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
public class Contabilidade implements Serializable{
    protected int dia;
    protected int mes;
    protected int ano;
    protected LucroDiario l;
    
    public Contabilidade(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        l = new LucroDiario(0,0,0,0);
    }
    
    public void setDia(int dia){
	this.dia = dia;		
    }

    public int getDia(){
	return dia;		
    } 
    
    public void setMes(int mes){
	this.mes = mes;		
    }

    public int getMes(){
	return mes;		
    } 
    
    public void setAno(int ano){
	this.ano = ano;		
    }

    public int getAno(){
	return ano;		
    } 
    
    public void setLucroDiario(LucroDiario l){
	this.l = l;		
    }

    public LucroDiario getLucroDiario(){
	return l;		
    } 
   
    public String imprime(){
        String s;
        s = "Data contabilidade: " + dia + "/" + mes + "/"+ ano + "\n"+
            l.imprime();
        return s;
    }
}
