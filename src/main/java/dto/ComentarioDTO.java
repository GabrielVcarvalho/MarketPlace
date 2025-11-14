package dto;

public class ComentarioDTO extends DTO{
    private int idComment;
    private int idAuthor;
    private int idAnuncio;
    private String content;

    public ComentarioDTO(int idAuthor, int idAnuncio, String content) {
        this.idAuthor = idAuthor;
        this.idAnuncio = idAnuncio;
        this.content = content;
    }

    public ComentarioDTO(int idComment, int idAuthor, int idAnuncio, String content) {
        this.idComment = idComment;
        this.idAuthor = idAuthor;
        this.idAnuncio = idAnuncio;
        this.content = content;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
