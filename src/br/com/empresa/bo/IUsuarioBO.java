package br.com.empresa.bo;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public interface IUsuarioBO {

	/**
	 * 
	 * A partir do login e da senha retorna o uusário de sistema. 
	 * A senha nesse caso não é criptografada.
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws BOValidationException
	 * @throws BOException           - Parâmentros não informados.
	 */
	public abstract UsuarioVO buscarUsuario(String login, String senha) throws BOValidationException, BOException;

}
