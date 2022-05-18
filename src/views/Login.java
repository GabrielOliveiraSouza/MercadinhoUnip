package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.Dao;
import dto.LoginModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;

public class Login extends JFrame {

	private Dao dao = new Dao();
	private LoginModel loginModel = null;

	
	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField tfPass;
	JButton btnEntrar = new JButton("ENTRAR");
	private Label label_2;
	private Label label_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("LOGIN - MERCADINHO UNIP");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnEntrar.setForeground(new Color(0, 0, 102));
		btnEntrar.setBackground(Color.WHITE);

		btnEntrar.setBounds(143, 172, 89, 23);
		btnEntrar.setEnabled(false);
		contentPane.add(btnEntrar);

		tfUser = new JTextField();
		tfUser.setBounds(106, 92, 157, 20);
		contentPane.add(tfUser);
		tfUser.setColumns(10);

		tfPass = new JPasswordField();
		tfPass.setBounds(106, 141, 157, 20);
		contentPane.add(tfPass);
		tfPass.setColumns(10);

		Label label = new Label("USU\u00C1RIO");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.BOLD, 12));
		label.setBounds(106, 66, 163, 22);
		contentPane.add(label);

		Label label_1 = new Label("SENHA");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Arial", Font.BOLD, 12));
		label_1.setBounds(106, 118, 168, 22);
		contentPane.add(label_1);

		label_2 = new Label("MERCADINHO");
		label_2.setFont(new Font("Arial", Font.BOLD, 23));
		label_2.setForeground(Color.YELLOW);
		label_2.setBounds(122, 2, 178, 22);
		contentPane.add(label_2);

		label_3 = new Label("UNIP");
		label_3.setAlignment(Label.CENTER);
		label_3.setForeground(Color.YELLOW);
		label_3.setFont(new Font("Arial", Font.BOLD, 23));
		label_3.setBounds(111, 24, 178, 22);
		contentPane.add(label_3);

		// eventos

		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				executarLogin(tfUser.getText(), new String(tfPass.getPassword()));
				if (dao.executarLogin(loginModel)) {
					Estoque estoque = new Estoque();
					setVisible(false);
					estoque.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "USUÁRIO OU SENHA INVÁLIDO");
					limparCampos();
				}
			}
		});

		tfUser.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				validarCampos();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				validarCampos();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validarCampos();
			}
		});

		tfPass.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				validarCampos();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				validarCampos();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validarCampos();
			}
		});
	}

	public void validarCampos() {
		if (tfUser.getText().strip().length() > 0 && new String(tfPass.getPassword()).length() > 0) {
			btnEntrar.setEnabled(true);
		}
	}

	public void limparCampos() {
		tfUser.setText("");
		tfPass.setText("");
	}

	public void executarLogin(String user, String pass) {
		loginModel = new LoginModel();
		loginModel.setUser(user);
		loginModel.setPass(pass);
		Estoque.activeUser = loginModel.getUser();

	}
}
