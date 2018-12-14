package controleestacionamento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FrameFrontEnd extends JFrame implements ActionListener {
    JLabel pisoT = new JLabel("Térreo");
    JLabel pisoP = new JLabel("1º Piso");
    
    ImageIcon carroVazio = new ImageIcon(getClass().getResource("carroSim.png"));
    ImageIcon carroCheio= new ImageIcon(getClass().getResource("carroNao.png"));
    
    ImageIcon motoVazio = new ImageIcon(getClass().getResource("motoSim.png"));
    ImageIcon motoCheio= new ImageIcon(getClass().getResource("motoNao.png"));
    
    ImageIcon pickupVazio = new ImageIcon(getClass().getResource("pickupSim.jpg"));
    ImageIcon pickupCheio= new ImageIcon(getClass().getResource("pickupNao.jpg"));
    
    Panel piso1 = new Panel();
    Panel piso2 = new Panel();
    
    JButton[] piso11 = new JButton[100];
    JButton[] piso22 = new JButton[100];
    
    JButton config = new JButton("Configuração");
    JButton veiculo = new JButton("Veiculos");
    JButton estatisticas = new JButton("Estatisticas");
    
public FrameFrontEnd() throws IOException{
    Font f = new Font("SansSerif", Font.BOLD, 50);
    pisoT.setFont(f);
    pisoP.setFont(f);
    
    Estacionamento est = Estacionamento.getInstance();
    
    config.setBounds(600,300,150,70);
    veiculo.setBounds(600,400,150,70);
    estatisticas.setBounds(600,500,150,70);
    
    piso1.setLayout(new GridLayout(10,10));
    piso2.setLayout(new GridLayout(10,10));
    
    pisoT.setBounds(225,100,200,100);
    pisoP.setBounds(975,100,200,100);
    
    piso1.setBounds(50,200,500,500);
    piso2.setBounds(800,200,500,500);
    
    for (int i =0; i<100;i++){
        if (est.vagaCarroOcupada(i) == 0){
        piso11[i] = new JButton(carroVazio);
        piso1.add(piso11[i]);}
        else{
            piso11[i] = new JButton(carroCheio);
            piso1.add(piso11[i]);
        }
    }
    
    for (int i =0; i<60;i++){
        if (est.vagaCarroOcupada(i+100) == 0){
        piso22[i] = new JButton(carroVazio);
        piso2.add(piso22[i]);}
        else{
            piso22[i] = new JButton(carroCheio);
            piso2.add(piso22[i]);
        }
    }
    
    for (int i =60; i<80;i++){
        if (est.vagaMotoOcupada(i-60) == 0){
        piso22[i] = new JButton(motoVazio);
        piso2.add(piso22[i]);}
        else{
            piso22[i] = new JButton(motoCheio);
            piso2.add(piso22[i]);
        }
    }
    
    for (int i =80; i<100;i++){
        if (est.vagaCaminhoneteOcupada(i-80) == 0){
        piso22[i] = new JButton(pickupVazio);
        piso2.add(piso22[i]);}
        else{
            piso22[i] = new JButton(pickupCheio);
            piso2.add(piso22[i]);
        }
    }
    
    
    
    add(pisoT);
    add(pisoP);
    add(piso2);
    add(piso1);
    add(config);
    add(veiculo);
    add(estatisticas);
      
    config.addActionListener(this);
    veiculo.addActionListener(this);
    estatisticas.addActionListener(this);
    for(int i=0;i<100;i++){
    piso11[i].addActionListener(this);
    }
    for(int i=0;i<100;i++){
    piso22[i].addActionListener(this);
    }
    
    setLayout(null);    
    setTitle("Menu Principal");
    setSize(1500,1000);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
    
        TratadorArquivo.escreverLucro();
        TratadorArquivo.escreverCarros();
        TratadorArquivo.escreverMotos();
        TratadorArquivo.escreverCaminhonetes();
        TratadorArquivo.escreverEstacionamento();
    
    
}

    public void actionPerformed(ActionEvent event)
    {
        Estacionamento est = Estacionamento.getInstance();
        if(event.getSource()== config){
            setVisible(false);
            FrameConfig next = new FrameConfig();}
        if(event.getSource()== veiculo){
            setVisible(false);
            FrameVeiculos next = new FrameVeiculos();}
        if(event.getSource()== estatisticas){
            setVisible(false);
            FrameContabilidade next = new FrameContabilidade();}
        for(int i=0;i<100;i++){
            if(event.getSource()== piso11[i]){
                if(est.vagaCarroOcupada(i) != 0){
                    JOptionPane.showMessageDialog(null, "Placa do veículo " + est.placaCarro(i)+"", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Data de entrada do veículo " + est.dataCarro(i) +"", "Mensagem", JOptionPane.PLAIN_MESSAGE);   
                }
                else{
                    JOptionPane.showMessageDialog(null, "Vaga não ocupada");
                }
        }
        }
        
        for(int i=0;i<60;i++){
            if(event.getSource()== piso22[i]){
                if(est.vagaCarroOcupada(i) != 0){
                    JOptionPane.showMessageDialog(null, "Placa do veículo " + est.placaCarro(i)+"", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Data de entrada do veículo " + est.dataCarro(i) +"", "Mensagem", JOptionPane.PLAIN_MESSAGE);   
                }
                else{
                    JOptionPane.showMessageDialog(null, "Vaga não ocupada");
                }
        }
        }
        
        for(int i=60;i<80;i++){
            if(event.getSource()== piso22[i]){
                if(est.vagaMotoOcupada(i-60) != 0){
                    JOptionPane.showMessageDialog(null, "Placa do veículo " + est.placaMoto(i-60)+"", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Data de entrada do veículo " + est.dataMoto(i-60) +"", "Mensagem", JOptionPane.PLAIN_MESSAGE);   
                }
                else{
                    JOptionPane.showMessageDialog(null, "Vaga não ocupada");
                }
        }
        }
        
        for(int i=80;i<100;i++){
            if(event.getSource()== piso22[i]){
                if(est.vagaCaminhoneteOcupada(i-80) != 0){
                    JOptionPane.showMessageDialog(null, "Placa do veículo " + est.placaCam(i-80)+"", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Data de entrada do veículo " + est.dataCam(i-80) +"", "Mensagem", JOptionPane.PLAIN_MESSAGE);   
                }
                else{
                    JOptionPane.showMessageDialog(null, "Vaga não ocupada");
                }
        }
        }
        
        
    }
    
    
} 



    
