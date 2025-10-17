package com.tecdes.aluguel.model;

public class AluguelComercial implements Aluguel{

    double ValorMensal;
    int qtdMeses;

    @Override
    public String calcular() {
        return "O Contrato de Aluguel Comercial ficou no valor final de R$: " + ValorMensal*qtdMeses;
    }

}
