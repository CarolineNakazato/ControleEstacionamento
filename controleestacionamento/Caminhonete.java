/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleestacionamento;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Caminhonete extends Veiculo implements Serializable{
    
    public Caminhonete(String placa, Date entrada, double preco) {
        super(placa, entrada, preco);
    }
        
}
