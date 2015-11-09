import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
    	
    	System.out.println("=========BIENVENIDO=========");
    	System.out.println("Ciudades Disponibles:");
    	System.out.println("Guatemala, Escuintla, Antigua, Atitlan\n");
        Floyd matriz = new Floyd(); // Matriz de adyacencia
        matriz.caminoCorto(); // Algoritmo de Floyd para encontrar el camino mas corto entre todos los nodos
        int seleccion; // Seleccion del usuario para realizar
        int opcion; // Seleccion del usuario para la pregunta 3
        Scanner scan = new Scanner(System.in); //Entrada por teclado
        System.out.println("1. Ruta mas corta entre ciudades ");
        System.out.println("2. Ciudad en el centro del grafo");
        System.out.println("3. Modificar grafo");
        System.out.println("4. Finalizar");
        seleccion = scan.nextInt(); 
        
        // Realizar ciclo mientras la respuesta del usuario no sea 4
        while(seleccion!=4){
            // Desplegar la matriz
            //System.out.println("\nMatriz de adyacencia");
            //matriz.D.show();
            
            // Si la respuesta es 1, preguntar por las ciudades y desplegar resultados
            if(seleccion==1){
                matriz.caminoCorto(); // Camino mas corto entre todas las ciudades
                System.out.println("Ingrese el nombre de la ciudad de salida");
                String ciudad1 = scan.next(); 
                System.out.println("Ingrese el nombre de la ciudad de destino");
                String ciudad2 = scan.next();
                // Si las ciudades se encuentran en la matriz, entonces desplegar la distancia minima y la ruta completa
                if(matriz.D.contains(ciudad1)&&matriz.D.contains(ciudad2)){
                    System.out.println("\nLa distancia minima es: "+matriz.D.getEdge(ciudad1, ciudad2)+".");
                    if(matriz.D.getEdge(ciudad1, ciudad2)!=10000){
                        System.out.print("La ruta es: "+ciudad1);
                        matriz.mostrarIntermedias(matriz.D.getIndex(ciudad1), matriz.D.getIndex(ciudad2));
                        System.out.println(", "+ciudad2);
                    }
                }
            }
            
            // Si la respuesta es 2, desplegar el centro de grafo
            else if(seleccion==2){
                matriz.centroGrafo();
            }
            
            // Si la respuesta es 3, preguntar cual es el cambio a realizar y ejecutarlo
            else if(seleccion==3){
                System.out.println("1. Hay interrupcion entre dos de ciudades");
                System.out.println("2. Establecer nueva conexion");
                opcion = scan.nextInt();
                // Si la respuesta es 1, preguntar por el nombre de las ciudades y colocar un numero muy grande
                // en la matriz de adyacencia para indicar que no hay conexion
                if(opcion==1){
                    System.out.println("Ingrese el nombre de la ciudad de salida");
                    String ciudad1 = scan.next();
                    System.out.println("Ingrese el nombre de la ciudad de destino");
                    String ciudad2 = scan.next();
                    if(matriz.D.contains(ciudad1)&&matriz.D.contains(ciudad2)){
                        matriz.D.addEdge(ciudad1, ciudad2, 10000);
                        // Guarda los cambios en el archivo
                        try {
                            matriz.a.write(ciudad1+" "+ciudad2+" 10000");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                // Si la respuesta es 2, preguntar por el nombre de las ciudades y agregar nueva distancia.
                else if(opcion==2){
                    System.out.println("Ingrese el nombre de la ciudad de salida");
                    String ciudad1 = scan.next();
                    System.out.println("Ingrese el nombre de la ciudad de destino");
                    String ciudad2 = scan.next();
                    System.out.println("Ingrese la distancia entre las ciudades");
                    int distancia = scan.nextInt();
                    // Si las ciudades ya existen, cambiar el valor
                    if(matriz.D.contains(ciudad1)&&matriz.D.contains(ciudad2)){
                        matriz.D.addEdge(ciudad1, ciudad2, distancia);
                    }
                    // Si las ciudades no existen, agregarlas a la matriz
                    else{
                        matriz.D.add(ciudad1);
                        matriz.D.add(ciudad2);
                        matriz.D.addEdge(ciudad1, ciudad2, distancia);
                    }
                    // Guarda los cambios en el archivo
                    try {
                        matriz.a.write(ciudad1+" "+ciudad2+" "+distancia);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                // Recalcular las rutas mas cortas
                matriz.caminoCorto();
            }
       
            System.out.println("1. Ruta mas corta entre ciudades ");
            System.out.println("2. Ciudad en el centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Finalizar");
            seleccion = scan.nextInt();
        }
    }
}