package uvg.com;

import org.junit.*;
import static org.junit.Assert.*;

public class VectorHeapTest {

    private VectorHeap<Paciente> heap;

    @Before
    public void setUp() {
        heap = new VectorHeap<>();
    }

    // Test que verifica que los pacientes se insertan y se atienden en orden de prioridad
    @Test
    public void testAddAndRemovePaciente() {
        Paciente p1 = new Paciente("Maria", "apendicitis", 'A');
        Paciente p2 = new Paciente("Juan", "fractura", 'C');
        Paciente p3 = new Paciente("Carmen", "parto", 'B');

        heap.add(p2);
        heap.add(p1);
        heap.add(p3);

        Assert.assertEquals("El primer paciente atendido debería ser Maria", "Maria", heap.remove().getNombre());
        Assert.assertEquals("El segundo paciente atendido debería ser Carmen", "Carmen", heap.remove().getNombre());
        Assert.assertEquals("El tercer paciente atendido debería ser Juan", "Juan", heap.remove().getNombre());
    }

    // Test que comprueba que el heap está vacío al inicio
    @Test
    public void testHeapInitiallyEmpty() {
        Assert.assertTrue("El heap debería estar vacío al inicio", heap.isEmpty());
    }

    // Test que comprueba que el heap no está vacío después de agregar un elemento
    @Test
    public void testHeapNotEmptyAfterInsert() {
        Paciente p = new Paciente("Lorenzo", "chikunguya", 'E');
        heap.add(p);
        Assert.assertFalse("El heap no debería estar vacío después de insertar un paciente", heap.isEmpty());
    }

    // Test que verifica que eliminar de un heap vacío lanza excepción
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveFromEmptyHeap() {
        heap.remove(); // Esto debe lanzar una excepción
    }
}

