

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp extends JFrame {

    private static final long serialVersionUID = 2451829341034438685L;

    public static JButton inputButton = new JButton("Guardar");
    public static JTextArea editTextArea = new JTextArea("Ingresar Nombre");
    public static JTextArea uneditTextArea = new JTextArea();

    public PopUp(String title) {
        //SET LAYOUT MANAGER (How it arranges components)
        setLayout(new BorderLayout());
//////CREATE SWING COMPONENTS////////////
//OUTPUT TEXT AREA
        uneditTextArea.setEditable(false);

//INPUT TEXT AREA
        editTextArea.setBackground(Color.BLUE);
        editTextArea.setForeground(Color.WHITE);

//SET CONTENT PANE
        Container c = getContentPane();

//ADD COMPONENTS TO CONTENT PANE
        c.add(uneditTextArea, BorderLayout.CENTER);
        c.add(editTextArea, BorderLayout.SOUTH);
        c.add(inputButton, BorderLayout.WEST);
       PopUp.inputButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String str = editTextArea.getText();
                editTextArea.setText(" ");
                System.out.println(str);
            }
        });
    }
}

