package com.pods.fclabs.services;

import com.pods.fclabs.exception.CampoObrigatorioException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ValidaCamposObrigatoriosEnderecoService {
    public void validaCamposObrigatoriosEndereco(Endereco endereco) {

        validaCampo(endereco.getCep(), "Campo cep é obrigatório para cadastro de endereço!");
        validaCampo(endereco.getUf(), "Campo uf é obrigatório para cadastro de endereço!");
        validaCampo(endereco.getCidade(), "Campo cidade é obrigatório para cadastro de endereço!");
        validaCampo(endereco.getLogradouro(), "Campo logradouro é obrigatório para cadastro de endereço!");
        validaCampo(endereco.getNumero(), "Campo numero é obrigatório para cadastro de endereço!");
        validaUsuario(endereco.getUsuario());

    }


    private static void validaCampo(String campo, String mensagemException) {
        if (java.util.Objects.isNull(campo) || campo.isEmpty()) {
            throw new CampoObrigatorioException(mensagemException);
        }
    }

    public void validaIdEndereco(UUID id) {
        validaCampo(id.toString(), "Informar o ID do Endereco");
    }

    private static void validaUsuario(Usuario usuario) {
        if (usuario == null || usuario.getNome() == null || usuario.getNomeMae() == null) {
            throw new CampoObrigatorioException("Campo usuario é obrigatório para cadastro de endereço!");
        }
    }

}
