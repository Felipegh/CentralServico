package modelo;

import java.util.List;
//import entidades.Setor;
import entidades.Tecnico;

/** Interface que contém as assinaturas de metodos de setor.
*
* @author silas
*
*/

public interface ITecnico {
	
	/** Metodo que consulta todas os setores cadastrados.
	 *
	 * @return List<Setor>, Contendo todos os setores cadastrados ordenados por seu local.
	 */
	public List<Tecnico> listar();
	
	/** Metodo que consulta setor especifico por seu codigo de identificação.
	 * @param codigo, codigo de identificação do setor. 
	 * @return Setor, retorna o setor cadastrado.
	 */
	public Tecnico porCodigo(Integer codigo);
	
	/** Metodo que cria setor.
	 * @param setor, requer objeto setor para sua criação.
	 * @return Setor, retorna o setor cadastrado.
	 */
	public Tecnico salvar(Tecnico tecnico);
	
	/** Metodo que remove setor.
	 * @param setor, requer objeto setor para sua remoção.
	 */
	public void remover(Tecnico tecnico);
	
	/** Metodo que atualiza setor.
	 * @param setor, requer objeto setor para sua atualização.
	 */
	public void editar(Tecnico tecnico);
}