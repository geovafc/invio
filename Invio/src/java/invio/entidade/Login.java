/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dir de Armas Marinha
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findById", query = "SELECT l FROM Login l WHERE l.id = :id"),
    @NamedQuery(name = "Login.findBySenha", query = "SELECT l FROM Login l WHERE l.senha = :senha"),
    @NamedQuery(name = "Login.findByCodigoConfirmacao", query = "SELECT l FROM Login l WHERE l.codigoConfirmacao = :codigoConfirmacao"),
    @NamedQuery(name = "Login.findByCodigoConfimacaoTemp", query = "SELECT l FROM Login l WHERE l.codigoConfimacaoTemp = :codigoConfimacaoTemp"),
    @NamedQuery(name = "Login.findByDtCriacao", query = "SELECT l FROM Login l WHERE l.dtCriacao = :dtCriacao"),
    @NamedQuery(name = "Login.findByEmail", query = "SELECT l FROM Login l WHERE l.email = :email"),
    @NamedQuery(name = "Login.findByPerfil", query = "SELECT l FROM Login l WHERE l.perfil = :perfil")})
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
    @Column(name = "codigoConfimacaoTemp")
    private String codigoConfimacaoTemp;
    @Column(name = "dtCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "perfil")
    private String perfil;
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

    public String getCodigoConfimacaoTemp() {
        return codigoConfimacaoTemp;
    }

    public void setCodigoConfimacaoTemp(String codigoConfimacaoTemp) {
        this.codigoConfimacaoTemp = codigoConfimacaoTemp;
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
