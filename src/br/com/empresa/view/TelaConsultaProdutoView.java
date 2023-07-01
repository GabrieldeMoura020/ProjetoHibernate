package br.com.empresa.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.Service;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.vo.DadosConstantesVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.StatusEnum;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class TelaConsultaProdutoView extends JFrame {

	private JPanel contentPane;
	private JComboBox cbStatus;
	private TableModel tableModel;
	private JTable table;
	private JFormattedTextField ftfCodigo;
	private JFormattedTextField ftfDescricao;
	private JFormattedTextField ftfCodBar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TelaConsultaProdutoView() {
		setResizable(false);
		setTitle("Manutenção de Produto");
		setBounds(100, 100, 613, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 577, 102);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pesquisar();
				
			}
		});
		btnPesquisar.setIcon(
				new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/pesquisar.png")));
		btnPesquisar.setBounds(450, 68, 117, 23);
		panel.add(btnPesquisar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limparPesquisa();
				
			}
		});
		btnLimpar.setIcon(
				new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/eraser.png")));
		btnLimpar.setBounds(338, 68, 102, 23);
		panel.add(btnLimpar);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 11, 46, 14);
		panel.add(lblCodigo);

		ftfCodigo = new JFormattedTextField();
		String mascaraCodigo = "##########";
		MascaraJFormattedTextField.formatField(mascaraCodigo, ftfCodigo);
		ftfCodigo.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		ftfCodigo.setBounds(49, 8, 85, 20);
		panel.add(ftfCodigo);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(144, 11, 46, 14);
		panel.add(lblDescricao);

		ftfDescricao = new JFormattedTextField();
		ftfDescricao.setBounds(207, 8, 345, 20);
		panel.add(ftfDescricao);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 36, 46, 14);
		panel.add(lblStatus);

		cbStatus = new JComboBox();
		DefaultComboBoxModel dcbm = new DefaultComboBoxModel<>(StatusEnum.values());
		cbStatus.setModel(dcbm);
		dcbm.insertElementAt(null, 0);
		cbStatus.setSelectedIndex(0);

		cbStatus.setBounds(49, 32, 85, 22);
		panel.add(cbStatus);

		JLabel lblCodBarl = new JLabel("Cód. barras");
		lblCodBarl.setBounds(144, 36, 68, 14);
		panel.add(lblCodBarl);

		ftfCodBar = new JFormattedTextField();
		ftfCodBar.setBounds(207, 33, 345, 20);
		panel.add(ftfCodBar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(
				new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/adicionar.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirTelaEdicaoProduto();

			}

		});
		btnAdicionar.setBounds(10, 123, 105, 23);
		contentPane.add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				editarRegistro();
				
			}
		});
		btnEditar.setIcon(
				new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/editar.png")));
		btnEditar.setBounds(122, 123, 89, 23);
		contentPane.add(btnEditar);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				fecharJanela();

			}
		});

		btnFechar.setIcon(
				new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/cancel.png")));
		btnFechar.setBounds(482, 415, 105, 23);
		contentPane.add(btnFechar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				excluirRegistro();
				
			}
		});
		btnExcluir.setIcon(
				new ImageIcon(TelaConsultaProdutoView.class.getResource("/br/com/empresa/view/img/Fechar.png")));
		btnExcluir.setBounds(221, 123, 105, 23);
		contentPane.add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane.setBounds(10, 157, 577, 248);
		contentPane.add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Qtd. Estoque");
		tableModel.addColumn("Status");
		tableModel.addColumn("Vlr. Compra");
		tableModel.addColumn("Vlr. Venda");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(100);
		tableColumnModel.getColumn(1).setPreferredWidth(100);
		tableColumnModel.getColumn(2).setPreferredWidth(110);
		tableColumnModel.getColumn(3).setPreferredWidth(100);
		tableColumnModel.getColumn(4).setPreferredWidth(80);
		tableColumnModel.getColumn(5).setPreferredWidth(80);

		scrollPane.setViewportView(table);

		pesquisar();

	}
	
	public void editarRegistro() {
		
		if (table.getSelectedRow() < 0) {
			
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
			
		} else {
			  
			try {
				
				TelaProdutoView tpv = new TelaProdutoView();
				ProdutoVO aux = (ProdutoVO)tableModel.getRows().get(table.getSelectedRow()).getElement();
				//TODO NESSE PONTO DEVERIA BUSCAR O PRODUTO POR ID NOVAMENTE.
				
				tpv.editar(aux);
				tpv.setVisible(true);
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Ocorreu um erro " + "ao realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		
	}
	
	public void excluirRegistro() {
		
		if (table.getSelectedRow() < 0) {
			
			JOptionPane.showMessageDialog(this,"É necessário selecionar um " + "registro!","Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
			
		} else {
			Object[] optins = {"Sim!", "Não"};
			int n = JOptionPane.showOptionDialog(null, 
					"Deseja realmente excluir o registro?", 
					"Confirmação", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					optins, 
					optins[1]);
		 if (n == 0) {
			
			ProdutoVO produto = (ProdutoVO)tableModel.getRows().get(table.getSelectedRow()).getElement();
			
			try {
				
				Service servico = new Service();
				servico.excluirProduto(produto);
				
				JOptionPane.showMessageDialog(this, 
											"Registro excluído com sucesso!",
											"Mensagem de aviso", 
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
	
	public void limparPesquisa() {
		
		this.ftfCodBar.setText(null);
		this.ftfDescricao.setText(null);
		this.cbStatus.setSelectedItem(null);
		this.ftfCodigo.setText(null);
		pesquisar();
		
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
			
			if (this.ftfDescricao.getText() != null && ftfDescricao.getText().trim().length() > 0) {
				
				filters.put("descri", ftfDescricao.getText().trim());
				
			}
			
			if (cbStatus.getSelectedItem() != null) {
				
				StatusEnum statusEnum = (StatusEnum)this.cbStatus.getSelectedItem();
				filters.put("status", statusEnum.name());
				
			}
			
			if (ftfCodBar.getText() != null && ftfCodBar.getText().trim().length() > 0) {
				
				filters.put("codbar", ftfCodBar.getText().trim());
				
			}

			Service service = new Service();

			List<ProdutoVO> produtoVOs = service.consultarProduto(0, Integer.MAX_VALUE, 
																				null, 
																				null,
																				filters,
																				DadosConstantesVO.getClienteVO());

			if (produtoVOs != null) {

				DecimalFormat decimalFormat = new DecimalFormat("#,##.00");
				DecimalFormat decimalFormatQtd = new DecimalFormat("###,###.00");

				for (ProdutoVO produtoVO : produtoVOs) {

					RowData rowData = new RowData();
					rowData.getValues().put(0, produtoVO.getId().toString());
					rowData.getValues().put(1, produtoVO.getDescri());

					if (produtoVO.getQtdest() != null) {

						rowData.getValues().put(2, decimalFormatQtd.format(produtoVO.getQtdest()));

					}

					if (produtoVO.getStatus().equals("A")) {

						rowData.getValues().put(3, "Ativo");

					} else if (produtoVO.getStatus().equals("I")) {

						rowData.getValues().put(3, "Inativo");

					}

					if (produtoVO.getVlrcom() != null) {

						rowData.getValues().put(4, decimalFormat.format(produtoVO.getVlrcom()));

					}

					if (produtoVO.getVlrven() != null) {

						rowData.getValues().put(5, decimalFormat.format(produtoVO.getVlrven()));

					}

					rowData.setElement(produtoVO);
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

	public void fecharJanela() {

		TelaConsultaProdutoView tcpv = new TelaConsultaProdutoView();
		tcpv.setVisible(false);
		this.setVisible(false);

	}

	public void abrirTelaEdicaoProduto() {

		TelaProdutoView tpv = new TelaProdutoView();
		tpv.setVisible(true);

	}
}
