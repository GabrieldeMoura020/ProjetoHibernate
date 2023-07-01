package br.com.empresa;

import java.awt.EventQueue;

import br.com.empresa.view.LoginView;

public class PrincipalInterface {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
