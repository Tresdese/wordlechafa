package Graficas;

import db.GestionDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CongratulationsWindow extends JDialog {
    public CongratulationsWindow(JFrame parent, String summary) {
        super(parent, "Congratulations", true);
        setLayout(new BorderLayout());

        // Create the message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel messageLabel = new JLabel("Felicidades, has adivinado la palabra!");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(messageLabel, BorderLayout.NORTH);
        JLabel summaryLabel = new JLabel(summary);
        summaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(summaryLabel, BorderLayout.CENTER);

        // Create the buttons
        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));
        JButton playAgainButton = new JButton("Jugar de nuevo");
        playAgainButton.addActionListener(e -> {
            parent.dispose();
            new Juego(GestionDB.consultarPalabra().toUpperCase());
            dispose();
        });

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitButton);

        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
}