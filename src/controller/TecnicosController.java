package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.ITecnico;
import util.Repositorios;
import entidades.Tecnico;


@ManagedBean(name="cadastroTecnicosBean")
@RequestScoped
public class TecnicosController implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Tecnico tecnico = new Tecnico();
	private List<Tecnico> tecnicos = new ArrayList<Tecnico>();
	

	@PostConstruct
	public void init(){
		ITecnico tecnicos = this.repositorios.getTecnico();
		this.tecnicos = tecnicos.listar();
	}

	
	public void cadastrar(){
		ITecnico tecnicos = this.repositorios.getTecnico();
		tecnicos.salvar(tecnico);
		this.tecnico = new Tecnico();
			System.out.println("testando salvar" + tecnico);
		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Tecnico tecnico){
		ITecnico tecnicos = this.repositorios.getTecnico();
		tecnicos.editar(tecnico);
	}

	public void excluir(Tecnico tecnico){
		ITecnico tecnicos = this.repositorios.getTecnico();
		tecnicos.remover(tecnico);
		this.init();
	}


	public Tecnico getTecnico() {
		return tecnico;
	}


	public void setTecnico(Tecnico tecnico) throws CloneNotSupportedException {
		this.tecnico = tecnico;
		if (this.tecnico == null) {
			this.tecnico = new Tecnico();
		}else{
			this.tecnico = (Tecnico) tecnico.clone();
		}
	}


	public List<Tecnico> getTecnicos() {
		return tecnicos;
	}


	public void setSetores(List<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}
}