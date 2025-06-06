package com.projeto;

public class Regiao {
    private int idRegiao;
    private String nomeRegiao;

    public Regiao(int idRegiao, String nomeRegiao) {
        this.idRegiao = idRegiao;
        this.nomeRegiao = nomeRegiao;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }
}
