package br.acre.fapac.certificado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.EventoAjudanteDTO;
import br.acre.fapac.certificado.dto.EventoAlunoDTO;
import br.acre.fapac.certificado.dto.EventoCoorientadorDTO;
import br.acre.fapac.certificado.dto.EventoDTO;
import br.acre.fapac.certificado.dto.EventoOrientadorDTO;
import br.acre.fapac.certificado.dto.EventoProjetoDTO;
import br.acre.fapac.certificado.exception.PersistenciaException;


public class EventoDAO {

	public void inserir(EventoDTO eventoDTO) throws PersistenciaException {
		try{

			Connection connection =  ConexaoUtil.getInstance().getConnection();

			String sql ="INSERT INTO evento (NomeEvento, Patrocinadores, CargaHoraria, NomePrograma, CidadeEvento, EstadoEvento,"
					+ " DataInicio, DataFinal)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement Statement = connection.prepareStatement(sql);

			Statement.setString(1, eventoDTO.getNomeEvento());
			Statement.setString(2, eventoDTO.getPatrocinadores());
			Statement.setString(3, eventoDTO.getCargaHoraria());
			Statement.setString(4, eventoDTO.getNomePrograma());
			Statement.setString(5, eventoDTO.getCidadeEvento());
			Statement.setString(6, eventoDTO.getEstadoEvento());
			Statement.setString(7, eventoDTO.getDataInicio());
			Statement.setString(8, eventoDTO.getDataFinal());


			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
	}


	public void atualizar(EventoDTO eventoDTO) throws PersistenciaException {
		try{
			Connection connection =  ConexaoUtil.getInstance().getConnection();

			String sql =  "UPDATE evento " + "SET NomeEvento = ?,"
			+ " Patrocinadores =?,"  
			+ " CargaHoraria =?," 
			+ " NomePrograma =?,"
			+ " CidadeEvento =?,"
			+ " EstadoEvento =?,"
			+ " DataInicio = ?,"
			+ " DataFinal = ?"
			+ " WHERE IDEvento = ?;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setString(1, eventoDTO.getNomeEvento());
			Statement.setString(2, eventoDTO.getPatrocinadores());
			Statement.setString(3, eventoDTO.getCargaHoraria());
			Statement.setString(4, eventoDTO.getNomePrograma());
			Statement.setString(5, eventoDTO.getCidadeEvento());
			Statement.setString(6, eventoDTO.getEstadoEvento());
			Statement.setString(7, eventoDTO.getDataInicio());
			Statement.setString(8, eventoDTO.getDataFinal());

			Statement.setInt(9, eventoDTO.getIDEvento());

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
			String sql = "DELETE FROM evento WHERE IDEvento = ?;";

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
			String sql = "DELETE FROM evento;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
	}

	public List<EventoDTO> listarTodos() throws PersistenciaException {
		List<EventoDTO> listaEventos = new ArrayList<EventoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				EventoDTO eventoDTO = new EventoDTO();
				eventoDTO.setIDEvento(resultSet.getInt(1));
				eventoDTO.setNomeEvento(resultSet.getString(2));
				eventoDTO.setPatrocinadores(resultSet.getString(3));
				eventoDTO.setCargaHoraria(resultSet.getString(4));
				eventoDTO.setNomePrograma(resultSet.getString(5));
				eventoDTO.setCidadeEvento(resultSet.getString(6));
				eventoDTO.setEstadoEvento(resultSet.getString(7));
				eventoDTO.setDataInicio(resultSet.getString(8));
				eventoDTO.setDataFinal(resultSet.getString(9));



				listaEventos.add(eventoDTO);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaEventos;
	}

	public EventoDTO buscarEventoPoID(int id) throws PersistenciaException {
		EventoDTO eventoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento WHERE IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				eventoDTO = new EventoDTO();
				eventoDTO.setIDEvento(resultSet.getInt(1));
				eventoDTO.setNomeEvento(resultSet.getString(2));
				eventoDTO.setPatrocinadores(resultSet.getString(3));
				eventoDTO.setCargaHoraria(resultSet.getString(4));
				eventoDTO.setNomePrograma(resultSet.getString(5));
				eventoDTO.setCidadeEvento(resultSet.getString(6));
				eventoDTO.setEstadoEvento(resultSet.getString(7));
				eventoDTO.setDataInicio(resultSet.getString(8));
				eventoDTO.setDataFinal(resultSet.getString(9));


			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoDTO;
	}


	//inserção da tabela evento_aluno
	public void inserirEventoAluno(EventoAlunoDTO eventoAlunoDTO) throws PersistenciaException {
		try{

			Connection connection =  ConexaoUtil.getInstance().getConnection();

			String sql ="INSERT INTO evento_aluno(aluno_IDAluno, evento_IDEvento)" + " VALUES(?, ?);";

			PreparedStatement Statement = connection.prepareStatement(sql);

			Statement.setInt(1, eventoAlunoDTO.getIdAluno());
			Statement.setInt(2, eventoAlunoDTO.getIdEvento());


			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
	}
	// deletar da tabela evento_aluno
	public void deletarEventoAluno(Integer idEvento, Integer idAluno) throws PersistenciaException {
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM evento_aluno WHERE evento_IDEvento = ? AND aluno_IDAluno = ?;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setInt(1, idEvento);
			Statement.setInt(2, idAluno);

			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}

	}
	//listar todos da tabela Evento_aluno
	public List<EventoAlunoDTO> listarTodosEventoAluno() throws PersistenciaException {
		List<EventoAlunoDTO> listaEventosAlunos = new ArrayList<EventoAlunoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_aluno;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				EventoAlunoDTO eventoAlunoDTO = new EventoAlunoDTO();
				eventoAlunoDTO.setIdAluno(resultSet.getInt(1));
				eventoAlunoDTO.setIdEvento(resultSet.getInt(2));


				listaEventosAlunos.add(eventoAlunoDTO);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaEventosAlunos;
	}
	// buscar por ids tabela Evento_aluno

	// luisa disse q era legal fazer um join pra trazer os atributos, 
	// querida gêh essa query fica para vc
	public int buscarEventoAlunoPoIDE(int idEvento) throws PersistenciaException {
		EventoAlunoDTO eventoAlunoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_aluno WHERE evento_IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEvento);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				eventoAlunoDTO = new EventoAlunoDTO();
				eventoAlunoDTO.setIdAluno(resultSet.getInt(1));
				eventoAlunoDTO.setIdEvento(resultSet.getInt(2));

			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoAlunoDTO.getIdAluno();
	}
	
	public int buscarEventoAlunoPoIDA(int idAluno) throws PersistenciaException {
		EventoAlunoDTO eventoAlunoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_aluno WHERE aluno_IDAluno = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAluno);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				eventoAlunoDTO = new EventoAlunoDTO();
				eventoAlunoDTO.setIdAluno(resultSet.getInt(1));
				eventoAlunoDTO.setIdEvento(resultSet.getInt(2));

			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoAlunoDTO.getIdEvento();
	}

	//inserção da tabela evento_Orientador
	public void inserirEventoOrientador(EventoOrientadorDTO eventoOrientadorDTO) throws PersistenciaException {
		try{

			Connection connection =  ConexaoUtil.getInstance().getConnection();

			String sql ="INSERT INTO evento_orientador(orientador_IDOrientador, evento_IDEvento) " + "VALUES(?, ?);";

			PreparedStatement Statement = connection.prepareStatement(sql);

			Statement.setInt(1, eventoOrientadorDTO.getIdOrientador());
			Statement.setInt(2, eventoOrientadorDTO.getIdEvento());


			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
	}
	// deletar da tabela evento_orientador
	public void deletarEventoOrientador(Integer idEvento, Integer idOrientador) throws PersistenciaException {
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM evento_orientador WHERE evento_IDEvento = ? AND orientador_IDOrientador = ?;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setInt(1, idEvento);
			Statement.setInt(2, idOrientador);

			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}

	}
	//listar todos da tabela Evento_Orientador
	public List<EventoOrientadorDTO> listarTodosEventoOrientador() throws PersistenciaException {
		List<EventoOrientadorDTO> listaEventosOrientador = new ArrayList<EventoOrientadorDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_orientador;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				EventoOrientadorDTO eventoOrientadorDTO = new EventoOrientadorDTO();
				eventoOrientadorDTO.setIdOrientador(resultSet.getInt(1));
				eventoOrientadorDTO.setIdEvento(resultSet.getInt(2));


				listaEventosOrientador.add(eventoOrientadorDTO);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaEventosOrientador;
	}
	
	public List<EventoOrientadorDTO> listarTodosEventoOrientador(int idEvento) throws PersistenciaException {
		List<EventoOrientadorDTO> listaEventosOrientador = new ArrayList<EventoOrientadorDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_orientador WHERE evento_IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEvento);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				EventoOrientadorDTO eventoOrientadorDTO = new EventoOrientadorDTO();
				eventoOrientadorDTO.setIdOrientador(resultSet.getInt(1));
				eventoOrientadorDTO.setIdEvento(resultSet.getInt(2));


				listaEventosOrientador.add(eventoOrientadorDTO);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaEventosOrientador;
	}
	// buscar por ids tabela Evento_Orientador

	// luisa disse q era legal fazer um join pra trazer os atributos, 
	// querida gêh essa query fica para vc
	public int buscarEventoOrientadorPoIDE(int idEvento) throws PersistenciaException {
		EventoOrientadorDTO eventoOrientadorDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_orientador WHERE evento_IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEvento);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				
				eventoOrientadorDTO = new EventoOrientadorDTO();
				eventoOrientadorDTO.setIdOrientador(resultSet.getInt(1));
				eventoOrientadorDTO.setIdEvento(resultSet.getInt(2));

			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoOrientadorDTO.getIdOrientador();
	}
	
	public int buscarEventoOrientadorPoIDO(int idOrientador) throws PersistenciaException {
		EventoOrientadorDTO eventoOrientadorDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_orientador WHERE orientador_IDOrientador = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idOrientador);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				eventoOrientadorDTO = new EventoOrientadorDTO();
				eventoOrientadorDTO.setIdOrientador(resultSet.getInt(1));
				eventoOrientadorDTO.setIdEvento(resultSet.getInt(2));
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoOrientadorDTO.getIdEvento();
	}

	//inserção da tabela evento_Coorientador
	public void inserirEventoCoorientador(EventoCoorientadorDTO eventoCoorientadorDTO) throws PersistenciaException {
		try{

			Connection connection =  ConexaoUtil.getInstance().getConnection();

			String sql ="INSERT INTO evento_coorientador(evento_IDEvento, coorientador_IDCoorientador) VALUES(?, ?);";

			PreparedStatement Statement = connection.prepareStatement(sql);
			
			Statement.setInt(1, eventoCoorientadorDTO.getIdEvento());
			Statement.setInt(2, eventoCoorientadorDTO.getIdCoorientador());

			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
	}
	// deletar da tabela evento_coorientador
	public void deletarEventoCoorientador(Integer idEvento, Integer idCoorientador) throws PersistenciaException {
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM evento_coorientador WHERE evento_IDEvento = ? AND coorientador_IDCoorientador = ?;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setInt(1, idEvento);
			Statement.setInt(2, idCoorientador);

			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}

	}
	//listar todos da tabela Evento_coorientador
	public List<EventoCoorientadorDTO> listarTodosEventoCoorientador() throws PersistenciaException {
		List<EventoCoorientadorDTO> listaEventosCoorientador = new ArrayList<EventoCoorientadorDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_coorientador;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				EventoCoorientadorDTO eventoCoorientadorDTO = new EventoCoorientadorDTO();
				eventoCoorientadorDTO.setIdEvento(resultSet.getInt(1));
				eventoCoorientadorDTO.setIdCoorientador(resultSet.getInt(2));


				listaEventosCoorientador.add(eventoCoorientadorDTO);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaEventosCoorientador;
	}
	
	public List<EventoCoorientadorDTO> listarTodosEventoCoorientador(int idEvento) throws PersistenciaException {
		List<EventoCoorientadorDTO> listaEventosCoorientador = new ArrayList<EventoCoorientadorDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_coorientador WHERE evento_IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEvento);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				EventoCoorientadorDTO eventoCoorientadorDTO = new EventoCoorientadorDTO();
				eventoCoorientadorDTO.setIdEvento(resultSet.getInt(1));
				eventoCoorientadorDTO.setIdCoorientador(resultSet.getInt(2));


				listaEventosCoorientador.add(eventoCoorientadorDTO);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaEventosCoorientador;
	}
	// buscar por ids tabela Evento_Coorientador

	// luisa disse q era legal fazer um join pra trazer os atributos, 
	// querida gêh essa query fica para vc
	
	public int buscarEventoCoorientadorPoIDE(int idEvento) throws PersistenciaException {
		EventoCoorientadorDTO eventoCoorientadorDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_coorientador WHERE evento_IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEvento);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				eventoCoorientadorDTO = new EventoCoorientadorDTO();
				eventoCoorientadorDTO.setIdEvento(resultSet.getInt(1));
				eventoCoorientadorDTO.setIdCoorientador(resultSet.getInt(2));

			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoCoorientadorDTO.getIdCoorientador();
	}
	
	public int buscarEventoCoorientadorPoIDO(int idCoorientador) throws PersistenciaException {
		EventoCoorientadorDTO eventoCoorientadorDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM evento_coorientador WHERE coorientador_IDCoorientador = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idCoorientador);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				eventoCoorientadorDTO = new EventoCoorientadorDTO();
				eventoCoorientadorDTO.setIdEvento(resultSet.getInt(1));
				eventoCoorientadorDTO.setIdCoorientador(resultSet.getInt(2));
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoCoorientadorDTO.getIdEvento();
	}
	

	//inserção da tabela ajudante_evento
	public void inserirEventoAjudante(EventoAjudanteDTO eventoAjudanteDTO) throws PersistenciaException {
		try{

			Connection connection =  ConexaoUtil.getInstance().getConnection();

			String sql ="INSERT INTO ajudante_evento(evento_IDEvento, ajudante_IDAjudante) " + "VALUES(?, ?);";

			PreparedStatement Statement = connection.prepareStatement(sql);

			Statement.setInt(1, eventoAjudanteDTO.getIdEvento());
			Statement.setInt(2, eventoAjudanteDTO.getIdAjudante());


			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
	}
	// deletar da tabela ajudante_evento
	public void deletarEventoAjudante(Integer idEvento, Integer idAjudante) throws PersistenciaException {
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM ajudante_evento WHERE evento_IDEvento = ? AND ajudante_IDAjudante = ?;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setInt(1, idEvento);
			Statement.setInt(2, idAjudante);

			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}

	}
	//listar todos da tabela Ajudante_evento
	public List<EventoAjudanteDTO> listarTodosEventoAjudante(int idEvento) throws PersistenciaException {
		List<EventoAjudanteDTO> listaEventosAjudantes = new ArrayList<EventoAjudanteDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM fapac_certificado.ajudante_evento WHERE evento_IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEvento);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				EventoAjudanteDTO eventoAJudanteDTO = new EventoAjudanteDTO();
				eventoAJudanteDTO.setIdEvento(resultSet.getInt(1));
				eventoAJudanteDTO.setIdAjudante(resultSet.getInt(2));


				listaEventosAjudantes.add(eventoAJudanteDTO);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaEventosAjudantes;
	}
	
	public List<EventoAlunoDTO> listarTodosEventoAluno(int idEvento) throws PersistenciaException {
		List<EventoAlunoDTO> listaEventosAlunos = new ArrayList<EventoAlunoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM fapac_certificado.evento_aluno WHERE evento_IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEvento);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){ 
				EventoAlunoDTO eventoAluno = new EventoAlunoDTO();
				eventoAluno.setIdAluno(resultSet.getInt(1));
				eventoAluno.setIdEvento(resultSet.getInt(2));
				
				listaEventosAlunos.add(eventoAluno);
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaEventosAlunos;
	}
	
	// buscar por ids tabela ajudante_evento

	// luisa disse q era legal fazer um join pra trazer os atributos, 
	// querida gêh essa query fica para vc
	public int buscarEventoAjudantePoIDE(int idEvento) throws PersistenciaException {
		EventoAjudanteDTO eventoAjudanteDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM ajudante_evento WHERE evento_IDEvento = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEvento);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				eventoAjudanteDTO = new EventoAjudanteDTO();
				eventoAjudanteDTO.setIdEvento(resultSet.getInt(1));
				eventoAjudanteDTO.setIdAjudante(resultSet.getInt(2));

			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoAjudanteDTO.getIdAjudante();
	}
	
	public int buscarEventoAjudantePoIDA(int idAjudante) throws PersistenciaException {
		EventoAjudanteDTO eventoAjudanteDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM ajudante_evento WHERE ajudante_IDAjudante= ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAjudante);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()){ 
				eventoAjudanteDTO = new EventoAjudanteDTO();
				eventoAjudanteDTO.setIdEvento(resultSet.getInt(1));
				eventoAjudanteDTO.setIdAjudante(resultSet.getInt(2));
			}
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return eventoAjudanteDTO.getIdEvento();
	}
	
	//inserção da tabela evento_projeto
		public void inserirEventoProjeto(EventoProjetoDTO eventoProjetoDTO) throws PersistenciaException {
			try{

				Connection connection =  ConexaoUtil.getInstance().getConnection();

				String sql ="INSERT INTO evento_projeto(evento_IDEvento, projeto_IDProjeto) " + "VALUES(?, ?);";

				PreparedStatement Statement = connection.prepareStatement(sql);

				Statement.setInt(1, eventoProjetoDTO.getIdEvento());
				Statement.setInt(2, eventoProjetoDTO.getIdProjeto());


				Statement.execute();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(),e ) ;
			}
		}
		// deletar da tabela evento_ajudante
		public void deletarEventoProjeto(Integer idEvento, Integer idProjeto) throws PersistenciaException {
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();
				String sql = "DELETE FROM evento_projeto WHERE evento_IDEvento = ? AND projeto_IDProjeto = ?;";

				PreparedStatement Statement = connection.prepareStatement(sql);
				Statement.setInt(1, idEvento);
				Statement.setInt(2, idProjeto);

				Statement.execute();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(),e);
			}

		}
		//listar todos da tabela Evento_ajudante
		public List<EventoProjetoDTO> listarTodosEventoProjeto() throws PersistenciaException {
			List<EventoProjetoDTO> listaEventosProjetos = new ArrayList<EventoProjetoDTO>();
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();

				String sql = "SELECT * FROM evento_projeto;";

				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();

				while(resultSet.next()){ 
					EventoProjetoDTO eventoProjetoDTO = new EventoProjetoDTO();
					eventoProjetoDTO.setIdEvento(resultSet.getInt(1));
					eventoProjetoDTO.setIdProjeto(resultSet.getInt(2));

					listaEventosProjetos.add(eventoProjetoDTO);
				}
				connection.close();

			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
			return listaEventosProjetos;
		}
		// buscar por ids tabela Evento_ajudante 

		// luisa disse q era legal fazer um join pra trazer os atributos, 
		// querida gêh essa query fica para vc
		public int buscarEventoProjetosPoIDE(int idEvento) throws PersistenciaException {
			EventoProjetoDTO eventoProjetoDTO = null;
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();

				String sql = "SELECT * FROM evento_projeto WHERE evento_IDEvento = ?;";

				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, idEvento);
				ResultSet resultSet = statement.executeQuery();

				if(resultSet.next()){ 
					eventoProjetoDTO = new EventoProjetoDTO();
					eventoProjetoDTO.setIdEvento(resultSet.getInt(1));
					eventoProjetoDTO.setIdProjeto(resultSet.getInt(2));

				}
				connection.close();

			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
			return eventoProjetoDTO.getIdProjeto();
		}
		
		public int buscarEventoProjetosPoIDP(int idProjeto) throws PersistenciaException {
			EventoProjetoDTO eventoProjetoDTO = null;
			try{
				Connection connection = ConexaoUtil.getInstance().getConnection();

				String sql = "SELECT * FROM evento_projeto WHERE projeto_IDProjeto = ?;";

				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, idProjeto);
				ResultSet resultSet = statement.executeQuery();

				if(resultSet.next()){ 
					eventoProjetoDTO = new EventoProjetoDTO();
					eventoProjetoDTO.setIdEvento(resultSet.getInt(1));
					eventoProjetoDTO.setIdProjeto(resultSet.getInt(2));

				}
				connection.close();

			}catch(Exception e){
				e.printStackTrace();
				throw new PersistenciaException(e.getMessage(), e);
			}
			return eventoProjetoDTO.getIdEvento();
		}
}
