package util;

public class MensagemErroLDAP {

	public String mensagem(int idErro){
		String erro = "";
		
		if (idErro == 20) {
			erro = "Atributo já existe na entrada";
		}
		
		return erro;
	}
	
}
