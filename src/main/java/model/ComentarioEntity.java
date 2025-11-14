package model;

public class ComentarioEntity {
    private int idComment;
    private int idAuthor;
    private int anuncioId;
    private String content;

    public ComentarioEntity(int idAuthor, int anuncioId, String content) {
        this.idAuthor = idAuthor;
        this.anuncioId = anuncioId;
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

    public int getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(int anuncioId) {
        this.anuncioId = anuncioId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
