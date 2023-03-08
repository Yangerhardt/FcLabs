package com.pods.fclabs.controllers;

import com.google.gson.Gson;
import com.pods.fclabs.dtos.EnderecoDTO;
import com.pods.fclabs.enums.LoggerInfoLevelEnum;
import com.pods.fclabs.exception.CampoObrigatorioException;
import com.pods.fclabs.exception.EnderecoExistenteException;
import com.pods.fclabs.exception.EnderecoInexistenteException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.EnderecoResponse;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.models.UsuarioResponse;
import com.pods.fclabs.services.EnderecoService;
import com.pods.fclabs.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/endereco")
@Api(value = "Endereco", description = "Controle de Endereços")
public class EnderecoController {

    private static final String DADOS_OBRIGATORIOS = "Obrigatório enviar os dados para cadastro conforme documentacão do Swagger";
    private static final String ENDERECO_EXISTENTE = "Já existe um endereco com estes dados";
    private static final String ENDERECO_EXISTENTE_EXCEPTION = "EnderecooExistenteException";
    private static final String ENDERECO_INEXISTENTE_EXCEPTION = "EnderecoInexistenteException";
    private static final String CAMPO_OBRIGATORIO_EXCEPTION = "CampoObrigatorioException";


    @Autowired
    private EnderecoService service;

    private Gson gson = new Gson();

    @ApiOperation(value = "salva", nickname = "Salvar Endereco")
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = EnderecoResponse.class, message = ""),
            @ApiResponse(code = 400, message = DADOS_OBRIGATORIOS),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 409, message = ENDERECO_EXISTENTE),
            @ApiResponse(code = 500, response = Exception.class ,message = "Failure")})
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> salva(@RequestBody @Valid EnderecoDTO enderecoDTO) throws Exception {
        try {
            if (enderecoDTO == null) {
                Util.registraLog(this.getClass(), CAMPO_OBRIGATORIO_EXCEPTION, "Salva", DADOS_OBRIGATORIOS, enderecoDTO, LoggerInfoLevelEnum.ERROR);

                return new ResponseEntity<>(Util.criaMsgRetorno(HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.CONFLICT.name(),
                        DADOS_OBRIGATORIOS, CAMPO_OBRIGATORIO_EXCEPTION), HttpStatus.BAD_REQUEST);
            }
            final EnderecoResponse enderecoResponse = service.salva(enderecoDTO);

            return enderecoResponse == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(enderecoResponse, HttpStatus.CREATED);
        } catch (CampoObrigatorioException coe) {
            Util.registraLog(this.getClass(), CAMPO_OBRIGATORIO_EXCEPTION, "Salva", coe.getMessage(), enderecoDTO, LoggerInfoLevelEnum.ERROR);

            return new ResponseEntity<>(Util.criaMsgRetorno(HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    coe.getMessage(), CAMPO_OBRIGATORIO_EXCEPTION), HttpStatus.BAD_REQUEST);

        } catch (EnderecoExistenteException e) {
            Util.registraLog(this.getClass(), ENDERECO_EXISTENTE_EXCEPTION, "salva", e.getMessage(), enderecoDTO, LoggerInfoLevelEnum.ERROR);

            return new ResponseEntity<>(Util.criaMsgRetorno(HttpStatus.CONFLICT.value(),
                    HttpStatus.CONFLICT.name(),
                    e.getMessage(), ENDERECO_EXISTENTE_EXCEPTION), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "atualiza", nickname = "Atualizar Endereco")
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = EnderecoResponse.class, message = ""),
            @ApiResponse(code = 400, message = DADOS_OBRIGATORIOS),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 409, message = ENDERECO_EXISTENTE),
            @ApiResponse(code = 500, response = Exception.class ,message = "Failure")})
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> atualiza(@RequestBody EnderecoDTO enderecoDTO) {
        try {
            return new ResponseEntity<>(service.atualiza(enderecoDTO), HttpStatus.OK);
        } catch (NullPointerException e) {
            Util.registraLog(this.getClass(), e.getClass().getName(), "atualiza", e.getStackTrace().toString(), enderecoDTO, LoggerInfoLevelEnum.INFO);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (EnderecoInexistenteException eie) {
            Util.registraLog(this.getClass(), ENDERECO_INEXISTENTE_EXCEPTION, "atualiza", eie.getMessage(), enderecoDTO, LoggerInfoLevelEnum.ERROR);
            return new ResponseEntity<>(Util.criaMsgRetorno(HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    eie.getMessage(), ENDERECO_INEXISTENTE_EXCEPTION ), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> desativa(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(service.remove(id), HttpStatus.OK);
        } catch (NullPointerException e) {
            Util.registraLog(this.getClass(), e.getClass().getName(), "Remove", e.getStackTrace().toString(), new Endereco(), LoggerInfoLevelEnum.ERROR);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> busca(@PathVariable UUID id) {
        try {
            final EnderecoResponse enderecoResponse = service.findByIdEndereco(id);

            if (Objects.isNull(enderecoResponse))
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
        } catch (Exception e) {
            Util.registraLog(this.getClass(), e.getClass().getName(), "busca", e.getStackTrace().toString(), new Endereco(), LoggerInfoLevelEnum.ERROR);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> buscaFindAll() {
        try {
            final List<EnderecoResponse> enderecoResponse = service.findAll();

            if (enderecoResponse == null || enderecoResponse.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
        } catch (Exception e) {
            Util.registraLog(this.getClass(), e.getClass().getName(), "busca", e.getStackTrace().toString(), new Endereco(), LoggerInfoLevelEnum.ERROR);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
