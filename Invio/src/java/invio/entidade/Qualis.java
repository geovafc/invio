/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabio
 */
@Entity
@Table(name = "qualis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qualis.findAll", query = "SELECT q FROM Qualis q"),
    @NamedQuery(name = "Qualis.findByTitulo", query = "SELECT q FROM Qualis q WHERE q.qualisPK.titulo = :titulo"),
    @NamedQuery(name = "Qualis.findByAreaAvaliacao", query = "SELECT q FROM Qualis q WHERE q.qualisPK.areaAvaliacao = :areaAvaliacao"),
    @NamedQuery(name = "Qualis.findByIssn", query = "SELECT q FROM Qualis q WHERE q.qualisPK.issn = :issn"),
    @NamedQuery(name = "Qualis.findByEstrato", query = "SELECT q FROM Qualis q WHERE q.estrato = :estrato"),
    @NamedQuery(name = "Qualis.findByStatus", query = "SELECT q FROM Qualis q WHERE q.status = :status")})
public class Qualis implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QualisPK qualisPK;
    @Column(name = "estrato")
    private String estrato;
    @Column(name = "status")
    private String status;

    public Qualis() {
    }

    public Qualis(QualisPK qualisPK) {
        this.qualisPK = qualisPK;
    }

    public Qualis(String titulo, String areaAvaliacao, String issn) {
        this.qualisPK = new QualisPK(titulo, areaAvaliacao, issn);
    }

    public QualisPK getQualisPK() {
        return qualisPK;
    }

    public void setQualisPK(QualisPK qualisPK) {
        this.qualisPK = qualisPK;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
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
        hash += (qualisPK != null ? qualisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qualis)) {
            return false;
        }
        Qualis other = (Qualis) object;
        if ((this.qualisPK == null && other.qualisPK != null) || (this.qualisPK != null && !this.qualisPK.equals(other.qualisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.Qualis[ qualisPK=" + qualisPK + " ]";
    }
    
}
