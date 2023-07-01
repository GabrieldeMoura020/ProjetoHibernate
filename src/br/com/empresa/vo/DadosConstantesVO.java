package br.com.empresa.vo;

/**
 * Classe static que armazena o usuário e cliente selecionado.
 * 
 * @author GabrieldeMoura
 * @since 05/06/2023
 *
 */

public class DadosConstantesVO {

	public static UsuarioVO usuarioVO;

	public static ClienteVO clienteVO;

	public static UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public static void setUsuarioVO(UsuarioVO usuarioVO) {
		DadosConstantesVO.usuarioVO = usuarioVO;
	}

	public static ClienteVO getClienteVO() {
		return clienteVO;
	}

	public static void setClienteVO(ClienteVO clienteVO) {
		DadosConstantesVO.clienteVO = clienteVO;
	}
}
