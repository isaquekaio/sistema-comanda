import java.util.*;

public class ProdutoDAO extends ArrayList<Produto> {
	private static final long serialVersionUID = 8002444801632069740L;
	
	public ProdutoDAO(){
		add(new Produto(1, "X-Tudo", 5.90));
		add(new Produto(2, "Coca-cola", 3.50));
		add(new Produto(3, "Bauru", 4.0));
	}

	public Produto buscaProduto(int codigo) throws ComandaException{
		for(Produto p : this){
			if(p.getCodigo() == codigo){
				return p;
			}
		}
		throw new ComandaException("Produto inexistente");
	}
}