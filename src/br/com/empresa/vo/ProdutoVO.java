package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "esprodut")
public class ProdutoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//CÓDIGO DO PRODUTO
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "sq_esprodut", sequenceName = "sq_esprodut", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_esprodut")
	private BigInteger id;

	//NOME DO PRODUTO
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "descri", nullable = false, length = 100)
	private String descri;

	//CÓDIGO DE BARRAS
	@Basic(optional = true)
	@Size(max = 20)
	@Column(name = "codbar", nullable = true, length = 20)	
	private String codbar;

	//STATUS (A-ATIVO / I-INATIVO)
	@Basic(optional = false)
	@NotNull
	@Column(name = "status", nullable = false, length = 1)
	private String status;
	
	//QUANTIDADE DE ESTOQUE
	@Basic(optional = false)
	@NotNull
	@Column(name = "qtdest", nullable = false, precision = 11, scale = 4)
	private BigDecimal qtdest;

	//VALOR DE VENDA
	@Basic(optional = false)
	@NotNull
	@Column(name = "vlrven", nullable = false, precision = 9, scale = 2)
	private BigDecimal vlrven;
	
	//VALOR DE COMPRA
	@Basic(optional = false)
	@NotNull
	@Column(name = "vlrcom", nullable = false, precision = 9, scale = 2)
	private BigDecimal vlrcom;

	@JoinColumn(name = "client", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ClienteVO client;

	public ProdutoVO() {

		super();

	}

	public ProdutoVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}
	
	public String getCodbar() {
		return codbar;
	}

	public void setCodbar(String codbar) {
		this.codbar = codbar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getQtdest() {
		return qtdest;
	}

	public void setQtdest(BigDecimal qtdest) {
		this.qtdest = qtdest;
	}

	public BigDecimal getVlrven() {
		return vlrven;
	}

	public void setVlrven(BigDecimal vlrven) {
		this.vlrven = vlrven;
	}
	
	public BigDecimal getVlrcom() {
		return vlrcom;
	}

	public void setVlrcom(BigDecimal vlrcom) {
		this.vlrcom = vlrcom;
	}

	public ClienteVO getClient() {
		return client;
	}

	public void setClient(ClienteVO client) {
		this.client = client;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoVO other = (ProdutoVO) obj;
		return Objects.equals(client, other.client);
	}

	@Override
	public String toString() {
		return "ProdutoVO [id=" + id + "]";
	}
	
	

}
