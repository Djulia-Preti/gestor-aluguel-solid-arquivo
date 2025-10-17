package com.tecdes.aluguel.model;

public class AluguelCasa implements Aluguel{

    double ValorMensal;
    int qtdMeses;

    @Override
    public String calcular() {
        return "O Contrato de Aluguel Casa ficou no valor final de R$: " + ValorMensal*qtdMeses;
    }

}
