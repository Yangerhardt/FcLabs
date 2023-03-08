package com.pods.fclabs.mapper;

import com.pods.fclabs.dtos.EnderecoDTO;
import com.pods.fclabs.dtos.UsuarioDTO;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.models.UsuarioResponse;
import com.pods.fclabs.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    @Autowired
    private static UsuarioService service;

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
        UsuarioResponse usuarioResponse = service.findbyidUsuario(enderecoDTO.getUsuarioId());
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioResponse);
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);

        usuario.setEndereco(endereco);
        endereco.setUsuario(usuario);

        return endereco;
    }
}
