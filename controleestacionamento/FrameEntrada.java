package controleestacionamento;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FrameEntrada extends JFrame implements ActionListener{
    
       String[] veiculos = {"Carro","Moto","Camionete"};
    
       JLabel placa = new JLabel("Placa do veículo");
       JLabel data = new JLabel("Data da entrada");
       JLabel hora = new JLabel("Hora da entrada");
       
       JLabel[] barra = new JLabel[4];
       
       JTextField horaText = new JTextField(25);
       JTextField minutoText = new JTextField(25);
       JTextField segundoText = new JTextField(25);
       JTextField diaText = new JTextField(25);
       JTextField mesText = new JTextField(25);
       JTextField anoText = new JTextField(25);
       
       JButton sairCriar = new JButton("Sair");
       JButton add = new JButton("Adicionar");
       
       JTextField placaText = new JTextField(25);
       
       JComboBox listaVei = new JComboBox(veiculos);

    public FrameEntrada(){
        
        barra[0] = new JLabel("/");
        barra[1] = new JLabel("/");
        barra[2] = new JLabel(":");
        barra[3] = new JLabel(":");

        barra[0].setBounds(165,90,30,30);
        barra[1].setBounds(215,90,30,30);
        barra[2].setBounds(165,140,30,25);
        barra[3].setBounds(215,140,30,25);
        
        
        Font f = new Font("SansSerif", Font.BOLD, 25);
        barra[0].setFont(f);
        barra[1].setFont(f);
        barra[2].setFont(f);
        barra[3].setFont(f);

        listaVei.setSelectedIndex(0);
        listaVei.addActionListener(this);

        hora.setBounds(25,100,100,100);
        placa.setBounds(25,0,100,100);
        data.setBounds(25,50,100,100);
        
        horaText.setBounds(130,140,30,30);
        minutoText.setBounds(180,140,30,30);
        segundoText.setBounds(230,140,30,30);
        diaText.setBounds(130,90,30,30);
        mesText.setBounds(180,90,30,30);
        anoText.setBounds(230,90,50,30);
        
        
        placaText.setBounds(130,40,75,25);
        listaVei.setBounds(230,40,100,25);
        
        add.setBounds(200,200,100,50);
        sairCriar.setBounds(50,200,100,50);
        
        add(barra[0]);
        add(barra[1]);
        add(barra[2]);
        add(barra[3]);
        
        add(segundoText);
        add(hora);
        add(horaText);
        add(minutoText);
        add(add);
        add(listaVei);
        add(data);
        add(diaText);
        add(mesText);
        add(anoText);
        add(placa);
        add(sairCriar);
        add(placaText);
        
        sairCriar.addActionListener(this);
        add.addActionListener(this);
        
        setLayout(null);    
        setTitle("Entrada de veículos");
        setSize(400,350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    public void actionPerformed(ActionEvent event){
        Estacionamento est = Estacionamento.getInstance();
        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();

        if(event.getSource() == sairCriar){
            setVisible(false);
            try {
                FrameFrontEnd next = new FrameFrontEnd();
            } catch (IOException ex) {
                Logger.getLogger(FrameEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == add){
            try{
            if (Integer.parseInt(diaText.getText()) > 28 && Integer.parseInt(diaText.getText()) < 32 && Integer.parseInt(mesText.getText()) == 2){
                JOptionPane.showMessageDialog(null, "Dia "+Integer.parseInt(diaText.getText())+" não disponível em fevereiro");
            }
            else{
            
            
                
       
            data.setDate(Integer.parseInt(diaText.getText()));
            data.setMonth(Integer.parseInt(mesText.getText())-1);
            data.setYear(Integer.parseInt(anoText.getText())-1900);
            data.setHours(Integer.parseInt(horaText.getText()));
            data.setMinutes(Integer.parseInt(minutoText.getText()));
            data.setSeconds(Integer.parseInt(segundoText.getText()));

            System.out.println(data);
            
            
            if (String.valueOf(listaVei.getSelectedItem()).equals("Carro")){
                try {
                    
                    est.entrarCarro(placaText.getText(),data);
                    JOptionPane.showMessageDialog(null, "Carro com placa "+placaText.getText()+" adicionado com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                    setVisible(false);
                    FrameFrontEnd next = new FrameFrontEnd();
                    //setVisible(false);
                   // FrameFrontEnd next = new FrameFrontEnd();
                } catch (Exception ex) {
                   // Logger.getLogger(FrameEntrada.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            }
            if (String.valueOf(listaVei.getSelectedItem()).equals("Moto")){
                try {
                    
                    est.entrarMoto(placaText.getText(),data);
                    JOptionPane.showMessageDialog(null, "Moto com placa "+placaText.getText()+" adicionado com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                    setVisible(false);
                    FrameFrontEnd next = new FrameFrontEnd();
                    //setVisible(false);
                    //FrameFrontEnd next = new FrameFrontEnd();
                } catch (Exception ex) {
                    //Logger.getLogger(FrameEntrada.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            }
            if (String.valueOf(listaVei.getSelectedItem()).equals("Camionete")){
                try {
                    
                    est.entrarCaminhonete(placaText.getText(),data);
                    JOptionPane.showMessageDialog(null, "Camionete com placa "+placaText.getText()+" adicionado com sucesso", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                    setVisible(false);
                    FrameFrontEnd next = new FrameFrontEnd();
                   // setVisible(false);
                   // FrameFrontEnd next = new FrameFrontEnd();
                } catch (Exception ex) {
                   // Logger.getLogger(FrameEntrada.class.getName()).log(Level.SEVERE, null, ex);
                     JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            }
            }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Erro",JOptionPane.PLAIN_MESSAGE);
            }
    }
}
}
