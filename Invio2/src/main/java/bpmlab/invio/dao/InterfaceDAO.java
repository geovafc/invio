package bpmlab.invio.dao;

import java.util.List;

public interface InterfaceDAO<T> {
    public boolean criar(T o);
    public boolean alterar(T o);
    public boolean excluir(T o);
    public T obter(Class<T> classe, Object id);
    public List<T> obterTodos(Class<T> classe);
}
