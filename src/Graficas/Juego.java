// src/Graficas/Juego.java
package Graficas;

// Se importan las clases locales
import extras.EstadoLetra;
import funciones.Comodin;
import funciones.ValidarObjeto;
import operacionesbd.PalabrasBD;
import funciones.Jugador;
import funciones.Comodin;
import extras.Palabra;
import operacionesbd.JugadorBD;

// Se importan las clases de java
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Juego extends JFrame {
    private static final int filas = 6;
    private static final int columnas = 5;
    private Jugador jugador;
    private JugadorBD jbd;
    private JTextField[][] guessFields;
    private JButton[] keyboardButtons;
    private JButton comodinButton;
    private String wordToGuess;
    private int currentRow = 0;
    private int currentCol = 0;

    public Juego(String wordToGuess) {

        // Inicialización de la base de datos
        try {
            this.jbd = new JugadorBD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error connecting to database: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }

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
        this.jugador = new Jugador("", 0, 0, 0, 0, 0);
        System.out.println("Palabra a adivinar: " + this.wordToGuess);

        // Crear y añadir componentes
        initializeUI();
        crearPanelSuperior();

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para crear el panel superior de la ventana
    private void crearPanelSuperior() {
        // Crear panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(Color.DARK_GRAY);
        
        // Crear panel izquierdo
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(Color.DARK_GRAY);
        
        // Crear paneles y botones
        JPanel statsPanel = createStatsPanel(); // Panel de estadísticas
        JButton loginButton = createLoginButton(); // Botón de inicio de sesión
        JButton comodinBtn = createComodinButton(); // Botón de comodín

        // Añadir componentes al panel izquierdo
        leftPanel.add(statsPanel);
        leftPanel.add(comodinBtn);
        
        // Añadir componentes al panel superior
        panelSuperior.add(leftPanel, BorderLayout.WEST);
        panelSuperior.add(loginButton, BorderLayout.EAST);
        
        // Añadir panel superior a la ventana
        add(panelSuperior, BorderLayout.NORTH);
    }

    // Método para crear el botón de inicio de sesión
    private JButton createLoginButton() {
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(50, 50, 50));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        loginButton.addActionListener(e -> {
            try {
                if (jbd == null) {
                    throw new Exception("Database connection not initialized");
                }
                
                String username = JOptionPane.showInputDialog(this, 
                    "Ingrese su nombre de usuario:",
                    "Iniciar Sesión",
                    JOptionPane.PLAIN_MESSAGE);
                    
                if (username != null && !username.trim().isEmpty()) {
                    boolean usuarioExistente = false;
                    
                    // Buscar si el usuario existe
                    for (Jugador j : jbd.consultarJugador()) {
                        if (j.getNombreUsuario().equals(username)) {
                            jugador = j;
                            usuarioExistente = true;
                            break;
                        }
                    }
                    
                    // Si no existe, crear nuevo
                    if (!usuarioExistente) {
                        jbd.insertarJugador(username, 0, 0, 0);
                        jugador = new Jugador(username, 0, 0, 0, 0, 0);
                    }
                    
                    jugador.setNombreUsuario(username);
                    loginButton.setText("Usuario: " + username);
                    loginButton.setEnabled(false);
                    
                    JOptionPane.showMessageDialog(this,
                        "Sesión iniciada correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al procesar el login: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        return loginButton;
    }

    // Método para crear el botón de comodín
    private JButton createComodinButton() {
        comodinButton = new JButton("Usar Comodín");
        comodinButton.setForeground(Color.WHITE);
        comodinButton.setBackground(new Color(255, 193, 7)); // Color amarillo
        comodinButton.setFocusPainted(false);
        comodinButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        comodinButton.setVisible(false); // Inicialmente invisible
        
        comodinButton.addActionListener(e -> {
            Comodin comodin = new Comodin();
            String pista = String.valueOf(comodin.mostrarLetra(wordToGuess));
            JOptionPane.showMessageDialog(this, "Pista: " + pista, "Comodín", JOptionPane.INFORMATION_MESSAGE);
            comodinButton.setVisible(false);
            comodinButton.setEnabled(false);
        });
        
        return comodinButton;
    }

    // Método para verificar si se puede mostrar el botón de comodín
    public void checkComodinButton() {
        if (jugador.getRachaPerdedora() >= 3 && comodinButton != null) {
            comodinButton.setVisible(true);
            comodinButton.setEnabled(true);
        }
    }

    // Método para crear el panel de estadísticas
    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statsPanel.setBackground(Color.DARK_GRAY);
    
        JLabel lossLabel = new JLabel("Perdidas: " + jugador.getRachaPerdedora());
        JLabel streakLabel = new JLabel("Racha: " + jugador.getRacha());
    
        // Estilo para las labels
        lossLabel.setForeground(new Color(244, 67, 54)); // Rojo
        streakLabel.setForeground(Color.WHITE);
    
        // Añadir padding
        lossLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        streakLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        statsPanel.add(lossLabel);
        statsPanel.add(streakLabel);
    
        return statsPanel;
    }

    // Método para actualizar las estadísticas del jugador
    public void actualizarEstadisticas() {
        SwingUtilities.invokeLater(() -> {
            Container c = getContentPane();
            Component[] components = c.getComponents();
            for (Component comp : components) {
                if (comp instanceof JPanel) {
                    JPanel panel = (JPanel) comp;
                    updatePanelLabels(panel);
                }
            }
        });
    }

    private void actualizarDatosJugador() {

        int id = jugador.getId();
        String nombreUsuario = jugador.getNombreUsuario();
        int jugadas = jugador.getTotalJugadas();
        int ganadas = jugador.getGanadas();
        int perdidas = jugador.getRachaPerdedora();
        int racha = jugador.getRacha();

        try {
            if (jbd != null && jugador != null) {
                
                jbd.actualizarJugadorTotalJugadas(nombreUsuario, jugadas);
                jbd.actualizarJugadorTotalGanadas(nombreUsuario, ganadas);
                jbd.actualizarJugadorTotalPerdidas(nombreUsuario, perdidas);
                jbd.actualizarJugadorRachaMaxima(nombreUsuario, racha);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al actualizar datos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para actualizar las etiquetas del panel
    private void updatePanelLabels(JPanel panel) {
        for (Component innerComp : panel.getComponents()) {
            if (innerComp instanceof JPanel) {
                // Recursive check for nested panels
                updatePanelLabels((JPanel) innerComp);
            }
            if (innerComp instanceof JLabel) {
                JLabel label = (JLabel) innerComp;
                String labelText = label.getText();
                
                if (labelText.startsWith("Perdidas")) {
                    label.setText("Perdidas: " + jugador.getRachaPerdedora());
                } else if (labelText.startsWith("Racha")) {
                    label.setText("Racha: " + jugador.getRacha());
                }
            }
        }
    }

    // Método para inicializar la interfaz de usuario
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
        if (guess == null || guess.length() != wordToGuess.length()) {
            throw new IllegalArgumentException("Invalid guess length");
        }
    
        try {
            List<EstadoLetra> estados = ValidarObjeto.validarLetra(wordToGuess, guess);
            if (estados == null || estados.size() != guess.length()) {
                throw new IllegalStateException("Invalid validation result");
            }
    
            boolean isCorrect = true;
    
            for (int col = 0; col < guess.length(); col++) {
                EstadoLetra estado = estados.get(col);
                switch (estado) {
                    case correcta:
                        guessFields[currentRow][col].setBackground(Color.GREEN);
                        break;
                    case posIncorrecta:
                        guessFields[currentRow][col].setBackground(Color.ORANGE);
                        isCorrect = false;
                        break;
                    default:
                        guessFields[currentRow][col].setBackground(Color.GRAY);
                        isCorrect = false;
                        break;
                }
                guessFields[currentRow][col].setForeground(Color.WHITE);
            }
    
            if (isCorrect) {
                jugador.registrarVictoria();
                actualizarDatosJugador();
                actualizarEstadisticas();
                showCongratulationsWindow();
                checkComodinButton();
            } else if (currentRow == filas - 1) {
                jugador.registrarDerrota();
                actualizarDatosJugador();
                actualizarEstadisticas();
                showEndWindow();
                checkComodinButton();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error validating guess: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
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
        // actualizarRachaPerdida();

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