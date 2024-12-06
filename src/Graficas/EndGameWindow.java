// src/Graficas/EndGameWindow.java
package Graficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameWindow extends JFrame {
    private Juego juego;

    public EndGameWindow(Juego juego) {
        this.juego = juego;
        setTitle("Fin del Juego");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY); // Set background color

        JLabel messageLabel = new JLabel("Intento terminado. ¿Quieres intentar de nuevo?", SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE); // Set text color
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); // Set font
        add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY); // Set background color
        buttonPanel.setLayout(new FlowLayout());

        JButton retryButton = new JButton("Intentar de nuevo");
        styleButton(retryButton);
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juego.resetGame();
                dispose();
            }
        });
        buttonPanel.add(retryButton);

        JButton exitButton = new JButton("Salir");
        styleButton(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setBackground(Color.GRAY); // Set button background color
        button.setForeground(Color.WHITE); // Set button text color
        button.setFocusPainted(false); // Remove focus border
    }
}