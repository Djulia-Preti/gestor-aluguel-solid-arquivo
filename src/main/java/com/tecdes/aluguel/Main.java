package com.tecdes.aluguel;

import javax.swing.SwingUtilities;

import com.tecdes.aluguel.view.AluguelForm;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AluguelForm().setVisible(true));
    }
}