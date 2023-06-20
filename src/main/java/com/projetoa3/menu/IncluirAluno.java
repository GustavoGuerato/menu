package com.projetoa3.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncluirAluno extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField raField;
    private JTextField campusField; // Campo de campus
    private JTextField cursoField; // Campo de curso
    private JPasswordField senhaField;

    public IncluirAluno() {
        super("Incluir Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350); // Ajustar a altura para acomodar os campos adicionais
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 10, 10)); // Ajustar para acomodar os campos adicionais
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel raLabel = new JLabel("RA:");
        raField = new JTextField();
        JLabel campusLabel = new JLabel("Campus:"); // Rótulo para o campo de campus
        campusField = new JTextField(); // Campo de campus
        JLabel cursoLabel = new JLabel("Curso:"); // Rótulo para o campo de curso
        cursoField = new JTextField(); // Campo de curso
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        JButton incluirButton = new JButton("Incluir");
        JButton limparButton = new JButton("Limpar");

        incluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String ra = raField.getText();
                String campus = campusField.getText(); // Obter o campus digitado
                String curso = cursoField.getText(); // Obter o curso digitado
                String senha = new String(senhaField.getPassword());

                // Incluir aluno no banco de dados
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistemadb", "root", "gg08142325");
                    String query = "INSERT INTO alunos (nome, cpf, email, ra, campus, curso, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, nome);
                    stmt.setString(2, cpf);
                    stmt.setString(3, email);
                    stmt.setString(4, ra);
                    stmt.setString(5, campus);
                    stmt.setString(6, curso);
                    stmt.setString(7, senha);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Aluno incluído com sucesso!");
                        limparCampos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao incluir aluno.");
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
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(raLabel);
        panel.add(raField);
        panel.add(campusLabel); // Adicionar o rótulo do campo de campus
        panel.add(campusField); // Adicionar o campo de campus
        panel.add(cursoLabel); // Adicionar o rótulo do campo de curso
        panel.add(cursoField); // Adicionar o campo de curso
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(incluirButton);
        panel.add(limparButton);

        add(panel);
        setVisible(true);
    }

    private void limparCampos() {
        nomeField.setText("");
        cpfField.setText("");
        emailField.setText("");
        raField.setText("");
        campusField.setText(""); // Limpar o campo de campus
        cursoField.setText(""); // Limpar o campo de curso
        senhaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IncluirAluno();
            }
        });
    }
}
