package aula05;

import java.util.List;

public class Pedido {
	private int id;
	private Cliente cliente;
	private List<Produto> produtos;
	
	public Pedido(int id, Cliente cliente, List<Produto> produtos) {
		this.id = id;
		this.cliente = cliente;
		this.produtos = produtos;
	}
	
	//getters and setters


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
		
	

}
