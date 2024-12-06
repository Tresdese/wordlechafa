// src/Graficas/Juego.java
package Graficas;

import extras.EstadoLetra;
import funciones.ValidarObjeto;
import operacionesbd.PalabrasBD;
import funciones.Jugador;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Juego extends JFrame {
    private static final int filas = 6;
    private static final int columnas = 5;
    private Jugador jugador;
    private JTextField[][] guessFields;
    private JButton[] keyboardButtons;
    private String wordToGuess;
    private int currentRow = 0;
    private int currentCol = 0;

    public Juego(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.jugador = new Jugador(0, 0, 0, 0, 0, 0.0f);
        System.out.println("Palabra a adivinar: " + this.wordToGuess); // Print the word to guess
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Wordle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800); // Tamaño ajustado de la ventana
        setResizable(false); // Deshabilita el redimensionamiento
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY); // Establecer fondo gris oscuro

        // Centrar la ventana
        setLocationRelativeTo(null);

        // Panel para los intentos
        JPanel guessPanel = new JPanel(new GridLayout(filas, columnas, 5, 5)); // Espaciado entre celdas
        guessPanel.setBackground(Color.DARK_GRAY); // Fondo gris oscuro
        guessPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margen alrededor del panel

        guessFields = new JTextField[filas][columnas];

        for (int row = 0; row < filas; row++) {
            for (int col = 0; col < columnas; col++) {
                guessFields[row][col] = new JTextField();
                guessFields[row][col].setHorizontalAlignment(JTextField.CENTER);
                guessFields[row][col].setFont(new Font("SansSerif", Font.BOLD, 24));
                guessFields[row][col].setEditable(false);
                guessFields[row][col].setBackground(Color.BLACK);
                guessFields[row][col].setForeground(Color.WHITE);
                guessFields[row][col].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Borde gris
                guessPanel.add(guessFields[row][col]);
            }
        }

        // Panel para el teclado virtual
        JPanel keyboardPanel = new JPanel(new GridLayout(3, 10, 5, 5)); // Espaciado entre botones
        keyboardPanel.setBackground(Color.DARK_GRAY); // Fondo gris oscuro
        keyboardPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margen alrededor del panel

        String[] keys = {
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ",
                "Z", "X", "C", "V", "B", "N", "M", "Enter"
        };
        keyboardButtons = new JButton[keys.length];

        for (int i = 0; i < keys.length; i++) {
            keyboardButtons[i] = new JButton(keys[i]);
            keyboardButtons[i].setFont(new Font("SansSerif", Font.BOLD, 20));
            keyboardButtons[i].setBackground(Color.GRAY); // Color gris oscuro para las teclas
            keyboardButtons[i].setForeground(Color.WHITE); // Color de texto blanco para las teclas
            keyboardButtons[i].setFocusPainted(false); // Eliminar el borde de enfoque
            keyboardButtons[i].addActionListener(new KeyboardButtonListener());
            keyboardPanel.add(keyboardButtons[i]);
        }

        add(guessPanel, BorderLayout.CENTER);
        add(keyboardPanel, BorderLayout.SOUTH);

        // KeyListener para capturar la entrada desde el teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isLetter(keyChar)) {
                    keyChar = Character.toUpperCase(keyChar); // Convertir a mayúscula
                    if (currentCol < columnas) {
                        guessFields[currentRow][currentCol].setText(String.valueOf(keyChar));
                        currentCol++;
                    }
                } else if (keyChar == KeyEvent.VK_ENTER) {
                    handleEnterKey();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (currentCol > 0) {
                        currentCol--;
                        guessFields[currentRow][currentCol].setText("");
                    }
                }
            }
        });

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);

        // Request focus on the JFrame
        requestFocusInWindow();
    }

    private void handleEnterKey() {
        if (currentCol == columnas) {
            StringBuilder guess = new StringBuilder();
            for (int col = 0; col < columnas; col++) {
                guess.append(guessFields[currentRow][col].getText());
            }
            if (guess.length() == columnas) {
                System.out.println("Word formed: " + guess.toString()); // Print the formed word
                checkGuess(guess.toString());
                currentRow++;
                currentCol = 0;

                if (currentRow == filas) {
                    showEndWindow();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a 5-letter word.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void checkGuess(String guess) {
        System.out.println("Word to guess: " + wordToGuess); // Print the word to guess
        System.out.println("Word formed: " + guess); // Print the formed word

        List<EstadoLetra> estados = ValidarObjeto.validarLetra(wordToGuess, guess);
        boolean isCorrect = true;

        for (int col = 0; col < guess.length(); col++) {
            EstadoLetra estado = estados.get(col);
            if (estado == EstadoLetra.correcta) {
                guessFields[currentRow][col].setBackground(Color.GREEN);
            } else if (estado == EstadoLetra.posIncorrecta) {
                guessFields[currentRow][col].setBackground(Color.ORANGE);
                isCorrect = false;
            } else {
                guessFields[currentRow][col].setBackground(Color.GRAY);
                isCorrect = false;
            }
        }

        if (isCorrect) {
            jugador.registrarVictoria();
            showCongratulationsWindow();
        } else if (currentRow == filas - 1) {
            jugador.registrarDerrota();
            showEndWindow();
        }
    }

    private void showCongratulationsWindow() {
        // Create a summary of the guesses
        StringBuilder summary = new StringBuilder("<html>Resumen de intentos:<br>");
        for (int row = 0; row < currentRow; row++) {
            summary.append("Intento ").append(row + 1).append(": ");
            for (int col = 0; col < columnas; col++) {
                summary.append(guessFields[row][col].getText());
            }
            summary.append("<br>");
        }
        summary.append("</html>");

        summary.append("<br>Racha actual: ").append(jugador.getRacha());
        summary.append("<br>Racha máxima: ").append(jugador.getRachaMaxima());
        summary.append("<br>Racha perdedora: ").append(jugador.getRachaPerdedora());
        summary.append("<br>Total jugadas: ").append(jugador.getTotalJugadas());
        summary.append("<br>Win rate: ").append(jugador.getWinRate()).append("%");

        new CongratulationsWindow(this, summary.toString()).setVisible(true);
    }

    private void showEndWindow() {
        JOptionPane.showMessageDialog(this, "Game Over! You've used all attempts.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resetGame() {
        currentRow = 0;
        currentCol = 0;

        List<String> palabrasMayusculas;

        for (String palabra : PalabrasBD.consultarPalabra()) {
            palabrasMayusculas.add(palabra.toUpperCase());
        }

        for (int row = 0; row < filas; row++) {
            for (int col = 0; col < columnas; col++) {
                guessFields[row][col].setText("");
                guessFields[row][col].setBackground(Color.BLACK);
            }
        }
        this.wordToGuess = palabrasMayusculas;
        if (this.wordToGuess == null) {
            throw new RuntimeException("No se pudo obtener una palabra de la base de datos.");
        }
    }

    private class KeyboardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String keyPressed = source.getText();

            if (keyPressed.equals("Enter")) {
                handleEnterKey();
            } else {
                if (currentCol < columnas) {
                    guessFields[currentRow][currentCol].setText(keyPressed);
                    currentCol++;
                }
            }
        }
    }
}