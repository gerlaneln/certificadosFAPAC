package br.acre.fapac.certificado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.DiretorDTO;
import br.acre.fapac.certificado.exception.PersistenciaException;

public class DiretorDAO {
	
	public int inserir(DiretorDTO diretorDTO) throws PersistenciaException {
		int chave = 0;

		try{
			
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql ="INSERT INTO diretor(NomeDiretor, CargoDiretor, NDecreto)"
				      + " VALUES(?,?,?);";
			
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, diretorDTO.getNomeDiretor());
		    statement.setString(2, diretorDTO.getCargoDiretor());
		    statement.setString(3, diretorDTO.getNDecreto());

			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
		       if(result.next()){
		         chave = result.getInt(1);  
		       }
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
		return chave;
	}	
	public void atualizar(DiretorDTO diretorDTO) throws PersistenciaException {
		try{
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql =  "UPDATE diretor" + " SET NomeDiretor =?," 
			+ " CargoDiretor=?," 
			+ " NDecreto =?"
			+ " WHERE IDDiretor = ?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setString(1, diretorDTO.getNomeDiretor());
			Statement.setString(2, diretorDTO.getCargoDiretor());
			Statement.setString(3, diretorDTO.getNDecreto());
			Statement.setInt(4, diretorDTO.getIDDiretor());
			
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
			String sql = "DELETE FROM fapac_certificado.diretor WHERE IDDiretor= ?;";
			
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
			String sql = "DELETE FROM diretor;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
	}
	
	
	
	
	public List<DiretorDTO> listarTodos() throws PersistenciaException {
		List<DiretorDTO> listaDiretor = new ArrayList<DiretorDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM diretor;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 DiretorDTO diretorDTO = new DiretorDTO();
				 diretorDTO.setIDDiretor(resultSet.getInt(1));
				 diretorDTO.setNomeDiretor(resultSet.getString(2));
				 diretorDTO.setCargoDiretor(resultSet.getString(3));
				 diretorDTO.setNDecreto(resultSet.getString(4));
				 
				
				 listaDiretor.add(diretorDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaDiretor;
	}
	public DiretorDTO buscarDiretorPoID(int id) throws PersistenciaException {
		 DiretorDTO diretorDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM diretor WHERE IDDiretor = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, id);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 diretorDTO = new DiretorDTO();
				 diretorDTO.setIDDiretor(resultSet.getInt(1));
				 diretorDTO.setNomeDiretor(resultSet.getString(2));
				 diretorDTO.setCargoDiretor(resultSet.getString(3));
				 diretorDTO.setNDecreto(resultSet.getString(4));

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return diretorDTO;
	}
}
