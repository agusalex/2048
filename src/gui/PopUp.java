

package gui;

import paquete.Jugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import static gui.Game.isRecord;
import static gui.Game.setRecord;

public class PopUp extends JFrame implements ActionListener{
    private JTextField textfield1;
    private JLabel label1;
    private JButton boton1;
    public PopUp() {
        setBounds(0,0,300,150);
        setTitle("2048 : Nuevo Record");
        setLocationRelativeTo(null);
        setLayout(null);
        label1=new JLabel("Nombre:");
        label1.setBounds(10,10,100,30);
        add(label1);
        textfield1=new JTextField("");
        textfield1.setBounds(120,10,150,20);
        add(textfield1);
        boton1=new JButton("Aceptar");
        boton1.setBounds(10,80,100,30);
        add(boton1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
           this.setIconImage(ImageIO.read(new File("img/icon.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        boton1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==boton1) {
           Game.Player.setName (textfield1.getText());

            if(Game.isRecord ()){
                Game.setRecord ();
            Game.isInRecord=true;}
            Game.Player=new Jugador ();
            Menu.SetShowRankings (true);
            setVisible(false);
            textfield1.setText ("");
        }
    }


}