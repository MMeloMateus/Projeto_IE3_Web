package com.controleDeAcesso.dao;

import com.controleDeAcesso.Views.AcessoView;
import com.controleDeAcesso.util.StatusAcesso;
import com.controleDeAcesso.util.TipoAcesso;

import com.controleDeAcesso.model.Acesso;
import com.controleDeAcesso.model.LocalControlado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessoDAO {

    // Gravar
    public int incluirAcesso(Acesso acesso) throws SQLException {

        String sql = "INSERT INTO ACESSOS (local_id, pessoa_id, acesso_data, acesso_tipo, acesso_status) VALUES (?,?,?,?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, acesso.getLocId());
            stmt.setInt(2, acesso.getPesId());
            stmt.setTimestamp(3, acesso.getData());
            //stmt.setTimestamp(3, Timestamp.valueOf(acesso.getData()));
            stmt.setString(4, acesso.getTipoAcesso().name());

            // Linha afetada pela ação
            int affectedRows = stmt.executeUpdate();

            // Cursos sempre fica um antes
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // id gerado
                }
            }

            // Retornar a linha afetada pela ação
            return affectedRows;
        }
    }

    // Consultar
    public Acesso consultarAcesso(int id) throws SQLException {

        String sql = "SELECT * FROM ACESSOS WHERE acesso_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                // cursor mostra a linha n-1
                if (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setData(rs.getTimestamp("acesso_data"));
                    a.setTipoAcesso(TipoAcesso.valueOf(rs.getString("acesso_tipo")));
                    a.setStatusAcesso(StatusAcesso.valueOf(rs.getString("acesso_status")));
                    return a;
                }
                return null;
            }
        }
    }

    // Consultar por View
    public AcessoView consultarAcessoView(int id) throws SQLException {

        String sql = "SELECT " +
                "a.acesso_id, " +
                "a.local_id, " +
                "a.pessoa_id, " +
                "a.acesso_data, " +
                "a.acesso_tipo, " +
                "a.acesso_status, " +
                "b.pessoa_nome AS pessoa_nome, " +
                "b.pessoa_tipo, " +
                "c.local_nome AS local_nome " +
                "FROM ACESSOS a " +
                "INNER JOIN PESSOAS b ON a.pessoa_id = b.pessoa_id " +
                "INNER JOIN LOCAIS_CONTROLADOS c ON a.local_id = c.local_id " +
                "WHERE a.acesso_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    AcessoView a = new AcessoView();

                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setLocNome(rs.getString("local_nome"));
                    a.setPesNome(rs.getString("pessoa_nome"));
                    a.setTipoPessoa(rs.getString("pessoa_tipo"));
                    a.setData(rs.getTimestamp("acesso_data"));

                    // Conversões de enums
                    String tipo = rs.getString("acesso_tipo");
                    if (tipo != null) {
                        try {
                            a.setTipoAcesso(TipoAcesso.valueOf(tipo.trim().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            a.setTipoAcesso(null);
                        }
                    }

                    String status = rs.getString("acesso_status");
                    if (status != null) {
                        try {
                            a.setStatusAcesso(StatusAcesso.valueOf(status.trim().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            a.setStatusAcesso(null);
                        }
                    }

                    return a;
                }

                return null;
            }
        }
    }

    // Consultar relacionados a Local
    public List<Acesso> consultarAcessosPorLocal(int local_id) throws SQLException {

        String sql = "SELECT * FROM ACESSOS WHERE local_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, local_id);

            try (ResultSet rs = stmt.executeQuery()) {

                List<Acesso> listaAcessos = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setData(rs.getTimestamp("acesso_data"));
                    a.setTipoAcesso(TipoAcesso.valueOf(rs.getString("acesso_tipo")));
                    a.setStatusAcesso(StatusAcesso.valueOf(rs.getString("acesso_status")));
                    listaAcessos.add(a);
                }
                return listaAcessos;
            }
        }
    }

    // Consultar relacionados a Pessoas
    public List<Acesso> consultarAcessosPorPessoa(int pessoa_id) throws SQLException {

        String sql = "SELECT * FROM ACESSOS WHERE pessoa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoa_id);

            try (ResultSet rs = stmt.executeQuery()) {

                List<Acesso> listaAcessos = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setData(rs.getTimestamp("acesso_data"));
                    a.setTipoAcesso(TipoAcesso.valueOf(rs.getString("acesso_tipo")));
                    a.setStatusAcesso(StatusAcesso.valueOf(rs.getString("acesso_status")));
                    listaAcessos.add(a);
                }
                return listaAcessos;
            }
        }
    }

    // Consultar relacionados a Pessoas
    // Necessidade de Melhora no Try
    public List<Acesso> consultarAcessosOrderData( ) throws SQLException {
    // [AcessoView]
        String sql = "SELECT * FROM ACESSOS ORDER BY acesso_data ASC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println(rs);
                List<Acesso> listaAcessos = new ArrayList<>();

                // cursor mostra a linha n-1
                while (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("acesso_id"));
                    a.setLocId(rs.getInt("local_id"));
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setData(rs.getTimestamp("acesso_data"));
                    a.setTipoAcesso(TipoAcesso.valueOf(rs.getString("acesso_tipo").toUpperCase()));
                    a.setStatusAcesso(StatusAcesso.valueOf(rs.getString("acesso_status").toUpperCase()));
                    listaAcessos.add(a);
                }
                return listaAcessos;
            }
        }
    }

    public List<AcessoView> consultarAcessosViewOrderData() throws SQLException {
        String sql = "SELECT " +
                "a.acesso_id, " +
                "a.local_id, " +
                "a.pessoa_id, " +
                "a.acesso_data, " +
                "a.acesso_tipo, " +
                "a.acesso_status, " +
                "b.pessoa_nome AS pessoa_nome, " +
                "b.pessoa_tipo" +
                "c.local_nome AS local_nome " +
                "FROM ACESSOS a " +
                "INNER JOIN PESSOAS b ON a.pessoa_id = b.pessoa_id " +
                "INNER JOIN LOCAIS_CONTROLADOS c ON a.local_id = c.local_id " +
                "ORDER BY a.acesso_data ASC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<AcessoView> listaAcessoView = new ArrayList<>();

            while (rs.next()) {
                AcessoView a = new AcessoView();

                a.setId(rs.getInt("acesso_id"));
                a.setLocId(rs.getInt("local_id"));
                a.setPesId(rs.getInt("pessoa_id"));
                a.setLocNome(rs.getString("local_nome"));
                a.setPesNome(rs.getString("pessoa_nome"));
                a.setTipoPessoa(rs.getString("pessoa_tipo"));
                a.setData(rs.getTimestamp("acesso_data"));

                // Conversoes Necessárias
                String tipo = rs.getString("acesso_tipo");
                if (tipo != null) {
                    try {
                        a.setTipoAcesso(TipoAcesso.valueOf(tipo.trim().toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        a.setTipoAcesso(null);
                    }
                }

                String status = rs.getString("acesso_status");

                if (status != null) {
                    try {
                        a.setStatusAcesso(StatusAcesso.valueOf(status.trim().toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        a.setStatusAcesso(null);
                    }
                }

                listaAcessoView.add(a);
            }

            return listaAcessoView;
        }
    }

}
