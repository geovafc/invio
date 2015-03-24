/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invioscript;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Dedo
 */
public class Qualis implements Serializable {

    private String issn;
    private String titulo;
    private String estrato;
    private String areaDeAvaliacao;
    private String status;

    public Qualis() {
    }

    public Qualis(String issn, String titulo, String estrato, String areaDeAvaliacao, String status) {
        this.issn = issn;
        this.titulo = titulo;
        this.estrato = estrato;
        this.areaDeAvaliacao = areaDeAvaliacao;
        this.status = status;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getAreaDeAvaliacao() {
        return areaDeAvaliacao;
    }

    public void setAreaDeAvaliacao(String areaDeAvaliacao) {
        this.areaDeAvaliacao = areaDeAvaliacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.issn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Qualis other = (Qualis) obj;
        if (!Objects.equals(this.issn, other.issn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Qualis{" + "issn=" + issn + ", titulo=" + titulo + ", estrato=" + estrato + ", areaDeAvaliacao=" + areaDeAvaliacao + ", status=" + status + '}';
    }
    
}
