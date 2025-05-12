/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pesce
 */
public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {
    }

    @Before
    public void setUp() {
        //Completar para primera entrega
        //miSistema
        miSistema = new Sistema();

    }

    @Test
    public void testCrearSistemaDeGestion() {
        Retorno r = miSistema.crearSistemaDeGestion();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaok() {
        //Completar para primera entrega

        Retorno r = miSistema.registrarSala("Nombre", 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaError1() {
        //Completar para primera entrega
        miSistema.registrarSala("Ana", 3);
        Retorno r = miSistema.registrarSala("Ana", 3);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testRegistrarSalaError2() {
        //Completar para primera entrega
        Retorno r = miSistema.registrarSala("Nombre", 0);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testEliminarSalaOk() {
        //Completar para primera entrega
        miSistema.registrarSala("Nombre", 4);
        Retorno r = miSistema.eliminarSala("Nombre");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testEliminarSalaError1() {

        Retorno r = miSistema.eliminarSala("Nombre");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testRegistrarEventoOk() {
        miSistema.registrarSala("Sala1", 20);
        Retorno r = miSistema.registrarEvento("Nombre", "desc", 15, LocalDate.EPOCH);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarEventoError1() {
        miSistema.registrarSala("Sala1", 20);
        miSistema.registrarEvento("Nombre", "desc", 15, LocalDate.EPOCH);
        Retorno r = miSistema.registrarEvento("Nombre", "desc", 15, LocalDate.EPOCH);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testRegistrarEventoError2() {
        Retorno r = miSistema.registrarEvento("Nombre", "desc", 0, LocalDate.EPOCH);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testRegistrarEventoError3() {
        miSistema.registrarSala("Sala1", 10);
        Retorno r = miSistema.registrarEvento("Nombre", "desc", 15, LocalDate.EPOCH);
        assertEquals(Retorno.error3().resultado, r.resultado);
    }


    @Test
    public void testRegistrarClienteOk() {
        //Completar para primera entrega
        Retorno r = miSistema.registrarCliente("12345678", "Nombre");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testRegistrarClienteError1() {
        //Completar para primera entrega 
        Retorno r = miSistema.registrarCliente("123456", "Nombre");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testRegistrarClienteError2() {
        //Completar para primera entrega
        miSistema.registrarCliente("12345678", "Nombre");
        Retorno r = miSistema.registrarCliente("12345678", "Nombre");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testListarSalas() {
        miSistema.registrarSala("Nombre1", 15);
        Retorno r = miSistema.listarSalas();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testListarEventos() {
        //Completar para primera entrega

        Retorno r = miSistema.listarEventos();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testListarClientes() {
        //Completar para primera entrega

        Retorno r = miSistema.listarClientes();
        assertEquals(Retorno.ok().resultado, r.resultado);
    }

    @Test
    public void testEsSalaOptima() {
        //Completar para primera entrega
        //Retorno r = miSistema.esSalaOptima();
        // assertEquals(Retorno.ok().resultado ,r.resultado);
    }

}
