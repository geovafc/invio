/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bpmlab.invio.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bpmlab
 */
@Entity
@Table(name = "curriculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curriculo.findAll", query = "SELECT c FROM Curriculo c"),
    @NamedQuery(name = "Curriculo.findById", query = "SELECT c FROM Curriculo c WHERE c.id = :id"),
    @NamedQuery(name = "Curriculo.findByCpf", query = "SELECT c FROM Curriculo c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "Curriculo.findByNome", query = "SELECT c FROM Curriculo c WHERE c.nome = :nome"),
    @NamedQuery(name = "Curriculo.findByDtNascimento", query = "SELECT c FROM Curriculo c WHERE c.dtNascimento = :dtNascimento"),
    @NamedQuery(name = "Curriculo.findByLogradouro", query = "SELECT c FROM Curriculo c WHERE c.logradouro = :logradouro"),
    @NamedQuery(name = "Curriculo.findByNumeroEnd", query = "SELECT c FROM Curriculo c WHERE c.numeroEnd = :numeroEnd"),
    @NamedQuery(name = "Curriculo.findByCep", query = "SELECT c FROM Curriculo c WHERE c.cep = :cep"),
    @NamedQuery(name = "Curriculo.findByBairro", query = "SELECT c FROM Curriculo c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Curriculo.findByCidade", query = "SELECT c FROM Curriculo c WHERE c.cidade = :cidade"),
    @NamedQuery(name = "Curriculo.findByEstado", query = "SELECT c FROM Curriculo c WHERE c.estado = :estado"),
    @NamedQuery(name = "Curriculo.findByPais", query = "SELECT c FROM Curriculo c WHERE c.pais = :pais"),
    @NamedQuery(name = "Curriculo.findByTelefone", query = "SELECT c FROM Curriculo c WHERE c.telefone = :telefone"),
    @NamedQuery(name = "Curriculo.findByCelular", query = "SELECT c FROM Curriculo c WHERE c.celular = :celular"),
    @NamedQuery(name = "Curriculo.findByEmail", query = "SELECT c FROM Curriculo c WHERE c.email = :email"),
    @NamedQuery(name = "Curriculo.findByMatricula", query = "SELECT c FROM Curriculo c WHERE c.matricula = :matricula"),
    @NamedQuery(name = "Curriculo.findByGrupoPesq", query = "SELECT c FROM Curriculo c WHERE c.grupoPesq = :grupoPesq"),
    @NamedQuery(name = "Curriculo.findByLattes", query = "SELECT c FROM Curriculo c WHERE c.lattes = :lattes"),
    @NamedQuery(name = "Curriculo.findByCurso", query = "SELECT c FROM Curriculo c WHERE c.curso = :curso"),
    @NamedQuery(name = "Curriculo.findByGenero", query = "SELECT c FROM Curriculo c WHERE c.genero = :genero"),
    @NamedQuery(name = "Curriculo.findByInstitutoCampi", query = "SELECT c FROM Curriculo c WHERE c.institutoCampi = :institutoCampi"),
    @NamedQuery(name = "Curriculo.findByFco", query = "SELECT c FROM Curriculo c WHERE c.fco = :fco"),
    @NamedQuery(name = "Curriculo.findByExtrato", query = "SELECT c FROM Curriculo c WHERE c.extrato = :extrato"),
    @NamedQuery(name = "Curriculo.findByTitulacao", query = "SELECT c FROM Curriculo c WHERE c.titulacao = :titulacao")})
public class Curriculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Column(name = "dtNascimento")
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 50)
    @Column(name = "numero_end")
    private String numeroEnd;
    @Size(max = 10)
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "pais")
    private String pais;
    @Size(max = 20)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "celular")
    private String celular;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "matricula")
    private String matricula;
    @Size(max = 200)
    @Column(name = "grupo_pesq")
    private String grupoPesq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "lattes")
    private String lattes;
    @Size(max = 200)
    @Column(name = "curso")
    private String curso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "genero")
    private String genero;
    @Size(max = 100)
    @Column(name = "instituto_campi")
    private String institutoCampi;
    @Column(name = "fco")
    private Integer fco;
    @Column(name = "extrato")
    private Integer extrato;
    @Size(max = 45)
    @Column(name = "titulacao")
    private String titulacao;
    @JoinTable(name = "plano_curriculo", joinColumns = {
        @JoinColumn(name = "curriculo_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "plano_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Plano> planoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculo")
    private List<Periodico> periodicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculo")
    private List<Orientacao> orientacaoList;
    @JoinColumn(name = "instituicao", referencedColumnName = "id")
    @ManyToOne
    private Instituicao instituicao;
    @JoinColumn(name = "area", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Area area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculo")
    private List<Relatorio> relatorioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculo")
    private List<Frequencia> frequenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculo")
    private List<Livro> livroList;
    @OneToMany(mappedBy = "curriculo")
    private List<Login> loginList;

    public Curriculo() {
    }

    public Curriculo(Integer id) {
        this.id = id;
    }

    public Curriculo(Integer id, String cpf, String nome, String logradouro, String bairro, String cidade, String estado, String pais, String celular, String email, String matricula, String lattes, String genero) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.celular = celular;
        this.email = email;
        this.matricula = matricula;
        this.lattes = lattes;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroEnd() {
        return numeroEnd;
    }

    public void setNumeroEnd(String numeroEnd) {
        this.numeroEnd = numeroEnd;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGrupoPesq() {
        return grupoPesq;
    }

    public void setGrupoPesq(String grupoPesq) {
        this.grupoPesq = grupoPesq;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getInstitutoCampi() {
        return institutoCampi;
    }

    public void setInstitutoCampi(String institutoCampi) {
        this.institutoCampi = institutoCampi;
    }

    public Integer getFco() {
        return fco;
    }

    public void setFco(Integer fco) {
        this.fco = fco;
    }

    public Integer getExtrato() {
        return extrato;
    }

    public void setExtrato(Integer extrato) {
        this.extrato = extrato;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    @XmlTransient
    public List<Plano> getPlanoList() {
        return planoList;
    }

    public void setPlanoList(List<Plano> planoList) {
        this.planoList = planoList;
    }

    @XmlTransient
    public List<Periodico> getPeriodicoList() {
        return periodicoList;
    }

    public void setPeriodicoList(List<Periodico> periodicoList) {
        this.periodicoList = periodicoList;
    }

    @XmlTransient
    public List<Orientacao> getOrientacaoList() {
        return orientacaoList;
    }

    public void setOrientacaoList(List<Orientacao> orientacaoList) {
        this.orientacaoList = orientacaoList;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @XmlTransient
    public List<Relatorio> getRelatorioList() {
        return relatorioList;
    }

    public void setRelatorioList(List<Relatorio> relatorioList) {
        this.relatorioList = relatorioList;
    }

    @XmlTransient
    public List<Frequencia> getFrequenciaList() {
        return frequenciaList;
    }

    public void setFrequenciaList(List<Frequencia> frequenciaList) {
        this.frequenciaList = frequenciaList;
    }

    @XmlTransient
    public List<Livro> getLivroList() {
        return livroList;
    }

    public void setLivroList(List<Livro> livroList) {
        this.livroList = livroList;
    }

    @XmlTransient
    public List<Login> getLoginList() {
        return loginList;
    }

    public void setLoginList(List<Login> loginList) {
        this.loginList = loginList;
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
        if (!(object instanceof Curriculo)) {
            return false;
        }
        Curriculo other = (Curriculo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bpmlab.invio.entidade.Curriculo[ id=" + id + " ]";
    }
    
}
