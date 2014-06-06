/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name = "orientacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orientacao.findAll", query = "SELECT o FROM Orientacao o"),
    @NamedQuery(name = "Orientacao.findById", query = "SELECT o FROM Orientacao o WHERE o.id = :id"),
    @NamedQuery(name = "Orientacao.findByAluno", query = "SELECT o FROM Orientacao o WHERE o.aluno = :aluno"),
    @NamedQuery(name = "Orientacao.findByPInicial", query = "SELECT o FROM Orientacao o WHERE o.pInicial = :pInicial"),
    @NamedQuery(name = "Orientacao.findByPFinal", query = "SELECT o FROM Orientacao o WHERE o.pFinal = :pFinal"),
    @NamedQuery(name = "Orientacao.findByTipoOrientacao", query = "SELECT o FROM Orientacao o WHERE o.tipoOrientacao = :tipoOrientacao"),
    @NamedQuery(name = "Orientacao.findByTipoBolsa", query = "SELECT o FROM Orientacao o WHERE o.tipoBolsa = :tipoBolsa"),
    @NamedQuery(name = "Orientacao.findByEstrato", query = "SELECT o FROM Orientacao o WHERE o.estrato = :estrato"),
    @NamedQuery(name = "Orientacao.findByAvaliacao", query = "SELECT o FROM Orientacao o WHERE o.avaliacao = :avaliacao"),
    @NamedQuery(name = "Orientacao.findByComprovante", query = "SELECT o FROM Orientacao o WHERE o.comprovante = :comprovante"),
    @NamedQuery(name = "Orientacao.findByArquivo", query = "SELECT o FROM Orientacao o WHERE o.arquivo = :arquivo")})
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
    @Column(name = "p_inicial")
    @Temporal(TemporalType.DATE)
    private Date pInicial;
    @Basic(optional = false)
    @Column(name = "p_final")
    @Temporal(TemporalType.DATE)
    private Date pFinal;
    @Basic(optional = false)
    @Column(name = "tipo_orientacao")
    private int tipoOrientacao;
    @Basic(optional = false)
    @Column(name = "tipo_bolsa")
    private String tipoBolsa;
    @Column(name = "estrato")
    private Integer estrato;
    @Column(name = "avaliacao")
    private String avaliacao;
    @Column(name = "comprovante")
    private String comprovante;
    @Column(name = "arquivo")
    private String arquivo;
    @JoinColumn(name = "curriculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curriculo curriculo;

    public Orientacao() {
    }

    public Orientacao(Integer id) {
        this.id = id;
    }

    public Orientacao(Integer id, String aluno, Date pFinal, int tipoOrientacao, String tipoBolsa) {
        this.id = id;
        this.aluno = aluno;
        this.pFinal = pFinal;
        this.tipoOrientacao = tipoOrientacao;
        this.tipoBolsa = tipoBolsa;
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

    public Date getPInicial() {
        return pInicial;
    }

    public void setPInicial(Date pInicial) {
        this.pInicial = pInicial;
    }

    public Date getPFinal() {
        return pFinal;
    }

    public void setPFinal(Date pFinal) {
        this.pFinal = pFinal;
    }

    public int getTipoOrientacao() {
        return tipoOrientacao;
    }

    public void setTipoOrientacao(int tipoOrientacao) {
        this.tipoOrientacao = tipoOrientacao;
    }

    public String getTipoBolsa() {
        return tipoBolsa;
    }

    public void setTipoBolsa(String tipoBolsa) {
        this.tipoBolsa = tipoBolsa;
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

    public String getComprovante() {
        return comprovante;
    }

    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
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
