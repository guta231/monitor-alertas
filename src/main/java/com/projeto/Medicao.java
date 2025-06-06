package com.projeto;

import java.time.LocalDateTime;

public class Medicao {
    private int idMedicao;
    private LocalDateTime dataHoraMedicao;
    private double valorMedicao;
    private int sensorIdSensor;
    private String tipoMedicao;

    public Medicao(int idMedicao, LocalDateTime dataHoraMedicao, double valorMedicao, int sensorIdSensor, String tipoMedicao) {
        this.idMedicao = idMedicao;
        this.dataHoraMedicao = dataHoraMedicao;
        this.valorMedicao = valorMedicao;
        this.sensorIdSensor = sensorIdSensor;
        this.tipoMedicao = tipoMedicao;
    }

    public int getIdMedicao() {
        return idMedicao;
    }

    public LocalDateTime getDataHoraMedicao() {
        return dataHoraMedicao;
    }

    public double getValorMedicao() {
        return valorMedicao;
    }

    public int getSensorIdSensor() {
        return sensorIdSensor;
    }

    public String getTipoMedicao() {
        return tipoMedicao;
    }
}
