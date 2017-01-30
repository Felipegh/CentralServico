package modelo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.novell.ldap.LDAPException;

import entidades.Nslcd;
import entidades.PessoaContainer;




public interface PessoaContainerDAO {
	
	
	public boolean login(PessoaContainer pessoaContainer);
	
	public void logout();
	
	public PessoaContainer listAbobora(PessoaContainer pessoaContainer);
	
	public void migrate(PessoaContainer pessoaContainer);
	
}