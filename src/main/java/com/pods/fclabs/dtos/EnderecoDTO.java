package com.pods.fclabs.dtos;


import com.pods.fclabs.models.Endereco;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EnderecoDTO {

    private UUID id;
    @NotBlank
    private String cep;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotNull
    private UUID usuarioId;

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.usuarioId = endereco.getUsuario().getId();
    }

/*    public EnderecoDTO(EnderecoResponse enderecoResponse) {
        this.id = enderecoResponse.getId();
        this.cep = enderecoResponse.getCep();
        this.cidade = enderecoResponse.getCidade();
        this.uf = enderecoResponse.getUf();
        this.logradouro = enderecoResponse.getLogradouro();
        this.numero = enderecoResponse.getNumero();
        this.complemento = enderecoResponse.getComplemento();
    }*/

}
