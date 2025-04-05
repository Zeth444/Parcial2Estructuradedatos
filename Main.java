import java.util.Scanner;

// Esta clase representa cada nodo de la lista enlazada
class Nodo {
    int dato;           // Valor que guarda el nodo
    Nodo siguiente;     // Referencia al siguiente nodo

    // Constructor que inicializa el nodo con un dato
    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Esta es nuestra clase principal que gestiona toda la lista enlazada
class ListaEnlazada {
    private Nodo cabeza; 

    // Agrega un nodo al inicio de la lista
    public void agregarInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    // Agrega un nodo al final de la lista
    public void agregarFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) { // Si la lista está vacía, el nuevo nodo será la cabeza
            cabeza = nuevo;
            return;
        }
         // Avanzamos hasta el último nodo
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo; // Agregamos al final
    }

    // Agrega un nodo en una posición específica (como insertar en medio)
    public void agregarMedio(int dato, int posicion) {
        if (posicion < 0) {
            System.out.println("Posición inválida.");
            return;
        }
    // Si la posición es 0, se agrega al inicio
        if (posicion == 0) { 
            agregarInicio(dato);
            return;
        }

        Nodo actual = cabeza;
        for (int i = 0; i < posicion - 1 && actual != null; i++) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            System.out.println("Posición fuera de rango.");
            return;
        }

        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

    // Elimina el primer nodo que contenga el valor indicado
    public void eliminar(int dato) {
        if (cabeza == null) return;

        if (cabeza.dato == dato) { // Si el nodo a eliminar es el primero
            cabeza = cabeza.siguiente;
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.dato != dato) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }

    // Verifica si un número está presente en la lista
    public boolean contiene(int dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato == dato) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    // Muestra todos los elementos de la lista
    public void mostrar() {
        Nodo actual = cabeza;
        if (actual == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) System.out.print(" -> ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    // Invierte el orden de los elementos en la lista
    public void reverse() {
        Nodo anterior = null;
        Nodo actual = cabeza;
        Nodo siguiente;

        while (actual != null) {
            siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }

        cabeza = anterior; // La nueva cabeza es el nodo que era el último
    }
}

public class Main {
    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Este ciclo muestra el menú principal y gestiona las opciones del usuario
        do {
            System.out.println("\n--- Menú de Lista Enlazada ---");
            System.out.println("1. Agregar número al inicio");
            System.out.println("2. Agregar número al final");
            System.out.println("3. Agregar número en posición específica");
            System.out.println("4. Mostrar lista");
            System.out.println("5. Eliminar un número");
            System.out.println("6. Buscar un número en la lista");
            System.out.println("7. Revertir la lista");
            System.out.println("8. Salir");
            System.out.print("Seleccione opción: ");

            // Verifica si la entrada del usuario es válida
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, elija una opción válida del menú.");
                scanner.next(); // limpia entrada incorrecta
                continue;
            }

            opcion = scanner.nextInt();
            int numero, posicion;

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese número a agregar al inicio: ");
                    numero = scanner.nextInt();
                    lista.agregarInicio(numero);
                    break;
                case 2:
                    System.out.print("Ingrese número a agregar al final: ");
                    numero = scanner.nextInt();
                    lista.agregarFinal(numero);
                    break;
                case 3:
                    System.out.print("Ingrese número a agregar: ");
                    numero = scanner.nextInt();
                    System.out.print("Ingrese posición (empezando desde 0): ");
                    posicion = scanner.nextInt();
                    lista.agregarMedio(numero, posicion);
                    break;
                case 4:
                    lista.mostrar();
                    break;
                case 5:
                    System.out.print("Ingrese número a eliminar: ");
                    numero = scanner.nextInt();
                    lista.eliminar(numero);
                    System.out.println("Número eliminado.");
                    break;
                case 6:
                    System.out.print("Ingrese número a buscar: ");
                    numero = scanner.nextInt();
                    if (lista.contiene(numero)) {
                        System.out.println("El número " + numero + " está en la lista.");
                    } else {
                        System.out.println("El número " + numero + " no se encuentra en la lista.");
                    }
                    break;
                case 7:
                    lista.reverse();
                    System.out.println("La lista ha sido revertida.");
                    break;
                case 8:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Por favor, elija una opción válida del menú.");
            }

        } while (opcion != 8);

        scanner.close();
    }
}
