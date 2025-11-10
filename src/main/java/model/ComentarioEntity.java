package model;

public class ComentarioEntity {
    private int idAuthor;
    private String content;
    private int likes;
    private int disLikes;

    public ComentarioEntity(int idAuthor, String content) {
        this.idAuthor = idAuthor;
        this.content = content;
    }

    public ComentarioEntity(int idAuthor, String content, int likes, int disLikes) {
        this.idAuthor = idAuthor;
        this.content = content;
        this.likes = likes;
        this.disLikes = disLikes;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(int disLikes) {
        this.disLikes = disLikes;
    }
}
