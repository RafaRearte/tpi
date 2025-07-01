package ar.edu.utn.frc.tup.lciii.Models.Cartas;

import ar.edu.utn.frc.tup.lciii.Models.Jugador.Jugador;
import ar.edu.utn.frc.tup.lciii.Models.Tablero;
import ar.edu.utn.frc.tup.lciii.Services.CartaService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Carta implements CartaService {

    private int id;
    private CartaTipo cartaTipo;
    private TipoCarta typeCard;
    private String description;
    private Tablero tablero;

    private Jugador jugador;

    public Carta(int id, CartaTipo cartaTipo, TipoCarta tipoCarta, String descripcion) {
        this.id = id;
        this.cartaTipo = cartaTipo;
        this.typeCard = tipoCarta;
        this.description = descripcion;
    }

    public Carta() {

    }

    public void verCarta() {
        System.out.println("Obtuvo la carta");
        System.out.println("descripcion : " + description);
        System.out.println("Tipo : " + cartaTipo);
        System.out.println("Tipo de carta: " + typeCard);

    }

    public boolean verTipoDeCarta(TipoCarta tipoCarta) {

        if (this.typeCard == tipoCarta) {
            return true;
        } else {
            return false;
        }

    }

    public abstract void ejecutarAccion(Jugador jugador);

}
