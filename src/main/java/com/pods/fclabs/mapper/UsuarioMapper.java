package com.pods.fclabs.mapper;

import com.pods.fclabs.dtos.EnderecoDTO;
import com.pods.fclabs.exception.EnderecoInexistenteException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.EnderecoResponse;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.dtos.UsuarioDTO;
import com.pods.fclabs.models.UsuarioResponse;
import com.pods.fclabs.services.EnderecoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(usuario);
    }

    public Usuario toUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setNomeMae(usuarioDTO.getNomeMae());
        if (usuarioDTO.getEndereco() == null) {
            usuario.setEndereco(null);
        } else {
            usuario.setEndereco(usuarioDTO.getEndereco());
        }

        return usuario;
    }
//
//    public UsuarioResponse toUsuarioResponse (Usuario usuario) {
//        UsuarioResponse usuarioResponse = new UsuarioResponse();
//        usuarioResponse.setId(usuario.getId());
//        usuarioResponse.setNome(usuario.getNome());
//        usuarioResponse.setNomeMae(usuario.getNomeMae());
//
//        return usuarioResponse;
//    }
}
