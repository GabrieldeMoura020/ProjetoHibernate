package br.com.empresa.dao;

import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.UsuarioVO;

	/**
	 * Busca todos os clientes de um determinado usuário.
	 * 
	 * @author GabrieldeMoura
	 * @param usuario
	 * @param nomeCliente
	 * @return
	 * @throws BOException
	 */
	public interface IUsuarioClienteDAO {

		public abstract List<ClienteUsuarioVO> listarClientesUsuario(UsuarioVO usuario, String nomeCliente) throws BOException;

}
