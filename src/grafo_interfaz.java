public interface grafo_interfaz<V,E> {

    public void add(V label);

    public void addEdge(V vtx1, V vtx2, E label);

    public void show();

    public int getIndex(V label);

    public V get(int label);
    
    public int getEdge(V label1, V label2);
    
    public boolean contains(V label);
    
    public int size();
    
}