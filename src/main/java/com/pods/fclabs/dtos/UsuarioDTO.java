package com.pods.fclabs.dtos;

import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.models.UsuarioResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private UUID id;
    private String nome;
    private String nomeMae;
    private Date dtCriacao;
    private Date dtUltAlteracao;
    private UUID enderecoId;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.nomeMae = usuario.getNomeMae();
        this.enderecoId = usuario.getEndereco().getId();
    }

    public UsuarioDTO(UsuarioResponse usuarioResponse) {
        this.id = usuarioResponse.getId();
        this.nome = usuarioResponse.getNome();
        this.nomeMae = usuarioResponse.getNomeMae();
        this.enderecoId = usuarioResponse.getEndereco().getId();
    }
}
