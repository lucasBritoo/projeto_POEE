package com.projeto.view.usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Departamento;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class UsuarioGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtEmail;

	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	
	private JRadioButton rdbtnAtivo;
	
	private JRadioButton rdbtnAdmin;
	private JButton btnSair;
	private JPasswordField passwordFieldSenha;
	private JLabel lblCodigo;
	private JTextField textCodigo;
	
	
	private JLabel checkSenha;
	private JLabel checkNome;
	private JLabel checkEmail;
	
	private boolean status = true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioGUI frame = new UsuarioGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UsuarioGUI() {
		setTitle("Cadastro de Uusarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsername = new JLabel("Nome:");
		
		JLabel lblEmail = new JLabel("Email:");
		
		JLabel lblSenha = new JLabel("Senha");
		
		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						verificaDigitacaoNome();
				}
			}
		});
		txtUsername.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					verificaDigitacaoEmail();
					//passwordFieldSenha.requestFocus();
				}
			}
		});
		txtEmail.setColumns(10);
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				rdbtnAdmin.requestFocus();
			}
		});
		rdbtnAdmin = new JRadioButton("Admin");
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_add.png")));
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}

		});
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}

			
		});
		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirUsuario();
			}

		});
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		passwordFieldSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						verificaDigitacaoSenha();
						//passwordFieldSenha.requestFocus();
					}
				}
			}
		});
		
		lblCodigo = new JLabel("C\u00F3digo");
		
		textCodigo = new JTextField();
		textCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarUsuario();
					txtUsername.requestFocus();
				}
			}
		});
		textCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarUsuario();
			}

			
		});
		textCodigo.setColumns(10);
		
		checkNome = new JLabel("");
		checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkEmail = new JLabel("");
		checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkSenha = new JLabel("");
		checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkSenha, 0, 0, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtEmail))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(checkEmail, 0, 0, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(checkNome, GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
											.addGap(34))))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(rdbtnAtivo, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(rdbtnAdmin, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(47)
							.addComponent(btnIncluir)
							.addGap(35)
							.addComponent(btnAlterar)
							.addGap(43)
							.addComponent(btnExcluir)
							.addGap(31)
							.addComponent(btnSair))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(74)
							.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(checkNome)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodigo)
								.addComponent(textCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkEmail, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkSenha, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAtivo)
						.addComponent(rdbtnAdmin))
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addGap(25))
		);
		contentPane.setLayout(gl_contentPane);
		limpaTextoCampo();
		desabilitaCheckCampos();
	}
	
	private void verificaDigitacaoNome() {
		if(VariaveisProjeto.digitacaoCampo(txtUsername.getText())) {
			status = false;
			mudaStatusCheckNome();
		}
		else {
			digitacaoNomeValido();
		}
	}
	
	private void verificaDigitacaoEmail() {
		if(VariaveisProjeto.digitacaoCampo(txtEmail.getText())) {
			status = false;
			mudaStatusCheckEmail();
		}
		else {
			digitacaoEmailValido();
		}
	}
	
	private void verificaDigitacaoSenha() {
		if(VariaveisProjeto.digitacaoCampo(new String(passwordFieldSenha.getPassword()))) {
			status = false;
			mudaStatusCheckSenha();
		}
		else {
			digitacaoSenhaValido();
		}
	}

	private void digitacaoNomeValido() {
		status= true;
		mudaStatusCheckNome();
		txtEmail.requestFocus();
	}
	
	private void digitacaoEmailValido() {
		status= true;
		mudaStatusCheckEmail();
		passwordFieldSenha.requestFocus();
	}
	
	private void digitacaoSenhaValido() {
		status= true;
		mudaStatusCheckSenha();
		rdbtnAtivo.requestFocus();
	}
	
	private void desabilitaCheckCampos() {
		checkEmail.setVisible(false);
		checkNome.setVisible(false);
		checkSenha.setVisible(false);
	}
	
	private void mudaStatusCheckNome() {
		//checkNomeSucesso = new JLabel("");
		if(status == false){
			checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
		checkNome.setVisible(true);
		
		txtUsername.requestFocus();
	}
	
	private void mudaStatusCheckEmail() {
		//checkNomeSucesso = new JLabel("");
		if(status == false){
			checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
		checkEmail.setVisible(true);
		
		txtEmail.requestFocus();
	}
	
	private void mudaStatusCheckSenha() {
		//checkNomeSucesso = new JLabel("");
		if(status == false){
			checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
		checkSenha.setVisible(true);
		
		passwordFieldSenha.requestFocus();
	}
	
	protected void incluirUsuario() {
		
		Integer toReturn =0;
		Usuario usuario = pegarDadosUsuario();
		
		Departamento departamento = new Departamento();
		departamento.setId(1L);
		departamento.setNome("Vendas");
		
		usuario.setDepartamento(departamento);
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.save(usuario);
		
		if(toReturn == VariaveisProjeto.NOME_CAMPO_VAZIO) {
			digitacaoNomeValido();
			showMensagem("Campo nome deve ser informado","Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na inclusão do registro","Erro", JOptionPane.ERROR_MESSAGE );
		}
		if(toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem( "Inclusao foi realizada com sucesso","OK", JOptionPane.OK_OPTION );
			limpaTextoCampo();
			usuario = new Usuario();
		}
		
	}

	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem, status, option );
	}
	
	private void buscarUsuario() {
		Usuario usuario = new Usuario();
		
		if(VariaveisProjeto.digitacaoCampo(textCodigo.getText())) {
			textCodigo.requestFocus();
			return ; 
		}
		Integer id = Integer.valueOf(textCodigo.getText());
		UsuarioService usuarioService = new UsuarioService();
		
		usuario = usuarioService.findById(id);
		
		txtUsername.setText(usuario.getUsername());
		txtEmail.setText(usuario.getEmail());
		passwordFieldSenha.setText(usuario.getPassword());
		
		if(usuario.isAdmin())
			rdbtnAdmin.setSelected(true);
		
		if(usuario.isAtivo())
			rdbtnAtivo.setSelected(true);
	}
	
	protected void alterarUsuario() {
		Integer toReturn =0;
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.update(usuario);
		
		if(toReturn == VariaveisProjeto.NOME_CAMPO_VAZIO) {
			digitacaoNomeValido();
			showMensagem("Campo nome deve ser informado","Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na alteração do registro","Erro", JOptionPane.ERROR_MESSAGE );
		}
		if(toReturn == VariaveisProjeto.ALTERECAO_REALIZADA) {
			showMensagem( "Alteração foi realizada com sucesso","OK", JOptionPane.OK_OPTION );
			limpaTextoCampo();
			usuario = new Usuario();
		}
	}
	
	private void excluirUsuario() {
		
		Integer toReturn = 0;
		
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.delete(usuario);
		
		if(toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na exclusao do registro","Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem( "Exclusao realizada com sucesso","OK", JOptionPane.OK_OPTION );
			limpaTextoCampo();
			usuario = new Usuario();
		}
	}
	
	public Usuario pegarDadosUsuario() {
		Usuario usuario = new Usuario();
		
		//if(VariaveisProjeto.digitacaoCampo(textCodigo.getText())) {
		//	textCodigo.requestFocus();
		//	return ;
		//}
		
		usuario.setId(VariaveisProjeto.convertToInteger(textCodigo.getText()));
		usuario.setId(Integer.valueOf(textCodigo.getText()));
		usuario.setUsername(txtUsername.getText());
		usuario.setEmail(txtEmail.getText());
		usuario.setPassword(new String(passwordFieldSenha.getPassword()));
		
		if(rdbtnAtivo.isSelected()) {
			usuario.setAtivo(true);
		}else {
			usuario.setAtivo(false);
		}
		
		if(rdbtnAdmin.isSelected()) {
			usuario.setAdmin(true);
		}else {
			usuario.setAdmin(false);
		}
		
		return usuario;
	}
	
	private void limpaTextoCampo() {
		textCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		txtUsername.setText(VariaveisProjeto.LIMPA_CAMPO);
		txtEmail.setText(VariaveisProjeto.LIMPA_CAMPO);
		passwordFieldSenha.setText(VariaveisProjeto.LIMPA_CAMPO);
		rdbtnAdmin.setSelected(false);
		rdbtnAtivo.setSelected(false);
	}
}
