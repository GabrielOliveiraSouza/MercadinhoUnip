package util;

import java.util.ArrayList;

import dto.EstoqueModel;

public interface Crud {

	public void criarProduto(EstoqueModel estoque);
	public void excluirProduto(EstoqueModel estoque);
	public ArrayList<EstoqueModel> lerProdutos();
	public void atualizarProduto(EstoqueModel estoque);
}
