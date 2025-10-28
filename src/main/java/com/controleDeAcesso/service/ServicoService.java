package com.controleDeAcesso.service;

import com.controleDeAcesso.dao.ServicoDAO;
import com.controleDeAcesso.dto.ServicoDTO;
import com.controleDeAcesso.model.Servico;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoService {

    private ServicoDAO servicoDAO;

    public ServicoService(){
        this.servicoDAO = new ServicoDAO();
    }

    private ServicoDTO toDTO(Servico servico){
        ServicoDTO dto = new ServicoDTO();

        dto.setMorId(servico.getMorId());
        dto.setPrestId(servico.getPrestId());
        dto.setServTipo(servico.getServTipo());
        dto.setDataInicio(servico.getDataInicio());
        dto.setDataFim(servico.getDataFim());

        return dto;
    }

    private void validarServicoDTO(ServicoDTO servicoDTO){

        if(servicoDTO.getDataInicio() == null)
            throw new IllegalArgumentException("Data de início inválida");
    }

    public int incluirServico(ServicoDTO servicoDTO){
        validarServicoDTO(servicoDTO);

        Servico servico = new Servico(
                servicoDTO.getMorId(),
                servicoDTO.getPrestId(),
                servicoDTO.getServTipo(),
                servicoDTO.getDataInicio(),
                servicoDTO.getDataFim()
        );

        try{
            return servicoDAO.incluirServico(servico);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao incluir serviço no banco de dados", e);
        }
    }

    public boolean atualizarServico(ServicoDTO servicoDTO){
        validarServicoDTO(servicoDTO);

        Servico servico = new Servico(
                servicoDTO.getMorId(),
                servicoDTO.getPrestId(),
                servicoDTO.getServTipo(),
                servicoDTO.getDataInicio(),
                servicoDTO.getDataFim()
        );

        try{
            return servicoDAO.atualizarServico(servico);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço no banco de dados", e);
        }
    }

    public boolean deletarServico(int serv_id){

        try{
            return servicoDAO.deletarServico(serv_id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar serviço do banco de dados", e);
        }
    }

    public List<ServicoDTO> consultarServicosGeral(){

        try{
            return servicoDAO.consultarServicosGeral()
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar servicos geral no banco de dados", e);
        }
    }

    public List<ServicoDTO> consultarServicosPorMorador(int mor_id){

        try{
            return servicoDAO.consultarServicosPorMorador(mor_id)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar servicos por morador no banco de dados", e);
        }
    }

    public List<ServicoDTO> consultarServicosPorPrestador(int pres_id){

        try{
            return servicoDAO.consultarServicosPorPrestador(pres_id)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar servicos por prestador no banco de dados", e);
        }
    }
}
