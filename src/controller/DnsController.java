package controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.IDns;
import modelo.ITecnico;
import entidades.Dns;
import util.Repositorios;

/** Classe EquipeController responsavel por unir as views referentes da classe equipe.
*
* @author Felipe 
*
*/

@SuppressWarnings("serial")
@ManagedBean(name="cadastroDnsBean")
@RequestScoped
public class DnsController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Dns dns = new Dns();
	//private List<Equipe> equipes = new ArrayList<Equipe>();
	IDns name = this.repositorios.getDns();
	
	public Dns getDns() {
		return dns;
	}
	public void setDns(Dns dns) {
		this.dns = dns;
	}
	public Repositorios getRepositorios() {
		return repositorios;
	}
	public void setRepositorios(Repositorios repositorios) {
		this.repositorios = repositorios;
	}

	
	
	

}