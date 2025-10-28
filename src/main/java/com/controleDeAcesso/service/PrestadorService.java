package com.controleDeAcesso.service;

import com.controleDeAcesso.dao.PrestadorDAO;
import com.controleDeAcesso.dto.PrestadorDTO;
import com.controleDeAcesso.model.Prestador;

import java.sql.SQLException;

public class PrestadorService {

    PrestadorDAO prestadorDAO;

    public PrestadorService(){
        this.prestadorDAO = new PrestadorDAO();
    }

    private PrestadorDTO toDTO(Prestador prestador){
        PrestadorDTO dto = new PrestadorDTO();

        dto.setId(prestador.getId());
        dto.setCnpj(prestador.getCnpj());
        dto.setEmpresa(prestador.getEmpresa());

        return dto;
    }

    private void validarPrestadorDTO(PrestadorDTO prestadorDTO){

        if(prestadorDTO.getCnpj() != null && prestadorDTO.getCnpj().length() != 14)
            throw new IllegalArgumentException("CPNJ inválido");

        if(prestadorDTO.getEmpresa() != null && prestadorDTO.getEmpresa().length() > 150)
            throw new IllegalArgumentException("Empresa ultrapassou a quantidade de caracteres permitido (100)");
    }

    public int incluirPrestador(PrestadorDTO prestadorDTO){

        PessoaService.validarPessoaDTO(prestadorDTO);
        validarPrestadorDTO(prestadorDTO);

        Prestador prestador = new Prestador(
                prestadorDTO.getNome(),
                prestadorDTO.getCpf(),
                prestadorDTO.getDataNasc(),
                prestadorDTO.getTelefone(),
                prestadorDTO.getEmail(),
                prestadorDTO.getCnpj(),
                prestadorDTO.getEmpresa()
        );

        try{
            return prestadorDAO.incluirPrestador(prestador);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao incluir prestador no banco de dados", e);
        }
    }

    public boolean atualizarPrestador(PrestadorDTO prestadorDTO){

        PessoaService.validarPessoaDTO(prestadorDTO);
        validarPrestadorDTO(prestadorDTO);

        Prestador prestador = new Prestador(
                prestadorDTO.getNome(),
                prestadorDTO.getCpf(),
                prestadorDTO.getDataNasc(),
                prestadorDTO.getTelefone(),
                prestadorDTO.getEmail(),
                prestadorDTO.getCnpj(),
                prestadorDTO.getEmpresa()
        );

        try{
            return prestadorDAO.atualizarPrestador(prestador);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar prestador no banco de dados", e);
        }
    }

    public boolean deletarPrestador(int prest_id){

        try{
            return prestadorDAO.deletarPrestador(prest_id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar prestador no banco de dados", e);
        }
    }

    public PrestadorDTO consultarPrestador(int prest_id){

        try{
            return toDTO(prestadorDAO.consultarPrestador(prest_id));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar prestador no banco de dados", e);
        }
    }
}
