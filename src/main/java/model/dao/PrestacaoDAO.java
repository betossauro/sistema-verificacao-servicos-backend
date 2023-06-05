package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.vo.Atividade;
import model.vo.Ocorrencia;
import model.vo.Prestacao;

public class PrestacaoDAO {

	public Prestacao inserir(Prestacao novaPrestacao) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO PRESTACAO (IDFUNCIONARIO, IDSALA, DATAINICIO, DATAFIM) " + " VALUES (?,?,?,?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			query.setInt(1, novaPrestacao.getIdFuncionario());
			query.setInt(2, novaPrestacao.getIdSala());
			query.setTimestamp(3, java.sql.Timestamp.valueOf(novaPrestacao.getDataInicio()));
			query.setTimestamp(4, java.sql.Timestamp.valueOf(novaPrestacao.getDataFim()));
			query.execute();
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaPrestacao.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao inserir prestação de serviço" + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novaPrestacao;
	}

	public boolean atualizar(Prestacao prestacaoAlterada) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE PRESTACAO SET IDFUNCIONARIO = ?, IDSALA = ?, DATAINICIO = ?, DATAFIM = ? "
				+ " WHERE ID = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			query.setInt(1, prestacaoAlterada.getIdFuncionario());
			query.setInt(2, prestacaoAlterada.getIdSala());
			query.setTimestamp(3, java.sql.Timestamp.valueOf(prestacaoAlterada.getDataInicio()));
			query.setTimestamp(4, java.sql.Timestamp.valueOf(prestacaoAlterada.getDataFim()));
			query.setInt(5, prestacaoAlterada.getId());
			registrosAlterados = query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar prestação existente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public Prestacao consultarPorId(int id) {
		Prestacao prestacaoBuscada = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PRESTACAO " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				prestacaoBuscada = montarPrestacaoComResultadoDoBanco(resultado);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao buscar prestação com id" + id + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return prestacaoBuscada;
	}

	public List<Prestacao> consultarTodos() {
		List<Prestacao> prestacoes = new ArrayList<Prestacao>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PRESTACAO ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Prestacao prestacaoBuscada = montarPrestacaoComResultadoDoBanco(resultado);
				prestacoes.add(prestacaoBuscada);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todas as prestações. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return prestacoes;
	}

	private Prestacao montarPrestacaoComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Prestacao prestacaoBuscada = new Prestacao();
		prestacaoBuscada.setId(resultado.getInt("id"));
		prestacaoBuscada.setIdFuncionario(resultado.getInt("idfuncionario"));
		prestacaoBuscada.setIdSala(resultado.getInt("idsala"));
		prestacaoBuscada.setDataInicio(resultado.getTimestamp("datainicio").toLocalDateTime());
		prestacaoBuscada.setDataFim(resultado.getTimestamp("datafim").toLocalDateTime());
		
		OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
		prestacaoBuscada.setListaOcorrencias(ocorrenciaDAO.consultarPorIdPrestacao(prestacaoBuscada.getId()));
		
		AtividadeDAO atividadeDAO = new AtividadeDAO();
		prestacaoBuscada.setListaAtividades(atividadeDAO.consultarPorIdPrestacao(prestacaoBuscada.getId()));
		return prestacaoBuscada;
	}
	
}
