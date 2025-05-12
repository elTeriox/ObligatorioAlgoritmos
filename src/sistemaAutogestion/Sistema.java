package sistemaAutogestion;

import java.time.LocalDate;
import dominio.*;
import tads.*;

public class Sistema implements IObligatorio {

    private static Sistema sistema;
    private ListaSalaDE salas;
    private ListaEventoSE eventos;
    private ListaClienteSE clientes;

    public Sistema() {
        this.salas = new ListaSalaDE();
        this.eventos = new ListaEventoSE();
        this.clientes = new ListaClienteSE();
    }

    @Override
    public Retorno crearSistemaDeGestion() {
        sistema = new Sistema();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        if (capacidad <= 0) {
            return Retorno.error2();
        }
        //if (salas.existeSala(nombre)) return Retorno.error1();
        Sala nuevaSala = new Sala(nombre, capacidad);
        //if (nuevaSala.getNombre().equals(nombre)) Retorno.error1();
        for (int i = 0; i < salas.longitud(); i++) {
            Sala s = (Sala) salas.obtener(i);
            if (s.getNombre().equals(nombre)) {
                return Retorno.error1();
            }
        }
        salas.adicionar(nuevaSala);
        // salas.adicionar(nuevaSala);
        return Retorno.ok();
    }

    @Override
    public Retorno eliminarSala(String nombre) {
        if (salas.vacia()) {
            return Retorno.error1();
        }
        for (int i = 0; i < salas.longitud(); i++) {
            Sala salaActual = (Sala) salas.obtener(i);
            if (salaActual.getNombre().equals(nombre)) {
                salas.eliminar(i);
                return Retorno.ok();
            }
        }
        return Retorno.error2();
    }


    
    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
    // Validar aforo ingresado
    if (aforoNecesario <= 0) {
        return Retorno.error2();
    }

    // Validar que el código no exista
    for (int i = 0; i < eventos.longitud(); i++) {
        Evento e = (Evento) eventos.obtener(i);
        if (e.getCodigo().equals(codigo)) {
            return Retorno.error1(); // Evento ya registrado
        }
    }

    // Buscar sala para el evento
    for (int i = 0; i < salas.longitud(); i++) {
        Sala sala = (Sala) salas.obtener(i);
        if (sala.getCapacidad() >= aforoNecesario && !sala.estaOcupada(fecha)) {
            Evento e = new Evento(codigo, descripcion, aforoNecesario, fecha, sala, aforoNecesario, 0);
            eventos.adicionar(e);
            sala.agendarEvento(fecha);
            return Retorno.ok();
        }
    }

    // Si no encontró sala válida
    return Retorno.error3(); // No hay sala disponible
}
    
    
    
    
    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        // Validar CI
        if (cedula.length() != 8) {
            return Retorno.error1();
        }
        // Buscar si ya existe el cliente
        for (int i = 0; i < clientes.longitud(); i++) {
            Cliente c = (Cliente) clientes.obtener(i);
            if (c.getCedula().equals(cedula)) {
                return Retorno.error2();
            }
          
        }
        Cliente nuevo = new Cliente(cedula, nombre);
        clientes.adicionar(nuevo);
        return Retorno.ok();
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarSalas() {
        if (salas.vacia()) {
            return Retorno.error1();
        }
        // Invertir Lista
        //todo: agregar att ultimo en listaDE? asi solo recorre para mostrar y no se utiliza 2 veces el .invertirIterativo()
        salas.invertirIterativo();
        // Mensaje con las salas
        String mensaje = "Listado de Salas:\n";
        for (int i = 0; i < salas.longitud(); i++) {
            Sala actual = (Sala) salas.obtener(i);
            mensaje += "- " + actual.getNombre() + ", Capacidad: " + actual.getCapacidad() + "\n";
        }
        // Volver a lista de salas original
        salas.invertirIterativo();
        return Retorno.ok(mensaje);
    }

    @Override
    public Retorno listarEventos() {
        ListaEventoSE listaAux = new ListaEventoSE();
        for (int i = 0; i < eventos.longitud(); i++) {
            Evento actual = (Evento) eventos.obtener(i);
            listaAux.adicionar(actual);
        }
        //Ordenar segun codigo

        //listaAux.sort(null);
        // Mensaje a devolver
        String mensaje = "Listado de Eventos:\n";
        // while(miListaSE.)String mensaje = "Listado de Eventos:\n";
        for (int i = 0; i < listaAux.longitud(); i++) {
            Evento e = (Evento) listaAux.obtener(i);
            mensaje += "-Codigo: " + e.getCodigo()
                    + ", Descripcion: " + e.getDescripcion()
                    + ", Sala: " + e.getSala()
                    + ", Entradas disponibles: " + e.getEntradasDisponibles()
                    + ", Entradas vendidas: " + e.getEntradasVendidas() + "\n";
        }

        return Retorno.ok(mensaje);
    }

    @Override
    public Retorno listarClientes() {
        ListaClienteSE listaAux = new ListaClienteSE();
        // Agregar los clientes a la lista auxiliar
        for (int i = 0; i < clientes.longitud(); i++) {
            listaAux.adicionar((Cliente) clientes.obtener(i));
        }
        // Si no esta ordenada, se ordena
        if (!clientes.estaOrdenada()) {
          //  listaAux.ordenar();
        }

        // Se genera el mensaje con el listado
        String mensaje = "Listado de Clientes:\n";
        for (int i = 0; i < listaAux.longitud(); i++) {
            Cliente c = (Cliente) listaAux.obtener(i);
            mensaje += "- " + c.getCedula() + ", Nombre: " + c.getNombre() + "\n";
        }

        return Retorno.ok(mensaje);
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        int filas = vistaSala.length;
        int columnas = vistaSala[0].length;
        int columnasOptimas = 0;

        // Se recorre cada columna
        for (int col = 0; col < columnas; col++) {
            int libres = 0;
            int ocupadosConsecutivos = 0;
            int maxOcupadosConsecutivos = 0;

            // Se recorre cada fila de esa columna
            for (int fila = 0; fila < filas; fila++) {
                String valor = vistaSala[fila][col];
                switch (valor) {
                    // Si esta libre se suma a la variable y se cortan los OcupadosConsecutivos
                    case "X":
                        libres++;
                        ocupadosConsecutivos = 0;
                        break;
                    //Si esta ocupado se suma a ocupadosConsecutivos
                    case "O":
                        ocupadosConsecutivos++;
                        // Se compara para establecer el maximo
                        if (ocupadosConsecutivos > maxOcupadosConsecutivos) {
                            maxOcupadosConsecutivos = ocupadosConsecutivos;
                        }
                        break;
                    // Si es "#" se cortan los ocupadosConsecutivos
                    default:
                        ocupadosConsecutivos = 0;
                        break;
                }
            }
            // Se verifica que la columna sea optima
            if (maxOcupadosConsecutivos > libres) {
                columnasOptimas++;
            }
        }
        String mensaje = "";
        if (columnasOptimas > 2) {
            mensaje += "Es optimo.";
        } else {
            mensaje += "No es optimo";
        }
        return Retorno.ok(mensaje);
    }

    @Override
    public Retorno listarClientesDeEvento(String código, int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEsperaEvento() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasXDia(int mes) {
        return Retorno.noImplementada();
    }

}
