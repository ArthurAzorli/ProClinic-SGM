package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bin.Alergias;
import bin.Auxilia;
import bin.Consulta;
import bin.Ficha_Tecnica;
import bin.Medicamentos;
import bin.Medico;
import bin.Paciente;
import bin.Receita;
import bin.Sintomas;
import bin.Tecnico_de_Enfermagem;
import bin.Telefones;
import bin.Triagem;
import bin.Enfermeiro;
import dao.AuxiliaDAO;
import dao.ConsultaDAO;
import dao.EnfermeiroDAO;
import dao.Ficha_TecnicaDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.UFDAO;
import dao.ReceitaDAO;
import dao.SintomasDAO;
import dao.Tecnico_de_EnfermagemDAO;
import dao.TriagemDAO;
import utils.Data;
import utils.Text;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import java.awt.Panel;
import java.awt.Toolkit;

public class MoreInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableEnfer ;
	private JTextPane descricaoRec;
	private JLabel nome;
	private JLabel nomePac;
	private JLabel cpf;
	private JLabel cpfPac;
	private JLabel dataNasc;
	private JLayeredPane layeredPane;
	private JPanel sintomas;
	private JPanel Enfermeiro;
	private int cont;
	private ArrayList<JPanel> telas = new ArrayList<>();
	private JLabel convenio;
	private JLabel valorMensal;
	private JLabel tipoSanguineo;
	private JPanel Convenio;
	private JLabel dataTriagem;
	private JLabel urgencia;
	private JLabel logradouro;
	private JLabel numero;
	private JLabel estado;
	private JLabel bairro;
	private JLabel cidade;
	private JPanel Contato;
	private JTable tableTelefones;
	private JPanel DadosFicha;
	private JTextPane historico;
	private JTable tableAlergias;
	private JLabel corenTec;
	private JLabel nomeTec;
	private JTable tableSin;
	private JLabel peso;
	private JLabel temperatura;
	private JLabel pressao;
	private JLabel statusPressao;
	private JPanel InfoPac;
	private JTextPane descricao;
	private JLabel crmMed;
	private JLabel nomeMed;
	private JLabel dataConsulta;
	private JLabel horario;
	private JLabel valorConsulta;
	private JPanel Receita;
	private JTable tableMed;
	private JPanel Diagnostico;

	//---------------------------------------// MAIS INFO PACIENTE //---------------------------------------//

	public MoreInfo(Paciente p) {
		setTitle("Mais Informação ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel detalhe = new JPanel();
		detalhe.setOpaque(false);
		detalhe.setBounds(70, 0, 155, 55);
		contentPane.add(detalhe);
		detalhe.setLayout(null);
		
		JLabel tituloPaciente = new JLabel("Paciente:");
		tituloPaciente.setForeground(new Color(255, 255, 255));
		tituloPaciente.setFont(new Font("Dubai", Font.BOLD, 30));
		tituloPaciente.setBounds(0, 10, 152, 40);
		detalhe.add(tituloPaciente);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.setBounds(57, 54, 875, 121);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel nomeLabel = new JLabel("Nome: ");
		nomeLabel.setForeground(new Color(255, 255, 255));
		nomeLabel.setFont(new Font("Dubai", Font.BOLD, 18));
		nomeLabel.setBounds(30, 20, 55, 30);
		panel.add(nomeLabel);
		
		nome = new JLabel("");
		nome.setForeground(new Color(255, 255, 255));
		nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nome.setBounds(85, 20, 739, 30);
		panel.add(nome);
		
		cpf = new JLabel("");
		cpf.setForeground(new Color(255, 255, 255));
		cpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpf.setBounds(85, 80, 300, 30);
		panel.add(cpf);
		
		JLabel cpfLabel = new JLabel("CPF: ");
		cpfLabel.setForeground(new Color(255, 255, 255));
		cpfLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		cpfLabel.setBounds(30, 80, 55, 30);
		panel.add(cpfLabel);
		
		JLabel dataNascLabel = new JLabel("Data de Nascimento: ");
		dataNascLabel.setForeground(new Color(255, 255, 255));
		dataNascLabel.setFont(new Font("Dubai", Font.BOLD, 18));
		dataNascLabel.setBounds(421, 80, 175, 30);
		panel.add(dataNascLabel);
		
		dataNasc = new JLabel("");
		dataNasc.setForeground(new Color(255, 255, 255));
		dataNasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dataNasc.setBounds(595, 80, 270, 30);
		panel.add(dataNasc);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(57, 199, 875, 278);
		contentPane.add(layeredPane);
		
		// ======================== TELA DE CONVENIOS ======================== //
		
		Convenio = new JPanel();
		Convenio.setBackground(new Color(255, 255, 255));
		Convenio.setBounds(0, 0, 875, 278);
		layeredPane.add(Convenio);
		Convenio.setLayout(null);
		
		JLabel tituloConvenio = new JLabel("Convenio:");
		tituloConvenio.setFont(new Font("Dubai", Font.BOLD, 30));
		tituloConvenio.setBounds(30, 10, 200, 40);
		Convenio.add(tituloConvenio);
		
		JLabel convenioLabel = new JLabel("Convenio: ");
		convenioLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		convenioLabel.setBounds(30, 90, 80, 30);
		Convenio.add(convenioLabel);
		
		JLabel valorMensalLabel = new JLabel("Valor Mensal: ");
		valorMensalLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		valorMensalLabel.setBounds(30, 180, 102, 30);
		Convenio.add(valorMensalLabel);
		
		convenio = new JLabel((String) null);
		convenio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		convenio.setBounds(132, 90, 290, 30);
		Convenio.add(convenio);
		
		valorMensal = new JLabel((String) null);
		valorMensal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		valorMensal.setBounds(132, 180, 290, 30);
		Convenio.add(valorMensal);
		
		JLabel descricaoLabel = new JLabel("Descrição: ");
		descricaoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		descricaoLabel.setBounds(540, 60, 100, 30);
		Convenio.add(descricaoLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(540, 100, 290, 140);
		Convenio.add(scrollPane_1);
		
		descricao = new JTextPane();
		scrollPane_1.setViewportView(descricao);
		descricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descricao.setEditable(false);
		descricao.setText(p.getConvenio().getDescricao());
		
		telas.add(Convenio);
		
		// ======================== TELA DE CONTATOS ======================== //
		Contato = new JPanel();
		Contato.setBackground(new Color(255, 255, 255));
		Contato.setBounds(0, 0, 875, 278);
		layeredPane.add(Contato);
		Contato.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 68, 360, 180);
		Contato.add(scrollPane);
		
		tableTelefones  = new JTable();
		scrollPane.setViewportView(tableTelefones);
		tableTelefones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableTelefones.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Telefones"
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
		
		JLabel tituloContato = new JLabel("Contato:");
		tituloContato.setFont(new Font("Dubai", Font.BOLD, 30));
		tituloContato.setBounds(31, 10, 198, 39);
		Contato.add(tituloContato);
		
		JLabel logradouroLabel = new JLabel("Logradouro: ");
		logradouroLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		logradouroLabel.setBounds(470, 30, 93, 30);
		Contato.add(logradouroLabel);
		
		JLabel bairroLabel = new JLabel("Bairro: ");
		bairroLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		bairroLabel.setBounds(470, 120, 60, 30);
		Contato.add(bairroLabel);
		
		JLabel numeroLabel = new JLabel("Nùmero: ");
		numeroLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		numeroLabel.setBounds(470, 70, 71, 30);
		Contato.add(numeroLabel);
		
		JLabel cidadeLabel = new JLabel("Cidade: ");
		cidadeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		cidadeLabel.setBounds(471, 170, 71, 30);
		Contato.add(cidadeLabel);
		
		JLabel estadoLabel = new JLabel("Estado: ");
		estadoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		estadoLabel.setBounds(471, 220, 71, 30);
		Contato.add(estadoLabel);
		
		logradouro = new JLabel("");
		logradouro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logradouro.setBounds(565, 30, 290, 30);
		Contato.add(logradouro);
		
		numero = new JLabel("");
		numero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numero.setBounds(545, 70, 310, 30);
		Contato.add(numero);
		
		bairro = new JLabel("");
		bairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bairro.setBounds(545, 120, 310, 30);
		Contato.add(bairro);
		
		cidade = new JLabel("");
		cidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cidade.setBounds(545, 170, 310, 30);
		Contato.add(cidade);
		
		estado = new JLabel("");
		estado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		estado.setBounds(545, 218, 310, 30);
		Contato.add(estado);
		
		telas.add(Contato);
		
		// ======================== PREENCHER DADOS ======================== //
		
		nome.setText(p.getNome());
		cpf.setText(p.getCPF());
		((JLabel) dataNasc).setText(Data.toDateBrFormat(p.getDataNasc()));
		logradouro.setText(p.getEndereco().getRua());
		numero.setText(p.getEndereco().getNro());
		bairro.setText(p.getEndereco().getBairro());
		cidade.setText(p.getEndereco().getCidade());
		((JLabel) estado).setText(new UFDAO().getEstado(p.getEndereco().getUF()));
		convenio.setText(p.getConvenio().getConvenio());
		valorMensal.setText(Text.toMoney(p.getConvenio().getValor_mensal()));
		
		
		DefaultTableModel modelTel = (DefaultTableModel) tableTelefones.getModel();
		for (Telefones t:p.getTelefone()) {
			modelTel.addRow(new Object[] {t.getTelefone()});
		}		
		
		
		// ======================== TELA FICHA TECNICA ======================== //
		
		if(new Ficha_TecnicaDAO().have(p)) {
			DadosFicha = new JPanel();
			DadosFicha.setBackground(new Color(255, 255, 255));
			DadosFicha.setBounds(0, 0, 875, 278);
			layeredPane.add(DadosFicha);
			DadosFicha.setLayout(null);
			
			JLabel tituloFicha = new JLabel("Ficha Técnica:");
			tituloFicha.setFont(new Font("Dubai", Font.BOLD, 30));
			tituloFicha.setBounds(30, 10, 198, 39);
			DadosFicha.add(tituloFicha);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(365, 80, 300, 170);
			DadosFicha.add(scrollPane);
			
			historico = new JTextPane();
			historico.setFont(new Font("Tahoma", Font.PLAIN, 14));
			scrollPane.setViewportView(historico);
			historico.setEditable(false);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(30, 80, 300, 170);
			DadosFicha.add(scrollPane_2);
			
			tableAlergias = new JTable();
			scrollPane_2.setViewportView(tableAlergias);
			tableAlergias.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableAlergias.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Alergias"
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
			
			
			JLabel tipoSanguineoLabel = new JLabel("Tipo Sanguineo: ");
			tipoSanguineoLabel.setFont(new Font("Dubai", Font.BOLD, 18));
			tipoSanguineoLabel.setBounds(700, 111, 131, 30);
			DadosFicha.add(tipoSanguineoLabel);
			
			JLabel historicoLabel = new JLabel("Histórico Familiar: ");
			historicoLabel.setFont(new Font("Dubai", Font.BOLD, 18));
			historicoLabel.setBounds(365, 51, 171, 30);
			DadosFicha.add(historicoLabel);
			
			tipoSanguineo = new JLabel("");
			tipoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 36));
			tipoSanguineo.setBounds(737, 137, 65, 62);
			DadosFicha.add(tipoSanguineo);
			
			Ficha_Tecnica ficha = new Ficha_TecnicaDAO().getOfPaciente(p.getCPF());
			tipoSanguineo.setText(ficha.getTipo_Sanguineo());
			historico.setText(ficha.getHistorico_familiar());
			DefaultTableModel modelAle = (DefaultTableModel) tableAlergias.getModel();
			for (Alergias a:ficha.getAlergia()) {
				modelAle.addRow(new Object[] {a.getAlergia()});
			}
			telas.add(DadosFicha);
		}
		
		
		// ======================== OPÇÕES ======================== //
		if (telas.size() >1){
			cont = 0;
			JButton btnProximo = new JButton("Próximo");
			btnProximo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cont += 1;
					if(cont==telas.size()){cont = 0;}
					SwitchScreen(telas.get(cont));
				}
			});
			btnProximo.setBackground(new Color(227, 227, 227));
			btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnProximo.setBounds(192, 510, 120, 40);
			contentPane.add(btnProximo);
			
			JButton btnAnterior = new JButton("Anterior");
			btnAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cont -= 1;
					if(cont==-1){cont = telas.size()-1;}
					SwitchScreen(telas.get(cont));
				}
			});
			btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAnterior.setBackground(new Color(227, 227, 227));
			btnAnterior.setBounds(57, 510, 120, 40);
			contentPane.add(btnAnterior);
		}
		
		//-------fundo-------
		JLabel background = new JLabel();
		background.setBounds(0, 0, 986, 565);
		background.setIcon(new ImageIcon(Index.class.getResource("/img/backgroud_1.png")));
		contentPane.add(background);
		
		
	}
	
	//---------------------------------------// MAIS INFO CONSULTA //---------------------------------------//

	public MoreInfo(Consulta c) {
		setTitle("Mais Informação ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/img/logo.png")));
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel detalhe = new JPanel();
		detalhe.setBounds(57, 2, 160, 40);
		detalhe.setOpaque(false);
		contentPane.add(detalhe);
		detalhe.setLayout(null);
		
		JLabel tituloPaciente = new JLabel("Paciente:");
		tituloPaciente.setForeground(new Color(255, 255, 255));
		tituloPaciente.setBounds(20, 0, 119, 51);
		detalhe.add(tituloPaciente);
		tituloPaciente.setBackground(Color.DARK_GRAY);
		tituloPaciente.setFont(new Font("Dubai", Font.BOLD, 30));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.setBounds(57, 40, 875, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCPF = new JLabel("CPF :");
		lblCPF.setForeground(new Color(255, 255, 255));
		lblCPF.setFont(new Font("Dubai", Font.BOLD, 18));
		lblCPF.setBounds(38, 30, 66, 30);
		panel.add(lblCPF);
		
		cpfPac = new JLabel((String) null);
		cpfPac.setForeground(new Color(255, 255, 255));
		cpfPac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfPac.setBounds(74, 30, 242, 30);
		panel.add(cpfPac);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Dubai", Font.BOLD, 18));
		lblNome.setBounds(333, 30, 66, 30);
		panel.add(lblNome);
		
		nomePac = new JLabel((String) null);
		nomePac.setForeground(new Color(255, 255, 255));
		nomePac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomePac.setBounds(393, 30, 448, 30);
		panel.add(nomePac);
		
		JPanel detalhe_1 = new JPanel();
		detalhe_1.setOpaque(false);
		detalhe_1.setBounds(57, 120, 160, 40);
		contentPane.add(detalhe_1);
		detalhe_1.setLayout(null);
		
		JLabel tituloMedico = new JLabel("Médico(a):");
		tituloMedico.setForeground(new Color(255, 255, 255));
		tituloMedico.setBounds(10, 0, 140, 51);
		detalhe_1.add(tituloMedico);
		tituloMedico.setBackground(Color.DARK_GRAY);
		tituloMedico.setFont(new Font("Dubai", Font.BOLD, 30));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel_1.setBounds(57, 160, 875, 82);
		contentPane.add(panel_1);
		panel.setLayout(null);
		panel_1.setLayout(null);
		
		JLabel lblCRM = new JLabel("CRM:");
		lblCRM.setForeground(new Color(255, 255, 255));
		lblCRM.setFont(new Font("Dubai", Font.BOLD, 18));
		lblCRM.setBounds(38, 30, 66, 30);
		panel_1.add(lblCRM);
		
		crmMed = new JLabel((String) null);
		crmMed.setForeground(new Color(255, 255, 255));
		crmMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		crmMed.setBounds(81, 30, 242, 30);
		panel_1.add(crmMed);
		
		JLabel lblNomeMed = new JLabel("Nome :");
		lblNomeMed.setForeground(new Color(255, 255, 255));
		lblNomeMed.setFont(new Font("Dubai", Font.BOLD, 18));
		lblNomeMed.setBounds(333, 30, 66, 30);
		panel_1.add(lblNomeMed);
		
		nomeMed = new JLabel((String) null);
		nomeMed.setForeground(new Color(255, 255, 255));
		nomeMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomeMed.setBounds(391, 30, 448, 30);
		panel_1.add(nomeMed);
		
		JLabel lblDataConsulta = new JLabel("Data:");
		lblDataConsulta.setForeground(new Color(255, 255, 255));
		lblDataConsulta.setFont(new Font("Dubai", Font.BOLD, 18));
		lblDataConsulta.setBounds(57, 263, 66, 30);
		contentPane.add(lblDataConsulta);
		
		dataConsulta = new JLabel((String) null);
		dataConsulta.setForeground(new Color(255, 255, 255));
		dataConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dataConsulta.setBounds(103, 262, 180, 30);
		contentPane.add(dataConsulta);
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setForeground(new Color(255, 255, 255));
		lblHorario.setFont(new Font("Dubai", Font.BOLD, 18));
		lblHorario.setBounds(388, 263, 85, 30);
		contentPane.add(lblHorario);
		
		horario = new JLabel((String) null);
		horario.setForeground(new Color(255, 255, 255));
		horario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horario.setBounds(463, 262, 160, 30);
		contentPane.add(horario);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setForeground(new Color(255, 255, 255));
		lblValor.setFont(new Font("Dubai", Font.BOLD, 18));
		lblValor.setBounds(667, 263, 85, 30);
		contentPane.add(lblValor);
		
		valorConsulta = new JLabel((String) null);
		valorConsulta.setForeground(new Color(255, 255, 255));
		valorConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		valorConsulta.setBounds(725, 262, 207, 30);
		contentPane.add(valorConsulta);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(57, 320, 875, 278);
		contentPane.add(layeredPane);
		
		// ======================== TELA DE ENFERMEIROS ======================== //
		if (new AuxiliaDAO().have(c)) {
			Enfermeiro = new JPanel();
			Enfermeiro.setBackground(new Color(255, 255, 255));
			Enfermeiro.setBounds(0, 0, 875, 278);
			layeredPane.add(Enfermeiro);
			Enfermeiro.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(31, 68, 813, 180);
			Enfermeiro.add(scrollPane);
			
			tableEnfer = new JTable();
			tableEnfer.setFont(new Font("Tahoma", Font.PLAIN, 14));
			scrollPane.setViewportView(tableEnfer);
			tableEnfer.setModel(new DefaultTableModel(
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
			tableEnfer.setRowHeight(30);
			tableEnfer.getTableHeader().setBackground(new Color(56, 56, 56));
			tableEnfer.getTableHeader().setForeground(new Color(255, 255, 255));
			tableEnfer.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
			
			JLabel tituloEnfermeiros = new JLabel("Enfermeiros:");
			tituloEnfermeiros.setFont(new Font("Dubai", Font.BOLD, 30));
			tituloEnfermeiros.setBounds(31, 10, 198, 39);
			Enfermeiro.add(tituloEnfermeiros);
			
			telas.add(Enfermeiro);		
			
			DefaultTableModel modelEnfer = (DefaultTableModel) tableEnfer.getModel();
			for (Auxilia a: new AuxiliaDAO().getConsultaList(c.getId())) {
				Enfermeiro enf = new EnfermeiroDAO().get(a.getId_enfer());
				modelEnfer.addRow(new Object[] {enf.getCoren(), enf.getNome(), Data.toDateBrFormat(enf.getDataInscricao()), enf.getEspecializacao()});
			}
		
		}
		
		// ======================== TELA RECEITA ======================== //
		
		if(new ReceitaDAO().have(c)) {
			Receita = new JPanel();
			Receita.setBackground(new Color(255, 255, 255));
			Receita.setBounds(0, 0, 875, 278);
			layeredPane.add(Receita);
			Receita.setLayout(null);
			
			JLabel tituloReceita = new JLabel("Receita:");
			tituloReceita.setFont(new Font("Dubai", Font.BOLD, 30));
			tituloReceita.setBounds(30, 10, 198, 39);
			Receita.add(tituloReceita);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(458, 80, 388, 170);
			Receita.add(scrollPane);
			
			descricao = new JTextPane();
			scrollPane.setViewportView(descricao);
			descricao.setEditable(false);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(30, 80, 388, 170);
			Receita.add(scrollPane_2);
			
			tableMed = new JTable();
			scrollPane_2.setViewportView(tableMed);
			tableMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableMed.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Medicamentos"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tableMed.setRowHeight(30);
			tableMed.getTableHeader().setBackground(new Color(56, 56, 56));
			tableMed.getTableHeader().setForeground(new Color(255, 255, 255));
			tableMed.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
			
			JLabel descricaoLabel = new JLabel("Descrição: ");
			descricaoLabel.setFont(new Font("Dubai", Font.BOLD, 18));
			descricaoLabel.setBounds(458, 49, 171, 30);
			Receita.add(descricaoLabel);
			
			Receita receita = new ReceitaDAO().getOfConsulta(c.getId());
			descricao.setText(receita.getDescricao());
			DefaultTableModel modelMed = (DefaultTableModel) tableMed.getModel();
			for (Medicamentos m:receita.getMedicamentos()) {
				modelMed.addRow(new Object[] {m.getMedicamento()});
			}
			telas.add(Receita);
		}
		
		
		Diagnostico = new JPanel();
		Diagnostico.setBackground(new Color(255, 255, 255));
		Diagnostico.setBounds(0, 0, 875, 278);
		layeredPane.add(Diagnostico);
		Diagnostico.setLayout(null);
		
		JLabel tituloDiagnostico = new JLabel("Diagnostico:");
		tituloDiagnostico.setFont(new Font("Dubai", Font.BOLD, 30));
		tituloDiagnostico.setBounds(30, 10, 198, 39);
		Diagnostico.add(tituloDiagnostico);
		
		JTextPane diagnosticoText = new JTextPane();
		diagnosticoText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		diagnosticoText.setBounds(30, 59, 808, 181);
		Diagnostico.add(diagnosticoText);
		
		
		telas.add(Diagnostico);
		
		// ======================== PREENCHER DADOS ======================== //
		
		Paciente p = new PacienteDAO().get(c.getId_paciente());
		Medico m = new MedicoDAO().get(c.getId_medico());
		
		cpfPac.setText(p.getCPF());
		nomePac.setText(p.getNome());
		crmMed.setText(m.getCRM());
		nomeMed.setText(m.getNome());
		dataConsulta.setText(Data.toDateBrFormat(c.getDataConsulta()));
		horario.setText(c.getHorario().toString());
		valorConsulta.setText(Text.toMoney(c.getValor()));
		diagnosticoText.setText(c.getDiagnostico());

		
		// ======================== OPÇÕES ======================== //
		if (telas.size() > 1) {
			cont = 0;
			JButton btnProximo = new JButton("Próximo");
			btnProximo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cont += 1;
					if(cont==telas.size()){cont = 0;}
					SwitchScreen(telas.get(cont));
				}
			});
			btnProximo.setBackground(new Color(227, 227, 227));
			btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnProximo.setBounds(192, 630, 120, 40);
			contentPane.add(btnProximo);
			
			JButton btnAnterior = new JButton("Anterior");
			btnAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cont -= 1;
					if(cont==-1){cont = telas.size()-1;}
					SwitchScreen(telas.get(cont));
				}
			});
			btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAnterior.setBackground(new Color(227, 227, 227));
			btnAnterior.setBounds(57, 630, 120, 40);
			contentPane.add(btnAnterior);
		}
		
		//-------fundo-------
		JLabel background = new JLabel();
		background.setBounds(0, 0, 986, 713);
		background.setIcon(new ImageIcon(Index.class.getResource("/img/backgroud_1.png")));
		contentPane.add(background);
	}
	
	//---------------------------------------// MAIS INFO TRIAGEM //---------------------------------------//
	public MoreInfo(Triagem t) {
		setTitle("Mais Informação ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/img/logo.png")));
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel detalhe = new JPanel();
		detalhe.setOpaque(false);
		detalhe.setBounds(57, 0, 160, 40);
		contentPane.add(detalhe);
		detalhe.setLayout(null);
		
		JLabel tituloPaciente = new JLabel("Paciente:");
		tituloPaciente.setForeground(new Color(255, 255, 255));
		tituloPaciente.setBounds(20, 0, 119, 51);
		detalhe.add(tituloPaciente);
		tituloPaciente.setBackground(Color.DARK_GRAY);
		tituloPaciente.setFont(new Font("Dubai", Font.BOLD, 30));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.setBounds(57, 37, 875, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCPF = new JLabel("CPF :");
		lblCPF.setForeground(new Color(255, 255, 255));
		lblCPF.setFont(new Font("Dubai", Font.BOLD, 18));
		lblCPF.setBounds(38, 30, 66, 30);
		panel.add(lblCPF);
		
		cpfPac = new JLabel((String) null);
		cpfPac.setForeground(new Color(255, 255, 255));
		cpfPac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfPac.setBounds(80, 30, 242, 30);
		panel.add(cpfPac);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Dubai", Font.BOLD, 18));
		lblNome.setBounds(333, 30, 66, 30);
		panel.add(lblNome);
		
		nomePac = new JLabel((String) null);
		nomePac.setForeground(new Color(255, 255, 255));
		nomePac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomePac.setBounds(393, 30, 448, 30);
		panel.add(nomePac);
		
		JPanel detalhe_1 = new JPanel();
		detalhe_1.setOpaque(false);
		detalhe_1.setBounds(67, 124, 384, 40);
		contentPane.add(detalhe_1);
		detalhe_1.setLayout(null);
		
		JLabel tituloTecnico = new JLabel("Técnico(a) de Enfermagem:");
		tituloTecnico.setForeground(new Color(255, 255, 255));
		tituloTecnico.setBounds(10, 0, 364, 51);
		detalhe_1.add(tituloTecnico);
		tituloTecnico.setBackground(Color.DARK_GRAY);
		tituloTecnico.setFont(new Font("Dubai", Font.BOLD, 30));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel_1.setBounds(57, 162, 875, 82);
		contentPane.add(panel_1);
		panel.setLayout(null);
		panel_1.setLayout(null);
		
		JLabel lblCoren = new JLabel("COREN:");
		lblCoren.setForeground(new Color(255, 255, 255));
		lblCoren.setFont(new Font("Dubai", Font.BOLD, 18));
		lblCoren.setBounds(38, 30, 66, 30);
		panel_1.add(lblCoren);
		
		corenTec = new JLabel((String) null);
		corenTec.setForeground(new Color(255, 255, 255));
		corenTec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		corenTec.setBounds(102, 30, 227, 30);
		panel_1.add(corenTec);
		
		JLabel lblNomeTec = new JLabel("Nome :");
		lblNomeTec.setForeground(new Color(255, 255, 255));
		lblNomeTec.setFont(new Font("Dubai", Font.BOLD, 18));
		lblNomeTec.setBounds(333, 30, 66, 30);
		panel_1.add(lblNomeTec);
		
		nomeTec = new JLabel((String) null);
		nomeTec.setForeground(new Color(255, 255, 255));
		nomeTec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomeTec.setBounds(391, 30, 448, 30);
		panel_1.add(nomeTec);
		
		JLabel lblDataTriagem = new JLabel("Data:");
		lblDataTriagem.setForeground(new Color(255, 255, 255));
		lblDataTriagem.setFont(new Font("Dubai", Font.BOLD, 18));
		lblDataTriagem.setBounds(57, 263, 66, 30);
		contentPane.add(lblDataTriagem);
		
		dataTriagem = new JLabel((String) null);
		dataTriagem.setForeground(new Color(255, 255, 255));
		dataTriagem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dataTriagem.setBounds(103, 262, 180, 30);
		contentPane.add(dataTriagem);
		
		JLabel lblUrgencia = new JLabel("Urgência:");
		lblUrgencia.setForeground(new Color(255, 255, 255));
		lblUrgencia.setFont(new Font("Dubai", Font.BOLD, 18));
		lblUrgencia.setBounds(538, 263, 85, 30);
		contentPane.add(lblUrgencia);
		
		urgencia = new JLabel((String) null);
		urgencia.setForeground(new Color(255, 255, 255));
		urgencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		urgencia.setBounds(614, 262, 273, 30);
		contentPane.add(urgencia);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(57, 320, 875, 278);
		contentPane.add(layeredPane);
		
		// ======================== TELA DE INFOPAC ======================== //
		InfoPac = new JPanel();
		InfoPac.setBackground(new Color(255, 255, 255));
		InfoPac.setBounds(0, 0, 875, 278);
		layeredPane.add(InfoPac);
		InfoPac.setLayout(null);
		
		JLabel tituloInfoPac = new JLabel("Informações do Paciente:");
		tituloInfoPac.setFont(new Font("Dubai", Font.BOLD, 30));
		tituloInfoPac.setBounds(30, 10, 388, 39);
		InfoPac.add(tituloInfoPac);
		
		
		JLabel pesoLabel = new JLabel("Peso: ");
		pesoLabel.setFont(new Font("Dubai", Font.BOLD, 18));
		pesoLabel.setBounds(30, 101, 65, 30);
		InfoPac.add(pesoLabel);
		
		peso = new JLabel((String) null);
		peso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		peso.setBounds(86, 101, 180, 30);
		InfoPac.add(peso);
		
		JLabel temperaturaLabel = new JLabel("Temperatura: ");
		temperaturaLabel.setFont(new Font("Dubai", Font.BOLD, 18));
		temperaturaLabel.setBounds(30, 164, 120, 30);
		InfoPac.add(temperaturaLabel);
		
		temperatura = new JLabel((String) null);
		temperatura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		temperatura.setBounds(146, 164, 180, 30);
		InfoPac.add(temperatura);
		
		JLabel lblPressao = new JLabel("Pressao: ");
		lblPressao.setFont(new Font("Dubai", Font.BOLD, 18));
		lblPressao.setBounds(421, 101, 84, 30);
		InfoPac.add(lblPressao);
		
		JLabel lblStatusDaPressao = new JLabel("Status da Pressao: ");
		lblStatusDaPressao.setFont(new Font("Dubai", Font.BOLD, 18));
		lblStatusDaPressao.setBounds(421, 164, 163, 30);
		InfoPac.add(lblStatusDaPressao);
		
		pressao = new JLabel((String) null);
		pressao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pressao.setBounds(497, 101, 249, 30);
		InfoPac.add(pressao);
		
		statusPressao = new JLabel((String) null);
		statusPressao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		statusPressao.setBounds(580, 164, 249, 30);
		InfoPac.add(statusPressao);
		
		telas.add(InfoPac);

		
		// ======================== TELA RECEITA ======================== //
		
		if(new SintomasDAO().have(t)) {
			sintomas = new JPanel();
			sintomas.setBackground(new Color(255, 255, 255));
			sintomas.setBounds(0, 0, 875, 278);
			sintomas.setLayout(null);
			
			JLabel tituloSintomas = new JLabel("Sintomas:");
			tituloSintomas.setFont(new Font("Dubai", Font.BOLD, 30));
			tituloSintomas.setBounds(30, 10, 198, 39);
			sintomas.add(tituloSintomas);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(30, 80, 816, 170);
			sintomas.add(scrollPane_2);
			
			tableSin = new JTable();
			scrollPane_2.setViewportView(tableSin);
			tableSin.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableSin.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Sintomas"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tableSin.setRowHeight(30);
			tableSin.getTableHeader().setBackground(new Color(56, 56, 56));
			tableSin.getTableHeader().setForeground(new Color(255, 255, 255));
			tableSin.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
			
			
			DefaultTableModel modelSin = (DefaultTableModel) tableSin.getModel();
			for (Sintomas s:t.getSintomas()) {
				modelSin.addRow(new Object[] {s.getSintoma()});
			}
			telas.add(sintomas);
			
			
			
		}
		
		// ======================== PREENCHER DADOS ======================== //
		
		Paciente p = new PacienteDAO().get(t.getId_paciente());
		Tecnico_de_Enfermagem enf = new Tecnico_de_EnfermagemDAO().get(t.getId_tecnico());
		
		cpfPac.setText(p.getCPF());
		nomePac.setText(p.getNome());
		corenTec.setText(enf.getCoren());
		nomeTec.setText(enf.getNome());
		dataTriagem.setText(Data.toDateBrFormat(t.getDataTriagem()));
		urgencia.setText(t.getUrgencia());
		peso.setText(t.getPeso().toString() + " Kg");
		temperatura.setText(t.getTemperatura().toString() + " ºC");
		pressao.setText(t.getPressao().getPressaoNomeada());
		statusPressao.setText(t.getPressao().getCategoria());

		
		// ======================== OPÇÕES ======================== //
		if (telas.size() > 1) {
			cont = 0;
			JButton btnProximo = new JButton("Próximo");
			btnProximo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cont += 1;
					if(cont==telas.size()){cont = 0;}
					SwitchScreen(telas.get(cont));
				}
			});
			btnProximo.setBackground(new Color(227, 227, 227));
			btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnProximo.setBounds(192, 630, 120, 40);
			contentPane.add(btnProximo);
			
			JButton btnAnterior = new JButton("Anterior");
			btnAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cont -= 1;
					if(cont==-1){cont = telas.size()-1;}
					SwitchScreen(telas.get(cont));
				}
			});
			btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAnterior.setBackground(new Color(227, 227, 227));
			btnAnterior.setBounds(57, 630, 120, 40);
			contentPane.add(btnAnterior);
		}
		
		//-------fundo-------
		JLabel background = new JLabel();
		background.setBounds(0, 0, 986, 713);
		background.setIcon(new ImageIcon(Index.class.getResource("/img/backgroud_1.png")));
		contentPane.add(background);
	}
	
	
	// ======================== TROCA DE TELAS ======================== //
	
	private void SwitchScreen(JPanel pane) {
		layeredPane.removeAll();
		layeredPane.add(pane);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
}
