package sistemaAutogestion;

import java.time.LocalDate;
import dominio.*;
import tads.*;

public class Sistema implements IObligatorio {

    private static Sistema sistema;
    private ListaSalaDE salas;
    private ListaEventoSE eventos;
    //private ListaClientes clientes;
    
    public Sistema(){
        this.salas = new ListaSalaDE();
       // this.eventos = new ListaEventosDE();
        //this.clientes = new ListaClientes();
    }
    
    @Override
    public Retorno crearSistemaDeGestion() {
        sistema = new Sistema();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
         if (capacidad <= 0) return Retorno.error2();
        //if (salas.existeSala(nombre)) return Retorno.error1();

        Sala nuevaSala = new Sala(nombre, capacidad);
       // salas.adicionar(nuevaSala);
        return Retorno.ok();
    }

    @Override
    public Retorno eliminarSala(String nombre) {
        if(salas.vacia()){
            return Retorno.error1();
        }
        for (int i = 0; i < salas.longitud(); i++) {
            Sala salaActual = (Sala) salas.obtener(i);
            if(salaActual.getNombre().equals(nombre)){
                salas.eliminar(i);
                return Retorno.ok("Si pudo eliminar la sala.");
            }
        }
        return Retorno.error2();
    }

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        // Validar aforo ingresado
        if(aforoNecesario <= 0){
            return Retorno.error2();
        }
        // Validar que el codigo no exista
        for (int i = 0; i < salas.longitud(); i++) {
            Evento e = (Evento) eventos.obtener(i);
            if(e.getCodigo().equals(codigo)){
                return Retorno.error1();
            }
        }
        // Buscar sala para el evento
        for(int i = 0; i < salas.longitud(); i++){
            Sala sala = (Sala) salas.obtener(i);
            if(sala.getCapacidad() >= aforoNecesario && !sala.estaOcupada(fecha)){
                // Se crea el evento y se lo asocia a la sala
                Evento e = new Evento(codigo, descripcion, aforoNecesario, fecha, sala);
                eventos.adicionarInicio(e);
                sala.agendarEvento(fecha);
                return Retorno.ok("Evento registrado correctamente.");
            }
        }
        return Retorno.error3();
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        return Retorno.noImplementada();
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
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEventos() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarClientes() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        return Retorno.noImplementada();
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
