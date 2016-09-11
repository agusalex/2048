package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class Window extends Canvas {

    /**
     *
     */


    private static final long serialVersionUID = -4166964448327045634L;


    public Window(Game game) {

        JFrame frame = new JFrame("2048!");

        frame.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        frame.setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        frame.setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  //empieza en el medio

        frame.add(game);


        try {
            frame.setIconImage(ImageIO.read(new File("img/icon.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        Frame f=new Frame("Record");




        game.start();


    }


}