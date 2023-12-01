package exercise.practice;

public class Information {
    String Username;
    String password;
    String email;

    public Information(String Username, String password, String email){
        this.Username =Username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

 }
