package model;

public class DeslikeEntity {
    private int id;
    private int idAnuncio;
    private int idUsuario;

    public DeslikeEntity(int idAnuncio, int idUsuario) {
        this.idAnuncio = idAnuncio;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}