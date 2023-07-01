package br.com.empresa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.ClienteUsuarioVO;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioClienteDAO implements IUsuarioClienteDAO {

	@Override
	public List<ClienteUsuarioVO> listarClientesUsuario(UsuarioVO usuario, String nomeCliente) throws BOException {

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);

		// CLÁUSULA FROM
		Root<ClienteUsuarioVO> usuarioClienteFrom = criteria.from(ClienteUsuarioVO.class);
		Join<ClienteUsuarioVO, ClienteVO> clienteFrom = usuarioClienteFrom.join("clienteVO");

		// CLÁUSULA SELECT
		criteria.multiselect(usuarioClienteFrom, clienteFrom);

		// CLÁUSULA WHERE
		Predicate usuarioClienteWhere = cb.equal(usuarioClienteFrom.get("usuarioVO"), usuario);

		if (nomeCliente != null) {

			usuarioClienteWhere = cb.and(usuarioClienteWhere, cb.like(cb.lower(clienteFrom.get("descri")),
					"%" + nomeCliente.toLowerCase() + "%"));

		}

		// CLÁUSULA ORDER BY
		Order usuarioClienteOrderBy = cb.asc(clienteFrom.get("descri"));

		// ATRIBIUINDO AS CLÁUSULAS À CONSULTA
		criteria.where(usuarioClienteWhere);
		criteria.orderBy(usuarioClienteOrderBy);

		TypedQuery<Tuple> query = em.createQuery(criteria);

		List<Tuple> tuples = query.getResultList();
		List<ClienteUsuarioVO> ret = new ArrayList<ClienteUsuarioVO>();

		if (tuples != null) {

			for (Tuple tuple : tuples) {

				ClienteVO cliente = tuple.get(clienteFrom);
				ClienteUsuarioVO usuarioClienteVO = tuple.get(usuarioClienteFrom);
				usuarioClienteVO.setClienteVO(cliente);
				ret.add(usuarioClienteVO);

			}
		}

		// FECHA O ENTYTY MANAGER
		em.close();

		return ret;
	}
}