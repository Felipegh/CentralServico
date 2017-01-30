package modelo;

import java.util.List;

import entidades.Dns;
import entidades.Equipe;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import modelo.IEquipe;

/** Classe EquipeImpl que implementa a Interface das IEquipe, esta classe implementa crud de equipe..
*
* @author Felipe
*
*/

public class DnsImpl implements IDns{
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public DnsImpl(Session sessao){
		this.sessao = sessao;
	}
	
	

	/** Metodo que atualiza equipe.
	 * @param equipe, requer objeto equipe para sua atualização.
	 */
	@Override
	public void consultar(Dns dns) {
		this.sessao.update(dns);
	}
}