package br.com.application.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.application.jpa.persistence.UsuarioPersistence;
import br.com.application.model.Usuario;

@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginManagedBean {

	private UsuarioPersistence usuarioDAO = new UsuarioPersistence();
	private Usuario usuario = new Usuario();

	public String envia() {

		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));
			return null;
		} else {
			return "/main";
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
