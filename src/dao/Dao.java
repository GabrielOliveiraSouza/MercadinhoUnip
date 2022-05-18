package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexoes.ConexaoMysql;
import dto.EstoqueModel;
import dto.LoginModel;
import util.Crud;

public class Dao implements Crud {

	private ConexaoMysql conexao = new ConexaoMysql();

	public boolean executarLogin(LoginModel login) {
		String sql = "select * from estudos.login where user = ?";
		boolean loginExecutado = false;
		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
			pst.setString(1, login.getUser());
			ResultSet rs = pst.executeQuery();

			if (rs != null) {
				while (rs.next()) {

					if (rs.getString("user").equals(login.getUser().strip())
							&& rs.getString("pass").equals(login.getPass().strip())) {
						loginExecutado = true;
					} else {
						loginExecutado = false;
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("ERRO AO EXECUTAR QUERY");
			e.printStackTrace();
		}
		return loginExecutado;
	}

	@Override
	public void criarProduto(EstoqueModel estoque) {
		String sql = "INSERT INTO estudos.estoque (produto, quant, prateleira) VALUES (?,?,?)";
		try {
			PreparedStatement pst = this.conexao.getConexao().prepareStatement(sql);
			pst.setString(1, estoque.getProduto());
			pst.setInt(2, estoque.getQuant());
			pst.setString(3, estoque.getPrateleira());
			
			pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FALHA NA CRIAÇÃO");
		}

	}

	@Override
	public void excluirProduto(EstoqueModel estoque) {
		String sql = "DELETE FROM estudos.estoque WHERE id_prod = ?";
		try {
			PreparedStatement pst = this.conexao.getConexao().prepareStatement(sql);
			pst.setInt(1,estoque.getIdProd());
			
			pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FALHA NA EXCLUSÃO");
		}
	}

	@Override
	public ArrayList<EstoqueModel> lerProdutos() {
		ArrayList<EstoqueModel> listaProdutos = new ArrayList<EstoqueModel>();
		String sql = "SELECT * FROM estudos.estoque;";
		try {
			PreparedStatement pst = this.conexao.getConexao().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					EstoqueModel estoque = new EstoqueModel();
					
					estoque.setIdProd(rs.getInt("id_prod"));
					estoque.setProduto(rs.getString("produto"));
					estoque.setQuant(rs.getInt("quant"));
					estoque.setPrateleira(rs.getString("prateleira"));
					
					listaProdutos.add(estoque);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProdutos;
	}

	@Override
	public void atualizarProduto(EstoqueModel estoque) {
		String sql = "UPDATE  estudos.estoque SET produto = ?, quant = ?, prateleira = ? WHERE id_prod = ?";
		try {
			PreparedStatement pst = this.conexao.getConexao().prepareStatement(sql);
			pst.setString(1, estoque.getProduto());
			pst.setInt(2, estoque.getQuant());
			pst.setString(3, estoque.getPrateleira());
			pst.setInt(4,estoque.getIdProd());
			
			pst.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("FALHA NA ATUALIZAÇÃO");
		}

	}

}
