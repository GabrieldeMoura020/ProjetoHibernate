package br.com.empresa.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioDAO implements IUsuarioDAO {

	@Override
	public UsuarioVO buscarUsuario(String login, String senha) throws BOValidationException, BOException {

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UsuarioVO> criteria = cb.createQuery(UsuarioVO.class);

		// CLÁULULA FROM
		Root<UsuarioVO> usuarioFrom = criteria.from(UsuarioVO.class);

		// CLÁULULA WHERE
		Predicate usuarioWhere = cb.equal(cb.lower(usuarioFrom.get("logusu")), login.toLowerCase());
		usuarioWhere = cb.and(usuarioWhere, cb.equal(usuarioFrom.get("senusu"), senha));

		criteria.select(usuarioFrom);
		criteria.where(usuarioWhere);

		TypedQuery<UsuarioVO> query = em.createQuery(criteria);

		UsuarioVO usuario = null;

		try {

			usuario = query.getSingleResult();

		} catch (NoResultException e) {

		}

		em.close();

		return usuario;

	}
}