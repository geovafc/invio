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
 * @author fabio
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
    @NamedQuery(name = "Periodico.findByPagina", query = "SELECT p FROM Periodico p WHERE p.pagina = :pagina"),
    @NamedQuery(name = "Periodico.findByArquivo", query = "SELECT p FROM Periodico p WHERE p.arquivo = :arquivo"),
    @NamedQuery(name = "Periodico.findByEstrato", query = "SELECT p FROM Periodico p WHERE p.estrato = :estrato"),
    @NamedQuery(name = "Periodico.findByAvaliacao", query = "SELECT p FROM Periodico p WHERE p.avaliacao = :avaliacao")})
public class Periodico implements Serializable {
    @Basic(optional = false)
    @Column(name = "autores")
    private String autores;
    @Basic(optional = false)
    @Column(name = "paginainicial")
    private int paginainicial;
    @Basic(optional = false)
    @Column(name = "paginafinal")
    private int paginafinal;
    @Column(name = "link")
    private String link;
    @Column(name = "doi")
    private String doi;
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
    @Column(name = "arquivo")
    private String arquivo;
    @Column(name = "estrato")
    private Integer estrato;
    @Column(name = "avaliacao")
    private String avaliacao;
    @JoinColumn(name = "curriculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculo curriculo;

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

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }
    
}
