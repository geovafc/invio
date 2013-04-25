package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Area;
import invio.entidade.Periodico;
import invio.entidade.Programa;
import invio.rn.pdf.QualisRN;
import java.util.List;

public class PeriodicoRN {

    GenericDAO<Periodico> dao = new GenericDAO<Periodico>();
    QualisRN qualisRN = new QualisRN();

    public boolean salvar(Periodico periodico) {
        boolean salvou = false;

        int pt = 0;

        for (Programa temp : periodico.getCurriculoId().getProgramaList()) {
            for (Area areaTemp : temp.getAreaList()) {
                int ptTemp = qualisRN.obterEstrato(periodico.getRevista(), areaTemp.getNome());
                if (ptTemp >= pt) {
                    pt = ptTemp;
                }
            }
        }
        
        periodico.setEstrato(pt);
        
        
        if (dao.iniciarTransacao()) {
            if (periodico.getId() == null) {
                if (dao.criar(periodico)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(periodico)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
    }

    public boolean remover(Periodico periodico) {
        return dao.excluir(periodico);
    }

    public Periodico obter(Integer id) {
        return dao.obter(Periodico.class, id);
    }

    public List<Periodico> obterTodos() {
        return dao.obterTodos(Periodico.class);
    }
}
