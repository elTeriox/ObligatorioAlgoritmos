package tads;

public class ListaSE<T extends Comparable<? super T> > implements ILista<T> {

    protected NodoSE<T> cabeza;
    protected int longitud;

    public ListaSE() {
        cabeza = null;
        longitud = 0;
    }

    @Override
    public void adicionar(T x) {
        NodoSE<T> elem = new NodoSE<T>(x);
        if (vacia()) 
            cabeza = elem;
        else
        {
          NodoSE<T> aux = cabeza;
          while (aux.getSiguiente() != null)
            aux = aux.getSiguiente();
          
          aux.setSiguiente(elem);  
        }
        
        longitud++;
    }
   

    @Override
    public void insertar(T x, int pos) throws PosFueraDeRangoException {
        if ((pos < 0) || (pos >= longitud)) {
             throw new PosFueraDeRangoException();
        }

        NodoSE<T> nodo = new NodoSE<T>(x, null);
        if (pos == 0) {
            nodo.setSiguiente(cabeza);
            cabeza = nodo;
        } else {
            NodoSE<T> cursor = cabeza;
            int i = 0;
            while (i < pos - 1) {
                i++;
                cursor = cursor.getSiguiente();
            }
            nodo.setSiguiente(cursor.getSiguiente());
            cursor.setSiguiente(nodo);
        }
        longitud++;
   
    }

    @Override
    public T obtener(int pos) throws PosFueraDeRangoException{
       NodoSE<T> aux = cabeza; 
       if (vacia())
           throw new PosFueraDeRangoException();
       else 
       {
           if (pos >=0 && pos <= longitud){
                int i = 0;
                while(i<pos){
                    aux = aux.getSiguiente();
                    i++;
                }
            return aux.getDato(); 
           }
               
       
       }
  
      return null;
    }

    @Override
    public void eliminar(int pos) throws PosFueraDeRangoException, ListaVaciaException {
         if (vacia()) {
            throw new ListaVaciaException();
        }
        if ((pos < 0) || (pos >= longitud)) {
            throw new PosFueraDeRangoException();
        }

        NodoSE<T> cursor = cabeza;
        if (pos == 0) {
            cabeza = cursor.getSiguiente();
        } else {
            int i = 0;
            while (i < pos - 1) {
                i++;
                cursor = cursor.getSiguiente();
            }            
            cursor.setSiguiente(cursor.getSiguiente().getSiguiente());
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
    
    /*Ejercicio 1 Practico 4 inciso a */
    public void adicionarInicio(T elem){
      NodoSE<T> nodo = new NodoSE<T>(elem, null);
        if (vacia()) {
            cabeza = nodo;
        } else {
           nodo.setSiguiente(cabeza);
           cabeza = nodo;
        }
        longitud++;
        
    }
   
    /*Ejercicio 1 Practico 4 inciso d */
    public boolean existeElemento(T elem){
        if (vacia()) {
            throw new ListaVaciaException();
        } else {
           NodoSE<T> aux = cabeza;
           while (aux != null){
            if (aux.getDato().equals(elem)) return true;
            else 
            aux = aux.getSiguiente();
           }
        }
       
        return false;
    }
    
    /*Ejercicio 1 Practico 4 inciso j*/
    public void eliminarInicio(){
        if (vacia()) {
            throw new ListaVaciaException();
        } 
        else {
          cabeza = cabeza.getSiguiente();
          longitud--;
        }

    }
    
    /*Ejercicio 1 Practico 4 inciso k */
    public void eliminarFinal(){
        if (vacia()) {
            throw new ListaVaciaException();
        } 
        else {
         int i = 0;
         NodoSE<T> aux = cabeza;
         while (i < longitud()-1) {
            i++;
            aux = aux.getSiguiente();
         }            
         aux.setSiguiente(null);
         longitud--;
        }

    }
    
    /*Ejercicio 2 Practico 4 inciso a*/
    public void invertirIterativo(){
        if (vacia()) {
            throw new ListaVaciaException();
        } 
        NodoSE<T> nodoActual = cabeza;
        NodoSE<T> nodoAnterior = null;
        NodoSE<T> nodoSiguiente;

        while (nodoActual != null) {
            nodoSiguiente = nodoActual.getSiguiente();
            nodoActual.setSiguiente(nodoAnterior);
            nodoAnterior = nodoActual;
            nodoActual = nodoSiguiente;
        }

    cabeza = nodoAnterior; 
    }
    
    public void invertirRecursivo() {
    cabeza = invertirRecursivo(cabeza, null);
    }

    private NodoSE<T> invertirRecursivo(NodoSE<T> actual, NodoSE<T> anterior) {
    if (actual == null) {
        return anterior;
    }

    NodoSE<T> siguiente = actual.getSiguiente();
    actual.setSiguiente(anterior);
    return invertirRecursivo(siguiente, actual);
    }
    
    public boolean estaOrdenada() {
    NodoSE<T> nodoActual = cabeza;
    while (nodoActual != null && nodoActual.getSiguiente() != null) {
        if (nodoActual.getDato().compareTo(nodoActual.getSiguiente().getDato()) > 0) {
            return false; 
        }
        nodoActual = nodoActual.getSiguiente();
    }
    return true; 
    }
    
     public void insertarOrdenado(T elem) {
        NodoSE<T> nuevoNodo = new NodoSE<T>(elem, null);

        if (cabeza == null || cabeza.getDato().compareTo(elem) > 0) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        } else {
            NodoSE<T> nodoActual = cabeza;
            while (nodoActual.getSiguiente() != null && nodoActual.getSiguiente().getDato().compareTo(elem) < 0) {

                nodoActual = nodoActual.getSiguiente();
            }
            nuevoNodo.setSiguiente(nodoActual.getSiguiente());
            nodoActual.setSiguiente(nuevoNodo);
        }

        longitud++;
    }
     
    public int contar(T elem) {
        NodoSE<T> nodoActual = cabeza;
        int contador = 0;

        while (nodoActual != null) {
            if (nodoActual.getDato().compareTo(elem) == 0) {
                contador++;
            }
            nodoActual = nodoActual.getSiguiente();
        }

        return contador;
    }
    
    public T maximo() {
        if (vacia()) {
            throw new ListaVaciaException();
        } 
        
        NodoSE<T> nodoActual = cabeza;
        T maximo = cabeza.getDato();

        while (nodoActual != null) {
            if (nodoActual.getDato().compareTo(maximo) > 0) {
                maximo = nodoActual.getDato();
            }
            nodoActual = nodoActual.getSiguiente();
        }

        return maximo;
    }

    public ListaSE<T> cambiar(T n, T m) {
        if (existeElemento(n)) {
            ListaSE<T> listaResultado = new ListaSE<>();
            NodoSE<T> nodoActual = cabeza;

            while (nodoActual != null) {
                if (nodoActual.getDato().equals(n)) {
                    listaResultado.adicionar(m); 
                } else {
                    listaResultado.adicionar(nodoActual.getDato());
                }
                nodoActual = nodoActual.getSiguiente();
            }

            return listaResultado;
        }
        else return this;
    }
    
     public void Eliminar1(int pos) throws PosFueraDeRangoException, ListaVaciaException {
        if (vacia()) {
            throw new ListaVaciaException();
        }
        if ((pos < 0) || (pos >= longitud)) {
            throw new PosFueraDeRangoException();
        }

        if (pos == 1) {
            // Eliminar el primer nodo y el siguiente
            if (cabeza.getSiguiente() != null) {
                cabeza = cabeza.getSiguiente().getSiguiente();
            } else {
                cabeza = null;
            }
        } else {
            NodoSE actual = cabeza;
            // Recorrer la lista hasta el nodo anterior a la posición dada
            for (int i = 1; i < pos - 1; i++) {
                actual = actual.getSiguiente();
            }
            // Eliminar el nodo en la posición dada y el siguiente
            if (actual.getSiguiente() != null) {
                if (actual.getSiguiente().getSiguiente() != null) {
                    actual.setSiguiente(actual.getSiguiente().getSiguiente().getSiguiente());
                } else {
                    actual.setSiguiente(null);
                }
            }
        }

    }
      
}
