package com.projetoa3.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        super("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Usuário:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Entrar");
        JButton exitButton = new JButton("Sair");
        JButton clearButton = new JButton("Limpar");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Credenciais do banco de dados
                String dbUrl = "jdbc:mysql://127.0.0.1:3306/sistemadb";
                String dbUser = "root";
                String dbPassword = "gg08142325";

                // Estabelece a conexão com o banco de dados
                try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                    // Cria uma declaração SQL
                    Statement statement = connection.createStatement();

                    // Executa uma consulta para verificar as credenciais
                    String query = "SELECT * FROM alunos WHERE nome = '" + username + "' AND senha = '" + password + "'";
                    ResultSet resultSet = statement.executeQuery(query);

                    // Verifica se há resultados
                    if (resultSet.next()) {
                        dispose(); // Fecha a tela de login

                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                new Menu();
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Login falhou. Verifique suas credenciais.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(exitButton);
        panel.add(clearButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm();
            }
        });
    }
}
