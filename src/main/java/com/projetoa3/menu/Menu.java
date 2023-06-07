package com.projetoa3.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    public Menu() {
        super("Menu");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuUsuario = new JMenu("Usuário");

        JMenuItem itemConsultarUsuario = new JMenuItem("Consultar");
        JMenuItem itemIncluirUsuario = new JMenuItem("Incluir");
        JMenuItem itemAlterarUsuario = new JMenuItem("Alterar");
        JMenuItem itemExcluirUsuario = new JMenuItem("Excluir");

        menuUsuario.add(itemConsultarUsuario);
        menuUsuario.add(itemIncluirUsuario);
        menuUsuario.add(itemAlterarUsuario);
        menuUsuario.add(itemExcluirUsuario);

        JMenu menuAluno = new JMenu("Aluno");

        JMenuItem itemConsultarAluno = new JMenuItem("Consultar");
        JMenuItem itemIncluirAluno = new JMenuItem("Incluir");
        JMenuItem itemAlterarAluno = new JMenuItem("Alterar");
        JMenuItem itemExcluirAluno = new JMenuItem("Excluir");

        menuAluno.add(itemConsultarAluno);
        menuAluno.add(itemIncluirAluno);
        menuAluno.add(itemAlterarAluno);
        menuAluno.add(itemExcluirAluno);

        JMenu menuAjuda = new JMenu("Ajuda");

        JMenuItem itemComoConsultar = new JMenuItem("Como Consultar?");
        JMenuItem itemComoIncluir = new JMenuItem("Como Incluir?");
        JMenuItem itemComoAlterar = new JMenuItem("Como Alterar?");
        JMenuItem itemComoExcluir = new JMenuItem("Como Excluir?");

        menuAjuda.add(itemComoConsultar);
        menuAjuda.add(itemComoIncluir);
        menuAjuda.add(itemComoAlterar);
        menuAjuda.add(itemComoExcluir);

        JMenuItem itemSair = new JMenuItem("Sair");

        itemSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        itemConsultarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new ConsultarUsuario();
                    }
                });
            }
        });

        itemIncluirUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new IncluirUsuario();
                    }
                });
            }
        });

        itemAlterarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new AlterarUsuario();
                    }
                });
            }
        });

        itemExcluirUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new ExcluirUsuario();
                    }
                });
            }
        });

        itemConsultarAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new ConsultarAluno();
                    }
                });
            }
        });

        itemIncluirAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new IncluirAluno();
                    }
                });
            }
        });

        itemAlterarAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new AlterarAluno();
                    }
                });
            }
        });

        itemExcluirAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new ExcluirAluno();
                    }
                });
            }
        });

        menuBar.add(menuUsuario);
        menuBar.add(menuAluno);
        menuBar.add(menuAjuda);
        menuBar.add(itemSair);

        setJMenuBar(menuBar);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
    }
}
