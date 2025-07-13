package br.com.edgardmoraes.model;

import br.com.edgardmoraes.exception.LivroException;
import br.com.edgardmoraes.utils.GenerateIsbn;

public class Livro {
    static int increaseId = 0;

    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private String isbn;


    public Livro() {
        this.id = ++increaseId;
        this.isbn = GenerateIsbn.generateIsbn();
    }

    public Livro(int id, String titulo, String autor, int ano, String isbn) {
        this.id = ++increaseId;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.isbn = GenerateIsbn.generateIsbn();
    }

    public int getId() {
        return id;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getIsbn() {
        return isbn;
    }

    public void validate() throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            increaseId--;
            throw new LivroException("O título é obrigatório.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            increaseId--;
            throw new LivroException("O autor é obrigatório.");
        }
        if (ano < 1500) {
            increaseId--;
            throw new LivroException("Ano inválido.");
        }
        if (ano > 2025) {
            increaseId--;
            throw new LivroException("Ano inválido.");
        }

    }
}