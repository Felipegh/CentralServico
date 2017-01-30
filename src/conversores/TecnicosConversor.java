package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

//import modelo.ISetor;
import modelo.ITecnico;
import util.Repositorios;
import entidades.Setor;
import entidades.Tecnico;



@FacesConverter(forClass=Tecnico.class)
public class TecnicosConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Tecnico retorno = null;
		ITecnico tecnicos = repositorios.getTecnico();
		if (value != null && !value.equals("")) {
			retorno = tecnicos.porCodigo(new Integer(value));
		if (retorno == null) {
			String descricaoErro = "Estado não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ConverterException(message);
		}

		} 

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Integer codigo = ((Tecnico) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}