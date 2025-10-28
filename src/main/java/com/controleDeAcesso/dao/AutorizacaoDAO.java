package com.controleDeAcesso.dao;

import com.controleDeAcesso.model.Autorizacao;
import com.controleDeAcesso.model.Pessoa;
import com.controleDeAcesso.model.LocalControlado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorizacaoDAO {

    // Gravar
    public int incluirAutorizacao(Autorizacao autorizacao) throws SQLException {

        String sql = "INSERT INTO AUTORIZACOES (pessoa_id, local_id) VALUES (?,?)";

        // Abre e fecha o conector
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, autorizacao.getPesId());
            stmt.setInt(2, autorizacao.getLocId());


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

    // Deletar - Retorna boolean
    public boolean deletarAutorizacao(int pessoa_id, int local_id) throws SQLException {

        String sql = "DELETE FROM AUTORIZACOES WHERE pessoa_id=? AND local_id=?";

        try (Connection conn = ConnectionFactory.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoa_id);
            stmt.setInt(2, local_id);

            return stmt.executeUpdate() > 0;
        }
    }

    // Consultar autorizações relacionas a uma pessoa(arrayList)
    public List<Autorizacao> consultarAutorizacoesPorPessoa(int pessoa_id) throws SQLException {

        String sql = "SELECT * FROM AUTORIZACOES WHERE pessoa_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pessoa_id);

            try (ResultSet rs = stmt.executeQuery()) {

                List<Autorizacao> listaAutorizacao = new ArrayList<>();

                // cursor mostra a linha n-1

                while (rs.next()) {
                    Autorizacao a = new Autorizacao();
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setLocId(rs.getInt("local_id"));
                    listaAutorizacao.add(a);
                }
                return listaAutorizacao;
            }
        }
    }

    // Consultar autorizações relacionadas a um local(arrayList)
    public List<Autorizacao> consultarAutorizacoesPorLocal(int local_id) throws SQLException {

        String sql = "SELECT * FROM AUTORIZACOES WHERE local_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, local_id);

            try (ResultSet rs = stmt.executeQuery()) {

                List<Autorizacao> listaAutorizacao = new ArrayList<>();

                while (rs.next()) {
                    Autorizacao a = new Autorizacao();
                    a.setPesId(rs.getInt("pessoa_id"));
                    a.setLocId(rs.getInt("local_id"));
                    listaAutorizacao.add(a);
                }
                return listaAutorizacao;
            }
        }
    }

    // Atualizar - Retorna boolean
   /* public boolean atualizarAutorizacao(Autorizacao autorizacao, LocalControlado locaisControlados, boolean novoValorAutorizacao) throws SQLException {

        String sql = "UPDATE AUTORIZACOES " +
                "WHERE pessoa_id=? AND local_id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novoValorAutorizacao ? 1:0 );
            stmt.setInt(2, autorizacao.getPesId());
            stmt.setInt(3, locaisControlados.getId());

            return stmt.executeUpdate() > 0;
        }
    }*/
}
