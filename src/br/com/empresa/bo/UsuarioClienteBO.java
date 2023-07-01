package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.dao.IUsuarioClienteDAO;
import br.com.empresa.dao.UsuarioClienteDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioClienteBO implements IUsuarioClienteBO {

	@Override
	public List<ClienteUsuarioVO> listarClientesUsuario(UsuarioVO usuario, String nomeCliente) throws BOException {
		
		if (usuario == null || usuario.getId() == null) {
			
			throw new BOException("Houve uma tentativa de listar clientes de um usuário sem informar o usuário");
			
		}
		
		IUsuarioClienteDAO usuarioClienteDAO = new UsuarioClienteDAO();
		
		return usuarioClienteDAO.listarClientesUsuario(usuario, nomeCliente);
	}
	
	

}
