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

public class AlterarAluno extends JFrame {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField raField;
    private JTextField campusField;
    private JTextField cursoField;
    private JPasswordField senhaField;

    public AlterarAluno() {
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
        JLabel raLabel = new JLabel("RA:");
        JLabel campusLabel = new JLabel("Campus:");
        JLabel cursoLabel = new JLabel("Curso:");
        JLabel senhaLabel = new JLabel("Nova Senha:");

        cpfField = new JTextField(15);
        nomeField = new JTextField(15);
        emailField = new JTextField(15);
        raField = new JTextField(15);
        campusField = new JTextField(15);
        cursoField = new JTextField(15);
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
        panel.add(raLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(raField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(campusLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(campusField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(cursoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(cursoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(senhaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(senhaField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(alterarButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        panel.add(limparButton, gbc);

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();

                // Buscar aluno no banco de dados
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistemadb", "root", "gg08142325");
                    String query = "SELECT nome, email, ra, campus, curso FROM alunos WHERE cpf = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, cpf);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        String email = rs.getString("email");
                        String ra = rs.getString("ra");
                        String campus = rs.getString("campus");
                        String curso = rs.getString("curso");

                        nomeField.setText(nome);
                        emailField.setText(email);
                        raField.setText(ra);
                        campusField.setText(campus);
                        cursoField.setText(curso);
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
                String ra = raField.getText();
                String campus = campusField.getText();
                String curso = cursoField.getText();
                String novaSenha = new String(senhaField.getPassword());

                // Alterar aluno no banco de dados
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemadb", "root", "gg08142325");
                    String query;
                    PreparedStatement stmt;

                    if (!novaSenha.isEmpty()) {
                        query = "UPDATE alunos SET nome = ?, email = ?, ra = ?, campus = ?, curso = ?, senha = ? WHERE cpf = ?";
                        stmt = conn.prepareStatement(query);
                        stmt.setString(6, novaSenha);
                        stmt.setString(7, cpf);
                    } else {
                        query = "UPDATE alunos SET nome = ?, email = ?, ra = ?, campus = ?, curso = ? WHERE cpf = ?";
                        stmt = conn.prepareStatement(query);
                        stmt.setString(6, cpf);
                    }

                    stmt.setString(1, nome);
                    stmt.setString(2, email);
                    stmt.setString(3, ra);
                    stmt.setString(4, campus);
                    stmt.setString(5, curso);

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
        raField.setText("");
        campusField.setText("");
        cursoField.setText("");
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
