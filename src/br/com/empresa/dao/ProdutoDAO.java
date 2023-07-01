package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public List<ProdutoVO> consultarProduto(Integer first, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> filters, ClienteVO cliente) throws BOException {

		System.out.println("Começando filtro completo");

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);
		Join<ProdutoVO, ClienteVO> clienteFrom = produtoFrom.join("client"); // INNER JOIN

		// CLÁUSULA SELECT
		criteria.multiselect(produtoFrom, clienteFrom);

		// CLÁUSULA WHERE
		Predicate produtoWhere = cb.equal(clienteFrom, cliente);

		if (filters != null) {

			for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {

				String filtersProperty = it.next();
				String filtersValue = filters.get(filtersProperty).toString();

				if (filtersProperty.equals("id")) {

					produtoWhere = cb.and(produtoWhere, cb.equal(produtoFrom.get(filtersProperty), filtersValue));

				} else {

					produtoWhere = cb.and(produtoWhere, cb.like(cb.lower(produtoFrom.get(filtersProperty)),
							"%" + filtersValue.toLowerCase() + "%"));

//					produtoWhere = cb.and(produtoWhere, cb.like(produtoFrom.get(filtersProperty), filtersValue));
				}
			}
		}

		// ORDER BY
		Order produtoOrderBY = cb.asc(produtoFrom.get("descri"));

		if (sortField != null) {

			if (sortOrder.equals("asc")) {

				produtoOrderBY = cb.asc(produtoFrom.get(sortField));

			} else if (sortOrder.equals("descri")) {

				produtoOrderBY = cb.desc(produtoFrom.get(sortField));

			}

		}

		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBY);

		TypedQuery<Tuple> query = em.createQuery(criteria);

		if (pageSize != null) {

			query.setMaxResults(pageSize); // LIMITA A QUANTIDADE DE CONSULTA

		}

		if (first != null) {

			query.setFirstResult(first); // LIMITA A QUANTIDADE DE CONSULTA

		}

		List<Tuple> tuples = query.getResultList();
		List<ProdutoVO> ret = new ArrayList<ProdutoVO>();

		if (tuples != null) {

			for (Tuple tuple : tuples) {

				ClienteVO clienteAux = tuple.get(clienteFrom);

				ProdutoVO produtoVO = tuple.get(produtoFrom);
				produtoVO.setClient(clienteAux);

				ret.add(produtoVO);

			}
		}

		em.close();
		System.out.println("UUFAA, ATÉ QUE ENFIM TERMINOU!");

		return ret;
	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) 
			throws BOValidationException, BOException {
		
		System.out.println("Excluindo Produto!");

		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			ProdutoVO produto = em.find(ProdutoVO.class, produtoVO.getId());

			em.remove(produto);
			em.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);

		} finally {

			em.close();

		}

		
	}

	@Override
	public void salvarProduto(ProdutoVO produtoVO) 
			throws BOValidationException, BOException {
		
		System.out.println("Salvando Produto!");

		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();
			
			if (produtoVO.getId() == null) {
				
				em.persist(produtoVO);
				
			} else {
				
				em.merge(produtoVO);
				
			}
			
			em.getTransaction().commit();
			
		} catch (Exception e) {

			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
			
		} finally {

			em.close();

		}

		
	}

	@Override
	public ProdutoVO buscarProdutoPorId(BigInteger id) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		ProdutoVO produto = em.find(ProdutoVO.class, id);
		
		em.close();
		
		return produto;
	}
}
