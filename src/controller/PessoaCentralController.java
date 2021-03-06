package controller;

import javax.faces.bean.ManagedBean;



import entidades.PessoaCentral;
import modelo.PessoaCentralDAO;
import modelo.PessoaCentralDAOImpl;

@ManagedBean(name="PessoaCentralBean")
public class PessoaCentralController {
	PessoaCentral pessoaCentral = new PessoaCentral();
	PessoaCentralDAO pessoaCentralDAO = new PessoaCentralDAOImpl();

	public String logar(){
		if (pessoaCentralDAO.login(pessoaCentral) == true) {
			return "body/central.xhtml?faces-redirect=true";
		}
		return  "login.xhtml?faces-redirect=true";
	}

	public String logout(){
		pessoaCentralDAO.logout();
		//return "http://snmp.info.ufrn.br:8080/centralServico/index.xhtml?faces-redirect=true";
		return "/index.xhtml?faces-redirect=true";
	}

	public PessoaCentral getPessoaCentral() {
		return pessoaCentral;
	}

	public void setPessoaCentral(PessoaCentral pessoaCentral) {
		this.pessoaCentral = pessoaCentral;
	}

	public PessoaCentralDAO getPessoaCentralDAO() {
		return pessoaCentralDAO;
	}

	public void setPessoaCentralDAO(PessoaCentralDAO pessoaCentralDAO) {
		this.pessoaCentralDAO = pessoaCentralDAO;
	}

}