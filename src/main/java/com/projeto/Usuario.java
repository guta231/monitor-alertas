package com.projeto;

public class Usuario {
    private int idUsuario;
    private String cpfUsuario;
    private String emailUsuario;
    private int regiaoIdRegiao;

    public Usuario(int idUsuario, String cpfUsuario, String emailUsuario, int regiaoIdRegiao) {
        this.idUsuario = idUsuario;
        this.cpfUsuario = cpfUsuario;
        this.emailUsuario = emailUsuario;
        this.regiaoIdRegiao = regiaoIdRegiao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public int getRegiaoIdUsuario() {
        return regiaoIdRegiao;
    }
}
