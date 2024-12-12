package rcp_cliente.cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interface_service.HelloService;

public class Connection {
	
	String response;
	
	public String registryClient (String user) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 8081);
			HelloService service = (HelloService) registry.lookup("Registro");
			
			this.response = service.sayHello(user);
			
			return response;
			
		} catch (Exception e) {
			System.out.println("|----------------| | ERROR CLIENTE | |----------------| |");
			e.printStackTrace();
			return null;
			
		}
	}
	
	public String getString(){
		return this.response;
	}
	
	
}
