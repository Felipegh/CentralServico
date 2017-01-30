package testes;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.HostHardwareInfo;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.VirtualDevice;
import com.vmware.vim25.VirtualEthernetCard;
import com.vmware.vim25.mo.HostDatastoreSystem;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
//import com.vmware.vim25.mo.ResourcePool;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;


public class BuscaVM {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String url = "https://vcenter.info.ufrn.br/sdk/vimService";
	    System.out.println("In main function");
	    
	ServiceInstance si = null;
	ManagedEntity[] managedEntities = null;
	ManagedEntity[] hostmanagedEntities = null;
	
	
	try {
		System.out.println("try");
	String user = "felipe.medeiros";
	String pass = "fe@me83";
	
	
	si = new ServiceInstance(new URL(url), user, pass,true);
	System.out.println("conected");
	
	hostmanagedEntities = new InventoryNavigator(
	si.getRootFolder()).searchManagedEntities("HostSystem");

	//process each host and call getVMDetails function to get details of all host       
	for (ManagedEntity managedEntity : hostmanagedEntities) {
	HostSystem hostsystemobj = (HostSystem) managedEntity;
	
	System.out.println("Host: '" + hostsystemobj.getName() + "'");
	
	String hostname = hostsystemobj.getName();
	String ESXhostname = hostsystemobj.getName();
	HostHardwareInfo hw = hostsystemobj.getHardware();
	String ESXhostmodel = hw.getSystemInfo().getModel();
	String Vendor = hw.getSystemInfo().getVendor();
	String Modelo = hw.getSystemInfo().getModel();
	HostDatastoreSystem Dtstore = hostsystemobj.getHostDatastoreSystem();
	//print all above variables. 
	System.out.println("Datastore: " + Dtstore);
	System.out.println("Modelo: " + Modelo);
	long ESXhostmem = hw.getMemorySize();

	short ESXhostcores = hw.getCpuInfo().getNumCpuCores();
	long ESXMHZ = hw.getCpuInfo().getHz();

	
	  //call function that collects vms data for specific host
	getVMDetailsInv(si,hostsystemobj.getName());

	}
	}
	catch (InvalidProperty e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (RuntimeFault e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	}	


	@SuppressWarnings({ "unused" })
	private static void getVMDetailsInv(ServiceInstance si,String name) {

	HostSystem myhost;
	System.out.println("host name from details function  is .. " + name);
	
	try {
		
	myhost = (HostSystem) new        InventoryNavigator(si.getRootFolder()).searchManagedEntity("HostSystem",name);
	System.out.println("myhost is .. " + myhost);
	ManagedEntity[] hostSpecificEntities = new InventoryNavigator(myhost).searchManagedEntities("VirtualMachine");

	for (int i = 0; i < hostSpecificEntities.length; i++) {
	    VirtualMachine vm = (VirtualMachine) hostSpecificEntities[i];
	    String macAddress="";
	    for(VirtualDevice vd:vm.getConfig().getHardware().getDevice()){
	    	
	    try {
	    VirtualEthernetCard vEth=(VirtualEthernetCard)vd;
	     macAddress=vEth.macAddress;
	    }
	    catch(Exception e){}
	    }
	    
	System.out.println("Nome: "+vm.getName());
	String vmVersion = vm.getConfig().version;
	System.out.println("Versão vm wayer: "+ vm.getConfig().version);
	System.out.println("guest os uuid "+vm.getSummary().getConfig().uuid);
	System.out.println("Mac Address:  "+macAddress);
	System.out.println("SO: "+vm.getSummary().getGuest().guestId);
	System.out.println("IP: "+vm.getSummary().getGuest().getIpAddress());  
	System.out.println("Estado: "+vm.getGuest().guestState);
	System.out.println("LUN: "+vm.getSummary().config.getVmPathName());
	System.out.println("Memória RAM: "+vm.getSummary().config.getMemorySizeMB());
	System.out.println("Placas de Rede: "+vm.getSummary().config.getNumEthernetCards());
	System.out.println("Sumário do Storage : "+vm.getSummary().getStorage().committed);
	 
	
	
	System.out.println("                                              ");   
	    }
	    } catch (RuntimeFault e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	}
	
	
}
