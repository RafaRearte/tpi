## <u>TPI Estanciero Laboratorio III</u>

**Este documento describe las clases base necesarias para resolver el problema del juego Estanciero. Cada clase incluye una lista de atributos, métodos y responsabilidades asociadas.**

## Clase Juego
*Va a contener toda la información acerca de las partidas que se desarrollen.*

### Atributos:
- id
- listaJugadores
- fechaInicio
- fechaFin
- ajustes
- tablero
- banco
- ganador

### Métodos:
- iniciarPartida(): Inicia el juego, pasándole los parámetros correspondientes de configuración.
- finalizarPartida(): Finaliza el juego, agregando la fecha de finalización y el ganador a la tabla Partida.
- actualizarPartida(): Método para actualizar algún dato del juego.
- agregarJugadores(): Agrega jugadores a la partida.
- actualizarListaQuiebras(): Agrega jugadores con bancarrota de la partida.
- siguienteTurno(): Método para indicar que el jugador X ya terminó con su turno, y que proceda el siguiente.


## Clase Peones
*Va a contener toda la información y los métodos que se pueden realizar desde la clase Fichas (Peones).*

### Atributos:
- color
- saldo
- bancarrota
- juego
- posicionActual
- ordenJuego
- jugador

### Métodos:
- actualizarSaldo(): Actualiza el saldo del jugador.
- comprarEscritura(): Compra una escritura y la pone a su nombre.
- venderEscritura(): Vende una escritura del jugador.
- comprarMejora(): Mejora una escritura pasada por parámetros.
- actualizarPosicion(): Actualiza la posición actual del jugador.
- declararBancarrota(): Declara la bancarrota del jugador.


## Clase Jugadores
*Va a contener toda la información y los métodos que se pueden realizar desde la clase Jugadores.*

### Atributos:
- nombre
- año

### Métodos:
- obtenerDatos(): Obtiene la información del jugador, en este caso su nombre y fecha de nacimiento.


## Clase Casillas ☑️
*Va a contener toda la información relacionada a las casillas.*

### Atributos:
- descripcion
- tipoDeCasilla
- disponibilidad
- precio
- recibirPago
- aceptarMejora
- carta

### Métodos:
- consultarDisponibilidad(): Consulta si la propiedad está disponible si es un casillero del tipo correspondiente.
- actualizarDisponibilidad(): Actualiza la disponibilidad del casillero si otro jugador cae en el mismo.
- cobrarAlquiler(): Colecta el alquiler de las propiedades en caso de corresponder.
- actualizarPrecio(): Actualiza el precio en caso de que se haya realizado una mejora a la propiedad.
- tomarCarta(): Si el jugador cae en una casilla de Suerte o Destino se tomará la carta correspondiente.
- cobrarPremio(): Recoge el premio si al jugador le tocó en una carta de Suerte o Destino.
- pagarImpuesto(): Paga el impuesto si al jugador le tocó en una carta de Suerte o Destino.
- salirDeCarcel(): El jugador sale de la cárcel.
- consultarMejoras(): Consulta las mejoras de propiedad para luego poder calcular el alquiler correspondiente a cada una.
- devolverCarta(): Devuelve la carta al fondo del mazo.


## Clase Cartas
*La clase utilizada para las cartas, las cuales pueden ser de distinto tipo.*

### Atributos
- tablero
- descripcion
- tipoDeCartas
- condicion
- precio

### Métodos
- agregarCarta(): Agrega una carta.
- actualizarCarta(): Edita una carta.
- eliminarCarta(): Elimina una carta.
- obtenerCartaAleatoria(): Obtiene una carta.
- mezclarCartas(): Mezcla las cartas del mazo.
- mostrarMazoDeCartas(): Muestra las cartas disponibles.

## Clase TipoDeCartas
*Clase secundaria utilizada para demarcar cada tipo de carta.*

### Atributos
- tipo

### Métodos
- agregarTipoDeCarta(): Agrega el tipo de carta.
- obtenerTiposDeCartas(): Obtiene el tipo de carta.
- eliminarTipoDeCarta(): Elimina el tipo de carta.
- actualizarTipoDeCarta(): Modifica el tipo de carta.

## Clase Tableros
La clase utilizada para el tablero, donde se unen los casilleros con el tablero.

### Atributos
- casilla

### Métodos
- moverJugador(): Mueve la posición de un jugador en el tablero.
- agregarCasilla(): Agrega un casillero, por si hiciese falta.
- obtenerCasillas(): Obtiene los casilleros del tablero.
- aplicarEfectoCasilla(): Aplica el efecto del casillero al jugador.


## Clase Dificultades
*Clase utilizada para los distintos modos de dificultad.*

### Atributos
- dificultad

### Métodos
- obtenerDificultades(): Obtiene los tipos de dificultades.
- agregarDificultad(): Agrega los tipos de dificultades.
- eliminarDificultad(): Elimina los tipos de dificultades.
- actualizarDificultad(): Modifica los tipos de dificultades.


## Clase TiposDeCasillas
*Clase secundaria utilizada para demarcar los tipos de casilla.*

### Atributos
- descripcion
- cantidad

### Métodos
- agregarTipoDeCasilla(): Agrega un tipo de casillero.
- eliminarTipoDeCasilla(): Elimina un tipo de casillero.
- obtenerTiposDeCasilla(): Obtiene los tipos de casilleros.
- actualizarTipoDeCasilla(): Modifica un tipo de casillero.


## Clase Colores
*Clase para identificar los colores de los peones.*

### Atributos
- color

### Métodos
- agregarColor(): Agrega un color.
- eliminarColor(): Elimina un color.
- obtenerColores(): Devuelve el listado de los colores.
- actualizarColor(): Modifica el color.


## Clase Escrituras
*Clase que representa una propiedad en el juego.*

### Atributos
- peon
- nombre
- tipoEscritura
- precio
- valorHipotecado
- disponibilidad
- mejora


## Clase TiposEscritura
*Clase utilizada para demarcar los distintos tipos de propiedades que hay en el juego.*

### Atributos
- descripcion

### Métodos
- agregarTipoEscritura(): Agregar un tipo de Escritura.
- eliminarTipoEscritura(): Eliminar un tipo de Escritura.
- obtenerTipoEscritura(): Obtener un tipo de Escritura.
- actualizarTipoEscritura(): Utilizada para actualizar el tipo de Escritura.


## Clase Mejoras
*Clase utilizada para marcar cuando una propiedad tiene una mejora aplicada.*

### Atributos
- descripcion
- valor

### Métodos
- agregarMejoras(): Agrega una mejora a una Escritura.
- eliminarMejoras(): Elimina la mejora en una Escritura.
- obtenerMejoras(): Obtener las mejoras que tiene una Escritura.
- aplicarMejoras(): Aplica la mejora a una Escritura.