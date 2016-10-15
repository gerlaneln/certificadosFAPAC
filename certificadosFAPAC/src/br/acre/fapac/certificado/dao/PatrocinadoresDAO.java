package br.acre.fapac.certificado.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.PatrocinadoresDTO;
import br.acre.fapac.certificado.exception.PersistenciaException;

public class PatrocinadoresDAO {
	public int inserir(PatrocinadoresDTO patrocinadoresDTO) throws PersistenciaException {
		int chave = 0;

		try{
			
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql ="INSERT INTO patrocinador(Imagem, NomePatrocinador)"
			+ " VALUES(?, ?);";
			
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setObject(1, patrocinadoresDTO.getImagem());
			statement.setString(2, patrocinadoresDTO.getNomePatrocinador());
					
			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
		       if(result.next()){
		         chave = result.getInt(1);  
		       }
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e) ;
		}
		return chave;
	}	
	public void atualizar(PatrocinadoresDTO patrocinadoresDTO) throws PersistenciaException {
		try{
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql =  "UPDATE patrocinador SET Imagem= ?," 
			+ " NomePatrocinador= ?"
			+ " WHERE IDPatrocinador= ?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setObject(1, patrocinadoresDTO.getImagem());
			Statement.setString(2, patrocinadoresDTO.getNomePatrocinador());
			Statement.setInt(3, patrocinadoresDTO.getIdPatrocinadores());

			
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
			String sql = "DELETE FROM patrocinador WHERE IDPatrocinador = ?;";
			
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
			String sql = "DELETE FROM patrocinador;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
	}
	
	
	
	
	public List<PatrocinadoresDTO> listarTodos() throws PersistenciaException {
		List<PatrocinadoresDTO> listaPatrocinadores = new ArrayList<PatrocinadoresDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM patrocinador;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 PatrocinadoresDTO patrocinadoresDTO = new PatrocinadoresDTO();
				 patrocinadoresDTO.setIdPatrocinadores(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] img = new byte[is.available()];
				 is.read(img);
				 patrocinadoresDTO.setImagem(img);
				 patrocinadoresDTO.setNomePatrocinador(resultSet.getString(3));
				 
				 listaPatrocinadores.add(patrocinadoresDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaPatrocinadores;
	}
	public PatrocinadoresDTO buscarPatrocinadoresPoID(int id) throws PersistenciaException {
		PatrocinadoresDTO patrocinadoresDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM patrocinadores WHERE IDPatrocinador = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, id);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 patrocinadoresDTO = new PatrocinadoresDTO();
				 patrocinadoresDTO.setIdPatrocinadores(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] img = new byte[is.available()];
				 is.read(img);
				 patrocinadoresDTO.setImagem(img);
				 patrocinadoresDTO.setNomePatrocinador(resultSet.getString(3));

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return patrocinadoresDTO;
	}
}
