# NeoMonitor - Gestión de Infraestructura y Alertas

NeoMonitor es una aplicación de escritorio profesional diseñada para la monitorización de servidores y la gestión de incidencias de red. Desarrollada con **JavaFX** y **Spring Boot**, la herramienta permite un control centralizado de activos tecnológicos, integrando geolocalización en tiempo real y exportación de datos.

## Diagrama Entidad-Relación

![Base De Datos](https://github.com/user-attachments/assets/21354215-7b70-477f-bbd8-1d7711e1d652)


*Modelo de datos diseñado para la persistencia con JPA, mostrando las relaciones entre Administradores, Servidores y Alertas.*

## Tecnologías y Requisitos

Este proyecto cumple con los requisitos del módulo de Programación de 1º DAW:
- **Persistencia:** Spring Data JPA con base de datos **SQLite**.
- **Interfaz:** JavaFX (FXML) con navegación entre 3 vistas (Dashboard, Formulario, Alertas).
- **API REST:** Integración con `ip-api.com` para geolocalización mediante IP.
- **Lógica Avanzada:** Uso de **Java Streams** para filtrado y la interfaz **Comparable** para ordenación de alertas.
- **Ficheros:** Exportación de informes de servidores a formato **CSV**.

## Decisiones de Diseño

1. **Arquitectura por Capas:** Se ha implementado un patrón de 4 capas (Entity, Repository, Service, Controller) para asegurar que el código sea mantenible y escalable.
2. **Inyección de Dependencias:** Se utiliza el contexto de Spring para gestionar los servicios y repositorios, inyectándolos en los controladores de JavaFX mediante una `ControllerFactory`.
3. **Persistencia Automática:** Se ha configurado el sistema para que la base de datos SQLite se inicialice automáticamente con datos de prueba si se encuentra vacía, facilitando la demostración.
4. **Validación de Datos:** El formulario de servidores incluye validación para asegurar que el Administrador responsable existe antes de permitir el guardado (integridad referencial).

##Instalación y Ejecución

Para ejecutar la aplicación correctamente en entornos modernos de Java:

1. Clonar el repositorio.
2. Importar como proyecto **Maven** en IntelliJ IDEA.
3. Actualizar dependencias (Maven Reload).
4. **IMPORTANTE:** Ejecutar la aplicación desde la clase `org.neomonitor.jpa.Launcher`. Esto evita errores de módulos nativos de JavaFX.

## Estructura del Proyecto

```text
src/main/java/org/neomonitor/jpa/
├── Launcher.java (Punto de entrada)
├── JpaApplication.java (Configuración Spring/JavaFX)
├── controller/ (Controladores de la UI)
├── entity/ (Modelos JPA)
├── repository/ (Interfaces Spring Data)
└── service/ (Lógica de negocio y API)
