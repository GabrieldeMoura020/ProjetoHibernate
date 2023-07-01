package br.com.empresa.bo;

import br.com.empresa.dao.IUsuarioDAO;
import br.com.empresa.dao.UsuarioDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioBO implements IUsuarioBO {

	@Override
	public UsuarioVO buscarUsuario(String login, String senha) throws BOValidationException, BOException {

		if (login == null || login.length() <=3) {
			
			throw new BOValidationException("Login: Erro de validação: " + " valor requerido.");
			
		}
		
		if (senha == null || senha.length() == 0) {
			
			throw new BOValidationException("Senha: Erro de validação: " + " valor requerido.");
			
		}
		
		IUsuarioDAO usuarioDAO = new UsuarioDAO();
		
		return usuarioDAO.buscarUsuario(login, senha);

	}
}