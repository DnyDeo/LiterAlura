    package com.challenge.LiterAlura.principal;

    import com.challenge.LiterAlura.model.Autor;
    import com.challenge.LiterAlura.model.DatosLibro;
    import com.challenge.LiterAlura.model.Libro;
    import com.challenge.LiterAlura.model.RespuestaGutendex;
    import com.challenge.LiterAlura.repository.AutorRepository;
    import com.challenge.LiterAlura.repository.LibroRepository;
    import com.challenge.LiterAlura.service.ConsumoAPI;
    import com.challenge.LiterAlura.service.ConvierteDatos;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.stereotype.Component;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;
    import java.util.Scanner;

    @Component
    public class Principal {
        private static final Logger log = LoggerFactory.getLogger(Principal.class);
        private Scanner teclado = new Scanner(System.in);
        private ConsumoAPI consumoApi = new ConsumoAPI();
        private final String URL_BASE = "https://gutendex.com/books/?search=";
        private ConvierteDatos conversor = new ConvierteDatos();
        private final LibroRepository libroRepository;
        private final AutorRepository autorRepository;

        public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
            this.libroRepository = libroRepository;
            this.autorRepository = autorRepository;
        }

        @Transactional
        public void mostrarMenu() {
            int opcion = -1;
            while (opcion != 0) {
                System.out.println("""
                ===== MENÚ =====
                1 - Buscar libro en Gutendex
                2 - Listar libros guardados
                3 - Listar autores guardados
                4 - Listar idiomas disponibles
                5 - Listar autores vivos en un año determinado
                0 - Salir""");
                System.out.println(" !Seleccione una Opcion!: ");

                opcion = teclado.nextInt();
                teclado.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> {
                        DatosLibro datos = getDatosLibro();
                        if (datos != null) {
                            guardarLibro(datos); // aquí guardamos en la BD
                            System.out.println("✅ Libro agregado: " + datos);
                            System.out.println("✅ Libro guardado en la base de datos");
                        }
                    }
                    case 2 -> {
                        var libros = libroRepository.findAll();
                        if (libros.isEmpty()) {
                            System.out.println("⚠️ No hay libros registrados en la base de datos.");
                        } else {
                            System.out.println("\n📚 Libros guardados en la base de datos:");
                            libros.forEach(libro -> {
                                System.out.println("Título: " + libro.getTitulo());
                                System.out.println("Idiomas: " + libro.getIdiomas());
                                System.out.println("Autores: ");
                                libro.getAutores().forEach(a ->
                                        System.out.println("   - " + a.getNombre() + " (" + a.getFechaDeNacimiento() + " - " + a.getFechaDeFallecimiento() + ")")
                                );
                                System.out.println("─────────────────────────────");
                            });
                        }
                    }
                    case 3 ->{
                        listarAutores();
                    }
                    case 4 ->{
                        listarIdiomas();
                    }
                    case 5 ->{
                        listarAutoresVivosEnAnio();
                    }
                    case 0 -> System.out.println("👋 Saliendo de la aplicación...");

                    default -> System.out.println("❌ Opción inválida.");
                }
            }
        }

        private DatosLibro getDatosLibro() {
            System.out.println("Escribe el nombre del libro que deseas buscar");
            var nombreLibro = teclado.nextLine();

            var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
            //System.out.println("🔎 JSON recibido: " + json);

            RespuestaGutendex respuesta = conversor.obtenerDatos(json, RespuestaGutendex.class);

            if (respuesta.getResults() != null && !respuesta.getResults().isEmpty()) {
                DatosLibro libro = respuesta.getResults().get(0); // el primero
                return libro;
            } else {
                System.out.println("⚠️ No se encontraron libros con ese nombre.");
                return null;
            }
        }

        private void guardarLibro(DatosLibro datos) {

            // Verificar si ya existe
            if (libroRepository.findByTitulo(datos.getTitulo()).isPresent()) {
                System.out.println("⚠️ El libro '" + datos.getTitulo() + "' ya está en la base de datos.");
                return;
            }

            Libro libro = new Libro();
            libro.setTitulo(datos.getTitulo());
            libro.setIdiomas(datos.getIdiomas());

            List<Autor> autores = datos.getAutores().stream()
                    .map(a -> new Autor(a.getNombre(), a.getFechaDeNacimiento(), a.getFechaDeFallecimiento(), libro))
                    .toList();

            libro.setAutores(autores);
            libroRepository.save(libro);
        }

        private void listarAutores() {
            var autores = autorRepository.findAll();

            if (autores.isEmpty()) {
                System.out.println("📭 No hay autores registrados.");
            } else {
                System.out.println("👨‍🏫 Autores registrados en la base de datos:");
                autores.forEach(a -> {
                    System.out.println(a.getNombre() +
                            " (" + a.getFechaDeNacimiento() + " - " + a.getFechaDeFallecimiento() + ")");
                });
            }
        }

        private void listarIdiomas() {
            List<String> idiomas = libroRepository.findIdiomasDisponibles();

            if (idiomas.isEmpty()) {
                System.out.println("No hay idiomas registrados en la base de datos.");
            } else {
                System.out.println("Idiomas disponibles:");
                idiomas.forEach(System.out::println);
            }
        }

        private void listarAutoresVivosEnAnio() {
            System.out.print("Ingrese el año: ");
            int anio = teclado.nextInt();
            teclado.nextLine(); // limpiar buffer

            List<Autor> autores = autorRepository.findAutoresVivosEnAnio(anio);


            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio);
            } else {
                System.out.println("Autores vivos en el año " + anio + ":");
                autores.forEach(System.out::println);
            }
        }
    }
