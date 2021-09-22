package com.company;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main implements NativeKeyListener {
    static volatile ImageIcon gif = new ImageIcon("src/resources/rick.gif");
    static volatile int count = 0;
    static volatile boolean blocked = false;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (Exception e) {
            System.out.println("Oops");
        }
        LogManager.getLogManager().reset();

        new Rickrick();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/resources/rickroll_sound1.mp3");
            Player player = new Player(fileInputStream);
            while (true)
                player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (NativeKeyEvent.VC_ALT == nativeKeyEvent.getKeyCode()) {
            System.exit(69);
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
