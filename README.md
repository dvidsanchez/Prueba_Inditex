# Prueba_Inditex

1.- Introducción.

El presente documento detalla las tecnologías, arquitectura y decisiones tomadas para el desarrollo de la prueba de BCNC para Inditex. En ella se pide realizar una aplicación con un microservicio
basado en Spring Boot que exponga 3 endpoints. Dichos endpoints tienen la siguiente funcionalidad:

	- Obtener datos desde un API y almacenarlos en una base de datos integrada.
	- Obtener datos desde un API y devolverlos en la respuesta.
	- Obtener datos desde la base de datos y devolverlos en la respuesta.
	
En los siguientes apartados se explica con más detalle los pasos seguidos para la realización de la prueba, así como los casos de ejemplo y comandos para poder ejecutar los test del proyecto.

2.- Decisiones Tomadas.

2.1.- Tecnologías.

El proyecto se ha desarrollado con Spring Boot v3.2.2 y Java 17. Se ha hecho uso del framework spring-web para realizar los servicios REST y de Spring-JPA junto con Hibernate para realizar
el modelado de los objetos y la interacción con la base de datos, que en este caso se trata de una base de datos H2 integrada. Para la realización de los test se ha usado Junit y Mockito
incluidas con Spring Boot Test junto con Jacoco para generar el informe de cobertura de los test. La gestión de dependencias se ha hecho mediante Apache Maven, en concreto se ha usado la versión 3.9.6.

2.2.- Arquitectura.

Se ha optado por una arquitectura hexagonal por capas que, además de aportar escalabilidad al proyecto, permite que cada capa de la aplicación tenga una funcionalidad determinada y que se acople
a contratos en lugar de acoplarse a implementaciones concretas. Todo esto permite que se puedan realizar modificaciones en las distintas capas sin que esto afecte al resto de la aplicación.
También permite que se puedan utilizar frameworks o tecnologías diferentes sin que esto afecte al resto de la aplicación.

En cuanto a la estructura de carpetas escogida, se podría hacer subdivisiones en función de los caso de uso para facilitar la búsqueda de clases e interfaces una vez el proyecto fuese tomando cierta embergadura, 
en este caso no se ha realizado así por tener únicamente un caso de uso y ser para una prueba en concreto.

2.3.- Desarrollo del código fuente.

La aplicación se ha desarrollado de la forma más abstracta posible, haciendo que cada uno de los componentes se dedique única y exclusivamente al cometido para el que han sido diseñados.

En cuanto a las estructuras de datos utilizadas, se ha optado por utilizar ArrayList debido a que en este caso era más conveniente su uso por tener una mayor velocidad de inserción que una LinkedList y no necesitar
estar eliminando elementos de las listas. 

Por otro lado, se ha utilizado un HashMap para formar el objeto de respuesta que relaciona las fotos con sus álbumes ya que, hubiese sido muy ineficiente haber estado recorriendo 
continuamente la lista de álbumes para asignar cada foto a su álbum. Al utilizar un HashMap podemos acceder más rápidamente a los elementos al haber utilizado como clave de este el identificador
del álbum. Dado que no se iba a acceder a este desde más de un hilo se ha considerado una mejor opción frente a HashTable.

Se ha incluido un control de errores común a toda la aplicación, que intercepta cualquier excepción producida en la aplicación y devuelve una respuesta adecuada con una descripción del error. Evitando que se muestren
trazas de error con código en la respuesta HTTP.

3.- Pruebas

3.1- Ejecución de los test.

Para la ejecución de los test se debe de realizar el siguiente comando estando situado en la carpeta del proyect, siendo necesario tener Apache Maven configurado en el equipo. 

	- mvn test
	
3.2- Reporte de cobertura.

Tras ejecutar el comando anterior se podrá obtener el reporte de cobertura. Para ello habrá que ejecutar el siguiente comando desde la carpeta del proyecto.

	- java -jar jacococli.jar report target\jacoco.exec --sourcefiles src\main\java --classfiles target\classes --html .
	
Nota: Se ha incluido el jar necesario para su ejecución en la carpeta del proyecto.

La ejecución del comando anterior generará un informe en Html que podrá ser consultado abriendo el fichero index.html que se ha generado en la carpeta del proyecto.

3.3- Prueba de los endpoints.

Como ya se comentó al principio del documento, la prueba consta de tres endpoints. A continuación, se indica el path de cada uno de los endpoints y el tipo de petición a realizar.

	- Obtención de datos desde el API y almacenado en base de datos: 
			- GET -> /api/save_data
			- Posibles respuestas: 
				- 204 No Content en caso de realizarse con éxito.
				- 500 Internal Server Error en caso de producirse algún error.
	- Obtención de datos desde el API y exposición en la respuesta: 
			- GET -> /api/api_data
			- Posibles respuestas: 
				- 200 OK en caso de realizarse con éxito.
				- 500 Internal Server Error en caso de producirse algún error.
	- Obtención de datos desde la base de datos y exposición en la respuesta: 
			- GET -> /api/db_data
			- Posibles respuestas: 
				- 200 OK en caso de realizarse con éxito.
				- 500 Internal Server Error en caso de producirse algún error.
				
3.4- Repositorio GIT

El proyecto se puede encontrar en el siguiente repositorio: https://github.com/dvidsanchez/Prueba_Inditex.git
