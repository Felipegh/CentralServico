package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/** Classe Tecnico que possui os metodos de acesso getter e setters, 
 * e também o mapeamento relacional das tabelas via hibernate, da entidade Tecnico.
*
* @author felipe
*
*/

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = @UniqueConstraint (columnNames = { "email", "nome"}))
public class Tecnico implements Serializable, Cloneable{

	private Integer codigo;
	
	private String local;
	private String cargo;
	private String vinculo;
	private String cel;
	private String ramal;
	private String fixo;
	@Column(name="nome",unique=true)
	private String nome;
	@Column(name="email",unique=true)
	private String email;
		
	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	} 
	@Column(name="nome",unique=true)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	@Column
	public String getfixo() {
		return fixo;
	}
	public void setfixo(String fixo) {
		this.fixo = fixo;
	}
	@Column
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	@Column
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	@Column(name="email",unique=true)
	public String getEmail() {
		return email;
	} 
	public void setEmail(String email) {
		this.email = email;
	}
	@Column
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	@Column
	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((cel == null) ? 0 : cel.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fixo == null) ? 0 : fixo.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ramal == null) ? 0 : ramal.hashCode());
		result = prime * result + ((vinculo == null) ? 0 : vinculo.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tecnico other = (Tecnico) obj;
		if (cargo == null) {
			if (other.cargo != null) {
				return false;
			}
		} else if (!cargo.equals(other.cargo)) {
			return false;
		}
		if (cel == null) {
			if (other.cel != null) {
				return false;
			}
		} else if (!cel.equals(other.cel)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (fixo == null) {
			if (other.fixo != null) {
				return false;
			}
		} else if (!fixo.equals(other.fixo)) {
			return false;
		}
		if (local == null) {
			if (other.local != null) {
				return false;
			}
		} else if (!local.equals(other.local)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (ramal == null) {
			if (other.ramal != null) {
				return false;
			}
		} else if (!ramal.equals(other.ramal)) {
			return false;
		}
		if (vinculo == null) {
			if (other.vinculo != null) {
				return false;
			}
		} else if (!vinculo.equals(other.vinculo)) {
			return false;
		}
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


}
