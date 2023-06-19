package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.FuncionarioController;
import model.exception.CampoInvalidoException;
import model.vo.Funcionario;
import model.vo.TipoUsuario;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class TelaLogin {

	private JFrame frmLogin;
	private JTextField txtMatricula;
	private JPasswordField pswSenha;
	private JLabel lblSenha;
	private JLabel lblMatricula;
	private JButton btnEntrar;
	private JLabel icon;
	private Funcionario usuarioAutenticado;
	private TelaPrincipal telaPrincipal;
	private JLabel lblErro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 1852, 1048);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmLogin.getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(119dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(139dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(139dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(100dlu;default)"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("max(30dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, }));

		icon = new JLabel("");
		icon.setIcon(new ImageIcon(TelaLogin.class.getResource("/view/icons/logo2.png")));
		frmLogin.getContentPane().add(icon, "24, 19, center, default");

		lblMatricula = new JLabel("Matrícula");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmLogin.getContentPane().add(lblMatricula, "22, 32, 5, 1");

		txtMatricula = new JTextField();
		txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMatricula.setToolTipText("Insira sua matrícula");
		frmLogin.getContentPane().add(txtMatricula, "22, 34, 5, 1, fill, fill");
		txtMatricula.setColumns(10);

		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmLogin.getContentPane().add(lblSenha, "22, 38");

		pswSenha = new JPasswordField();
		pswSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pswSenha.setToolTipText("Insira sua senha");
		frmLogin.getContentPane().add(pswSenha, "22, 40, 5, 1, fill, fill");

		lblErro = new JLabel("Erro ao validar usuário!");
		lblErro.setHorizontalAlignment(SwingConstants.CENTER);
		lblErro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmLogin.getContentPane().add(lblErro, "24, 45");
		lblErro.setVisible(false);

		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					usuarioAutenticado = autenticar();
					if (usuarioAutenticado != null) {
						telaPrincipal = new TelaPrincipal(usuarioAutenticado);
						frmLogin.setVisible(false);
						frmLogin.dispose();
					} else {
						lblErro.setVisible(true);
						limparTela();
					}
				} catch (CampoInvalidoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmLogin.getContentPane().add(btnEntrar, "24, 52, default, fill");
	}

	@SuppressWarnings("deprecation")
	public Funcionario autenticar() throws CampoInvalidoException {
		usuarioAutenticado = null;
		usuarioAutenticado = new FuncionarioController().consultarPorLoginSenha(this.txtMatricula.getText(),
				this.pswSenha.getText());
		return usuarioAutenticado;
	}

	protected void limparTela() {
		txtMatricula.setText("");
		pswSenha.setText("");
	}

}
