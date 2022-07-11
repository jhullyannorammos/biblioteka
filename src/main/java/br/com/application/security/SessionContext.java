package br.com.application.security;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionContext {
	
	private static SessionContext instance;

    public static SessionContext getInstance(){
         if (instance == null){
             instance = new SessionContext();
         }

         return instance;
    }

    private SessionContext(){

    }

    private ExternalContext currentExternalContext(){
         if (FacesContext.getCurrentInstance() == null){
             throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
         }else{
             return FacesContext.getCurrentInstance().getExternalContext();
         }
    }

    public User getUsuarioLogado(){
         return (User) getAttribute("usuarioLogado");
    }

    public void setUsuarioLogado(User usuario){
         setAttribute("usuarioLogado", usuario);
    }

    public void encerrarSessao(){
         currentExternalContext().invalidateSession();
    }

    public Object getAttribute(String nome){
         return currentExternalContext().getSessionMap().get(nome);
    }

    public void setAttribute(String nome, Object valor){
         currentExternalContext().getSessionMap().put(nome, valor);
    }

}
