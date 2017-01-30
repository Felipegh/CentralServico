package testes;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.mo.ServiceInstance;

public class SampleUtil {

	public static ServiceInstance createServiceInstance() throws RemoteException, MalformedURLException {
		return new ServiceInstance(new URL("https://vcenter.info.ufrn.br/sdk/vimService"), "felipe.medeiros", "fe@me83", true);
	}

}