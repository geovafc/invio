package bpmlab.invio.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDAO<T> implements InterfaceDAO<T> {
    
    private static final Logger LOG = Logger.getLogger(GenericDAO.class.getName());
    private EntityManager em = FabricaEntityManager.obterFabrica().createEntityManager();

    public GenericDAO() {
    }

    @Override
    public boolean criar(T o) {
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "ERRO NA CAMADA DAO", e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    @Override
    public boolean excluir(T o) {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(o));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "ERRO NA CAMADA DAO", e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    @Override
    public boolean alterar(T o) {
        try {
            em.getTransaction().begin();
            em.merge(o);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "ERRO NA CAMADA DAO", e);
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    @Override
    public T obter(Class<T> classe, Object id) {
        if (id == null) {
            return null;
        }
        String query = classe.getSimpleName() + ".findById";
        final Query q = em.createNamedQuery(query);
        try {
            return (T) q.setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<T> obterTodos(Class<T> classe) {
        String query = classe.getSimpleName() + ".findAll";
        Query q = em.createNamedQuery(query);
        return (List<T>) q.getResultList();
    }

    public List<T> obterLista(Class<T> classe,
            List<String> criterios,
            List valores,
            final boolean AND) {
        if (criterios == null
                || valores == null
                || criterios.size() != valores.size()
                || criterios.size() < 1) {
            return null;
        }
        String query = "SELECT o FROM " + classe.getSimpleName() + " o WHERE ";
        query += " o." + criterios.get(0) + " = :" + criterios.get(0);
        final String CONECTIVO = AND ? " AND " : " OR ";
        for (int i = 1; i < criterios.size(); i++) {
            query += CONECTIVO + " o." + criterios.get(i) + " = :" + criterios.get(i);
        }
        final Query q = em.createQuery(query);
        try {
            for (int i = 0; i < valores.size(); i++) {
                q.setParameter(criterios.get(i), valores.get(i));
            }
            return (List<T>) q.getResultList();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return null;
        }
    }

    /**
     * @return the entityManager
     */
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @param entityManager the entityManager to set
     */
    protected void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("*******************************MORREU");
        em.close();
    }
    
}