package com.alexxicatto.acervodelivros;

import java.util.ArrayList;
import java.util.List;

// Classe que permite registrar um livro
public class Livro {

    private int id;
    private int anoPublicacao;
    private String titulo;
    private String autor;
    private String edicao;
    private String editora;
    private String cidade;

    public Livro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Livro(int id, String titulo, String autor, String edicao, String editora) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.edicao = edicao;
        this.editora = editora;
    }

    public Livro(int id, int anoPublicacao, String titulo, String autor, String cidade) {
        this.id = id;
        this.anoPublicacao = anoPublicacao;
        this.titulo = titulo;
        this.autor = autor;
        this.cidade = cidade;
    }
    
    public Livro(int id, int anoPublicacao, String titulo, String autor, String edicao, String editora, String cidade) {
        this.id = id;
        this.anoPublicacao = anoPublicacao;
        this.titulo = titulo;
        this.autor = autor;
        this.edicao = edicao;
        this.editora = editora;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public static boolean existeLivroID(int id, List<Livro> livros) {

        for (Livro livro : livros) {

            if (livro.getId() == id) {
                return true;
            }
        }

        return false;
    }
    
    public static List<Livro> getLivroPeloID(int id, List<Livro> livros) {

        List<Livro> bookById = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                bookById.add(livro);
            }
        }

        return bookById;
    }
    
    @Override
    public String toString() {
        return "Livro{" + "id=" + id + ", anoPublicacao=" + anoPublicacao + ", titulo=" + titulo + ", autor=" + autor + ", edicao=" + edicao + ", editora=" + editora + ", cidade=" + cidade + '}';
    }
}
