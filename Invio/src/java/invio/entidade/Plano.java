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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
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
 * @author SORANSO
 */
@Entity
@Table(name = "plano")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plano.findAll", query = "SELECT p FROM Plano p"),
    @NamedQuery(name = "Plano.findById", query = "SELECT p FROM Plano p WHERE p.id = :id"),
    @NamedQuery(name = "Plano.findByTitulo", query = "SELECT p FROM Plano p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Plano.findByData", query = "SELECT p FROM Plano p WHERE p.data = :data")})
public class Plano implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Column(name = "resumo")
    private String resumo;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinTable(name = "plano_curriculo", joinColumns = {
        @JoinColumn(name = "plano_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "curriculo_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Curriculo> curriculoList;
    @JoinColumn(name = "edital", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Edital edital;

    public Plano() {
    }

    public Plano(Integer id) {
        this.id = id;
    }

    public Plano(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @XmlTransient
    public List<Curriculo> getCurriculoList() {
        return curriculoList;
    }

    public void setCurriculoList(List<Curriculo> curriculoList) {
        this.curriculoList = curriculoList;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
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
        if (!(object instanceof Plano)) {
            return false;
        }
        Plano other = (Plano) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.Plano[ id=" + id + " ]";
    }
    
}
