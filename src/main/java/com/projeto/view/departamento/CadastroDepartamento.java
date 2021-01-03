package com.projeto.view.departamento;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.view.departamento.TabelaDepartamentoModel;

public class CadastroDepartamento extends JFrame {

	private JPanel contentPane;
	
	
	
	
	
	private boolean status = true;
	
	private JTable tabelaDepartamento;
	private TabelaDepartamentoModel tabelaDepartamentoModel;
	
	private int linha =0;
	private int acao =0;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDepartamento frame = new CadastroDepartamento();
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
	
	public CadastroDepartamento(JFrame frame, boolean modal, JTable tabelaDepartamento, TabelaDepartamentoModel tabelaDepartamentoModel, int linha, int acao) {
		
		//super(frame, modal);
		
		this.tabelaDepartamento = tabelaDepartamento;
		this.tabelaDepartamentoModel = tabelaDepartamentoModel;
		this.linha = linha;
		this.acao = acao;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
