package controleestacionamento;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FrameConfig extends JFrame implements ActionListener{
    
       JPanel erro = new JPanel();
    
       JLabel valorCarro = new JLabel("Carro");
       JLabel valorMoto = new JLabel("Moto");
       JLabel valorCam = new JLabel("Camionete");
       
       JButton sairCriar = new JButton("Sair");
       JButton alterar = new JButton("Atualizar");
       JTextField valorCar = new JTextField(10);
       JTextField valorBike = new JTextField(10);
       JTextField valorTruck = new JTextField(10);

    public FrameConfig(){
        valorMoto.setBounds(50,50,100,100);
        valorCarro.setBounds(50,0,100,100);
        valorCam.setBounds(50,100,100,100);
        
        valorCar.setBounds(150,39,75,25);
        valorBike.setBounds(150,89,75,25);
        valorTruck.setBounds(150,139,75,25);
        
        alterar.setBounds(175,200,100,50);
        sairCriar.setBounds(45,200,100,50);
        
        erro.add(new JLabel("Erro"));
        
        add(alterar);
        add(valorTruck);
        add(valorCam);
        add(valorCar);
        add(valorCarro);
        add(sairCriar);
        add(valorBike);
        add(valorMoto);
        
        sairCriar.addActionListener(this);
        alterar.addActionListener(this);
           
        setLayout(null);    
        setTitle("Configuração");
        setSize(325,325);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    public void actionPerformed(ActionEvent event){
        Estacionamento est = Estacionamento.getInstance();
        
        if(event.getSource() == sairCriar){
            setVisible(false);
            try {
                FrameFrontEnd next = new FrameFrontEnd();
            } catch (IOException ex) {
                Logger.getLogger(FrameConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == alterar){
            try {
                est.configuracao(Double.parseDouble(valorBike.getText()),Double.parseDouble(valorCar.getText()),Double.parseDouble(valorTruck.getText()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Erro",JOptionPane.PLAIN_MESSAGE);
                //Logger.getLogger(FrameConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
            //setVisible(false);
            //FrameFrontEnd next = new FrameFrontEnd();
        }
    }
}
