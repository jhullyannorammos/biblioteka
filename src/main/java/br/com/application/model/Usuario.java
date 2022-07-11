package br.com.application.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@NamedQueries(value = { @NamedQuery(name = "Usuario.findByEmailSenha", query = "SELECT c FROM Usuario c WHERE c.email = :email AND c.senha = :senha")})
@Entity
public class Usuario {
	
	@Transient public static final String FIND_BY_EMAIL_SENHA = "Usuario.findByEmailSenha";
	
	@Id @Column(name="id", nullable=false, unique=true)
    private int id;
	
	@Column(unique = true)
    private String email;

    @Column(name="userName", nullable=false, unique=true)
    private String nomeUsuario;

    @Column(name="password", nullable=false, unique=false)
    private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
