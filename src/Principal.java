import java.math.BigDecimal;
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
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;
import br.com.empresa.dao.HibernateUtil;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class Principal {

	public static void main(String[] args) {

		Principal p = new Principal();
		// p.consultarProdutoId();
		// p.consultarProdutoSimples();
		// p.consultarProdutoSimplesComTupla();
		// p.inserirProduto();
		// p.editarPriduto();
		// p.excluirProduto();
		// p.consultarProdutoTuplaJoin();
		// p.consultarProdutoTuplaJoinPaginacao(1, 5);
		// p.consultarProdutoSimplesCount();

		// ClienteVO cliente = new ClienteVO();
		// cliente.setId(BigInteger.ONE);
		// Integer first = 0;
		// Integer pageSize = 5;
//		Map<String, Object> filters = new HashMap<String, Object>();
//		filters.put("descri", "MANTEIGA");
//		filters.put("status", "A");
//		String sortField = "descri";
//		String sortOrder = "asc";

//		p.consultarProdutoComTuplaJoinPaginacaoFiltroCompleto(first, pageSize, sortField, sortOrder, filters, cliente);

		// CONSULTAS ESPECIAIS
		// p.consultarProdutoMaiorIgual();
		// p.consultarProdutoComSubQuery();
		// p.consultarProdutoComBetWeen();
		// p.consultarProdutoComIn();
		// p.consultarProdutoComFetchEager();

		// CONSULTAS COM JPQL
		// p.consultaProdutoJPQL();
		// p.consultarProdutoJPQLJoin();
		// p.consultarProdutoJPQLComBetween();
		p.consultarProdutoJPQLComMax();

		System.exit(0);

	}

	private void consultarProdutoJPQLComMax() {

		System.out.println("-------------COMEÇANDO CONSULTA COM JPQL E BETWEEN EM JOIN");

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		TypedQuery<ProdutoVO> query = em.createQuery(
				"SELECT max(p) FROM ProdutoVO AS p " + "LEFT JOIN p.client AS c " + "WHERE p.client = :client AND "
						+ "p.client.descri like :nomecliente AND" + "p.id between :inicio and :termino",
				ProdutoVO.class);

		ClienteVO aux = new ClienteVO();
		aux.setId(BigInteger.ONE);

		query.setParameter("client", aux);
//		query.setParameter("nomecliente", "%A%");
//		query.setParameter("inicio", new BigInteger("1"));
//		query.setParameter("termino", new BigInteger("10"));
//		query.setMaxResults(5);

		List<ProdutoVO> listaProdutos = query.getResultList();
		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto >> " + produtoVO.getId() + " - " + produtoVO.getDescri() + " - "
					+ produtoVO.getQtdest() + " - " + produtoVO.getClient().getDescri());

		}

		em.close();

		System.out.println("-------------TERMINANDO CONSULTA COM JPQL E BETWEEN");

	}

	private void consultarProdutoJPQLComBetween() {

		System.out.println("-------------COMEÇANDO CONSULTA COM JPQL E BETWEEN EM JOIN");

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		TypedQuery<ProdutoVO> query = em.createQuery(
				"SELECT p FROM ProdutoVO AS p " + "LEFT JOIN p.client AS c " + "WHERE p.client = :client AND "
						+ "p.client.descri like :nomecliente AND" + "p.id between :inicio and :termino",
				ProdutoVO.class);

		ClienteVO aux = new ClienteVO();
		aux.setId(BigInteger.ONE);

		query.setParameter("client", aux);
		query.setParameter("nomecliente", "%A%");
		query.setParameter("inicio", new BigInteger("1"));
		query.setParameter("termino", new BigInteger("10"));
		query.setMaxResults(5);

		List<ProdutoVO> listaProdutos = query.getResultList();
		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto >> " + produtoVO.getId() + " - " + produtoVO.getDescri() + " - "
					+ produtoVO.getQtdest() + " - " + produtoVO.getClient().getDescri());

		}

		em.close();

		System.out.println("-------------TERMINANDO CONSULTA COM JPQL E BETWEEN");

	}

	private void consultarProdutoJPQLJoin() {

		System.out.println("-------------COMEÇANDO CONSULTA COM JPQL EM JOIN");

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		TypedQuery<ProdutoVO> query = em.createQuery("SELECT p FROM ProdutoVO AS p " + "LEFT JOIN p.client AS c "
				+ "WHERE p.client = :client AND " + "p.client.descri like :nomecliente", ProdutoVO.class);

		ClienteVO aux = new ClienteVO();
		aux.setId(BigInteger.ONE);

		query.setParameter("client", aux);
		query.setParameter("nomecliente", "A");
		query.setMaxResults(5);

		List<ProdutoVO> listaProdutos = query.getResultList();
		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto >> " + produtoVO.getId() + " - " + produtoVO.getDescri() + " - "
					+ produtoVO.getQtdest() + " - " + produtoVO.getClient().getDescri());

		}

		em.close();

		System.out.println("-------------TERMINANDO CONSULTA COM JPQL");

	}

	private void consultaProdutoJPQL() {

		System.out.println("-------------COMEÇANDO CONSULTA COM JPQL");

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		TypedQuery<ProdutoVO> query = em.createQuery("SELECT p FROM ProdutoVO AS p WHERE p.client = :client",
				ProdutoVO.class);

		ClienteVO aux = new ClienteVO();
		aux.setId(BigInteger.ONE);

		query.setParameter("client", aux);
		query.setMaxResults(5);

		List<ProdutoVO> listaProdutos = query.getResultList();
		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto >> " + produtoVO.getId() + " - " + produtoVO.getDescri() + " - "
					+ produtoVO.getQtdest() + " - " + produtoVO.getClient().getDescri());

		}

		em.close();

		System.out.println("-------------TERMINANDO CONSULTA COM JPQL");

	}

	private void consultarProdutoComFetchEager() {

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);
		Fetch<ProdutoVO, ClienteVO> clienteFetch = produtoFrom.fetch("client");

		// CLÁUSULA WHERE
		ClienteVO c1 = new ClienteVO();
		c1.setId(BigInteger.ONE);

		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), c1);

		// ORDER BY
		Order produtoOrderBY = cb.asc(produtoFrom.get("descri"));

		// ATRIBUINDO AS CLÁUSULAS AS CONSULTAS
		criteria.select(produtoFrom);
		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBY);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		List<ProdutoVO> listaProdutos = query.getResultList();

		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto: " + produtoVO.getId() + " - " + produtoVO.getDescri() + "- Cliente: "
					+ produtoVO.getClient().getDescri());

		}

		em.close();

	}

	private void consultarProdutoComIn() {

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// CLÁUSULA WHERE
		ClienteVO c1 = new ClienteVO();
		c1.setId(BigInteger.ONE);

		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), c1);

		List<ProdutoVO> valores = new ArrayList<>();
		ProdutoVO p1 = new ProdutoVO(new BigInteger("5"));
		ProdutoVO p2 = new ProdutoVO(new BigInteger("6"));
		ProdutoVO p3 = new ProdutoVO(new BigInteger("7"));
		ProdutoVO p4 = new ProdutoVO(new BigInteger("8"));

		valores.add(p1);
		valores.add(p2);
		valores.add(p3);
		valores.add(p4);

		produtoWhere = cb.and(produtoWhere, produtoFrom.in(valores));

		// ORDER BY
		Order produtoOrderBY = cb.asc(produtoFrom.get("descri"));

		// ATRIBUINDO AS CLÁUSULAS AS CONSULTAS
		criteria.select(produtoFrom);
		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBY);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		List<ProdutoVO> listaProdutos = query.getResultList();

		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto: " + produtoVO.getId() + " - " + produtoVO.getDescri());

		}

		em.close();

	}

	private void consultarProdutoComBetWeen() {

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// CLÁUSULA WHERE
		ClienteVO c1 = new ClienteVO();
		c1.setId(BigInteger.ONE);

		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), c1);

		produtoWhere = cb.and(produtoWhere, cb.between(produtoFrom.get("id"), 1, 5));

		produtoWhere = cb.and(produtoWhere, cb.greaterThanOrEqualTo(produtoFrom.get("id"), 10));

		produtoWhere = cb.and(produtoWhere, cb.lessThanOrEqualTo(produtoFrom.get("id"), 15));

		// ORDER BY
		Order produtoOrderBY = cb.asc(produtoFrom.get("descri"));

		// ATRIBUINDO AS CLÁUSULAS AS CONSULTAS
		criteria.select(produtoFrom);
		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBY);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		List<ProdutoVO> listaProdutos = query.getResultList();

		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto: " + produtoVO.getId() + " - " + produtoVO.getDescri());

		}

		em.close();

	}

	private void consultarProdutoComSubQuery() {

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// CLÁUSULA WHERE
		Predicate produtoWhere = cb.like(cb.lower(produtoFrom.get("descri")), "%banana%");

		// SUBQUERY
		Subquery<Integer> subquery = criteria.subquery(Integer.class);
		Root<ClienteVO> clienteFrom = subquery.from(ClienteVO.class);
		subquery.select(cb.literal(1));

		Predicate subQueryWhere = cb.like(clienteFrom.get("descri"), "%A%");
		subquery.where(subQueryWhere);

		produtoWhere = cb.and(produtoWhere, cb.exists(subquery));

		// ORDER BY
		Order produtoOrderBY = cb.asc(produtoFrom.get("descri"));

		// ATRIBUINDO AS CLÁUSULAS AS CONSULTAS
		criteria.select(produtoFrom);
		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBY);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		List<ProdutoVO> listaProdutos = query.getResultList();

		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto: " + produtoVO.getId() + " - " + produtoVO.getDescri());

		}

		em.close();

	}

	private void consultarProdutoMaiorIgual() {

		// CONSULTAR PRODUTO COM MAIOR IGUAL
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// CLÁUSULA WHERE
		ClienteVO c1 = new ClienteVO();
		c1.setId(BigInteger.ONE);

		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), c1);

		produtoWhere = cb.and(produtoWhere, cb.greaterThanOrEqualTo(produtoFrom.get("id"), 10));

		produtoWhere = cb.and(produtoWhere, cb.lessThanOrEqualTo(produtoFrom.get("id"), 15));

		// ORDER BY
		Order produtoOrderBY = cb.asc(produtoFrom.get("descri"));

		// ATRIBUINDO AS CLÁUSULAS AS CONSULTAS
		criteria.select(produtoFrom);
		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBY);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		List<ProdutoVO> listaProdutos = query.getResultList();

		for (ProdutoVO produtoVO : listaProdutos) {
			System.out.println("Produto: " + produtoVO.getId() + " - " + produtoVO.getDescri());

		}

		em.close();

	}

	private void consultarProdutoComTuplaJoinPaginacaoFiltroCompleto(Integer first, Integer pageSize, String sortField,
			String sortOrder, Map<String, Object> filters, ClienteVO cliente) {

		System.out.println("Começando filtro completo");

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// INNER JOIN
		Join<ProdutoVO, ClienteVO> clienteFrom = produtoFrom.join("client");

		// BUSCANDO CAMPOS INDIVIDUALMENTE
		Path<BigInteger> produtoFrom_Id = produtoFrom.get("id");
		Path<String> produtoFrom_Descri = produtoFrom.get("descri");
		Path<BigDecimal> produtoFrom_Qtdest = produtoFrom.get("qtdest");

		Path<BigInteger> clienteFrom_Id = clienteFrom.get("id");
		Path<String> clienteFrom_Descri = clienteFrom.get("descri");

		// CLÁUSULA SELECT
		criteria.multiselect(produtoFrom_Id, produtoFrom_Descri, produtoFrom_Qtdest, clienteFrom_Id,
				clienteFrom_Descri);

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

					if (pageSize != null) {

						query.setFirstResult(first); // LIMITA A QUANTIDADE DE CONSULTA

					}

					query.setMaxResults(2); // LIMITA A QUANTIDADE DE CONSULTA

					List<Tuple> tuples = query.getResultList();
					List<ProdutoVO> ret = new ArrayList<ProdutoVO>();

					if (tuples != null) {

						for (Tuple tuple : tuples) {

							ClienteVO clienteVO = new ClienteVO(tuple.get(clienteFrom_Id));
							clienteVO.setDescri(tuple.get(clienteFrom_Descri));

							ProdutoVO produtoVO = new ProdutoVO();
							produtoVO.setDescri(tuple.get(produtoFrom_Descri));
							produtoVO.setQtdest(tuple.get(produtoFrom_Qtdest));
							produtoVO.setClient(clienteVO);

							ret.add(produtoVO);

						}

						for (ProdutoVO produtoVO : ret) {
							System.out.println("Produto: " + produtoVO.getDescri() + " do cliente "
									+ produtoVO.getClient().getId());

						}
					}
				}
			}
			em.close();
			System.out.println("UUFAA, ATÉ QUE ENFIM TERMINOU!");

		}
	}

	private void consultarProdutoSimplesCount() {

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// CLÁUSULA SELECT
		Selection<Long> produtoSelect = cb.count(produtoFrom);

		criteria.select(produtoSelect);

		TypedQuery<Long> query = em.createQuery(criteria);

		Long ret = (Long) query.getSingleResult();
		em.close();

		System.out.println("Quantidade no banco: " + ret);

	}

	private void consultarProdutoTuplaJoinPaginacao(Integer first, Integer pageSize) {

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// INNER JOIN
		// Join<ProdutoVO, ClienteVO> clienteFrom = produtoFrom.join("client");

		// LEFT JOIN
		Join<ProdutoVO, ClienteVO> clienteFrom = produtoFrom.join("client", JoinType.LEFT);

		Path<BigInteger> produtoFrom_Id = produtoFrom.get("id");
		Path<String> produtoFrom_Descri = produtoFrom.get("descri");
		Path<BigDecimal> produtoFrom_Qtdest = produtoFrom.get("qtdest");

		Path<BigInteger> clienteFrom_Id = clienteFrom.get("id");
		Path<String> clienteFrom_Descri = clienteFrom.get("descri");

		// CLÁUSULA SELECT
		criteria.multiselect(produtoFrom_Id, produtoFrom_Descri, produtoFrom_Qtdest, clienteFrom_Id,
				clienteFrom_Descri);

		// CLÁSULA WHERE
		Predicate produtoWhere = cb.equal(clienteFrom, BigInteger.ONE);

		// CLÁUSULA ORDER BY
		Order produtoOrderBy = cb.asc(produtoFrom_Descri);

		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBy);

		TypedQuery<Tuple> query = em.createQuery(criteria);

		if (pageSize != null) {

			query.setMaxResults(pageSize); // Limita a quantidade de consulta

		}

		if (pageSize != null) {

			query.setFirstResult(first); // Limita a quantidade de consulta

		}

		query.setMaxResults(2); // Limita a quantidade de consulta

		List<Tuple> tuples = query.getResultList();
		List<ProdutoVO> ret = new ArrayList<ProdutoVO>();

		if (tuples != null) {

			for (Tuple tuple : tuples) {

				ClienteVO clienteVO = new ClienteVO(tuple.get(clienteFrom_Id));
				clienteVO.setDescri(tuple.get(clienteFrom_Descri));

				ProdutoVO produtoVO = new ProdutoVO();
				produtoVO.setDescri(tuple.get(produtoFrom_Descri));
				produtoVO.setQtdest(tuple.get(produtoFrom_Qtdest));
				produtoVO.setClient(clienteVO);

				ret.add(produtoVO);

			}

			for (ProdutoVO produtoVO : ret) {
				System.out
						.println("Produto: " + produtoVO.getDescri() + " do cliente " + produtoVO.getClient().getId());

			}

			em.close();

		}

	}

	private void consultarProdutoTuplaJoin() {

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);
		Join<ProdutoVO, ClienteVO> clienteFrom = produtoFrom.join("client");

		Path<BigInteger> produtoFrom_Id = produtoFrom.get("id");
		Path<String> produtoFrom_Descri = produtoFrom.get("descri");
		Path<BigDecimal> produtoFrom_Qtdest = produtoFrom.get("qtdest");

		Path<BigInteger> clienteFrom_Id = clienteFrom.get("id");
		Path<String> clienteFrom_Descri = clienteFrom.get("descri");

		// CLÁUSULA SELECT
		criteria.multiselect(produtoFrom_Id, produtoFrom_Descri, produtoFrom_Qtdest, clienteFrom_Id,
				clienteFrom_Descri);

		// CLÁSULA WHERE
		Predicate produtoWhere = cb.equal(clienteFrom, BigInteger.ONE);

		// CLÁUSULA ORDER BY
		Order produtoOrderBy = cb.asc(produtoFrom_Descri);

		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBy);

		TypedQuery<Tuple> query = em.createQuery(criteria);
		query.setMaxResults(2); // Limita a quantidade de consulta

		List<Tuple> tuples = query.getResultList();
		List<ProdutoVO> ret = new ArrayList<ProdutoVO>();

		if (tuples != null) {

			for (Tuple tuple : tuples) {

				ClienteVO clienteVO = new ClienteVO(tuple.get(clienteFrom_Id));
				clienteVO.setDescri(tuple.get(clienteFrom_Descri));

				ProdutoVO produtoVO = new ProdutoVO();
				produtoVO.setDescri(tuple.get(produtoFrom_Descri));
				produtoVO.setQtdest(tuple.get(produtoFrom_Qtdest));
				produtoVO.setClient(clienteVO);

				ret.add(produtoVO);

			}

			for (ProdutoVO produtoVO : ret) {
				System.out
						.println("Produto: " + produtoVO.getDescri() + " do cliente " + produtoVO.getClient().getId());

			}

		}

	}

	private void excluirProduto() {

		System.out.println("Excluindo Produto!");

		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			ProdutoVO produto = em.find(ProdutoVO.class, new BigInteger("1"));

			em.remove(produto);
			em.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();

		}

	}

	private void editarPriduto() {

		System.out.println("EDITANDO PRODUTO!");

		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			ProdutoVO produto = em.find(ProdutoVO.class, new BigInteger("1"));
			produto.setDescri("MANTEIGA SALGADA");

			em.merge(produto);
			em.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();

		}

	}

	private void inserirProduto() {

		System.out.println("Inserindo produto!");

		EntityManager em = HibernateUtil.getEntityManager();

		ClienteVO c = new ClienteVO();
		c.setId(BigInteger.ONE);

		ProdutoVO produtoVO = new ProdutoVO();
		produtoVO.setDescri("BANANA CATURRA");
		produtoVO.setCodbar("34.45.68.24");
		produtoVO.setQtdest(new BigDecimal("12.00"));
		produtoVO.setVlrcom(new BigDecimal("4.00"));
		produtoVO.setVlrven(new BigDecimal("2.50"));
		produtoVO.setStatus("A");
		produtoVO.setClient(c);

		try {

			em.getTransaction().begin();
			em.persist(produtoVO);
			em.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			em.getTransaction().rollback();

		}
	}

	private void consultarProdutoSimplesComTupla() {

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		Path<BigInteger> produto_Id = produtoFrom.get("id");
		Path<String> produto_Desri = produtoFrom.get("descri");
		Path<BigDecimal> produto_Qtdest = produtoFrom.get("qtdest");

		criteria.multiselect(produto_Id, produto_Desri, produto_Qtdest);

		// CLÁUSULA WHERE
		ClienteVO c = new ClienteVO(BigInteger.ONE);

		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), c);

		// CLÁUSULA ORDER BY
		Order produtoOrderBy = cb.desc(produtoFrom.get("descri"));

		// ATRIBIUINDO AS CLÁUSULAS À CONSULTA
		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBy);

		TypedQuery<Tuple> query = em.createQuery(criteria);

		List<Tuple> tuples = query.getResultList();
		List<ProdutoVO> ret = new ArrayList<>();

		if (tuples != null) {

			for (Tuple tuple : tuples) {

				ProdutoVO produtoVO = new ProdutoVO();
				produtoVO.setId(tuple.get(produto_Id));
				produtoVO.setDescri(tuple.get(produto_Desri));
				produtoVO.setQtdest(tuple.get(produto_Qtdest));
				ret.add(produtoVO);

				System.out.println("Produto" + produtoVO.getId() + " - " + produtoVO.getDescri());

			}

		}

	}

	private void consultarProdutoSimples() {

		System.out.println("-------------Começando--------------");

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// CLÁUSULA FROM
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// CLÁUSULA WHERE
		ClienteVO c = new ClienteVO(BigInteger.ONE);

		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), c);

		// CLÁUSULA ORDER BY
		Order produtoOrderBy = cb.desc(produtoFrom.get("descri"));

		// ATRIBIUINDO AS CLÁUSULAS À CONSULTA
		criteria.select(produtoFrom);
		criteria.where(produtoWhere);
		criteria.orderBy(produtoOrderBy);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);
		List<ProdutoVO> listaProdutos = query.getResultList();

		for (ProdutoVO produtoVO : listaProdutos) {

			System.out.println("Produto" + produtoVO.getId() + " - " + produtoVO.getDescri());

		}

		System.out.println("-------------Terminou---------------");

	}

	private void consultarProdutoId() {

		EntityManager em = HibernateUtil.getEntityManager();

		ProdutoVO produto = em.find(ProdutoVO.class, new BigInteger("1"));

		System.out.println(">>>" + produto.getDescri());

		em.close();

	}
}