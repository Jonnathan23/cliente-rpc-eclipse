package rcp_cliente.cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import interface_service.HelloService;

public class Cliente {
	
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable task = () ->{
			try {
				// Localizar el registro RMI en el servidor (puerto 1099)
				Registry registry = LocateRegistry.getRegistry("localhost", 8081);
				
				// Buscar el servicio en el registro
				HelloService service = (HelloService) registry.lookup("Registro");
				
				// Llamar al método remoto
				System.out.println(" |----------------| |Cliente Creado | |----------------| |");
				String response = service.sayHello("Jonnathan");
				System.out.println("Respuesta del servidor: " + response);
			} catch (Exception e) {
				System.out.println("|----------------| | ERROR CLIENTE | |----------------| |");
				e.printStackTrace();
			}			
		};
		
		scheduler.schedule(task, 2, TimeUnit.SECONDS);
	}
	
}
