package controller;

import java.util.List;

import model.bo.PrestacaoBO;
import model.dto.PrestacaoDTO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;
import model.vo.Prestacao;

public class PrestacaoController {
	PrestacaoBO bo = new PrestacaoBO();

//	public Prestacao inserir(Prestacao novaPrestacao) {
//	return bo.inserir(Prestacao novaPrestacao);
//	}

	public boolean atualizar(Prestacao servicoAlterado) {
		return bo.atualizar(servicoAlterado);
	}

	public Prestacao consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<Prestacao> consultarTodos() {
		return bo.consultarTodos();
	}

	public List<PrestacaoDTO> consultarTodosDTO() {
		return bo.consultarTodosDTO();
	}

	public String gerarPlanilha(List<PrestacaoDTO> prestacoes, String caminho) throws CampoInvalidoException {

		if (prestacoes == null || caminho == null || caminho.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}

		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaServicos(prestacoes, caminho);
	}
}
