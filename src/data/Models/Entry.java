package data.Models;

import java.time.LocalDate;

public class Entry {
    private int id;
    private String title;
    private String body;
    private LocalDate creationDate = LocalDate.now();
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Entry(String title, String body){
        this.title = title;
        this.body = body;
        this.creationDate = LocalDate.now();
    }

    public Entry(){}

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return this.id;
    }

    public String toString(){
        return String.format("%d",id);
    }
}
