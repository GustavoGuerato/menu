package com.projetoa3.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlterarUsuario extends JFrame {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField enderecoField;
    private JTextField profissaoField;
    private JPasswordField senhaField;

    public AlterarUsuario() {
        super("Alterar Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel cpfLabel = new JLabel("CPF:");
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel enderecoLabel = new JLabel("Endereço:");
        JLabel profissaoLabel = new JLabel("Profissão:");
        JLabel senhaLabel = new JLabel("Nova Senha:");

        cpfField = new JTextField(15);
        nomeField = new JTextField(15);
        emailField = new JTextField(15);
        enderecoField = new JTextField(15);
        profissaoField = new JTextField(15);
        senhaField = new JPasswordField(15);

        JButton buscarButton = new JButton("Buscar");
        JButton alterarButton = new JButton("Alterar");
        JButton limparButton = new JButton("Limpar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cpfLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(cpfField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(buscarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nomeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(enderecoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(enderecoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(profissaoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(profissaoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(senhaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(senhaField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(alterarButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(limparButton, gbc);

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();

                // Buscar aluno no banco de dados
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistemadb", "root", "gg08142325");
                    String query = "SELECT nome, email, endereco, profissao FROM alunos WHERE cpf = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, cpf);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        String email = rs.getString("email");
                        String endereco = rs.getString("endereco");
                        String profissao = rs.getString("profissao");

                        nomeField.setText(nome);
                        emailField.setText(email);
                        enderecoField.setText(endereco);
                        profissaoField.setText(profissao);
                    } else {
                        JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                        limparCampos();
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        alterarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();
                String nome = nomeField.getText();
                String email = emailField.getText();
                String endereco = enderecoField.getText();
                String profissao = profissaoField.getText();
                String novaSenha = new String(senhaField.getPassword());

                // Alterar aluno no banco de dados
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemadb", "root", "gg08142325");
                    String query;
                    PreparedStatement stmt;

                    if (!novaSenha.isEmpty()) {
                        query = "UPDATE alunos SET nome = ?, email = ?, endereco = ?, profissao = ?, senha = ? WHERE cpf = ?";
                        stmt = conn.prepareStatement(query);
                        stmt.setString(5, novaSenha);
                        stmt.setString(6, cpf);
                    } else {
                        query = "UPDATE alunos SET nome = ?, email = ?, endereco = ?, profissao = ? WHERE cpf = ?";
                        stmt = conn.prepareStatement(query);
                        stmt.setString(5, cpf);
                    }

                    stmt.setString(1, nome);
                    stmt.setString(2, email);
                    stmt.setString(3, endereco);
                    stmt.setString(4, profissao);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!");
                        limparCampos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao alterar aluno.");
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

        add(panel);
        setVisible(true);
    }

    private void limparCampos() {
        cpfField.setText("");
        nomeField.setText("");
        emailField.setText("");
        enderecoField.setText("");
        profissaoField.setText("");
        senhaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AlterarAluno();
            }
        });
    }
}
