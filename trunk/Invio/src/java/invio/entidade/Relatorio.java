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
 * @author bpmlab
 */
@Entity
@Table(name = "relatorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relatorio.findAll", query = "SELECT r FROM Relatorio r"),
    @NamedQuery(name = "Relatorio.findById", query = "SELECT r FROM Relatorio r WHERE r.id = :id"),
    @NamedQuery(name = "Relatorio.findByTipo", query = "SELECT r FROM Relatorio r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Relatorio.findByMes", query = "SELECT r FROM Relatorio r WHERE r.mes = :mes"),
    @NamedQuery(name = "Relatorio.findByDataUpload", query = "SELECT r FROM Relatorio r WHERE r.dataUpload = :dataUpload"),
    @NamedQuery(name = "Relatorio.findByLocalArquivo", query = "SELECT r FROM Relatorio r WHERE r.localArquivo = :localArquivo")})
public class Relatorio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "mes")
    private String mes;
    @Column(name = "dataUpload")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUpload;
    @Column(name = "localArquivo")
    private String localArquivo;
    @JoinColumn(name = "curriculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculo curriculo;

    public Relatorio() {
    }

    public Relatorio(Integer id) {
        this.id = id;
    }

    public Relatorio(Integer id, String tipo, String mes) {
        this.id = id;
        this.tipo = tipo;
        this.mes = mes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Date getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(Date dataUpload) {
        this.dataUpload = dataUpload;
    }

    public String getLocalArquivo() {
        return localArquivo;
    }

    public void setLocalArquivo(String localArquivo) {
        this.localArquivo = localArquivo;
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
        if (!(object instanceof Relatorio)) {
            return false;
        }
        Relatorio other = (Relatorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.Relatorio[ id=" + id + " ]";
    }
    
}
