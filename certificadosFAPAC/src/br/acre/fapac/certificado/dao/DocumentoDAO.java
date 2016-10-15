package br.acre.fapac.certificado.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.DocumentoDTO;
import br.acre.fapac.certificado.dto.EventoDTO;
import br.acre.fapac.certificado.exception.LogicException;
import br.acre.fapac.certificado.exception.PersistenciaException;

public class DocumentoDAO {
	
	public int inserir(DocumentoDTO documentoDTO) throws PersistenciaException {
		int chave = 0;

		try{
			
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql ="INSERT INTO documento(Documento, Cpf, Evento, Data, Token)"
			+ " VALUES(?, ?, ?, ?, ?);";
			
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setObject(1, documentoDTO.getDocumento());
			statement.setString(2, documentoDTO.getCpf());
			statement.setString(3, documentoDTO.getEvento());
			statement.setString(4, documentoDTO.getData());
			statement.setString(5, documentoDTO.getToken());
						
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
	public void atualizar(DocumentoDTO documentoDTO) throws PersistenciaException {
		try{
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql =  "UPDATE documento" + " SET Documento = ?," +
			" Cpf=?,"+ " Evento=?, " +
			" Data=?" +" Token=?"+ " WHERE IDDocumento= ?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setObject(1, documentoDTO.getDocumento());
			Statement.setString(2, documentoDTO.getCpf());
			Statement.setString(3, documentoDTO.getEvento());
			Statement.setString(4, documentoDTO.getData());
			Statement.setString(5, documentoDTO.getToken());
			Statement.setInt(6, documentoDTO.getIDDocumento());
			
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
			String sql = "DELETE FROM documento WHERE IDDocumento= ?;";
			
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
			String sql = "DELETE FROM documento;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
	}
	
	
	
	
	public List<DocumentoDTO> listarTodos() throws PersistenciaException {
		List<DocumentoDTO> listaDocumentos = new ArrayList<DocumentoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM documento;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 DocumentoDTO documentoDTO = new DocumentoDTO();
				 documentoDTO.setIDDocumento(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] doc = new byte[is.available()];
				 is.read(doc);
				 documentoDTO.setDocumento(doc);
				 documentoDTO.setCpf(resultSet.getString(3));
				 documentoDTO.setEvento(resultSet.getString(4));
				 documentoDTO.setData(resultSet.getString(5));
				 documentoDTO.setToken(resultSet.getString(6));
				
				 listaDocumentos.add(documentoDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaDocumentos;
	}
	public DocumentoDTO buscarDocumentoPoID(int id) throws PersistenciaException {
		 DocumentoDTO documentoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM documento WHERE IDDocumento= ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, id);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 documentoDTO = new DocumentoDTO();
				 documentoDTO.setIDDocumento(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] doc = new byte[is.available()];
				 is.read(doc);
				 documentoDTO.setDocumento(doc);
				 documentoDTO.setCpf(resultSet.getString(3));
				 documentoDTO.setEvento(resultSet.getString(4));
				 documentoDTO.setData(resultSet.getString(5));
				 documentoDTO.setToken(resultSet.getString(6));

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return documentoDTO;
	}
	
	public List<DocumentoDTO> buscarDocumentoPorCPF(String cpf) throws PersistenciaException {
		 DocumentoDTO documentoDTO = null;
		 List<DocumentoDTO> listaDocumentos = new ArrayList<DocumentoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM documento WHERE Cpf = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, cpf);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 documentoDTO = new DocumentoDTO();
				 documentoDTO.setIDDocumento(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] doc = new byte[is.available()];
				 is.read(doc);
				 documentoDTO.setDocumento(doc);
				 documentoDTO.setCpf(resultSet.getString(3));
				 documentoDTO.setEvento(resultSet.getString(4));
				 documentoDTO.setData(resultSet.getString(5));
				 documentoDTO.setToken(resultSet.getString(6));
				 
				 listaDocumentos.add(documentoDTO);

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaDocumentos;
	}
	
	public DocumentoDTO buscarDocumentoCPF(String cpf) throws PersistenciaException {
		 DocumentoDTO documentoDTO = null;
		 
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM documento WHERE Cpf = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, cpf);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 documentoDTO = new DocumentoDTO();
				 documentoDTO.setIDDocumento(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] doc = new byte[is.available()];
				 is.read(doc);
				 documentoDTO.setDocumento(doc);
				 documentoDTO.setCpf(resultSet.getString(3));
				 documentoDTO.setEvento(resultSet.getString(4));
				 documentoDTO.setData(resultSet.getString(5));
				 documentoDTO.setToken(resultSet.getString(6));

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return documentoDTO;
	}
	
	public DocumentoDTO buscarDocumentoPorToken(String token) throws PersistenciaException {
		 DocumentoDTO documentoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM documento WHERE Token = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, token);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 documentoDTO = new DocumentoDTO();
				 documentoDTO.setIDDocumento(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] doc = new byte[is.available()];
				 is.read(doc);
				 documentoDTO.setDocumento(doc);
				 documentoDTO.setCpf(resultSet.getString(3));
				 documentoDTO.setEvento(resultSet.getString(4));
				 documentoDTO.setData(resultSet.getString(5));
				 documentoDTO.setToken(resultSet.getString(6));

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return documentoDTO;
	}
	
	public File converterPdf(byte[] documento, String nome) throws LogicException{
		File file = null;
		
		try{
			byte [] doc = documento;
				file = new File(nome+".pdf");
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(doc);
				fos.flush();
				fos.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new LogicException(e.getMessage());
		}
		return file;
	}
	
	public boolean validaDocumento(String evento, String cpf, String token) throws PersistenciaException{
		boolean validado = false;
		EventoDAO eventoDB = new EventoDAO();
		DocumentoDTO documentoLogic = new DocumentoDTO();
		
		documentoLogic = buscarDocumentoPorToken(token.toUpperCase());
		if(documentoLogic != null){
			if(documentoLogic.getEvento().equals(evento)){
				if(documentoLogic.getCpf().equals(cpf)){
					validado = true;
				}else{
					validado = false;
				}
			}else{
				validado = false;
			}
		}else{
			validado = false;
		}
		
		
		return validado;
	}
}
