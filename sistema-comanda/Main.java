import java.util.*;

class Main {
  static Scanner sc;
	static ComandaDAO comandaDAO = new ComandaDAO();
	static ProdutoDAO produtoDAO = new ProdutoDAO();

	public static void main(String[] args){
		sc = new Scanner(System.in);
		boolean encerra = false;
		do{
			exibeMenu();
			int opcao = sc.nextInt();
			encerra = tratarOpcao(opcao);

		}while(!encerra);
  }

	private static void exibeMenu(){
		System.out.println("\n\nSISTEMA EM COMANDAS");
		System.out.println("Opções");
		System.out.println("1 - Registrar pedidos em comanda");
		System.out.println("2 - Lista comandas ativas");
		System.out.println("3 - Finalizar comanda ativas");
		System.out.println("0 - SAIR");
		System.out.print("Selecione uma opção: ");
	}

	private static boolean tratarOpcao(int opcao){
		switch(opcao){
			case 0:
				return true;
			case 1:
				adicionaItemEmComanda();
				break;
			case 2:
				comadasAtivas();
				break;
			case 3:
				finalizarAtiva();
				break;				
			default :
				System.out.print("\nOpção INVÁLIDA, tente novamente!");
				break;
		}
		return false;
	}

	private static void adicionaItemEmComanda(){
		System.out.print("Digite o número da comanda: ");
		int numero = sc.nextInt();
		Comanda comanda;
		try{
			comanda = comandaDAO.recuperaComanda(numero);
		}catch(ComandaException e){
			System.out.print("Erro: "+e.getMessage()+" Deseja criar (s/n)? ");
			sc = new Scanner(System.in);
			String opcao = sc.nextLine();
			if (opcao.toLowerCase().equals("s")) {
				System.out.print("Digite o número da comanda: ");
				int numeroComanda = sc.nextInt();
				comanda = comandaDAO.criarComanda(numeroComanda);
				exibeParcial(comanda);
			} else {
				return;
			}
		}
		exibeParcial(comanda);
		try{
			int codigo;
			do{
				System.out.print("Digite o código do produto a adicionar: ");
				codigo = sc.nextInt();
				if(codigo == 0) break;
				Produto produto = produtoDAO.buscaProduto(codigo);
				System.out.print("Digite a quantidade de "+produto.getNome()+": ");
				int quantidade = sc.nextInt();
				comanda.adicionaItem(produto, quantidade);
				exibeParcial(comanda);
			}while (codigo != 0);
		}catch(ComandaException e){
			System.out.print("Erro: "+e.getMessage());
		}
	}

	private static void comadasAtivas(){
		ArrayList<Comanda> ativas = comandaDAO.getAtivas();
		for(Comanda comanda : ativas){
			System.out.println("["+comanda.getNumero()+"] - R$ "+comanda.getTotal());
		}
	}

	private static void finalizarAtiva(){
		System.out.print("Digite o número da comanda: ");
		int numero = sc.nextInt();
		ArrayList<Comanda> ativas = comandaDAO.getAtivas();
		for(Comanda comanda : ativas){
			if(comanda.getNumero() == numero){
				exibeParcial(comanda);
				System.out.print("Formas de pagamento:\n1)Dinheiro\n2)Cheque\n3)Cartão\n");
				int op = sc.nextInt();
				switch(op){
					case 1:
						System.out.print("Digite o valor para quitar a comanda: ");		
						try{
						double dinheiro = sc.nextDouble();
						comanda.setPagamento(dinheiro);
						comanda.setDataPagamento(comanda.getDataPagemnto());					
						double troco = comanda.getTroco();
						System.out.print("Troco: "+troco);
						}catch(ComandaException e){
							System.out.print("\nErro: "+e.getMessage());
							break;
						}
						System.out.print("\nDigite o 2 para fecha comanda: ");
						int estado = sc.nextInt();
						comanda.setEstado(estado);
						break;
					case 2 :
						break;
					case 3 :
						break;
					default :
						System.out.println("\nOpção INVÁLIDA!");
						break;
				}
			}
		}
	}
	
	private static void exibeParcial(Comanda comanda){
		System.out.println(comanda);
	}
}