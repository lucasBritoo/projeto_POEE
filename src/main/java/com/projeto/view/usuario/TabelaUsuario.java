package com.projeto.view.usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TabelaUsuario extends JInternalFrame {

	private static final long serialVersionUID = -1898454122848949025L;

	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int EMAIL = 2;
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabelaUsuario;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JPanel panel;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;
	private JLabel lblNewLabel_1;
	private JTextField txtPesquisar;
	private JButton btnPesquisar;
	private JLabel lblPagina;
	private JLabel lblInicio;
	private JLabel lblTotal;
	private JLabel lblFinal;

	
	private TabelaUsuarioModel tabelaUsuarioModel;
	private TableRowSorter<TabelaUsuarioModel> sortTabelaUsuario;
	
	private Integer totalData= 0;
	private Integer defaultPagina= 5;
	private Integer totalPagina= 1;
	private Integer numeroPagina= 1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaUsuario frame = new TabelaUsuario();
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
	public TabelaUsuario() {
		initComponents();
		iniciaPaginacao();
	}
	private void initComponents() {
		setTitle("TABELA USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		btnIncluir = new JButton("INCLUIR");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirUsuario();
				iniciaPaginacao();
			}

			
		});
		btnIncluir.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("ALTERAR");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
				iniciaPaginacao();
			}

			
		});
		btnAlterar.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_go.png")));
		
		panel = new JPanel();
		
		lblNewLabel = new JLabel("P\u00E1gina:");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "10", "15", "20"}));
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				iniciaPaginacao();
			}
		});
		
		
		lblNewLabel_1 = new JLabel("Pesquisar");
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = txtPesquisar.getText();
				
				filtraNomeUsuario(filtro);
			}

			
		});
		txtPesquisar.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnPesquisar.setToolTipText("Pesquisar usu\u00E1rio cadastrado");
		btnPesquisar.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		lblPagina = new JLabel("P\u00E1gina:");
		
		lblInicio = new JLabel("10");
		
		lblTotal = new JLabel("Total:");
		
		lblFinal = new JLabel("50");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPesquisar))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(124)
										.addComponent(btnIncluir))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(54)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGap(33)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblPagina, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblInicio, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblFinal, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnAlterar)
										.addGap(26)
										.addComponent(btnExcluir)
										.addGap(18)
										.addComponent(btnSair)
										.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE))))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(30)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 631, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(115, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPagina)
										.addComponent(lblInicio, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTotal)
										.addComponent(lblFinal))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnIncluir)
								.addComponent(btnAlterar)
								.addComponent(btnExcluir)
								.addComponent(btnSair)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		
		btnPrimeiro = new JButton("");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}

			
		});
		btnPrimeiro.setToolTipText("Primeiro");
		btnPrimeiro.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina > 1) {
					numeroPagina = numeroPagina -1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		btnAnterior.setToolTipText("Anterior");
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina) {
					numeroPagina += 1;
					iniciaPaginacao();
					
				}
			}
		});
		btnProximo.setToolTipText("Pr\u00F3ximo");
		btnProximo.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		btnUltimo.setToolTipText("\u00DAltimo");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPrimeiro)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAnterior)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnProximo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUltimo)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUltimo)
						.addComponent(btnProximo)
						.addComponent(btnAnterior)
						.addComponent(btnPrimeiro))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		tabelaUsuario = new JTable();
		scrollPane.setViewportView(tabelaUsuario);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void iniciaPaginacao() {
		totalData = buscaTotalRegistroUsuario();
		
		defaultPagina = Integer.valueOf(comboBox.getSelectedItem().toString());
		
		Double totalPaginasExistentes = Math.ceil(totalData.doubleValue()/defaultPagina.doubleValue());
		
		totalPagina = totalPaginasExistentes.intValue(); 
		
		if(numeroPagina.equals(1)) {
			btnPrimeiro.setEnabled(false);
			btnProximo.setEnabled(false);
		}
		else {
			btnPrimeiro.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if(numeroPagina.equals(totalPagina)) {
			btnUltimo.setEnabled(false);
			btnProximo.setEnabled(false);
		}
		else {
			btnUltimo.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if(numeroPagina > totalPagina) {
			numeroPagina = 1;
		}
		
		tabelaUsuarioModel = new TabelaUsuarioModel();
		
		tabelaUsuarioModel.setListaUsuario(carregaListaUsuario(numeroPagina, defaultPagina));
		
		tabelaUsuario.setModel(tabelaUsuarioModel);
		
		tabelaUsuario.setFillsViewportHeight(true);
		
		tabelaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaUsuarioModel.fireTableDataChanged();
		
		sortTabelaUsuario = new TableRowSorter<TabelaUsuarioModel>(tabelaUsuarioModel);
		
		tabelaUsuario.setRowSorter(sortTabelaUsuario);
		
		tabelaUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaUsuario.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaUsuario.getColumnModel().getColumn(NOME).setWidth(100);
		tabelaUsuario.getColumnModel().getColumn(EMAIL).setWidth(100);
		
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblFinal.setText(String.valueOf(totalData));
		
	}
	
	private void filtraNomeUsuario(String filtro) {
		RowFilter<TabelaUsuarioModel, Object> rowFilter = null;
		
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		}catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaUsuario.setRowFilter(rowFilter);
		
	}
	
	private void incluirUsuario() {
		CadastroUsuario usuario = new CadastroUsuario(new JFrame(), true, tabelaUsuario, tabelaUsuarioModel, 0, VariaveisProjeto.INCLUSAO);
		usuario.setLocationRelativeTo(null);
		usuario.setResizable(false);
		usuario.setVisible(true);
	}
	
	private void alterarUsuario() {
		if(tabelaUsuario.getSelectedRow() != -1 && tabelaUsuario.getSelectedRow() < tabelaUsuarioModel.getRowCount()) {
			int linha = tabelaUsuario.getSelectedRow();
			
			CadastroUsuario usuario = new CadastroUsuario(new JFrame(), true, tabelaUsuario, tabelaUsuarioModel, linha, VariaveisProjeto.ALTERACAO);
			usuario.setLocationRelativeTo(null);
			usuario.setResizable(false);
			usuario.setVisible(true);
			
			
		}
	}
	
	private List<Usuario> carregaListaUsuario(Integer numeroPagina, Integer defaultPagina) {
		
		UsuarioService usuarioService = new UsuarioService();
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		listaUsuario = usuarioService.listUsuarioPaginacao((defaultPagina * (numeroPagina - 1)), defaultPagina);
		return listaUsuario;
	}

	private Integer buscaTotalRegistroUsuario() {
		
		Integer totalRegistro = 0;
		
		UsuarioService usuarioService = new UsuarioService();
		totalRegistro = usuarioService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTable() {
		return tabelaUsuario;
	}
}
