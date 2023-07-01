package br.com.empresa.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.bo.IProdutoBO;
import br.com.empresa.bo.IUsuarioBO;
import br.com.empresa.bo.IUsuarioClienteBO;
import br.com.empresa.bo.ProdutoBO;
import br.com.empresa.bo.UsuarioBO;
import br.com.empresa.bo.UsuarioClienteBO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.UsuarioVO;

public class Service {

	public UsuarioVO buscarUsuario(String login, String senha) throws BOValidationException, BOException {

		IUsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.buscarUsuario(login, senha);
	}

	/**
	 * Busca todos os clientes de um determinado usuário.
	 * 
	 * @param usuario
	 * @param nomeCliente
	 * @author GabrieldeMoura
	 * @return
	 * @throws BOException
	 */
		public List<ClienteUsuarioVO> listarClientesUsuario(UsuarioVO usuario, String nomeCliente)
				throws BOException{
			
			IUsuarioClienteBO usuarioClienteBO = new UsuarioClienteBO();
			
			return usuarioClienteBO.listarClientesUsuario(usuario, nomeCliente);
			
		}
		
		public List<ProdutoVO> consultarProduto(Integer first, Integer pageSize, String sortField, String sortOrder,
				Map<String, Object> filters, ClienteVO cliente) throws BOException {
			
			IProdutoBO produtoBO = new ProdutoBO();
			
			return produtoBO.consultarProduto(first, pageSize, sortField, sortOrder, filters, cliente);
			
		}
		

		
		public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {
			
			IProdutoBO produtoBO = new ProdutoBO();
			produtoBO.excluirProduto(produtoVO);
			
		}

		
		public void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {
			
			IProdutoBO produtoBO = new ProdutoBO();
			produtoBO.salvarProduto(produtoVO);
			
		}
		
		public ProdutoVO buscarProdutoPorId(BigInteger id) throws BOException {
			
			IProdutoBO produtoBO = new ProdutoBO();
			return produtoBO.buscarProdutoPorId(id);
			
		}

	}

