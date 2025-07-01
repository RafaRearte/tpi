package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class JugadorEntity {
    @Id
    private Long idJugador;

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public Long getIdJugador() {
        return idJugador;
    }
}
