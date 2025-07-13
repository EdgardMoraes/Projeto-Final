package br.com.edgardmoraes.controller;


import br.com.edgardmoraes.exception.LivroException;
import br.com.edgardmoraes.model.Livro;
import br.com.edgardmoraes.utils.GenerateBookPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LivroController",value = "/livros")
public class LivroController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private List<Livro> livros;
    public LivroController() {
        super();
    }

    @Override
    public void init(){
        livros = new ArrayList<>();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookPages = GenerateBookPage.GeneratePage(request, livros);
        request.setAttribute("bookPages", bookPages);
        request.getRequestDispatcher("/view/index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idParse = Integer.parseInt(id);
                livros.removeIf(livro -> livro.getId() == idParse);
            }
            response.sendRedirect(request.getContextPath() + "/livros");
        }

        try {
            Livro livro = new Livro();
            livro.setTitulo(request.getParameter("titulo"));
            livro.setAutor(request.getParameter("autor"));
            livro.setAno(Integer.parseInt(request.getParameter("ano")));
            livro.validate();
            livros.add(livro);
            response.sendRedirect(request.getContextPath() + "/livros");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Ano deve ser um número.");
            request.setAttribute("bookPages", GenerateBookPage.GeneratePage(request, livros));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        } catch (LivroException e) {
            request.setAttribute("mensagemErro", e.getMessage());
            request.setAttribute("bookPages", GenerateBookPage.GeneratePage(request, livros));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
