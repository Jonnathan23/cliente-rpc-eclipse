package rcp_cliente.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;

public class frontend extends JFrame {
	
	private Connection connectionServer = new Connection();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTable tbClientes;
	private JLabel lbSignUp;
	JButton btListar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frontend frame = new frontend();
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
	public frontend() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 777);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 238, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(80, 68, 187)));
		panel.setBackground(new Color(80, 68, 187));
		panel.setBounds(189, 53, 373, 242);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btRegistrar = new JButton("Registrarse");
		btRegistrar.setForeground(Color.BLUE);
		btRegistrar.setFont(new Font("Cascadia Code", Font.BOLD, 11));
		btRegistrar.setBackground(new Color(0, 0, 160));
		
		
		
		
		btRegistrar.setBounds(110, 159, 130, 42);
		panel.add(btRegistrar);
		
		txtName = new JTextField();
		txtName.setForeground(new Color(39, 33, 92));
		txtName.setBackground(new Color(147, 139, 214));
		txtName.setBounds(66, 60, 226, 42);
		panel.add(txtName);
		txtName.setColumns(10);
		
		lbSignUp = new JLabel(" ");
		lbSignUp.setBounds(40, 126, 252, 13);
		panel.add(lbSignUp);
		
		tbClientes = new JTable();
		tbClientes.setBounds(76, 383, 373, 57);
		contentPane.add(tbClientes);
		
		JButton btListar = new JButton("Listar");
		btListar.setBounds(76, 333, 98, 38);
		contentPane.add(btListar);
		
		
		btRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbSignUp.setText("Cargando...");
				String name = txtName.getText();
				
				if(name != null) {
					String response = connectionServer.registryClient(name);
					lbSignUp.setText(response);
				}else {
					lbSignUp.setText("Ocurrio un error...");
				}
				
			}
		});
	}
}
