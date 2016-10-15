package br.acre.fapac.certificado.dto;

import br.acre.fapac.certificado.dao.AdministradorDAO;
import br.acre.fapac.certificado.exception.LogicException;

public class AdministradorDTO {
	private String Login;
	private String Senha;
	
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	
	public AdministradorDTO buscarAdm(String login) throws LogicException{
	    AdministradorDTO admDTO;
	    try{
	      AdministradorDAO admDAO = new AdministradorDAO();
	        admDTO = admDAO.buscarPorAdm(login);
	    }catch(Exception e){
	      throw new LogicException(e.getMessage());
	    }
	    return admDTO;
	  }
	
	public boolean validaAdministrador(String login, String senha) throws LogicException{
	    AdministradorDTO admDTO;
	    try{
	      admDTO = buscarAdm(login);
	      if(admDTO.getSenha().equalsIgnoreCase(senha)){
	        return true;
	      }
	      else {
	        throw new LogicException("Senha Incorreta");
	      }
	    }catch(Exception  e){
	      throw new LogicException(e.getMessage());
	    }
	  }
	
}
