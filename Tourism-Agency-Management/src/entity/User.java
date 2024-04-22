package entity;

public class User {
    private int user_id;
    private String user_name;
    private String user_pass;
    private Perm perm;

    public enum Perm {
        ADMIN,
        EMPLOYEE
    }

    public User() {
    }


    public User(int user_id, String user_name, String user_pass, Perm perm) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.perm = perm;
    }

    public User(String user_name, String user_pass, Perm perm) {
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.perm = perm;
    }

    public Perm getPerm() {
        return perm;
    }

    public void setPerm(Perm perm) {
        this.perm = perm;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }
}
