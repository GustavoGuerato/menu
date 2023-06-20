package com.projetoa3.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncluirUsuario extends JFrame {
    private JTextField nomeField;
    private JPasswordField senhaField;
    private JTextField cpfField;
    private JTextField enderecoField;
    private JTextField profissaoField;
    private JTextField emailField;

    public IncluirUsuario() {
        super("Incluir Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();
        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoField = new JTextField();
        JLabel profissaoLabel = new JLabel("Profissão:");
        profissaoField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JButton incluirButton = new JButton("Incluir");
        JButton limparButton = new JButton("Limpar");

        incluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String senha = new String(senhaField.getPassword());
                String cpf = cpfField.getText();
                String endereco = enderecoField.getText();
                String profissao = profissaoField.getText();
                String email = emailField.getText();

                // Incluir usuário no banco de dados
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistemadb", "root", "gg08142325");
                    String query = "INSERT INTO usuarios (nome, senha, cpf, endereco, profissao, email) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, nome);
                    stmt.setString(2, senha);
                    stmt.setString(3, cpf);
                    stmt.setString(4, endereco);
                    stmt.setString(5, profissao);
                    stmt.setString(6, email);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Usuário incluído com sucesso!");
                        limparCampos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao incluir usuário.");
                    }

                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(enderecoLabel);
        panel.add(enderecoField);
        panel.add(profissaoLabel);
        panel.add(profissaoField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(incluirButton);
        panel.add(limparButton);

        add(panel);
        setVisible(true);
    }

    private void limparCampos() {
        nomeField.setText("");
        senhaField.setText("");
        cpfField.setText("");
        enderecoField.setText("");
        profissaoField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IncluirUsuario();
            }
        });
    }
}
