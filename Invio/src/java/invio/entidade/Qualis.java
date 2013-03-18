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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "qualis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qualis.findAll", query = "SELECT q FROM Qualis q"),
    @NamedQuery(name = "Qualis.findById", query = "SELECT q FROM Qualis q WHERE q.id = :id"),
    @NamedQuery(name = "Qualis.findByIssn", query = "SELECT q FROM Qualis q WHERE q.issn = :issn"),
    @NamedQuery(name = "Qualis.findByTitulo", query = "SELECT q FROM Qualis q WHERE q.titulo = :titulo"),
    @NamedQuery(name = "Qualis.findByEstrato", query = "SELECT q FROM Qualis q WHERE q.estrato = :estrato"),
    @NamedQuery(name = "Qualis.findByAreaAvaliacao", query = "SELECT q FROM Qualis q WHERE q.areaAvaliacao = :areaAvaliacao"),
    @NamedQuery(name = "Qualis.findByStatus", query = "SELECT q FROM Qualis q WHERE q.status = :status")})
public class Qualis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "issn")
    private String issn;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "estrato")
    private String estrato;
    @Column(name = "areaAvaliacao")
    private String areaAvaliacao;
    @Column(name = "status")
    private String status;

    public Qualis() {
    }

    public Qualis(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getAreaAvaliacao() {
        return areaAvaliacao;
    }

    public void setAreaAvaliacao(String areaAvaliacao) {
        this.areaAvaliacao = areaAvaliacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Qualis)) {
            return false;
        }
        Qualis other = (Qualis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.Qualis[ id=" + id + " ]";
    }
    
}
