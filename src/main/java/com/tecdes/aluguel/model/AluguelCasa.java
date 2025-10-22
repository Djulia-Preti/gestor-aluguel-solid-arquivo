package com.tecdes.aluguel.model;

public class AluguelCasa implements Aluguel{

    private double ValorMensal;
    private int meses;

    public AluguelCasa(double valorMensal, int meses) {
        ValorMensal = valorMensal;
        this.meses = meses;
    }

    @Override
    public String calcular() {
        double resultado = ValorMensal * meses;
        return "O Contrato de Aluguel Casa ficou no valor final de R$: " + resultado;
    }



}
