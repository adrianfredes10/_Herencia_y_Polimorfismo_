import java.util.ArrayList;
import java.util.List;

abstract class CanalNotificacion {
    protected String usuario;
    protected String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public abstract void enviarNotificacion();
}

interface Personalizable {
    void personalizarMensaje(String mensajePersonalizado);
}

class CorreoElectronico extends CanalNotificacion implements Personalizable {
    private String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Notificación enviada por correo electrónico a " + direccionCorreo);
    }

    @Override
    public void personalizarMensaje(String mensajePersonalizado) {
        mensaje = mensajePersonalizado;
    }
}

class MensajeTexto extends CanalNotificacion implements Personalizable {
    private String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Notificación enviada por mensaje de texto al " + numeroTelefono);
    }

    @Override
    public void personalizarMensaje(String mensajePersonalizado) {
        mensaje = mensajePersonalizado;
    }
}

class Notificaciones {
    private List<CanalNotificacion> canales = new ArrayList<>();

    public void agregarCanal(CanalNotificacion canal) {
        canales.add(canal);
    }

    public void enviarNotificaciones() {
        for (CanalNotificacion canal : canales) {
            canal.enviarNotificacion();
        }
    }

    public void personalizarMensajes(String mensajePersonalizado) {
        for (CanalNotificacion canal : canales) {
            if (canal instanceof Personalizable) {
                ((Personalizable) canal).personalizarMensaje(mensajePersonalizado);
            }
        }
    }
}

public class SistemaNotificaciones {
    public static void main(String[] args) {
        Notificaciones notificaciones = new Notificaciones();

        CanalNotificacion correo = new CorreoElectronico("Usuario1", "Mensaje original", "usuario1@example.com");
        CanalNotificacion mensaje = new MensajeTexto("Usuario2", "Mensaje original", "555123456");

        notificaciones.agregarCanal(correo);
        notificaciones.agregarCanal(mensaje);

        notificaciones.personalizarMensajes("Mensaje personalizado para todos los canales.");
        notificaciones.enviarNotificaciones();
    }
}
