package Graficas;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Juego extends JFrame {
    private static final int filas = 6;
    private static final int columnas = 5;

    private JTextField[][] guessFields;
    private JButton[] keyboardButtons;
    private String wordToGuess;
    private int currentRow = 0;
    private int currentCol = 0;

    public Juego(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Wordle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800); // Tamaño ajustado de la ventana
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
                guessFields[row][col].setFont(new Font("SansSerif", Font.BOLD, 20));
                guessFields[row][col].setEditable(false);
                guessFields[row][col].setBackground(Color.BLACK);
                guessFields[row][col].setForeground(Color.WHITE);
                guessFields[row][col].setBorder(new EmptyBorder(5, 5, 5, 5)); // Separación leve dentro de las celdas
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
            keyboardButtons[i].addActionListener(new KeyboardButtonListener());
            keyboardPanel.add(keyboardButtons[i]);
        }

        add(guessPanel, BorderLayout.CENTER);
        add(keyboardPanel, BorderLayout.SOUTH);

        //KeyListener para capturar la entrada desde el teclado
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
    }

    private void handleEnterKey() {
        if (currentCol == columnas) {
            StringBuilder guess = new StringBuilder();
            for (int col = 0; col < columnas; col++) {
                guess.append(guessFields[currentRow][col].getText());
            }
//            checkGuess(guess.toString());
            currentRow++;
            currentCol = 0;
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
