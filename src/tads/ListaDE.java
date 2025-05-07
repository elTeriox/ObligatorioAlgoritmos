package tads;

public class ListaDE<T extends Comparable<T>> implements ILista<T> {

    protected NodoDE<T> cabeza;
    protected int longitud;

    public ListaDE() {
        cabeza = null;
        longitud = 0;
    }

    @Override
    public void adicionar(T x) {
        NodoDE<T> nodo = new NodoDE<T>(x);
        if (vacia()) {
            cabeza = nodo;
        } else {
            NodoDE<T> cursor = cabeza;
            while (cursor.getSiguiente() != null) {
                cursor = cursor.getSiguiente();
            }
            nodo.setAnterior(cursor);
            cursor.setSiguiente(nodo);
        }
        longitud++;
    }

    @Override
    public void insertar(T x, int pos) throws PosFueraDeRangoException {
        if ((pos < 0) || (pos >= longitud)) {
            throw new PosFueraDeRangoException();
        }

        NodoDE<T> nodo = new NodoDE<T>(x);
        if (pos == 0) {
            nodo.setSiguiente(cabeza);
            if (cabeza != null) {
                cabeza.setAnterior(nodo);
            }
            cabeza = nodo;
        } else {
            NodoDE<T> cursor = cabeza;
            int i = 0;
            while (i < pos - 1) {
                i++;
                cursor = cursor.getSiguiente();
            }
            nodo.setSiguiente(cursor.getSiguiente());
            nodo.setAnterior(cursor);
            cursor.getSiguiente().setAnterior(nodo);
            cursor.setSiguiente(nodo);

        }
        longitud++;
    }

    @Override
    public T obtener(int pos) throws PosFueraDeRangoException {
        if ((pos < 0) || (pos >= longitud)) {
            throw new PosFueraDeRangoException();
        }

        NodoDE<T> cursor = cabeza;
        for (int i = 0; i < pos; i++) {
            cursor = cursor.getSiguiente();
        }
        return cursor.getDato();
    }

    @Override
    public void eliminar(int pos) throws PosFueraDeRangoException, ListaVaciaException {
        if (vacia()) {
            throw new ListaVaciaException();
        }
        if ((pos < 0) || (pos >= longitud)) {
            throw new PosFueraDeRangoException();
        }

        NodoDE<T> cursor = cabeza;
        if (pos == 0) {
            if (cabeza.getSiguiente() != null) {
                cabeza.getSiguiente().setAnterior(null);
            }
            cabeza = cursor.getSiguiente();
        } else {
            int i = 0;
            while (i < pos - 1) {
                i++;
                cursor = cursor.getSiguiente();
            }
            cursor.setSiguiente(cursor.getSiguiente().getSiguiente());
            if (cursor.getSiguiente() != null) {
                cursor.getSiguiente().setAnterior(cursor);
            }

        }
        longitud--;
    }

    @Override
    public int longitud() {
        return longitud;
    }

    @Override
    public boolean vacia() {
        return (longitud == 0);
    }
    
    /*Ejercicio 10 Practico 4*/
    public void eliminarInicio(){
        if (vacia()) {
            throw new ListaVaciaException();
        } 
        else 
          cabeza = cabeza.getSiguiente().getSiguiente();

    }

    public void invertirIterativo() {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return; 
        }

        NodoDE<T> actual = cabeza;
        NodoDE<T> siguiente = null;
        NodoDE<T> anterior = null;

        while (actual != null) {
            siguiente = actual.getSiguiente();
            actual.setSiguiente(anterior);
            actual.setAnterior(siguiente);
            anterior = actual;
            actual = siguiente;
        }

        cabeza = anterior; 
    }
    
    

    public void insertarOrdenado(T elem) {
        NodoDE<T> nuevoNodo = new NodoDE<>(elem);

        if (vacia()) {
            cabeza = nuevoNodo;
            longitud++;
            return;
        }

        NodoDE<T> actual = cabeza;
        NodoDE<T> anterior = null;

        while (actual != null && (actual.getDato().compareTo(nuevoNodo.getDato()) < 0)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (anterior == null) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevoNodo);
            cabeza = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(actual);
            nuevoNodo.setAnterior(anterior);
            anterior.setSiguiente(nuevoNodo);
            if (actual != null) {
                actual.setAnterior(nuevoNodo);
            }
        }

        longitud++;
    }
     
}


