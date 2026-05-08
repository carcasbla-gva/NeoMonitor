# 🖥️ NetMonitor - Gestor de Infraestructura y Alertas

**Proyecto Final: Programación DAW** **Desarrollo de Aplicaciones Web - JavaFX + Spring JPA**

## 1. Descripción del Proyecto
NetMonitor es una solución integral diseñada para la gestión y monitorización de activos de red. La aplicación permite a los administradores de sistemas registrar servidores, supervisar su estado operativo en tiempo real y gestionar un histórico detallado de alertas técnicas (caídas de servicio, picos de carga, etc.). El objetivo es integrar los conocimientos de Programación Orientada a Objetos, persistencia de datos y desarrollo de interfaces gráficas adquiridos durante el curso.

## 2. Decisiones de Diseño y Arquitectura
Para garantizar un código limpio, escalable y mantenible, se han tomado las siguientes decisiones de diseño:

* **Arquitectura por Capas:** El código se organiza de forma modular para separar responsabilidades:
    * **Modelo:** Entidades JPA que definen la estructura de la base de datos.
    * **Repositorio:** Interfaces de Spring Data para el acceso eficiente a los datos.
    * **Servicio:** Lógica de negocio, consumo de APIs y procesamiento de datos con Streams.
    * **Controlador/Vista:** Interfaz gráfica desarrollada con JavaFX para la interacción con el usuario.
* **Persistencia Robusta:** Uso de Spring JPA para gestionar las relaciones entre entidades y asegurar la integridad de la información.
* **Integración de API Externa:** Se utiliza la API **IP-API** para geolocalizar automáticamente cada servidor mediante su dirección IP, aportando contexto físico a la infraestructura digital.
* **Gestión de Datos Dinámica:** Implementación de *Java Streams* para realizar operaciones de filtrado y búsqueda avanzada sobre las colecciones de servidores y alertas sin penalizar el rendimiento de la base de datos.

## 3. Modelo de Datos (Diagrama Entidad-Relación)
La base de datos relacional se fundamenta en tres entidades principales:

1.  **Administrador:** Gestiona el perfil del técnico responsable.
2.  **Servidor:** Almacena datos técnicos (IP, SO, estado) y está vinculado a un administrador.
3.  **Alerta:** Registro cronológico de incidencias vinculadas a un servidor específico.

**Relaciones:**
* **Administrador (1) ↔ (N) Servidor:** Un administrador supervisa múltiples servidores.
* **Servidor (1) ↔ (N) Alerta:** Un servidor genera un historial de múltiples alertas técnicas.

## 4. Requisitos Técnicos Implementados
* **Persistencia:** Mínimo 3 entidades JPA con relaciones complejas y operaciones CRUD completas.
* **Interfaz Gráfica:** Mínimo 3 vistas en JavaFX con navegación fluida y componentes dinámicos (TableView).
* **Consumo REST:** Peticiones HTTP para la obtención de datos de ubicación en formato JSON.
* **Ficheros:** Sistema de exportación de informes técnicos a formato CSV/TXT.
* **Lógica Java Avanzada:** Uso de interfaces (Comparable), Colecciones (ArrayList/HashMap) y Streams.

## 5. Instrucciones de Instalación
*(Se completará tras el despliegue del código fuente en las fases finales del proyecto).*

---
*Este proyecto cumple con los requisitos mínimos obligatorios establecidos en la guía docente del módulo de Programación DAW.*
