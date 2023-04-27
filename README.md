# Proyecto Catálogo de Películas y Series - Instaflix

Este proyecto es una aplicación de Android desarrollada en Kotlin que muestra un catálogo de películas y series de diferentes categorías consumiendo datos de la API de **[The Movie Database (TMDB)](https://developers.themoviedb.org/3/getting-started/introduction)**. La aplicación sigue una arquitectura Clean, haciendo uso de Room como base de datos y Paging3 para la paginación de resultados.

## **Instalación**

Para instalar y probar la aplicación, sigue los siguientes pasos:

1. Clona el repositorio a tu computadora
2. Abre el proyecto en Android Studio
3. Agrega tu API key de TMDB en el archivo **`local.properties`** del proyecto:

```

API_KEY=your_api_key_here

```

1. Ejecuta la aplicación en un dispositivo o emulador de Android.

## **Arquitectura**

La aplicación sigue la arquitectura Clean, separando la lógica de negocio en diferentes capas y usando la inyección de dependencias con Hilt para la gestión de las dependencias.

Las capas de la aplicación son las siguientes:

- **Data**: Se encarga de la obtención y almacenamiento de datos. En esta capa se encuentran los repositorios, que implementan la lógica de acceso a datos. En este proyecto se utiliza Room como base de datos y Retrofit para la conexión a la API de TMDB.
- **Domain**: Se encarga de la lógica de negocio. En esta capa se encuentran los casos de uso, que son las diferentes acciones que puede realizar el usuario en la aplicación.
- **Presentation**: Se encarga de la presentación de datos al usuario. En esta capa se encuentran los ViewModels, que implementan la lógica de presentación de datos. En este proyecto se utiliza la arquitectura de Android Jetpack, incluyendo Flow y Paging3 para la presentación de datos en la interfaz de usuario.

## **Funcionamiento**

La aplicación consta de tres pantallas principales:

- **Pantalla de lista de películas o series**: Muestra la lista de películas o series de la categoría seleccionada. Al hacer scroll en la lista, se cargan más resultados automáticamente gracias a la paginación implementada con Paging3.
- **Pantalla de detalle de película o serie**: Muestra la información detallada de la película o serie seleccionada. En esta pantalla se muestra el título, la sinopsis, la valoración de la película o serie, así como los géneros a los que pertenece.

La aplicación cuenta con soporte de navegación offline, gracias al uso de la cache implementada con Room y la lógica implementada en los repositorios.

## **Dependencias**

En este proyecto se han utilizado las siguientes dependencias principales:

- Retrofit y OkHttp: Para la conexión con la API de TMDB
- Room: Para la implementación de la base de datos
- Paging3: Para la paginación de resultados
- Coil: Para la carga de imágenes
- Hilt: Para la inyección de dependencias