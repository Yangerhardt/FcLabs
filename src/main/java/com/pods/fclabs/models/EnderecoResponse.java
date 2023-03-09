package com.pods.fclabs.models;

import java.util.UUID;

public class EnderecoResponse {

    private UUID id;
    private String cep;
    private String cidade;
    private String uf;
    private String logradouro;
    private String numero;
    private String complemento;
    private UsuarioResponse usuarioResponse;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public UsuarioResponse getUsuarioResponse() {
        return usuarioResponse;
    }
    public void setUsuarioResponse(UsuarioResponse usuarioResponse) {
        this.usuarioResponse = usuarioResponse;
    }

}
