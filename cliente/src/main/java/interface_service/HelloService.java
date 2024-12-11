package interface_service;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface HelloService extends Remote {
	String sayHello(String name) throws RemoteException;
}
