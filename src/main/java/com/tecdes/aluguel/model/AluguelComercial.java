package com.tecdes.aluguel.model;

public class AluguelComercial implements Aluguel{

    private double ValorMensal;
    private int meses;

    public AluguelComercial(double valorMensal, int meses) {
        ValorMensal = valorMensal;
        this.meses = meses;
    }

    @Override
    public String calcular() {
        double resultado = ValorMensal * meses;
        return "O Contrato de Aluguel Comercial ficou no valor final de R$: " + resultado;
    }


}
