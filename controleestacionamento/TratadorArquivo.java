/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleestacionamento;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author PC
 */
public class TratadorArquivo {
        public static void lerLucro() throws IOException, ClassNotFoundException {
        Estacionamento estacionamento  = Estacionamento.getInstance();
        FileInputStream arquivoLeitura = new FileInputStream("contabilidade.dat");
        ObjectInputStream objLeitura   = new ObjectInputStream(arquivoLeitura);
        
        estacionamento.conta =  (ArrayList<Contabilidade>) objLeitura.readObject();
        
        objLeitura.close();
        arquivoLeitura.close();
    }
 
    public static void escreverLucro() throws IOException {
        Estacionamento estacionamento = Estacionamento.getInstance();
        FileOutputStream fos   = new FileOutputStream("contabilidade.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        int n = estacionamento.conta.size();
        for (int i = 0; i < n; i++) {
            oos.writeObject(estacionamento.conta);          
        }  
        oos.close();
    }
     
    public static void lerCarros() throws IOException, ClassNotFoundException {
        Estacionamento estacionamento  = Estacionamento.getInstance();
        FileInputStream arquivoLeitura = new FileInputStream("carros.dat");
        ObjectInputStream objLeitura   = new ObjectInputStream(arquivoLeitura);
        
        estacionamento.vagasCarros =  (ArrayList<VagaCarro>) objLeitura.readObject();
        
        objLeitura.close();
        arquivoLeitura.close();
    }
 
    public static void escreverCarros() throws IOException {
        Estacionamento estacionamento = Estacionamento.getInstance();
        FileOutputStream fos   = new FileOutputStream("carros.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        int n = estacionamento.vagasCarros.size();
        for (int i = 0; i < n; i++) {
            oos.writeObject(estacionamento.vagasCarros);          
        }   
        oos.close();
    }
    
    public static void lerMotos() throws IOException, ClassNotFoundException {
        Estacionamento estacionamento  = Estacionamento.getInstance();
        FileInputStream arquivoLeitura = new FileInputStream("motos.dat");
        ObjectInputStream objLeitura   = new ObjectInputStream(arquivoLeitura);
        
        estacionamento.vagasMotos = (ArrayList<VagaMoto>) objLeitura.readObject();
        
        objLeitura.close();
        arquivoLeitura.close();
    }
 
    public static void escreverMotos() throws IOException {
        Estacionamento estacionamento = Estacionamento.getInstance();
        FileOutputStream fos   = new FileOutputStream("motos.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        int n = estacionamento.vagasMotos.size();
        for (int i = 0; i < n; i++) {
            oos.writeObject(estacionamento.vagasMotos);          
        }  
        oos.close();
    }
    
    public static void lerCaminhonetes() throws IOException, ClassNotFoundException {
        Estacionamento estacionamento  = Estacionamento.getInstance();
        FileInputStream arquivoLeitura = new FileInputStream("caminhonetes.dat");
        ObjectInputStream objLeitura   = new ObjectInputStream(arquivoLeitura);
        
        estacionamento.vagasCaminhonetes = (ArrayList<VagaCaminhonete>) objLeitura.readObject();
       
        objLeitura.close();
        arquivoLeitura.close();
    }
 
    public static void escreverCaminhonetes() throws IOException {
        Estacionamento estacionamento = Estacionamento.getInstance();
        FileOutputStream fos   = new FileOutputStream("caminhonetes.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        int n = estacionamento.vagasCaminhonetes.size();
        for (int i = 0; i < n; i++) {
            oos.writeObject(estacionamento.vagasCaminhonetes);          
        }   
        oos.close();
    }
 
    
    public static void lerEstacionamento() throws IOException, ClassNotFoundException {
        Estacionamento estacionamento  = Estacionamento.getInstance();
        FileInputStream arquivoLeitura = new FileInputStream("estacionamento.dat");
        ObjectInputStream objLeitura   = new ObjectInputStream(arquivoLeitura);
        
        estacionamento = (Estacionamento) objLeitura.readObject();
       
        objLeitura.close();
        arquivoLeitura.close();
    }
 
    public static void escreverEstacionamento() throws IOException {
        Estacionamento estacionamento = Estacionamento.getInstance();
        FileOutputStream fos   = new FileOutputStream("estacionamento.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(estacionamento);          
        oos.close();
    }
}
