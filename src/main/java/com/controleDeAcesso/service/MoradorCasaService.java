package com.controleDeAcesso.service;

import com.controleDeAcesso.dao.MoradorCasaDAO;
import com.controleDeAcesso.dto.MoradorCasaDTO;
import com.controleDeAcesso.model.MoradorCasa;

import java.sql.SQLException;

public class MoradorCasaService {

    private MoradorCasaDAO moradorCasaDAO;

    public MoradorCasaService(){
        this.moradorCasaDAO = new MoradorCasaDAO();
    }

    private MoradorCasaDTO toDTO(MoradorCasa moradorCasa){
        MoradorCasaDTO dto = new MoradorCasaDTO();

        dto.setCasaId(moradorCasa.getCasaId());
        dto.setMoradorId(moradorCasa.getMoradorId());
        dto.setTipoVinculo(moradorCasa.getTipoVinculo());

        return dto;
    }

    private void validarMoradorCasaDTO(MoradorCasaDTO moradorCasaDTO){
         if(moradorCasaDTO.getTipoVinculo() == null)
             throw new IllegalArgumentException("Tipo Vínculo de morador-casa inválido");
    }

    public int incluirMoradorCasa(MoradorCasaDTO moradorCasaDTO){
        validarMoradorCasaDTO(moradorCasaDTO);

        MoradorCasa moradorCasa = new MoradorCasa(
                moradorCasaDTO.getMoradorId(),
                moradorCasaDTO.getCasaId(),
                moradorCasaDTO.getTipoVinculo()
        );

        try{
            return moradorCasaDAO.incluirMoradorCasa(moradorCasa);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao incluir morador-casa no banco de dados",e);
        }
    }

    public boolean atualizarMoradorCasa(MoradorCasaDTO moradorCasaDTO){
        validarMoradorCasaDTO(moradorCasaDTO);

        MoradorCasa moradorCasa = new MoradorCasa(
                moradorCasaDTO.getMoradorId(),
                moradorCasaDTO.getCasaId(),
                moradorCasaDTO.getTipoVinculo()
        );

        try{
            return moradorCasaDAO.atualizarMoradorCasa(moradorCasa);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar morador-casa no banco de dados",e);
        }
    }

    public boolean deletarMoradorCasa(int morador_id, int casa_id){

        try{
            return  moradorCasaDAO.deletarMoradorCasa(morador_id, casa_id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar morador-casa no banco de dados",e);
        }
    }

}
