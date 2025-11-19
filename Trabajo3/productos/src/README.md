📌 Enunciado del Proyecto

El presente proyecto consiste en el diseño, implementación y despliegue en la nube de un sistema web basado en servicios REST, que permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre un conjunto de registros almacenados en un archivo XML estructurado.

El objetivo principal es demostrar el uso adecuado de servicios web, intercambio de datos mediante XML y la integración de un cliente web para la interacción con el usuario.

El sistema expone un servicio backend encargado de gestionar la información utilizando un árbol XML como fuente de datos persistente. Todas las operaciones del CRUD se realizan directamente sobre dicho archivo XML, empleando una estructura jerárquica que almacena elementos como:

Identificadores

Nombres

Descripciones

El frontend consiste en una aplicación web desarrollada con HTML, CSS y JavaScript, la cual se comunica con el backend mediante solicitudes HTTP para realizar las operaciones CRUD. A través de un formulario interactivo, el usuario puede:

Registrar nuevos elementos

Consultar los registros existentes

Editarlos

Eliminarlos

Además, se ofrece una sección que permite visualizar el árbol XML completo, retornado por el servicio web, facilitando la comprensión de la estructura y la organización de los datos.

Finalmente, el sistema se encuentra desplegado en la nube mediante la plataforma Render, lo que garantiza su accesibilidad pública y evidencia habilidades en:

Despliegue de servicios web

Manejo de rutas

Persistencia de datos en XML

Consumo de APIs desde un cliente web

