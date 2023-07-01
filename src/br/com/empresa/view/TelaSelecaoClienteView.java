package br.com.empresa.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.empresa.exception.BOException;
import br.com.empresa.service.Service;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.DadosConstantesVO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaSelecaoClienteView extends JFrame {

	private JPanel contentPane;
	private JTextField tfFiltro;
	private JList list;

	/**
	 * Classe static que armazena o usuário e cliente selecionado.
	 * 
	 * @author GabrieldeMoura
	 * @since 29/05/2023
	 *
	 *        Create the frame.
	 */
	public TelaSelecaoClienteView() {
		setResizable(false);
		setTitle("Seleção de Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 11, 46, 14);
		contentPane.add(lblFiltro);

		tfFiltro = new JTextField();
		tfFiltro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				carregarValoresListModel();

			}
		});

		tfFiltro.setBounds(10, 29, 438, 20);
		contentPane.add(tfFiltro);
		tfFiltro.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 438, 180);
		contentPane.add(scrollPane);

		// Criei um model
		ListModel<ClienteVO> listModel = new DefaultListModel<ClienteVO>();

		list = new JList();
		// Atribui o model ao Just
		list.setModel(listModel);

		scrollPane.setViewportView(list);

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				selecionarCliente();

			}
		});

		btnSelecionar.setIcon(
				new ImageIcon(TelaSelecaoClienteView.class.getResource("/br/com/empresa/view/img/selection.png")));
		btnSelecionar.setBounds(10, 250, 115, 23);
		contentPane.add(btnSelecionar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				cancelar();

			}
		});

		btnCancelar
				.setIcon(new ImageIcon(TelaSelecaoClienteView.class.getResource("/br/com/empresa/view/img/error.png")));
		btnCancelar.setBounds(345, 251, 103, 23);
		contentPane.add(btnCancelar);

		carregarValoresListModel();

	}

	private void cancelar() {

		LoginView lv = new LoginView();
		lv.setVisible(true);
		this.setVisible(true);

	}

	private void selecionarCliente() {

		DefaultListModel defaultListModel = (DefaultListModel) list.getModel();

		// Se clicou em selecionar e havia algo selecionado
		if (list.getSelectedIndex() >= 0) {

			ClienteVO clienteVO = (ClienteVO) defaultListModel.get(list.getSelectedIndex());

			DadosConstantesVO.setClienteVO(clienteVO);

			// Exibe a tela princioal do sistema
			TelaPrincipalView tpv = new TelaPrincipalView();
			tpv.setVisible(true);

			// Oculto a tela atual
			this.setVisible(false);

		} else {

			JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente para continuar!", "Validação",
					JOptionPane.WARNING_MESSAGE);

		}

	}

	private void carregarValoresListModel() {

		try {

			Service servico = new Service();

			String nomeFiltro = null;

			if (tfFiltro.getText() != null && tfFiltro.getText().trim().length() > 0) {

				nomeFiltro = tfFiltro.getText().trim();

			}

			List<ClienteUsuarioVO> usuarioClienteVOs = servico.listarClientesUsuario(DadosConstantesVO.getUsuarioVO(),
					nomeFiltro);

			DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
			defaultListModel.clear();

			for (ClienteUsuarioVO usuarioClienteVO : usuarioClienteVOs) {

				defaultListModel.addElement(usuarioClienteVO.getClienteVO());

			}

		} catch (BOException e) {

			e.printStackTrace();

		}
	}
}