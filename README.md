# Proyecto App "Prueba2_AM" (Feria Virtual)

Este es un proyecto de aplicación nativa de Android desarrollado en Kotlin como parte de un requerimiento académico. La aplicación utiliza Jetpack Compose para la interfaz de usuario y sigue una arquitectura MVVM (Model-View-ViewModel) para una clara separación de responsabilidades.

## Estudiantes

* **Diego Castro**
* **Benjamin Figueroa**

---

## Funcionalidades Implementadas

### 1. Arquitectura y Estructura
* **Jetpack Compose:** Toda la interfaz de usuario está construida de forma declarativa con Jetpack Compose.
* **Arquitectura MVVM:** Se utiliza un `ViewModel` para cada pantalla que maneja lógica (`LoginViewModel`, `RegisterViewModel`, `ProductsViewModel`), separando la lógica de la UI.
* **Modularidad:** El proyecto está estructurado en paquetes claros:
    * `model`: Contiene las clases de datos (ej. `Product`).
    * `components`: Almacena componentes reutilizables (ej. `ProfileImagePicker`).
    * `navigation`: Define el flujo de navegación de la app (`AppNavigation`, `AppScreens`).
    * `screens`: Contiene los composables de cada pantalla principal.
    * `ui.theme`: Tema de Material 3 autogenerado.

### 2. Navegación
* Se implementó un grafo de navegación centralizado (`NavHost`) que gestiona el flujo de la aplicación:
    * `Home` (Pantalla de bienvenida)
        * `->` `Login`
        * `->` `Register`
    * `Login` (Tras éxito) `->` `Products`
    * `Products` (Al Salir) `->` `Home`

### 3. Formulario de Registro (`RegisterScreen`)
* Formulario completo con campos de Nombre, Email, Contraseña y Confirmación de Contraseña.
* **Validación por Campo:** Todos los campos son validados en tiempo real por el `RegisterViewModel` (ej. campos no vacíos, email válido, contraseñas coincidentes).
* **Retroalimentación Visual:**
    * Los campos con errores se marcan en rojo.
    * Se muestran mensajes de error específicos debajo de cada campo.
    * **Animaciones:** Los mensajes de error aparecen y desaparecen con una animación de deslizamiento y desvanecimiento (`AnimatedVisibility`) para una retroalimentación más clara.
* **Persistencia Local:** Al registrarse con éxito, el email y la contraseña se guardan localmente usando `SharedPreferences` para simular una base de datos de usuarios.

### 4. Formulario de Login (`LoginScreen`)
* Formulario con campos de Email y Contraseña.
* **Validación Cruzada:** El `LoginViewModel` valida los campos y luego comprueba los datos ingresados contra los guardados en `SharedPreferences`.
* **Retroalimentación Clara:** Muestra animaciones de error si los campos son inválidos o si el "email o contraseña son incorrectos".

### 5. Acceso a Recursos del Dispositivo
Se integran múltiples recursos del hardware del dispositivo:
* **Cámara:** El usuario puede tomar una foto de perfil durante el registro, accediendo a la cámara del dispositivo (`ACTION_IMAGE_CAPTURE` y `android.permission.CAMERA`).
* **Almacenamiento Interno:** Se utiliza `SharedPreferences` para la persistencia de datos del usuario registrado.
* **Vibración:** Al iniciar sesión correctamente, el dispositivo vibra (`android.permission.VIBRATE`) para dar una retroalimentación táctil (háptica) al usuario.

### 6. Pantalla de Productos (`ProductsScreen`)
* Pantalla protegida a la que solo se accede después de un login exitoso.
* Muestra una lista (ficticia, "hardcodeada") de productos en un `LazyColumn` (vista de lista eficiente).
* Cada ítem de la lista está diseñado como un `Card` independiente.
* Incluye un botón de "Salir" (`Cerrar Sesión`) que devuelve al usuario a la pantalla `Home` y limpia el historial de navegación.

---

## Pasos para Ejecutar el Proyecto

1.  **Clonar o Descargar:** Descarga el archivo ZIP de este repositorio o clónalo usando Git.
2.  **Abrir en Android Studio:** Abre el proyecto en la última versión estable de Android Studio.
3.  **Sincronizar Gradle:** Espera a que Android Studio descargue todas las dependencias del proyecto (definidas en los archivos `build.gradle.kts`).
4.  **Ejecutar:** Conecta un dispositivo físico (con modo desarrollador activado) o inicia un Emulador de Android (API 30+ recomendado).
5.  **Dar Play:** Presiona el botón "Run" (▶) en Android Studio.
