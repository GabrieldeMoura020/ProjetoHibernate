package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.Service;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.DadosConstantesVO;
import br.com.empresa.vo.UsuarioVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField tfLogin;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 271, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Login do Sistema!");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Login: ");
		lblLogin.setBounds(10, 11, 46, 14);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(10, 45, 46, 14);
		contentPane.add(lblSenha);

		tfLogin = new JTextField();
		tfLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pfSenha.requestFocus();

			}
		});
		tfLogin.setBounds(50, 8, 148, 20);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);

		JLabel lblEsqueciMinhaSenha = new JLabel("Esqueci minha senha");
		lblEsqueciMinhaSenha
				.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/empresa/view/img/forgot-password.png")));
		lblEsqueciMinhaSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,
						"Entre em contato com o " + "administrador da Narwal que " + "ele roda na produção na hora");
			}
		});
		lblEsqueciMinhaSenha.setBounds(60, 73, 124, 14);
		contentPane.add(lblEsqueciMinhaSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				efetuarLogin();
			}

		});
		btnEntrar.setToolTipText("Clique aqui para entrar aplicação");
		btnEntrar.setMnemonic('E');
		btnEntrar.setBounds(20, 121, 89, 23);
		contentPane.add(btnEntrar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Selecione uma opção...",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (input == 0) {

					System.exit(0);

				}
			}
		});
		btnSair.setToolTipText("Clique aqui para sair aplicação");
		btnSair.setMnemonic('S');
		btnSair.setBounds(141, 121, 89, 23);
		contentPane.add(btnSair);

		pfSenha = new JPasswordField();
		pfSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				efetuarLogin();

			}
		});
		pfSenha.setBounds(50, 42, 148, 20);
		contentPane.add(pfSenha);

		JSeparator separator = new JSeparator();
		separator.setBounds(-16, 105, 414, 5);
		contentPane.add(separator);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}

	/**
	 * Realiza o processo de login no sistema
	 */

	private void efetuarLogin() {

		String login = this.tfLogin.getText();
		String senha = new String(this.pfSenha.getPassword());

		// Verificar se o login e a senha estão corretos no banco.

		Service service = new Service();
		UsuarioVO usuario;
		try {

			usuario = service.buscarUsuario(login, senha);

			if (usuario == null) {

				JOptionPane.showMessageDialog(null, "Usuário e/ou senha não reconhecido(s)", "Validação",
						JOptionPane.WARNING_MESSAGE);

			} else { // Se entrou aqui é porque o usuário existe e foi encontrado

				DadosConstantesVO.setUsuarioVO(usuario);

				List<ClienteUsuarioVO> clientes = service.listarClientesUsuario(usuario, null);

				if (clientes != null && clientes.size() > 1) {

					TelaSelecaoClienteView telaSelecaoClienteView = new TelaSelecaoClienteView();
					telaSelecaoClienteView.setVisible(true);

				} else if (clientes.size() == 1) { // Se o usuario possuir 1 cliente

					DadosConstantesVO.setClienteVO(clientes.get(0).getClienteVO());

					TelaPrincipalView telaPrincipalView = new TelaPrincipalView();
					telaPrincipalView.setVisible(true);

				} else { // Não tem ligação com cliente

					JOptionPane.showMessageDialog(null, "O usuário não possui permissão de acesso a clientes",
							"Validação", JOptionPane.WARNING_MESSAGE);

				}
				
				// Oculta a tela atual
				this.setVisible(false);
				
			}
		} catch (BOValidationException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);

		} catch (BOException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);

		}
	}
}
