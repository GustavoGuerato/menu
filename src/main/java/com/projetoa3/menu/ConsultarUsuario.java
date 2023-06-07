package com.projetoa3.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarUsuario extends JFrame {
    private JTextField nomeField;
    private JTextField senhaField;
    private JTextField cpfField;
    private JTextField enderecoField;
    private JTextField profissaoField;

    public ConsultarUsuario() {
        super("Consultar Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        JLabel senhaLabel = new JLabel("Senha:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel enderecoLabel = new JLabel("Endereço:");
        JLabel profissaoLabel = new JLabel("Profissão:");

        nomeField = new JTextField();
        senhaField = new JTextField();
        cpfField = new JTextField();
        enderecoField = new JTextField();
        profissaoField = new JTextField();

        JButton consultarButton = new JButton("Consultar");
        JButton limparButton = new JButton("Limpar");

        consultarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String senha = senhaField.getText();
                String cpf = cpfField.getText();
                String endereco = enderecoField.getText();
                String profissao = profissaoField.getText();

                // Realizar a consulta do usuário no banco de dados em uma thread separada
                Thread consultaThread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            // Configurar a conexão com o banco de dados MySQL
                            String url = "jdbc:mysql://127.0.0.1:3306/sistemadb";
                            String username = "root";
                            String password = "gg08142325";
                            Connection conn = DriverManager.getConnection(url, username, password);
                            Statement stmt = conn.createStatement();
                            String query = "SELECT * FROM usuarios WHERE nome = '" + nome + "' AND senha = '" + senha + "' AND cpf = '" + cpf + "' AND endereco = '" + endereco + "' AND profissao = '" + profissao + "'";
                            ResultSet rs = stmt.executeQuery(query);

                            if (rs.next()) {
                                // Usuário encontrado
                                String resultado = "Consulta do usuário:\nNome: " + rs.getString("nome") + "\nSenha: " + rs.getString("senha") + "\nCPF: " + rs.getString("cpf") + "\nEndereço: " + rs.getString("endereco") + "\nProfissão: " + rs.getString("profissao");
                                JOptionPane.showMessageDialog(null, resultado);
                            } else {
                                // Usuário não encontrado
                                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
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
                senhaField.setText("");
                cpfField.setText("");
                enderecoField.setText("");
                profissaoField.setText("");
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
        panel.add(consultarButton);
        panel.add(limparButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConsultarUsuario();
            }
        });
    }
}
