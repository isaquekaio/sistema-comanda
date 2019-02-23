public class Item{
	private int quantidade;
	private Produto produto;

	public Item(){
		super ();
	}

	public Item(int quantidade, Produto produto){
		super ();
		this.quantidade = quantidade;
		this.produto = produto;
	}	

	public int getQuantidade(){
		return this.quantidade;
	}

	public void setQuantidadde(int quantidade){
		this.quantidade = quantidade;
	}

	public Produto getProduto(){
		return this.produto;
	}

	public void setProduto(Produto produto){
		this.produto = produto;
	}

	public String getNome(){
		return produto.getNome();
	}

	public double getSubtotal(){
		return quantidade * produto.getPreco();
	}
}