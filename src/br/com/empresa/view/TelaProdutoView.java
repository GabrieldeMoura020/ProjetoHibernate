package br.com.empresa.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.Service;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.DadosConstantesVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.StatusEnum;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class TelaProdutoView extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JFormattedTextField ftfCodBar;
	private JFormattedTextField ftfQtd;
	private JFormattedTextField ftfVlrCompra;
	private JFormattedTextField ftfVlrVenda;
	private JFormattedTextField ftfLucro;
	private JComboBox cbStatus;
	
	private ProdutoVO produtoVO;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TelaProdutoView() {
		
		setResizable(false);
		setTitle("Manutenção de Produto");
		setBounds(100, 100, 576, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(10, 11, 46, 14);
		contentPane.add(lblCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(87, 8, 86, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 36, 61, 14);
		contentPane.add(lblDescricao);
		
		tfDescricao = new JTextField();
		tfDescricao.setBounds(87, 33, 448, 20);
		contentPane.add(tfDescricao);
		tfDescricao.setColumns(10);
		
		JLabel lblCodBar = new JLabel("Cód. barras:");
		lblCodBar.setBounds(10, 61, 61, 14);
		contentPane.add(lblCodBar);
		
		JLabel lblQtd = new JLabel("Qtd. estoque:");
		lblQtd.setBounds(10, 86, 73, 14);
		contentPane.add(lblQtd);
		
		ftfCodBar = new JFormattedTextField();
		ftfCodBar.setBounds(87, 58, 249, 20);
		contentPane.add(ftfCodBar);
		
		ftfQtd = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField("######0.000", ftfQtd);
		ftfQtd.setBounds(87, 83, 61, 20);
		contentPane.add(ftfQtd);
		
		JLabel lblVlrCompra = new JLabel("Vlr. compra:");
		lblVlrCompra.setBounds(10, 111, 61, 14);
		contentPane.add(lblVlrCompra);
		
		ftfVlrCompra = new JFormattedTextField();
		//FORMATAR O VALOR NO PADRÃO BRASILEIRO.
		MascaraJFormattedTextField.formatNumericField(ftfVlrCompra);		
		ftfVlrCompra.setBounds(87, 108, 98, 20);
		contentPane.add(ftfVlrCompra);
		
		JLabel lblVlrVenda = new JLabel("Vlr. venda:");
		lblVlrVenda.setBounds(10, 136, 61, 14);
		contentPane.add(lblVlrVenda);
		
		ftfVlrVenda = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField(ftfVlrVenda);
		ftfVlrVenda.setBounds(87, 133, 98, 20);
		contentPane.add(ftfVlrVenda);
		
		JLabel lblLucro = new JLabel("Lucro:");
		lblLucro.setBounds(10, 161, 46, 14);
		contentPane.add(lblLucro);
		
		ftfLucro = new JFormattedTextField();
//		MascaraJFormattedTextField.formatNumericField(ftfLucro);
		ftfLucro.setEditable(false);
		ftfLucro.setBounds(87, 158, 98, 20);
		contentPane.add(ftfLucro);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 193, 46, 14);
		contentPane.add(lblStatus);
		
		cbStatus = new JComboBox();
		DefaultComboBoxModel dcbm = new DefaultComboBoxModel<>(StatusEnum.values());
		cbStatus.setModel(dcbm);
		dcbm.insertElementAt(null, 0);
		cbStatus.setSelectedIndex(0);
		
		cbStatus.setBounds(87, 189, 113, 22);
		contentPane.add(cbStatus);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				salvar();
				
			}
		});
		btnSalvar.setIcon(new ImageIcon(TelaProdutoView.class.getResource("/br/com/empresa/view/img/salvar.png")));
		btnSalvar.setBounds(332, 227, 98, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fecharTela();
				
			}
		});
		btnFechar.setIcon(new ImageIcon(TelaProdutoView.class.getResource("/br/com/empresa/view/img/cancel.png")));
		btnFechar.setBounds(440, 227, 102, 23);
		contentPane.add(btnFechar);
	}
	
	public void salvar() {
		
		try {
			
			Service servico = new Service();
			
			produtoVO = new ProdutoVO();
			
			String codigo = tfCodigo.getText();
			String descri = tfDescricao.getText().trim();
			String vlrcom = ftfVlrCompra.getText().trim();
			String vlrven = ftfVlrVenda.getText().trim();
			String qtd = ftfQtd.getText().trim();
			String codbar = ftfCodBar.getText().trim();
			String status = null;
			StatusEnum statusEnum = (StatusEnum)cbStatus.getSelectedItem();
			
			if (statusEnum != null) {
				
				status = statusEnum.name();
				
			}
			
			ClienteVO cliente = DadosConstantesVO.getClienteVO();
			produtoVO.setClient(cliente);
			
			if (codigo != null && codigo.length() > 0) {
				
				produtoVO = servico.buscarProdutoPorId(new BigInteger(codigo));
				
			}
			
			produtoVO.setDescri(descri);
			
			if (vlrcom != null && vlrcom.length()> 0) {
				
				vlrcom = vlrcom.replaceAll("\\.", "").replaceAll("," , ".");
				produtoVO.setVlrcom(new BigDecimal(vlrcom));
				 
			}
			
			if (vlrven != null && vlrven.length() > 1) {
				
				vlrven = vlrven.replaceAll("\\.", "").replaceAll("," , ".");
				produtoVO.setVlrven(new BigDecimal(vlrven));
				
			}
			
			if (qtd != null && qtd.length() > 1) {
				
				qtd = qtd.replaceAll("\\.", "").replaceAll("," , ".");
				produtoVO.setQtdest(new BigDecimal(qtd));
				
			}
			
			produtoVO.setCodbar(codbar);
			
			if (status != null) {
				
				produtoVO.setStatus(status);
				
			}
			
			servico.salvarProduto(produtoVO);
			tfCodigo.setText(produtoVO.getId().toString());
			
			JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
			
		} catch (BOValidationException e) {
			
			JOptionPane.showMessageDialog(this, 
					e.getMessage(), 
					"Mensagem de aviso", 
					JOptionPane.WARNING_MESSAGE);
			
			e.printStackTrace();
			
		} catch (BOException e) {
			
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, 
					"Ocorreu um erro ao realizar a operação.", "Erro", 
					JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void fecharTela() {
		
		this.setVisible(false);
		
	}

	public void editar(ProdutoVO aux) {
		
		this.tfCodigo.setText(aux.getId().toString());
		this.tfDescricao.setText(aux.getDescri());
		this.ftfCodBar.setText(aux.getCodbar());
		
		this.ftfVlrCompra.setText(aux.getVlrcom().toPlainString().replaceAll("\\.", ","));
		this.ftfVlrVenda.setText(aux.getVlrven().toPlainString().replaceAll("\\.", ","));
		this.ftfQtd.setText(aux.getQtdest().toPlainString().replaceAll("\\.", ","));
		
		if(aux.getStatus() != null) {
			
			if(aux.getStatus().equals("A")) {
				
				this.cbStatus.setSelectedIndex(1);
				
			} else if (aux.getStatus().equals("I")) {
				
				this.cbStatus.setSelectedIndex(2);				
				
			}
			
		}
		
		//TODO CALCULAR O LUCRO E COLOCAR NO CAMPO POSTEIORMENTE
		
	}
}
