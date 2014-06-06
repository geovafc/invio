/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package invio.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author bpmlab
 */
@Embeddable
public class QualisPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "areaAvaliacao")
    private String areaAvaliacao;
    @Basic(optional = false)
    @Column(name = "issn")
    private String issn;

    public QualisPK() {
    }

    public QualisPK(String titulo, String areaAvaliacao, String issn) {
        this.titulo = titulo;
        this.areaAvaliacao = areaAvaliacao;
        this.issn = issn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAreaAvaliacao() {
        return areaAvaliacao;
    }

    public void setAreaAvaliacao(String areaAvaliacao) {
        this.areaAvaliacao = areaAvaliacao;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (titulo != null ? titulo.hashCode() : 0);
        hash += (areaAvaliacao != null ? areaAvaliacao.hashCode() : 0);
        hash += (issn != null ? issn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QualisPK)) {
            return false;
        }
        QualisPK other = (QualisPK) object;
        if ((this.titulo == null && other.titulo != null) || (this.titulo != null && !this.titulo.equals(other.titulo))) {
            return false;
        }
        if ((this.areaAvaliacao == null && other.areaAvaliacao != null) || (this.areaAvaliacao != null && !this.areaAvaliacao.equals(other.areaAvaliacao))) {
            return false;
        }
        if ((this.issn == null && other.issn != null) || (this.issn != null && !this.issn.equals(other.issn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.QualisPK[ titulo=" + titulo + ", areaAvaliacao=" + areaAvaliacao + ", issn=" + issn + " ]";
    }
    
}
