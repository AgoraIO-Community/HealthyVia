package io.agora.ui.model;

public class User {
    private String fullname;
    private boolean isEMT;
    private String email;

    public User(String fullname, String email, boolean isEMT) {
        this.fullname = fullname;
        this.email = email;
        this.isEMT = isEMT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isEMT() {
        return isEMT;
    }

    public void setEMT(boolean EMT) {
        isEMT = EMT;
    }
}
