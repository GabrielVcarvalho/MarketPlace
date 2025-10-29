package dto;

import model.AnuncioEntity;

public class AnuncioDTO extends DTO{
    private int id;
    private int idVendedor;
    private String titulo;
    private String descricao;
    private int likes;
    private int deslikes;

    public AnuncioDTO() {

    }

    public AnuncioDTO(int id, int idVendedor, String titulo, String descricao, int likes, int deslikes) {
        this.id = id;
        this.idVendedor = idVendedor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.likes = likes;
        this.deslikes = deslikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDeslikes() {
        return deslikes;
    }

    public void setDeslikes(int deslikes) {
        this.deslikes = deslikes;
    }

    //Mantido dentro da classe para dar mais legibilidade já o fluxo natural de TODO DTO é virar uma Entity
    public AnuncioEntity toEntity(){
        return new AnuncioEntity(this.getIdVendedor(), this.getTitulo().toUpperCase(), this.getDescricao());
    }
}