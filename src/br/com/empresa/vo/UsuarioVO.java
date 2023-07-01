package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "siusuari")
public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	// CÓDIGO DA TABELA
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "sq_siusuari", sequenceName = "sq_siusuari", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_siusuari")
	private BigInteger id;

	// LOGIN - 30 CARACTERES
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "logusu", nullable = false, length = 30)
	private String logusu;

	// SENHA - 100 CARACTERES
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "senusu", nullable = false, length = 100)
	private String senusu;

	public UsuarioVO() {
		super();
	}

	public UsuarioVO(@NotNull BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getLogusu() {
		return logusu;
	}

	public void setLogusu(String logusu) {
		this.logusu = logusu;
	}

	public String getSenusu() {
		return senusu;
	}

	public void setSenusu(String senusu) {
		this.senusu = senusu;
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
		UsuarioVO other = (UsuarioVO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UsuarioVO [id=" + id + "]";
	}
}