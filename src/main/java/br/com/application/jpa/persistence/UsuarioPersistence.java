package br.com.application.jpa.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.*;
import br.com.application.model.Usuario;
import br.com.application.security.Seguranca;

public class UsuarioPersistence {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
	private EntityManager em = factory.createEntityManager();
	
	public Usuario isUsuarioReadyToLogin(String email, String senha) {
	     try {
	            email = email.toLowerCase().trim();
	            List<Usuario> retorno; // = em.findByNamedQuery(Usuario.FIND_BY_EMAIL_SENHA, new NamedParams("email", email.trim(), "senha", Segurança.convertStringToMd5(senha)));

	            if (retorno.size() == 1) {
	                   Usuario userFound = (Usuario) retorno.get(0);
	                   return userFound;
	            }

	            return null;
	     } catch (DAOException e) {
	            e.printStackTrace();
	            throw new BOException(e.getMessage());
	     }
	}

	public Usuario getUsuario(String nomeUsuario, String senha) {

		try {
			Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.nomeUsuario =:name and u.senha =:senha").setParameter("name", nomeUsuario).setParameter("senha", senha).getSingleResult();

			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean inserirUsuario(Usuario usuario) {
		try {
			em.persist(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarUsuario(Usuario usuario) {
		try {
			em.remove(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
