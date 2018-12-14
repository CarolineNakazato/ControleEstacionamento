/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleestacionamento;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PC
 */
public abstract class Veiculo implements Serializable{
    protected String placa;
    protected Date entrada;
    protected Date saida;
    protected double valor;
    protected double precoPorHora;
    
    public Veiculo(String placa, Date entrada, double precoPorHora){
        this.placa   = placa;
        this.entrada = entrada;
        this.saida   = null;
        this.valor   = 0;
        this.precoPorHora = precoPorHora;
    }
    
    public void setPlaca(String placa){
	this.placa = placa;		
    }

    public String getPlaca(){
	return placa;		
    } 
    public void setEntrada(Date entrada){
	this.entrada = entrada;		
    }

    public Date getEntrada(){
	return entrada;		
    }
    
    public void setSaida(Date saida){
	this.saida = saida;		
    }

    public Date getSaida(){
	return saida;		
    }
    
    public void setValor(double valor){
	this.valor = valor;		
    }

    public double getValor(){
	return valor;		
    }
    
    public void setPrecoPorHora(double precoPorHora){
	this.precoPorHora = precoPorHora;		
    }

    public double getPrecoPorHora(){
	return precoPorHora;		
    }
    
    //calcula o valor a ser pago
    public double calculaValor() throws Exception{
        LocalDate localDate = entrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int day   = localDate.getDayOfMonth();
        
        LocalDate localDateSaida = saida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int daySaida   = localDateSaida.getDayOfMonth();
        
        int hora = saida.getHours() - entrada.getHours();
        int dia  = daySaida   - day;
            
        valor = dia*24*precoPorHora + hora*precoPorHora;
        
        //System.out.println("NA FUNCAO "+"\n"+imprime());
        
        return valor;
    }
    
    public String imprime(){
        
        LocalDate localDate = entrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        
        String precoPorHoraFormat = String.format("%.2f", precoPorHora);
        String valorFormat = String.format("%.2f", valor);
        String s;
        if(saida == null){
        s = "Placa: "   + placa   + "\n" +
            //"Data: "    + horario + "\n" +
            "Entrada: " + day + "/" + month + "/" + year + " " + 
                          entrada.getHours()+ ":" + entrada.getMinutes()+ ":" +  entrada.getSeconds()+"\n" +
            "Preco por hora: " + precoPorHoraFormat + "\n";
        }else{
            
                    LocalDate localDateSaida = saida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int yearSaida  = localDateSaida.getYear();
        int monthSaida = localDateSaida.getMonthValue();
        int daySaida   = localDateSaida.getDayOfMonth();
        
        
        
        
         s = "Placa: "   + placa   + "\n" +
            //"Data: "    + horario + "\n" +
            "Entrada: " + day + "/" + month + "/" + year + " " + 
                          entrada.getHours()+ ":" + entrada.getMinutes()+ ":" +  entrada.getSeconds()+"\n" +
            "Saida: "   + daySaida+ "/" + monthSaida + "/" + yearSaida + " " + 
                          saida.getHours()+ ":" + saida.getMinutes()+ ":" +  saida.getSeconds()+"\n" +
            "Valor: "   + valorFormat + "\n"+
            "Preco por hora: " + precoPorHoraFormat + "\n";
        }
        return s;
    }
}
