package com.pods.fclabs.services;

import com.pods.fclabs.dtos.EnderecoDTO;
import com.pods.fclabs.exception.CampoObrigatorioException;
import com.pods.fclabs.models.Endereco;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ValidaCamposObrigatoriosEnderecoService {
    public void validaCamposObrigatoriosEndereco(EnderecoDTO enderecoDTO) {

        validaCampo(enderecoDTO.getCep(), "Campo cep é obrigatório para cadastro de endereço!");
        validaCampo(enderecoDTO.getUf(), "Campo uf é obrigatório para cadastro de endereço!");
        validaCampo(enderecoDTO.getCidade(), "Campo cidade é obrigatório para cadastro de endereço!");
        validaCampo(enderecoDTO.getLogradouro(), "Campo logradouro é obrigatório para cadastro de endereço!");
        validaCampo(enderecoDTO.getNumero(), "Campo numero é obrigatório para cadastro de endereço!");

    }


    private static void validaCampo(String campo, String mensagemException) {
        if (java.util.Objects.isNull(campo) || campo.isEmpty()) {
            throw new CampoObrigatorioException(mensagemException);
        }
    }

    public void validaIdEndereco(UUID id) {
        validaCampo(id.toString(), "Informar o ID do Endereco");
    }

/*    private static void validaUsuarioId(UUID usuarioId, String mensagemException) {
        if (usuarioId == null || usuarioId.toString().isEmpty()) {
            throw new CampoObrigatorioException(mensagemException);
        }
    }*/

}
