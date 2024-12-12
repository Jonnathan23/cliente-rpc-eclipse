package rcp_cliente.cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//import Model.UserModel;
import interface_service.HelloService;

public class Cliente {

	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
			try {
				// Localizar el registro RMI en el servidor (puerto 1099)
				Registry registry = LocateRegistry.getRegistry("localhost", 8081);

				// Buscar el servicio en el registro
				HelloService service = (HelloService) registry.lookup("Registro");

				Map<String, String> userData = new HashMap<>();
				userData.put("us_name", "Pedro");
				userData.put("us_email", "pedroger@gmail.com");
				userData.put("us_password", "root_123");
				// UserModel newUser = new UserModel(name, email, password);

				// String response = service.signUp(newUser);
				String response = service.signUp(userData);

				// Llamar al método remoto
				System.out.println(" |----------------| |Cliente Creado | |----------------| |");
				String message = service.sayHello("Jonnathan");
				System.out.println("Respuesta del servidor: " + message);
				System.out.println("Respuesta del servidor: " + response);

				System.out.println(" |----------------| | Lista de Usuarios | |----------------| |");

				List<Map<String, String>> users = service.listAllUsers();
				if (users != null) {
					for (Map<String, String> user : users) {
						System.out.println("name: " + user.get("us_name") + ", email: " + user.get("us_email")
								+ ", password: " + user.get("us_password"));
					}
				} else {
					System.out.println("Sin usuarios");
				}

				System.out.println(" |----------------| | x | |----------------| |");
			} catch (Exception e) {
				System.out.println("|----------------| | ERROR CLIENTE | |----------------| |");
				e.printStackTrace();
			}
		};

		scheduler.schedule(task, 2, TimeUnit.SECONDS);
	}

}
