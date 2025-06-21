package ar.edu.utn.frc.tup.lciii.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MazoCartas {
    private TipoDeCarta tipo;
    private List<Carta> cartas;
    private List<Carta> cartasUsadas;
}
