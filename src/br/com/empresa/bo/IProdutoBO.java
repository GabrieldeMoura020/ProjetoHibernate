package br.com.empresa.bo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public interface IProdutoBO {

	/**
	 * Listar todos os produtos de um determinado cliente.
	 * @param first
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filters
	 * @param cliente
	 * @return
	 * @throws BOException
	 * 
	 * @author GabrieldeMoura
	 * 
	 */
	public abstract List<ProdutoVO> consultarProduto(Integer first, Integer pageSize, String sortField, String sortOrder, 
			Map<String, Object> filters, ClienteVO cliente)
	throws BOException;
	
	public abstract void excluirProduto(ProdutoVO produtoVO) 
			throws BOValidationException, BOException;

	public abstract void salvarProduto(ProdutoVO produtoVO) 
			throws BOValidationException, BOException;

	public abstract ProdutoVO buscarProdutoPorId(BigInteger id)
			throws BOException;

	Integer consultarProdutoCount(Map<String, Object> filters, ClienteVO cliente) throws BOException; 


}
