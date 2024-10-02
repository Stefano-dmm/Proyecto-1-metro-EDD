/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listamain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mainp
 */
public class RandomRondasMitad {

    private ListaCircular Lista;
    //private ListaSimple Lista;
    //private ListaDoblementeEnlasada Lista;

    public RandomRondasMitad(ListaDoblementeEnlasada lista) {
        this.Lista = new ListaCircular();
        //this.Lista = new ListaSimple();
        //this.Lista = new ListaDoblementeEnlasada();
    }

    public List<Nodo> obtenerNodos() {
        List<Nodo> nodos = new ArrayList<>();
        Nodo actual = Lista.getCabeza();
        while (actual != null) {
            nodos.add(actual);
            actual = actual.siguiente;
        }
        return nodos;
    }

    public void realizarRondas() {
        List<Nodo> nodos = obtenerNodos();
        Random random = new Random();
        int ronda = 1;

        while (nodos.size() > 1) {
            System.out.println("Ronda " + ronda + ":");
            Collections.shuffle(nodos, random);
            int mitad = nodos.size() / 2;
            for (int i = 0; i < mitad; i++) {
                Nodo seleccionado = nodos.get(i);
                System.out.println("Nodo seleccionado: " + seleccionado.campo1 + ", " + seleccionado.campo2 + ", " + seleccionado.campo3);
            }
            nodos = nodos.subList(0, mitad);
            ronda++;
        }

        if (!nodos.isEmpty()) {
            Nodo ganador = nodos.get(0);
            System.out.println("Nodo ganador: " + ganador.campo1 + ", " + ganador.campo2 + ", " + ganador.campo3);
        }
    }

    private static class Nodo {
        int campo1;
        int campo2;
        int campo3;
        Nodo anterior;
        Nodo siguiente;

        Nodo(int campo1, int campo2, int campo3) {
            this.campo1 = campo1;
            this.campo2 = campo2;
            this.campo3 = campo3;
            this.anterior = null;
            this.siguiente = null;
        }
    }
}
    

