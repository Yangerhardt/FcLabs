package com.pods.fclabs.mapper;

import com.pods.fclabs.dtos.EnderecoDTO;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.EnderecoResponse;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.models.UsuarioResponse;
import com.pods.fclabs.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EnderecoMapper {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioMapper mapper;

    public EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(endereco);
    }

    public Endereco toEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setUf(enderecoDTO.getUf());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());

        Usuario usuario = service.findbyidUsuario(enderecoDTO.getUsuarioId());
        usuario.setEndereco(endereco);

        endereco.setUsuario(usuario);

        return endereco;
    }

    public EnderecoResponse toEnderecoResponse (Endereco endereco) {
        EnderecoResponse response = new EnderecoResponse();
        response.setId(endereco.getId());
        response.setCep(endereco.getCep());
        response.setCidade(endereco.getCidade());
        response.setUf(endereco.getUf());
        response.setLogradouro(endereco.getLogradouro());
        response.setNumero(endereco.getNumero());
        response.setComplemento(endereco.getComplemento());

        return response;

    }
}
