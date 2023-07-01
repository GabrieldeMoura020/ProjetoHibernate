package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.dao.IUsuarioClienteDAO;
import br.com.empresa.dao.UsuarioClienteDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class ClienteBO implements IClienteBO{
	
	public List<ClienteVO> listarClientes(UsuarioVO usuario, String nomeCliente) throws BOException {
		
		if (usuario == null || usuario.getId() == null) {
			
			throw new BOException("Houve uma tentativa de listar clientes de um usuário sem informar o usuário");
			
		}
		
		IClienteBO clienteBO = new ClienteBO();
		
		return ClienteBO.listarClientesUsuario(usuario, nomeCliente);
	}

	@Override
	public ClienteUsuarioVO buscarUsuario(String login, String senha) throws BOValidationException, BOException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
