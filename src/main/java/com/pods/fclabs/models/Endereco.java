package com.pods.fclabs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Data
@ApiModel(value = "Objeto Endereço", subTypes = {Endereco.class})
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ApiModelProperty(dataType = "String", example = "xxx.xxx.xx-xx", required = true)
    private String cep;
    @ApiModelProperty(dataType = "String", example = "Cidade X", required = true)
    private String cidade;
    @ApiModelProperty(dataType = "String", example = "xx", required = true)
    private String uf;
    @ApiModelProperty(dataType = "String", example = "Rua X", required = true)
    private String logradouro;
    @ApiModelProperty(dataType = "String", example = "xxxx", required = true)
    private String numero;
    @ApiModelProperty(dataType = "String", example = "Apto xxx", required = false)
    private String complemento;
    @ApiModelProperty(hidden = true)
    private Date dtCriacao;
    @ApiModelProperty(hidden = true)
    private Date dtUltAlteracao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
