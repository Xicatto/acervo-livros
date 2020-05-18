package com.alexxicatto.acervodelivros;

import java.time.LocalDate;
import java.util.List;

// Classe que permite o usuario fazer um emprestimo com um determinado livro atravéz 
// do id do usuário e do livro
public class Emprestimo {
    
    private int id;
    private int idUsuario;
    private int idLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    // Todos os campos são obrigatórios
    public Emprestimo(int id, int idUsuario, int idLivro, LocalDate dataEmprestimo, LocalDate dataDevolucao, boolean devolvido) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
    
    public static boolean existeEmprestimoID(int id, List<Emprestimo> emprestimos) {
        
        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getId() == id) {
                return true;
            }
        }

        return false;
    }
    
    // Transformando boolean para Sim e Não para uma leitura melhor.
    // Utilizando o LocalDate para construir a data para o nosso formato.
    @Override
    public String toString() {
        String devolvidoString;
        if (devolvido) {
            devolvidoString = "Sim";
        } else {
            devolvidoString = "Não";
        }
        String dataEmprestimoString = dataEmprestimo.getDayOfMonth() + "/" + dataEmprestimo.getMonthValue() + "/" + dataEmprestimo.getYear();
        String dataDevolucaoString = dataDevolucao.getDayOfMonth() + "/" + dataDevolucao.getMonthValue() + "/" + dataDevolucao.getYear();
        return "ID: " + id + "\nID Usuario: " + idUsuario + "\nID Livro: " + idLivro + "\nData Emprestimo: " + dataEmprestimoString + "\nData Devolucao: " + dataDevolucaoString + "\nDevolvido: " + devolvidoString;
    }
}
