package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sicliusu")
public class ClienteUsuarioVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "sq_sicliusu", sequenceName = "sq_sicliusu", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_sicliusu")
	private BigInteger id;
	
	@JoinColumn(name = "usuari", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UsuarioVO usuarioVO;
	
	@JoinColumn(name = "client", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ClienteVO clienteVO;

	public ClienteUsuarioVO() {
		super();
	}

	public ClienteUsuarioVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteUsuarioVO other = (ClienteUsuarioVO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ClienteUsuarioVO [id=" + id + "]";
	}
	
	
	
	

}
