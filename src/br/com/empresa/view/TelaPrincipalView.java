package br.com.empresa.view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.awt.event.InputEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipalView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TelaPrincipalView() {
		setTitle("NARWAL SISTEMAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/folder.png")));
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);

		JMenuItem mntmArquivo = new JMenuItem("Sair");
		
		mntmArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sair();

			}
		});
		
		mntmArquivo.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/sair.png")));
		mntmArquivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnArquivo.add(mntmArquivo);

		JMenu mnManutencao = new JMenu("Manutenção");
		mnManutencao.setIcon(
				new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/manutencao1.png")));
		mnManutencao.setMnemonic('M');
		menuBar.add(mnManutencao);

		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirTelaConsultaProduto();

			}
		});

		JMenuItem mntmCliente = new JMenuItem("Usuário");
		mntmCliente.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/novo.png")));
		mnManutencao.add(mntmCliente);
		mntmProduto.setIcon(
				new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/new-product.png")));
		mnManutencao.add(mntmProduto);

		JMenu mnUtilitario = new JMenu("Utilitário");
		mnUtilitario
				.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/utilitario.png")));
		mnUtilitario.setMnemonic('U');
		menuBar.add(mnUtilitario);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnUtilitario.add(mntmNewMenuItem);

		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/ajuda.png")));
		mnAjuda.setMnemonic('J');
		menuBar.add(mnAjuda);

		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mntmAjuda.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/support.png")));
		mntmAjuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnAjuda.add(mntmAjuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		try {

			InputStream streamLogo = getClass().getResourceAsStream("senac_logo.png");

			BufferedImage img = ImageIO.read(streamLogo);
			ImageIcon imageIcon = new ImageIcon(img);

			JLabel centerLabel = new JLabel(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/senac_logo.png")));

			JPanel main = new JPanel(new BorderLayout());
			main.add(centerLabel, BorderLayout.CENTER);

			getContentPane().add(main, BorderLayout.CENTER);

			JToolBar toolBar = new JToolBar();
			main.add(toolBar, BorderLayout.NORTH);

			JButton btnProduto = new JButton("Produto");
			btnProduto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					abrirTelaConsultaProduto();

				}
			});
			btnProduto.setIcon(
					new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/new-product.png")));
			toolBar.add(btnProduto);

			JButton btnCliente = new JButton("Usuário");

			btnCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					abrirTelaCadastroUsuario();

				}
			});

			btnCliente.setIcon(new ImageIcon(TelaPrincipalView.class.getResource("/br/com/empresa/view/img/novo.png")));
			toolBar.add(btnCliente);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abir a tela!", "Erro!", JOptionPane.ERROR_MESSAGE);

		}

	}
	
	public void sair() {

		TelaPrincipalView tpv = new TelaPrincipalView();
		tpv.setVisible(false);
		this.setVisible(false);

	}

	public void abrirTelaCadastroUsuario() {

		TelaCadastroUsuarioView tcu = new TelaCadastroUsuarioView();
		tcu.setVisible(true);

	}

	public void abrirTelaConsultaProduto() {

		TelaConsultaProdutoView tcpv = new TelaConsultaProdutoView();
		tcpv.setVisible(true);

	}

}