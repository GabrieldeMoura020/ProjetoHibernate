package br.com.empresa.dao;

import java.math.BigInteger;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.UsuarioVO;

/**
 * Interface responsável por definir os métodos de persistência do usuário.
 * 
 * @author GabrieldeMoura
 * @since 30/05/2023
 *
 * 12456 - GabrieldeMoura
 */

public interface IClienteDAO {
	
	/**
	 * 
	 * A partir do login e da senha retorna o uusário de sistema.
	 * A senha nesse caso não é criptografada. 
	 * 
	 * @param id
	 * @param usuarioVO
	 * @param clienteVO
	 * @return
	 * @throws BOValidationException
	 * @throws BOException - Parâmentros não informados.
	 */
	public abstract ClienteUsuarioVO buscarCliente(BigInteger id, UsuarioVO usuarioVO, ClienteVO clienteVO)
	throws BOValidationException, BOException;

}