package com.pods.fclabs.services;

import com.pods.fclabs.dtos.EnderecoDTO;
import com.pods.fclabs.exception.EnderecoExistenteException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.EnderecoResponse;
import com.pods.fclabs.repositories.EnderecoRepository;
import com.pods.fclabs.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    private Util util;

    @Autowired
    ValidaCamposObrigatoriosEnderecoService validaCamposObrigatorios;

    public EnderecoResponse salva(EnderecoDTO enderecoDTO) throws EnderecoExistenteException {
        try {
            validaCamposObrigatorios.validaCamposObrigatoriosEndereco(enderecoDTO);
            enderecoDTO.setId(UUID.randomUUID());
            Endereco endereco = util.converterParaEndereco(enderecoDTO);
            endereco.setDtCriacao(Util.formatarData((new Date())));
            endereco.setDtUltAlteracao(Util.formatarData((new Date())));
            return util.converteEnderecoInResponse(enderecoRepository.save(endereco));

        } catch (EnderecoExistenteException e) {
            throw e;
        }
    }

    public EnderecoResponse atualiza (EnderecoDTO enderecoDTO) {
        validaCamposObrigatorios.validaIdEndereco(enderecoDTO.getId());
        validaCamposObrigatorios.validaCamposObrigatoriosEndereco(enderecoDTO);
        Endereco endereco = util.converterParaEndereco(enderecoDTO);
        endereco.setDtUltAlteracao(Util.formatarData((new Date())));
        return util.converteEnderecoInResponse(enderecoRepository.save(endereco));
    }

    public EnderecoResponse findByIdEndereco (UUID id) {
        validaCamposObrigatorios.validaIdEndereco(id);
        return util.converteEnderecoInResponse(enderecoRepository.getById(id));
    }

    public List<EnderecoResponse> findAll() {
        return util.converteListEnderecoInResponse(enderecoRepository.findAll());
    }

    public void remove(UUID id) {
        validaCamposObrigatorios.validaIdEndereco(id);
        enderecoRepository.deleteById(id);
    }
}
