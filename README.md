# LiterAlura 📚

**LiterAlura** es una aplicación en Java que permite interactuar con libros y autores utilizando la API pública [Gutendex](https://gutendex.com/).  
El proyecto utiliza **Spring Boot**, **Spring Data JPA** y una base de datos para almacenar información de libros y autores.

---

## 🔹 Funcionalidades

La aplicación cuenta con un menú interactivo en consola que permite:

1. **Buscar libros en Gutendex**  
   - Permite ingresar el nombre de un libro y obtiene información desde la API Gutendex.  
   - Los libros pueden ser guardados en la base de datos local.

2. **Listar libros guardados**  
   - Muestra todos los libros almacenados, incluyendo sus idiomas y autores.

3. **Listar autores guardados**  
   - Muestra todos los autores registrados en la base de datos.

4. **Listar idiomas disponibles**  
   - Muestra los idiomas de todos los libros guardados.

5. **Listar autores vivos en un año determinado**  
   - Permite ingresar un año y muestra los autores que estaban vivos en ese período.

0. **Salir de la aplicación**  

---

## 🔹 Tecnologías utilizadas

- Java 17+
- Spring Boot 3
- Spring Data JPA
- PostgreSQL / H2 (según configuración)
- Maven
- SLF4J (Logging)
- Gutendex API para consulta de libros

---

## 🔹 Instalación y ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/dnydeo/LiterAlura.git
cd LiterAlura

Configurar la base de datos en application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

Ejecutar la aplicación desde IntelliJ o desde la terminal:

./mvnw spring-boot:run

Ejemplo de uso:

===== MENÚ =====
1 - Buscar libro en Gutendex
2 - Listar libros guardados
3 - Listar autores guardados
4 - Listar idiomas disponibles
5 - Listar autores vivos en un año determinado
0 - Salir

!Seleccione una Opcion!: 1
Escribe el nombre del libro que deseas buscar: Pride and Prejudice
✅ Libro agregado: Pride and Prejudice
✅ Libro guardado en la base de datos
