/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bpmlab.invio.entidade;

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
 * @author bpmlab
 */
@Entity
@Table(name = "livro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l"),
    @NamedQuery(name = "Livro.findById", query = "SELECT l FROM Livro l WHERE l.id = :id"),
    @NamedQuery(name = "Livro.findByTitulo", query = "SELECT l FROM Livro l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Livro.findByCapitulo", query = "SELECT l FROM Livro l WHERE l.capitulo = :capitulo"),
    @NamedQuery(name = "Livro.findByAutor", query = "SELECT l FROM Livro l WHERE l.autor = :autor"),
    @NamedQuery(name = "Livro.findByAno", query = "SELECT l FROM Livro l WHERE l.ano = :ano"),
    @NamedQuery(name = "Livro.findByEstrato", query = "SELECT l FROM Livro l WHERE l.estrato = :estrato"),
    @NamedQuery(name = "Livro.findByIsbn", query = "SELECT l FROM Livro l WHERE l.isbn = :isbn"),
    @NamedQuery(name = "Livro.findByArquivo", query = "SELECT l FROM Livro l WHERE l.arquivo = :arquivo"),
    @NamedQuery(name = "Livro.findByAvaliacao", query = "SELECT l FROM Livro l WHERE l.avaliacao = :avaliacao"),
    @NamedQuery(name = "Livro.findByTipoLivro", query = "SELECT l FROM Livro l WHERE l.tipoLivro = :tipoLivro")})
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "capitulo")
    private String capitulo;
    @Column(name = "autor")
    private String autor;
    @Column(name = "ano")
    private String ano;
    @Column(name = "estrato")
    private Integer estrato;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "arquivo")
    private String arquivo;
    @Column(name = "avaliacao")
    private String avaliacao;
    @Basic(optional = false)
    @Column(name = "tipoLivro")
    private int tipoLivro;
    @JoinColumn(name = "curriculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculo curriculo;

    public Livro() {
    }

    public Livro(Integer id) {
        this.id = id;
    }

    public Livro(Integer id, int tipoLivro) {
        this.id = id;
        this.tipoLivro = tipoLivro;
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

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
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

    public Integer getEstrato() {
        return estrato;
    }

    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getTipoLivro() {
        return tipoLivro;
    }

    public void setTipoLivro(int tipoLivro) {
        this.tipoLivro = tipoLivro;
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
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "invio.entidade.Livro[ id=" + id + " ]";
    }
    
}
