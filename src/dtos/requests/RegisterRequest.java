package dtos.requests;

public class RegisterRequest {
    String name;
    String password;

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
