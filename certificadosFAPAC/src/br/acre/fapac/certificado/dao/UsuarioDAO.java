package br.acre.fapac.certificado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.UsuarioDTO;
import br.acre.fapac.certificado.exception.PersistenciaException;

public class UsuarioDAO {
	
	public List<UsuarioDTO> listarLoginSenha() throws PersistenciaException {
		List<UsuarioDTO> listaLoginSenha = new ArrayList<UsuarioDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT CPFAluno as Login, SenhaAluno as Senha FROM aluno union"
					+ " SELECT CPFAjudante as Login, SenhaAjudante as Senha FROM ajudante union"
					+ " SELECT CPFOrientador as Login, SenhaOrientador as Senha FROM orientador union" 
					+ " SELECT CPFCoorientador as Login, SenhaCoorientador as Senha FROM coorientador;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 UsuarioDTO usuarioDTO = new UsuarioDTO();
				 usuarioDTO.setLogin(resultSet.getString(1));
				 usuarioDTO.setSenha(resultSet.getString(2));
	
				 listaLoginSenha.add(usuarioDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaLoginSenha;
	}

}
