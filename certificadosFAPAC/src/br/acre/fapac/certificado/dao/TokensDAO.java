package br.acre.fapac.certificado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.TokensDTO;
import br.acre.fapac.certificado.exception.PersistenciaException;

public class TokensDAO {
	public int inserir(TokensDTO tokensDTO) throws PersistenciaException {
		int chave = 0;

		try{
			
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql ="INSERT INTO tokens(Token)"
			+ " VALUES(?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, tokensDTO.getToken());
			
			statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
		return chave;
	}
	/* confirir se a chave primaria vai ser login , pois se for não tem como atualizar o login do adm
	 * 
	 */
//	public void atualizar(TokensDTO tokensDTO) throws PersistenciaException {
//		try{
//			Connection connection =  ConexaoUtil.getInstance().getConnection();
//			
//			String sql =  "UPDATE TOKENS " + "SET S =? " 
//			+ "WHERE LOGIN = ?";
//			
//			PreparedStatement Statement = connection.prepareStatement(sql);
//			Statement.setString(1, tokensDTO.getSenha());
//			Statement.setString(3, tokensDTO.getLogin());
//			
//			Statement.execute();
//			connection.close();	
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new PersistenciaException(e.getMessage(), e);
//		}
//	}
	
//	public void deletar(Integer id) throws PersistenciaException {
//		try{
//			Connection connection = ConexaoUtil.getInstance().getConnection();
//			String sql = "DELETE FROM ADMINISTARDOR WHERE LOGIN = ?";
//			
//			PreparedStatement Statement = connection.prepareStatement(sql);
//			Statement.setInt(1, id);
//			Statement.execute();
//			connection.close();
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new PersistenciaException(e.getMessage(),e);
//		}
//		
//	}
//	public void deletartudo() throws PersistenciaException{
//		try{
//			Connection connection = ConexaoUtil.getInstance().getConnection();
//			String sql = "DELETE FROM ADMINISTRADOR";
//			
//			PreparedStatement Statement = connection.prepareStatement(sql);
//			Statement.execute();
//			connection.close();
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new PersistenciaException(e.getMessage(),e);
//		}
//	}
	public List<TokensDTO> listarTodos() throws PersistenciaException {
		List<TokensDTO> listaTokens = new ArrayList<TokensDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM tokens;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 TokensDTO TokensDTO = new TokensDTO();
				 TokensDTO.setToken(resultSet.getString(1)); 
				
				 listaTokens.add(TokensDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaTokens;
	}
	public TokensDTO buscarTokensPorToken(String token) throws PersistenciaException {
		 TokensDTO tokensDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM tokens WHERE Token = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, token);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 tokensDTO = new TokensDTO();
				 tokensDTO.setToken(resultSet.getString(1)); 

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return tokensDTO;
	}
	
	public static String criaTokens() {

		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String palavra = "";

		Random random = new Random();

		int index = -1;
		{
			for (int i = 0; i < 10; i++) {
				String armazenaChaves = "";
				Random random1 = new Random();
				int x = random1.nextInt(10);
				String y = Integer.toString(x);
				palavra = palavra + y;
				index = random.nextInt(letras.length());
				armazenaChaves = letras.substring(index, index + 1);
				palavra = palavra + armazenaChaves;
			}

		//	String sempontos = palavra;

			StringBuilder stringBuilder = new StringBuilder(palavra);
			stringBuilder.insert(palavra.length() - 16, '.');
			stringBuilder.insert(palavra.length() - 11, '.');
			stringBuilder.insert(palavra.length() - 6, '.');
			stringBuilder.insert(palavra.length() - 1, '.');

			String pontos = stringBuilder.toString();
			return pontos;

		}

	}
}
