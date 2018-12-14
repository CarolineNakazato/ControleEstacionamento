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
import javax.swing.JOptionPane;

public class FrameVeiculos extends JFrame implements ActionListener {
    
    JButton entrada = new JButton("Entrada");
    JButton saida = new JButton("Saida");
    JButton sair = new JButton("Voltar");

public FrameVeiculos(){
    entrada.setBounds(125,20,150,70);
    saida.setBounds(125,100,150,70);
    sair.setBounds(125,180,150,70);

    add(entrada);
    add(saida);
    add(sair);
      
    entrada.addActionListener(this);
    saida.addActionListener(this);
    sair.addActionListener(this);
    
    setLayout(null);    
    setTitle("Veiculos");
    setSize(400,325);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
}

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource()== entrada){
            setVisible(false);
            FrameEntrada next = new FrameEntrada();}
        if(event.getSource()== sair){
            setVisible(false);
            try {
                FrameFrontEnd next = new FrameFrontEnd();
            } catch (IOException ex) {
                Logger.getLogger(FrameVeiculos.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        if(event.getSource()== saida){
            setVisible(false);
            FrameSaida next = new FrameSaida();}
    }
}

    
