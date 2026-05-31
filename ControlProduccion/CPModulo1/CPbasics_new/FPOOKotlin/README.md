# FPOO Kotlin - Ejercicios de Programacion Orientada a Objetos

Ejercicios practicos de POO en Kotlin para la materia Fundamentos de Programacion Orientada a Objetos.

## Como ejecutar

```bash
kotlin Ejercicio01_Libro.kt
kotlin Ejercicio02_ProductoInventario.kt
kotlin Ejercicio03_Vehiculo.kt
kotlin Ejercicio04_MetodoPago.kt
kotlin Ejercicio05_Biblioteca.kt
```

O desde IntelliJ IDEA: abrir el archivo y ejecutar `main()`.

---

## Ejercicio 1 — Clase Libro

Crear una clase **Libro** con:

- Titulo
- Autor
- Anio de publicacion
- Numero de paginas

Debe incluir metodos para:

- Mostrar la informacion del libro
- Indicar si el libro es antiguo o reciente

**Condicion:** Un libro es antiguo si fue publicado antes del anio 2000.

### Evidencias requeridas

- Codigo fuente en Kotlin
- Captura de ejecucion
- Explicacion breve de la clase y los objetos creados
- Enlace al repositorio Git publico

---

## Ejercicio 2 — Encapsulamiento con ProductoInventario

Crear una clase **ProductoInventario** con:

- Codigo
- Nombre
- Precio **privado**
- Stock **privado**

Debe incluir metodos para:

- Consultar precio y stock
- Aumentar stock
- Disminuir stock
- Cambiar precio

**Condiciones:**

- El precio no puede ser negativo
- El stock no puede quedar por debajo de cero

### Evidencias requeridas

- Codigo fuente completo
- Capturas mostrando actualizacion de precio y stock
- Explicacion de como se aplico el encapsulamiento
- Enlace al repositorio Git publico

---

## Ejercicio 3 — Herencia con vehiculos

Crear una clase base **Vehiculo** con:

- Marca
- Modelo
- Anio

**Metodo:** `mostrarDatos()`

Crear las clases hijas:

- **Auto** — atributo propio: numero de puertas
- **Motocicleta** — atributo propio: cilindrada

Cada clase hija debe sobrescribir el metodo `mostrarDatos()`.

### Evidencias requeridas

- Codigo fuente
- Captura de ejecucion mostrando objetos de cada tipo
- Explicacion breve sobre el uso de herencia
- Repositorio Git publico actualizado

---

## Ejercicio 4 — Polimorfismo con metodos de pago

Crear una interfaz **MetodoPago** con el metodo:

- `procesarPago(monto: Double)`

Implementar al menos tres metodos de pago:

- **PagoEfectivo**
- **PagoTarjeta**
- **PagoTransferencia**

Cada clase debe procesar el pago de forma diferente y mostrar un mensaje propio.
El programa debe recorrer una lista de metodos de pago y ejecutar el pago correspondiente.

### Evidencias requeridas

- Codigo fuente en Kotlin
- Captura de resultados
- Explicacion de como se aplico el polimorfismo
- Enlace al repositorio Git publico

---

## Ejercicio 5 — Mini sistema de biblioteca

Desarrollar un sistema orientado a objetos para gestionar prestamos de libros.

El sistema debe permitir:

- Registrar libros
- Listar libros disponibles
- Prestar un libro
- Devolver un libro
- Buscar libro por titulo

Cada libro debe tener:

- ID
- Titulo
- Autor
- Estado: disponible o prestado

**Requisitos:**

- Usar clases y objetos
- Aplicar encapsulamiento
- Utilizar listas o colecciones
- Validar que no se preste un libro ya prestado
- Validar que solo se devuelvan libros prestados

### Evidencias requeridas

- Codigo fuente en Kotlin
- Captura de ejecucion
- Explicacion del funcionamiento del sistema
- Enlace al repositorio Git publico

---

## Repositorio

[URL del repositorio Git publico]
