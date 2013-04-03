/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Renan
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findById", query = "SELECT l FROM Login l WHERE l.id = :id"),
    @NamedQuery(name = "Login.findBySenha", query = "SELECT l FROM Login l WHERE l.senha = :senha"),
    @NamedQuery(name = "Login.findByCodigoConfirmacao", query = "SELECT l FROM Login l WHERE l.codigoConfirmacao = :codigoConfirmacao"),
    @NamedQuery(name = "Login.findByCodigoConfirmacaoTemp", query = "SELECT l FROM Login l WHERE l.codigoConfirmacaoTemp = :codigoConfirmacaoTemp"),
    @NamedQuery(name = "Login.findByDtCriacao", query = "SELECT l FROM Login l WHERE l.dtCriacao = :dtCriacao"),
    @NamedQuery(name = "Login.findByEmail", query = "SELECT l FROM Login l WHERE l.email = :email"),
    @NamedQuery(name = "Login.findByPerfil", query = "SELECT l FROM Login l WHERE l.perfil = :perfil"),
    @NamedQuery(name = "Login.findByAtivo", query = "SELECT l FROM Login l WHERE l.ativo = :ativo")})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Column(name = "codigoConfirmacao")
    private String codigoConfirmacao;
    @Column(name = "codigoConfirmacaoTemp")
    private String codigoConfirmacaoTemp;
    @Column(name = "dtCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "perfil")
    private String perfil;
    @Column(name = "ativo")
    private Boolean ativo;
    @ManyToMany(mappedBy = "loginList")
    private List<Perfil> perfilList;
    @JoinColumn(name = "curriculo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculo curriculoId;

    public Login() {
    }

    public Login(Integer id) {
        this.id = id;
    }

    public Login(Integer id, String senha, String email, String perfil) {
        this.id = id;
        this.senha = senha;
        this.email = email;
        this.perfil = perfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCodigoConfirmacao() {
        return codigoConfirmacao;
    }

    public void setCodigoConfirmacao(String codigoConfirmacao) {
        this.codigoConfirmacao = codigoConfirmacao;
    }

    public String getCodigoConfirmacaoTemp() {
        return codigoConfirmacaoTemp;
    }

    public void setCodigoConfirmacaoTemp(String codigoConfirmacaoTemp) {
        this.codigoConfirmacaoTemp = codigoConfirmacaoTemp;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    public Curriculo getCurriculoId() {
        return curriculoId;
    }

    public void setCurriculoId(Curriculo curriculoId) {
        this.curriculoId = curriculoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.Login[ id=" + id + " ]";
    }
    
}
