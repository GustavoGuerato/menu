package com.projetoa3.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarAluno extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField raField;

    public ConsultarAluno() {
        super("Consultar Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel raLabel = new JLabel("RA:");
        raField = new JTextField();

        JButton consultarButton = new JButton("Consultar");
        JButton limparButton = new JButton("Limpar");

        consultarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String ra = raField.getText();

                // Realizar a consulta do aluno no banco de dados em uma thread separada
                Thread consultaThread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistemadb", "root", "gg08142325");
                            Statement stmt = conn.createStatement();
                            String query = "SELECT * FROM alunos WHERE nome = '" + nome + "' AND cpf = '" + cpf + "' AND email = '" + email + "' AND ra = '" + ra + "'";
                            ResultSet rs = stmt.executeQuery(query);

                            if (rs.next()) {
                                // Aluno encontrado
                                String resultado = "Consulta do aluno:\nNome: " + rs.getString("nome") + "\nCPF: " + rs.getString("cpf") + "\nEmail: " + rs.getString("email") + "\nRA: " + rs.getString("ra");
                                JOptionPane.showMessageDialog(null, resultado);
                            } else {
                                // Aluno não encontrado
                                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                            }

                            rs.close();
                            stmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                consultaThread.start();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nomeField.setText("");
                cpfField.setText("");
                emailField.setText("");
                raField.setText("");
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
        panel.add(consultarButton);
        panel.add(limparButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConsultarAluno();
            }
        });
    }
}
