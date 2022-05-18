package views;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Dao;
import dto.EstoqueModel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;

public class Estoque extends JFrame {

	private Dao dao = new Dao();

	private ArrayList<EstoqueModel> listaProdutos = dao.lerProdutos();

	private JPanel contentPane;
	static String  activeUser;
	private JTable table;

	private JScrollPane rolagem;
	private JTextField tf_produto;
	private JTextField tf_id;
	private JTextField tf_quant;
	private JTextField tf_prateleira;
	private JButton btnRemove;
	private JButton btnAtualizar;
	private Label label;
	private Label label_1;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public Estoque() {
		setTitle("CONTROLE DE ESTOQUE");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "ID PRODUTO", "PRODUTO", "QUANTIDADE", "PRATELEIRA" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		model.setNumRows(listaProdutos.size());

		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(95);

		table.setBounds(350, 11, 350, 391);
		contentPane.setLayout(null);

		rolagem = new JScrollPane();
		rolagem.setBounds(374, 41, 417, 359);
		rolagem.setBackground(new Color(255, 0, 0));
		rolagem.setViewportView(table);
		contentPane.add(rolagem);

		tf_produto = new JTextField();
		tf_produto.setHorizontalAlignment(SwingConstants.CENTER);
		tf_produto.setBounds(20, 123, 226, 20);
		contentPane.add(tf_produto);
		tf_produto.setColumns(10);

		tf_id = new JTextField();
		tf_id.setEditable(false);
		tf_id.setHorizontalAlignment(SwingConstants.CENTER);
		tf_id.setColumns(10);
		tf_id.setBounds(20, 69, 226, 20);
		contentPane.add(tf_id);

		tf_quant = new JTextField();
		tf_quant.setHorizontalAlignment(SwingConstants.CENTER);
		tf_quant.setColumns(10);
		tf_quant.setBounds(20, 170, 226, 20);
		contentPane.add(tf_quant);

		tf_prateleira = new JTextField();
		tf_prateleira.setHorizontalAlignment(SwingConstants.CENTER);
		tf_prateleira.setColumns(10);
		tf_prateleira.setBounds(20, 223, 226, 20);
		contentPane.add(tf_prateleira);

		JButton btnAdd = new JButton("ADICIONAR");
		btnAdd.setForeground(new Color(0, 0, 102));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(10, 342, 100, 23);
		contentPane.add(btnAdd);

		btnRemove = new JButton("REMOVER");
		btnRemove.setForeground(new Color(0, 0, 102));
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setBounds(120, 342, 113, 23);
		contentPane.add(btnRemove);

		btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.setBackground(Color.WHITE);
		btnAtualizar.setForeground(new Color(0, 0, 102));

		btnAtualizar.setBounds(243, 342, 109, 23);
		contentPane.add(btnAtualizar);

		label = new Label("MERCADINHO");
		label.setForeground(Color.YELLOW);
		label.setFont(new Font("Arial", Font.BOLD, 23));
		label.setBounds(0, 10, 163, 22);
		contentPane.add(label);
		label_1 = new Label("UNIP");
		label_1.setForeground(Color.YELLOW);
		label_1.setFont(new Font("Arial", Font.BOLD, 23));
		label_1.setBounds(169, 10, 74, 22);
		contentPane.add(label_1);

		JLabel lblNewLabel = new JLabel("ID PRODUTO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(20, 47, 90, 14);
		contentPane.add(lblNewLabel);

		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setForeground(Color.WHITE);
		lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProduto.setBounds(20, 108, 90, 14);
		contentPane.add(lblProduto);

		JLabel lblQuantidade = new JLabel("QUANTIDADE");
		lblQuantidade.setForeground(Color.WHITE);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantidade.setBounds(20, 154, 90, 14);
		contentPane.add(lblQuantidade);

		JLabel lblPrateleira = new JLabel("PRATELEIRA");
		lblPrateleira.setForeground(Color.WHITE);
		lblPrateleira.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrateleira.setBounds(20, 208, 90, 14);
		contentPane.add(lblPrateleira);

		JButton btnLimpar = new JButton("LIMPAR");

		btnLimpar.setForeground(new Color(0, 0, 102));
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(120, 376, 113, 23);
		contentPane.add(btnLimpar);
//		
		lblNewLabel_1 = new JLabel("USU\u00C1RIO ATIVO: "+activeUser.toUpperCase());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(439, 10, 210, 14);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton = new JButton("LOGOUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				setVisible(false);
				login.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(0, 0, 102));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(742, 7, 89, 23);
		contentPane.add(btnNewButton);

		// ---------------------------------events--------------------------------------------
		this.adicionarLinhas();

		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				alterarFields();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificaCampos()) {
					EstoqueModel estoque = ProdutoSelecionado("INSERIR");
					dao.criarProduto(estoque);
					atualizaDados();
				} else {
					JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS");
				}

			}
		});

		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstoqueModel estoque = produtoExcluido();
				Integer a = JOptionPane.showConfirmDialog(null, "VOCÊ TEM CERTEZA QUE DESEJA EXCLUIR O "
						+ estoque.getProduto().toUpperCase() + " COM O ID" + estoque.getIdProd() + "?", "",
						JOptionPane.YES_NO_OPTION);

				if (a == JOptionPane.YES_OPTION) {
					if (verificaCampos()) {
						dao.excluirProduto(estoque);
						atualizaDados();
					} else {
						JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS");
					}
				}
			}
		});

		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificaCampos()) {
					EstoqueModel estoque = ProdutoSelecionado("ATUALIZAR");
					dao.atualizarProduto(estoque);
					atualizaDados();
				} else {
					JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS");
				}

			}
		});

		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});

	}

	public HashMap<String, Object> pegarLinhaTabela() {
		HashMap<String, Object> linha = new HashMap<String, Object>();
		int row = table.getSelectedRow();
		linha.put("ID", table.getValueAt(row, 0));
		linha.put("PRODUTO", table.getValueAt(row, 1));
		linha.put("QUANTIDADE", table.getValueAt(row, 2));
		linha.put("PRATELEIRA", table.getValueAt(row, 3));
		return linha;
	}

	public void alterarFields() {

		HashMap<String, Object> linhaSelecionada = this.pegarLinhaTabela();
		tf_id.setText(linhaSelecionada.get("ID").toString());
		tf_produto.setText(linhaSelecionada.get("PRODUTO").toString());
		tf_quant.setText(linhaSelecionada.get("QUANTIDADE").toString());
		tf_prateleira.setText(linhaSelecionada.get("PRATELEIRA").toString());
	}

	public EstoqueModel ProdutoSelecionado(String acao) {
		EstoqueModel estoque = new EstoqueModel();
		if (acao.equals("ATUALIZAR")) {
			
			estoque.setIdProd(Integer.parseInt(tf_id.getText()));
		}
		estoque.setProduto(tf_produto.getText().toString());
		estoque.setQuant(Integer.parseInt(tf_quant.getText()));
		estoque.setPrateleira(tf_prateleira.getText().toString());

		return estoque;
	}

	public EstoqueModel produtoExcluido() {
		EstoqueModel estoque = new EstoqueModel();
		estoque.setIdProd(Integer.parseInt(tf_id.getText()));
		estoque.setProduto(tf_produto.getText().toString());

		return estoque;
	}

	public void adicionarLinhas() {

		int line = 0;
		for (EstoqueModel produto : listaProdutos) {
			table.setValueAt(produto.getIdProd(), line, 0);
			table.setValueAt(produto.getProduto(), line, 1);
			table.setValueAt(produto.getQuant(), line, 2);
			table.setValueAt(produto.getPrateleira(), line, 3);
			line++;
		}
	}

	public boolean verificaCampos() {
		boolean retorno = false;
		if (tf_prateleira.getText().strip().length() > 0
				&& tf_produto.getText().strip().length() > 0 && tf_quant.getText().strip().length() > 0) {
			retorno = true;
		}
		return retorno;
	}

	public void limparCampos() {
		tf_id.setText("");
		tf_produto.setText("");
		tf_quant.setText("");
		tf_prateleira.setText("");
	}

	public void atualizaDados() {
		Estoque estoque = new Estoque();
		this.contentPane = null;
		setVisible(false);
		estoque.setVisible(true);
	}
}
