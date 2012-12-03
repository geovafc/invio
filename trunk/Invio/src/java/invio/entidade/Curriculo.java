/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dir de Armas Marinha
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
    @NamedQuery(name = "Curriculo.findByLattes", query = "SELECT c FROM Curriculo c WHERE c.lattes = :lattes"),
    @NamedQuery(name = "Curriculo.findByCurso", query = "SELECT c FROM Curriculo c WHERE c.curso = :curso")})
public class Curriculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "dtNascimento")
    private String dtNascimento;
    @Basic(optional = false)
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "numero_end")
    private String numeroEnd;
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "pais")
    private String pais;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "lattes")
    private String lattes;
    @Column(name = "curso")
    private String curso;
    @JoinTable(name = "curriculo_programa", joinColumns = {
        @JoinColumn(name = "curriculo", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "programa", referencedColumnName = "id")})
    @ManyToMany
    private List<Programa> programaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculoId")
    private List<Login> loginList;

    public Curriculo() {
    }

    public Curriculo(Integer id) {
        this.id = id;
    }

    public Curriculo(Integer id, String cpf, String nome, String dtNascimento, String logradouro, String bairro, String cidade, String estado, String pais, String email, String matricula, String lattes) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.email = email;
        this.matricula = matricula;
        this.lattes = lattes;
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

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
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

    @XmlTransient
    public List<Programa> getProgramaList() {
        return programaList;
    }

    public void setProgramaList(List<Programa> programaList) {
        this.programaList = programaList;
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
        return "invio.entidade.Curriculo[ id=" + id + " ]";
    }
    
}
