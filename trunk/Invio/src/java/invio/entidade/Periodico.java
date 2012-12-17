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
 * @author RSORANSO
 */
@Entity
@Table(name = "periodico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodico.findAll", query = "SELECT p FROM Periodico p"),
    @NamedQuery(name = "Periodico.findById", query = "SELECT p FROM Periodico p WHERE p.id = :id"),
    @NamedQuery(name = "Periodico.findByTitulo", query = "SELECT p FROM Periodico p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Periodico.findByAutor", query = "SELECT p FROM Periodico p WHERE p.autor = :autor"),
    @NamedQuery(name = "Periodico.findByAno", query = "SELECT p FROM Periodico p WHERE p.ano = :ano"),
    @NamedQuery(name = "Periodico.findByRevista", query = "SELECT p FROM Periodico p WHERE p.revista = :revista"),
    @NamedQuery(name = "Periodico.findByVolume", query = "SELECT p FROM Periodico p WHERE p.volume = :volume"),
    @NamedQuery(name = "Periodico.findByPagina", query = "SELECT p FROM Periodico p WHERE p.pagina = :pagina")})
public class Periodico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "autor")
    private String autor;
    @Column(name = "ano")
    private String ano;
    @Column(name = "revista")
    private String revista;
    @Column(name = "volume")
    private String volume;
    @Column(name = "pagina")
    private String pagina;
    @JoinColumn(name = "curriculo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculo curriculoId;

    public Periodico() {
    }

    public Periodico(Integer id) {
        this.id = id;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
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
        if (!(object instanceof Periodico)) {
            return false;
        }
        Periodico other = (Periodico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.Periodico[ id=" + id + " ]";
    }
    
}
