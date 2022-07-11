package br.com.application.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.application.exception.BOException;
import br.com.application.jpa.persistence.UsuarioPersistence;
import br.com.application.model.Usuario;
import br.com.application.security.Seguranca;
import br.com.application.security.SessionContext;
import br.com.application.security.User;

//import org.apache.log4j.Logger;

@ManagedBean(name = "usuarioLogadoMB")
@SessionScoped
public class UsuarioLogadoMBImpl extends BasicMBImpl {

	@ManagedProperty(value = "#{userBO}")
    private UsuarioPersistence userBO;

    private String email;
    private String login;
    private String senha;

    /**
     * Retorna usuario logado
     * */
    public User getUser() {
       return (User) SessionContext.getInstance().getUsuarioLogado();
    }

    public String doLogin() {
       try {
           
           Usuario user = userBO.isUsuarioReadyToLogin(login, senha);

           if (user == null) {
             //FacesMessage.addErrorMessage("Login ou Senha errado, tente novamente !");
             FacesContext.getCurrentInstance().validationFailed();
             return "";
           }

           Usuario usuario = null; // = (Usuario) getUserBO().findByNamedQuery(Usuario.FIND_BY_ID, new NamedParams("id", user.getId())).get(0);
           SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
           
       } catch (Exception e) {
           //addErrorMessage(e.getMessage());
           FacesContext.getCurrentInstance().validationFailed();
           e.printStackTrace();
           return "";
       } finally {
    	   return "/index.xhtml?faces-redirect=true";
       }

    }

    public String doLogout() {
       //logger.info("Fazendo logout com usuário " + SessionContext.getInstance().getUsuarioLogado().getLogin());
       SessionContext.getInstance().encerrarSessao();
       //addInfoMessage("Logout realizado com sucesso !");
       return "/security/form_login.xhtml?faces-redirect=true";
    }

    public void solicitarNovaSenha() {
       try {
           //Seguranca.gerarNovaSenha(login, email);
           //addInfoMessage("Nova Senha enviada para o email " + email);
       } catch (Exception e) {
           //addErrorMessage(e.getMessage());
           FacesContext.getCurrentInstance().validationFailed();
       }
    }

    public UsuarioPersistence getUserBO() {
       return userBO;
    }

    public void setUserBO(UsuarioPersistence userBO) {
       this.userBO = userBO;
    }

    public String getLogin() {
       return login;
    }

    public void setLogin(String login) {
       this.login = login;
    }

    public String getSenha() {
       return senha;
    }

    public void setSenha(String senha) {
       this.senha = senha;
    }

    public String getEmail() {
       return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

}
