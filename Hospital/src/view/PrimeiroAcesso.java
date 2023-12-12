package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PacienteDAO;
import dao.SenhaAdmDAO;

import javax.swing.JPasswordField;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.JDesktopPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrimeiroAcesso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeiroAcesso frame = new PrimeiroAcesso();
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
	public PrimeiroAcesso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Index.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		layeredPane.setBounds(0, 0, 436, 263);
		contentPane.add(layeredPane);
		
		SwitchScreen(inicio());
		
		
	}
	
	private void SwitchScreen(JPanel pane) {
		layeredPane.removeAll();
		layeredPane.add(pane);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	private JPanel inicio() {
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 436, 263);
		Screen.setBackground(new Color(255,255,255));
		layeredPane.add(Screen);
		Screen.setLayout(null);
		
		JLabel txt = new JLabel("Seja Bem-Vindo ao ProClinic SGM!");
		txt.setFont(new Font("Dubai", Font.PLAIN, 25));
		txt.setBounds(10, 10, 416, 27);
		Screen.add(txt);
		
		JButton btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchScreen(senhaADM());
			}
		});
		btnProximo.setBackground(new Color(227, 227, 227));
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProximo.setBounds(294, 205, 117, 34);
		Screen.add(btnProximo);
		
		JTextPane txtpnOSgm = new JTextPane();
		txtpnOSgm.setFont(new Font("Dubai", Font.PLAIN, 14));
		txtpnOSgm.setText("O SGM é o melhor sistema para cadastro, edição e documentação dos dados de sua clínica. Com total sigilo e transparência ele compila os dados de todos funcionários e pacientes em um só lugar, tornando a rotina de seus usuários mais prática!");
		txtpnOSgm.setBounds(10, 64, 400, 112);
		Screen.add(txtpnOSgm);
		
		return Screen;
	}
	
	private JPanel senhaADM(){
		JPanel Screen = new JPanel();
		Screen.setBounds(0, 0, 436, 263);
		Screen.setBackground(new Color(255,255,255));
		layeredPane.add(Screen);
		Screen.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(27, 130, 384, 32);
		Screen.add(passwordField);
		
		JButton btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (passwordField.getText().length()<8) {throw new Exception("A senha deve ter ao meno 8 digitos!");}
					if (new SenhaAdmDAO().create(passwordField.getText())==1) {
						SwitchScreen(concluido());
					} else {
						throw new Exception("Erro ao Cadastrar a senha!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnProximo.setBackground(new Color(227, 227, 227));
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProximo.setBounds(294, 205, 117, 34);
		Screen.add(btnProximo);
		
		JTextPane txt = new JTextPane();
		txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt.setText("Agora vamos criar a sua senha de administrador! Ela será usada por todos os funcionários com nivel de administração superior!");
		txt.setBounds(27, 35, 384, 65);
		txt.setOpaque(true);
		Screen.add(txt);
		
		return Screen;
		
		
	}
	
	private JPanel concluido(){
		JPanel Screen = new JPanel();
		Screen.setBackground(new Color(255, 255, 255));
		Screen.setBounds(0, 0, 436, 263);
		layeredPane.add(Screen);
		Screen.setLayout(null);
		
		JLabel txt = new JLabel("Configuração Concluída!");
		txt.setFont(new Font("Dubai", Font.PLAIN, 25));
		txt.setBounds(10, 25, 416, 27);
		Screen.add(txt);
		
		JTextPane txt_2 = new JTextPane();
		txt_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_2.setText("Agora está tudo Pronto! Vamos começar?");
		txt_2.setBounds(10, 83, 416, 75);
		Screen.add(txt_2);
		
		JButton btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Index tela = new Index();
				tela.setVisible(true);
				dispose();
			}
		});
		btnProximo.setBackground(new Color(227, 227, 227));
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProximo.setBounds(294, 205, 117, 34);
		Screen.add(btnProximo);
		
		return Screen;
	}
}
