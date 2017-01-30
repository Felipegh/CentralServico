package controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

import com.novell.ldap.LDAPException;

import modelo.PessoaContainerDAO;
import modelo.PessoaContainerDAOImpl;
import modelo.PessoaWifiDAO;
import modelo.PessoaWifiDAOImpl;
import entidades.PessoaContainer;
import entidades.PessoaWifi;


@ManagedBean(name="PessoaContainerBean")
public class PessoaContainerController implements Serializable{
	PessoaContainer pessoaContainer = new PessoaContainer();
	ArrayList<PessoaContainer> pessoasContainer = new ArrayList<PessoaContainer>();
	PessoaContainerDAO pessoaContainerDAO = new PessoaContainerDAOImpl();

	public String logar(){
		if (pessoaContainerDAO.login(pessoaContainer) == true) {
			return "body/container.xhtml?faces-redirect=true";
		}
		return "login.xhtml?faces-redirect=true";
	}

	public String logout() throws LDAPException, IOException{
		pessoaContainerDAO.logout();
		//return "http://snmp.info.ufrn.br:8080/centralServico/index.xhtml?faces-redirect=true";
		return "/index.xhtml?faces-redirect=true";
	}

	public String MigrarUsuario() throws UnsupportedEncodingException{
			PessoaWifiDAO pessoaWifiDAO = new PessoaWifiDAOImpl();
			pessoaContainerDAO.migrate(pessoaContainer);
			return "container.xhtml?faces-redirect=true";
	}

	public PessoaContainer getPessoaContainer() {
		return pessoaContainer;
	}

	public void setPessoaContainer(PessoaContainer pessoaContainer) {
		this.pessoaContainer = pessoaContainer;
	}

	public ArrayList<PessoaContainer> getPessoasContainer() {
		return pessoasContainer;
	}

	public void setPessoasContainer(ArrayList<PessoaContainer> pessoasContainer) {
		this.pessoasContainer = pessoasContainer;
	}
}
