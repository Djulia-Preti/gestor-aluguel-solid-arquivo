package com.tecdes.aluguel.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tecdes.aluguel.controller.AluguelController;
import com.tecdes.aluguel.model.Aluguel;
import com.tecdes.aluguel.model.AluguelApartamento;
import com.tecdes.aluguel.model.AluguelCasa;
import com.tecdes.aluguel.model.AluguelComercial;

public class AluguelForm extends JFrame {

    private JTextField txtValor; // Correto
    private JTextField txtMeses; // Correto
    private JComboBox<String> cmbTipoAluguel; // Correto
    private JTextArea txtResultado; // Correto
    private AluguelController controller; // Correto

    public AluguelForm() {
        setSize(420, 520);
        setTitle("Gestor de Pagamentos - v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        controller = new AluguelController();

        initComponents();
    }

    private void initComponents() {
        // ----------------------ENTRADA DE DADOS--------------------------------
        JLabel lblValor = new JLabel("Valor (R$):");
        lblValor.setBounds(40, 30, 100, 25);
        add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(150, 30, 200, 25);
        add(txtValor);

        JLabel lblMeses = new JLabel("Meses:");
        lblMeses.setBounds(40, 70, 100, 25);
        add(lblMeses);

        txtMeses = new JTextField();
        txtMeses.setBounds(150, 70, 200, 25);
        add(txtMeses);

        JLabel lblTipoAluguel = new JLabel("Tipo de Aluguel:");
        lblTipoAluguel.setBounds(40, 120, 150, 25);
        add(lblTipoAluguel);

        cmbTipoAluguel = new JComboBox<>(new String[] { "Casa", "Apartamento", "Comercial" });
        cmbTipoAluguel.setBounds(190, 120, 160, 25);
        add(cmbTipoAluguel);

        // ----------------------BOTÕES--------------------------------
        JButton btnProcessar = new JButton("Processar Aluguel");
        btnProcessar.setBounds(120, 200, 180, 35);
        add(btnProcessar);

        JButton btnSalvarHistorico = new JButton("Salvar Histórico");
        btnSalvarHistorico.setBounds(120, 240, 180, 35);
        add(btnSalvarHistorico);

        JButton btnRecarregarHistorico = new JButton("Recarregar Histórico");
        btnRecarregarHistorico.setBounds(120, 280, 180, 35);
        add(btnRecarregarHistorico);

        // ----------------------SAÍDA DE DADOS--------------------------------
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(40, 350, 320, 100);
        add(scroll);

        // ----------------------EVENTO (Listener)--------------------------------
        btnProcessar.addActionListener(e -> processar());
        btnSalvarHistorico.addActionListener(e -> btnSalvarHistorico());
        btnRecarregarHistorico.addActionListener(e -> btnRecarregarHistorico());

    }

    private void btnRecarregarHistorico() {
       
    }

    private void processar() {
        try {
            double valorMensal = Double.parseDouble(txtValor.getText());
            int meses = Integer.parseInt(txtMeses.getText());
            String tipo = (String) cmbTipoAluguel.getSelectedItem();

            Aluguel aluguel;
            switch (tipo) {
                case "Casa":
                    aluguel = new AluguelCasa(valorMensal, meses);
                    break;
                case "Apartamento":
                    aluguel = new AluguelApartamento(valorMensal, meses);
                    break;
                case "Comercial":
                    aluguel = new AluguelComercial(valorMensal, meses);
                    break;
                default:
                    txtResultado.setText("Erro: Tipo de imóvel inválido.");
                    return;
            }

            String resultado = controller.processarAluguel(aluguel);
            txtResultado.append(resultado + "\n");

        } catch (NumberFormatException e) {
            txtResultado.setText("Erro: Valores inválidos.");
        }
    }

    private void btnSalvarHistorico() {
        List<String> alugueis = controller.listarAlugueis();

        if (alugueis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há alugueis no histórico. ");
            return;
        }

        try (FileWriter writer = new FileWriter("data/alugueis.txt")) {
            for (String registro : alugueis) {
                writer.write(registro);
            }
            JOptionPane.showMessageDialog(null, "Gravação do Arquivo com Sucessso!! ");
            txtResultado.setVisible(false);
            txtResultado.setText("");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro na tentativa de salvar o arquivo: " + e.getMessage());
        }
    }

}
