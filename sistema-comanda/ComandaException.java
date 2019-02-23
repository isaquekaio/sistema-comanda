import java.util.*;
public class ComandaException extends Exception{
	private static final long serialVersionUID = 8002444801632069740L;
	
	public ComandaException(){
		super("Excecao do sistema de comandas");
	}

	public ComandaException(String message){
		super(message);
	}
}