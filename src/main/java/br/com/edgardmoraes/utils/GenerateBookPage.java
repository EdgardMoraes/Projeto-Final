package br.com.edgardmoraes.utils;

import br.com.edgardmoraes.model.Livro;
import jakarta.servlet.http.HttpServletRequest;

import java.awt.print.Book;
import java.util.List;

public class GenerateBookPage {
    public static String GeneratePage(HttpServletRequest request, List<Livro> livros) {
        StringBuilder bookPages = new StringBuilder();
        bookPages.append("<h2> Livros cadastrados</h2>");
        for (Livro livro : livros) {
            bookPages
                    .append("<div class=\"lista\">")
                    //Lista dos Livros
                    .append("<p><strong> Titulo: </strong>" + livro.getTitulo() + "</p>")
                    .append("<p><strong> Autor: </strong>" + livro.getAutor() + "</p>")
                    .append("<p><strong> Ano: </strong>" + livro.getAno() + "</p>")
                    .append("<p><strong> ISBN: </strong>" + livro.getIsbn() + "</p>")
                    .append("<p><strong> id: </strong>" + livro.getId() + "</p>")

                    //Botao para excluir
                    .append("<form class=\"delete-form\" method='post' action='" + request.getContextPath() + "/livros' style='display:inline;'> ")
                    .append("<input type='hidden' name='action' value='delete' />")
                    .append("<input type='hidden' name='id' value='" + livro.getId() + "' />")
                    .append("<button type='submit'>EXCLUIR</button>")
                    .append("</form>")
                    .append("</div>");
        }
        return bookPages.toString();
         }

    }


