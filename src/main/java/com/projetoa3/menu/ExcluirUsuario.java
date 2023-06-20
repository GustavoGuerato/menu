package com.projetoa3.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExcluirUsuario extends JFrame {
    private JTextField nomeField;
    private JTextField senhaField;
    private JTextField cpfField;
    private JTextField enderecoField;
    private JTextField profissaoField;
    private JTextField emailField;

    public ExcluirUsuario() {
        super("Excluir Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JTextField();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();
        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoField = new JTextField();
        JLabel profissaoLabel = new JLabel("Profissão:");
        profissaoField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JButton excluirButton = new JButton("Excluir");

        excluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String senha = senhaField.getText();
                String cpf = cpfField.getText();
                String endereco = enderecoField.getText();
                String profissao = profissaoField.getText();
                String email = emailField.getText();

                // Excluir usuário do banco de dados
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistemadb", "root", "gg08142325");
                    String query = "DELETE FROM usuarios WHERE nome = ? AND senha = ? AND cpf = ? AND endereco = ? AND profissao = ? AND email = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, nome);
                    stmt.setString(2, senha);
                    stmt.setString(3, cpf);
                    stmt.setString(4, endereco);
                    stmt.setString(5, profissao);
                    stmt.setString(6, email);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado com os dados fornecidos.");
                    }

                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
        panel.add(excluirButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExcluirUsuario();
            }
        });
    }
}
