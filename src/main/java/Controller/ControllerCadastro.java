/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import model.Livro;

/**
 *
 * @author tiago
 */
public class ControllerCadastro {
    
    public boolean atualizarLivro(int id, String titulo, String isbn, String autor, String editora, String publicado, String genero, int paginas, int qtd_livros) throws Exception{
        
        Livro livro = new Livro(id, titulo, isbn, autor, editora, publicado, genero, paginas, qtd_livros);
        livro.editarLivros(livro);
        return true;
    }
    
    public Livro exibirLivro(int id) {
        Livro livroConsultado = new Livro().exibirLivro(id); // Consulta o livro por ID
        
        if (livroConsultado != null) {
            System.out.println("Livro encontrado:");
            System.out.println("ID: " + livroConsultado.getId());
            System.out.println("Título: " + livroConsultado.getTitulo());
            System.out.println("ISBN: " + livroConsultado.getIsbn());
            System.out.println("Autor: " + livroConsultado.getAutor());
            System.out.println("Editora: " + livroConsultado.getEditora());
            System.out.println("Publicado: " + livroConsultado.getPublicado());
            System.out.println("Gênero: " + livroConsultado.getGenero());
            System.out.println("Páginas: " + livroConsultado.getPaginas());
            System.out.println("Quantidade de Livros: " + livroConsultado.getQtd_livros());
            
            return livroConsultado; // Retorna o livro encontrado
        } else {
            System.out.println("Livro com ID " + id + " não encontrado.");
            return null; // Retorna null se o livro não for encontrado
        }
    }
}
