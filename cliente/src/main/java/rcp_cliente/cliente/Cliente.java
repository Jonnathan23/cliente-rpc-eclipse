package rcp_cliente.cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import service.HelloService;

@Singleton
@Startup
public class Cliente {
	/*
	@PostConstruct
	public void init() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		Runnable task = () -> {
			try {
	            // Localizar el registro RMI en el servidor (puerto 1099)
	            Registry registry = LocateRegistry.getRegistry("localhost", 8081);

	            // Buscar el servicio en el registro
	            HelloService service = (HelloService) registry.lookup("HelloService");

	            // Llamar al método remoto
	            System.out.println(" |----------------| |Cliente Creado | |----------------| |");
	            String response = service.sayHello("Jonnathan Saquicela");
	            System.out.println(" |------------------------------------| -|Respuesta del servidor: |- |------------------------------------|\n " + response);
	        } catch (Exception e) {
	        	System.out.println("|----------------| | ERROR CLIENTE | |----------------| |");
	            e.printStackTrace();
	        }
		};
		
		scheduler.schedule(task, 2, TimeUnit.SECONDS);
	}
*/

	
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
				String response = service.sayHello("Mundo");
				System.out.println("Respuesta del servidor: " + response);
			} catch (Exception e) {
				System.out.println("|----------------| | ERROR CLIENTE | |----------------| |");
				e.printStackTrace();
			}			
		};
		
		scheduler.schedule(task, 2, TimeUnit.SECONDS);
	}
	
}