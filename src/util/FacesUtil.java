package util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtil {

	public void adicionarMensagem(Severity tipo, String msg) {
		FacesMessage facesMessage = new FacesMessage(tipo, msg, msg);
		
		
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage); // mensagem global
	}
	
	public static Object getRequestAttribute(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}
	
}
