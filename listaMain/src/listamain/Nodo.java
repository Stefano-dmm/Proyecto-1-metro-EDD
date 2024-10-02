/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listamain;

public class Nodo {
    int campo1;
    int campo2;
    int campo3;
    Nodo siguiente;
    Nodo anterior;

    public Nodo(int campo1, int campo2, int campo3) {
        this.campo1 = campo1;
        this.campo2 = campo2;
        this.campo3 = campo3;
        this.siguiente = null;
        this.anterior = null;
    }

    public boolean equals(int campo1, int campo2, int campo3) {
        return this.campo1 == campo1 && this.campo2 == campo2 && this.campo3 == campo3;
    }
}
