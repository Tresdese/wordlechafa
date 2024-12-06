// src/Graficas/Juego.java
package Graficas;

import extras.EstadoLetra;
import funciones.Comodin;
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
import java.util.Random;

import extras.Palabra;
import java.util.ArrayList;

public class Juego extends JFrame {
    private static final int filas = 6;
    private static final int columnas = 5;
    private Jugador jugador;
    private JTextField[][] guessFields;
    private JButton[] keyboardButtons;
    private JButton iniciarSesionButton;
    private JButton comodinButton;
    private JLabel rachaPerdidaLabel;
    private String wordToGuess;
    private int currentRow = 0;
    private int currentCol = 0;

    public Juego(String wordToGuess) {
        // Configuración básica de la ventana
        setTitle("Wordle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY);
        setLocationRelativeTo(null);

        // Inicialización de datos
        this.wordToGuess = wordToGuess;
        this.jugador = new Jugador("", 0, 0, 0, 0, 0, 0.0f);
        System.out.println("Palabra a adivinar: " + this.wordToGuess);

        // Crear y añadir componentes
        initializeUI();
        crearPanelSuperior();
        crearBotonComodin();

        // Hacer visible la ventana
        setVisible(true);
    }

    private void crearPanelSuperior() {
        // Crear panel superior
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configurar botón de login
        iniciarSesionButton = new JButton("Iniciar Sesión");
        iniciarSesionButton.setMaximumSize(new Dimension(120, 30));
        iniciarSesionButton.setPreferredSize(new Dimension(120, 30));
        iniciarSesionButton.setBackground(Color.WHITE);
        iniciarSesionButton.setForeground(Color.BLACK);
        iniciarSesionButton.addActionListener(e -> new IniciarSesionWindow().setVisible(true));
    
        // Crear etiqueta para racha perdida
        rachaPerdidaLabel = new JLabel("Racha perdida: " + jugador.getRachaPerdedora());
        rachaPerdidaLabel.setForeground(Color.WHITE);
        rachaPerdidaLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        rachaPerdidaLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    
        actualizarRachaPerdida();
    
        // Añadir componentes al panel
        topPanel.add(iniciarSesionButton);
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        topPanel.add(rachaPerdidaLabel);
        
        // Añadir panel al contenedor principal
        add(topPanel, BorderLayout.NORTH);
        validate();
    }

    private void crearBotonComodin() {
        // Configurar botón comodín
        comodinButton = new JButton("Usar Comodín");
        comodinButton.setMaximumSize(new Dimension(120, 30));
        comodinButton.setPreferredSize(new Dimension(120, 30));
        comodinButton.setBackground(Color.WHITE);
        comodinButton.setForeground(Color.BLACK);
        
        // Hacer visible el botón cuando la racha perdedora sea 3 o más
        comodinButton.setVisible(jugador.getRachaPerdedora() >= 1);
        
        comodinButton.addActionListener(e -> {
            if (jugador.getRachaPerdedora() >= 1) {
                Random random = new Random();
                int posicionAleatoria = random.nextInt(wordToGuess.length());
                char letraAyuda = wordToGuess.charAt(posicionAleatoria);
                
                JOptionPane.showMessageDialog(this,
                    "Una letra de la palabra es: " + letraAyuda + 
                    "\nEstá en la posición: " + (posicionAleatoria + 1),
                    "Comodín Usado",
                    JOptionPane.INFORMATION_MESSAGE);
                
                comodinButton.setVisible(false);
                jugador.setRachaPerdedora(0);
                actualizarRachaPerdida();
            }
        });
    
        // Obtener el panel superior y añadir el botón
        JPanel topPanel = (JPanel) getContentPane().getComponent(0);
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        topPanel.add(comodinButton);
        
        // Refrescar la UI
        topPanel.revalidate();
        topPanel.repaint();
    }

    private void actualizarRachaPerdida() {
        if (jugador.getRachaPerdedora() >= 1) {
            rachaPerdidaLabel.setText("¡Comodín disponible! Racha: " + jugador.getRachaPerdedora());
            rachaPerdidaLabel.setForeground(Color.YELLOW);
        } else {
            rachaPerdidaLabel.setText("Racha perdida: " + jugador.getRachaPerdedora());
            rachaPerdidaLabel.setForeground(Color.WHITE);
        }
    }

    private void initializeUI() {
        setTitle("Wordle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800); // Adjusted size of the window
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        setLocationRelativeTo(null);

        // Panel contenedor principal con tamaño fijo
        JPanel mainGuessContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainGuessContainer.setBackground(Color.DARK_GRAY);

        // Panel de cuadrícula con tamaño fijo
        JPanel guessPanel = new JPanel(new GridLayout(filas, columnas, 5, 5));
        guessPanel.setBackground(Color.DARK_GRAY);
        guessPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        guessPanel.setPreferredSize(new Dimension(300, 360)); // Tamaño fijo para el panel de cuadrícula

        guessFields = new JTextField[filas][columnas];

        for (int row = 0; row < filas; row++) {
            for (int col = 0; col < columnas; col++) {
                guessFields[row][col] = new JTextField();
                guessFields[row][col].setHorizontalAlignment(JTextField.CENTER);
                guessFields[row][col].setFont(new Font("SansSerif", Font.BOLD, 24));
                guessFields[row][col].setEditable(false);
                guessFields[row][col].setBackground(Color.BLACK);
                guessFields[row][col].setForeground(Color.WHITE);
                guessFields[row][col].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                
                // Establecer tamaño fijo para cada campo
                guessFields[row][col].setPreferredSize(new Dimension(50, 50));
                guessFields[row][col].setMinimumSize(new Dimension(50, 50));
                guessFields[row][col].setMaximumSize(new Dimension(50, 50));
                
                guessPanel.add(guessFields[row][col]);
            }
        }

        mainGuessContainer.add(guessPanel);
        add(mainGuessContainer, BorderLayout.CENTER);

        JPanel keyboardPanel = new JPanel(new GridLayout(3, 10, 5, 5));
        keyboardPanel.setBackground(Color.DARK_GRAY);
        keyboardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] keys = {
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ",
                "Z", "X", "C", "V", "B", "N", "M", "Enter"
        };
        keyboardButtons = new JButton[keys.length];

        for (int i = 0; i < keys.length; i++) {
            keyboardButtons[i] = new JButton(keys[i]);
            keyboardButtons[i].setFont(new Font("SansSerif", Font.BOLD, 20));
            keyboardButtons[i].setBackground(Color.GRAY);
            keyboardButtons[i].setForeground(Color.WHITE);
            keyboardButtons[i].setFocusPainted(false);
            keyboardButtons[i].addActionListener(new KeyboardButtonListener());
            keyboardPanel.add(keyboardButtons[i]);
        }

        add(guessPanel, BorderLayout.CENTER);
        add(keyboardPanel, BorderLayout.SOUTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isLetter(keyChar)) {
                    keyChar = Character.toUpperCase(keyChar);
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

        requestFocusInWindow();
    }

    private void handleEnterKey() {
        if (currentCol == columnas) {
            StringBuilder guess = new StringBuilder();
            for (int col = 0; col < columnas; col++) {
                guess.append(guessFields[currentRow][col].getText());
            }
            if (guess.length() == columnas) {
                System.out.println("Word formed: " + guess.toString());
                checkGuess(guess.toString());
                currentRow++;
                currentCol = 0;
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a 5-letter word.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void checkGuess(String guess) {
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
        actualizarRachaPerdida();

        new EndGameWindow(this).setVisible(true);
    }

    public void resetGame() {
        currentRow = 0;
        currentCol = 0;

        List<Palabra> palabras = PalabrasBD.consultarPalabra();
        List<Palabra> palabrasDeCincoLetras = ValidarObjeto.validarPalabra(palabras);
        List<String> palabrasMayusculas = new ArrayList<>();

        for (Palabra palabra : palabrasDeCincoLetras) {
            palabrasMayusculas.add(palabra.getPalabra().toUpperCase());
        }

        for (int row = 0; row < filas; row++) {
            for (int col = 0; col < columnas; col++) {
                guessFields[row][col].setText("");
                guessFields[row][col].setBackground(Color.BLACK);
            }
        }

        if (!palabrasMayusculas.isEmpty()) {
            this.wordToGuess = palabrasMayusculas.get(0);
        } else {
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