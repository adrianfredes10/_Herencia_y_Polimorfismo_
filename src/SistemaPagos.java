import java.util.ArrayList;
import java.util.List;

abstract class MetodoPago {
    protected String titular;
    protected String numero;

    public MetodoPago(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago();
}

interface Cancelable {
    void cancelarPago();
}

class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private String codigoSeguridad;

    public TarjetaCredito(String titular, String numero, String fechaExpiracion, String codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

  
    public void realizarPago() {
        System.out.println("Pago realizado con tarjeta de crédito.");
    }

   
    public void cancelarPago() {
        System.out.println("Pago con tarjeta de crédito cancelado.");
    }
}

class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, String numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    
    public void realizarPago() {
        System.out.println("Pago realizado con PayPal.");
      }
        
    public void cancelarPago() {
        System.out.println("Pago con PayPal cancelado.");
    }
}

class Pagos {
    private List<MetodoPago> metodosPago = new ArrayList<>();

    public void agregarMetodoPago(MetodoPago metodoPago) {
        metodosPago.add(metodoPago);
    }

    public void realizarPagos() {
        for (MetodoPago metodoPago : metodosPago) {
            metodoPago.realizarPago();
        }
    }

    public void cancelarPagos() {
        for (MetodoPago metodoPago : metodosPago) {
            if (metodoPago instanceof Cancelable) {
                ((Cancelable) metodoPago).cancelarPago();
            }
        }
    }
}

public class SistemaPagos {
    public static void main(String[] args) {
        Pagos pagos = new Pagos();

        MetodoPago tarjeta = new TarjetaCredito("Juan Perez", "123456789", "12/25", "123");
        MetodoPago paypal = new PayPal("Ana Garcia", "987654321", "ana@example.com");

        pagos.agregarMetodoPago(tarjeta);
        pagos.agregarMetodoPago(paypal);

        pagos.realizarPagos();
        pagos.cancelarPagos();
    }
}
