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
@Table(name = "periodico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodico.findAll", query = "SELECT p FROM Periodico p"),
    @NamedQuery(name = "Periodico.findById", query = "SELECT p FROM Periodico p WHERE p.id = :id"),
    @NamedQuery(name = "Periodico.findByTitulo", query = "SELECT p FROM Periodico p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Periodico.findByAutores", query = "SELECT p FROM Periodico p WHERE p.autores = :autores"),
    @NamedQuery(name = "Periodico.findByAno", query = "SELECT p FROM Periodico p WHERE p.ano = :ano"),
    @NamedQuery(name = "Periodico.findByRevista", query = "SELECT p FROM Periodico p WHERE p.revista = :revista"),
    @NamedQuery(name = "Periodico.findByVolume", query = "SELECT p FROM Periodico p WHERE p.volume = :volume"),
    @NamedQuery(name = "Periodico.findByPaginainicial", query = "SELECT p FROM Periodico p WHERE p.paginainicial = :paginainicial"),
    @NamedQuery(name = "Periodico.findByPaginafinal", query = "SELECT p FROM Periodico p WHERE p.paginafinal = :paginafinal"),
    @NamedQuery(name = "Periodico.findByArquivo", query = "SELECT p FROM Periodico p WHERE p.arquivo = :arquivo"),
    @NamedQuery(name = "Periodico.findByEstrato", query = "SELECT p FROM Periodico p WHERE p.estrato = :estrato"),
    @NamedQuery(name = "Periodico.findByAvaliacao", query = "SELECT p FROM Periodico p WHERE p.avaliacao = :avaliacao"),
    @NamedQuery(name = "Periodico.findByJcr", query = "SELECT p FROM Periodico p WHERE p.jcr = :jcr"),
    @NamedQuery(name = "Periodico.findByDoi", query = "SELECT p FROM Periodico p WHERE p.doi = :doi"),
    @NamedQuery(name = "Periodico.findByLink", query = "SELECT p FROM Periodico p WHERE p.link = :link")})
public class Periodico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "autores")
    private String autores;
    @Basic(optional = false)
    @Column(name = "ano")
    private String ano;
    @Basic(optional = false)
    @Column(name = "revista")
    private String revista;
    @Column(name = "volume")
    private String volume;
    @Basic(optional = false)
    @Column(name = "paginainicial")
    private int paginainicial;
    @Basic(optional = false)
    @Column(name = "paginafinal")
    private int paginafinal;
    @Column(name = "arquivo")
    private String arquivo;
    @Column(name = "estrato")
    private Integer estrato;
    @Column(name = "avaliacao")
    private String avaliacao;
    @Column(name = "jcr")
    private String jcr;
    @Column(name = "doi")
    private String doi;
    @Column(name = "link")
    private String link;
    @JoinColumn(name = "curriculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculo curriculo;

    public Periodico() {
    }

    public Periodico(Integer id) {
        this.id = id;
    }

    public Periodico(Integer id, String titulo, String autores, String ano, String revista, int paginainicial, int paginafinal) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.ano = ano;
        this.revista = revista;
        this.paginainicial = paginainicial;
        this.paginafinal = paginafinal;
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

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
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

    public int getPaginainicial() {
        return paginainicial;
    }

    public void setPaginainicial(int paginainicial) {
        this.paginainicial = paginainicial;
    }

    public int getPaginafinal() {
        return paginafinal;
    }

    public void setPaginafinal(int paginafinal) {
        this.paginafinal = paginafinal;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getEstrato() {
        return estrato;
    }

    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getJcr() {
        return jcr;
    }

    public void setJcr(String jcr) {
        this.jcr = jcr;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
