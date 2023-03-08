package com.pods.fclabs.mapper;

import com.pods.fclabs.dtos.EnderecoDTO;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.EnderecoResponse;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.dtos.UsuarioDTO;
import com.pods.fclabs.services.EnderecoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private static EnderecoService service;

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(usuario);
    }

    public Usuario toUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setNomeMae(usuarioDTO.getNomeMae());
        EnderecoResponse enderecoResponse = service.findByIdEndereco(usuarioDTO.getEnderecoId());
        EnderecoDTO enderecoDTO = new EnderecoDTO(enderecoResponse);
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);

        endereco.setUsuario(usuario);
        usuario.setEndereco(endereco);

        return usuario;
    }
}
