package br.acre.fapac.certificado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.AdministradorDTO;
import br.acre.fapac.certificado.exception.PersistenciaException;

public class AdministradorDAO {
	public void inserir(AdministradorDTO administradorDTO) throws PersistenciaException {
		try{
			
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql ="INSERT INTO administrador(login, senha)"
			+ " VALUES(?, ?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,"'%" + administradorDTO.getLogin() + "%'" );
			statement.setString(2, "%" + administradorDTO.getSenha() + "%");
			
			statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
	}
	/* confirir se a chave primaria vai ser login , pois se for não tem como atualizar o login do adm
	 * 
	 */
	public void atualizar(AdministradorDTO administardorDTO) throws PersistenciaException {
		try{
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql =  "UPDATE administrador SET Senha = ? WHERE login = ?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setString(1, administardorDTO.getSenha());
			Statement.setString(2, administardorDTO.getLogin());
			
			Statement.execute();
			connection.close();	
			
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	public void deletar(Integer id) throws PersistenciaException {
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM administrador WHERE login = ?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setInt(1, id);
			Statement.execute();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
		
	}
	public void deletartudo() throws PersistenciaException{
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM administrador;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
	}
	public List<AdministradorDTO> listarTodos() throws PersistenciaException {
		List<AdministradorDTO> listaAdms = new ArrayList<AdministradorDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM administrador;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 AdministradorDTO administardorDTO = new AdministradorDTO();
				 administardorDTO.setLogin(resultSet.getString(1));
				 administardorDTO.setSenha(resultSet.getString(2));

		
				
				 listaAdms.add(administardorDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaAdms;
	}
	public AdministradorDTO buscarAdmPoID(String login) throws PersistenciaException {
		 AdministradorDTO administardorDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM administrador WHERE login LIKE ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1,"'%" + login + "%'" );
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 administardorDTO = new AdministradorDTO();
				 administardorDTO.setLogin(resultSet.getString(1));
				 administardorDTO.setSenha(resultSet.getString(2));

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return administardorDTO;
	}
	
	public AdministradorDTO buscarPorAdm(String login) throws PersistenciaException {
	    AdministradorDTO admDTO = null;
	    try{
	      Connection connection = ConexaoUtil.getInstance().getConnection();

	      String sql ="SELECT * FROM administrador WHERE login LIKE ?;";
	      PreparedStatement statement =  connection.prepareStatement(sql);
	      statement.setString(1, "%" + login +"%");
	      ResultSet resultSet = statement.executeQuery();
	      if(resultSet.next()){
	        admDTO = new AdministradorDTO();
	        admDTO.setLogin(resultSet.getString(1));
	        admDTO.setSenha(resultSet.getString(2));
	      }
	      connection.close();
	    }catch(Exception e){
	      e.printStackTrace();
	      throw new PersistenciaException(e.getMessage(), e);
	    }
	    return admDTO;
	  }
	
}
