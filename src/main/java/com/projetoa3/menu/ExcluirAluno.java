package com.projetoa3.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExcluirAluno extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField senhaField;
    private JTextField cursoField;
    private JTextField raField;
    private JTextField emailField;
    private JTextField campusField;

    public ExcluirAluno() {
        super("Excluir Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JTextField();
        JLabel cursoLabel = new JLabel("Curso:");
        cursoField = new JTextField();
        JLabel raLabel = new JLabel("RA:");
        raField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel campusLabel = new JLabel("Campus:");
        campusField = new JTextField();

        JButton excluirButton = new JButton("Excluir");

        excluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String senha = senhaField.getText();
                String curso = cursoField.getText();
                String ra = raField.getText();
                String email = emailField.getText();
                String campus = campusField.getText();

                // Excluir aluno do banco de dados
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistemadb", "root", "gg08142325");
                    String query = "DELETE FROM alunos WHERE nome = ? AND cpf = ? AND senha = ? AND curso = ? AND ra = ? AND email = ? AND campus = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, nome);
                    stmt.setString(2, cpf);
                    stmt.setString(3, senha);
                    stmt.setString(4, curso);
                    stmt.setString(5, ra);
                    stmt.setString(6, email);
                    stmt.setString(7, campus);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado com os dados fornecidos.");
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
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(cursoLabel);
        panel.add(cursoField);
        panel.add(raLabel);
        panel.add(raField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(campusLabel);
        panel.add(campusField);
        panel.add(excluirButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExcluirAluno();
            }
        });
    }
}
