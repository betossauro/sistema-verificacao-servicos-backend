package view;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import model.vo.Funcionario;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelConsultaGerenciamentoFuncionarios extends JPanel {
	private JTable tblConsultaGerencia;
	private JButton btnVoltar;
	private JButton btnExportar;
	private JButton btnFiltrar;
	private JButton btnAvancar;
	private JButton btnRetroceder;
	private JLabel lblPagina;

	// FONTE: https://github.com/LGoodDatePicker/LGoodDatePicker
	private DatePickerSettings dateSettings;
	private JButton btnPegarData;

	private ArrayList<Funcionario> funcionarios;
	private String[] nomesColunas = { "Nome", "Cargo", "Data de desligamento" };
	private JButton btnEditar;
	private JLabel lblPeriodoServico;
	private JLabel lblDataInicial;
	private DateTimePicker dataInicial;
	private JLabel lblDataFinal;
	private DateTimePicker dataFinal;
	private JLabel lblSala;
	private JTextField textField;
	
	private void limparTabelaConsulta() {
		tblConsultaGerencia.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaConsulta() {
		this.limparTabelaConsulta();

		// TODO
		DefaultTableModel model = (DefaultTableModel) tblConsultaGerencia.getModel();

		for (Funcionario f : funcionarios) {
			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = f.getNome();
			novaLinhaDaTabela[1] = f.getTipoCargo();
			novaLinhaDaTabela[2] = f.getDataDesligamento();

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Create the panel.
	 */
	public PainelConsultaGerenciamentoFuncionarios() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(93dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.MIN_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		// Configurações da parte de DATAS do componente
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		
		lblPeriodoServico = new JLabel("Período de Serviço");
		lblPeriodoServico.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblPeriodoServico, "16, 6, 3, 1");
		
		lblDataInicial = new JLabel("De:");
		lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataInicial, "16, 10");
		
		dataInicial = new DateTimePicker((DatePickerSettings) null, (TimePickerSettings) null);
		add(dataInicial, "18, 10, fill, fill");
		
		lblDataFinal = new JLabel("Até:");
		lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataFinal, "32, 10");
		
		dataFinal = new DateTimePicker((DatePickerSettings) null, (TimePickerSettings) null);
		add(dataFinal, "34, 10, fill, fill");
		
		lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSala, "16, 14, left, default");
		
		textField = new JTextField();
		textField.setColumns(10);
		add(textField, "18, 14, fill, fill");
		
				btnFiltrar = new JButton("Filtrar");
				btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				add(btnFiltrar, "34, 18, default, fill");

		tblConsultaGerencia = new JTable();
		add(tblConsultaGerencia, "18, 24, 17, 1, fill, fill");

		btnRetroceder = new JButton("<");
		add(btnRetroceder, "22, 26");

		lblPagina = new JLabel("1/1");
		lblPagina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPagina, "24, 26, 5, 1");

		btnAvancar = new JButton(">");
		add(btnAvancar, "30, 26");

		btnExportar = new JButton("Exportar Excel");
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnExportar, "18, 32, default, fill");

		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnEditar, "21, 32, 10, 1, default, fill");

		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnVoltar, "34, 32, default, fill");

	}
	

}