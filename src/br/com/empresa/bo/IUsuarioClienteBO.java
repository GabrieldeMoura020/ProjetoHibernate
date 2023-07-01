package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.UsuarioVO;

/**
 * Busca todos os clientes de um determinado usuário.
 * 
 * @param usuario
 * @param nomeCliente
 * @author GabrieldeMoura
 * @return
 * @throws BOException
 */
public interface IUsuarioClienteBO {

	public abstract List<ClienteUsuarioVO> listarClientesUsuario(UsuarioVO usuario, String nomeCliente) throws BOException;

}
