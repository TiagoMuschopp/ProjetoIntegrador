/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Controller.Cadastro;

/**
 *
 * @author tiago
 */
public class Livro {
    private int id;
    private String titulo;
    private String isbn;
    private String autor;
    private String editora;
    private String publicado;
    private String genero;
    private int paginas;
    private int qtd_livros;
    
    public Livro(){}
    
    public  Livro(int id, String titulo, String isbn, String autor, String editora, String publicado, String genero, int paginas, int qtd_livros){
       this.id = id;
       this.titulo = titulo;
       this.isbn = isbn;
       this.autor = autor;
       this.editora = editora;
       this.publicado = publicado;
       this.genero = genero;
       this.paginas = paginas;
       this.qtd_livros = qtd_livros;
    }
    
     public void editarLivros(Livro livro) throws Exception{ 
        new Cadastro().atualizarLivro(livro);
    }
     
     public Livro exibirLivro(int id){
         return new Cadastro().consultarLivroPorId(id);
     }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getPublicado() {
        return publicado;
    }

    public void setPublicado(String publicado) {
        this.publicado = publicado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getQtd_livros() {
        return qtd_livros;
    }

    public void setQtd_livros(int qtd_livros) {
        this.qtd_livros = qtd_livros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
