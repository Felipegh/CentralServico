package modelo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.SchemasLDAP;
import util.Utilitaria;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

import entidades.Nslcd;
import entidades.PessoaContainer;



/** Classe PessoaSSHDAOImpl que implementa a Interface das PessoasSSHDAO, esta classe implementa os metodos da classe PessoaSSH.
 *
 * @author silas
 * @see   Utilitaria 
 */

public class PessoaContainerDAOImpl implements PessoaContainerDAO{
	SchemasLDAP utilLDAP;


	public PessoaContainerDAOImpl(){
		utilLDAP = new SchemasLDAP();

	}


	@Override
	public boolean login(PessoaContainer pessoaContainer) {
		boolean estado = false;
		String loginDN = "uid="+pessoaContainer.getUid()+"ou=container,dc=ufrn,dc=br";
		String password = pessoaContainer.getSenha();
		String searchBase = "uid="+pessoaContainer.getUid()+"ou=container,dc=ufrn,dc=br", searchFilter = "(ou=admin)";
		int searchScope = LDAPConnection.SCOPE_SUB;
		//String[] atributos = {"userPassword", "cn", "uid", "mail", "gidNumber"};
		String[] atributos = {"uid"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.156.9", 389 );
			try {
				lc.bind( LDAPConnection.LDAP_V3, loginDN,  password.getBytes("UTF8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);
			System.out.println("Numero: "+ searchResults.getCount());
			
			if (searchResults. hasMore()) {
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("usuarioContainer", pessoaContainer.getUid());
				session.setAttribute("senhaContainer", pessoaContainer.getSenha());
				session.setAttribute("conexaoContainer", lc);
				estado = true;
			}else{
				estado = false;
			}

		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estado;

	}



	@Override
	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LDAPConnection conexao = (LDAPConnection) session.getAttribute("conexaoContainer");
		session.removeAttribute("usuarioContainer");
		session.removeAttribute("senhaContainer");
		try {
			conexao.disconnect();
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.invalidate();

	}


	@Override
	public PessoaContainer listAbobora(PessoaContainer pessoaContainer) {
		String loginDN = "uid=redes,ou=departamento,ou=pessoa,dc=ufrn,dc=br";
		String password = "gob0l1nux";
		String searchBase = "ou=pessoa,dc=ufrn,dc=br", searchFilter = "(uid="+pessoaContainer.getUid()+")";
		int searchScope = LDAPConnection.SCOPE_ONE;
		//String[] atributos = {"userPassword", "cn", "uid", "mail", "gidNumber"};
		String[] atributos = {"userPassword", "uid"};
		LDAPConnection lc = new LDAPConnection();
		try {
			lc.connect("10.3.226.192", 389 );
			try {
				lc.bind( LDAPConnection.LDAP_V3, loginDN,  password.getBytes("UTF8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, atributos, false);
			while (searchResults.hasMore() ) {
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch(LDAPException e) {
					System.out.println("Error: " + e);
					continue;
				}

				LDAPAttribute attributeuid = nextEntry.getAttribute("uid");
				LDAPAttribute attributeuserPassword = nextEntry.getAttribute("userPassword");

				pessoaContainer.setUid(attributeuid.getStringValue());
				pessoaContainer.setSenha(attributeuserPassword.getStringValue());

			}
		} catch( LDAPException e ) {
			System.out.println("Error " + e.toString() );
		}

		return pessoaContainer;
	}


	@Override
	public void migrate(PessoaContainer pessoaContainer) {
		PessoaContainer pessoaAbobora = listAbobora(pessoaContainer);

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) req.getSession();
		String usuario = (String) session.getAttribute("usuarioContainer");
		String senha = (String) session.getAttribute("senhaContainer");
		LDAPAttributeSet attributes = new LDAPAttributeSet();
		SchemasLDAP schema = new SchemasLDAP();

		LDAPConnection conn = new LDAPConnection();
		try {
			conn.connect("10.3.156.9",389);
		} catch (LDAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.bind(LDAPConnection.LDAP_V3, usuario, senha.getBytes());
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String base = "ou=container,dc=ufrn,dc=br";
		attributes = schema.adicionarPessoaContainer(pessoaContainer);
		LDAPEntry entry = new LDAPEntry(base, attributes);
		try {
			conn.add(entry);
		} catch (LDAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

	}

}