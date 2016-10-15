package br.acre.fapac.certificado.dao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;

import com.mysql.jdbc.Statement;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.DocumentoDTO;
import br.acre.fapac.certificado.dto.ImagemDTO;
import br.acre.fapac.certificado.dto.PatrocinadoresDTO;
import br.acre.fapac.certificado.exception.LogicException;
import br.acre.fapac.certificado.exception.PersistenciaException;

public class ImagemDAO {
	
	public int inserir(ImagemDTO imagemDTO) throws PersistenciaException {
		int chave = 0;

		try{
			
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql ="INSERT INTO patrocinador(Imagem, NomePatrocinador)"
					+ " VALUES(?, ?);";
			
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setObject(1, imagemDTO.getImagem());
			statement.setString(2, imagemDTO.getNome());
						
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
	
	public void atualizar(ImagemDTO imagemDTO) throws PersistenciaException {
		try{
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql =  "UPDATE patrocinador SET Imagem= ?," 
					+ " NomePatrocinador= ?"
					+ " WHERE IDPatrocinador= ?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setObject(1, imagemDTO.getImagem());
			Statement.setString(2, imagemDTO.getNome());
			Statement.setInt(3, imagemDTO.getIdImagem());
			
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
	
	public ImagemDTO buscarImagemPorID(int id) throws PersistenciaException {
		ImagemDTO imagemDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM patrocinadores WHERE IDPatrocinador = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, id);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 imagemDTO = new ImagemDTO();
				 imagemDTO.setIdImagem(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] img = new byte[is.available()];
				 is.read(img);
				 imagemDTO.setImagem(img);
				 imagemDTO.setNome(resultSet.getString(3));

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return imagemDTO;
	}
	
	public ImagemDTO buscarImagemPorNome(String nome) throws PersistenciaException {
		ImagemDTO imagemDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM patrocinadores WHERE NomePatrocinador = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, nome);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 imagemDTO = new ImagemDTO();
				 imagemDTO.setIdImagem(resultSet.getInt(1));
				 InputStream is = resultSet.getBinaryStream(2);
				 byte[] img = new byte[is.available()];
				 is.read(img);
				 imagemDTO.setImagem(img);
				 imagemDTO.setNome(resultSet.getString(3));

			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return imagemDTO;
	}
	
	public ImageIcon converterImagemByteToImageIcon(byte[] imagem, String nome) throws LogicException{
		ImageIcon imgIcon = null;
		Image img = null;
		
		try{
			byte [] bimg = imagem;
			InputStream is = new ByteArrayInputStream(bimg);
	        img = new ImageIcon(bimg).getImage();
	        imgIcon = new ImageIcon(img);			
		}catch(Exception e){
			e.printStackTrace();
			throw new LogicException(e.getMessage());
		}
		
		return imgIcon;
	}
	
	public void converterImagemToByte(String path, String nome) throws LogicException, Exception{
		ImagemDTO imagemDTO = new ImagemDTO();
		File img = new File(path);
	      byte[] imagem = new byte[(int)img.length()];
	      System.out.println("Lendo " + img.length() + " bytes");
	      
	      java.io.DataInputStream is = new java.io.DataInputStream(new FileInputStream(path));
	      is.readFully(imagem);
	      is.close();
	      imagemDTO.setImagem(imagem);
	      imagemDTO.setNome(nome);
	      inserir(imagemDTO);
	}

}
