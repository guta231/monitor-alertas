package com.projeto;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlertaMonitor {

    private static final String URL = "jdbc:mysql://localhost/sfgb";
    private static final String USER = "admin_gustavo";
    private static final String PASS = "@SAVEFOREST1";

    // Inicializa com 2 minutos atrás para evitar perder alertas recentes ao iniciar
    private LocalDateTime ultimaDataHoraAlerta = LocalDateTime.now().minusMinutes(2);

    public void monitorarAlertas() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            while (true) {
                System.out.println("Buscando alertas após " + ultimaDataHoraAlerta);
                List<Alerta> novosAlertas = buscarNovosAlertas(conn);
                for (Alerta alerta : novosAlertas) {
                    System.out.println("Alerta encontrado: ID=" + alerta.getIdAlerta() +
                            " | Nível=" + alerta.getNivelRisco() +
                            " | Região=" + alerta.getRegiaoIdRegiao());

                    List<Usuario> usuarios = buscarUsuariosDaRegiao(conn, alerta.getRegiaoIdRegiao());
                    EmailSender.enviarEmails(usuarios, alerta);
                }

                // Atualiza a última data/hora com a mais recente encontrada
                if (!novosAlertas.isEmpty()) {
                    ultimaDataHoraAlerta = novosAlertas.get(novosAlertas.size() - 1).getDataHoraAlerta();
                }

                // Espera 1 minuto antes da próxima checagem
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Alerta> buscarNovosAlertas(Connection conn) throws SQLException {
        String sql = "SELECT ID_ALERTA, NIVEL_RISCO, DATA_HORA_ALERTA, MENSAGEM_ALERTA, REGIAO_ID_REGIAO " +
                     "FROM ALERTA WHERE DATA_HORA_ALERTA > ? ORDER BY DATA_HORA_ALERTA";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(ultimaDataHoraAlerta));
            ResultSet rs = stmt.executeQuery();
            List<Alerta> alertas = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("ID_ALERTA");
                String nivelRisco = rs.getString("NIVEL_RISCO");
                Timestamp ts = rs.getTimestamp("DATA_HORA_ALERTA");
                LocalDateTime dataHora = ts.toLocalDateTime();
                String mensagem = rs.getString("MENSAGEM_ALERTA");
                int regiaoId = rs.getInt("REGIAO_ID_REGIAO");

                alertas.add(new Alerta(id, nivelRisco, dataHora, mensagem, regiaoId));
            }
            return alertas;
        }
    }

    private List<Usuario> buscarUsuariosDaRegiao(Connection conn, int regiaoId) throws SQLException {
        String sql = "SELECT ID_USUARIO, CPF_USUARIO, EMAIL_USUARIO, REGIAO_ID_REGIAO FROM USUARIO WHERE REGIAO_ID_REGIAO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, regiaoId);
            ResultSet rs = stmt.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                int idUsuario = rs.getInt("ID_USUARIO");
                String cpfUsuario = rs.getString("CPF_USUARIO");
                String emailUsuario = rs.getString("EMAIL_USUARIO");
                int regiaoIdUsuario = rs.getInt("REGIAO_ID_REGIAO");

                usuarios.add(new Usuario(idUsuario, cpfUsuario, emailUsuario, regiaoIdUsuario));
            }
            return usuarios;
        }
    }
}
