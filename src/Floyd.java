import java.io.IOException;

public class Floyd {
    
    doc a= new doc();
    grafo_interfaz g_i;
    int[][] Matriz;
    int[] max;
    int centro;
    int minimo=10000;
    
    /**
     * Nombre: Floyd
     * Descripcion: constructor de la clase
     * Pre: no hay
     * Post: nuevo grafo 
     * No hay parametros ni valor de retorno
     */
    public Floyd(){
        
        try {
            a.obtenerArchivo("datos.txt");
            a.arregloNombres(); // Nombre de los nodos
            g_i = a.matrizCostos(); // Peso de los arcos entre nodos
            Matriz = new int[25][25]; // Matriz de nodos intermedios
            max = new int[25];
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for(int n=0;n<a.grafo.size();n++){
            for(int m=0;m<a.grafo.size();m++){
            	Matriz[n][m]=10000;
            }
        }
    }
    
    /**
     * Nombre: caminoCorto
     * Descripcion: algoritmo de Floyd para encontrar el camino mas corto entre todos los nodos
     * Basado en la presentacion de clase
     * Pre: existe una matriz de adyacencia con los valores iniciales del grafo
     * Post: matriz de adyacencia con los valores mas pequeños entre cada nodo
     * No hay parametros ni valor de retorno
     */
    public void caminoCorto(){
        for(int k=0;k<g_i.size();k++){
            for(int i=0;i<g_i.size();i++){
                for(int j=0;j<g_i.size();j++){
                    if(g_i.getEdge(g_i.get(i),g_i.get(j))>(g_i.getEdge(g_i.get(i), g_i.get(k))+g_i.getEdge(g_i.get(k), g_i.get(j)))){
                    	g_i.addEdge(g_i.get(i), g_i.get(j), (g_i.getEdge(g_i.get(i), g_i.get(k))+g_i.getEdge(g_i.get(k), g_i.get(j))));
                    	Matriz[i][j]=k;
                    }
                }
            }
        }
        
    }
    
    /**
     * Nombre: centroGrafo
     * Descripcion: encontrar el centro del grafo
     * Pre: matriz de adyacencia con los menores valores entre cada nodo
     * Post: centro del grafo
     * No hay parametros ni valor de retorno
     */
    public void centroGrafo(){
        caminoCorto();
        int n=0;       
        // Encontrar los maximos de cada columna de la matriz
        for(int i=0;i<g_i.size();i++){
            for(int j=0;j<g_i.size()-1;j++){
                int num1=g_i.getEdge(g_i.get(j), g_i.get(i));
                n=j;
                n++;
                int num2=g_i.getEdge(g_i.get(n), g_i.get(i));
                if(num1>num2){
                    max[i]=num1;
                }
                else{
                    max[i]=num2;
                }
            }
            n++;
        }
        // Encontrar el minimo de los maximos de cada columna
        for(int i=0;i<g_i.size();i++){
            int num1=max[i];
            if(num1<minimo){
                centro=i;
                minimo=num1;
            }
        }
        System.out.println("\nEl centro del grafo es: "+g_i.get(centro)+"\n");
        
    }
    
    /**
     * Nombres: mostrarIntermedias
     * Descripcion: desplegar los nodos intermedios
     * Basado en el material de clase
     * Pre: no hay
     * Post: nodos intermedios desplegados
     * No hay valor de retorno
     * @param num1
     * @param num2
     */
    public void mostrarIntermedias(int num1, int num2){
        if(Matriz[num1][num2]!=10000){
            mostrarIntermedias(num1,Matriz[num1][num2]);
            System.out.print(", "+g_i.get(Matriz[num1][num2]));
            mostrarIntermedias(Matriz[num1][num2],num2);
            return;
        }
        else return;
    }
}