package model;

public class User {

    private int idUser;
    private String nama;
    private String username;
    private String password;
    private int idRole;
    private String role;
    private Integer idDokter;

    public User(int idUser, String nama, String username, String password, int idRole, String role, Integer idDokter) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.role = role;
        this.idDokter = idDokter;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getRole() {
        return role;
    }

    public Integer getIdDokter() {
        return idDokter;
    }

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    public void logout() {
        System.out.println("Logout berhasil");
    }
}