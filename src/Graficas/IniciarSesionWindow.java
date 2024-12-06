// src/Graficas/IniciarSesionWindow.java
package Graficas;

import javax.swing.*;
import java.awt.*;

public class IniciarSesionWindow extends JFrame {
    public IniciarSesionWindow() {
        setTitle("Iniciar Sesión");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioTextField = new JTextField();

        panel.add(usuarioLabel);
        panel.add(usuarioTextField);

        add(panel);
    }
}