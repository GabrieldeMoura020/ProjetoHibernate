package br.com.empresa.bo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import br.com.empresa.dao.HibernateUtil;
import br.com.empresa.dao.IProdutoDAO;
import br.com.empresa.dao.ProdutoDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

/**
 * 
 * @author GabrieldeMoura
 * @since 06/06/2023
 *
 */
public class ProdutoBO implements IProdutoBO {

	@Override
	public List<ProdutoVO> consultarProduto(Integer first, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> filters, ClienteVO cliente) throws BOException {

		IProdutoDAO produtoDAO = new ProdutoDAO();

		return produtoDAO.consultarProduto(first, pageSize, sortField, sortOrder, filters, cliente);

	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {
		
		if (produtoVO == null || produtoVO.getId() == null) {
			throw new BOException("Objeto nulo ou inválido!");
			
			
		} else {
			//TODO: NÃO ESQUECER SENÃO VAI DAR MERDA NA NARWAL
			//Seria nesse ponto que haveria uma consulta buscando se o produto 
			//foi utilizado em outro ponto da aplicação. Se sim, iriamos disparar um throw new
			//BOValidationException("Produto: erro de validação: Cadastro ... ")
		}
		
		IProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.excluirProduto(produtoVO);
		
	}

	@Override
	public void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {
		
		if (produtoVO == null) {
			
			throw new BOException(); 
			
		} else if(produtoVO.getClient() == null) {
			
			throw new BOException(); 
			
		} else if (produtoVO.getDescri() == null || produtoVO.getDescri().trim().length() == 0) {
			
			throw new BOValidationException("Descrição: erro de validação. "
					+ "Preenchimento obrigatório.");
			
		} else if (produtoVO.getStatus().equals("A")) { //Se for ativo
			
			if(produtoVO.getQtdest() == null) {
				
				throw new BOValidationException("Qtd. Etoque: erro de validação");
				
			} else if (produtoVO.getVlrcom() == null) {
				
				throw new BOValidationException("Valor de compra: erro de validação");
				
			} else if (produtoVO.getVlrven() == null) {
				
				throw new BOValidationException("Valor de venda: erro de validação");
				
			}
		} else {
			//TODO: VERIFICAR SE EXISTEM DOIS PRODUTOS COM NOME SEMELHANTE.
			//SE TIVER, IMPEDIR QUE SEJA SALVO. A MENOS QUE SEJA COM ID IGUAL.
		}
		
		IProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvarProduto(produtoVO);
		
	}
	
	
	public ProdutoVO buscarProdutoPorId(BigInteger id) throws BOException {
		
		IProdutoDAO produtoDAO = new ProdutoDAO();
		
		return produtoDAO.buscarProdutoPorId(id);
	}

	@Override
	public Integer consultarProdutoCount(Map<String, Object> filters, ClienteVO cliente) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}
}
