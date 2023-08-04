package br.com.controle.portaria.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "controle_registro")
public class ControleRegistro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private Integer id;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_reg")
	private Date dataHoraReg;
	
	@ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
	Pessoa pessoa;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
	Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name = "imovel_condominio_id", nullable = false)
	ImovelCondominio imovelCondominio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraReg() {
		return dataHoraReg;
	}

	public void setDataHoraReg(Date dataHoraReg) {
		this.dataHoraReg = dataHoraReg;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ImovelCondominio getImovelCondominio() {
		return imovelCondominio;
	}

	public void setImovelCondominio(ImovelCondominio imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}
		
}
