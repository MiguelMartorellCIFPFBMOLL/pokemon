//   #Miguel Martorell Gari – examen 1º DAW
package simuEx1;
import java.io.*;
import java.util.*;

public class GestionDeportiva {
	protected static List<Jugador> jugadores = new ArrayList<>();
	protected static Map<Integer, List<Integer>> alineaciones = new HashMap<>();

	public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int opcion;
       do {
           System.out.println("\nGestión Deportiva");
           System.out.println("1. Insertar Jugador");
           System.out.println("2. Crear Alineación");
           System.out.println("3. Consultar Alineación");
           System.out.println("4. Salir (Mostrando alineaciones)");
           System.out.print("Seleccione una opción: ");
           
           try {
               opcion = scanner.nextInt();
               scanner.nextLine(); // Consumir nueva línea
               
               switch (opcion) {
                   case 1:
                       insertarJugador(scanner);
                       break;
                   case 2:
                       crearAlineacion(scanner);
                       break;
                   case 3:
                       consultarAlineacion(scanner);
                       break;
                   case 4:
                       salirYMostrarAlineaciones();
                       break;
                   default:
                       System.out.println("Opción inválida, por favor intente de nuevo.");
               }
           } catch (InputMismatchException e) {
               System.out.println("Error: Debe ingresar un número válido.");
               scanner.nextLine(); // Limpiar entrada incorrecta
               opcion = 0; // Resetear opción para no salir inesperadamente
           }
       } while (opcion != 4);
       
       scanner.close();  	       	
   }

	// 🔹 Método para mostrar todas las alineaciones en consola y guardarlas antes de salir
   protected static void salirYMostrarAlineaciones() {
       System.out.println("\n📋 Alineaciones registradas antes de salir:");

       if (alineaciones.isEmpty()) {
           System.out.println("⚠ No hay alineaciones registradas.");
           System.out.println("\n🔴 Saliendo del programa...");
           System.exit(0);
       }

       // 🔹 Guardamos el archivo dentro de `src/simuEx1/`
       String archivoNombre = "src/simuEx1/salida.txt";

       try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNombre))) { 
           bw.write("============== Alineaciones Registradas ========================\n");
           bw.write("***************************************************************\n");

           for (Map.Entry<Integer, List<Integer>> entry : alineaciones.entrySet()) {
               int codigo = entry.getKey();
               List<Integer> dorsales = entry.getValue();

               System.out.println("\nCódigo de Alineación: " + codigo);
               bw.write("\nCódigo de Alineación: " + codigo + "\n");

               for (int dorsal : dorsales) {
                   System.out.println("ID: " + codigo + " - Dorsal: " + dorsal);
                   bw.write("ID: " + codigo + " - Dorsal: " + dorsal + "\n");
               }
           }

           System.out.println("\n✅ Archivo generado correctamente en: " + archivoNombre);
       } catch (IOException e) {
           System.out.println("❌ Error al escribir el archivo: " + e.getMessage());
       }

       System.out.println("\n🔴 Saliendo del programa...");
       System.exit(0);
   }
   
	protected static void insertarJugador(Scanner sc) {
		System.out.println("Nombre:");
		String nombre = sc.nextLine();
		
		System.out.println("Apellido:");
		String apellido = sc.nextLine();
		
		System.out.println("Demarcación:");
		String demarcacion = sc.nextLine();
		
		 int dorsal;
	     boolean dorsalExiste;
	       
	        do {
	            System.out.print("Dame el dorsal del jugador: ");
	            dorsal = sc.nextInt();
	            dorsalExiste = false;
	            for (Jugador j : jugadores) {
	                if (j.getDorsal() == dorsal) {
	                    System.out.println("Error: Ya existe un jugador con ese dorsal. Inténtelo de nuevo.");
	                    dorsalExiste = true;
	                    break;
	                }
	            }
	        } while (dorsalExiste);
	       
	        Jugador nuevoJugador = new Jugador(nombre, apellido, dorsal, demarcacion);
	        jugadores.add(nuevoJugador);
	        System.out.println("Jugador insertado con éxito: " + nuevoJugador);
	}
	
	protected static void crearAlineacion(Scanner sc) {
	    System.out.print("Ingrese el código de alineación: ");
	    int codigo = sc.nextInt();
	    
	    if (alineaciones.get(codigo) != null) {
	        System.out.println("Error: Ya existe una alineación con este código.");
	        return;
	    }
	    
	    List<Integer> dorsales = new ArrayList<>();
	    boolean error = false;
	    
	    for (int i = 0; i < 5; i++) {
	        System.out.print("Ingrese el dorsal del jugador " + (i + 1) + ": ");
	        int dorsal = sc.nextInt();

	        boolean existe = false;
	        for (Jugador j : jugadores) {
	            if (j.getDorsal() == dorsal) {
	                existe = true;
	                break;
	            }
	        }
	        
	        if (!existe) {
	            System.out.println("Error: El dorsal " + dorsal + " no existe. Transacción cancelada.");
	            error = true;
	            break;
	        }

	        dorsales.add(dorsal);
	    }

	    if (error) {
	        System.out.println("No se ha guardado la alineación.");
	    } else {
	        alineaciones.put(codigo, dorsales);
	        System.out.println("Alineación creada correctamente.");
	    }
	}

	protected static void consultarAlineacion(Scanner sc)  {
	    System.out.print("Ingrese el código de alineación a consultar: ");
	    int codigo;
	    
	    try {
	        codigo = sc.nextInt();
	        sc.nextLine();
	    } catch (InputMismatchException e) {
	        System.out.println("Error: Debe ingresar un número válido.");
	        sc.nextLine();
	        return;
	    }

	    List<Integer> dorsales = alineaciones.get(codigo);

	    if (dorsales == null) {
	        System.out.println("Error: No existe una alineación con ese código.");
	        return;
	    }

	    String archivoNombre = "src/simuEx1/salida.txt";

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNombre, true))) { 
	        bw.write("============== Alineación ========================\n");
	        bw.write("**************************************************\n");
	        bw.write("Código de Alineación: " + codigo + "\n");

	        for (int dorsal : dorsales) {
	            bw.write("ID: " + codigo + " - Dorsal: " + dorsal + "\n");
	        }
	        
	        bw.write("\n");
	        System.out.println("✅ Alineación guardada correctamente en: " + archivoNombre);
	    } catch (IOException e) {
	        System.out.println("❌ Error al escribir el archivo: " + e.getMessage());
	    }
	}
}


	