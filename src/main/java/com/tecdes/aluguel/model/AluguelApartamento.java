package com.tecdes.aluguel.model;

public class AluguelApartamento implements Aluguel{

    double Valor;
    int Meses;

    double resultado = Valor * Meses;

    @Override
    public String calcular() {
        return "O Contrato de Aluguel Apartamento ficou no valor final de R$: " + resultado;
    }

}
