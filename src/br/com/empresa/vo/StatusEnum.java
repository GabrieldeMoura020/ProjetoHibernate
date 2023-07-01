package br.com.empresa.vo;

/**
 * 
 * @author GabrieldeMoura
 * @since 06/06/2023
 *
 */
public enum StatusEnum {

	A("Ativo"), I("Inativo");

	private String status;

	private StatusEnum(String descricao) {

		this.status = descricao;

	}

	public String toString() {

		return status;

	}
}