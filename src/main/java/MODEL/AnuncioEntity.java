package MODEL;

import DTO.AnuncioDTO;

public class AnuncioEntity {
    private int id;
    private int idVendedor;
    private String titulo;
    private String descricao;
    private int likes;
    private int deslikes;

    public AnuncioEntity() {

    }

    public AnuncioEntity(int idVendedor, String titulo, String descricao) {
        this.idVendedor = idVendedor;
        this.titulo = titulo.toUpperCase();
        this.descricao = descricao.toUpperCase();
    }

    public AnuncioEntity(int id, int idVendedor, String titulo, String descricao, int likes, int deslikes) {
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

    public AnuncioDTO toDTO(){
        return new AnuncioDTO(getId(),
                getIdVendedor(),
                getTitulo(),
                getDescricao(),
                getLikes(),
                getDeslikes());
    }
}