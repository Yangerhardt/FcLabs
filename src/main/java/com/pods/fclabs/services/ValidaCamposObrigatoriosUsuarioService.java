package com.pods.fclabs.services;

import java.util.UUID;

import com.pods.fclabs.dtos.UsuarioDTO;
import org.springframework.stereotype.Service;

import com.pods.fclabs.exception.CampoObrigatorioException;
import com.pods.fclabs.models.Usuario;

@Service
public class ValidaCamposObrigatoriosUsuarioService {

  
    public void validaCamposObrigatoriosUsuario(UsuarioDTO usuarioDTO) {
    
    
        validaCampo(usuarioDTO.getNome(), "Campo nome é obrigatório para cadastro de Usuario!");
        validaCampo(usuarioDTO.getNomeMae(), "Campo Nome Mãe é obrigatório para cadastro de Usuario!");

    }

    private static void validaCampo(String campo, String mensagemException) {
        if (java.util.Objects.isNull(campo) || campo.isEmpty()) {
            throw new CampoObrigatorioException(mensagemException);
        }
    }

    public void validaIdUsuario(UUID id) {
        validaCampo(id.toString(), "Informar o ID do Usuario");
    }

  

}