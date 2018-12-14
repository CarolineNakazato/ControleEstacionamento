/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleestacionamento;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import javax.swing.JFrame;

/**
 *
 * @author PC
 */
public class ControleEstacionamento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TratadorArquivo.lerLucro();
        TratadorArquivo.lerCarros();
        TratadorArquivo.lerMotos();
        TratadorArquivo.lerCaminhonetes();
        TratadorArquivo.lerEstacionamento();
        Estacionamento estacionamento = Estacionamento.getInstance();
        FrameFrontEnd b1 = new FrameFrontEnd();
        b1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //b1.setSize(1500, 1000);
        b1.setVisible(true);
        

        
    }
    
}
