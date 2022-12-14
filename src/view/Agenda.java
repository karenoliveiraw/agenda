package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Agenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JLabel lblFone;
	private JTextField txtFone;
	private JLabel lblEmail;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor
	 */
	public Agenda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setResizable(false);
		setFont(new Font("Dialog", Font.BOLD, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Agenda.class.getResource("/img/agenda.png")));
		setTitle("Agenda de Contatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(321, 47, 46, 14);
		contentPane.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtId.setBackground(new Color(0, 206, 209));
		txtId.setForeground(new Color(255, 255, 255));
		txtId.setBounds(341, 44, 57, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(10, 47, 46, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setForeground(new Color(255, 255, 255));
		txtNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNome.setBackground(new Color(0, 206, 209));
		txtNome.setColumns(10);
		txtNome.setBounds(65, 44, 194, 20);
		contentPane.add(txtNome);

		lblFone = new JLabel("Fone");
		lblFone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFone.setBounds(10, 89, 46, 14);
		contentPane.add(lblFone);

		txtFone = new JTextField();
		txtFone.setForeground(new Color(255, 255, 255));
		txtFone.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFone.setBackground(new Color(0, 206, 209));
		txtFone.setColumns(10);
		txtFone.setBounds(65, 86, 155, 20);
		contentPane.add(txtFone);

		lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 128, 46, 14);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(255, 255, 255));
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtEmail.setBackground(new Color(0, 206, 209));
		txtEmail.setColumns(10);
		txtEmail.setBounds(65, 125, 194, 20);
		contentPane.add(txtEmail);

		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnDelete.setIcon(new ImageIcon(Agenda.class.getResource("/img/delete.png")));
		btnDelete.setToolTipText("Deletar contato");
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBounds(286, 177, 81, 73);
		contentPane.add(btnDelete);

		btnUpdate = new JButton("");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarContato();
			}
		});
		btnUpdate.setIcon(new ImageIcon(Agenda.class.getResource("/img/update.png")));
		btnUpdate.setToolTipText("Atualizar contato");
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBounds(195, 177, 64, 64);
		contentPane.add(btnUpdate);

		btnRead = new JButton("");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarContato();
			}
		});
		btnRead.setIcon(new ImageIcon(Agenda.class.getResource("/img/search32x32.png")));
		btnRead.setToolTipText("Pesquisar ");
		btnRead.setContentAreaFilled(false);
		btnRead.setBorderPainted(false);
		btnRead.setBounds(269, 37, 32, 32);
		contentPane.add(btnRead);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(8, 200, 48, 48);
		contentPane.add(lblStatus);
		// uso da tecla <enter> junto com um bot??o
		getRootPane().setDefaultButton(btnRead);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});
		btnCreate.setIcon(new ImageIcon(Agenda.class.getResource("/img/add.png")));
		btnCreate.setToolTipText("Adicionar contato");
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setBounds(99, 177, 64, 64);
		contentPane.add(btnCreate);

		// Uso da biblioteca Atxy2k
		RestrictedTextField validarFone = new RestrictedTextField(txtFone, "0123456789-()");
		validarFone.setLimit(15);

		RestrictedTextField validarNome = new RestrictedTextField(txtNome);
		validarNome.setOnlyText(true);
		validarNome.setAcceptSpace(true);
		validarNome.setLimit(50);

		RestrictedTextField validarEmail = new RestrictedTextField(txtEmail);
		validarEmail.setLimit(50);

	}// fim do construtor

	/**
	 * criar um objeto para acessar o metodo conectar () da classe DAO
	 */

	DAO dao = new DAO();
	private JLabel lblStatus;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnRead;

	/**
	 * M??todo respons??vel por verificar o status da conex??o com o banco
	 */
	private void status() {
		// System.out.println("teste - Janela Ativada");
		// uso da classe Connection (JDBC) para estabelecer a conex??o
		try {
			Connection con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conex??o");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
			} else {
				// System.out.println("Banco conectado!");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dbon.png")));
			}
			// Nunca esquecer de encerrar a conex??o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do status

	/**
	 * M??todo respons??vel pela pesquisa(select) de um contato no banco
	 */
	private void pesquisarContato() {
		// valida????o
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do contato");
			txtNome.requestFocus();
		} else {
			// System.out.println("Teste pesquisar");
			// Iniciar com a instru????o sql
			// ? ?? um par??metro a ser substitu??do
			String read = "select * from contatos where nome = ?";
			try {
				// Estabelecer a conex??o ("abrir a porta da geladeira")
				Connection con = dao.conectar();
				// Preparar o c??digo sql para execu????o
				PreparedStatement pst = con.prepareStatement(read);
				// a linha abaixo substitui o ? pelo conte??do da caixa de texto txtNome, o 1 faz
				// refer??ncia a interroga????o
				pst.setString(1, txtNome.getText());
				// Obter os dados do contato (resultado)
				ResultSet rs = pst.executeQuery();
				// verificar se existe um contato cadastrado
				// rs.next() significa ter um contato correspondente ao nome pesquisado
				if (rs.next()) {
					// setar as caixas de texto com o resultado da pesquisa
					txtId.setText(rs.getString(1));
					txtFone.setText(rs.getString(3));
					txtEmail.setText(rs.getString(4));
					// habilitar bot??es (alterar e excluir)
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Contato inexistente");
					// setar campos e bot??es (UX)
					txtFone.setText(null);
					txtEmail.setText(null);
					txtFone.requestFocus();
					btnCreate.setEnabled(true);
					btnRead.setEnabled(false);
				}
				// fechar a conex??o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M??todo respons??vel pelo cadastro de um novo contato
	 */
	void adicionarContato() {
		// valida????o de campos obrigat??rios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o fone");
			txtFone.requestFocus();
		} else {
			// System.out.println("teste adicionar");
			String create = "insert into contatos (nome,fone,email) values (?,?,?)";
			try {
				// Abrir a conex??o
				Connection con = dao.conectar();
				// Preparar a query (substitui????o de par??metros)
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				// Executar a query e confirmar a inser????o no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato adicionado.");
					limpar();
				}
				// Encerrar a conex??o
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M??todo respons??vel pela edi????o do contato
	 */
	private void alterarContato() {
		// System.out.println("teste do bot??o Update");
		// valida????o de campos obrigat??rios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o fone");
			txtFone.requestFocus();
		} else {
			// l??gica principal
			String update = "update contatos set nome = ?, fone = ?, email = ? where id = ?";
			try {
				// abrir a conex??o
				Connection con = dao.conectar();
				// preparar a query (instru????o sql)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtId.getText());
				// Executar a query e confirmar as altera????es no banco
				int confirma = pst.executeUpdate();
				// System.out.println(confirma);
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do contato atualizados com sucesso");
					limpar();
				}
				// Encerrar a conex??o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M??todo usado para excluir um contato
	 */
	private void excluirContato() {
		// System.out.println("excluir contato");

		// valida????o (confirma????o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus??o deste contato?", "ATEN????O!",
				JOptionPane.YES_NO_OPTION);

		if (confirma == JOptionPane.YES_OPTION) {
			// logica principal
			String delete = "delete from contatos where id = ?";
			try {
				// abrir conex??o

				Connection con = dao.conectar();
				// preparar a query

				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				// executar o comando sql e confirmar a exclus??o
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					JOptionPane.showMessageDialog(null, "Contato excluido com sucesso!");
					limpar ();
				}
				// encerrar a conex??o
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		} 

	}

	/**
	 * M??todo usado para limpar os campos e setar os bot??es
	 */
	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtNome.requestFocus();
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);
	}
}
// fim do c??digo
