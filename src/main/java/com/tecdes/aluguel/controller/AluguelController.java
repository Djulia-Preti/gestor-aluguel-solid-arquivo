package com.tecdes.aluguel.controller;

import java.util.List;

import com.tecdes.aluguel.model.Aluguel;
import com.tecdes.aluguel.repository.AluguelRepository;

public class AluguelController {

    private AluguelRepository repository; // Injetando uma dependência na classe

    public AluguelController(){
        repository = new AluguelRepository();
    }
   
    public String processarAluguel(Aluguel aluguel){
        String resultado = aluguel.calcular();
        repository.salvar(resultado);
        System.out.println("Aluguel registrado: " + resultado);
        return resultado;
    }

    public List<String> listarAlugueis(){
        List<String> lista = repository.listar();
        System.out.println("Lista de Alugueis: " + lista);
        return lista;
    }

   
    

}
