package com.controleDeAcesso.service;

import com.controleDeAcesso.dao.MoradorDAO;
import com.controleDeAcesso.dto.MoradorDTO;
import com.controleDeAcesso.model.Morador;

import java.sql.SQLException;

public class MoradorService {

    MoradorDAO moradorDAO;

    public MoradorService(){
        this.moradorDAO = new MoradorDAO();
    }

    private MoradorDTO toDTO(Morador morador){
        MoradorDTO dto = new MoradorDTO();

        dto.setId(morador.getId());

        return dto;
    }

    public int incluirMorador(MoradorDTO moradorDTO){

        PessoaService.validarPessoaDTO(moradorDTO);
        //validarMoradorDTO();

        Morador morador = new Morador(
                moradorDTO.getNome(),
                moradorDTO.getCpf(),
                moradorDTO.getDataNasc(),
                moradorDTO.getTelefone(),
                moradorDTO.getEmail()
        );

        try{
            return moradorDAO.incluirMorador(morador);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao incluir morador no banco de dados", e);
        }
    }

    public boolean atualizarMorador(MoradorDTO moradorDTO){

        PessoaService.validarPessoaDTO(moradorDTO);
        //validarMoradorDTO();

        Morador morador = new Morador(
                moradorDTO.getNome(),
                moradorDTO.getCpf(),
                moradorDTO.getDataNasc(),
                moradorDTO.getTelefone(),
                moradorDTO.getEmail()
        );

        try{
            return moradorDAO.atualizarMorador(morador);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar morador no banco de dados", e);
        }
    }

    public boolean deletarMorador(int mor_id){

        try{
            return moradorDAO.deletarMorador(mor_id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar morador no banco de dados", e);
        }
    }

}
