package controleestacionamento;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FrameContabilidade extends JFrame implements ActionListener{
       
       JLabel dias = new JLabel("Selecione o período dos dias");
       JLabel dataIni = new JLabel("Data Inicio");
       JLabel dataFim = new JLabel("Data Fim");
       
       JLabel[] barra = new JLabel[4];

       JTextField diaIni = new JTextField(25);
       JTextField mesIni = new JTextField(25);
       JTextField anoIni = new JTextField(25);
       JTextField diaFim = new JTextField(25);
       JTextField mesFim = new JTextField(25);
       JTextField anoFim = new JTextField(25);
       
       //JTextField inicio = new JTextField(25);
      // JTextField fim = new JTextField(25);
       JButton sairCriar = new JButton("Sair");
       JButton conta = new JButton("Contabilizar");

    public FrameContabilidade(){
        barra[0] = new JLabel("/");
        barra[1] = new JLabel("/");
        barra[2] = new JLabel("/");
        barra[3] = new JLabel("/");

        barra[0].setBounds(60,100,30,30);
        barra[1].setBounds(110,100,30,30);
        barra[2].setBounds(60,160,30,30);
        barra[3].setBounds(110,160,30,30);
        
        
        Font f = new Font("SansSerif", Font.BOLD, 25);
        barra[0].setFont(f);
        barra[1].setFont(f);
        barra[2].setFont(f);
        barra[3].setFont(f);
        
        diaIni.setBounds(25,100,30,30);
        mesIni.setBounds(75,100,30,30);
        anoIni.setBounds(125,100,50,30);
        diaFim.setBounds(25,160,30,30);
        mesFim.setBounds(75,160,30,30);
        anoFim.setBounds(125,160,50,30);
         
        dataIni.setBounds(25,40,100,100);
        dataFim.setBounds(25,100,100,100);
        
        dias.setBounds(25,0,200,100);
       // inicio.setBounds(120,90,50,25);
       // fim.setBounds(120,140,50,25);
        sairCriar.setBounds(20,210,100,50);  
        conta.setBounds(150,210,100,50); 
        
        add(barra[0]);
        add(barra[1]);
        add(barra[2]);
        add(barra[3]);
        
        add(diaIni);
        add(mesIni);
        add(anoIni);
        add(diaFim);
        add(mesFim);
        add(anoFim);
        
        add(conta);
        add(dias);
        add(dataFim);
        add(dataIni);
        add(sairCriar);
       // add(inicio);
       // add(fim);
        
        sairCriar.addActionListener(this);
        conta.addActionListener(this);
           
        setLayout(null);    
        setTitle("Estatísticas");
        setSize(400,350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    
    public void actionPerformed(ActionEvent event){
        Estacionamento est = Estacionamento.getInstance();
        Calendar calendar = Calendar.getInstance();
        Date data1 = calendar.getTime();
        Date data2 = calendar.getTime();
        if(event.getSource() == sairCriar){
            setVisible(false);
            try {
                FrameFrontEnd next = new FrameFrontEnd();
            } catch (IOException ex) {
                Logger.getLogger(FrameContabilidade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == conta){
            try{
            data1.setDate(Integer.parseInt(diaIni.getText()));
            data1.setMonth(Integer.parseInt(mesIni.getText()) - 1);
            data1.setYear(Integer.parseInt(anoIni.getText()) - 1900);
            
            data2.setDate(Integer.parseInt(diaFim.getText()));
            data2.setMonth(Integer.parseInt(mesFim.getText()) - 1);
            data2.setYear(Integer.parseInt(anoFim.getText()) - 1900);   
            
                       LucroDiario l;
            
                        try {
                            l = est.contabilizar(data1,data2);
                            JOptionPane.showMessageDialog(null, l.imprime(), "Mensagem", JOptionPane.PLAIN_MESSAGE);
                        } 
                        catch (InvalidDateException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensagem", JOptionPane.PLAIN_MESSAGE);
                        }
            
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Erro",JOptionPane.PLAIN_MESSAGE);
            }
            



        }
    }
}
