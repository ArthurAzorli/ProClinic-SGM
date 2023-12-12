package view;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bin.*;
import dao.*;
import utils.*;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.Panel;
import java.awt.Label;

public class Index extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JLabel background;
	private ArrayList<String> idPacientes;
	private ArrayList<String> idMedicos;
	private ArrayList<String> idEnfermeiros;
	private ArrayList<String> idEnfermeirosSelected;
	private ArrayList<String> idTecnicos_de_Enfermagem;
	private ArrayList<Integer> idConsultas;
	private ArrayList<Integer> idTriagens;
	private ArrayList<Integer> idConvenios;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (!new SenhaAdmDAO().exist()) {
						PrimeiroAcesso tela = new PrimeiroAcesso();
						tela.setVisible(true);
					} else {
						Index frame = new Index();
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
/*
 * Coisas que faltaM:
 * -Relatorios (total de lucro mensal para cada convenio, quantidade de pacientes para cada convenio, pessoal da enfermaria)
 * */

	public Index() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/img/logo.png")));
		setTitle("ProClinic SGM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-10, 0, 1553, 820);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(56, 56, 56));
		setJMenuBar(menuBar);
		
		JMenu bntCadastro = new JMenu("Cadastro");
		bntCadastro.setBackground(new Color(56, 56, 56));
		bntCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		bntCadastro.setForeground(new Color(255, 255, 255));
		menuBar.add(bntCadastro);
		
		JMenuItem bntPaciente = new JMenuItem("Paciente");
		bntPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenListPaciente());
			}
		});
		bntPaciente.setForeground(new Color(255, 255, 255));
		bntPaciente.setBackground(new Color(56, 56, 56));
		bntPaciente.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntCadastro.add(bntPaciente);
		
		JMenuItem bntMedico = new JMenuItem("Médico");
		bntMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenListMedico());
			}
		});
		bntMedico.setForeground(new Color(255, 255, 255));
		bntMedico.setBackground(new Color(56, 56, 56));
		bntMedico.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntCadastro.add(bntMedico);
		
		JMenuItem bntEnfermeiro = new JMenuItem("Enfermeiro");
		bntEnfermeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenListEnfermeiro());
			}
		});
		bntEnfermeiro.setForeground(new Color(255, 255, 255));
		bntEnfermeiro.setBackground(new Color(56, 56, 56));
		bntEnfermeiro.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntCadastro.add(bntEnfermeiro);
		
		JMenuItem bntTecnicoDeEnfermagem = new JMenuItem("Técnico de Enfermagem");
		bntTecnicoDeEnfermagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenListTecnicoDeEnfermagem());
			}
		});
		bntTecnicoDeEnfermagem.setForeground(new Color(255, 255, 255));
		bntTecnicoDeEnfermagem.setBackground(new Color(56, 56, 56));
		bntTecnicoDeEnfermagem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntCadastro.add(bntTecnicoDeEnfermagem);
		
		JMenu bntAtentimento = new JMenu("Atendimento");
		bntAtentimento.setForeground(new Color(255, 255, 255));
		bntAtentimento.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		bntAtentimento.setBackground(new Color(56, 56, 56));
		menuBar.add(bntAtentimento);
		
		JMenuItem bntConsulta = new JMenuItem("Consulta");
		bntConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenListConsulta());
			}
		});
		bntConsulta.setBackground(new Color(56, 56, 56));
		bntConsulta.setForeground(new Color(255, 255, 255));
		bntConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntAtentimento.add(bntConsulta);
		
		JMenuItem bntTriagem = new JMenuItem("Triagem");
		bntTriagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenListTriagem());
			}
		});
		bntTriagem.setBackground(new Color(56, 56, 56));
		bntTriagem.setForeground(new Color(255, 255, 255));
		bntTriagem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntAtentimento.add(bntTriagem);
		
		JMenu bntServico = new JMenu("Serviços");
		bntServico.setForeground(new Color(255, 255, 255));
		bntServico.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		bntServico.setBackground(new Color(56, 56, 56));
		menuBar.add(bntServico);
		
		JMenuItem bntConvenio = new JMenuItem("Convenio");
		bntConvenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenListConvenio());
			}
		});
		bntConvenio.setBackground(new Color(56, 56, 56));
		bntConvenio.setForeground(new Color(255, 255, 255));
		bntConvenio.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntServico.add(bntConvenio);
		
		JMenu bntRelatorio = new JMenu("Relatórios");
		bntRelatorio.setForeground(new Color(255, 255, 255));
		bntRelatorio.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		bntRelatorio.setBackground(new Color(56, 56, 56));
		menuBar.add(bntRelatorio);
		
		JMenuItem bntConvenioR = new JMenuItem("Convenio");
		bntConvenioR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenRelatorioConvenio());
			}
		});
		bntConvenioR.setBackground(new Color(56, 56, 56));
		bntConvenioR.setForeground(new Color(255, 255, 255));
		bntConvenioR.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntRelatorio.add(bntConvenioR);
		
		JMenuItem bntEnfermariaR = new JMenuItem("Enfermaria");
		bntEnfermariaR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenRelatorioEnfermaria());
			}
		});
		bntEnfermariaR.setBackground(new Color(56, 56, 56));
		bntEnfermariaR.setForeground(new Color(255, 255, 255));
		bntEnfermariaR.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		bntRelatorio.add(bntEnfermariaR);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane); 
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, -23, 1539, 858);
		contentPane.add(layeredPane);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(Index.class.getResource("/img/backgroud_1.png")));
		background.setBounds(0, -25, 1600, 800);
		contentPane.add(background);		
		
		// ======================== HOME ======================== //	
		
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 22, 1539, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		
		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon(Index.class.getResource("/img/ProClinic SGM.png")));
		image.setBounds(434, 0, 1122, 750);
		Screen.add(image);
		
		JLabel Neoaurora = new JLabel("NEOAURORA");
		Neoaurora.setForeground(Color.WHITE);
		Neoaurora.setFont(new Font("Arial Black", Font.BOLD, 50));
		Neoaurora.setBounds(144, 622, 479, 90);
		Screen.add(Neoaurora);
		
		JLabel text_2 = new JLabel("Desenvolvido por:");
		text_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		text_2.setForeground(new Color(255, 255, 255));
		text_2.setBounds(144, 602, 209, 55);
		Screen.add(text_2);
		
		JPanel detalhe = new JPanel();
		detalhe.setBackground(new Color(255, 255, 255));
		detalhe.setBounds(24, 380, 506, 9);
		Screen.add(detalhe);
		
		JLabel text_1 = new JLabel("Sistema de Gerenciamento Médico");
		text_1.setForeground(new Color(255, 255, 255));
		text_1.setFont(new Font("Arial Black", Font.BOLD, 25));
		text_1.setBounds(23, 380, 536, 77);
		Screen.add(text_1);
		
		JLabel text = new JLabel("ProClinic ");
		text.setFont(new Font("Arial Black", Font.BOLD, 99));
		text.setForeground(new Color(255, 255, 255));
		text.setBounds(24, 217, 636, 213);
		Screen.add(text);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Index.class.getResource("/img/NeoAurora.png")));
		logo.setBounds(10, 580, 150, 150);
		Screen.add(logo);
		
		JLabel backgroundInicio = new JLabel("New label");
		backgroundInicio.setIcon(new ImageIcon(Index.class.getResource("/img/background_2.jpeg")));
		backgroundInicio.setBounds(0, -12, 1579, 807);
		Screen.add(backgroundInicio);
		
		
	}
	
	// ======================== TROCA DE TELA ======================== //
	private void SwitchScreen(JPanel pane) {
		layeredPane.removeAll();
		layeredPane.add(pane);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	private void SwitchScreen(JPanel pane, JLayeredPane layeredPane) {
		layeredPane.removeAll();
		layeredPane.add(pane);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	// ======================== TELA DA LISTA DE PACIENTES ======================== //
	private JPanel ScreenListPaciente() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Lista de Paciente:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1000, 430);
		Screen.add(scrollPane);
		
		JTable tablePacientes = new JTable();
		tablePacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tablePacientes);
		tablePacientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF", "Nome", "Data de Nascimento", "Convenio", "Endere\u00E7o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePacientes.setRowHeight(30);
		tablePacientes.getTableHeader().setBackground(new Color(56, 56, 56));
		tablePacientes.getTableHeader().setForeground(new Color(255, 255, 255));
		tablePacientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		tablePacientes.getColumnModel().getColumn(4).setPreferredWidth(320);
		
		DefaultTableModel model = (DefaultTableModel) tablePacientes.getModel();
		idPacientes = new ArrayList<>();
		for (Paciente p:new PacienteDAO().getAll()) {
			model.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio(), p.getEndereco()});
			idPacientes.add(p.getCPF());
		}
		
		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (model.getRowCount()>0) {
					model.setRowCount(0);
				}
				
				idPacientes = new ArrayList<>();
				for (Paciente p:new PacienteDAO().search(pesquisar.getText())) {
					model.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio(), p.getEndereco()});
					idPacientes.add(p.getCPF());
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenCreatePaciente());
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionar.setBackground(new Color(227, 227, 227));
		btnAdicionar.setBounds(1130, 250, 260, 62);
		Screen.add(btnAdicionar);
		
		JButton btnEditarPaciente = new JButton("Editar");
		btnEditarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tablePacientes.getSelectedRow() == -1) {throw new Exception("Nenhum Paciente foi selecionado!");}
					SwitchScreen(ScreenEditPaciente(new PacienteDAO().get(idPacientes.get(tablePacientes.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEditarPaciente.setBackground(new Color(227, 227, 227));
		btnEditarPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditarPaciente.setBounds(1130, 312, 260, 62);
		Screen.add(btnEditarPaciente);
		
		JButton btnDelPaciente = new JButton("Deletar");
		btnDelPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PacienteDAO dao = new PacienteDAO();
					if(tablePacientes.getSelectedRow() == -1) {throw new Exception("Nenhum Paciente foi selecionado!");}
					if(!new SenhaAdmDAO().validSenha(JOptionPane.showInputDialog(null,"Digite a sua Senha de Administrador:"))) {throw new Exception("Senha Inválida!");}
					if (JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar este Paciente?") == 0) {
						if (dao.delete(dao.get(idPacientes.get(tablePacientes.getSelectedRow())))==1) {
							JOptionPane.showMessageDialog(null, "Paciente deletado com Sucesso!");
							
							while (model.getRowCount()>0) {
								model.setRowCount(0);
							}
							
							idPacientes = new ArrayList<>();
							for (Paciente p:new PacienteDAO().search(pesquisar.getText())) {
								model.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio(), p.getEndereco()});
								idPacientes.add(p.getCPF());
							}
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao deletar o(a) Paciente!");
						}
					}
					
					
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnDelPaciente.setBackground(new Color(227, 227, 227));
		btnDelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelPaciente.setBounds(1130, 374, 260, 62);
		Screen.add(btnDelPaciente);
		
		JButton btnMaisInfo = new JButton("Mais Info");
		btnMaisInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tablePacientes.getSelectedRow() == -1) {throw new Exception("Nenhum Paciente foi selecionado!");}
					MoreInfo tela = new MoreInfo(new PacienteDAO().get(idPacientes.get(tablePacientes.getSelectedRow())));
					tela.setVisible(true);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnMaisInfo.setBackground(new Color(227, 227, 227));
		btnMaisInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMaisInfo.setBounds(1130, 436, 260, 61);
		Screen.add(btnMaisInfo);
		
		JButton btnAdicionarFicha = new JButton("Adicionar Ficha Técnica");
		btnAdicionarFicha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Ficha_TecnicaDAO daoFicha = new Ficha_TecnicaDAO();
					if(tablePacientes.getSelectedRow() == -1) {throw new Exception("Nenhum Paciente foi selecionado!");}
					if(daoFicha.have(new PacienteDAO().get(idPacientes.get(tablePacientes.getSelectedRow())))) {throw new Exception("Este Paciente já possui Ficha Tecnica!");}
					SwitchScreen(ScreenCreateFicha(new PacienteDAO().get(idPacientes.get(tablePacientes.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAdicionarFicha.setBackground(new Color(227, 227, 227));
		btnAdicionarFicha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionarFicha.setBounds(1130, 497, 260, 61);
		Screen.add(btnAdicionarFicha);
		
		JButton btnEditarFicha = new JButton("Editar Ficha Tecnica");
		btnEditarFicha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tablePacientes.getSelectedRow() == -1) {throw new Exception("Nenhum Paciente foi selecionado!");}
					if(! new Ficha_TecnicaDAO().have(new PacienteDAO().get(idPacientes.get(tablePacientes.getSelectedRow())))) {throw new Exception("Este Paciente não possui Ficha Técnica!");}
					SwitchScreen(ScreenEditFicha(new PacienteDAO().get(idPacientes.get(tablePacientes.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEditarFicha.setBackground(new Color(227, 227, 227));
		btnEditarFicha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditarFicha.setBounds(1130, 558, 260, 61);
		Screen.add(btnEditarFicha);
		
		JButton btnDelFicha = new JButton("Deletar Ficha Tecnica");
		btnDelFicha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Ficha_TecnicaDAO daoFicha = new Ficha_TecnicaDAO();
					if(tablePacientes.getSelectedRow() == -1) {throw new Exception("Nenhum Paciente foi selecionado!");}
					if(!daoFicha.have(new PacienteDAO().get(idPacientes.get(tablePacientes.getSelectedRow())))) {throw new Exception("Este Paciente não possui Ficha Técnica!");}
					if (JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar a Ficha Técnica deste Paciente?") == 0) {
						if (daoFicha.delete(daoFicha.getOfPaciente(idPacientes.get(tablePacientes.getSelectedRow())))==1) {
							JOptionPane.showMessageDialog(null, "Ficha Técnica deletada com Sucesso!");
							
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao deletar a Ficha Técnica!");
						}
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnDelFicha.setBackground(new Color(227, 227, 227));
		btnDelFicha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelFicha.setBounds(1130, 619, 260, 61);
		Screen.add(btnDelFicha);
		
		return Screen;
	}

	// ======================== TELA DE CADASTRO DO PACIENTE ======================== //
	private JPanel ScreenCreatePaciente() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro de Paciente:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldCPF = new JTextField();
		fieldCPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCPF.setText(CPF.cpfFormat(fieldCPF.getText()));
				try {
					if (!CPF.cpfIsValid(fieldCPF.getText())){throw new Exception("CPF inválido!");}
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		fieldCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCPF.setBounds(130, 180, 480, 30);
		Screen.add(fieldCPF);
		fieldCPF.setColumns(10);
		
		JLabel labelCPF = new JLabel("CPF");
		labelCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCPF.setForeground(new Color(245, 245, 245));
		labelCPF.setBounds(130, 150, 300, 30);
		Screen.add(labelCPF);
		
		JTextField fieldNome = new JTextField();
		fieldNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNome.setText(Text.toName(fieldNome.getText()));
			}
		});
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNome.setColumns(10);
		fieldNome.setBounds(130, 280, 480, 30);
		Screen.add(fieldNome);
		
		JLabel labelNome = new JLabel("Nome Completo");
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNome.setForeground(new Color(245, 245, 245));
		labelNome.setBounds(130, 250, 300, 30);
		Screen.add(labelNome);
		
		JTextField fieldDataNasc = new JTextField();
		fieldDataNasc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataNasc = Data.toDateUsFormat(fieldDataNasc.getText());
				try {
					if (!Data.BirthdayIsValid(dataNasc)){throw new Exception("Data de Nascimento inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataNasc.setText(Data.toDateBrFormat(dataNasc));
			}
		});
		fieldDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataNasc.setColumns(10);
		fieldDataNasc.setBounds(130, 380, 480, 30);
		Screen.add(fieldDataNasc);
		
		JLabel labelDataNasc = new JLabel("Data de Nascimento (dd/mm/aaaa)");
		labelDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataNasc.setForeground(new Color(245, 245, 245));
		labelDataNasc.setBounds(130, 350, 350, 30);
		Screen.add(labelDataNasc);
		
		JTextField fieldRua = new JTextField();
		fieldRua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldRua.setText(Text.toName(fieldRua.getText()));
			}
		});
		fieldRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldRua.setColumns(10);
		fieldRua.setBounds(840, 180, 550, 30);
		Screen.add(fieldRua);
		
		JLabel labelRua = new JLabel("Logradouro");
		labelRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelRua.setForeground(new Color(245, 245, 245));
		labelRua.setBounds(840, 150, 300, 30);
		Screen.add(labelRua);
		
		JTextField fieldBairro = new JTextField();
		fieldBairro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldBairro.setText(Text.toName(fieldBairro.getText()));
			}
		});
		fieldBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldBairro.setColumns(10);
		fieldBairro.setBounds(840, 280, 375, 30);
		Screen.add(fieldBairro);
		
		JLabel labelBairro = new JLabel("Bairro");
		labelBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelBairro.setForeground(new Color(245, 245, 245));
		labelBairro.setBounds(840, 250, 300, 30);
		Screen.add(labelBairro);
		
		JTextField fieldNumero = new JTextField();
		fieldNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNumero.setText(Text.toName(fieldNumero.getText()));
			}
		});
		fieldNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNumero.setColumns(10);
		fieldNumero.setBounds(1250, 280, 140, 30);
		Screen.add(fieldNumero);
		
		JLabel labelNumero = new JLabel("Número");
		labelNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNumero.setForeground(new Color(245, 245, 245));
		labelNumero.setBounds(1250, 250, 140, 30);
		Screen.add(labelNumero);
		
		JTextField fieldCidade = new JTextField();
		fieldCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCidade.setText(Text.toName(fieldCidade.getText()));
			}
		});
		fieldCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCidade.setColumns(10);
		fieldCidade.setBounds(840, 380, 375, 30);
		Screen.add(fieldCidade);
		
		JLabel labelCidade = new JLabel("Cidade");
		labelCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCidade.setForeground(new Color(245, 245, 245));
		labelCidade.setBounds(840, 350, 300, 30);
		Screen.add(labelCidade);
		
		JComboBox fieldUF = new JComboBox();
		fieldUF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldUF.setBounds(1250, 380, 140, 30);
		Screen.add(fieldUF);
		
		JLabel labelEstado = new JLabel("Estado");
		labelEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelEstado.setForeground(new Color(245, 245, 245));
		labelEstado.setBounds(1250, 350, 140, 30);
		Screen.add(labelEstado);
		
		ArrayList<String> estados = new UFDAO().getAll();
		for (String uf : estados) {
			fieldUF.addItem(uf);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(820, 480, 600, 150);
		Screen.add(scrollPane);
		
		JTable tableConvenios = new JTable();
		tableConvenios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableConvenios);
		tableConvenios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Convenio", "Valor Mensal", "Descri\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConvenios.setRowHeight(26);
		tableConvenios.getTableHeader().setBackground(new Color(56, 56, 56));
		tableConvenios.getTableHeader().setForeground(new Color(255, 255, 255));
		tableConvenios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tableConvenios.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableConvenios.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableConvenios.getColumnModel().getColumn(2).setPreferredWidth(240);
		tableConvenios.getColumnModel().getColumn(2).setMinWidth(30);
		
		DefaultTableModel model = (DefaultTableModel) tableConvenios.getModel();
		ArrayList<Integer> idConvenios = new ArrayList<>();
		for (Convenio conv: new ConvenioDAO().getAll()) {
			model.addRow(new Object[] {conv.getConvenio(), Text.toMoney(conv.getValor_mensal()), conv.getDescricao()});
			idConvenios.add(conv.getId());
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(130, 480, 342, 150);
		Screen.add(scrollPane_1);
		
		
		JTable tableTelefones = new JTable();
		scrollPane_1.setViewportView(tableTelefones);
		tableTelefones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableTelefones.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Telefone"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableTelefones.setRowHeight(30);
		tableTelefones.getTableHeader().setBackground(new Color(56, 56, 56));
		tableTelefones.getTableHeader().setForeground(new Color(255, 255, 255));
		tableTelefones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JLabel labelTelefones = new JLabel("Telefones ");
		labelTelefones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTelefones.setForeground(new Color(245, 245, 245));
		labelTelefones.setBounds(130, 450, 350, 30);
		Screen.add(labelTelefones);
		
		JButton btnAddTel = new JButton("Adiocionar");
		btnAddTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableTelefones.getModel();
					String telefone = Telefone.telefoneFormat(JOptionPane.showInputDialog("Digite o telefone com o ddd:"));
					
					ArrayList<String> telefones = new ArrayList<>();
					for (int i = 0; i<tableTelefones.getRowCount(); i++) {
						telefones.add(tableTelefones.getValueAt(i, 0).toString());
					}
					if (telefone != null) {
						if (telefone.isEmpty()) {throw new Exception("Telefone inválido!");}
						if (Text.ExistIn(telefone, telefones)){throw new Exception("Telefone já existe na tabela!");}
						
						model.addRow(new Object[] {telefone});
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddTel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddTel.setBackground(new Color(227, 227, 227));
		btnAddTel.setBounds(470, 480, 140, 75);
		Screen.add(btnAddTel);
		
		JButton btnDelTel = new JButton("Deletar");
		btnDelTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try{
					if (tableTelefones.getSelectedRow() == -1){throw new Exception("Nenhum Telefone foi selecionado!");}
					DefaultTableModel model = (DefaultTableModel) tableTelefones.getModel();
					model.removeRow(tableTelefones.getSelectedRow());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnDelTel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelTel.setBackground(new Color(227, 227, 227));
		btnDelTel.setBounds(470, 555, 140, 75);
		Screen.add(btnDelTel);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldCPF.getText().isEmpty()) {throw new Exception("Campo CPF vazio!");	} 
					if (fieldNome.getText().isEmpty()) {throw new Exception("Campo do Nome vazio!");}
					if (fieldDataNasc.getText().isEmpty()) {throw new Exception("Campo da Data de nascimento vazio!");}
					if (fieldRua.getText().isEmpty()) {throw new Exception("Campo do Logradouro vazio!");}
					if (fieldNumero.getText().isEmpty()) {throw new Exception("Campo do numero da casa vazio!");}
					if (fieldBairro.getText().isEmpty()) {throw new Exception("Campo do Bairro vazio!");}
					if (fieldCidade.getText().isEmpty()) {throw new Exception("Campo da Cidade vazio!");}
					if (fieldUF.getSelectedIndex()<0) {throw new Exception("Estado não selecionado!");}
					if (tableConvenios.getSelectedRow()<0) {throw new Exception("Convenio não selecionado!");}
					
					fieldNome.setText(Text.toName(fieldNome.getText()));
					fieldRua.setText(Text.toName(fieldRua.getText()));
					fieldNumero.setText(fieldNumero.getText().toUpperCase());
					fieldBairro.setText(Text.toName(fieldBairro.getText()));
					fieldCidade.setText(Text.toName(fieldCidade.getText()));
					fieldCPF.setText(CPF.cpfFormat(fieldCPF.getText()));
					fieldDataNasc.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataNasc.getText())));
					
					if(!CPF.cpfIsValid(fieldCPF.getText())) {throw new Exception("CPF inválido!");}
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataNasc.getText()))) {throw new Exception("Data de Nascimento inválido!");}
					
					Convenio conv = new ConvenioDAO().get(idConvenios.get(tableConvenios.getSelectedRow()));
					DefaultTableModel modelTel = (DefaultTableModel) tableTelefones.getModel();
					ArrayList<Telefones> telefones = new ArrayList<>();
					Telefones tel;
					for (int i = 0; i<modelTel.getRowCount(); i++) {
						tel = new Telefones(modelTel.getValueAt(i, 0).toString());
						telefones.add(tel);
					}
					
					PacienteDAO dao = new PacienteDAO();
					Endereco end = new Endereco(fieldRua.getText(), fieldNumero.getText(), fieldBairro.getText(), fieldCidade.getText(), fieldUF.getSelectedItem().toString());
					Paciente p = new Paciente(fieldCPF.getText(), fieldNome.getText(), Data.toDateUsFormat(fieldDataNasc.getText()), conv, end);
					p.setTelefone(telefones);
					
					if (dao.exist(p)) {throw new Exception("Paciente já existe!");}
					
					if (dao.create(p) == 1) {
						JOptionPane.showMessageDialog(null, "Paciente Cadastrado com Sucesso!");
						
						fieldCPF.setText(null);
						fieldNome.setText(null);
						fieldDataNasc.setText(null);
						fieldRua.setText(null);
						fieldNumero.setText(null);
						fieldBairro.setText(null);
						fieldCidade.setText(null);
						fieldUF.setSelectedIndex(0);
						
						while (modelTel.getRowCount()>0) {
							modelTel.setRowCount(0);
						}
						
						if (JOptionPane.showConfirmDialog(null, "Deseja criar a Ficha Tecnica do Paciente?")== 0){
							SwitchScreen(ScreenCreateFicha(p));
						} else {
							SwitchScreen(ScreenListPaciente());
						}
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro do(a) Paciente!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(130, 660, 150, 40);
		Screen.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCPF.setText(null);
				fieldNome.setText(null);
				fieldDataNasc.setText(null);
				fieldRua.setText(null);
				fieldNumero.setText(null);
				fieldBairro.setText(null);
				fieldCidade.setText(null);
				fieldUF.setSelectedIndex(0);
				
				DefaultTableModel modelTel = (DefaultTableModel) tableTelefones.getModel();
				while (modelTel.getRowCount()>0) {
					modelTel.setRowCount(0);
				}
				
				
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 660, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListPaciente());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 660, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DE CADASTRO DA FICHA TECNICA ======================== //
	private JPanel ScreenCreateFicha(Paciente pacienteSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro da Ficha Tecnica:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JLabel labelPaciente = new JLabel("Paciente");
		labelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPaciente.setForeground(new Color(245, 245, 245));
		labelPaciente.setBounds(130, 150, 300, 30);
		Screen.add(labelPaciente);
		
		JComboBox fieldPaciente = new JComboBox();
		fieldPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldPaciente.setBounds(130, 180, 550, 30);
		Screen.add(fieldPaciente);
		
		ArrayList<String> cpfPacientes = new ArrayList<>();
		for (Paciente p:new PacienteDAO().getAll()) {
			fieldPaciente.addItem(p.getPaciente());
			cpfPacientes.add(p.getCPF());
			if (pacienteSelected != null) {
				if (p.getCPF().equals(pacienteSelected.getCPF())) {
					fieldPaciente.setSelectedItem(pacienteSelected.getPaciente());
				}
			}
		}
		
		JLabel labelTipoSanguineo = new JLabel("Tipo Sanguíneo");
		labelTipoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoSanguineo.setForeground(new Color(245, 245, 245));
		labelTipoSanguineo.setBounds(130, 250, 300, 30);
		Screen.add(labelTipoSanguineo);
		
		JComboBox fieldTipoSanguineo = new JComboBox();
		fieldTipoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldTipoSanguineo.setBounds(130, 280, 140, 30);
		Screen.add(fieldTipoSanguineo);
		
		for (String t:  new Tipo_SanguineoDAO().getAll()) {
			fieldTipoSanguineo.addItem(t);
		}
		
		JLabel labelHistorico = new JLabel("Historico Familiar");
		labelHistorico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelHistorico.setForeground(new Color(245, 245, 245));
		labelHistorico.setBounds(820, 150, 300, 30);
		Screen.add(labelHistorico);
		
		JTextPane fieldHistorico = new JTextPane();
		fieldHistorico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldHistorico.setBounds(820, 180, 575, 350);
		Screen.add(fieldHistorico);
		
		JLabel labelAlergia = new JLabel("Alergias");
		labelAlergia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelAlergia.setForeground(new Color(245, 245, 245));
		labelAlergia.setBounds(130, 350, 300, 30);
		Screen.add(labelAlergia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 380, 411, 150);
		Screen.add(scrollPane);
		
		JTable tableAlergias = new JTable();
		scrollPane.setViewportView(tableAlergias);
		tableAlergias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableAlergias.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Alergia"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableAlergias.setRowHeight(30);
		tableAlergias.getTableHeader().setBackground(new Color(56, 56, 56));
		tableAlergias.getTableHeader().setForeground(new Color(255, 255, 255));
		tableAlergias.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		
		
		
		JButton btnAddAlergia = new JButton("Adiocionar");
		btnAddAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableAlergias.getModel();
					String alergia = JOptionPane.showInputDialog("Digite a alergia:");
					
					ArrayList<String> alergias = new ArrayList<>();
					for (int i = 0; i<tableAlergias.getRowCount(); i++) {
						alergias.add(tableAlergias.getValueAt(i, 0).toString());
					}
					
					if (alergia != null) {
						if (alergia.isEmpty()) {throw new Exception("Alergia inválida!");}
						if (Text.ExistIn(alergia, alergias)){throw new Exception("Esta alergia já existe na tabela!");}
						
						model.addRow(new Object[] {alergia});
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddAlergia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddAlergia.setBackground(new Color(227, 227, 227));
		btnAddAlergia.setBounds(540, 380, 140, 75);
		Screen.add(btnAddAlergia);
		
		JButton btnDelAlergia = new JButton("Deletar");
		btnDelAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableAlergias.getSelectedRow() == -1) {throw new Exception("Nenhuma Alergia foi selecionada!");}
					DefaultTableModel model = (DefaultTableModel) tableAlergias.getModel();
					model.removeRow(tableAlergias.getSelectedRow());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnDelAlergia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelAlergia.setBackground(new Color(227, 227, 227));
		btnDelAlergia.setBounds(540, 455, 140, 75);
		Screen.add(btnDelAlergia);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldHistorico.getText().isEmpty()) {throw new Exception("Campo do Historico Familiar vazio!");	} 
					if (fieldTipoSanguineo.getSelectedIndex()<0) {throw new Exception("Tipo Sanguíneo não selecionado!");}
					
					
					DefaultTableModel modelAle = (DefaultTableModel) tableAlergias.getModel();
					ArrayList<Alergias> alergias = new ArrayList<>();
					Alergias alergia;
					for (int i = 0; i<modelAle.getRowCount(); i++) {
						alergia = new Alergias(modelAle.getValueAt(i, 0).toString());
						alergias.add(alergia);
					}
					
					Ficha_TecnicaDAO dao = new Ficha_TecnicaDAO();
					Ficha_Tecnica f = new Ficha_Tecnica(cpfPacientes.get(fieldPaciente.getSelectedIndex()), fieldHistorico.getText(), fieldTipoSanguineo.getSelectedItem().toString());
					f.setAlergia(alergias);
					
					if (dao.have(new PacienteDAO().get(f.getId_Paciente()))) {throw new Exception("O Paciente já possui uma ficha!");}
					if (dao.create(f) == 1) {
						JOptionPane.showMessageDialog(null, "Ficha Cadastrada com Sucesso!");
						
						fieldTipoSanguineo.setSelectedIndex(0);
						fieldHistorico.setText(null);
						while (modelAle.getRowCount()>0) {
							modelAle.setRowCount(0);
						}
						
						SwitchScreen(ScreenListPaciente());
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro da Ficha Técnica!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}		
				
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setBounds(130, 615, 150, 40);
		Screen.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldTipoSanguineo.setSelectedIndex(0);
				fieldHistorico.setText(null);
				DefaultTableModel modelAle = (DefaultTableModel) tableAlergias.getModel();
				while (modelAle.getRowCount()>0) {
					modelAle.setRowCount(0);
				}
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setBounds(310, 615, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListPaciente());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 615, 130, 40);
		Screen.add(btnCancelar);
		
		DefaultTableModel modelAle = (DefaultTableModel) tableAlergias.getModel();
		fieldTipoSanguineo.setSelectedIndex(0);
		fieldHistorico.setText(null);
		while (modelAle.getRowCount()>0) {
			modelAle.setRowCount(0);
		}
		
		return Screen;
	}
	
	// ======================== TELA DE EDIÇÃO DO PACIENTE ======================== //
	private JPanel ScreenEditPaciente(Paciente pacienteSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Editando o(a) Paciente "+pacienteSelected.getNome()+": ");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldCPF = new JTextField();
		fieldCPF.setEditable(false);
		fieldCPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCPF.setText(CPF.cpfFormat(fieldCPF.getText()));
				try {
					if (!CPF.cpfIsValid(fieldCPF.getText())){throw new Exception("CPF inválido!");}
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		fieldCPF.setText(pacienteSelected.getCPF());
		
		fieldCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCPF.setBounds(130, 180, 480, 30);
		Screen.add(fieldCPF);
		fieldCPF.setColumns(10);
		
		JLabel labelCPF = new JLabel("CPF");
		labelCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCPF.setForeground(new Color(245, 245, 245));
		labelCPF.setBounds(130, 150, 300, 30);
		Screen.add(labelCPF);
		
		JTextField fieldNome = new JTextField();
		fieldNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNome.setText(Text.toName(fieldNome.getText()));
			}
		});
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNome.setColumns(10);
		fieldNome.setBounds(130, 280, 480, 30);
		Screen.add(fieldNome);
		fieldNome.setText(pacienteSelected.getNome());
		
		JLabel labelNome = new JLabel("Nome Completo");
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNome.setForeground(new Color(245, 245, 245));
		labelNome.setBounds(130, 250, 300, 30);
		Screen.add(labelNome);
		
		JTextField fieldDataNasc = new JTextField();
		fieldDataNasc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataNasc = Data.toDateUsFormat(fieldDataNasc.getText());
				try {
					if (!Data.BirthdayIsValid(dataNasc)){throw new Exception("Data de Nascimento inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataNasc.setText(Data.toDateBrFormat(dataNasc));
			}
		});
		fieldDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataNasc.setColumns(10);
		fieldDataNasc.setBounds(130, 380, 480, 30);
		Screen.add(fieldDataNasc);
		fieldDataNasc.setText(Data.toDateBrFormat(pacienteSelected.getDataNasc()));
		
		JLabel labelDataNasc = new JLabel("Data de Nascimento (dd/mm/aaaa)");
		labelDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataNasc.setForeground(new Color(245, 245, 245));
		labelDataNasc.setBounds(130, 350, 350, 30);
		Screen.add(labelDataNasc);
		
		JTextField fieldRua = new JTextField();
		fieldRua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldRua.setText(Text.toName(fieldRua.getText()));
			}
		});
		fieldRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldRua.setColumns(10);
		fieldRua.setBounds(840, 180, 550, 30);
		Screen.add(fieldRua);
		fieldRua.setText(pacienteSelected.getEndereco().getRua());
		
		JLabel labelRua = new JLabel("Logradouro");
		labelRua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelRua.setForeground(new Color(245, 245, 245));
		labelRua.setBounds(840, 150, 300, 30);
		Screen.add(labelRua);
		
		JTextField fieldBairro = new JTextField();
		fieldBairro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldBairro.setText(Text.toName(fieldBairro.getText()));
			}
		});
		fieldBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldBairro.setColumns(10);
		fieldBairro.setBounds(840, 280, 375, 30);
		Screen.add(fieldBairro);
		fieldBairro.setText(pacienteSelected.getEndereco().getBairro());
		
		JLabel labelBairro = new JLabel("Bairro");
		labelBairro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelBairro.setForeground(new Color(245, 245, 245));
		labelBairro.setBounds(840, 250, 300, 30);
		Screen.add(labelBairro);
		
		JTextField fieldNumero = new JTextField();
		fieldNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNumero.setText(Text.toName(fieldNumero.getText()));
			}
		});
		fieldNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNumero.setColumns(10);
		fieldNumero.setBounds(1250, 280, 140, 30);
		Screen.add(fieldNumero);
		fieldNumero.setText(pacienteSelected.getEndereco().getNro());
		
		JLabel labelNumero = new JLabel("Número");
		labelNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNumero.setForeground(new Color(245, 245, 245));
		labelNumero.setBounds(1250, 250, 140, 30);
		Screen.add(labelNumero);
		
		JTextField fieldCidade = new JTextField();
		fieldCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCidade.setText(Text.toName(fieldCidade.getText()));
			}
		});
		fieldCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCidade.setColumns(10);
		fieldCidade.setBounds(840, 380, 375, 30);
		Screen.add(fieldCidade);
		fieldCidade.setText(pacienteSelected.getEndereco().getCidade());
		
		JLabel labelCidade = new JLabel("Cidade");
		labelCidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCidade.setForeground(new Color(245, 245, 245));
		labelCidade.setBounds(840, 350, 300, 30);
		Screen.add(labelCidade);
		
		JComboBox fieldUF = new JComboBox();
		fieldUF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldUF.setBounds(1250, 380, 140, 30);
		Screen.add(fieldUF);
		
		JLabel labelEstado = new JLabel("Estado");
		labelEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelEstado.setForeground(new Color(245, 245, 245));
		labelEstado.setBounds(1250, 350, 140, 30);
		Screen.add(labelEstado);
		
		ArrayList<String> estados = new UFDAO().getAll();
		for (String uf : estados) {
			fieldUF.addItem(uf);
			if (pacienteSelected.getEndereco().getUF().equals(uf)) {fieldUF.setSelectedItem(uf);}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(820, 480, 600, 150);
		Screen.add(scrollPane);
		
		JTable tableConvenios = new JTable();
		tableConvenios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableConvenios);
		tableConvenios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Convenio", "Valor Mensal", "Descri\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConvenios.setRowHeight(26);
		tableConvenios.getTableHeader().setBackground(new Color(56, 56, 56));
		tableConvenios.getTableHeader().setForeground(new Color(255, 255, 255));
		tableConvenios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tableConvenios.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableConvenios.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableConvenios.getColumnModel().getColumn(2).setPreferredWidth(240);
		tableConvenios.getColumnModel().getColumn(2).setMinWidth(30);
		
		DefaultTableModel modelConv = (DefaultTableModel) tableConvenios.getModel();
		ArrayList<Integer> idConvenios = new ArrayList<>();
		for (Convenio conv: new ConvenioDAO().getAll()) {
			modelConv.addRow(new Object[] {conv.getConvenio(), Text.toMoney(conv.getValor_mensal()), conv.getDescricao()});
			idConvenios.add(conv.getId());
			if (pacienteSelected.getConvenio().getId() == conv.getId()) {
				tableConvenios.setRowSelectionInterval(modelConv.getRowCount()-1, modelConv.getRowCount()-1);
			}
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(130, 480, 342, 150);
		Screen.add(scrollPane_1);
		
		
		JTable tableTelefones = new JTable();
		scrollPane_1.setViewportView(tableTelefones);
		tableTelefones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableTelefones.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Telefone"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableTelefones.setRowHeight(30);
		tableTelefones.getTableHeader().setBackground(new Color(56, 56, 56));
		tableTelefones.getTableHeader().setForeground(new Color(255, 255, 255));
		tableTelefones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelTel = (DefaultTableModel) tableTelefones.getModel();
		for (Telefones tel:pacienteSelected.getTelefone()) {
			modelTel.addRow(new Object[] {tel.getTelefone()});
		}
		
		JLabel labelTelefones = new JLabel("Telefones ");
		labelTelefones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTelefones.setForeground(new Color(245, 245, 245));
		labelTelefones.setBounds(130, 450, 350, 30);
		Screen.add(labelTelefones);
		
		JButton btnAddTel = new JButton("Adiocionar");
		btnAddTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableTelefones.getModel();
					String telefone = Telefone.telefoneFormat(JOptionPane.showInputDialog("Digite o telefone com o ddd:"));
					
					ArrayList<String> telefones = new ArrayList<>();
					for (int i = 0; i<tableTelefones.getRowCount(); i++) {
						telefones.add(tableTelefones.getValueAt(i, 0).toString());
					}
					if (telefone != null) {
						if (telefone.isEmpty()) {throw new Exception("Telefone inválido!");}
						if (Text.ExistIn(telefone, telefones)){throw new Exception("Telefone já existe na tabela!");}
						
						model.addRow(new Object[] {telefone});
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddTel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddTel.setBackground(new Color(227, 227, 227));
		btnAddTel.setBounds(470, 480, 140, 75);
		Screen.add(btnAddTel);
		
		JButton btnDelTel = new JButton("Deletar");
		btnDelTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try{
					if (tableTelefones.getSelectedRow() == -1){throw new Exception("Nenhum Telefone foi selecionado!");}
					DefaultTableModel model = (DefaultTableModel) tableTelefones.getModel();
					model.removeRow(tableTelefones.getSelectedRow());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnDelTel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelTel.setBackground(new Color(227, 227, 227));
		btnDelTel.setBounds(470, 555, 140, 75);
		Screen.add(btnDelTel);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldCPF.getText().isEmpty()) {throw new Exception("Campo CPF vazio!");	} 
					if (fieldNome.getText().isEmpty()) {throw new Exception("Campo do Nome vazio!");}
					if (fieldDataNasc.getText().isEmpty()) {throw new Exception("Campo da Data de nascimento vazio!");}
					if (fieldRua.getText().isEmpty()) {throw new Exception("Campo do Logradouro vazio!");}
					if (fieldNumero.getText().isEmpty()) {throw new Exception("Campo do numero da casa vazio!");}
					if (fieldBairro.getText().isEmpty()) {throw new Exception("Campo do Bairro vazio!");}
					if (fieldCidade.getText().isEmpty()) {throw new Exception("Campo da Cidade vazio!");}
					if (fieldUF.getSelectedIndex()<0) {throw new Exception("Estado não selecionado!");}
					if (tableConvenios.getSelectedRow()<0) {throw new Exception("Convenio não selecionado!");}
					
					fieldNome.setText(Text.toName(fieldNome.getText()));
					fieldRua.setText(Text.toName(fieldRua.getText()));
					fieldNumero.setText(fieldNumero.getText().toUpperCase());
					fieldBairro.setText(Text.toName(fieldBairro.getText()));
					fieldCidade.setText(Text.toName(fieldCidade.getText()));
					fieldCPF.setText(CPF.cpfFormat(fieldCPF.getText()));
					fieldDataNasc.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataNasc.getText())));
					
					if(!CPF.cpfIsValid(fieldCPF.getText())) {throw new Exception("CPF inválido!");}
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataNasc.getText()))) {throw new Exception("Data de Nascimento inválido!");}
					
					Convenio conv = new ConvenioDAO().get(idConvenios.get(tableConvenios.getSelectedRow()));
					DefaultTableModel modelTel = (DefaultTableModel) tableTelefones.getModel();
					ArrayList<Telefones> telefones = new ArrayList<>();
					Telefones tel;
					for (int i = 0; i<modelTel.getRowCount(); i++) {
						tel = new Telefones(modelTel.getValueAt(i, 0).toString());
						telefones.add(tel);
					}
					
					PacienteDAO dao = new PacienteDAO();
					Endereco end = new Endereco(fieldRua.getText(), fieldNumero.getText(), fieldBairro.getText(), fieldCidade.getText(), fieldUF.getSelectedItem().toString());
					Paciente p = new Paciente(fieldCPF.getText(), fieldNome.getText(), Data.toDateUsFormat(fieldDataNasc.getText()), conv, end);
					p.setTelefone(telefones);
					
					if (!dao.exist(p)) {throw new Exception("Paciente não está Cadastrado!");}
					
					
					if (dao.update(p) == 1 || p == pacienteSelected) {
						JOptionPane.showMessageDialog(null, "Paciente Editado com Sucesso!");
						
						fieldCPF.setText(null);
						fieldNome.setText(null);
						fieldDataNasc.setText(null);
						fieldRua.setText(null);
						fieldNumero.setText(null);
						fieldBairro.setText(null);
						fieldCidade.setText(null);
						fieldUF.setSelectedIndex(0);
	
						while (modelTel.getRowCount()>0) {
							modelTel.setRowCount(0);
						}
						
						SwitchScreen(ScreenListPaciente());
						
						
					} else {
						throw new Exception("Erro ao Realizar a Edição do(a) Paciente!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnEditar.setBackground(new Color(85, 200, 100));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(130, 660, 150, 40);
		Screen.add(btnEditar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCPF.setText(null);
				fieldNome.setText(null);
				fieldDataNasc.setText(null);
				fieldRua.setText(null);
				fieldNumero.setText(null);
				fieldBairro.setText(null);
				fieldCidade.setText(null);
				fieldUF.setSelectedIndex(0);
				
				DefaultTableModel modelTel = (DefaultTableModel) tableTelefones.getModel();
				while (modelTel.getRowCount()>0) {
					modelTel.setRowCount(0);
				}
				
				
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 660, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListPaciente());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 660, 130, 40);
		Screen.add(btnCancelar);
	
		return Screen;
	}
	
	// ======================== TELA DE EDIÇÃO DA FICHA TECNICA ======================== //
	private JPanel ScreenEditFicha(Paciente pacienteSelected) {
		Ficha_Tecnica fichaSelected = new Ficha_TecnicaDAO().getOfPaciente(pacienteSelected.getCPF());
		
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Editando a Ficha Tecnica de "+ pacienteSelected.getNome());
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 900, 58);
		Screen.add(titulo);
		
		JLabel labelPaciente = new JLabel("Paciente");
		labelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPaciente.setForeground(new Color(245, 245, 245));
		labelPaciente.setBounds(130, 150, 300, 30);
		Screen.add(labelPaciente);
		
		JComboBox fieldPaciente = new JComboBox();
		fieldPaciente.setEnabled(false);
		fieldPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldPaciente.setBounds(130, 180, 550, 30);
		Screen.add(fieldPaciente);
		
		ArrayList<String> cpfPacientes = new ArrayList<>();
		for (Paciente p:new PacienteDAO().getAll()) {
			fieldPaciente.addItem(p.getPaciente());
			cpfPacientes.add(p.getCPF());
			if (pacienteSelected != null) {
				if (p.getCPF().equals(pacienteSelected.getCPF())) {
					fieldPaciente.setSelectedItem(pacienteSelected.getPaciente());
				}
			}
		}
		
		JLabel labelTipoSanguineo = new JLabel("Tipo Sanguíneo");
		labelTipoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoSanguineo.setForeground(new Color(245, 245, 245));
		labelTipoSanguineo.setBounds(130, 250, 300, 30);
		Screen.add(labelTipoSanguineo);
		
		JComboBox fieldTipoSanguineo = new JComboBox();
		fieldTipoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldTipoSanguineo.setBounds(130, 280, 140, 30);
		Screen.add(fieldTipoSanguineo);
		
		for (String t:  new Tipo_SanguineoDAO().getAll()) {
			fieldTipoSanguineo.addItem(t);
			if (fichaSelected.getTipo_Sanguineo().equals(t)) {
				fieldTipoSanguineo.setSelectedItem(t);
			}
				
		}
		
		JLabel labelAlergia = new JLabel("Alergias");
		labelAlergia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelAlergia.setForeground(new Color(245, 245, 245));
		labelAlergia.setBounds(130, 350, 300, 30);
		Screen.add(labelAlergia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 380, 411, 150);
		Screen.add(scrollPane);
		
		JTable tableAlergias = new JTable();
		scrollPane.setViewportView(tableAlergias);
		tableAlergias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableAlergias.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Alergia"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableAlergias.setRowHeight(30);
		tableAlergias.getTableHeader().setBackground(new Color(56, 56, 56));
		tableAlergias.getTableHeader().setForeground(new Color(255, 255, 255));
		tableAlergias.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelAle = (DefaultTableModel) tableAlergias.getModel();
		for (Alergias a: fichaSelected.getAlergia()) {
			modelAle.addRow(new Object[] {a.getAlergia()});
		}
		
		JLabel labelHistorico = new JLabel("Historico Familiar");
		labelHistorico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelHistorico.setForeground(new Color(245, 245, 245));
		labelHistorico.setBounds(820, 150, 300, 30);
		Screen.add(labelHistorico);
		
		JTextPane fieldHistorico = new JTextPane();
		fieldHistorico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldHistorico.setBounds(820, 180, 575, 350);
		Screen.add(fieldHistorico);
		fieldHistorico.setText(fichaSelected.getHistorico_familiar());
		
		
		JButton btnAddAlergia = new JButton("Adiocionar");
		btnAddAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableAlergias.getModel();
					String alergia = JOptionPane.showInputDialog("Digite a alergia:");
					
					ArrayList<String> alergias = new ArrayList<>();
					for (int i = 0; i<tableAlergias.getRowCount(); i++) {
						alergias.add(tableAlergias.getValueAt(i, 0).toString());
					}
					
					if (alergia != null) {
						if (alergia.isEmpty()) {throw new Exception("Alergia inválida!");}
						if (Text.ExistIn(alergia, alergias)){throw new Exception("Esta alergia já existe na tabela!");}
						
						model.addRow(new Object[] {alergia});
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddAlergia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddAlergia.setBackground(new Color(227, 227, 227));
		btnAddAlergia.setBounds(540, 380, 140, 75);
		Screen.add(btnAddAlergia);
		
		JButton btnDelAlergia = new JButton("Deletar");
		btnDelAlergia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableAlergias.getSelectedRow() == -1) {throw new Exception("Nenhuma Alergia foi selecionada!");}
					DefaultTableModel model = (DefaultTableModel) tableAlergias.getModel();
					model.removeRow(tableAlergias.getSelectedRow());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnDelAlergia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelAlergia.setBackground(new Color(227, 227, 227));
		btnDelAlergia.setBounds(540, 455, 140, 75);
		Screen.add(btnDelAlergia);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldHistorico.getText().isEmpty()) {throw new Exception("Campo do Historico Familiar vazio!");	} 
					if (fieldTipoSanguineo.getSelectedIndex()<0) {throw new Exception("Tipo Sanguíneo não selecionado!");}
					
					
					DefaultTableModel modelAle = (DefaultTableModel) tableAlergias.getModel();
					ArrayList<Alergias> alergias = new ArrayList<>();
					Alergias alergia;
					for (int i = 0; i<modelAle.getRowCount(); i++) {
						alergia = new Alergias(modelAle.getValueAt(i, 0).toString());
						alergias.add(alergia);
					}
					
					Ficha_TecnicaDAO dao = new Ficha_TecnicaDAO();
					Ficha_Tecnica f = new Ficha_Tecnica(cpfPacientes.get(fieldPaciente.getSelectedIndex()), fieldHistorico.getText(), fieldTipoSanguineo.getSelectedItem().toString());
					f.setAlergia(alergias);
					f.setProntuario(fichaSelected.getProntuario());
					
					if (!dao.have(new PacienteDAO().get(f.getId_Paciente()))) {throw new Exception("O(a) Paciente não possui uma ficha!");}
					if (dao.update(f) == 1 || f == fichaSelected) {
						JOptionPane.showMessageDialog(null, "Ficha Editada com Sucesso!");
						
						fieldTipoSanguineo.setSelectedIndex(0);
						fieldHistorico.setText(null);
						while (modelAle.getRowCount()>0) {
							modelAle.setRowCount(0);
						}
						
						SwitchScreen(ScreenListPaciente());
						
					} else {
						throw new Exception("Erro ao Realizar a Edição da Ficha Tecnica!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}		
				
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBackground(new Color(85, 200, 100));
		btnEditar.setBounds(130, 615, 150, 40);
		Screen.add(btnEditar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldTipoSanguineo.setSelectedIndex(0);
				fieldHistorico.setText(null);
				DefaultTableModel modelAle = (DefaultTableModel) tableAlergias.getModel();
				while (modelAle.getRowCount()>0) {
					modelAle.setRowCount(0);
				}
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setBounds(310, 615, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListPaciente());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 615, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DA LISTA DE MEDICOS ======================== //
	private JPanel ScreenListMedico() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Lista de Médicos:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1000, 430);
		Screen.add(scrollPane);
		
		JTable tableMedicos = new JTable();
		tableMedicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableMedicos);
		tableMedicos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CRM", "Nome", "Data de Inscri\u00E7\u00E3o", "Especializa\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableMedicos.setRowHeight(30);
		tableMedicos.getTableHeader().setBackground(new Color(56, 56, 56));
		tableMedicos.getTableHeader().setForeground(new Color(255, 255, 255));
		tableMedicos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel model = (DefaultTableModel) tableMedicos.getModel();
		idMedicos = new ArrayList<>();
		for (Medico m:new MedicoDAO().getAll()) {
			model.addRow(new Object[] {m.getCRM(), m.getNome(), Data.toDateBrFormat(m.getDataInscricao()), m.getEspecializacao()});
			idMedicos.add(m.getCRM());
		}
		
		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (model.getRowCount()>0) {
					model.setRowCount(0);
				}
				
				idMedicos = new ArrayList<>();
				for (Medico m:new MedicoDAO().search(pesquisar.getText())) {
					model.addRow(new Object[] {m.getCRM(), m.getNome(), m.getDataInscricao(), m.getEspecializacao()});
					idMedicos.add(m.getCRM());
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenCreateMedico());
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionar.setBackground(new Color(227, 227, 227));
		btnAdicionar.setBounds(1130, 250, 260, 144);
		Screen.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableMedicos.getSelectedRow() == -1) {throw new Exception("Nenhum Médico foi selecionado!");}
					SwitchScreen(ScreenEditMedico(new MedicoDAO().get(idMedicos.get(tableMedicos.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEditar.setBackground(new Color(227, 227, 227));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(1130, 394, 260, 143);
		Screen.add(btnEditar);
		
		JButton btnDelete = new JButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MedicoDAO dao = new MedicoDAO();
					if(tableMedicos.getSelectedRow() == -1) {throw new Exception("Nenhum Médico foi selecionado!");}
					if (JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar este Médico(a)?") == 0) {
						if (dao.delete(dao.get(idMedicos.get(tableMedicos.getSelectedRow())))==1) {
							JOptionPane.showMessageDialog(null, "Médico(a) deletado com Sucesso!");
							
							while (model.getRowCount()>0) {
								model.setRowCount(0);
							}
							
							idMedicos = new ArrayList<>();
							for (Medico m:new MedicoDAO().search(pesquisar.getText())) {
								model.addRow(new Object[] {m.getCRM(), m.getNome(), m.getDataInscricao(), m.getEspecializacao()});
								idMedicos.add(m.getCRM());
							}
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao deletar o Médico(a)!");
						}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnDelete.setBackground(new Color(227, 227, 227));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(1130, 537, 260, 143);
		Screen.add(btnDelete);
		
		return Screen;
	}

	// ======================== TELA DE CADASTRO DO MEDICO ======================== //
	private JPanel ScreenCreateMedico() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro de Médico:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldCRM = new JTextField();
		fieldCRM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCRM.setText(Conselho.conselhoFormat(fieldCRM.getText()));
			}
		});
		
		fieldCRM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCRM.setBounds(130, 180, 480, 30);
		Screen.add(fieldCRM);
		fieldCRM.setColumns(10);
		
		JLabel labelCRM = new JLabel("CRM");
		labelCRM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCRM.setForeground(new Color(245, 245, 245));
		labelCRM.setBounds(130, 150, 300, 30);
		Screen.add(labelCRM);
		
		JTextField fieldNome = new JTextField();
		fieldNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNome.setText(Text.toName(fieldNome.getText()));
			}
		});
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNome.setColumns(10);
		fieldNome.setBounds(830, 180, 540, 30);
		Screen.add(fieldNome);
		
		JLabel labelNome = new JLabel("Nome Completo");
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNome.setForeground(new Color(245, 245, 245));
		labelNome.setBounds(830, 150, 300, 30);
		Screen.add(labelNome);
		
		JTextField fieldDataInscricao = new JTextField();
		fieldDataInscricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataInscricao.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data de Inscrição inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataInscricao.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataInscricao.setColumns(10);
		fieldDataInscricao.setBounds(130, 330, 480, 30);
		Screen.add(fieldDataInscricao);
		
		JLabel labelDataInscricao = new JLabel("Data de Inscrição (dd/mm/aaaa)");
		labelDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataInscricao.setForeground(new Color(245, 245, 245));
		labelDataInscricao.setBounds(130, 300, 350, 30);
		Screen.add(labelDataInscricao);
		
		JTextField fieldEspecializacao = new JTextField();
		fieldEspecializacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldEspecializacao.setColumns(10);
		fieldEspecializacao.setBounds(830, 330, 540, 30);
		Screen.add(fieldEspecializacao);
		
		JLabel labelEspecializacacao = new JLabel("Especializacao");
		labelEspecializacacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelEspecializacacao.setForeground(new Color(245, 245, 245));
		labelEspecializacacao.setBounds(830, 300, 350, 30);
		Screen.add(labelEspecializacacao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldCRM.getText().isEmpty()) {throw new Exception("Campo CRM vazio!");	} 
					if (fieldNome.getText().isEmpty()) {throw new Exception("Campo do Nome vazio!");}
					if (fieldDataInscricao.getText().isEmpty()) {throw new Exception("Campo da Data de Inscrição vazio!");}
					if (fieldEspecializacao.getText().isEmpty()) {throw new Exception("Campo Especialização vazio!");}
					
					
					fieldNome.setText(Text.toName(fieldNome.getText()));
					fieldCRM.setText(Conselho.conselhoFormat(fieldCRM.getText()));
					fieldDataInscricao.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataInscricao.getText())));
					fieldEspecializacao.setText(Text.toName(fieldEspecializacao.getText()));               
					
					if(!Conselho.conselhoIsValid(fieldCRM.getText())) {throw new Exception("CRM inválido!");}
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataInscricao.getText()))) {throw new Exception("Data de Inscrição inválida!");}
					
					
					MedicoDAO dao = new MedicoDAO();
					Medico m = new Medico(fieldCRM.getText(), fieldNome.getText(), Data.toDateUsFormat(fieldDataInscricao.getText()), fieldEspecializacao.getText());
					
					if (dao.exist(m)) {throw new Exception("Médico(a) já existe!");}
					
					if (dao.create(m) == 1) {
						JOptionPane.showMessageDialog(null, "Médico(a) Cadastrado(a) com Sucesso!");
						
						fieldCRM.setText(null);
						fieldNome.setText(null);
						fieldDataInscricao.setText(null);
						fieldEspecializacao.setText(null);
						
						SwitchScreen(ScreenListMedico());
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro do(a) Médico(a)!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(130, 590, 150, 40);
		Screen.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCRM.setText(null);
				fieldNome.setText(null);
				fieldDataInscricao.setText(null);
				fieldEspecializacao.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListMedico());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DE EDIÇÃO DO MEDICO ======================== //
	private JPanel ScreenEditMedico(Medico medicoSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Editando o(a) Médico(a) "+medicoSelected.getNome()+":");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldCRM = new JTextField();
		fieldCRM.setEditable(false);
		fieldCRM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCRM.setText(Conselho.conselhoFormat(fieldCRM.getText()));
			}
		});
		
		fieldCRM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCRM.setBounds(130, 180, 480, 30);
		Screen.add(fieldCRM);
		fieldCRM.setColumns(10);
		fieldCRM.setText(medicoSelected.getCRM());
		
		JLabel labelCRM = new JLabel("CRM");
		labelCRM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCRM.setForeground(new Color(245, 245, 245));
		labelCRM.setBounds(130, 150, 300, 30);
		Screen.add(labelCRM);
		
		JTextField fieldNome = new JTextField();
		fieldNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNome.setText(Text.toName(fieldNome.getText()));
			}
		});
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNome.setColumns(10);
		fieldNome.setBounds(830, 180, 540, 30);
		Screen.add(fieldNome);
		fieldNome.setText(medicoSelected.getNome());
		
		JLabel labelNome = new JLabel("Nome Completo");
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNome.setForeground(new Color(245, 245, 245));
		labelNome.setBounds(830, 150, 300, 30);
		Screen.add(labelNome);
		
		JTextField fieldDataInscricao = new JTextField();
		fieldDataInscricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataInscricao.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data de Inscrição inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataInscricao.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataInscricao.setColumns(10);
		fieldDataInscricao.setBounds(130, 330, 480, 30);
		Screen.add(fieldDataInscricao);
		fieldDataInscricao.setText(Data.toDateBrFormat(medicoSelected.getDataInscricao()));
		
		JLabel labelDataInscricao = new JLabel("Data de Inscrição (dd/mm/aaaa)");
		labelDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataInscricao.setForeground(new Color(245, 245, 245));
		labelDataInscricao.setBounds(130, 300, 350, 30);
		Screen.add(labelDataInscricao);
		
		JTextField fieldEspecializacao = new JTextField();
		fieldEspecializacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldEspecializacao.setColumns(10);
		fieldEspecializacao.setBounds(830, 330, 540, 30);
		Screen.add(fieldEspecializacao);
		
		JLabel labelEspecializacacao = new JLabel("Especializacao");
		labelEspecializacacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelEspecializacacao.setForeground(new Color(245, 245, 245));
		labelEspecializacacao.setBounds(830, 300, 350, 30);
		Screen.add(labelEspecializacacao);
		fieldEspecializacao.setText(medicoSelected.getEspecializacao());
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldCRM.getText().isEmpty()) {throw new Exception("Campo CRM vazio!");	} 
					if (fieldNome.getText().isEmpty()) {throw new Exception("Campo do Nome vazio!");}
					if (fieldDataInscricao.getText().isEmpty()) {throw new Exception("Campo da Data de Inscrição vazio!");}
					if (fieldEspecializacao.getText().isEmpty()) {throw new Exception("Campo Especialização vazio!");}
					
					
					fieldNome.setText(Text.toName(fieldNome.getText()));
					fieldCRM.setText(Conselho.conselhoFormat(fieldCRM.getText()));
					fieldDataInscricao.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataInscricao.getText())));
					fieldEspecializacao.setText(Text.toName(fieldEspecializacao.getText()));               
					
					if(!Conselho.conselhoIsValid(fieldCRM.getText())) {throw new Exception("CRM inválido!");}
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataInscricao.getText()))) {throw new Exception("Data de Inscrição inválida!");}
					
					
					MedicoDAO dao = new MedicoDAO();
					Medico m = new Medico(fieldCRM.getText(), fieldNome.getText(), Data.toDateUsFormat(fieldDataInscricao.getText()), fieldEspecializacao.getText());
					
					if (!dao.exist(m)) {throw new Exception("Médico(a) não existe!");}
					
					if (dao.update(m) == 1 || m == medicoSelected) {
						JOptionPane.showMessageDialog(null, "Médico(a) Editado(a) com Sucesso!");
						
						fieldCRM.setText(null);
						fieldNome.setText(null);
						fieldDataInscricao.setText(null);
						fieldEspecializacao.setText(null);
						
						SwitchScreen(ScreenListMedico());
						
					} else {
						throw new Exception("Erro ao Realizar a Edição do(a) Médico(a)!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnEditar.setBackground(new Color(85, 200, 100));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(130, 590, 150, 40);
		Screen.add(btnEditar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCRM.setText(null);
				fieldNome.setText(null);
				fieldDataInscricao.setText(null);
				fieldEspecializacao.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListMedico());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		Screen.add(btnCancelar);

		return Screen;
	}

	// ======================== TELA DA LISTA DE ENFERMEIROS ======================== //
	private JPanel ScreenListEnfermeiro() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Lista de Enfermeiros:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1000, 430);
		Screen.add(scrollPane);
		
		JTable tableEnfermeiros = new JTable();
		tableEnfermeiros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableEnfermeiros);
		tableEnfermeiros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COREN", "Nome", "Data de Inscri\u00E7\u00E3o", "Especializa\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableEnfermeiros.setRowHeight(30);
		tableEnfermeiros.getTableHeader().setBackground(new Color(56, 56, 56));
		tableEnfermeiros.getTableHeader().setForeground(new Color(255, 255, 255));
		tableEnfermeiros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel model = (DefaultTableModel) tableEnfermeiros.getModel();
		idEnfermeiros = new ArrayList<>();
		for (Enfermeiro enf:new EnfermeiroDAO().getAll()) {
			model.addRow(new Object[] {enf.getCoren(), enf.getNome(), Data.toDateBrFormat(enf.getDataInscricao()), enf.getEspecializacao()});
			idEnfermeiros.add(enf.getCoren());
		}
		
		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (model.getRowCount()>0) {
					model.setRowCount(0);
				}
				
				idEnfermeiros = new ArrayList<>();
				for (Enfermeiro enf:new EnfermeiroDAO().search(pesquisar.getText())) {
					model.addRow(new Object[] {enf.getCoren(), enf.getNome(), Data.toDateBrFormat(enf.getDataInscricao()), enf.getEspecializacao()});
					idEnfermeiros.add(enf.getCoren());
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenCreateEnfermeiro());
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionar.setBackground(new Color(227, 227, 227));
		btnAdicionar.setBounds(1130, 250, 260, 144);
		Screen.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableEnfermeiros.getSelectedRow() == -1) {throw new Exception("Nenhum Enfermeiro foi selecionado!");}
					SwitchScreen(ScreenEditEnfermeiro(new EnfermeiroDAO().get(idEnfermeiros.get(tableEnfermeiros.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEditar.setBackground(new Color(227, 227, 227));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(1130, 394, 260, 143);
		Screen.add(btnEditar);
		
		JButton btnDelete = new JButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EnfermeiroDAO dao = new EnfermeiroDAO();
					if(tableEnfermeiros.getSelectedRow() == -1) {throw new Exception("Nenhum Enfermeiro foi selecionado!");}
					if (JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar este Enfermeiro(a)?") == 0) {
						if (dao.delete(dao.get(idEnfermeiros.get(tableEnfermeiros.getSelectedRow())))==1) {
							JOptionPane.showMessageDialog(null, "Enfermeiro(a) deletado(a) com Sucesso!");
							
							while (model.getRowCount()>0) {
								model.setRowCount(0);
							}
							
							idEnfermeiros = new ArrayList<>();
							for (Enfermeiro enf:new EnfermeiroDAO().search(pesquisar.getText())) {
								model.addRow(new Object[] {enf.getCoren(), enf.getNome(), Data.toDateBrFormat(enf.getDataInscricao()), enf.getEspecializacao()});
								idEnfermeiros.add(enf.getCoren());
							}
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao deletar o(a) Enfermeiro(a)!");
						}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnDelete.setBackground(new Color(227, 227, 227));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(1130, 537, 260, 143);
		Screen.add(btnDelete);
		
		return Screen;
	}

	// ======================== TELA DE CADASTRO DO ENFERMEIRO ======================== //
	private JPanel ScreenCreateEnfermeiro() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro de Enfermeiro:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldCoren = new JTextField();
		fieldCoren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCoren.setText(Conselho.conselhoFormat(fieldCoren.getText()));
			}
		});
		
		fieldCoren.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCoren.setBounds(130, 180, 480, 30);
		Screen.add(fieldCoren);
		fieldCoren.setColumns(10);
		
		JLabel labelCoren = new JLabel("COREN");
		labelCoren.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCoren.setForeground(new Color(245, 245, 245));
		labelCoren.setBounds(130, 150, 300, 30);
		Screen.add(labelCoren);
		
		JTextField fieldNome = new JTextField();
		fieldNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNome.setText(Text.toName(fieldNome.getText()));
			}
		});
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNome.setColumns(10);
		fieldNome.setBounds(830, 180, 540, 30);
		Screen.add(fieldNome);
		
		JLabel labelNome = new JLabel("Nome Completo");
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNome.setForeground(new Color(245, 245, 245));
		labelNome.setBounds(830, 150, 300, 30);
		Screen.add(labelNome);
		
		JTextField fieldDataInscricao = new JTextField();
		fieldDataInscricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataInscricao.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data de Inscrição inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataInscricao.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataInscricao.setColumns(10);
		fieldDataInscricao.setBounds(130, 330, 480, 30);
		Screen.add(fieldDataInscricao);
		
		JLabel labelDataInscricao = new JLabel("Data de Inscrição (dd/mm/aaaa)");
		labelDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataInscricao.setForeground(new Color(245, 245, 245));
		labelDataInscricao.setBounds(130, 300, 350, 30);
		Screen.add(labelDataInscricao);
		
		JTextField fieldEspecializacao = new JTextField();
		fieldEspecializacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldEspecializacao.setColumns(10);
		fieldEspecializacao.setBounds(830, 330, 540, 30);
		Screen.add(fieldEspecializacao);
		
		JLabel labelEspecializacacao = new JLabel("Especializacao");
		labelEspecializacacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelEspecializacacao.setForeground(new Color(245, 245, 245));
		labelEspecializacacao.setBounds(830, 300, 350, 30);
		Screen.add(labelEspecializacacao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldCoren.getText().isEmpty()) {throw new Exception("Campo COREN vazio!");	} 
					if (fieldNome.getText().isEmpty()) {throw new Exception("Campo do Nome vazio!");}
					if (fieldDataInscricao.getText().isEmpty()) {throw new Exception("Campo da Data de Inscrição vazio!");}
					if (fieldEspecializacao.getText().isEmpty()) {throw new Exception("Campo Especialização vazio!");}
					
					
					fieldNome.setText(Text.toName(fieldNome.getText()));
					fieldCoren.setText(Conselho.conselhoFormat(fieldCoren.getText()));
					fieldDataInscricao.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataInscricao.getText())));
					fieldEspecializacao.setText(Text.toName(fieldEspecializacao.getText()));               
					
					if(!Conselho.conselhoIsValid(fieldCoren.getText())) {throw new Exception("COREN inválido!");}
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataInscricao.getText()))) {throw new Exception("Data de Inscrição inválida!");}
					
					
					EnfermeiroDAO dao = new EnfermeiroDAO();
					Enfermeiro enf = new Enfermeiro(fieldCoren.getText(), fieldNome.getText(), fieldEspecializacao.getText(), Data.toDateUsFormat(fieldDataInscricao.getText()));
					
					if (new EnfermariaDAO().exist(enf)) {throw new Exception("Enfermeiro(a) já existe!");}
					
					if (dao.create(enf) == 1) {
						JOptionPane.showMessageDialog(null, "Enfermeiro(a) Cadastrado(a) com Sucesso!");
						
						fieldCoren.setText(null);
						fieldNome.setText(null);
						fieldDataInscricao.setText(null);
						fieldEspecializacao.setText(null);
						
						SwitchScreen(ScreenListEnfermeiro());
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro do(a) Enfermeiro(a)!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(130, 590, 150, 40);
		Screen.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCoren.setText(null);
				fieldNome.setText(null);
				fieldDataInscricao.setText(null);
				fieldEspecializacao.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListEnfermeiro());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DE EDIÇÃO DO ENFERMEIRO ======================== //
	private JPanel ScreenEditEnfermeiro(Enfermeiro enfermeiroSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Editando Enfermeiro(a) "+enfermeiroSelected.getNome()+":");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldCoren = new JTextField();
		fieldCoren.setEditable(false);
		fieldCoren.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCoren.setBounds(130, 180, 480, 30);
		Screen.add(fieldCoren);
		fieldCoren.setColumns(10);
		fieldCoren.setText(enfermeiroSelected.getCoren());
		
		JLabel labelCoren = new JLabel("COREN");
		labelCoren.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCoren.setForeground(new Color(245, 245, 245));
		labelCoren.setBounds(130, 150, 300, 30);
		Screen.add(labelCoren);
		
		JTextField fieldNome = new JTextField();
		fieldNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNome.setText(Text.toName(fieldNome.getText()));
			}
		});
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNome.setColumns(10);
		fieldNome.setBounds(830, 180, 540, 30);
		Screen.add(fieldNome);
		fieldNome.setText(enfermeiroSelected.getNome());
		
		JLabel labelNome = new JLabel("Nome Completo");
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNome.setForeground(new Color(245, 245, 245));
		labelNome.setBounds(830, 150, 300, 30);
		Screen.add(labelNome);
		
		JTextField fieldDataInscricao = new JTextField();
		fieldDataInscricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataInscricao.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data de Inscrição inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataInscricao.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataInscricao.setColumns(10);
		fieldDataInscricao.setBounds(130, 330, 480, 30);
		Screen.add(fieldDataInscricao);
		fieldDataInscricao.setText(Data.toDateBrFormat(enfermeiroSelected.getDataInscricao()));
		
		JLabel labelDataInscricao = new JLabel("Data de Inscrição (dd/mm/aaaa)");
		labelDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataInscricao.setForeground(new Color(245, 245, 245));
		labelDataInscricao.setBounds(130, 300, 350, 30);
		Screen.add(labelDataInscricao);
		
		JTextField fieldEspecializacao = new JTextField();
		fieldEspecializacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldEspecializacao.setColumns(10);
		fieldEspecializacao.setBounds(830, 330, 540, 30);
		Screen.add(fieldEspecializacao);
		fieldEspecializacao.setText(enfermeiroSelected.getEspecializacao());
		
		JLabel labelEspecializacacao = new JLabel("Especializacao");
		labelEspecializacacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelEspecializacacao.setForeground(new Color(245, 245, 245));
		labelEspecializacacao.setBounds(830, 300, 350, 30);
		Screen.add(labelEspecializacacao);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldCoren.getText().isEmpty()) {throw new Exception("Campo COREN vazio!");	} 
					if (fieldNome.getText().isEmpty()) {throw new Exception("Campo do Nome vazio!");}
					if (fieldDataInscricao.getText().isEmpty()) {throw new Exception("Campo da Data de Inscrição vazio!");}
					if (fieldEspecializacao.getText().isEmpty()) {throw new Exception("Campo Especialização vazio!");}
					
					
					fieldNome.setText(Text.toName(fieldNome.getText()));
					fieldCoren.setText(Conselho.conselhoFormat(fieldCoren.getText()));
					fieldDataInscricao.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataInscricao.getText())));
					fieldEspecializacao.setText(Text.toName(fieldEspecializacao.getText()));               
					
					if(!Conselho.conselhoIsValid(fieldCoren.getText())) {throw new Exception("COREN inválido!");}
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataInscricao.getText()))) {throw new Exception("Data de Inscrição inválida!");}
					
					
					EnfermeiroDAO dao = new EnfermeiroDAO();
					Enfermeiro enf = new Enfermeiro(fieldCoren.getText(), fieldNome.getText(), fieldEspecializacao.getText(), Data.toDateUsFormat(fieldDataInscricao.getText()));
					
					if (!new EnfermariaDAO().exist(enf)) {throw new Exception("Enfermeiro(a) não existe!");}
					
					if (dao.update(enf) == 1 || enf == enfermeiroSelected) {
						JOptionPane.showMessageDialog(null, "Enfermeiro(a) Editado(a) com Sucesso!");
						
						fieldCoren.setText(null);
						fieldNome.setText(null);
						fieldDataInscricao.setText(null);
						fieldEspecializacao.setText(null);
						
						SwitchScreen(ScreenListEnfermeiro());
						
					} else {
						throw new Exception("Erro ao Realizar a Edição do(a) Enfermeiro(a)!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnEditar.setBackground(new Color(85, 200, 100));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(130, 590, 150, 40);
		Screen.add(btnEditar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCoren.setText(null);
				fieldNome.setText(null);
				fieldDataInscricao.setText(null);
				fieldEspecializacao.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListEnfermeiro());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DA LISTA DE TECNICOS DE ENFERMAGEM ======================== //
	private JPanel ScreenListTecnicoDeEnfermagem() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);

		JLabel titulo = new JLabel("Lista de Tecnicos de Enfermagem:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1000, 430);
		Screen.add(scrollPane);

		JTable tableTecnicos_de_Enfermagem = new JTable();
		tableTecnicos_de_Enfermagem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableTecnicos_de_Enfermagem);
		tableTecnicos_de_Enfermagem.setModel(new DefaultTableModel(
		    new Object[][] {
		    },
		    new String[] {
		        "COREN", "Nome", "Data de Inscri\u00E7\u00E3o"
		    }
		) {
		    Class[] columnTypes = new Class[] {
		        String.class, String.class, String.class
		    };
		    public Class getColumnClass(int columnIndex) {
		        return columnTypes[columnIndex];
		    }
		    boolean[] columnEditables = new boolean[] {
		        false, false, false
		    };
		    public boolean isCellEditable(int row, int column) {
		        return columnEditables[column];
		    }
		});
		tableTecnicos_de_Enfermagem.setRowHeight(30);
		tableTecnicos_de_Enfermagem.getTableHeader().setBackground(new Color(56, 56, 56));
		tableTecnicos_de_Enfermagem.getTableHeader().setForeground(new Color(255, 255, 255));
		tableTecnicos_de_Enfermagem.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

		DefaultTableModel model = (DefaultTableModel) tableTecnicos_de_Enfermagem.getModel();
		idTecnicos_de_Enfermagem = new ArrayList<>();
		for (Tecnico_de_Enfermagem enf:new Tecnico_de_EnfermagemDAO().getAll()) {
		    model.addRow(new Object[] {enf.getCoren(), enf.getNome(), Data.toDateBrFormat(enf.getDataInscricao())});
		    idTecnicos_de_Enfermagem.add(enf.getCoren());
		}

		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);

		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        while (model.getRowCount()>0) {
		            model.setRowCount(0);
		        }
		        
		        idTecnicos_de_Enfermagem = new ArrayList<>();
		        for (Tecnico_de_Enfermagem enf:new Tecnico_de_EnfermagemDAO().search(pesquisar.getText())) {
		            model.addRow(new Object[] {enf.getCoren(), enf.getNome(), Data.toDateBrFormat(enf.getDataInscricao())});
		            idTecnicos_de_Enfermagem.add(enf.getCoren());
		        }
		        
		    }
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        SwitchScreen(ScreenCreateTecnicoDeEnfermagem());
		    }
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionar.setBackground(new Color(227, 227, 227));
		btnAdicionar.setBounds(1130, 250, 260, 144);
		Screen.add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            if(tableTecnicos_de_Enfermagem.getSelectedRow() == -1) {throw new Exception("Nenhum Tecnico de Enfermagem foi selecionado!");}
		            SwitchScreen(ScreenEditTecnicoDeEnfermagem(new Tecnico_de_EnfermagemDAO().get(idTecnicos_de_Enfermagem.get(tableTecnicos_de_Enfermagem.getSelectedRow()))));
		        }catch(Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage());
		        }
		    }
		});
		btnEditar.setBackground(new Color(227, 227, 227));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(1130, 394, 260, 143);
		Screen.add(btnEditar);

		JButton btnDelete = new JButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            Tecnico_de_EnfermagemDAO dao = new Tecnico_de_EnfermagemDAO();
		            if(tableTecnicos_de_Enfermagem.getSelectedRow() == -1) {throw new Exception("Nenhum Tecnico de Enfermagem foi selecionado!");}
		            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar este Tecnico(a) de Enfermagem?") == 0) {
		                if (dao.delete(dao.get(idTecnicos_de_Enfermagem.get(tableTecnicos_de_Enfermagem.getSelectedRow())))==1) {
		                    JOptionPane.showMessageDialog(null, "Tecnico(a) de Enfermagem deletado(a) com Sucesso!");
		                    
		                    while (model.getRowCount()>0) {
		    		            model.setRowCount(0);
		    		        }
		    		        
		    		        idTecnicos_de_Enfermagem = new ArrayList<>();
		    		        for (Tecnico_de_Enfermagem enf:new Tecnico_de_EnfermagemDAO().search(pesquisar.getText())) {
		    		            model.addRow(new Object[] {enf.getCoren(), enf.getNome(), Data.toDateBrFormat(enf.getDataInscricao())});
		    		            idTecnicos_de_Enfermagem.add(enf.getCoren());
		    		        }
		                }else {
		                    JOptionPane.showMessageDialog(null, "Erro ao deletar o(a) Tecnico(a) de Enfermagem!");
		                }
		            }
		        }catch(Exception ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage());
		        }
		    }
		});
		btnDelete.setBackground(new Color(227, 227, 227));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(1130, 537, 260, 143);
		Screen.add(btnDelete);
		
		return Screen;
	}

	// ======================== TELA DE CADASTRO DO TECNICO DE ENFERMAGEM ======================== //
	private JPanel ScreenCreateTecnicoDeEnfermagem() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro de Técnico de Enfermagem:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldCoren = new JTextField();
		fieldCoren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCoren.setText(Conselho.conselhoFormat(fieldCoren.getText()));
			}
		});
		fieldCoren.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCoren.setBounds(130, 180, 540, 30);
		Screen.add(fieldCoren);
		fieldCoren.setColumns(10);
		
		JLabel labelCoren = new JLabel("COREN");
		labelCoren.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCoren.setForeground(new Color(245, 245, 245));
		labelCoren.setBounds(130, 150, 300, 30);
		Screen.add(labelCoren);
		
		JTextField fieldNome = new JTextField();
		fieldNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNome.setText(Text.toName(fieldNome.getText()));
			}
		});
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNome.setColumns(10);
		fieldNome.setBounds(130, 280, 540, 30);
		Screen.add(fieldNome);
		
		JLabel labelNome = new JLabel("Nome Completo");
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNome.setForeground(new Color(245, 245, 245));
		labelNome.setBounds(130, 250, 300, 30);
		Screen.add(labelNome);
		
		JTextField fieldDataInscricao = new JTextField();
		fieldDataInscricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataInscricao.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data de Inscrição inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataInscricao.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataInscricao.setColumns(10);
		fieldDataInscricao.setBounds(130, 380, 540, 30);
		Screen.add(fieldDataInscricao);
		
		JLabel labelDataInscricao = new JLabel("Data de Inscrição (dd/mm/aaaa)");
		labelDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataInscricao.setForeground(new Color(245, 245, 245));
		labelDataInscricao.setBounds(130, 350, 350, 30);
		Screen.add(labelDataInscricao);
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldCoren.getText().isEmpty()) {throw new Exception("Campo COREN vazio!");	} 
					if (fieldNome.getText().isEmpty()) {throw new Exception("Campo do Nome vazio!");}
					if (fieldDataInscricao.getText().isEmpty()) {throw new Exception("Campo da Data de Inscrição vazio!");}
					
					
					fieldNome.setText(Text.toName(fieldNome.getText()));
					fieldCoren.setText(Conselho.conselhoFormat(fieldCoren.getText()));
					fieldDataInscricao.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataInscricao.getText())));               
					
					if(!Conselho.conselhoIsValid(fieldCoren.getText())) {throw new Exception("COREN inválido!");}
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataInscricao.getText()))) {throw new Exception("Data de Inscrição inválida!");}
					
					
					Tecnico_de_EnfermagemDAO dao = new Tecnico_de_EnfermagemDAO();
					Tecnico_de_Enfermagem enf = new Tecnico_de_Enfermagem(fieldCoren.getText(), fieldNome.getText(), Data.toDateUsFormat(fieldDataInscricao.getText()));
					
					if (new EnfermariaDAO().exist(enf)) {throw new Exception("Técnico(a) de Enfermagem já existe!");}
					
					if (dao.create(enf) == 1) {
						JOptionPane.showMessageDialog(null, "Técnico(a) de Enfermagem Cadastrado(a) com Sucesso!");
						
						fieldCoren.setText(null);
						fieldNome.setText(null);
						fieldDataInscricao.setText(null);
						
						SwitchScreen(ScreenListTecnicoDeEnfermagem());
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro do(a) Técnico(a) de Enfermagem!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(130, 590, 150, 40);
		Screen.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCoren.setText(null);
				fieldNome.setText(null);
				fieldDataInscricao.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListTecnicoDeEnfermagem());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DE EDIÇÃO DO TECNICO DE ENFERMAGEM ======================== //
	private JPanel ScreenEditTecnicoDeEnfermagem(Tecnico_de_Enfermagem tecnicoSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Editando o Técnico "+ tecnicoSelected.getNome()+":");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldCoren = new JTextField();
		fieldCoren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCoren.setText(Conselho.conselhoFormat(fieldCoren.getText()));
			}
		});
		
		fieldCoren.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldCoren.setBounds(130, 180, 540, 30);
		Screen.add(fieldCoren);
		fieldCoren.setColumns(10);
		fieldCoren.setText(tecnicoSelected.getCoren());
		
		JLabel labelCoren = new JLabel("COREN");
		labelCoren.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelCoren.setForeground(new Color(245, 245, 245));
		labelCoren.setBounds(130, 150, 300, 30);
		Screen.add(labelCoren);
		
		JTextField fieldNome = new JTextField();
		fieldNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldNome.setText(Text.toName(fieldNome.getText()));
			}
		});
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldNome.setColumns(10);
		fieldNome.setBounds(130, 280, 540, 30);
		Screen.add(fieldNome);
		fieldNome.setText(tecnicoSelected.getNome());
		
		JLabel labelNome = new JLabel("Nome Completo");
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNome.setForeground(new Color(245, 245, 245));
		labelNome.setBounds(130, 250, 300, 30);
		Screen.add(labelNome);
		
		JTextField fieldDataInscricao = new JTextField();
		fieldDataInscricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataInscricao.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data de Inscrição inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataInscricao.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataInscricao.setColumns(10);
		fieldDataInscricao.setBounds(130, 380, 540, 30);
		Screen.add(fieldDataInscricao);
		fieldDataInscricao.setText(Data.toDateBrFormat(tecnicoSelected.getDataInscricao()));
		
		JLabel labelDataInscricao = new JLabel("Data de Inscrição (dd/mm/aaaa)");
		labelDataInscricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataInscricao.setForeground(new Color(245, 245, 245));
		labelDataInscricao.setBounds(130, 350, 350, 30);
		Screen.add(labelDataInscricao);
		
		
		JButton btnCadastrar = new JButton("Editar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldCoren.getText().isEmpty()) {throw new Exception("Campo COREN vazio!");	} 
					if (fieldNome.getText().isEmpty()) {throw new Exception("Campo do Nome vazio!");}
					if (fieldDataInscricao.getText().isEmpty()) {throw new Exception("Campo da Data de Inscrição vazio!");}
					
					
					fieldNome.setText(Text.toName(fieldNome.getText()));
					fieldCoren.setText(Conselho.conselhoFormat(fieldCoren.getText()));
					fieldDataInscricao.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataInscricao.getText())));               
					
					if(!Conselho.conselhoIsValid(fieldCoren.getText())) {throw new Exception("COREN inválido!");}
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataInscricao.getText()))) {throw new Exception("Data de Inscrição inválida!");}
					
					
					Tecnico_de_EnfermagemDAO dao = new Tecnico_de_EnfermagemDAO();
					Tecnico_de_Enfermagem enf = new Tecnico_de_Enfermagem(fieldCoren.getText(), fieldNome.getText(), Data.toDateUsFormat(fieldDataInscricao.getText()));
					
					if (!new EnfermariaDAO().exist(enf)) {throw new Exception("Técnico(a) de Enfermagem não existe!");}
					
					if (dao.update(enf) == 1 || enf == tecnicoSelected) {
						JOptionPane.showMessageDialog(null, "Técnico(a) de Enfermagem Editado(a) com Sucesso!");
						
						fieldCoren.setText(null);
						fieldNome.setText(null);
						fieldDataInscricao.setText(null);
						
						SwitchScreen(ScreenListTecnicoDeEnfermagem());
						
					} else {
						throw new Exception("Erro ao Realizar a Edição do(a) Técnico(a) de Enfermagem!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(130, 590, 150, 40);
		Screen.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldCoren.setText(null);
				fieldNome.setText(null);
				fieldDataInscricao.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListTecnicoDeEnfermagem());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DA LISTA DE CONSULTAS ======================== //
	private JPanel ScreenListConsulta() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Lista de Consultas:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1000, 430);
		Screen.add(scrollPane);
		
		JTable tableConsultas = new JTable();
		tableConsultas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableConsultas);
		tableConsultas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Paciente", "Médico", "Data da Consulta", "Horario", "Valor"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConsultas.setRowHeight(30);
		tableConsultas.getTableHeader().setBackground(new Color(56, 56, 56));
		tableConsultas.getTableHeader().setForeground(new Color(255, 255, 255));
		tableConsultas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tableConsultas.getColumnModel().getColumn(0).setPreferredWidth(230);
		tableConsultas.getColumnModel().getColumn(1).setPreferredWidth(230);
		
		DefaultTableModel model = (DefaultTableModel) tableConsultas.getModel();
		idConsultas = new ArrayList<>();
		for (Consulta c : new ConsultaDAO().getAll()) {
			model.addRow(new Object[] {new PacienteDAO().get(c.getId_paciente()).getPaciente(), new MedicoDAO().get(c.getId_medico()).getMedico(), Data.toDateBrFormat(c.getDataConsulta()), c.getHorario(), Text.toMoney(c.getValor())});
			idConsultas.add(c.getId());
		}
		
		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (model.getRowCount()>0) {
					model.setRowCount(0);
				}
				
				idConsultas = new ArrayList<>();
				for (Consulta c : new ConsultaDAO().search(pesquisar.getText())) {
					model.addRow(new Object[] {new PacienteDAO().get(c.getId_paciente()).getPaciente(), new MedicoDAO().get(c.getId_medico()).getMedico(), Data.toDateBrFormat(c.getDataConsulta()), c.getHorario(), Text.toMoney(c.getValor())});
					idConsultas.add(c.getId());
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenCreateConsulta());
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionar.setBackground(new Color(227, 227, 227));
		btnAdicionar.setBounds(1130, 250, 260, 62);
		Screen.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableConsultas.getSelectedRow() == -1) {throw new Exception("Nenhuma Consulta foi selecionada!");}
					SwitchScreen(ScreenEditConsulta(new ConsultaDAO().get(idConsultas.get(tableConsultas.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEditar.setBackground(new Color(227, 227, 227));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(1130, 312, 260, 62);
		Screen.add(btnEditar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConsultaDAO dao = new ConsultaDAO();
					if(tableConsultas.getSelectedRow() == -1) {throw new Exception("Nenhuma Consulta foi selecionada!");}
					if(JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar esta Consulta?") == 0) {
						if (dao.delete(dao.get(idConsultas.get(tableConsultas.getSelectedRow())))==1) {
							JOptionPane.showMessageDialog(null, "Consulta deletada com Sucesso!");
							
							while (model.getRowCount()>0) {
								model.setRowCount(0);
							}
							
							idConsultas = new ArrayList<>();
							for (Consulta c : new ConsultaDAO().search(pesquisar.getText())) {
								model.addRow(new Object[] {new PacienteDAO().get(c.getId_paciente()).getPaciente(), new MedicoDAO().get(c.getId_medico()).getMedico(), Data.toDateBrFormat(c.getDataConsulta()), c.getHorario(), Text.toMoney(c.getValor())});
								idConsultas.add(c.getId());
							}
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao deletar o(a) Paciente!");
						}
					}
					
					
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnDeletar.setBackground(new Color(227, 227, 227));
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeletar.setBounds(1130, 374, 260, 62);
		Screen.add(btnDeletar);
		
		JButton btnMaisInfo = new JButton("Mais Info");
		btnMaisInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableConsultas.getSelectedRow() == -1) {throw new Exception("Nenhuma Consulta foi selecionada!");}
					MoreInfo tela = new MoreInfo(new ConsultaDAO().get(idConsultas.get(tableConsultas.getSelectedRow())));
					tela.setVisible(true);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnMaisInfo.setBackground(new Color(227, 227, 227));
		btnMaisInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMaisInfo.setBounds(1130, 436, 260, 61);
		Screen.add(btnMaisInfo);
		
		JButton btnAdicionarReceita = new JButton("Adicionar Receita");
		btnAdicionarReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReceitaDAO daoReceita = new ReceitaDAO();
					if(tableConsultas.getSelectedRow() == -1) {throw new Exception("Nenhuma Consulta foi selecionada!");}
					if(daoReceita.have(new ConsultaDAO().get(idConsultas.get(tableConsultas.getSelectedRow())))) {throw new Exception("Esta Consulta já possui uma Receita!");}
					SwitchScreen(ScreenCreateReceita(new ConsultaDAO().get(idConsultas.get(tableConsultas.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAdicionarReceita.setBackground(new Color(227, 227, 227));
		btnAdicionarReceita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionarReceita.setBounds(1130, 497, 260, 61);
		Screen.add(btnAdicionarReceita);
		
		JButton btnEditarReceita = new JButton("Editar Receita");
		btnEditarReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableConsultas.getSelectedRow() == -1) {throw new Exception("Nenhuma Consulta foi selecionada!");}
					if(! new ReceitaDAO().have(new ConsultaDAO().get(idConsultas.get(tableConsultas.getSelectedRow())))) {throw new Exception("Esta Consulta não possui uma Receita!");}
					SwitchScreen(ScreenEditReceita(new ConsultaDAO().get(idConsultas.get(tableConsultas.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEditarReceita.setBackground(new Color(227, 227, 227));
		btnEditarReceita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditarReceita.setBounds(1130, 558, 260, 61);
		Screen.add(btnEditarReceita);
		
		JButton btnDelReceita = new JButton("Deletar Receita");
		btnDelReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReceitaDAO daoReceita = new ReceitaDAO();
					if(tableConsultas.getSelectedRow() == -1) {throw new Exception("Nenhuma Consulta foi selecionada!");}
					if(!daoReceita.have(new ConsultaDAO().get(idConsultas.get(tableConsultas.getSelectedRow())))) {throw new Exception("Esta Consulta não possui uma Receita!");}
					if (JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar a Receita desta Consulta?") == 0) {
						if (daoReceita.delete(daoReceita.getOfConsulta(idConsultas.get(tableConsultas.getSelectedRow())))==1) {
							JOptionPane.showMessageDialog(null, "Receita deletada com Sucesso!");
							
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao deletar a Receita!");
						}
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnDelReceita.setBackground(new Color(227, 227, 227));
		btnDelReceita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelReceita.setBounds(1130, 619, 260, 61);
		Screen.add(btnDelReceita);
	
		return Screen;
	}

	// ======================== TELA DE CADASTRO DE CONSULTA ======================== //
	private JPanel ScreenCreateConsulta() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1529, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro de Consulta:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, -23, 1539, 858);
		Screen.add(layeredPane);
		
		JPanel panelPaciente = new JPanel();
		panelPaciente.setBounds(0, 108, 1529, 632);
		panelPaciente.setLayout(null);
		panelPaciente.setOpaque(false);
		layeredPane.add(panelPaciente);
		
		JPanel panelMedico = new JPanel();
		panelMedico.setBounds(0, 108, 1529, 632);
		panelMedico.setLayout(null);
		panelMedico.setOpaque(false);
		
		JPanel panelEnfer = new JPanel();
		panelEnfer.setBounds(0, 108, 1529, 632);
		panelEnfer.setLayout(null);
		panelEnfer.setOpaque(false);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBounds(0, 108, 1529, 632);
		panelCadastro.setLayout(null);
		panelCadastro.setOpaque(false);
		
		
		
		//----------------------------//Selecionar Paciente//----------------------------//
		
		
		
		
		JLabel labelPaciente = new JLabel("Selecione o(a) Paciente");
		labelPaciente.setForeground(new Color(245, 245, 245));
		labelPaciente.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelPaciente.setBounds(130, 40, 300, 30);
		panelPaciente.add(labelPaciente);
		
		JScrollPane scrollPanePac = new JScrollPane();
		scrollPanePac.setBounds(130, 180, 1260, 318);
		panelPaciente.add(scrollPanePac);
		
		JTable tablePacientes = new JTable();
		tablePacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPanePac.setViewportView(tablePacientes);
		tablePacientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF", "Nome", "Data de Nascimento", "Convenio",
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePacientes.setRowHeight(30);
		tablePacientes.getTableHeader().setBackground(new Color(56, 56, 56));
		tablePacientes.getTableHeader().setForeground(new Color(255, 255, 255));
		tablePacientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		DefaultTableModel modelPac = (DefaultTableModel) tablePacientes.getModel();
		idPacientes = new ArrayList<>();
		for (Paciente p:new PacienteDAO().getAll()) {
			modelPac.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio()});
			idPacientes.add(p.getCPF());
		}
		
		JLabel labelPesquisarPac = new JLabel("Pesquisar");
		labelPesquisarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarPac.setForeground(new Color(245, 245, 245));
		labelPesquisarPac.setBounds(130, 80, 300, 30);
		panelPaciente.add(labelPesquisarPac);
		
		JTextField pesquisarPac = new JTextField();
		pesquisarPac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelPac.getRowCount()>0) {
					modelPac.setRowCount(0);
				}
				
				idPacientes = new ArrayList<>();
				for (Paciente p:new PacienteDAO().search(pesquisarPac.getText())) {
					modelPac.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio() });
					idPacientes.add(p.getCPF());
				}
				
			}
		});
		pesquisarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisarPac.setBounds(130, 110, 1260, 30);
		panelPaciente.add(pesquisarPac);
		pesquisarPac.setColumns(10);
		
		JButton btnProximoPac = new JButton("Próximo");
		btnProximoPac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tablePacientes.getSelectedRow() == -1) {throw new Exception("Selecione um Paciente Primeiro!");}
					SwitchScreen(panelMedico, layeredPane);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnProximoPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoPac.setBackground(new Color(227, 227, 227));
		btnProximoPac.setBounds(130, 540, 139, 46);
		panelPaciente.add(btnProximoPac);
		
		JButton btnCancelarPac = new JButton("Cancelar");
		btnCancelarPac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarPac.setBackground(new Color(227,227,227));
		btnCancelarPac.setBounds(306, 540, 139, 46);
		panelPaciente.add(btnCancelarPac);
		
		//----------------------------//Selecionar Medico//----------------------------//
		
		JLabel labelMedico = new JLabel("Selecione o(a) Médico(a)");
		labelMedico.setForeground(new Color(245, 245, 245));
		labelMedico.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelMedico.setBounds(130, 40, 319, 30);
		panelMedico.add(labelMedico);
		
		JScrollPane scrollPaneMed = new JScrollPane();
		scrollPaneMed.setBounds(130, 180, 1260, 318);
		panelMedico.add(scrollPaneMed);
		
		JTable tableMedicos = new JTable();
		tableMedicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneMed.setViewportView(tableMedicos);
		tableMedicos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CRM", "Nome", "Especializa\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableMedicos.setRowHeight(30);
		tableMedicos.getTableHeader().setBackground(new Color(56, 56, 56));
		tableMedicos.getTableHeader().setForeground(new Color(255, 255, 255));
		tableMedicos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelMed = (DefaultTableModel) tableMedicos.getModel();
		idMedicos = new ArrayList<>();
		for (Medico m:new MedicoDAO().getAll()) {
			modelMed.addRow(new Object[] {m.getCRM(), m.getNome(), m.getEspecializacao()});
			idMedicos.add(m.getCRM());
		}
		
		JLabel labelPesquisarMed = new JLabel("Pesquisar");
		labelPesquisarMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarMed.setForeground(new Color(245, 245, 245));
		labelPesquisarMed.setBounds(130, 80, 300, 30);
		panelMedico.add(labelPesquisarMed);
		
		JTextField pesquisarMed = new JTextField();
		pesquisarMed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelMed.getRowCount()>0) {
					modelMed.setRowCount(0);
				}
				
				idMedicos = new ArrayList<>();
				for (Medico m:new MedicoDAO().search(pesquisarMed.getText())) {
					modelMed.addRow(new Object[] {m.getCRM(), m.getNome(), m.getEspecializacao()});
					idMedicos.add(m.getCRM());
				}
				
			}
		});
		pesquisarMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisarMed.setBounds(130, 110, 1260, 30);
		panelMedico.add(pesquisarMed);
		pesquisarMed.setColumns(10);
		
		JButton btnProximoMed = new JButton("Próximo");
		btnProximoMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableMedicos.getSelectedRow() == -1) {throw new Exception("Selecione um Médico Primeiro!");}
					SwitchScreen(panelEnfer, layeredPane);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnProximoMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoMed.setBackground(new Color(227, 227, 227));
		btnProximoMed.setBounds(130, 540, 139, 46);
		panelMedico.add(btnProximoMed);

		JButton btnCancelarMed = new JButton("Cancelar");
		btnCancelarMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarMed.setBackground(new Color(227,227,227));
		btnCancelarMed.setBounds(310, 540, 139, 46);
		panelMedico.add(btnCancelarMed);
		
		//----------------------------//Selecionar Enfermeiros//----------------------------//
		
		idEnfermeirosSelected = new ArrayList<>();
		
		JLabel labelEnfermeiros = new JLabel("Selecione os(as) Enfermeiros(as)");
		labelEnfermeiros.setForeground(new Color(245, 245, 245));
		labelEnfermeiros.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelEnfermeiros.setBounds(130, 40, 441, 30);
		panelEnfer.add(labelEnfermeiros);
		
		JScrollPane scrollPaneEnf = new JScrollPane();
		scrollPaneEnf.setBounds(130, 180, 687, 270);
		panelEnfer.add(scrollPaneEnf);
		
		JTable tableEnfermeiros = new JTable();
		tableEnfermeiros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneEnf.setViewportView(tableEnfermeiros);
		tableEnfermeiros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COREN", "Nome",  "Especializa\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableEnfermeiros.setRowHeight(30);
		tableEnfermeiros.getTableHeader().setBackground(new Color(56, 56, 56));
		tableEnfermeiros.getTableHeader().setForeground(new Color(255, 255, 255));
		tableEnfermeiros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelEnf = (DefaultTableModel) tableEnfermeiros.getModel();
		idEnfermeiros = new ArrayList<>();
		for (Enfermeiro enf:new EnfermeiroDAO().getAll()) {
			modelEnf.addRow(new Object[] {enf.getCoren(), enf.getNome(), enf.getEspecializacao()});
			idEnfermeiros.add(enf.getCoren());
		}
		
		JLabel labelPesquisarEnf = new JLabel("Pesquisar");
		labelPesquisarEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarEnf.setForeground(new Color(245, 245, 245));
		labelPesquisarEnf.setBounds(130, 80, 300, 30);
		panelEnfer.add(labelPesquisarEnf);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelEnf.getRowCount()>0) {
					modelEnf.setRowCount(0);
				}
				
				idEnfermeiros = new ArrayList<>();
				for (Enfermeiro enf:new EnfermeiroDAO().search(pesquisar.getText())) {
					modelEnf.addRow(new Object[] {enf.getCoren(), enf.getNome(), enf.getEspecializacao()});
					idEnfermeiros.add(enf.getCoren());
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 110, 687, 30);;
		panelEnfer.add(pesquisar);
		pesquisar.setColumns(10);
		
		JScrollPane scrollPaneEnfSelected = new JScrollPane();
		scrollPaneEnfSelected.setBounds(920, 110, 492, 340);
		panelEnfer.add(scrollPaneEnfSelected);
		
		JTable tableEnfermeirosSelected = new JTable();
		tableEnfermeirosSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneEnfSelected.setViewportView(tableEnfermeirosSelected);
		tableEnfermeirosSelected.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COREN", "Nome"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableEnfermeirosSelected.setRowHeight(30);
		
		JLabel labelEnfermeirosSelecionados = new JLabel("Enfermeiros(as) Selecionados");
		labelEnfermeirosSelecionados.setForeground(new Color(245, 245, 245));
		labelEnfermeirosSelecionados.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelEnfermeirosSelecionados.setBounds(920, 40, 441, 30);
		panelEnfer.add(labelEnfermeirosSelecionados);
		
		JButton btnAdicionarEnf = new JButton("Adicionar");
		btnAdicionarEnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableEnfermeiros.getSelectedRow() == -1) {throw new Exception("Nenhum Enfermeiro foi Selecionado!");}
					
					DefaultTableModel modelEnf = (DefaultTableModel) tableEnfermeirosSelected.getModel();
					Enfermeiro enf = new EnfermeiroDAO().get(idEnfermeiros.get(tableEnfermeiros.getSelectedRow()));
					
					if(idEnfermeirosSelected.contains(enf.getCoren())) {throw new Exception("O Enfermeiro já foi Selecionado!");}
					
					modelEnf.addRow(new Object[] {enf.getCoren(), enf.getNome()});
					idEnfermeirosSelected.add(enf.getCoren());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAdicionarEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionarEnf.setBackground(new Color(227, 227, 227));
		btnAdicionarEnf.setForeground(new Color(0, 0, 0));
		btnAdicionarEnf.setBounds(130, 450, 687, 47);
		panelEnfer.add(btnAdicionarEnf);
		
		JButton btnRetirarEnf = new JButton("Retirar");
		btnRetirarEnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableEnfermeirosSelected.getSelectedRow() == -1) {throw new Exception("Nenhum Enfermeiro foi Selecionado!");}
					idEnfermeirosSelected.remove(idEnfermeiros.get(tableEnfermeirosSelected.getSelectedRow()));
					DefaultTableModel modelEnf = (DefaultTableModel) tableEnfermeirosSelected.getModel();
					modelEnf.removeRow(tableEnfermeirosSelected.getSelectedRow());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnRetirarEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRetirarEnf.setBackground(new Color(227, 227, 227));
		btnRetirarEnf.setBounds(920, 450, 492, 47);
		panelEnfer.add(btnRetirarEnf);
		tableEnfermeirosSelected.getTableHeader().setBackground(new Color(56, 56, 56));
		tableEnfermeirosSelected.getTableHeader().setForeground(new Color(255, 255, 255));
		tableEnfermeirosSelected.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JButton btnProximoEnf = new JButton("Próximo");
		btnProximoEnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(panelCadastro, layeredPane);
			}
		});
		btnProximoEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoEnf.setBackground(new Color(227, 227, 227));
		btnProximoEnf.setBounds(130, 540, 139, 46);
		panelEnfer.add(btnProximoEnf);

		JButton btnCancelarEnf = new JButton("Cancelar");
		btnCancelarEnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarEnf.setBackground(new Color(227,227,227));
		btnCancelarEnf.setBounds(310, 540, 139, 46);
		panelEnfer.add(btnCancelarEnf);
		
		//----------------------------//Cadastro da Consulta//----------------------------//
		
		JTextField fieldHorario = new JTextField();
		fieldHorario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldHorario.setColumns(10);
		fieldHorario.setBounds(130, 280, 500, 30);
		panelCadastro.add(fieldHorario);
		
		JLabel labelHorario = new JLabel("Horario(hh:mm:ss)");
		labelHorario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelHorario.setForeground(new Color(245, 245, 245));
		labelHorario.setBounds(130, 250, 300, 30);
		panelCadastro.add(labelHorario);
		
		JTextField fieldDataConsulta = new JTextField();
		fieldDataConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataConsulta.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data da Consulta inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataConsulta.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataConsulta.setColumns(10);
		fieldDataConsulta.setBounds(130, 180, 500, 30);
		panelCadastro.add(fieldDataConsulta);
		
		JLabel labelDataConsulta = new JLabel("Data da Consulta(dd/mm/aaaa)");
		labelDataConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataConsulta.setForeground(new Color(245, 245, 245));
		labelDataConsulta.setBounds(130, 150, 350, 30);
		panelCadastro.add(labelDataConsulta);
		
		JTextField fieldValor = new JTextField();
		fieldValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldValor.setText(Text.toMoney(fieldValor.getText()));
			}
		});
		fieldValor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldValor.setColumns(10);
		fieldValor.setBounds(130, 380, 500, 30);
		panelCadastro.add(fieldValor);
		
		JLabel labelValor = new JLabel("Valor");
		labelValor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelValor.setForeground(new Color(245, 245, 245));
		labelValor.setBounds(130, 350, 350, 30);
		panelCadastro.add(labelValor);
		
		JTextPane fieldDiagnostico = new JTextPane();
		fieldDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldDiagnostico.setBounds(830, 180, 540, 300);
		panelCadastro.add(fieldDiagnostico);
		
		JLabel labelDiagnostico = new JLabel("Diagnostico");
		labelDiagnostico.setForeground(new Color(245, 245, 245));
		labelDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDiagnostico.setBounds(830, 150, 350, 30);
		panelCadastro.add(labelDiagnostico);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldDiagnostico.getText().isEmpty()) {throw new Exception("Campo do Diagnostico vazio!");	} 
					if (fieldHorario.getText().isEmpty()) {throw new Exception("Campo do Horario vazio!");}
					if (fieldDataConsulta.getText().isEmpty()) {throw new Exception("Campo da Data da Consulta vazio!");}
					if (fieldValor.getText().isEmpty()) {throw new Exception("Campo Valor vazio!");}
					
					

					fieldDataConsulta.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataConsulta.getText())));
					fieldValor.setText(Text.toMoney(fieldValor.getText()));               
					
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataConsulta.getText()))) {throw new Exception("Data de Inscrição inválida!");}
					if(!Data.timeIsValid(fieldHorario.getText())) {throw new Exception("Horario inválido!");}
					
					ConsultaDAO dao = new ConsultaDAO();
					Consulta c = new Consulta(idMedicos.get(tableMedicos.getSelectedRow()), idPacientes.get(tablePacientes.getSelectedRow()), Text.moneyToDouble(fieldValor.getText()), Data.toDateUsFormat(fieldDataConsulta.getText()), Data.toTime(fieldHorario.getText()), fieldDiagnostico.getText());
					
					if (dao.create(c) == 1) {
						c.setId(dao.getID(c));
						for (String coren:idEnfermeirosSelected) {new AuxiliaDAO().create(new Auxilia(c.getId(), coren));}
						
						JOptionPane.showMessageDialog(null, "Consulta Cadastrada com Sucesso!");
					
						fieldHorario.setText(null);
						fieldDataConsulta.setText(null);
						fieldValor.setText(null);
						fieldDiagnostico.setText(null);
						
						SwitchScreen(ScreenListConsulta());
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro da Consulta!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(130, 590, 150, 40);
		panelCadastro.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldHorario.setText(null);
				fieldDataConsulta.setText(null);
				fieldValor.setText(null);
				fieldDiagnostico.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		panelCadastro.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		panelCadastro.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DA EDIÇÃO DE CONSULTA ======================== //
	private JPanel ScreenEditConsulta(Consulta consultaSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1529, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Edição de Consulta:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, -23, 1539, 858);
		Screen.add(layeredPane);
		
		JPanel panelPaciente = new JPanel();
		panelPaciente.setBounds(0, 108, 1529, 632);
		panelPaciente.setLayout(null);
		panelPaciente.setOpaque(false);
		layeredPane.add(panelPaciente);
		
		JPanel panelMedico = new JPanel();
		panelMedico.setBounds(0, 108, 1529, 632);
		panelMedico.setLayout(null);
		panelMedico.setOpaque(false);
		
		JPanel panelEnfer = new JPanel();
		panelEnfer.setBounds(0, 108, 1529, 632);
		panelEnfer.setLayout(null);
		panelEnfer.setOpaque(false);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBounds(0, 108, 1529, 632);
		panelCadastro.setLayout(null);
		panelCadastro.setOpaque(false);
		
		
		
		//----------------------------//Selecionar Paciente//----------------------------//
		
		
		
		
		JLabel labelPaciente = new JLabel("Selecione o(a) Paciente");
		labelPaciente.setForeground(new Color(245, 245, 245));
		labelPaciente.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelPaciente.setBounds(130, 40, 300, 30);
		panelPaciente.add(labelPaciente);
		
		JScrollPane scrollPanePac = new JScrollPane();
		scrollPanePac.setBounds(130, 180, 1260, 318);
		panelPaciente.add(scrollPanePac);
		
		JTable tablePacientes = new JTable();
		tablePacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPanePac.setViewportView(tablePacientes);
		tablePacientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF", "Nome", "Data de Nascimento", "Convenio",
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePacientes.setRowHeight(30);
		tablePacientes.getTableHeader().setBackground(new Color(56, 56, 56));
		tablePacientes.getTableHeader().setForeground(new Color(255, 255, 255));
		tablePacientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		DefaultTableModel modelPac = (DefaultTableModel) tablePacientes.getModel();
		idPacientes = new ArrayList<>();
		for (Paciente p:new PacienteDAO().getAll()) {
			modelPac.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio()});
			idPacientes.add(p.getCPF());
			if (p.getCPF().equals(consultaSelected.getId_paciente())) {tablePacientes.setRowSelectionInterval(modelPac.getRowCount()-1, modelPac.getRowCount()-1);}
		}
		
		JLabel labelPesquisarPac = new JLabel("Pesquisar");
		labelPesquisarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarPac.setForeground(new Color(245, 245, 245));
		labelPesquisarPac.setBounds(130, 80, 300, 30);
		panelPaciente.add(labelPesquisarPac);
		
		JTextField pesquisarPac = new JTextField();
		pesquisarPac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelPac.getRowCount()>0) {
					modelPac.setRowCount(0);
				}
				
				idPacientes = new ArrayList<>();
				for (Paciente p:new PacienteDAO().search(pesquisarPac.getText())) {
					modelPac.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio() });
					idPacientes.add(p.getCPF());
				}
				
			}
		});
		pesquisarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisarPac.setBounds(130, 110, 1260, 30);
		panelPaciente.add(pesquisarPac);
		pesquisarPac.setColumns(10);
		
		JButton btnProximoPac = new JButton("Próximo");
		btnProximoPac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tablePacientes.getSelectedRow() == -1) {throw new Exception("Selecione um Paciente Primeiro!");}
					SwitchScreen(panelMedico, layeredPane);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnProximoPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoPac.setBackground(new Color(227, 227, 227));
		btnProximoPac.setBounds(130, 540, 139, 46);
		panelPaciente.add(btnProximoPac);
		
		JButton btnCancelarPac = new JButton("Cancelar");
		btnCancelarPac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarPac.setBackground(new Color(227,227,227));
		btnCancelarPac.setBounds(306, 540, 139, 46);
		panelPaciente.add(btnCancelarPac);
		
		//----------------------------//Selecionar Medico//----------------------------//
		
		JLabel labelMedico = new JLabel("Selecione o(a) Médico(a)");
		labelMedico.setForeground(new Color(245, 245, 245));
		labelMedico.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelMedico.setBounds(130, 40, 319, 30);
		panelMedico.add(labelMedico);
		
		JScrollPane scrollPaneMed = new JScrollPane();
		scrollPaneMed.setBounds(130, 180, 1260, 318);
		panelMedico.add(scrollPaneMed);
		
		JTable tableMedicos = new JTable();
		tableMedicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneMed.setViewportView(tableMedicos);
		tableMedicos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CRM", "Nome", "Especializa\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableMedicos.setRowHeight(30);
		tableMedicos.getTableHeader().setBackground(new Color(56, 56, 56));
		tableMedicos.getTableHeader().setForeground(new Color(255, 255, 255));
		tableMedicos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelMed = (DefaultTableModel) tableMedicos.getModel();
		idMedicos = new ArrayList<>();
		for (Medico m:new MedicoDAO().getAll()) {
			modelMed.addRow(new Object[] {m.getCRM(), m.getNome(), m.getEspecializacao()});
			idMedicos.add(m.getCRM());
			if (m.getCRM().equals(consultaSelected.getId_medico())) {tableMedicos.setRowSelectionInterval(modelMed.getRowCount()-1, modelMed.getRowCount()-1);}
		}
		
		JLabel labelPesquisarMed = new JLabel("Pesquisar");
		labelPesquisarMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarMed.setForeground(new Color(245, 245, 245));
		labelPesquisarMed.setBounds(130, 80, 300, 30);
		panelMedico.add(labelPesquisarMed);
		
		JTextField pesquisarMed = new JTextField();
		pesquisarMed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelMed.getRowCount()>0) {
					modelMed.setRowCount(0);
				}
				
				idMedicos = new ArrayList<>();
				for (Medico m:new MedicoDAO().search(pesquisarMed.getText())) {
					modelMed.addRow(new Object[] {m.getCRM(), m.getNome(), m.getEspecializacao()});
					idMedicos.add(m.getCRM());
				}
				
			}
		});
		pesquisarMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisarMed.setBounds(130, 110, 1260, 30);
		panelMedico.add(pesquisarMed);
		pesquisarMed.setColumns(10);
		
		JButton btnProximoMed = new JButton("Próximo");
		btnProximoMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableMedicos.getSelectedRow() == -1) {throw new Exception("Selecione um Médico Primeiro!");}
					SwitchScreen(panelEnfer, layeredPane);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnProximoMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoMed.setBackground(new Color(227, 227, 227));
		btnProximoMed.setBounds(130, 540, 139, 46);
		panelMedico.add(btnProximoMed);

		JButton btnCancelarMed = new JButton("Cancelar");
		btnCancelarMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarMed.setBackground(new Color(227,227,227));
		btnCancelarMed.setBounds(310, 540, 139, 46);
		panelMedico.add(btnCancelarMed);
		
		//----------------------------//Selecionar Enfermeiros//----------------------------//
		
		idEnfermeirosSelected = new ArrayList<>();
		
		JLabel labelEnfermeiros = new JLabel("Selecione os(as) Enfermeiros(as)");
		labelEnfermeiros.setForeground(new Color(245, 245, 245));
		labelEnfermeiros.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelEnfermeiros.setBounds(130, 40, 441, 30);
		panelEnfer.add(labelEnfermeiros);
		
		JScrollPane scrollPaneEnf = new JScrollPane();
		scrollPaneEnf.setBounds(130, 180, 687, 270);
		panelEnfer.add(scrollPaneEnf);
		
		JTable tableEnfermeiros = new JTable();
		tableEnfermeiros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneEnf.setViewportView(tableEnfermeiros);
		tableEnfermeiros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COREN", "Nome",  "Especializa\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableEnfermeiros.setRowHeight(30);
		tableEnfermeiros.getTableHeader().setBackground(new Color(56, 56, 56));
		tableEnfermeiros.getTableHeader().setForeground(new Color(255, 255, 255));
		tableEnfermeiros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelEnf = (DefaultTableModel) tableEnfermeiros.getModel();
		idEnfermeiros = new ArrayList<>();
		for (Enfermeiro enf:new EnfermeiroDAO().getAll()) {
			modelEnf.addRow(new Object[] {enf.getCoren(), enf.getNome(), enf.getEspecializacao()});
			idEnfermeiros.add(enf.getCoren());
		}
		
		JLabel labelPesquisarEnf = new JLabel("Pesquisar");
		labelPesquisarEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarEnf.setForeground(new Color(245, 245, 245));
		labelPesquisarEnf.setBounds(130, 80, 300, 30);
		panelEnfer.add(labelPesquisarEnf);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelEnf.getRowCount()>0) {
					modelEnf.setRowCount(0);
				}
				
				idEnfermeiros = new ArrayList<>();
				for (Enfermeiro enf:new EnfermeiroDAO().search(pesquisar.getText())) {
					modelEnf.addRow(new Object[] {enf.getCoren(), enf.getNome(), enf.getEspecializacao()});
					idEnfermeiros.add(enf.getCoren());
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 110, 687, 30);;
		panelEnfer.add(pesquisar);
		pesquisar.setColumns(10);
		
		JScrollPane scrollPaneEnfSelected = new JScrollPane();
		scrollPaneEnfSelected.setBounds(920, 110, 492, 340);
		panelEnfer.add(scrollPaneEnfSelected);
		
		JTable tableEnfermeirosSelected = new JTable();
		tableEnfermeirosSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneEnfSelected.setViewportView(tableEnfermeirosSelected);
		tableEnfermeirosSelected.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COREN", "Nome"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableEnfermeirosSelected.setRowHeight(30);
		
		JLabel labelEnfermeirosSelecionados = new JLabel("Enfermeiros(as) Selecionados");
		labelEnfermeirosSelecionados.setForeground(new Color(245, 245, 245));
		labelEnfermeirosSelecionados.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelEnfermeirosSelecionados.setBounds(920, 40, 441, 30);
		panelEnfer.add(labelEnfermeirosSelecionados);
		
		DefaultTableModel modelEnfSelected = (DefaultTableModel) tableEnfermeirosSelected.getModel();
		idEnfermeirosSelected = new ArrayList<>();
		for (Auxilia a:new AuxiliaDAO().getConsultaList(consultaSelected.getId())) {
			Enfermeiro enf = new EnfermeiroDAO().get(a.getId_enfer());
			modelEnfSelected.addRow(new Object[] {enf.getCoren(), enf.getNome()});
			idEnfermeirosSelected.add(enf.getCoren());
		}
		
		
		JButton btnAdicionarEnf = new JButton("Adicionar");
		btnAdicionarEnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableEnfermeiros.getSelectedRow() == -1) {throw new Exception("Nenhum Enfermeiro foi Selecionado!");}
					
					DefaultTableModel modelEnf = (DefaultTableModel) tableEnfermeirosSelected.getModel();
					Enfermeiro enf = new EnfermeiroDAO().get(idEnfermeiros.get(tableEnfermeiros.getSelectedRow()));
					
					if(idEnfermeirosSelected.contains(enf.getCoren())) {throw new Exception("O Enfermeiro já foi Selecionado!");}
					
					modelEnf.addRow(new Object[] {enf.getCoren(), enf.getNome()});
					idEnfermeirosSelected.add(enf.getCoren());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAdicionarEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionarEnf.setBackground(new Color(227, 227, 227));
		btnAdicionarEnf.setForeground(new Color(0, 0, 0));
		btnAdicionarEnf.setBounds(130, 450, 687, 47);
		panelEnfer.add(btnAdicionarEnf);
		
		JButton btnRetirarEnf = new JButton("Retirar");
		btnRetirarEnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableEnfermeirosSelected.getSelectedRow() == -1) {throw new Exception("Nenhum Enfermeiro foi Selecionado!");}
					idEnfermeirosSelected.remove(tableEnfermeirosSelected.getSelectedRow());
					DefaultTableModel modelEnf = (DefaultTableModel) tableEnfermeirosSelected.getModel();
					modelEnf.removeRow(tableEnfermeirosSelected.getSelectedRow());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnRetirarEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRetirarEnf.setBackground(new Color(227, 227, 227));
		btnRetirarEnf.setBounds(920, 450, 492, 47);
		panelEnfer.add(btnRetirarEnf);
		tableEnfermeirosSelected.getTableHeader().setBackground(new Color(56, 56, 56));
		tableEnfermeirosSelected.getTableHeader().setForeground(new Color(255, 255, 255));
		tableEnfermeirosSelected.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JButton btnProximoEnf = new JButton("Próximo");
		btnProximoEnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(panelCadastro, layeredPane);
			}
		});
		btnProximoEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoEnf.setBackground(new Color(227, 227, 227));
		btnProximoEnf.setBounds(130, 540, 139, 46);
		panelEnfer.add(btnProximoEnf);

		JButton btnCancelarEnf = new JButton("Cancelar");
		btnCancelarEnf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarEnf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarEnf.setBackground(new Color(227,227,227));
		btnCancelarEnf.setBounds(310, 540, 139, 46);
		panelEnfer.add(btnCancelarEnf);
		
		//----------------------------//Cadastro da Consulta//----------------------------//
		
		JTextField fieldHorario = new JTextField();
		fieldHorario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldHorario.setColumns(10);
		fieldHorario.setBounds(130, 280, 500, 30);
		panelCadastro.add(fieldHorario);
		fieldHorario.setText(consultaSelected.getHorario().toString());
		
		JLabel labelHorario = new JLabel("Horario(hh:mm:ss)");
		labelHorario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelHorario.setForeground(new Color(245, 245, 245));
		labelHorario.setBounds(130, 250, 300, 30);
		panelCadastro.add(labelHorario);
		
		JTextField fieldDataConsulta = new JTextField();
		fieldDataConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataConsulta.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data da Consulta inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataConsulta.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataConsulta.setColumns(10);
		fieldDataConsulta.setBounds(130, 180, 500, 30);
		panelCadastro.add(fieldDataConsulta);
		fieldDataConsulta.setText(Data.toDateBrFormat(consultaSelected.getDataConsulta()));
		
		JLabel labelDataConsulta = new JLabel("Data da Consulta(dd/mm/aaaa)");
		labelDataConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataConsulta.setForeground(new Color(245, 245, 245));
		labelDataConsulta.setBounds(130, 150, 350, 30);
		panelCadastro.add(labelDataConsulta);
		
		JTextField fieldValor = new JTextField();
		fieldValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldValor.setText(Text.toMoney(fieldValor.getText()));
			}
		});
		fieldValor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldValor.setColumns(10);
		fieldValor.setBounds(130, 380, 500, 30);
		panelCadastro.add(fieldValor);
		fieldValor.setText(Text.toMoney(consultaSelected.getValor()));
		
		JLabel labelValor = new JLabel("Valor");
		labelValor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelValor.setForeground(new Color(245, 245, 245));
		labelValor.setBounds(130, 350, 350, 30);
		panelCadastro.add(labelValor);
		
		JTextPane fieldDiagnostico = new JTextPane();
		fieldDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldDiagnostico.setBounds(830, 180, 540, 300);
		panelCadastro.add(fieldDiagnostico);
		fieldDiagnostico.setText(consultaSelected.getDiagnostico());
		
		JLabel labelDiagnostico = new JLabel("Diagnostico");
		labelDiagnostico.setForeground(new Color(245, 245, 245));
		labelDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDiagnostico.setBounds(830, 150, 350, 30);
		panelCadastro.add(labelDiagnostico);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldDiagnostico.getText().isEmpty()) {throw new Exception("Campo do Diagnostico vazio!");	} 
					if (fieldHorario.getText().isEmpty()) {throw new Exception("Campo do Horario vazio!");}
					if (fieldDataConsulta.getText().isEmpty()) {throw new Exception("Campo da Data da Consulta vazio!");}
					if (fieldValor.getText().isEmpty()) {throw new Exception("Campo Valor vazio!");}
					
					

					fieldDataConsulta.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataConsulta.getText())));
					fieldValor.setText(Text.toMoney(fieldValor.getText()));               
					
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataConsulta.getText()))) {throw new Exception("Data de Inscrição inválida!");}
					if(!Data.timeIsValid(fieldHorario.getText())) {throw new Exception("Horario inválido!");}
					
					ConsultaDAO dao = new ConsultaDAO();
					Consulta c = new Consulta(consultaSelected.getId(), idMedicos.get(tableMedicos.getSelectedRow()), idPacientes.get(tablePacientes.getSelectedRow()), Text.moneyToDouble(fieldValor.getText()), Data.toDateUsFormat(fieldDataConsulta.getText()), Data.toTime(fieldHorario.getText()), fieldDiagnostico.getText());
					
					
					if(!dao.exist(c)) {throw new Exception("Esta Consulta não Existe!");}
					
					boolean enferEdit = false;
					ArrayList<Auxilia> antEnfer = new AuxiliaDAO().getConsultaList(consultaSelected.getId());
					for(String coren:idEnfermeirosSelected) {
						if(!antEnfer.contains(new Auxilia(consultaSelected.getId(), coren)) || antEnfer.size() != idEnfermeiros.size()) {
							enferEdit = true;
							break;
						}
					}
					
					
					if (dao.update(c) == 1||consultaSelected == c|| enferEdit) {
						for (Auxilia a:new AuxiliaDAO().getConsultaList(consultaSelected.getId())) {new AuxiliaDAO().delete(a);}
						for (String coren:idEnfermeirosSelected) {new AuxiliaDAO().create(new Auxilia(c.getId(), coren));}
						JOptionPane.showMessageDialog(null, "Consulta Editada com Sucesso!");
					
						fieldHorario.setText(null);
						fieldDataConsulta.setText(null);
						fieldValor.setText(null);
						fieldDiagnostico.setText(null);
						
						SwitchScreen(ScreenListConsulta());
						
					} else {
						throw new Exception("Erro ao Realizar a Edição da Consulta!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnEditar.setBackground(new Color(85, 200, 100));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(130, 590, 150, 40);
		panelCadastro.add(btnEditar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldHorario.setText(null);
				fieldDataConsulta.setText(null);
				fieldValor.setText(null);
				fieldDiagnostico.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		panelCadastro.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		panelCadastro.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DE CADASTRO DE RECEITA ======================== //
	private JPanel ScreenCreateReceita(Consulta consultaSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro da Receita:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JLabel labelConsulta = new JLabel("Consulta");
		labelConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelConsulta.setForeground(new Color(245, 245, 245));
		labelConsulta.setBounds(130, 150, 300, 30);
		Screen.add(labelConsulta);
		
		JComboBox fieldConsulta = new JComboBox();
		fieldConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldConsulta.setBounds(130, 180, 550, 30);
		Screen.add(fieldConsulta);
		
		ArrayList<Integer> idConsultas = new ArrayList<>();
		for (Consulta c:new ConsultaDAO().getAll()) {
			fieldConsulta.addItem(c.getConsulta());
			idConsultas.add(c.getId());
			if (consultaSelected != null) {
				if (c.getId().equals(consultaSelected.getId())) {
					fieldConsulta.setSelectedItem(consultaSelected.getConsulta());
				}
			}
		}
		
		JLabel labelMedicamentos = new JLabel("Medicamentos");
		labelMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelMedicamentos.setForeground(new Color(245, 245, 245));
		labelMedicamentos.setBounds(130, 250, 300, 30);
		Screen.add(labelMedicamentos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 280, 411, 250);
		Screen.add(scrollPane);
		
		JTable tableMedicamentos = new JTable();
		scrollPane.setViewportView(tableMedicamentos);
		tableMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableMedicamentos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Medicamentos"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableMedicamentos.setRowHeight(30);
		tableMedicamentos.getTableHeader().setBackground(new Color(56, 56, 56));
		tableMedicamentos.getTableHeader().setForeground(new Color(255, 255, 255));
		tableMedicamentos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JLabel labelDescricao = new JLabel("Descrição e Orientações");
		labelDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDescricao.setForeground(new Color(245, 245, 245));
		labelDescricao.setBounds(820, 150, 300, 30);
		Screen.add(labelDescricao);
		
		JTextPane fieldDescricao = new JTextPane();
		fieldDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldDescricao.setBounds(820, 180, 575, 350);
		Screen.add(fieldDescricao);
		
		
		JButton btnAddMed = new JButton("Adiocionar");
		btnAddMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableMedicamentos.getModel();
					String medicamento = JOptionPane.showInputDialog("Digite o nome do medicamento:");
					
					ArrayList<String> medicamentos = new ArrayList<>();
					for (int i = 0; i<tableMedicamentos.getRowCount(); i++) {
						medicamentos.add(tableMedicamentos.getValueAt(i, 0).toString());
					}
					
					if (medicamento != null) {
						if (medicamento.isEmpty()) {throw new Exception("Medicamento inválido!");}
						if (Text.ExistIn(medicamento, medicamentos)){throw new Exception("Esta medicamento já existe na tabela!");}
						
						model.addRow(new Object[] {medicamento});
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddMed.setBackground(new Color(227, 227, 227));
		btnAddMed.setBounds(540, 280, 140, 125);
		Screen.add(btnAddMed);
		
		JButton btnDelMed = new JButton("Deletar");
		btnDelMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableMedicamentos.getSelectedRow() == -1) {throw new Exception("Nenhum Medicamento foi selecionado!");}
					DefaultTableModel model = (DefaultTableModel) tableMedicamentos.getModel();
					model.removeRow(tableMedicamentos.getSelectedRow());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnDelMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelMed.setBackground(new Color(227, 227, 227));
		btnDelMed.setBounds(540, 405, 140, 125);
		Screen.add(btnDelMed);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldDescricao.getText().isEmpty()) {throw new Exception("Campo Descrição vazio!");	} 					
					
					DefaultTableModel modelMed = (DefaultTableModel) tableMedicamentos.getModel();
					ArrayList<Medicamentos> medicamentos = new ArrayList<>();
					Medicamentos med;
					for (int i = 0; i<modelMed.getRowCount(); i++) {
						med = new Medicamentos(modelMed.getValueAt(i, 0).toString());
						medicamentos.add(med);
					}
					
					ReceitaDAO dao = new ReceitaDAO();
					Receita r = new Receita(consultaSelected.getId(), fieldDescricao.getText());
					r.setMedicamentos(medicamentos);
					
					if (dao.have(new ConsultaDAO().get(r.getId_consulta()))) {throw new Exception("A Consulta já possui uma Receita!");}
					if (dao.create(r) == 1) {
						JOptionPane.showMessageDialog(null, "Receita Cadastrada com Sucesso!");
						
						fieldDescricao.setText(null);
						while (modelMed.getRowCount()>0) {
							modelMed.setRowCount(0);
						}
						
						SwitchScreen(ScreenListConsulta());
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro da Receita!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}		
				
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setBounds(130, 615, 150, 40);
		Screen.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldDescricao.setText(null);
				DefaultTableModel modelMed = (DefaultTableModel) tableMedicamentos.getModel();
				while (modelMed.getRowCount()>0) {
					modelMed.setRowCount(0);
				}
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setBounds(310, 615, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 615, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}
	
	// ======================== TELA DE EDIÇÃO DE RECEITAS ======================== //
	private JPanel ScreenEditReceita(Consulta consultaSelected) {
		Receita receitaSelected = new ReceitaDAO().getOfConsulta(consultaSelected.getId());
		
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Editando a Receita:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JLabel labelConsulta = new JLabel("Consulta");
		labelConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelConsulta.setForeground(new Color(245, 245, 245));
		labelConsulta.setBounds(130, 150, 300, 30);
		Screen.add(labelConsulta);
		
		JComboBox fieldConsulta = new JComboBox();
		fieldConsulta.setEnabled(false);
		fieldConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldConsulta.setBounds(130, 180, 550, 30);
		Screen.add(fieldConsulta);
		
		ArrayList<Integer> idConsultas = new ArrayList<>();
		for (Consulta c:new ConsultaDAO().getAll()) {
			fieldConsulta.addItem(c.getConsulta());
			idConsultas.add(c.getId());
			if (consultaSelected != null) {
				if (c.getId().equals(consultaSelected.getId())) {
					fieldConsulta.setSelectedItem(consultaSelected.getConsulta());
				}
			}
		}
		
		JLabel labelMedicamentos = new JLabel("Medicamentos");
		labelMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelMedicamentos.setForeground(new Color(245, 245, 245));
		labelMedicamentos.setBounds(130, 250, 300, 30);
		Screen.add(labelMedicamentos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 280, 411, 250);
		Screen.add(scrollPane);
		
		JTable tableMedicamentos = new JTable();
		scrollPane.setViewportView(tableMedicamentos);
		tableMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableMedicamentos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Medicamentos"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableMedicamentos.setRowHeight(30);
		tableMedicamentos.getTableHeader().setBackground(new Color(56, 56, 56));
		tableMedicamentos.getTableHeader().setForeground(new Color(255, 255, 255));
		tableMedicamentos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelMed = (DefaultTableModel) tableMedicamentos.getModel();
		for (Medicamentos m: receitaSelected.getMedicamentos()) {
			modelMed.addRow(new Object[] {m.getMedicamento()});
		}
		
		JLabel labelDescricao = new JLabel("Descrição e Orientações");
		labelDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDescricao.setForeground(new Color(245, 245, 245));
		labelDescricao.setBounds(820, 150, 300, 30);
		Screen.add(labelDescricao);
		
		JTextPane fieldDescricao = new JTextPane();
		fieldDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldDescricao.setBounds(820, 180, 575, 350);
		Screen.add(fieldDescricao);
		fieldDescricao.setText(receitaSelected.getDescricao());
		
		
		JButton btnAddMed = new JButton("Adiocionar");
		btnAddMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableMedicamentos.getModel();
					String medicamento = JOptionPane.showInputDialog("Digite o nome do medicamento:");
					
					ArrayList<String> medicamentos = new ArrayList<>();
					for (int i = 0; i<tableMedicamentos.getRowCount(); i++) {
						medicamentos.add(tableMedicamentos.getValueAt(i, 0).toString());
					}
					
					if (medicamento != null) {
						if (medicamento.isEmpty()) {throw new Exception("Medicamento inválido!");}
						if (Text.ExistIn(medicamento, medicamentos)){throw new Exception("Esta medicamento já existe na tabela!");}
						
						model.addRow(new Object[] {medicamento});
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddMed.setBackground(new Color(227, 227, 227));
		btnAddMed.setBounds(540, 280, 140, 125);
		Screen.add(btnAddMed);
		
		JButton btnDelMed = new JButton("Deletar");
		btnDelMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableMedicamentos.getSelectedRow() == -1) {throw new Exception("Nenhum Medicamento foi selecionado!");}
					DefaultTableModel model = (DefaultTableModel) tableMedicamentos.getModel();
					model.removeRow(tableMedicamentos.getSelectedRow());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnDelMed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelMed.setBackground(new Color(227, 227, 227));
		btnDelMed.setBounds(540, 405, 140, 125);
		Screen.add(btnDelMed);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldDescricao.getText().isEmpty()) {throw new Exception("Campo Descrição vazio!");	} 					
					
					DefaultTableModel modelMed = (DefaultTableModel) tableMedicamentos.getModel();
					ArrayList<Medicamentos> medicamentos = new ArrayList<>();
					Medicamentos med;
					for (int i = 0; i<modelMed.getRowCount(); i++) {
						med = new Medicamentos(modelMed.getValueAt(i, 0).toString());
						medicamentos.add(med);
					}
					
					ReceitaDAO dao = new ReceitaDAO();
					Receita r = new Receita(receitaSelected.getId(),consultaSelected.getId(), fieldDescricao.getText());
					r.setMedicamentos(medicamentos);
					
					if (!dao.have(new ConsultaDAO().get(r.getId_consulta()))) {throw new Exception("A Consulta não possui uma Receita!");}
					if (dao.update(r) == 1 || receitaSelected == r) {
						JOptionPane.showMessageDialog(null, "Receita Editada com Sucesso!");
						
						fieldDescricao.setText(null);
						while (modelMed.getRowCount()>0) {
							modelMed.setRowCount(0);
						}
						
						SwitchScreen(ScreenListConsulta());
						
					} else {
						throw new Exception("Erro ao Realizar a Edição da Receita!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}		
				
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBackground(new Color(85, 200, 100));
		btnEditar.setBounds(130, 615, 150, 40);
		Screen.add(btnEditar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldDescricao.setText(null);
				DefaultTableModel modelMed = (DefaultTableModel) tableMedicamentos.getModel();
				while (modelMed.getRowCount()>0) {
					modelMed.setRowCount(0);
				}
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setBounds(310, 615, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 615, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DA LISTA DE TRIAGENS ======================== //
	private JPanel ScreenListTriagem() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Lista de Triagens:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1000, 430);
		Screen.add(scrollPane);
		
		JTable tableTriagens = new JTable();
		tableTriagens.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableTriagens);
		tableTriagens.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Paciente", "Técnico de Enfermagem", "Data da Triagema", "Urgência"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableTriagens.setRowHeight(30);
		tableTriagens.getTableHeader().setBackground(new Color(56, 56, 56));
		tableTriagens.getTableHeader().setForeground(new Color(255, 255, 255));
		tableTriagens.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel model = (DefaultTableModel) tableTriagens.getModel();
		idTriagens = new ArrayList<>();
		for (Triagem t:new TriagemDAO().getAll()) {
			model.addRow(new Object[] {new PacienteDAO().get(t.getId_paciente()).getPaciente(), new Tecnico_de_EnfermagemDAO().get(t.getId_tecnico()).getTecnico(), Data.toDateBrFormat(t.getDataTriagem()), t.getUrgencia()});
			idTriagens.add(t.getId());
		}
		
		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (model.getRowCount()>0) {
					model.setRowCount(0);
				}
				
				idTriagens = new ArrayList<>();
				for (Triagem t:new TriagemDAO().search(pesquisar.getText())) {
					model.addRow(new Object[] {new PacienteDAO().get(t.getId_paciente()).getPaciente(), new Tecnico_de_EnfermagemDAO().get(t.getId_tecnico()).getTecnico(), Data.toDateBrFormat(t.getDataTriagem()), t.getUrgencia()});
					idTriagens.add(t.getId());
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenCreateTriagem());
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionar.setBackground(new Color(227, 227, 227));
		btnAdicionar.setBounds(1130, 250, 260, 108);
		Screen.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableTriagens.getSelectedRow() == -1) {throw new Exception("Nenhuma Triagem foi selecionada!");}
					SwitchScreen(ScreenEditTriagem(new TriagemDAO().get(idTriagens.get(tableTriagens.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEditar.setBackground(new Color(227, 227, 227));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(1130, 358, 260, 108);
		Screen.add(btnEditar);
		
		JButton btnDelete = new JButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TriagemDAO dao = new TriagemDAO();
					if(tableTriagens.getSelectedRow() == -1) {throw new Exception("Nenhuma Triagem foi selecionada!");}
					if (JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar esta Triagem?") == 0) {
						if (dao.delete(dao.get(idTriagens.get(tableTriagens.getSelectedRow())))==1) {
							JOptionPane.showMessageDialog(null, "Triagem deletada com Sucesso!");
							
							while (model.getRowCount()>0) {
								model.setRowCount(0);
							}
							
							idTriagens = new ArrayList<>();
							for (Triagem t:new TriagemDAO().search(pesquisar.getText())) {
								model.addRow(new Object[] {new PacienteDAO().get(t.getId_paciente()).getPaciente(), new Tecnico_de_EnfermagemDAO().get(t.getId_tecnico()).getTecnico(), Data.toDateBrFormat(t.getDataTriagem()), t.getUrgencia()});
								idTriagens.add(t.getId());
							}
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao deletar a Triagem!");
						}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnDelete.setBackground(new Color(227, 227, 227));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(1130, 466, 260, 107);
		Screen.add(btnDelete);
		
		JButton btnMaisInfo = new JButton("Mais Info");
		btnMaisInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableTriagens.getSelectedRow() == -1) {throw new Exception("Nenhuma Triagem foi selecionada!");}
					MoreInfo tela = new MoreInfo(new TriagemDAO().get(idTriagens.get(tableTriagens.getSelectedRow())));
					tela.setVisible(true);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnMaisInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMaisInfo.setBackground(new Color(227, 227, 227));
		btnMaisInfo.setBounds(1130, 573, 260, 107);
		Screen.add(btnMaisInfo);
		
		return Screen;
	}

	// ======================== TELA DE CADASTRO DE TRIAGEM ======================== //
	private JPanel ScreenCreateTriagem() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1529, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro de Triagem:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, -23, 1539, 858);
		Screen.add(layeredPane);
		
		JPanel panelPaciente = new JPanel();
		panelPaciente.setBounds(0, 108, 1529, 632);
		panelPaciente.setLayout(null);
		panelPaciente.setOpaque(false);
		layeredPane.add(panelPaciente);
		
		JPanel panelTecnico = new JPanel();
		panelTecnico.setBounds(0, 108, 1529, 632);
		panelTecnico.setLayout(null);
		panelTecnico.setOpaque(false);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBounds(0, 108, 1529, 632);
		panelCadastro.setLayout(null);
		panelCadastro.setOpaque(false);
		
		//----------------------------//Selecionar Paciente//----------------------------//
		
		JLabel labelPaciente = new JLabel("Selecione o(a) Paciente");
		labelPaciente.setForeground(new Color(245, 245, 245));
		labelPaciente.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelPaciente.setBounds(130, 40, 300, 30);
		panelPaciente.add(labelPaciente);
		
		JScrollPane scrollPanePac = new JScrollPane();
		scrollPanePac.setBounds(130, 180, 1260, 318);
		panelPaciente.add(scrollPanePac);
		
		JTable tablePacientes = new JTable();
		tablePacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPanePac.setViewportView(tablePacientes);
		tablePacientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF", "Nome", "Data de Nascimento", "Convenio",
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePacientes.setRowHeight(30);
		tablePacientes.getTableHeader().setBackground(new Color(56, 56, 56));
		tablePacientes.getTableHeader().setForeground(new Color(255, 255, 255));
		tablePacientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		DefaultTableModel modelPac = (DefaultTableModel) tablePacientes.getModel();
		idPacientes = new ArrayList<>();
		for (Paciente p:new PacienteDAO().getAll()) {
			modelPac.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio()});
			idPacientes.add(p.getCPF());
		}
		
		JLabel labelPesquisarPac = new JLabel("Pesquisar");
		labelPesquisarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarPac.setForeground(new Color(245, 245, 245));
		labelPesquisarPac.setBounds(130, 80, 300, 30);
		panelPaciente.add(labelPesquisarPac);
		
		JTextField pesquisarPac = new JTextField();
		pesquisarPac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelPac.getRowCount()>0) {
					modelPac.setRowCount(0);
				}
				
				idPacientes = new ArrayList<>();
				for (Paciente p:new PacienteDAO().search(pesquisarPac.getText())) {
					modelPac.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio() });
					idPacientes.add(p.getCPF());
				}
				
			}
		});
		pesquisarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisarPac.setBounds(130, 110, 1260, 30);
		panelPaciente.add(pesquisarPac);
		pesquisarPac.setColumns(10);
		
		JButton btnProximoPac = new JButton("Próximo");
		btnProximoPac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tablePacientes.getSelectedRow() == -1) {throw new Exception("Selecione um Paciente Primeiro!");}
					SwitchScreen(panelTecnico, layeredPane);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnProximoPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoPac.setBackground(new Color(227, 227, 227));
		btnProximoPac.setBounds(130, 540, 139, 46);
		panelPaciente.add(btnProximoPac);
		
		JButton btnCancelarPac = new JButton("Cancelar");
		btnCancelarPac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarPac.setBackground(new Color(227,227,227));
		btnCancelarPac.setBounds(306, 540, 139, 46);
		panelPaciente.add(btnCancelarPac);
		
		//----------------------------//Selecionar Tecnico//----------------------------//
		
		JLabel labelTecnico = new JLabel("Selecione o(a) Técnico(a) de Enfermagem");
		labelTecnico.setForeground(new Color(245, 245, 245));
		labelTecnico.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelTecnico.setBounds(130, 40, 524, 30);
		panelTecnico.add(labelTecnico);
		
		JScrollPane scrollPaneMed = new JScrollPane();
		scrollPaneMed.setBounds(130, 180, 1260, 318);
		panelTecnico.add(scrollPaneMed);
		
		JTable tableTecnicos = new JTable();
		tableTecnicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneMed.setViewportView(tableTecnicos);
		tableTecnicos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COREN", "Nome", "Data de Inscrição"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableTecnicos.setRowHeight(30);
		tableTecnicos.getTableHeader().setBackground(new Color(56, 56, 56));
		tableTecnicos.getTableHeader().setForeground(new Color(255, 255, 255));
		tableTecnicos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelTec = (DefaultTableModel) tableTecnicos.getModel();
		idTecnicos_de_Enfermagem = new ArrayList<>();
		for (Tecnico_de_Enfermagem t:new Tecnico_de_EnfermagemDAO().getAll()) {
			modelTec.addRow(new Object[] {t.getCoren(), t.getNome(), Data.toDateBrFormat(t.getDataInscricao())});
			idTecnicos_de_Enfermagem.add(t.getCoren());
		}
		
		JLabel labelPesquisarTec = new JLabel("Pesquisar");
		labelPesquisarTec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarTec.setForeground(new Color(245, 245, 245));
		labelPesquisarTec.setBounds(130, 80, 300, 30);
		panelTecnico.add(labelPesquisarTec);
		
		JTextField pesquisarTec = new JTextField();
		pesquisarTec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelTec.getRowCount()>0) {
					modelTec.setRowCount(0);
				}
				
				idTecnicos_de_Enfermagem = new ArrayList<>();
				for (Tecnico_de_Enfermagem t:new Tecnico_de_EnfermagemDAO().search(pesquisarTec.getText())) {
					modelTec.addRow(new Object[] {t.getCoren(), t.getNome(), Data.toDateBrFormat(t.getDataInscricao())});
					idTecnicos_de_Enfermagem.add(t.getCoren());
				}
				
			}
		});
		pesquisarTec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisarTec.setBounds(130, 110, 1260, 30);
		panelTecnico.add(pesquisarTec);
		pesquisarTec.setColumns(10);
		
		JButton btnProximoTec = new JButton("Próximo");
		btnProximoTec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableTecnicos.getSelectedRow() == -1) {throw new Exception("Selecione um Técnico de Enfermagem Primeiro!");}
					SwitchScreen(panelCadastro, layeredPane);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnProximoTec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoTec.setBackground(new Color(227, 227, 227));
		btnProximoTec.setBounds(130, 540, 139, 46);
		panelTecnico.add(btnProximoTec);

		JButton btnCancelarTec = new JButton("Cancelar");
		btnCancelarTec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarTec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarTec.setBackground(new Color(227,227,227));
		btnCancelarTec.setBounds(310, 540, 139, 46);
		panelTecnico.add(btnCancelarTec);
		
		//----------------------------//Cadastro da Consulta//----------------------------//
		
		JTextField fieldPeso = new JTextField();
		fieldPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldPeso.setColumns(10);
		fieldPeso.setBounds(130, 180, 500, 30);
		panelCadastro.add(fieldPeso);
		
		JLabel labelPeso = new JLabel("Peso");
		labelPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPeso.setForeground(new Color(245, 245, 245));
		labelPeso.setBounds(130, 150, 300, 30);
		panelCadastro.add(labelPeso);
		
		JTextField fieldDataTriagem = new JTextField();
		fieldDataTriagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataTriagem.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data da Triagem inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataTriagem.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataTriagem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataTriagem.setColumns(10);
		fieldDataTriagem.setBounds(130, 80, 500, 30);
		panelCadastro.add(fieldDataTriagem);
		
		JLabel labelDataTriagem = new JLabel("Data da Triagem (dd/mm/aaaa)");
		labelDataTriagem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataTriagem.setForeground(new Color(245, 245, 245));
		labelDataTriagem.setBounds(130, 50, 350, 30);
		panelCadastro.add(labelDataTriagem);
		
		JTextField fieldTemperatura = new JTextField();
		fieldTemperatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldTemperatura.setText(Text.toMoney(fieldTemperatura.getText()));
			}
		});
		fieldTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldTemperatura.setColumns(10);
		fieldTemperatura.setBounds(130, 280, 500, 30);
		panelCadastro.add(fieldTemperatura);
		
		JLabel labelTemperatura = new JLabel("Temperatura");
		labelTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTemperatura.setForeground(new Color(245, 245, 245));
		labelTemperatura.setBounds(130, 250, 350, 30);
		panelCadastro.add(labelTemperatura);
		
		JLabel labelSintomas = new JLabel("Sintomas");
		labelSintomas.setForeground(new Color(245, 245, 245));
		labelSintomas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelSintomas.setBounds(830, 150, 350, 30);
		panelCadastro.add(labelSintomas);
		
		JComboBox fieldUrgencia = new JComboBox();
		fieldUrgencia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldUrgencia.setBounds(830, 80, 550, 30);
		panelCadastro.add(fieldUrgencia);
		
		ArrayList<String> urgencias = new UrgenciaDAO().getAll();
		for (String u:new UrgenciaDAO().getAllWithClass()) {
			fieldUrgencia.addItem(u);
		}
		
		JLabel labelUrgencia = new JLabel("Urgência");
		labelUrgencia.setForeground(new Color(245, 245, 245));
		labelUrgencia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelUrgencia.setBounds(830, 50, 350, 30);
		panelCadastro.add(labelUrgencia);
		
		JTextField fieldPressaoSys = new JTextField();
		fieldPressaoSys.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldPressaoSys.setColumns(10);
		fieldPressaoSys.setBounds(130, 380, 221, 30);
		panelCadastro.add(fieldPressaoSys);
		
		JTextField fieldPressaoDia = new JTextField();
		fieldPressaoDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldPressaoDia.setColumns(10);
		fieldPressaoDia.setBounds(409, 380, 221, 30);
		panelCadastro.add(fieldPressaoDia);
		
		JLabel labelPressaoSys = new JLabel("Pressao Sys");
		labelPressaoSys.setForeground(new Color(245, 245, 245));
		labelPressaoSys.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPressaoSys.setBounds(130, 349, 221, 30);
		panelCadastro.add(labelPressaoSys);
		
		JLabel labelPressaoDia = new JLabel("Pressao Dia");
		labelPressaoDia.setForeground(new Color(245, 245, 245));
		labelPressaoDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPressaoDia.setBounds(409, 349, 221, 30);
		panelCadastro.add(labelPressaoDia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(830, 180, 411, 230);
		panelCadastro.add(scrollPane);
		
		JTable tableSintomas = new JTable();
		scrollPane.setViewportView(tableSintomas);
		tableSintomas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSintomas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sintomas"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableSintomas.setRowHeight(30);
		tableSintomas.getTableHeader().setBackground(new Color(56, 56, 56));
		tableSintomas.getTableHeader().setForeground(new Color(255, 255, 255));
		tableSintomas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JButton btnAddSintomas = new JButton("Adiocionar");
		btnAddSintomas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableSintomas.getModel();
					String sintoma = JOptionPane.showInputDialog("Digite o Sintoma:");
					
					ArrayList<String> sintomas = new ArrayList<>();
					for (int i = 0; i<tableSintomas.getRowCount(); i++) {
						sintomas.add(tableSintomas.getValueAt(i, 0).toString());
					}
					
					if (sintoma != null) {
						if (sintoma.isEmpty()) {throw new Exception("Sintoma inválido!");}
						if (Text.ExistIn(sintoma, sintomas)){throw new Exception("Este Sintoma já existe na tabela!");}
						
						model.addRow(new Object[] {sintoma});
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddSintomas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddSintomas.setBackground(new Color(227, 227, 227));
		btnAddSintomas.setBounds(1240, 180, 140, 115);
		panelCadastro.add(btnAddSintomas);
		
		JButton btnDelSintomas = new JButton("Deletar");
		btnDelSintomas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableSintomas.getSelectedRow() == -1) {throw new Exception("Nenhum Sintoma foi selecionado!");}
					DefaultTableModel model = (DefaultTableModel) tableSintomas.getModel();
					model.removeRow(tableSintomas.getSelectedRow());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnDelSintomas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelSintomas.setBackground(new Color(227, 227, 227));
		btnDelSintomas.setBounds(1240, 295, 140, 115);
		panelCadastro.add(btnDelSintomas);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldPeso.getText().isEmpty()) {throw new Exception("Campo do Peso vazio!");}
					if (fieldDataTriagem.getText().isEmpty()) {throw new Exception("Campo da Data da Triagem vazio!");}
					if (fieldTemperatura.getText().isEmpty()) {throw new Exception("Campo Temperatura vazio!");}
					if (fieldPressaoDia.getText().isEmpty()) {throw new Exception("Campo Pressao Dia vazio!");}
					if (fieldPressaoSys.getText().isEmpty()) {throw new Exception("Campo Pressao Sys vazio!");}
					if (fieldUrgencia.getSelectedIndex() == -1) {throw new Exception("Campo da Urgência não selecionado!");}
					

					fieldDataTriagem.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataTriagem.getText())));
					              
					
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataTriagem.getText()))) {throw new Exception("Data da Triagem inválida!");}
					
					Pressao p;
					Double peso;
					Double temperatura;
					try {p = new Pressao(Integer.parseInt(fieldPressaoDia.getText()), Integer.parseInt(fieldPressaoSys.getText()));} 
					catch (Exception ex) {throw new Exception("Pressão Inválida!");}
					try {peso = Double.parseDouble(fieldPeso.getText());}
					catch(Exception ex) {throw new Exception("Peso Inválido!");}
					try {temperatura = Double.parseDouble(fieldTemperatura.getText());} 
					catch(Exception ex) {throw new Exception("Temperatura Inválida!");}
					
					
					
					DefaultTableModel model = (DefaultTableModel) tableSintomas.getModel();
					TriagemDAO dao = new TriagemDAO();
					Triagem t = new Triagem(Data.toDateUsFormat(fieldDataTriagem.getText()), peso, urgencias.get(fieldUrgencia.getSelectedIndex()), temperatura, p, idTecnicos_de_Enfermagem.get(tableTecnicos.getSelectedRow()), idPacientes.get(tablePacientes.getSelectedRow()));
					
					for (int i = 0; i<model.getRowCount(); i++) {
						t.addSintomas(new Sintomas(model.getValueAt(i, 0).toString()));
					}
					
					if (dao.create(t) == 1) {		
						t.setId(dao.getIdOf(t));
						for (Sintomas s:t.getSintomas()) {
							new SintomasDAO().create(s, t);
						}
						
						JOptionPane.showMessageDialog(null, "Triagem Cadastrada com Sucesso!");
					
						fieldPeso.setText(null);
						fieldDataTriagem.setText(null);
						fieldTemperatura.setText(null);
						fieldPressaoSys.setText(null);
						fieldPressaoDia.setText(null);
						fieldUrgencia.setSelectedIndex(0);
						
						while (model.getRowCount()>0) {model.setRowCount(0);}
						
						SwitchScreen(ScreenListTriagem());
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro da Triagem!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(130, 570, 150, 40);
		panelCadastro.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldPeso.setText(null);
				fieldDataTriagem.setText(null);
				fieldTemperatura.setText(null);
				fieldPressaoSys.setText(null);
				fieldPressaoDia.setText(null);
				fieldUrgencia.setSelectedIndex(0);
				DefaultTableModel model = (DefaultTableModel) tableSintomas.getModel();
				while (model.getRowCount()>0) {model.setRowCount(0);}
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 570, 130, 40);
		panelCadastro.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListTriagem());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 570, 130, 40);
		panelCadastro.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DE EDIÇÃO DE TRIAGEM ======================== //
	private JPanel ScreenEditTriagem(Triagem triagemSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1529, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Editando Triagem:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, -23, 1539, 858);
		Screen.add(layeredPane);
		
		JPanel panelPaciente = new JPanel();
		panelPaciente.setBounds(0, 108, 1529, 632);
		panelPaciente.setLayout(null);
		panelPaciente.setOpaque(false);
		layeredPane.add(panelPaciente);
		
		JPanel panelTecnico = new JPanel();
		panelTecnico.setBounds(0, 108, 1529, 632);
		panelTecnico.setLayout(null);
		panelTecnico.setOpaque(false);
		
		JPanel panelEdicao = new JPanel();
		panelEdicao.setBounds(0, 108, 1529, 632);
		panelEdicao.setLayout(null);
		panelEdicao.setOpaque(false);
		
		//----------------------------//Selecionar Paciente//----------------------------//
		
		JLabel labelPaciente = new JLabel("Selecione o(a) Paciente");
		labelPaciente.setForeground(new Color(245, 245, 245));
		labelPaciente.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelPaciente.setBounds(130, 40, 300, 30);
		panelPaciente.add(labelPaciente);
		
		JScrollPane scrollPanePac = new JScrollPane();
		scrollPanePac.setBounds(130, 180, 1260, 318);
		panelPaciente.add(scrollPanePac);
		
		JTable tablePacientes = new JTable();
		tablePacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPanePac.setViewportView(tablePacientes);
		tablePacientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF", "Nome", "Data de Nascimento", "Convenio",
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePacientes.setRowHeight(30);
		tablePacientes.getTableHeader().setBackground(new Color(56, 56, 56));
		tablePacientes.getTableHeader().setForeground(new Color(255, 255, 255));
		tablePacientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablePacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		DefaultTableModel modelPac = (DefaultTableModel) tablePacientes.getModel();
		idPacientes = new ArrayList<>();
		for (Paciente p:new PacienteDAO().getAll()) {
			modelPac.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio()});
			idPacientes.add(p.getCPF());
			if (p.getCPF().equals(triagemSelected.getId_paciente())) {tablePacientes.setRowSelectionInterval(modelPac.getRowCount()-1, modelPac.getRowCount()-1);}
		}
		
		JLabel labelPesquisarPac = new JLabel("Pesquisar");
		labelPesquisarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarPac.setForeground(new Color(245, 245, 245));
		labelPesquisarPac.setBounds(130, 80, 300, 30);
		panelPaciente.add(labelPesquisarPac);
		
		JTextField pesquisarPac = new JTextField();
		pesquisarPac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelPac.getRowCount()>0) {
					modelPac.setRowCount(0);
				}
				
				idPacientes = new ArrayList<>();
				for (Paciente p:new PacienteDAO().search(pesquisarPac.getText())) {
					modelPac.addRow(new Object[] {p.getCPF(), p.getNome(), Data.toDateBrFormat(p.getDataNasc()), p.getConvenio().getConvenio() });
					idPacientes.add(p.getCPF());
				}
				
			}
		});
		pesquisarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisarPac.setBounds(130, 110, 1260, 30);
		panelPaciente.add(pesquisarPac);
		pesquisarPac.setColumns(10);
		
		JButton btnProximoPac = new JButton("Próximo");
		btnProximoPac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tablePacientes.getSelectedRow() == -1) {throw new Exception("Selecione um Paciente Primeiro!");}
					SwitchScreen(panelTecnico, layeredPane);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnProximoPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoPac.setBackground(new Color(227, 227, 227));
		btnProximoPac.setBounds(130, 540, 139, 46);
		panelPaciente.add(btnProximoPac);
		
		JButton btnCancelarPac = new JButton("Cancelar");
		btnCancelarPac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarPac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarPac.setBackground(new Color(227,227,227));
		btnCancelarPac.setBounds(306, 540, 139, 46);
		panelPaciente.add(btnCancelarPac);
		
		//----------------------------//Selecionar Tecnico//----------------------------//
		
		JLabel labelTecnico = new JLabel("Selecione o(a) Técnico(a) de Enfermagem");
		labelTecnico.setForeground(new Color(245, 245, 245));
		labelTecnico.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelTecnico.setBounds(130, 40, 524, 30);
		panelTecnico.add(labelTecnico);
		
		JScrollPane scrollPaneMed = new JScrollPane();
		scrollPaneMed.setBounds(130, 180, 1260, 318);
		panelTecnico.add(scrollPaneMed);
		
		JTable tableTecnicos = new JTable();
		tableTecnicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneMed.setViewportView(tableTecnicos);
		tableTecnicos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COREN", "Nome", "Data de Inscrição"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableTecnicos.setRowHeight(30);
		tableTecnicos.getTableHeader().setBackground(new Color(56, 56, 56));
		tableTecnicos.getTableHeader().setForeground(new Color(255, 255, 255));
		tableTecnicos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelTec = (DefaultTableModel) tableTecnicos.getModel();
		idTecnicos_de_Enfermagem = new ArrayList<>();
		for (Tecnico_de_Enfermagem t:new Tecnico_de_EnfermagemDAO().getAll()) {
			modelTec.addRow(new Object[] {t.getCoren(), t.getNome(), Data.toDateBrFormat(t.getDataInscricao())});
			idTecnicos_de_Enfermagem.add(t.getCoren());
			if (t.getCoren().equals(triagemSelected.getId_tecnico())) {tableTecnicos.setRowSelectionInterval(modelTec.getRowCount()-1, modelTec.getRowCount()-1);}
		}
		
		JLabel labelPesquisarTec = new JLabel("Pesquisar");
		labelPesquisarTec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisarTec.setForeground(new Color(245, 245, 245));
		labelPesquisarTec.setBounds(130, 80, 300, 30);
		panelTecnico.add(labelPesquisarTec);
		
		JTextField pesquisarTec = new JTextField();
		pesquisarTec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (modelTec.getRowCount()>0) {
					modelTec.setRowCount(0);
				}
				
				idTecnicos_de_Enfermagem = new ArrayList<>();
				for (Tecnico_de_Enfermagem t:new Tecnico_de_EnfermagemDAO().search(pesquisarTec.getText())) {
					modelTec.addRow(new Object[] {t.getCoren(), t.getNome(), Data.toDateBrFormat(t.getDataInscricao())});
					idTecnicos_de_Enfermagem.add(t.getCoren());
				}
				
			}
		});
		pesquisarTec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisarTec.setBounds(130, 110, 1260, 30);
		panelTecnico.add(pesquisarTec);
		pesquisarTec.setColumns(10);
		
		JButton btnProximoTec = new JButton("Próximo");
		btnProximoTec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableTecnicos.getSelectedRow() == -1) {throw new Exception("Selecione um Técnico de Enfermagem Primeiro!");}
					SwitchScreen(panelEdicao, layeredPane);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnProximoTec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProximoTec.setBackground(new Color(227, 227, 227));
		btnProximoTec.setBounds(130, 540, 139, 46);
		panelTecnico.add(btnProximoTec);

		JButton btnCancelarTec = new JButton("Cancelar");
		btnCancelarTec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConsulta());
			}
		});
		btnCancelarTec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelarTec.setBackground(new Color(227,227,227));
		btnCancelarTec.setBounds(310, 540, 139, 46);
		panelTecnico.add(btnCancelarTec);
		
		//----------------------------//Cadastro da Consulta//----------------------------//
		
		JTextField fieldPeso = new JTextField();
		fieldPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldPeso.setColumns(10);
		fieldPeso.setBounds(130, 180, 500, 30);
		panelEdicao.add(fieldPeso);
		fieldPeso.setText(triagemSelected.getPeso().toString());
		
		JLabel labelPeso = new JLabel("Peso");
		labelPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPeso.setForeground(new Color(245, 245, 245));
		labelPeso.setBounds(130, 150, 300, 30);
		panelEdicao.add(labelPeso);
		
		JTextField fieldDataTriagem = new JTextField();
		fieldDataTriagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dataInsc = Data.toDateUsFormat(fieldDataTriagem.getText());
				try {
					if (!Data.BirthdayIsValid(dataInsc)){throw new Exception("Data da Triagem inválida!");}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				fieldDataTriagem.setText(Data.toDateBrFormat(dataInsc));
			}
		});
		fieldDataTriagem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldDataTriagem.setColumns(10);
		fieldDataTriagem.setBounds(130, 80, 500, 30);
		panelEdicao.add(fieldDataTriagem);
		fieldDataTriagem.setText(Data.toDateBrFormat(triagemSelected.getDataTriagem()));
		
		JLabel labelDataTriagem = new JLabel("Data da Triagem (dd/mm/aaaa)");
		labelDataTriagem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDataTriagem.setForeground(new Color(245, 245, 245));
		labelDataTriagem.setBounds(130, 50, 350, 30);
		panelEdicao.add(labelDataTriagem);
		
		JTextField fieldTemperatura = new JTextField();
		fieldTemperatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldTemperatura.setText(Text.toMoney(fieldTemperatura.getText()));
			}
		});
		fieldTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldTemperatura.setColumns(10);
		fieldTemperatura.setBounds(130, 280, 500, 30);
		panelEdicao.add(fieldTemperatura);
		fieldTemperatura.setText(triagemSelected.getTemperatura().toString());
		
		JLabel labelTemperatura = new JLabel("Temperatura");
		labelTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTemperatura.setForeground(new Color(245, 245, 245));
		labelTemperatura.setBounds(130, 250, 350, 30);
		panelEdicao.add(labelTemperatura);
		
		JLabel labelSintomas = new JLabel("Sintomas");
		labelSintomas.setForeground(new Color(245, 245, 245));
		labelSintomas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelSintomas.setBounds(830, 150, 350, 30);
		panelEdicao.add(labelSintomas);
		
		JComboBox fieldUrgencia = new JComboBox();
		fieldUrgencia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldUrgencia.setBounds(830, 80, 550, 30);
		panelEdicao.add(fieldUrgencia);
		
		ArrayList<String> urgencias = new UrgenciaDAO().getAll();
		for (String u:new UrgenciaDAO().getAllWithClass()) {
			fieldUrgencia.addItem(u);
			if (u.split("// | ")[0].equals(triagemSelected.getUrgencia())) {fieldUrgencia.setSelectedItem(u);}
		}
		
		JLabel labelUrgencia = new JLabel("Urgência");
		labelUrgencia.setForeground(new Color(245, 245, 245));
		labelUrgencia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelUrgencia.setBounds(830, 50, 350, 30);
		panelEdicao.add(labelUrgencia);
		
		JTextField fieldPressaoSys = new JTextField();
		fieldPressaoSys.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldPressaoSys.setColumns(10);
		fieldPressaoSys.setBounds(130, 380, 221, 30);
		panelEdicao.add(fieldPressaoSys);
		fieldPressaoSys.setText(triagemSelected.getPressao().getPressaoSys().toString());
		
		JTextField fieldPressaoDia = new JTextField();
		fieldPressaoDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldPressaoDia.setColumns(10);
		fieldPressaoDia.setBounds(409, 380, 221, 30);
		panelEdicao.add(fieldPressaoDia);
		fieldPressaoDia.setText(triagemSelected.getPressao().getPressaoDia().toString());
		
		JLabel labelPressaoSys = new JLabel("Pressao Sys");
		labelPressaoSys.setForeground(new Color(245, 245, 245));
		labelPressaoSys.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPressaoSys.setBounds(130, 349, 221, 30);
		panelEdicao.add(labelPressaoSys);
		
		JLabel labelPressaoDia = new JLabel("Pressao Dia");
		labelPressaoDia.setForeground(new Color(245, 245, 245));
		labelPressaoDia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPressaoDia.setBounds(409, 349, 221, 30);
		panelEdicao.add(labelPressaoDia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(830, 180, 411, 230);
		panelEdicao.add(scrollPane);
		
		JTable tableSintomas = new JTable();
		scrollPane.setViewportView(tableSintomas);
		tableSintomas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSintomas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sintomas"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableSintomas.setRowHeight(30);
		tableSintomas.getTableHeader().setBackground(new Color(56, 56, 56));
		tableSintomas.getTableHeader().setForeground(new Color(255, 255, 255));
		tableSintomas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel modelSin = (DefaultTableModel) tableSintomas.getModel();
		for (Sintomas s:triagemSelected.getSintomas()) {
			modelSin.addRow(new Object[] {s.getSintoma()});
		}
		
		JButton btnAddSintomas = new JButton("Adiocionar");
		btnAddSintomas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) tableSintomas.getModel();
					String sintoma = JOptionPane.showInputDialog("Digite o Sintoma:");
					
					ArrayList<String> sintomas = new ArrayList<>();
					for (int i = 0; i<tableSintomas.getRowCount(); i++) {
						sintomas.add(tableSintomas.getValueAt(i, 0).toString());
					}
					
					if (sintoma != null) {
						if (sintoma.isEmpty()) {throw new Exception("Sintoma inválido!");}
						if (Text.ExistIn(sintoma, sintomas)){throw new Exception("Este Sintoma já existe na tabela!");}
						
						model.addRow(new Object[] {sintoma});
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddSintomas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddSintomas.setBackground(new Color(227, 227, 227));
		btnAddSintomas.setBounds(1240, 180, 140, 115);
		panelEdicao.add(btnAddSintomas);
		
		JButton btnDelSintomas = new JButton("Deletar");
		btnDelSintomas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableSintomas.getSelectedRow() == -1) {throw new Exception("Nenhum Sintoma foi selecionado!");}
					DefaultTableModel model = (DefaultTableModel) tableSintomas.getModel();
					model.removeRow(tableSintomas.getSelectedRow());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		btnDelSintomas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelSintomas.setBackground(new Color(227, 227, 227));
		btnDelSintomas.setBounds(1240, 295, 140, 115);
		panelEdicao.add(btnDelSintomas);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldPeso.getText().isEmpty()) {throw new Exception("Campo do Peso vazio!");}
					if (fieldDataTriagem.getText().isEmpty()) {throw new Exception("Campo da Data da Triagem vazio!");}
					if (fieldTemperatura.getText().isEmpty()) {throw new Exception("Campo Temperatura vazio!");}
					if (fieldPressaoDia.getText().isEmpty()) {throw new Exception("Campo Pressao Dia vazio!");}
					if (fieldPressaoSys.getText().isEmpty()) {throw new Exception("Campo Pressao Sys vazio!");}
					if (fieldUrgencia.getSelectedIndex() == -1) {throw new Exception("Campo da Urgência não selecionado!");}
					

					fieldDataTriagem.setText(Data.toDateBrFormat(Data.toDateUsFormat(fieldDataTriagem.getText())));
					              
					
					if(!Data.BirthdayIsValid(Data.toDateUsFormat(fieldDataTriagem.getText()))) {throw new Exception("Data da Triagem inválida!");}
					
					Pressao p;
					Double peso;
					Double temperatura;
					try {p = new Pressao(Integer.parseInt(fieldPressaoDia.getText()), Integer.parseInt(fieldPressaoSys.getText()));} 
					catch (Exception ex) {throw new Exception("Pressão Inválida!");}
					try {peso = Double.parseDouble(fieldPeso.getText());}
					catch(Exception ex) {throw new Exception("Peso Inválido!");}
					try {temperatura = Double.parseDouble(fieldTemperatura.getText());} 
					catch(Exception ex) {throw new Exception("Temperatura Inválida!");}
					
					
					
					DefaultTableModel model = (DefaultTableModel) tableSintomas.getModel();
					TriagemDAO dao = new TriagemDAO();
					Triagem t = new Triagem(triagemSelected.getId(),Data.toDateUsFormat(fieldDataTriagem.getText()), peso, urgencias.get(fieldUrgencia.getSelectedIndex()), temperatura, p, idTecnicos_de_Enfermagem.get(tableTecnicos.getSelectedRow()), idPacientes.get(tablePacientes.getSelectedRow()));
					
					for (int i = 0; i<model.getRowCount(); i++) {
						t.addSintomas(new Sintomas(model.getValueAt(i, 0).toString()));
					}
					
					if (dao.update(t) == 1) {	
						for (Sintomas s:triagemSelected.getSintomas()) {
							new SintomasDAO().delete(s, triagemSelected);
						}
						
						for (Sintomas s:t.getSintomas()) {
							new SintomasDAO().create(s, t);
						}
						
						JOptionPane.showMessageDialog(null, "Triagem Editada com Sucesso!");
					
						fieldPeso.setText(null);
						fieldDataTriagem.setText(null);
						fieldTemperatura.setText(null);
						fieldPressaoSys.setText(null);
						fieldPressaoDia.setText(null);
						fieldUrgencia.setSelectedIndex(0);
						
						while (model.getRowCount()>0) {model.setRowCount(0);}
						
						SwitchScreen(ScreenListTriagem());
						
					} else {
						throw new Exception("Erro ao Realizar a Edição da Triagem!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnEditar.setBackground(new Color(85, 200, 100));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(130, 570, 150, 40);
		panelEdicao.add(btnEditar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldPeso.setText(null);
				fieldDataTriagem.setText(null);
				fieldTemperatura.setText(null);
				fieldPressaoSys.setText(null);
				fieldPressaoDia.setText(null);
				fieldUrgencia.setSelectedIndex(0);
				DefaultTableModel model = (DefaultTableModel) tableSintomas.getModel();
				while (model.getRowCount()>0) {model.setRowCount(0);}
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 570, 130, 40);
		panelEdicao.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListTriagem());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 570, 130, 40);
		panelEdicao.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DA LISTA DE CONVENIOS ======================== //
	private JPanel ScreenListConvenio() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Lista de Convenios:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1000, 430);
		Screen.add(scrollPane);
		
		JTable tableConvenios = new JTable();
		tableConvenios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableConvenios);
		tableConvenios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Convenio", "Valor Mensal", "Descri\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConvenios.setRowHeight(30);
		tableConvenios.getTableHeader().setBackground(new Color(56, 56, 56));
		tableConvenios.getTableHeader().setForeground(new Color(255, 255, 255));
		tableConvenios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel model = (DefaultTableModel) tableConvenios.getModel();
		idConvenios = new ArrayList<>();
		for (Convenio c:new ConvenioDAO().getAll()) {
			model.addRow(new Object[] {c.getConvenio(), Text.toMoney(c.getValor_mensal()), c.getDescricao()});
			idConvenios.add(c.getId());
		}
		
		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (model.getRowCount()>0) {
					model.setRowCount(0);
				}
				
				idConvenios = new ArrayList<>();
				for (Convenio c:new ConvenioDAO().search(pesquisar.getText())) {
					model.addRow(new Object[] {c.getConvenio(), Text.toMoney(c.getValor_mensal()), c.getDescricao()});
					idConvenios.add(c.getId());
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(ScreenCreateConvenio());
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdicionar.setBackground(new Color(227, 227, 227));
		btnAdicionar.setBounds(1130, 250, 260, 144);
		Screen.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tableConvenios.getSelectedRow() == -1) {throw new Exception("Nenhum Convenio foi selecionado!");}
					SwitchScreen(ScreenEditConvenio(new ConvenioDAO().get(idConvenios.get(tableConvenios.getSelectedRow()))));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnEditar.setBackground(new Color(227, 227, 227));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(1130, 394, 260, 143);
		Screen.add(btnEditar);
		
		JButton btnDelete = new JButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConvenioDAO dao = new ConvenioDAO();
					if(tableConvenios.getSelectedRow() == -1) {throw new Exception("Nenhum Convenio foi selecionado!");}
					if (JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar este Convenio?") == 0) {
						if (dao.get(idConvenios.get(tableConvenios.getSelectedRow())).getId() == 1) {
							throw new Exception("Não é possivel deletar o convenio público");
						}
						
						if (dao.delete(dao.get(idConvenios.get(tableConvenios.getSelectedRow())))==1) {
							JOptionPane.showMessageDialog(null, "Convenio deletado com Sucesso!");
							
							while (model.getRowCount()>0) {
								model.setRowCount(0);
							}
							
							idConvenios = new ArrayList<>();
							for (Convenio c:new ConvenioDAO().search(pesquisar.getText())) {
								model.addRow(new Object[] {c.getConvenio(), Text.toMoney(c.getValor_mensal()), c.getDescricao()});
								idConvenios.add(c.getId());
							}
						}else {
							JOptionPane.showMessageDialog(null, "Erro ao deletar o Convenio!");
						}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnDelete.setBackground(new Color(227, 227, 227));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(1130, 537, 260, 143);
		Screen.add(btnDelete);
		
		return Screen;
	}

	// ======================== TELA DE CADASTRO DE CONVENIO ======================== //
	private JPanel ScreenCreateConvenio() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Cadastro de Convenio:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldConvenio = new JTextField();
		fieldConvenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldConvenio.setText(Conselho.conselhoFormat(fieldConvenio.getText()));
			}
		});
		
		fieldConvenio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldConvenio.setBounds(130, 180, 480, 30);
		Screen.add(fieldConvenio);
		fieldConvenio.setColumns(10);
		
		JLabel labelNomeConvenio = new JLabel("Nome do Convenio:");
		labelNomeConvenio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNomeConvenio.setForeground(new Color(245, 245, 245));
		labelNomeConvenio.setBounds(130, 150, 300, 30);
		Screen.add(labelNomeConvenio);
		
		JLabel labelDescricao = new JLabel("Descrição:");
		labelDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDescricao.setForeground(new Color(245, 245, 245));
		labelDescricao.setBounds(830, 150, 300, 30);
		Screen.add(labelDescricao);
		
		JTextField fieldValorMensal = new JTextField();
		fieldValorMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldValorMensal.setText(Text.toMoney(fieldValorMensal.getText()));
			}
		});
		fieldValorMensal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldValorMensal.setColumns(10);
		fieldValorMensal.setBounds(130, 330, 480, 30);
		Screen.add(fieldValorMensal);
		
		JLabel labelValorMensal = new JLabel("Valor Mensal:");
		labelValorMensal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelValorMensal.setForeground(new Color(245, 245, 245));
		labelValorMensal.setBounds(130, 300, 350, 30);
		Screen.add(labelValorMensal);
		
		JTextPane fieldDescricao = new JTextPane();
		fieldDescricao.setBounds(830, 180, 480, 180);
		Screen.add(fieldDescricao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldConvenio.getText().isEmpty()) {throw new Exception("Campo Nome do Convenio vazio!");	} 
					if (fieldDescricao.getText().isEmpty()) {throw new Exception("Campo da Descrição vazio!");}
					if (fieldValorMensal.getText().isEmpty()) {throw new Exception("Campo do Valor Mensal vazio!");}
					
					fieldConvenio.setText(Text.toName(fieldConvenio.getText()));
					fieldValorMensal.setText(Text.toMoney(fieldValorMensal.getText()));           
					
					
					ConvenioDAO dao = new ConvenioDAO();
					Convenio c = new Convenio(fieldConvenio.getText(), fieldDescricao.getText(), Text.moneyToDouble(fieldValorMensal.getText()));
					
					if (new ConvenioDAO().existNome(c)) {throw new Exception("Convenio já existe!");}
					
					if (dao.create(c) == 1) {
						JOptionPane.showMessageDialog(null, "Convenio Cadastrado com Sucesso!");
						
						fieldConvenio.setText(null);
						fieldDescricao.setText(null);
						fieldValorMensal.setText(null);
						
						SwitchScreen(ScreenListConvenio());
						
					} else {
						throw new Exception("Erro ao Realizar o Cadastro do Convenio!");
						
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnCadastrar.setBackground(new Color(85, 200, 100));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrar.setBounds(130, 590, 150, 40);
		Screen.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldConvenio.setText(null);
				fieldDescricao.setText(null);
				fieldValorMensal.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConvenio());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DE EDIÇÃO DE CONVENIO ======================== //
	private JPanel ScreenEditConvenio(Convenio convenioSelected) {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Editando Convenio:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JTextField fieldConvenio = new JTextField();
		fieldConvenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldConvenio.setText(Conselho.conselhoFormat(fieldConvenio.getText()));
			}
		});
		
		fieldConvenio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldConvenio.setBounds(130, 180, 480, 30);
		Screen.add(fieldConvenio);
		fieldConvenio.setColumns(10);
		fieldConvenio.setText(convenioSelected.getConvenio());
		
		JLabel labelNomeConvenio = new JLabel("Nome do Convenio:");
		labelNomeConvenio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNomeConvenio.setForeground(new Color(245, 245, 245));
		labelNomeConvenio.setBounds(130, 150, 300, 30);
		Screen.add(labelNomeConvenio);
		
		JLabel labelDescricao = new JLabel("Descrição:");
		labelDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDescricao.setForeground(new Color(245, 245, 245));
		labelDescricao.setBounds(830, 150, 300, 30);
		Screen.add(labelDescricao);
		
		JTextField fieldValorMensal = new JTextField();
		fieldValorMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldValorMensal.setText(Text.toMoney(fieldValorMensal.getText()));
			}
		});
		fieldValorMensal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldValorMensal.setColumns(10);
		fieldValorMensal.setBounds(130, 330, 480, 30);
		Screen.add(fieldValorMensal);
		fieldValorMensal.setText(Text.toMoney(convenioSelected.getValor_mensal()));
		
		JLabel labelValorMensal = new JLabel("Valor Mensal:");
		labelValorMensal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelValorMensal.setForeground(new Color(245, 245, 245));
		labelValorMensal.setBounds(130, 300, 350, 30);
		Screen.add(labelValorMensal);
		
		
		JTextPane fieldDescricao = new JTextPane();
		fieldDescricao.setBounds(830, 180, 480, 180);
		fieldDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Screen.add(fieldDescricao);
		fieldDescricao.setText(convenioSelected.getDescricao());
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fieldConvenio.getText().isEmpty()) {throw new Exception("Campo Nome do Convenio vazio!");	} 
					if (fieldDescricao.getText().isEmpty()) {throw new Exception("Campo da Descrição vazio!");}
					if (fieldValorMensal.getText().isEmpty()) {throw new Exception("Campo do Valor Mensal vazio!");}
					
					fieldConvenio.setText(Text.toName(fieldConvenio.getText()));
					fieldValorMensal.setText(Text.toMoney(fieldValorMensal.getText()));           
					
					
					ConvenioDAO dao = new ConvenioDAO();
					Convenio c = new Convenio(convenioSelected.getId() ,fieldConvenio.getText(), fieldDescricao.getText(), Text.moneyToDouble(fieldValorMensal.getText()));
					
					if (!new ConvenioDAO().exist(c)) {throw new Exception("Convenio não existe!");}
					if (new ConvenioDAO().existNome(c) && !c.getConvenio().equals(convenioSelected.getConvenio())) {throw new Exception("Convenio já existe!");}
					
					if (dao.update(c) == 1||convenioSelected == c) {
						JOptionPane.showMessageDialog(null, "Convenio Editado com Sucesso!");
						
						fieldConvenio.setText(null);
						fieldDescricao.setText(null);
						fieldValorMensal.setText(null);
						
						SwitchScreen(ScreenListConvenio());
						
					} else {
						throw new Exception("Erro ao Realizar a Edição do Convenio!");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				

			}
		});
		btnEditar.setBackground(new Color(85, 200, 100));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(130, 590, 150, 40);
		Screen.add(btnEditar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldConvenio.setText(null);
				fieldDescricao.setText(null);
				fieldValorMensal.setText(null);
			}
		});
		btnLimpar.setBackground(new Color(0, 128, 255));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLimpar.setBounds(310, 590, 130, 40);
		Screen.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada com Sucesso!");
				SwitchScreen(ScreenListConvenio());
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(227, 227, 227));
		btnCancelar.setBounds(470, 590, 130, 40);
		Screen.add(btnCancelar);
		
		return Screen;
	}

	// ======================== TELA DO RELATÓRIO DOS CONVENIOS ======================== //
	private JPanel ScreenRelatorioConvenio() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Relatório dos Convenios:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1260, 430);
		Screen.add(scrollPane);
		
		JTable tableConvenios = new JTable();
		tableConvenios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableConvenios);
		tableConvenios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Convenio", "Lucro Mensal Total", "Quantidade de Baneficiados"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConvenios.setRowHeight(30);
		tableConvenios.getTableHeader().setBackground(new Color(56, 56, 56));
		tableConvenios.getTableHeader().setForeground(new Color(255, 255, 255));
		tableConvenios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel model = (DefaultTableModel) tableConvenios.getModel();
		for (Object r:new ConvenioDAO().getRelatorio()) {
			Object[] dados = (Object[]) r;
			model.addRow(dados);
		}
		
		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (model.getRowCount()>0) {
					model.setRowCount(0);
				}
				
				for (Object r:new ConvenioDAO().searchRelatorio(pesquisar.getText())) {
					Object[] dados = (Object[]) r;
					model.addRow(dados);
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);
		
		return Screen;
	}

	// ======================== TELA DO RELATÓRIO DA ENFERMARIA ======================== //
	private JPanel ScreenRelatorioEnfermaria() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 1520, 740);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		Screen.setOpaque(false);
		
		JLabel titulo = new JLabel("Relatório da Enefermaria:");
		titulo.setFont(new Font("Dubai", Font.BOLD, 40));
		titulo.setForeground(new Color(245, 245, 245));
		titulo.setBounds(130, 40, 864, 58);
		Screen.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 250, 1260, 430);
		Screen.add(scrollPane);
		
		JTable tableEnfer = new JTable();
		tableEnfer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableEnfer);
		tableEnfer.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COREN", "Nome", "Data de Inscricão", "Especialização"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableEnfer.setRowHeight(30);
		tableEnfer.getTableHeader().setBackground(new Color(56, 56, 56));
		tableEnfer.getTableHeader().setForeground(new Color(255, 255, 255));
		tableEnfer.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		DefaultTableModel model = (DefaultTableModel) tableEnfer.getModel();
		for (Object r:new EnfermariaDAO().getRelatorio()) {
			Object[] dados = (Object[]) r;
			model.addRow(dados);
		}
		
		JLabel labelPesquisar = new JLabel("Pesquisar");
		labelPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPesquisar.setForeground(new Color(245, 245, 245));
		labelPesquisar.setBounds(130, 150, 300, 30);
		Screen.add(labelPesquisar);
		
		JTextField pesquisar = new JTextField();
		pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				while (model.getRowCount()>0) {
					model.setRowCount(0);
				}
				
				for (Object r:new EnfermariaDAO().searchRelatorio(pesquisar.getText())) {
					Object[] dados = (Object[]) r;
					model.addRow(dados);
				}
				
			}
		});
		pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pesquisar.setBounds(130, 180, 1000, 30);
		Screen.add(pesquisar);
		pesquisar.setColumns(10);
		
		return Screen;
	}
}




