package br.com.empresa.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.Service;
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.DadosConstantesVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.StatusEnum;
import br.com.empresa.vo.UsuarioVO;

public class TelaConsultaUsuarioView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFormattedTextField ftfCodigo;
	private JFormattedTextField ftfLogin;
	private JButton btnPesquisar;
	private TableModel tableModel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TelaConsultaUsuarioView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 429, 105);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 11, 46, 14);
		panel.add(lblCodigo);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setBounds(49, 8, 77, 20);
		panel.add(ftfCodigo);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 36, 46, 14);
		panel.add(lblLogin);

		ftfLogin = new JFormattedTextField();
		ftfLogin.setBounds(49, 33, 144, 20);
		panel.add(ftfLogin);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(
				new ImageIcon(TelaConsultaUsuarioView.class.getResource("/br/com/empresa/view/img/clean.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limparPesquisa();

			}
		});
		btnLimpar.setBounds(201, 71, 96, 23);
		panel.add(btnLimpar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(
				new ImageIcon(TelaConsultaUsuarioView.class.getResource("/br/com/empresa/view/img/people.png")));

		btnPesquisar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				pesquisar();

			}
		});

		btnPesquisar.setBounds(305, 71, 107, 23);
		panel.add(btnPesquisar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(
				new ImageIcon(TelaConsultaUsuarioView.class.getResource("/br/com/empresa/view/img/adicionar2.png")));
		btnAdicionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				abrirTelaCadastroUsuario();

			}
		});

		btnAdicionar.setBounds(20, 126, 105, 23);
		contentPane.add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar
				.setIcon(new ImageIcon(TelaConsultaUsuarioView.class.getResource("/br/com/empresa/view/img/edit.png")));
		btnEditar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				editarUsuario();

			}
		});

		btnEditar.setBounds(146, 126, 89, 23);
		contentPane.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir
				.setIcon(new ImageIcon(TelaConsultaUsuarioView.class.getResource("/br/com/empresa/view/img/user.png")));
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				excluirUsuario();

			}
		});

		btnExcluir.setBounds(245, 126, 97, 23);
		contentPane.add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(431, 160, -419, 211);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBounds(10, 156, 419, 215);
		contentPane.add(table);

		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(TelaConsultaUsuarioView.class.getResource("/br/com/empresa/view/img/exit.png")));

		btnSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				fecharJanela();

			}
		});

		btnSair.setBounds(328, 377, 89, 23);
		contentPane.add(btnSair);

		pesquisar();
	}

	public void abrirTelaCadastroUsuario() {

		TelaCadastroUsuarioView tcuv1 = new TelaCadastroUsuarioView();
		tcuv1.setVisible(true);

	}

	public void excluirUsuario() {

		if (table.getSelectedRow() < 0) {

			JOptionPane.showMessageDialog(this, "É necessário selecionar um " + "usuário!", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);

		} else {

			Object[] optins = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(null, "Deseja realmente excluir o usuário?", "Confirmação",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optins, optins[1]);

			if (n == 0) {

				ClienteUsuarioVO cliusu = (ClienteUsuarioVO) tableModel.getRows().get(table.getSelectedRow())
						.getElement();

				try {

					Service servico = new Service();
					servico.excluirUsuario(cliusu);

					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Mensagem de aviso",
							JOptionPane.INFORMATION_MESSAGE);

					pesquisar();

				} catch (BOException e) {

					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Ocorreu um erro ao executar a operação!",
							JOptionPane.WARNING_MESSAGE);

				}
			}
		}

	}

	public void pesquisar() {

		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();

		try {

			Map<String, Object> filters = new HashMap<>();

			if (this.ftfCodigo.getText() != null && this.ftfCodigo.getText().trim().length() > 0) {

				try {

					BigInteger id = new BigInteger(this.ftfCodigo.getText().trim());
					filters.put("id", id);

				} catch (Exception e) {

					throw new BOValidationException("Código: Erro de validação: " + " Valor incorreto.");

				}

			}

			if (this.ftfLogin.getText() != null && ftfLogin.getText().trim().length() > 0) {

				filters.put("logusu", ftfLogin.getText().trim());

			}

			if (ftfLogin.getText() != null && ftfLogin.getText().trim().length() > 0) {

				filters.put("logusu", ftfLogin.getText().trim());

			}

			Service service = new Service();

			List<ClienteUsuarioVO> clienteUsuarioVOs = service.consultarCliente(0, Integer.MAX_VALUE, null, null,
					filters, DadosConstantesVO.getClienteVO());

			if (clienteUsuarioVOs != null) {

				DecimalFormat decimalFormat = new DecimalFormat("#,##.00");
				DecimalFormat decimalFormatQtd = new DecimalFormat("###,###.00");

				for (ClienteUsuarioVO cliente : clienteUsuarioVOs) {

					RowData rowData = new RowData();
					rowData.getValues().put(0, cliente.getId().toString());
					rowData.getValues().put(1, cliente.getLogin());

					rowData.setElement(cliente);
					tableModel.addRow(rowData);

				}
			}

		} catch (BOValidationException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);

		} catch (BOException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Ocorreu um erro ao executar a operação!",
					JOptionPane.WARNING_MESSAGE);

		}
	}

	public void editarUsuario() {

		if (table.getSelectedRow() < 0) {

			JOptionPane.showMessageDialog(this, "É necessário selecionar um usuário!", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);

		} else {

			try {

				TelaCadastroUsuarioView tcuv = new TelaCadastroUsuarioView();
				UsuarioVO usuari = (UsuarioVO).getRows().get(table.getSelectedRow()).getElement();
				// TODO NESSE PONTO DEVERIA BUSCAR O PRODUTO POR ID NOVAMENTE.

				tcuv.efetuarCadastro(usuari);
				tcuv.setVisible(true);

			} catch (BOValidationException e) {

				JOptionPane.showMessageDialog(null, "Ocorreu um erro " + "ao realizar a operação.", "Erro",
						JOptionPane.ERROR_MESSAGE);

			} catch (BOException e) {

				e.printStackTrace();

			}
		}
	}

	public void limparPesquisa() {

		this.ftfCodigo.setText(null);
		this.ftfLogin.setText(null);
		pesquisar();
	}

	public void fecharJanela() {

		TelaConsultaUsuarioView tcuv = new TelaConsultaUsuarioView();
		tcuv.setVisible(false);
		this.setVisible(false);

	}
}
