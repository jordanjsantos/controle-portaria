package br.com.controle.portaria.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "usuario")
@ApiModel(value = "Usuario", description = "Usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private Integer id;	
	
	@Column(name = "login")
	private String user;
	
	@Column(name = "password")
	private String senha;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
	Pessoa pessoa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getSenhaCriptografadaMD5()  {
		MessageDigest m;
		String senha = "";
		try {
			senha = this.getSenha();
			m = MessageDigest.getInstance("MD5");
			m.update(senha.getBytes(), 0, senha.length());
			BigInteger bigSenha = new BigInteger(1, m.digest());
			senha = String.format("%1$032X", bigSenha);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return senha;
	}
		
}
