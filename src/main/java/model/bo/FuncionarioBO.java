package model.bo;

import java.util.List;

import model.dao.FuncionarioDAO;
import model.dto.FuncionarioDTO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;
import model.vo.Funcionario;

public class FuncionarioBO {
	private FuncionarioDAO dao = new FuncionarioDAO();

	public Funcionario inserir(Funcionario novoFuncionario) throws CampoInvalidoException {
		if (dao.cpfJaUtilizado(novoFuncionario.getCpf())) {
			throw new CampoInvalidoException("CPF informado já foi utilizado");
		}
		// Caso CPF não utilizado -> salvar e devolver o funcionario
		return dao.inserir(novoFuncionario);
	}

	public boolean atualizar(Funcionario funcionarioAlterado) throws CampoInvalidoException {
		Funcionario funcionarioOriginal = dao.consultarPorId(funcionarioAlterado.getId());

		if (funcionarioAlterado.getCpf() != funcionarioOriginal.getCpf()) {
			throw new CampoInvalidoException("CPF não pode ser alterado");
		}
		return dao.atualizar(funcionarioAlterado);
	}

	/*
	 * REGRA: O funcionario só poderá ser excluído caso a dataFim da Prestacao de
	 * seus serviços estiver setada.
	 * 
	 * public boolean excluir(int id) throws CException { List<Telefone>
	 * telefonesDoCliente = dao.consultarPorId(id).getTelefones();
	 * 
	 * if (telefonesDoCliente != null && !telefonesDoCliente.isEmpty()) { throw new
	 * ClienteComTelefoneException("Cliente possui telefone(s) associado(s) e não pode ser excluído"
	 * ); }
	 * 
	 * return dao.excluir(id); }
	 */

	public Funcionario consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Funcionario> consultarTodos() {
		return dao.consultarTodos();
	}

	public String gerarPlanilha(List<FuncionarioDTO> funcionarios, String caminho) {
		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaFuncionarios(funcionarios, caminho);
	}

	// REGRA 1: Apenas a gerência pode cadastrar novos funcionários e visualizar os
	// serviços prestados por todos os funcionários.

	// REGRA 2: Diante do login e senha do usuário o sistema vincula-o ao seu devido
	// cargo, dando acesso apenas às suas respectivas funcionalidades

	// REGRA 3: Apenas a gerência poderá excluir informações sobre funcionários
	// (demissão).

	// REGRA 4: Apenas a gerência poderá alterar/atualizar dados cadastrados dos
	// funcionários.

	// REGRA 5: No caso de demissão, o usuário sofrerá exclusão lógica, tendo seu
	// cargo
	// alterado para inativo e perdendo todos os níveis de acesso ao sistema.
}
