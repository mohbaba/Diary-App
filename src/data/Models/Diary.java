package data.Models;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private final List<Entry> entries = new ArrayList<>();

    public Diary(){}

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public String toString(){
        return String.format("%s",this.username);
    }
}
