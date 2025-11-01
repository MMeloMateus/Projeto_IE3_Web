package com.controleDeAcesso.service;

import com.controleDeAcesso.dao.AcessoDAO;
import com.controleDeAcesso.dto.AcessoDTO;
import com.controleDeAcesso.model.Acesso;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcessoService {

    private AcessoDAO acessoDAO;

    public AcessoService(){
        this.acessoDAO = new AcessoDAO();
    }

    private AcessoDTO toDTO(Acesso acesso){
        AcessoDTO dto = new AcessoDTO();

        dto.setId(acesso.getId());
        dto.setPesId(acesso.getPesId());
        dto.setLocId(acesso.getLocId());
        dto.setData(acesso.getData());
        dto.setTipoAcesso(acesso.getTipoAcesso());
        dto.setStatusAcesso(acesso.getStatusAcesso());

        return dto;
    }

    private void validarAcessoDTO(AcessoDTO acessoDTO){
        if(acessoDTO.getData() == null) throw new IllegalArgumentException("Data inválida");

        if(acessoDTO.getTipoAcesso() == null) throw new IllegalArgumentException("Tipo de Acesso inválido");

        if(acessoDTO.getStatusAcesso() == null) throw new IllegalArgumentException("Status do Acesso inválido");
    }

    public int incluirAcesso(AcessoDTO acessoDTO){

        validarAcessoDTO(acessoDTO);

        Acesso acesso = new Acesso(
                acessoDTO.getPesId(),
                acessoDTO.getLocId(),
                acessoDTO.getData(),
                acessoDTO.getTipoAcesso(),
                acessoDTO.getStatusAcesso()
        );

        try {
            return acessoDAO.incluirAcesso(acesso);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao incluir acesso  banco de dados", e);
        }
    }

    public AcessoDTO consultarAcesso(int id){
        try {
            return toDTO(acessoDAO.consultarAcesso(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AcessoDTO> consultarAcessosOrderData(){

        try {
            return acessoDAO.consultarAcessosOrderData()
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar acessos ordenados por data no banco de dados", e);
        }

    }

    public List<AcessoDTO> consultarAcessosPorLocal(int local_id){

        try{
            return acessoDAO.consultarAcessosPorLocal(local_id)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar acessos por local no banco de dados", e);
        }
    }

    public List<AcessoDTO> consultarAcessosPorPessoa(int pessoa_id){
        try{
            return acessoDAO.consultarAcessosPorPessoa(pessoa_id)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar acessos por pessoa no banco de dados", e);
        }
    }

}
