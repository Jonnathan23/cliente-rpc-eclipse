package rcp_cliente.cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;


import interface_service.HelloService;

public class Connection {

	private Registry registry;
	private HelloService service;
	private String messageError = "|----------------| | ERROR CLIENTE | |----------------| |";
	

	public Connection() {
		try {
			registry = LocateRegistry.getRegistry("localhost", 8081);
			service = (HelloService) registry.lookup("Registro");
		} catch (Exception e) {
			// TODO: handle exception			
		}
	}

	public String registryClient(String user) {
		try {	
			String response = service.sayHello(user);

			return response;

		} catch (Exception e) {
			System.out.println(messageError);
			e.printStackTrace();
			return null;

		}
	}

	public String signUpUser(Map<String, String> user) {
		try {
			
			String response = service.signUp(user);

			return response;
		} catch (Exception e) {
			System.out.println(messageError);
			return null;
		}
	}

	public List<Map<String, String>> listAllUsers(){
		try {
			List<Map<String, String>> listUsers =  service.listAllUsers();
			
			return listUsers;
			
		} catch (Exception e) {			
			System.out.println(messageError);
			return null;
		}
	}

}
