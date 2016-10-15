package br.acre.fapac.certificado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.AlunoDTO;
import br.acre.fapac.certificado.dto.CertificadoAlunoDTO;
import br.acre.fapac.certificado.dto.EventoAlunoDTO;
import br.acre.fapac.certificado.dto.ProjetoAjudanteDTO;
import br.acre.fapac.certificado.dto.ProjetoAlunoDTO;
import br.acre.fapac.certificado.dto.ProjetoCoorientadorDTO;
import br.acre.fapac.certificado.dto.ProjetoDTO;
import br.acre.fapac.certificado.dto.ProjetoOrientadorDTO;
import br.acre.fapac.certificado.exception.PersistenciaException;

public class ProjetoDAO {

	public int inserir(ProjetoDTO projetoDTO) throws PersistenciaException {
		int chave=0;
		try{

			Connection connection =  ConexaoUtil.getInstance().getConnection();

			String sql ="INSERT INTO projeto(IDProjeto, TituloProjeto)"
					+ " VALUES(?, ?);";

			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, projetoDTO.getIDProjeto());
			statement.setString(2, projetoDTO.getTituloProjeto());
			
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				chave = rs.getInt(1);
			}
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e) ;
		}
		return chave;
	}

	public void atualizar(ProjetoDTO projetoDTO) throws PersistenciaException {
		try{
			Connection connection =  ConexaoUtil.getInstance().getConnection();

			String sql =  "UPDATE projeto SET" 
			+ " TituloProjeto = ?" 
			+ " WHERE IDProjeto = ?;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setString(1, projetoDTO.getTituloProjeto());
			Statement.setInt(2, projetoDTO.getIDProjeto());

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
			String sql = "DELETE FROM projeto WHERE IDProjeto = ?;";

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
			String sql = "DELETE FROM projeto;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
	}

	public List<ProjetoDTO> listarTodos() throws PersistenciaException {
		List<ProjetoDTO> listaProjetos = new ArrayList<ProjetoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM projeto;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				ProjetoDTO projetoDTO = new ProjetoDTO();
				projetoDTO.setIDProjeto(resultSet.getInt(1));
				projetoDTO.setTituloProjeto(resultSet.getString(2));
				
				listaProjetos.add(projetoDTO);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaProjetos;
	}
	public ProjetoDTO buscarProjetoPoID(int id) throws PersistenciaException {
		ProjetoDTO projetoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM projeto WHERE IDProjeto = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				projetoDTO = new ProjetoDTO();
				projetoDTO.setIDProjeto(resultSet.getInt(1));
				projetoDTO.setTituloProjeto(resultSet.getString(2));

			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return projetoDTO;
	}
	
	//inserção da tabela Projeto_aluno
	public void inserirProjetoAluno(ProjetoAlunoDTO projetoAlunoDTO) throws PersistenciaException {
		try{
			
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql ="INSERT INTO projeto_aluno(aluno_IDAluno, projeto_IDProjeto)" 
			+ " VALUES(?, ?);";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			
			Statement.setInt(1, projetoAlunoDTO.getIdAluno());
			Statement.setInt(2, projetoAlunoDTO.getIdProjeto());
		
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
	}
	// deletar da tabela Projeto_aluno
	public void deletarProjetoAluno(Integer idProjeto, Integer idAluno) throws PersistenciaException {
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM projeto_aluno WHERE IDProjeto = ? AND IDAluno = ?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setInt(1, idProjeto);
			Statement.setInt(2, idAluno);
			
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
		
	}
	//listar todos da tabela Projeto_aluno
	public List<ProjetoAlunoDTO> listarTodosProjetoAluno() throws PersistenciaException {
		List<ProjetoAlunoDTO> listaProjetoAlunos = new ArrayList<ProjetoAlunoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM projeto_aluno;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 ProjetoAlunoDTO projetoAlunoDTO = new ProjetoAlunoDTO();
				 projetoAlunoDTO.setIdAluno(resultSet.getInt(1));
				 projetoAlunoDTO.setIdProjeto(resultSet.getInt(2));
		
				 
				 listaProjetoAlunos.add(projetoAlunoDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaProjetoAlunos;
	}
	
	public List<ProjetoAlunoDTO> listarTodosProjetoAluno(int idProjeto) throws PersistenciaException {
		List<ProjetoAlunoDTO> listaProjetoAlunos = new ArrayList<ProjetoAlunoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM fapac_certificado.projeto_aluno WHERE projeto_IDProjeto = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, idProjeto);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 ProjetoAlunoDTO projetoAlunoDTO = new ProjetoAlunoDTO();
				 projetoAlunoDTO.setIdAluno(resultSet.getInt(1));
				 projetoAlunoDTO.setIdProjeto(resultSet.getInt(2));
		
				 
				 listaProjetoAlunos.add(projetoAlunoDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaProjetoAlunos;
	}
	
	// buscar por ids tabela Projeto_aluno
	
	// luisa disse q era legal fazer um join pra trazer os atributos, 
	// querida gêh essa query fica para vc
	public int buscarProjetoAlunoPoIDP(int idProjeto) throws PersistenciaException {
		 ProjetoAlunoDTO projetoAlunoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM projeto_aluno WHERE projeto_IDProjeto = ?;";
	
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, idProjeto);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 projetoAlunoDTO = new ProjetoAlunoDTO();
				 projetoAlunoDTO.setIdAluno(resultSet.getInt(1));
				 projetoAlunoDTO.setIdProjeto(resultSet.getInt(2));
				 
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return projetoAlunoDTO.getIdAluno();
	}
	
	public int buscarProjetoAlunoPoIDA(int idAluno) throws PersistenciaException {
		 ProjetoAlunoDTO projetoAlunoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM projeto_aluno WHERE aluno_IDAluno = ?;";
	
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, idAluno);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 projetoAlunoDTO = new ProjetoAlunoDTO();
				 projetoAlunoDTO.setIdAluno(resultSet.getInt(1));
				 projetoAlunoDTO.setIdProjeto(resultSet.getInt(2));
				 
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return projetoAlunoDTO.getIdProjeto();
	}
	
	//inserção da tabela Projeto_Orientador
		public void inserirProjetoOrientador(ProjetoOrientadorDTO projetoOrientadorDTO) throws PersistenciaException {
			try{
				
				Connection connection =  ConexaoUtil.getInstance().getConnection();
				
				String sql ="INSERT INTO projeto_orientador(projeto_IDProjeto, orientador_IDOrientador)" 
				+ " VALUES(?, ?);";
				
				PreparedStatement Statement = connection.prepareStatement(sql);
				
				Statement.setInt(1, projetoOrientadorDTO.getIdProjeto());
				Statement.setInt(2, projetoOrientadorDTO.getIdOrientador());
			

				Statement.execute();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(),e ) ;
			}
		}
		// deletar da tabela Projeto_orientador
		public void deletarProjetoOrientador(Integer idProjeto, Integer idOrientador) throws PersistenciaException {
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();
				String sql = "DELETE FROM projeto_orientador WHERE projeto_IDProjeto = ?" 
						+ " AND orientador_IDOrientador = ?;";
				
				PreparedStatement Statement = connection.prepareStatement(sql);
				Statement.setInt(1, idProjeto);
				Statement.setInt(2, idOrientador);
				
				Statement.execute();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(),e);
			}
			
		}
		//listar todos da tabela Projeto_orientador
		public List<ProjetoOrientadorDTO> listarTodosProjetoOrientador() throws PersistenciaException {
			List<ProjetoOrientadorDTO> listaProjetoOrientador = new ArrayList<ProjetoOrientadorDTO>();
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();
				
				String sql = "SELECT * FROM projeto_orientador;";
				
				 PreparedStatement statement = connection.prepareStatement(sql);
				 ResultSet resultSet = statement.executeQuery();
				 
				 while(resultSet.next()){ 
					 ProjetoOrientadorDTO projetoOrientadorDTO = new ProjetoOrientadorDTO();
					 projetoOrientadorDTO.setIdProjeto(resultSet.getInt(1));
					 projetoOrientadorDTO.setIdOrientador(resultSet.getInt(2));
			
					 
					 listaProjetoOrientador.add(projetoOrientadorDTO);
				 }
				 connection.close();

			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
			return listaProjetoOrientador;
		}
		
		public List<ProjetoOrientadorDTO> listarTodosProjetoOrientador(int idProjeto) throws PersistenciaException {
			List<ProjetoOrientadorDTO> listaProjetoOrientador = new ArrayList<ProjetoOrientadorDTO>();
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();
				
				String sql = "SELECT * FROM projeto_orientador WHERE projeto_IDProjeto = ?;";
				
				 PreparedStatement statement = connection.prepareStatement(sql);
				 statement.setInt(1, idProjeto);
				 ResultSet resultSet = statement.executeQuery();
				 
				 while(resultSet.next()){ 
					 ProjetoOrientadorDTO projetoOrientadorDTO = new ProjetoOrientadorDTO();
					 projetoOrientadorDTO.setIdProjeto(resultSet.getInt(1));
					 projetoOrientadorDTO.setIdOrientador(resultSet.getInt(2));
			
					 
					 listaProjetoOrientador.add(projetoOrientadorDTO);
				 }
				 connection.close();

			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
			return listaProjetoOrientador;
		}
		// buscar por ids tabela Projeto_orientador
		
		// luisa disse q era legal fazer um join pra trazer os atributos, 
		// querida gêh essa query fica para vc
		public int buscarProjetoOrientadorPoIDP(int idProjeto) throws PersistenciaException {
			 ProjetoOrientadorDTO projetoOrientadorDTO = null;
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();
				
				String sql = "SELECT * FROM projeto_orientador WHERE projeto_IDProjeto = ?;";
				
				 PreparedStatement statement = connection.prepareStatement(sql);
				 statement.setInt(1, idProjeto);
				 ResultSet resultSet = statement.executeQuery();
				 
				 if(resultSet.next()){ 
					 projetoOrientadorDTO = new ProjetoOrientadorDTO();
					 projetoOrientadorDTO.setIdProjeto(resultSet.getInt(1));
					 projetoOrientadorDTO.setIdOrientador(resultSet.getInt(2));
					 
				 }
				 connection.close();

			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
			return projetoOrientadorDTO.getIdOrientador();
		}
		
		public int buscarProjetoOrientadorPoIDO(int idOrientador) throws PersistenciaException {
			 ProjetoOrientadorDTO projetoOrientadorDTO = null;
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();
				
				String sql = "SELECT * FROM projeto_orientador WHERE orientador_IDOrientador = ?;";
				
				 PreparedStatement statement = connection.prepareStatement(sql);
				 statement.setInt(1, idOrientador);
				 ResultSet resultSet = statement.executeQuery();
				 
				 if(resultSet.next()){ 
					 projetoOrientadorDTO = new ProjetoOrientadorDTO();
					 projetoOrientadorDTO.setIdProjeto(resultSet.getInt(1));
					 projetoOrientadorDTO.setIdOrientador(resultSet.getInt(2));
					 
				 }
				 connection.close();

			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
			return projetoOrientadorDTO.getIdProjeto();
		}
		
		
		//inserção da tabela Projeto_coorientador
				public void inserirProjetoCoorientador(ProjetoCoorientadorDTO projetoCoorientadorDTO) throws PersistenciaException {
					try{
						
						Connection connection =  ConexaoUtil.getInstance().getConnection();
						
						String sql ="INSERT INTO projeto_coorientador(projeto_IDProjeto, coorientador_IDCoorientador)" 
						+ " VALUES(?, ?);";
						
						PreparedStatement Statement = connection.prepareStatement(sql);
						
						Statement.setInt(1, projetoCoorientadorDTO.getIdProjeto());
						Statement.setInt(2, projetoCoorientadorDTO.getIdCoorientador());
					

						Statement.execute();
						connection.close();
					}catch(Exception e){
						e.printStackTrace();
						throw new PersistenciaException(e.getMessage(),e ) ;
					}
				}
				// deletar da tabela Projeto_coorientador
				public void deletarProjetoCoorientador(Integer idProjeto, Integer idCoorientador) throws PersistenciaException {
					try{
						Connection connection = ConexaoUtil.getInstance().getConnection();
						String sql = "DELETE FROM projeto_coorientador WHERE projeto_IDProjeto = ?"
								+ " AND coorientador_IDCoorientador = ?;";
						
						PreparedStatement Statement = connection.prepareStatement(sql);
						Statement.setInt(1, idProjeto);
						Statement.setInt(2, idCoorientador);
						
						Statement.execute();
						connection.close();
					}catch(Exception e){
						e.printStackTrace();
						throw new PersistenciaException(e.getMessage(),e);
					}
					
				}
				//listar todos da tabela Projeto_coorientador
				public List<ProjetoCoorientadorDTO> listarTodosProjetoCoorientador() throws PersistenciaException {
					List<ProjetoCoorientadorDTO> listaProjetoCoorientador = new ArrayList<ProjetoCoorientadorDTO>();
					try{
						Connection connection = ConexaoUtil.getInstance().getConnection();
						
						String sql = "SELECT * FROM projeto_coorientador;";
						
						 PreparedStatement statement = connection.prepareStatement(sql);
						 ResultSet resultSet = statement.executeQuery();
						 
						 while(resultSet.next()){ 
							 ProjetoCoorientadorDTO projetoCoorientadorDTO = new ProjetoCoorientadorDTO();
							 projetoCoorientadorDTO.setIdProjeto(resultSet.getInt(1));
							 projetoCoorientadorDTO.setIdCoorientador(resultSet.getInt(2));
					
							 
							 listaProjetoCoorientador.add(projetoCoorientadorDTO);
						 }
						 connection.close();

					}catch(Exception e){
						e.printStackTrace();
						throw new PersistenciaException(e.getMessage(), e);
					}
					return listaProjetoCoorientador;
				}
				
				public List<ProjetoCoorientadorDTO> listarTodosProjetoCoorientador(int idProjeto) throws PersistenciaException {
					List<ProjetoCoorientadorDTO> listaProjetoCoorientador = new ArrayList<ProjetoCoorientadorDTO>();
					try{
						Connection connection = ConexaoUtil.getInstance().getConnection();
						
						String sql = "SELECT * FROM projeto_coorientador WHERE projeto_IDProjeto = ?;";
						
						 PreparedStatement statement = connection.prepareStatement(sql);
						 statement.setInt(1, idProjeto);
						 ResultSet resultSet = statement.executeQuery();
						 
						 while(resultSet.next()){ 
							 ProjetoCoorientadorDTO projetoCoorientadorDTO = new ProjetoCoorientadorDTO();
							 projetoCoorientadorDTO.setIdProjeto(resultSet.getInt(1));
							 projetoCoorientadorDTO.setIdCoorientador(resultSet.getInt(2));
					
							 
							 listaProjetoCoorientador.add(projetoCoorientadorDTO);
						 }
						 connection.close();

					}catch(Exception e){
						e.printStackTrace();
						throw new PersistenciaException(e.getMessage(), e);
					}
					return listaProjetoCoorientador;
				}
				// buscar por ids tabela Projeto_coorientador
				
				// luisa disse q era legal fazer um join pra trazer os atributos, 
				// querida gêh essa query fica para vc
				public int buscarProjetoCoorientadorPoIDP(int idProjeto) throws PersistenciaException {
					 ProjetoCoorientadorDTO projetoCoorientadorDTO = null;
					try{
						Connection connection = ConexaoUtil.getInstance().getConnection();
						
						String sql = "SELECT * FROM fapac_certificado.projeto_coorientador WHERE projeto_IDProjeto = ?;";
						
						 PreparedStatement statement = connection.prepareStatement(sql);
						 statement.setInt(1, idProjeto);
						 ResultSet resultSet = statement.executeQuery();
						 
						 if(resultSet.next()){ 
							 projetoCoorientadorDTO = new ProjetoCoorientadorDTO();
							 projetoCoorientadorDTO.setIdProjeto(resultSet.getInt(1));
							 projetoCoorientadorDTO.setIdCoorientador(resultSet.getInt(2));
							 
						 }
						 connection.close();

					}catch(Exception e){
						e.printStackTrace();
						throw new PersistenciaException(e.getMessage(), e);
					}
					return projetoCoorientadorDTO.getIdCoorientador();
				}
				
				public int buscarProjetoCoorientadorPoIDC(int idCoorientador) throws PersistenciaException {
					 ProjetoCoorientadorDTO projetoCoorientadorDTO = null;
					try{
						Connection connection = ConexaoUtil.getInstance().getConnection();
						
						String sql = "SELECT * FROM projeto_coorientador WHERE coorientador_IDCoorientador = ?;";
						
						 PreparedStatement statement = connection.prepareStatement(sql);
						 statement.setInt(1, idCoorientador);
						 ResultSet resultSet = statement.executeQuery();
						 
						 if(resultSet.next()){ 
							 projetoCoorientadorDTO = new ProjetoCoorientadorDTO();
							 projetoCoorientadorDTO.setIdProjeto(resultSet.getInt(1));
							 projetoCoorientadorDTO.setIdCoorientador(resultSet.getInt(2));
							 
						 }
						 connection.close();

					}catch(Exception e){
						e.printStackTrace();
						throw new PersistenciaException(e.getMessage(), e);
					}
					return projetoCoorientadorDTO.getIdProjeto();
				}
				
//  NÃO EXISTE TABELA PROJETO_AJUDANTE VISTO QUE UM AJUDANTE SO AJUDA EM EVENTO E NÃO EM PROJETOS ESPECÍFICOS
//				//inserção da tabela Projeto_ajudante
//				public void inserirProjetoAjudante(ProjetoAjudanteDTO projetoAjudanteDTO) throws PersistenciaException {
//					try{
//						
//						Connection connection =  ConexaoUtil.getInstance().getConnection();
//						
//						String sql ="INSERT INTO projeto_ajudante(projeto_IDProjeto, ajudante_IDAjudante)" 
//						+ " VALUES(?, ?);";
//						
//						PreparedStatement Statement = connection.prepareStatement(sql);
//						
//						Statement.setInt(1, projetoAjudanteDTO.getIdProjeto());
//						Statement.setInt(2, projetoAjudanteDTO.getIdAjudante());
//					
//
//						Statement.execute();
//						connection.close();
//					}catch(Exception e){
//						e.printStackTrace();
//						throw new PersistenciaException(e.getMessage(),e ) ;
//					}
//				}
//				// deletar da tabela Projeto_ajudante
//				public void deletarProjetoAjudante(Integer idProjeto, Integer idAjudante) throws PersistenciaException {
//					try{
//						Connection connection = ConexaoUtil.getInstance().getConnection();
//						String sql = "DELETE FROM projeto_ajudante WHERE projeto_IDProjeto = ?" 
//								+ " AND ajudante_IDAjudante = ?;";
//						
//						PreparedStatement Statement = connection.prepareStatement(sql);
//						Statement.setInt(1, idProjeto);
//						Statement.setInt(2, idAjudante);
//						
//						Statement.execute();
//						connection.close();
//					}catch(Exception e){
//						e.printStackTrace();
//						throw new PersistenciaException(e.getMessage(), e);
//					}
//					
//				}
//				//listar todos da tabela Projeto_ajudante
//				public List<ProjetoAjudanteDTO> listarTodosProjetoAjudante() throws PersistenciaException {
//					List<ProjetoAjudanteDTO> listaProjetoAjudantes = new ArrayList<ProjetoAjudanteDTO>();
//					try{
//						Connection connection = ConexaoUtil.getInstance().getConnection();
//						
//						String sql = "SELECT * FROM projeto_ajudante;";
//						
//						 PreparedStatement statement = connection.prepareStatement(sql);
//						 ResultSet resultSet = statement.executeQuery();
//						 
//						 while(resultSet.next()){ 
//							 ProjetoAjudanteDTO projetoAjudanteDTO = new ProjetoAjudanteDTO();
//							 projetoAjudanteDTO.setIdProjeto(resultSet.getInt(1));
//							 projetoAjudanteDTO.setIdAjudante(resultSet.getInt(2));
//					
//							 
//							 listaProjetoAjudantes.add(projetoAjudanteDTO);
//						 }
//						 connection.close();
//
//					}catch(Exception e){
//						e.printStackTrace();
//						throw new PersistenciaException(e.getMessage(), e);
//					}
//					return listaProjetoAjudantes;
//				}
//				// buscar por ids tabela Projeto_ajudante
//				
//				// luisa disse q era legal fazer um join pra trazer os atributos, 
//				// querida gêh essa query fica para vc
//				public int buscarProjetoAjudantePoIDP(int idProjeto) throws PersistenciaException {
//					 ProjetoAjudanteDTO projetoAjudanteDTO = null;
//					try{
//						Connection connection = ConexaoUtil.getInstance().getConnection();
//						
//						String sql = "SELECT * FROM projeto_ajudante WHERE projeto_IDProjeto = ?;";
//						
//						 PreparedStatement statement = connection.prepareStatement(sql);
//						 statement.setInt(1, idProjeto);
//						 ResultSet resultSet = statement.executeQuery();
//						 
//						 if(resultSet.next()){ 
//							 projetoAjudanteDTO = new ProjetoAjudanteDTO();
//							 projetoAjudanteDTO.setIdProjeto(resultSet.getInt(1));
//							 projetoAjudanteDTO.setIdAjudante(resultSet.getInt(2));
//							 
//						 }
//						 connection.close();
//
//					}catch(Exception e){
//						e.printStackTrace();
//						throw new PersistenciaException(e.getMessage(), e);
//					}
//					return projetoAjudanteDTO.getIdAjudante();
//				}	
//				
//				public int buscarProjetoAjudantePoIDA(int idAjudante) throws PersistenciaException {
//					 ProjetoAjudanteDTO projetoAjudanteDTO = null;
//					try{
//						Connection connection = ConexaoUtil.getInstance().getConnection();
//						
//						String sql = "SELECT * FROM projeto_ajudante WHERE ajudante_IDAjudante = ?;";
//						
//						 PreparedStatement statement = connection.prepareStatement(sql);
//						 statement.setInt(1, idAjudante);
//						 ResultSet resultSet = statement.executeQuery();
//						 
//						 if(resultSet.next()){ 
//							 projetoAjudanteDTO = new ProjetoAjudanteDTO();
//							 projetoAjudanteDTO.setIdProjeto(resultSet.getInt(1));
//							 projetoAjudanteDTO.setIdAjudante(resultSet.getInt(2));
//							 
//						 }
//						 connection.close();
//
//					}catch(Exception e){
//						e.printStackTrace();
//						throw new PersistenciaException(e.getMessage(), e);
//					}
//					return projetoAjudanteDTO.getIdProjeto();
//				}	
}
