package data.Models;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private final List<Entry> entries = new ArrayList<>();

    public Diary(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String toString(){
        return String.format("%s",this.username);
    }
}
