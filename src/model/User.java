package model;

public class User {

    private int idUser;
    private String nama;
    private String username;
    private String password;
    private int idRole;
    private String role;
    private Integer idDokter;

    public User() {
    }

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

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(Integer idDokter) {
        this.idDokter = idDokter;
    }

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    public void logout() {
        System.out.println("Logout berhasil");
    }

    @Override
    public String toString() {
        return nama;
    }
}
