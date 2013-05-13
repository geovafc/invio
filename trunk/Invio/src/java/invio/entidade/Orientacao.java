/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.entidade;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author soranso
 */
@Entity
@Table(name = "Orientacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orientacao.findAll", query = "SELECT o FROM Orientacao o"),
    @NamedQuery(name = "Orientacao.findById", query = "SELECT o FROM Orientacao o WHERE o.id = :id"),
    @NamedQuery(name = "Orientacao.findByAluno", query = "SELECT o FROM Orientacao o WHERE o.aluno = :aluno"),
    @NamedQuery(name = "Orientacao.findByTipo", query = "SELECT o FROM Orientacao o WHERE o.tipo = :tipo")})
public class Orientacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "aluno")
    private String aluno;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "curriculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculo curriculo;

    public Orientacao() {
    }

    public Orientacao(Integer id) {
        this.id = id;
    }

    public Orientacao(Integer id, String aluno, String tipo) {
        this.id = id;
        this.aluno = aluno;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
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
        if (!(object instanceof Orientacao)) {
            return false;
        }
        Orientacao other = (Orientacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.Orientacao[ id=" + id + " ]";
    }
    
}
