package br.acre.fapac.certificado.exception;

public class LogicException extends Exception{
	
	private static final long serialVersionUID = -8916895283914218760L;

	  public LogicException(String msg, Exception exception){
	    super(msg, exception);
	  }
	  public LogicException(String msg){
	    super(msg);
	  }


}
