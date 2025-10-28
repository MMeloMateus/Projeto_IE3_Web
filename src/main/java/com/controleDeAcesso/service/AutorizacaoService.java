package com.controleDeAcesso.service;

import com.controleDeAcesso.dao.AutorizacaoDAO;
import com.controleDeAcesso.dto.AutorizacaoDTO;
import com.controleDeAcesso.model.Autorizacao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AutorizacaoService {

    private AutorizacaoDAO autorizacaoDAO;

    public AutorizacaoService(){
        autorizacaoDAO = new AutorizacaoDAO();
    }

    private AutorizacaoDTO toDTO(Autorizacao autorizacao){
        AutorizacaoDTO dto = new AutorizacaoDTO();

        dto.setId(autorizacao.getId());
        dto.setPesId(autorizacao.getPesId());
        dto.setLocId(autorizacao.getLocId());

        return dto;
    }

    public int incluirAutorizacao(AutorizacaoDTO autorizacaoDTO){

        //validarAutorizacaoDTO();

        Autorizacao autorizacao = new Autorizacao(
                autorizacaoDTO.getPesId(),
                autorizacaoDTO.getLocId()
        );

        try {
            return autorizacaoDAO.incluirAutorizacao(autorizacao);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao incluir autorização no banco de dados", e);
        }
    }

    public List<AutorizacaoDTO> consultarAutorizacoesPorLocal(int local_id){

        try{
            return autorizacaoDAO.consultarAutorizacoesPorLocal(local_id)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar autorizações por local no banco de dados", e);
        }
    }

    public List<AutorizacaoDTO> consultarAutorizacoesPorPessoa(int pessoa_id){

        try{
            return autorizacaoDAO.consultarAutorizacoesPorPessoa(pessoa_id)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar autorizações por local no banco de dados", e);
        }
    }

    public boolean deletarAutorizacao(int pessoa_id , int local_id){

        try{
            return autorizacaoDAO.deletarAutorizacao(pessoa_id,local_id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar autorização no banco de dados", e);
        }
    }
}
