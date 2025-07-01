package ar.edu.utn.frc.tup.lciii.Models.MenuPrincipal;

import ar.edu.utn.frc.tup.lciii.services.strategy.MenuPrincipalStrategy;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JugadorPrincipal {

    private MenuPrincipalStrategy menuPrincipalStrategy;

    public JugadorPrincipal() {
    }

    public JugadorPrincipal(MenuPrincipalStrategy menuPrincipalStrategy) {
        this.menuPrincipalStrategy = menuPrincipalStrategy;
    }

    public void cambiarEstrategia(MenuPrincipalStrategy menuPrincipalStrategy) {
        this.menuPrincipalStrategy = menuPrincipalStrategy;
    }

    public void adjustMainEstanciero() {
        if (menuPrincipalStrategy != null){
            System.out.println();
            menuPrincipalStrategy.ajustarMenuPrincipal();
        } else {
            System.out.println("No se ha definido una estrategia para el menu principal.");
        }
    }
}
