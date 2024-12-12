package rcp_cliente.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;

public class frontend extends JFrame {

	private Connection connectionServer = new Connection();

	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTable tbClientes;
	private JLabel lbSignUp;
	private JButton btListar;
	private JLabel lbResponse;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private DefaultTableModel tableModel;

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
		panel.setBounds(189, 53, 373, 295);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btRegistrar = new JButton("Registrarse");
		btRegistrar.setForeground(Color.BLUE);
		btRegistrar.setFont(new Font("Cascadia Code", Font.BOLD, 11));
		btRegistrar.setBackground(new Color(0, 0, 160));

		btRegistrar.setBounds(132, 243, 130, 42);
		panel.add(btRegistrar);

		txtName = new JTextField();
		txtName.setForeground(new Color(39, 33, 92));
		txtName.setBackground(new Color(147, 139, 214));
		txtName.setBounds(112, 32, 226, 42);
		panel.add(txtName);
		txtName.setColumns(10);

		lbSignUp = new JLabel(" ");
		lbSignUp.setBounds(71, 213, 252, 13);
		panel.add(lbSignUp);

		lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(21, 97, 65, 42);
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(21, 46, 65, 42);
		panel.add(lblNewLabel_1);

		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(39, 33, 92));
		txtEmail.setColumns(10);
		txtEmail.setBackground(new Color(147, 139, 214));
		txtEmail.setBounds(112, 97, 226, 42);
		panel.add(txtEmail);

		txtPassword = new JTextField();
		txtPassword.setForeground(new Color(39, 33, 92));
		txtPassword.setColumns(10);
		txtPassword.setBackground(new Color(147, 139, 214));
		txtPassword.setBounds(112, 161, 226, 42);
		panel.add(txtPassword);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(21, 161, 65, 42);
		panel.add(lblContrasea);

		String[] columnNames = { "Nombre", "Correo" };
		tableModel = new DefaultTableModel(columnNames, 0);
		tableModel.addRow(new Object[]{"Nombre", "Correo"});

		tbClientes = new JTable(tableModel);
		tbClientes.setBounds(78, 450, 373, 57);

		contentPane.add(tbClientes);

		btListar = new JButton("Listar");

		btListar.setBounds(75, 402, 98, 38);
		contentPane.add(btListar);

		lbResponse = new JLabel(" ");
		lbResponse.setBounds(199, 379, 363, 43);
		contentPane.add(lbResponse);

		btRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbSignUp.setText("Cargando...");
				String name = txtName.getText();
				String email = txtEmail.getText();
				String password = txtPassword.getText();

				Map<String, String> newUser = new HashMap<>();
				newUser.put("us_name", name);
				newUser.put("us_email", email);
				newUser.put("us_password", password);

				if (name != null) {
					String response = connectionServer.registryClient(name);
					String responseCreate = connectionServer.signUpUser(newUser);
					lbSignUp.setText(response);
					lbResponse.setText(responseCreate);

					Runnable task = () -> {
						lbResponse.setText(" ");
						lbSignUp.setText(" ");
					};

					scheduler.schedule(task, 3, TimeUnit.SECONDS);

				} else {
					lbSignUp.setText("Ocurrio un error...");
				}

			}
		});

		btListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Map<String, String>> users = connectionServer.listAllUsers();
				if (users != null) {
					for (Map<String, String> user : users) {
						tableModel.addRow(new Object[]{user.get("us_name"), user.get("us_email")});
					}
				}
			}
		});
	}
}
