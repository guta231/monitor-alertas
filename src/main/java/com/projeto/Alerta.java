package com.projeto;

import java.time.LocalDateTime;

public class Alerta {
    private int idAlerta;
    private String nivelRisco;             // ALTERADO para String
    private LocalDateTime dataHoraAlerta;
    private String mensagemAlerta;
    private int regiaoIdRegiao;

    public Alerta(int idAlerta, String nivelRisco, LocalDateTime dataHoraAlerta, String mensagemAlerta, int regiaoIdRegiao) {
        this.idAlerta = idAlerta;
        this.nivelRisco = nivelRisco;
        this.dataHoraAlerta = dataHoraAlerta;
        this.mensagemAlerta = mensagemAlerta;
        this.regiaoIdRegiao = regiaoIdRegiao;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public String getNivelRisco() {
        return nivelRisco;
    }

    public LocalDateTime getDataHoraAlerta() {
        return dataHoraAlerta;
    }

    public String getMensagemAlerta() {
        return mensagemAlerta;
    }

    public int getRegiaoIdRegiao() {
        return regiaoIdRegiao;
    }
}
