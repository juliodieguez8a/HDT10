import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class doc{
    
    File doc;
    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    PrintWriter pw;
    grafo_interfaz grafo = new matriz();

    public void obtenerArchivo(String direccion) throws FileNotFoundException{
       doc=new File(direccion); 
    }  
    
    public grafo_interfaz arregloNombres() throws IOException{
        // Lectura del fichero
        fr = new FileReader (doc);
        br = new BufferedReader(fr);
        String linea;
        while((linea=br.readLine())!=null){
            String[] tmp;
            tmp=linea.split(" ");
            grafo.add(tmp[0]);
            grafo.add(tmp[1]);
        }
        return grafo;
    }
    
    public void write(String cadena) throws IOException{
        fw = new FileWriter(doc);
        pw = new PrintWriter(fw);
        pw.println(cadena);
    }
    
    public grafo_interfaz matrizCostos() throws IOException{
        // Lectura del fichero
        fr = new FileReader (doc);
        br = new BufferedReader(fr);
        String linea;

        while((linea=br.readLine())!=null){
            String[] tmp;
            tmp=linea.split(" ");
            grafo.addEdge(tmp[0], tmp[1], tmp[2]);
        }
        return grafo;
    }

}
