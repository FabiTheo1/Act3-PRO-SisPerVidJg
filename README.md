# ⚔️ Motor RPG - Sistema de Gestión y Combate por Turnos

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MVC](https://img.shields.io/badge/Architecture-MVC-blue?style=for-the-badge)
![OOP](https://img.shields.io/badge/Paradigm-OOP-success?style=for-the-badge)

Este proyecto es un motor de juego de rol (RPG) basado en consola, desarrollado en Java. Demuestra el dominio de la **Programación Orientada a Objetos (POO)** mediante la implementación de herencia profunda, polimorfismo, interfaces, manejo de excepciones personalizadas y el patrón de diseño **Composición**.

Todo el sistema está estructurado bajo la arquitectura **Modelo-Vista-Controlador (MVC)** para garantizar un código limpio, escalable y mantenible.

---

## 🌟 Características Principales

- **Gestor de Personajes:** Creación dinámica de 11 clases únicas de personajes con atributos y habilidades propias.
- **Sistema de Armas (Composición):** Las armas son objetos independientes inyectados a los personajes. Tienen daño base, munición finita/infinita y lógicas de recarga.
- **Combate por Turnos:** Un mini-juego integrado donde el usuario puede enfrentar a sus personajes contra un NPC (Orco Berserker) usando ataques, habilidades especiales, lanzamiento de objetos y recargas.
- **Manejo de Excepciones:** Control de errores personalizado para situaciones críticas de combate (ej. `SinMunicionException`, `ManaInsuficienteException`, `AccionInvalidaException`).

---

## 🏗️ Arquitectura y Patrones de Diseño

El proyecto está dividido en paquetes siguiendo el patrón **MVC**:

- `MVC.app`: Contiene el `App.java` (Controlador Principal) con el bucle del juego y menús interactivos.
- `MVC.controller`: Contiene el `GestorJuego.java`, encargado de la persistencia en memoria (ArrayList) y validaciones de la lista de personajes.
- `MVC.model`: El corazón lógico del juego. Contiene la jerarquía de clases de Personajes, las Interfaces, las Armas y las Excepciones.
- `MVC.view`: Contiene `ConsolaView.java`, aislando completamente los `System.out` y `Scanner` para que el modelo nunca dependa de la consola.

### 🌳 Jerarquía de Clases (Modelo)

```text
Personaje (Abstracta)
 │
 ├── PersonajeFisico (Abstracta)
 │    ├── Guerrero (Usa Ira y Hachas)
 │    ├── Caballero (Usa Armadura y Lanza/Mandoble)
 │    ├── Asesino (Usa Sigilo, Puntos de Combo y Cuchillos)
 │    │
 │    └── CombatienteDistancia (Abstracta) [Usa Composición: Arma]
 │         ├── Arquero
 │         ├── Ballestero
 │         ├── Mosquetero
 │         └── Escaramuzador (Doble Composición: Honda/Jabalina + Escudo + Daga)
 │
 └── PersonajeMagico (Abstracta)
      ├── Mago (Hechizos Elementales y Curación)
      ├── Nigromante (Drenaje de Almas y Esqueletos)
      ├── Druida (Cambio de Forma y Regeneración)
      └── ArqueroMagico (Híbrido Magia/Arco)
```
## ⚙️ Interfaces Implementadas
Curacion: Para personajes que pueden curarse a sí mismos o a aliados (Mago, Druida).

Sigiloso: Para personajes que pueden ocultarse y evitar daño (Asesino, Nigromante, Druida).

Lanzar: Para habilidades que requieren arrojar objetos consumibles (Guerrero, Asesino).

Defendible: Para clases enfocadas en mitigar daño directo.

## 🚀 Cómo Ejecutar el Proyecto
Clona este repositorio en tu máquina local:

git clone <URL_DEL_REPOSITORIO>
Abre el proyecto en tu IDE favorito (Eclipse, IntelliJ IDEA, VS Code).

Localiza el archivo principal App.java en la ruta src/MVC/app/App.java.

Ejecuta el método main.

¡Sigue las instrucciones del menú interactivo en la consola!

## 👨‍💻 Equipo de Desarrollo
Este proyecto fue desarrollado de forma colaborativa:MiembroRol / Contribuciones Principales
Daniel Desarrollo del Controlador (`GestorJuego`), validaciones de la lista de personajes y creación de la estructura base del Modelo (`Personaje`).
Fabian Director de Juego. Integración MVC principal (App.java, ConsolaView.java), Patrón Composición (Arma.java), Clases CombatienteDistancia y sub-clases. Menús y Motor de Combate contra el Orco.
Jeremi Desarrollo de PersonajeFisico, Excepciones personalizadas (MVC.exceptions), Clases Guerrero, Caballero, Asesino. Lógica de impactos críticos y combos.
Jonay Desarrollo de PersonajeMagico, Clases Mago, Nigromante, Druida. Lógica de consumo/regeneración de maná, transformaciones animales y mecánicas de recolección de almas.
