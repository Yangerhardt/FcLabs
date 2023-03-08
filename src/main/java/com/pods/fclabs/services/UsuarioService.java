package com.pods.fclabs.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.pods.fclabs.dtos.UsuarioDTO;
import com.pods.fclabs.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pods.fclabs.exception.UsuarioExistenteException;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.models.UsuarioResponse;
import com.pods.fclabs.repositories.UsuarioRepository;
import com.pods.fclabs.util.Util;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private Util util;

	@Autowired
	private ValidaCamposObrigatoriosUsuarioService validaCamposObrigatorios;

	@Autowired
	private UsuarioMapper mapper;

	public UsuarioResponse salva(UsuarioDTO usuarioDTO) throws UsuarioExistenteException {
		try {

			validaCamposObrigatorios.validaCamposObrigatoriosUsuario(usuarioDTO);
			usuarioDTO.setId(UUID.randomUUID());
			Usuario usuario = mapper.toUsuario(usuarioDTO);
			usuario.setDtCriacao(Util.formatarData(new Date()));
			usuario.setDtUltAlteracao(Util.formatarData(new Date()));
			return util.converteUsuarioInResponse(usuarioRepository.save(usuario));

		} catch (UsuarioExistenteException e) {
			throw e;
		}
	}

	public UsuarioResponse atualiza(UsuarioDTO usuarioDTO) {
		validaCamposObrigatorios.validaIdUsuario(usuarioDTO.getId());
		validaCamposObrigatorios.validaCamposObrigatoriosUsuario(usuarioDTO);
		Usuario usuario = mapper.toUsuario(usuarioDTO);
		usuario.setDtUltAlteracao(Util.formatarData(new Date()));
		return util.converteUsuarioInResponse(usuarioRepository.save(usuario));
	}
	
	public UsuarioResponse findbyidUsuario(UUID id) {
		validaCamposObrigatorios.validaIdUsuario(id);
		return util.converteUsuarioInResponse(usuarioRepository.getById(id));
	}

	public List<UsuarioResponse> findAll() {
		return util.converteListUsuarioInResponse(usuarioRepository.findAll());
	}
	
	public UsuarioResponse remove(UUID id) {
		validaCamposObrigatorios.validaIdUsuario(id);
		usuarioRepository.delete(usuarioRepository.getById(id));
		return util.converteUsuarioInResponse(usuarioRepository.getById(id));
	}
	

}
