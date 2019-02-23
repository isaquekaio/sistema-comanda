import java.util.*;
public class ComandaDAO extends ArrayList<Comanda> {
	private static final long serialVersionUID = 8002444801632069740L;
		
	public ComandaDAO(){
		add(new Comanda(1));
		add(new Comanda(2));
		add(new Comanda(3));
	}

	public Comanda recuperaComanda(int numero) throws ComandaException{
		for(Comanda c : this){
			if(c.getNumero() == numero){
				return c;
			}
		}
		throw new ComandaException("Comanda inexistente");
	}

	public Comanda criarComanda(int numero){
		Comanda nova = new Comanda(numero);
		add(nova); 
		return nova;
	}

	public ArrayList<Comanda> getAtivas(){
		ArrayList<Comanda> lista = new ArrayList<Comanda>();
		for(Comanda atual : this){
			if(atual.getEstado() == Comanda.ATIVA){
				lista.add(atual);
			}else{
				lista.remove(atual);
			}
		}
		return lista;
	}	
}