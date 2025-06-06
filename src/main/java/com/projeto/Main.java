package com.projeto;

public class Main {
    public static void main(String[] args) {
        AlertaMonitor monitor = new AlertaMonitor();
        try {
            System.out.println("Iniciando o monitoramento de alertas...");
            monitor.monitorarAlertas();
        } catch (Exception e) {
            System.err.println("Erro durante o monitoramento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
