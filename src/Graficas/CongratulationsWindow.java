// src/Graficas/CongratulationsWindow.java
package Graficas;

import db.PalabrasBD;

import javax.swing.*;
import java.awt.*;

public class CongratulationsWindow extends JDialog {
    public CongratulationsWindow(JFrame parent, String summary) {
        super(parent, "Congratulations", true);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY); // Set background color

        // Create the message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBackground(Color.DARK_GRAY); // Set background color
        JLabel messageLabel = new JLabel("Felicidades, has adivinado la palabra!");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE); // Set text color
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); // Set font
        messagePanel.add(messageLabel, BorderLayout.NORTH);
        JLabel summaryLabel = new JLabel(summary);
        summaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        summaryLabel.setForeground(Color.WHITE); // Set text color
        summaryLabel.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Set font
        messagePanel.add(summaryLabel, BorderLayout.CENTER);

        // Create the buttons
        JButton exitButton = new JButton("Salir");
        styleButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        JButton playAgainButton = new JButton("Jugar de nuevo");
        styleButton(playAgainButton);
        playAgainButton.addActionListener(e -> {
            parent.dispose();
<<<<<<< HEAD
            new Juego(PalabrasBD.consultarPalabra().toUpperCase());
=======
            String newWord = GestionDB.consultarPalabra().toUpperCase();
            new Juego(newWord);
>>>>>>> 4739cea3326e2cb20e0ab8699626a9e50fa96af6
            dispose();
        });

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY); // Set background color
        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitButton);

        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setBackground(Color.GRAY); // Set button background color
        button.setForeground(Color.WHITE); // Set button text color
        button.setFocusPainted(false); // Remove focus border
    }
}