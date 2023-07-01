package br.com.empresa.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import br.com.empresa.dao.HibernateUtil;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * 
 * @author GabrieldeMoura
 *
 */
public class TelaCadastroUsuarioView extends JFrame {

	private JPanel contentPane;
	private JPasswordField pfSenha;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfLogin;
	private JButton btnCadastrar;
	private JButton btnCancelar;

	/**
	 * @author GabrieldeMoura
	 * @since 20/06/2023 Create the frame.
	 */
	public TelaCadastroUsuarioView() {

		setResizable(false);

		setTitle("Manutenção de usuário");
		setBounds(100, 100, 330, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 36, 46, 14);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 61, 46, 14);
		contentPane.add(lblSenha);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(53, 8, 154, 20);
		contentPane.add(ftfNome);

		ftfLogin = new JFormattedTextField();
		ftfLogin.setBounds(53, 33, 136, 20);
		contentPane.add(ftfLogin);

		pfSenha = new JPasswordField();
		pfSenha.setBounds(53, 58, 131, 20);
		contentPane.add(pfSenha);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				efetuarCadastro();

			}
		});

		btnCadastrar.setIcon(
				new ImageIcon(TelaCadastroUsuarioView.class.getResource("/br/com/empresa/view/img/register.png")));
		btnCadastrar.setBounds(30, 100, 109, 23);
		contentPane.add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				cancelar();

			}
		});

		btnCancelar.setIcon(
				new ImageIcon(TelaCadastroUsuarioView.class.getResource("/br/com/empresa/view/img/rejected.png")));
		btnCancelar.setBounds(149, 100, 103, 23);
		contentPane.add(btnCancelar);

	}

	public void cancelar() {

		TelaCadastroUsuarioView tcu = new TelaCadastroUsuarioView();
		tcu.setVisible(false);
		this.setVisible(false);

	}

	public void efetuarCadastro() {

		System.out.println("Inserindo cliente!");

		EntityManager em = HibernateUtil.getEntityManager();

		UsuarioVO usu = new UsuarioVO();
		usu.setLogusu(ftfLogin.getText());
		usu.setSenusu(new String(this.pfSenha.getPassword()));

		try {

			em.getTransaction().begin();
			em.persist(usu);
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");

		} catch (BOValidationException e) {

			e.printStackTrace();
			em.getTransaction().rollback();

		} catch (BOException e) {

			e.printStackTrace();
		}
	}
}