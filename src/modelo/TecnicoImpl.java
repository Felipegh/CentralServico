package modelo;

import java.util.List;


import entidades.Tecnico;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

/** Classe TecnicoImpl que implementa a Interface ITecnico, esta classe implementa crud da entidade Tecnico.
*
* @author felipe
*
*/
public class TecnicoImpl implements ITecnico{
	
	//Atributo de sessao do hibernate
	private Session sessao;

	/**Construtor da desta classe que inicializa o atributo de sessao.
	*/
	public TecnicoImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Metodo que consulta todas os Tecnicos cadastrados.
	 *
	 * @return List<Setor>, Contendo todos os Tecnicos cadastrados ordenados por seu nome.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tecnico> listar() {
		return sessao.createCriteria(Tecnico.class).addOrder(Order.asc("nome")).list();
	}

	/** Metodo que consulta setor especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do tecnico. 
	 * @return Tecnico, retorna o Tecnico cadastrado.
	 */
	@Override
	public Tecnico porCodigo(Integer codigo) {
		return (Tecnico) sessao.get(Tecnico.class, codigo);
	}

	/** Metodo que cria tecnico.
	 * @param tecnico, requer objeto tecnico para sua criação.
	 * @return Tecnico, retorna o tecnico cadastrado.
	 */
	@Override
	public Tecnico salvar(Tecnico tecnico) {
		return (Tecnico) sessao.merge(tecnico);
	}

	/** Metodo que remove tecnico.
	 * @param tecnico, requer objeto tecnico para sua remoção.
	 */
	@Override
	public void remover(Tecnico tecnico) {
		this.sessao.delete(tecnico);

	}

	/** Metodo que atualiza tecnico.
	 * @param tecnico, requer objeto tecnico para sua atualização.
	 */
	@Override
	public void editar(Tecnico tecnico) {
		this.sessao.update(tecnico);
	}
}