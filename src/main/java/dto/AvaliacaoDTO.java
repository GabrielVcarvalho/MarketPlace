package dto;

public class AvaliacaoDTO  extends DTO{
    //Criei para evitar uma duplo do AnuncioDTO

    private int idAnuncio;
    private int idUsuario;

    public AvaliacaoDTO() {

    }

    public AvaliacaoDTO(int idAnuncio, int idUsuario) {
        this.idAnuncio = idAnuncio;
        this.idUsuario = idUsuario;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}
