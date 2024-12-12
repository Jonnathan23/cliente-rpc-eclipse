package interface_service;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

//import Model.UserModel;

import java.rmi.Remote;

public interface HelloService extends Remote {
	String sayHello(String name) throws RemoteException;
	
	//String signUp(UserModel user) throws RemoteException;
	 String signUp(Map<String, String> userData) throws RemoteException;
	
	//List<UserModel> listAllUsers()throws RemoteException;
	 List<Map<String, String>> listAllUsers() throws RemoteException;
}
