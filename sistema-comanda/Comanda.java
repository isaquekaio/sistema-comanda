import java.util.*;

public class Comanda {
	public static final int ATIVA = 1;
	public static final int FECHADA = 2;
	
	private int estadoComanda;
	private double pagamento;
	private Date dataPagamento;
	private int numero;
	private List<Item> itens;
	
	public Comanda(int numero){
		super();
		this.numero = numero;
		itens = new ArrayList<Item>();
		estadoComanda = Comanda.ATIVA;
	}

	public void setPagamento(double pagamento) throws ComandaException{
		if(pagamento >= getTotal()){
			this.pagamento = pagamento;
		}else{
			throw new ComandaException("Valor inválido!!!");
		}
	}

	public double getPagamento(){
		return pagamento;
	}

	public Date getDataPagemnto(){
		Date hoje = new Date();
		return hoje;
	}

	public void setDataPagamento(Date d){
		dataPagamento = d;
	}

	public double getTroco() throws ComandaException{
		if(getPagamento() >= getTotal()){
			return getPagamento()-getTotal();
		}else{
			throw new ComandaException("Valor inválido!!!");
		}
	}	

	public int getEstado(){
		return estadoComanda;
	}

	public void setEstado(int estado){
		estadoComanda = estado;
	}

	public int getNumero(){
		return this.numero;
	}

	public void setNumero(int numero){
		this.numero = numero;
	}

	public List<Item> getItens(){
		return itens;
	}

	public void setItens(List<Item> itens){
		this.itens = itens;
	}

	@Override
	public String toString(){
		String parcial = "";
		double total = 0;
		for(Item it : itens){
			parcial += it.getNome()+" - "+it.getQuantidade()+" unid. - subtotal "+it.getSubtotal()+"\n";
			total += it.getSubtotal(); 
		}
		if(itens.isEmpty()){
			parcial += "Ainda não há itens associados a essa comanda!\n";
		}
		parcial += "TOTAL = R$ "+total+"\n";
		return parcial;
	}

	public double getTotal(){
		double total = 0;
		for(Item item : itens){
			total += item.getSubtotal();
		}
		return total;
	}

	public void adicionaItem(Produto produto, int quantidade){
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidadde(quantidade);
		itens.add(item);
	}
}