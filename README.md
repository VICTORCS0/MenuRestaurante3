# MenuRestaurante3

Aplicación móvil para la gestión de pedidos de un restaurante, desarrollada en Android Studio utilizando Kotlin. La aplicación permite gestionar productos, mesas y pedidos, además de incorporar autenticación de usuarios mediante Firebase Authentication y almacenamiento de pedidos en Cloud Firestore.

---

## 1. Descripción del proyecto

MenuRestaurante3 es una aplicación móvil desarrollada para facilitar la gestión de pedidos de un restaurante.

La aplicación permite al usuario iniciar sesión mediante correo electrónico y contraseña, consultar el menú disponible, seleccionar productos, asignarlos a una mesa, registrar pedidos y consultar los pedidos pendientes.

También se implementó la integración con Firebase para almacenar los pedidos en la nube y realizar operaciones CRUD sobre los documentos almacenados en Cloud Firestore.

La aplicación utiliza una base de datos SQLite local para determinadas operaciones internas, principalmente para la gestión de productos, mesas y pedidos locales, mientras que Cloud Firestore se utiliza para almacenar y gestionar los pedidos en la nube.

---

## 2. Objetivo

Desarrollar una aplicación móvil para la gestión de pedidos de un restaurante utilizando Android Studio y Kotlin, integrando Firebase Authentication y Cloud Firestore para proporcionar autenticación de usuarios y almacenamiento de información en la nube.

Además, se busca implementar las operaciones CRUD:

- Create: registrar pedidos.
- Read: consultar pedidos.
- Update: actualizar el estado de los pedidos.
- Delete: eliminar pedidos.

---

## 3. Tecnologías utilizadas

### Android Studio

Android Studio es el entorno de desarrollo utilizado para crear, compilar y ejecutar la aplicación Android.

Se utilizó para desarrollar la interfaz gráfica, las actividades, los adaptadores, controladores, modelos y la integración con Firebase.

### Kotlin

Kotlin es el lenguaje de programación utilizado para desarrollar la aplicación.

Se utiliza para implementar la lógica de negocio, las actividades, los modelos, los DAO, los controladores y la comunicación con Firebase.

### Firebase Authentication

Firebase Authentication permite implementar el registro e inicio de sesión de los usuarios mediante correo electrónico y contraseña.

En la aplicación se utiliza para:

- Registrar usuarios.
- Iniciar sesión.
- Mantener la sesión del usuario.
- Cerrar sesión.

### Cloud Firestore

Cloud Firestore es la base de datos NoSQL en la nube utilizada para almacenar los pedidos.

Los pedidos se almacenan dentro de la colección:

    pedidos

Cada documento contiene información relacionada con el pedido, como:

- ID del pedido.
- Mesa.
- Fecha.
- Estado.
- Total.
- Productos.

### SQLite

SQLite es la base de datos local utilizada por la aplicación para almacenar información necesaria para el funcionamiento local.

Se utiliza principalmente para la gestión de:

- Productos.
- Mesas.
- Pedidos.
- Detalles de los pedidos.

---

## 4. Requisitos

Para ejecutar el proyecto se requiere:

- Android Studio.
- JDK compatible con la versión del proyecto.
- Android SDK configurado.
- Un emulador Android o dispositivo físico.
- Conexión a Internet.
- Una cuenta de Firebase.
- Un proyecto creado en Firebase.
- Firebase Authentication habilitado.
- Cloud Firestore habilitado.

---

## 5. Instalación del proyecto

### Paso 1. Obtener el proyecto

Descargar o clonar el proyecto MenuRestaurante3.

Por ejemplo, si se utiliza Git:

    git clone URL_DEL_REPOSITORIO

También es posible descargar el proyecto directamente como archivo ZIP y descomprimirlo.

### Paso 2. Abrir el proyecto

Abrir Android Studio y seleccionar:

    Open

Posteriormente seleccionar la carpeta principal:

    MenuRestaurante3

Esperar a que Android Studio cargue el proyecto y sincronice las dependencias de Gradle.

### Paso 3. Configurar el dispositivo

Seleccionar un emulador Android o conectar un dispositivo físico con la depuración USB habilitada.

En el proyecto utilizado para las pruebas se empleó un emulador Pixel 8.

---

## 6. Configuración de Firebase

Para utilizar las funciones de Firebase es necesario vincular la aplicación Android con un proyecto de Firebase.

### Paso 1. Crear un proyecto de Firebase

Ingresar a la consola de Firebase y crear un proyecto.

### Paso 2. Registrar la aplicación Android

Dentro del proyecto de Firebase se debe registrar la aplicación Android utilizando el identificador del paquete de la aplicación.

El paquete utilizado en este proyecto es:

    com.example.menurestaurante3

### Paso 3. Descargar google-services.json

Después de registrar la aplicación Android, descargar el archivo:

    google-services.json

Colocar el archivo dentro de:

    app/google-services.json

La estructura debe quedar aproximadamente así:

    MenuRestaurante3/
    ├── app/
    │   ├── google-services.json
    │   ├── src/
    │   └── ...
    ├── gradle/
    ├── build.gradle.kts
    └── settings.gradle.kts

### Paso 4. Habilitar Authentication

En Firebase:

    Authentication
    → Sign-in method

Habilitar:

    Correo electrónico/contraseña

Esto permite registrar usuarios e iniciar sesión desde la aplicación.

### Paso 5. Configurar Cloud Firestore

En Firebase:

    Firestore Database
    → Crear base de datos

Seleccionar la configuración correspondiente para el proyecto.

La aplicación utiliza la colección:

    pedidos

---

## 7. Autenticación de usuarios

La aplicación cuenta con un sistema de autenticación mediante Firebase Authentication.

### Registro

El usuario puede crear una cuenta utilizando:

- Correo electrónico.
- Contraseña.

La información de autenticación es administrada por Firebase Authentication.

### Inicio de sesión

El usuario proporciona su correo electrónico y contraseña registrados previamente.

Si las credenciales son correctas, se permite el acceso a la aplicación principal.

### Cierre de sesión

La aplicación cuenta con la opción:

    Cerrar sesión

Al seleccionarla, se cierra la sesión actual mediante Firebase Authentication y el usuario vuelve a la pantalla de inicio de sesión.

---

## 8. Funcionamiento de la aplicación

El funcionamiento general de la aplicación es el siguiente:

    Inicio de la aplicación
            ↓
    Inicio de sesión
            ↓
    Menú principal
            ↓
    Selección de mesa
            ↓
    Selección de productos
            ↓
    Agregar productos al pedido
            ↓
    Calcular total
            ↓
    Registrar pedido
            ↓
    Guardar pedido
            ↓
    Cloud Firestore
            ↓
    Consultar pedidos
            ↓
    Cobrar o eliminar pedido

---

## 9. Gestión del menú

La pantalla principal muestra los productos disponibles organizados por categorías.

El usuario puede seleccionar un producto para agregarlo al pedido.

Cuando un producto ya se encuentra en el pedido, es posible aumentar o disminuir su cantidad.

El sistema calcula automáticamente el total del pedido.

---

## 10. Gestión de mesas

Antes de registrar un pedido se selecciona una mesa.

La aplicación verifica si la mesa se encuentra disponible.

Cuando se registra un pedido, la mesa pasa a un estado de ocupada.

Cuando el pedido es finalizado, la mesa puede volver a estar disponible.

---

## 11. Gestión de pedidos

Los pedidos contienen información como:

- ID del pedido.
- Número de mesa.
- Fecha.
- Estado.
- Total.
- Productos incluidos.

Los estados utilizados incluyen:

    Pendiente

y:

    Cobrado

---

## 12. Cloud Firestore

Los pedidos se almacenan en la colección:

    pedidos

La estructura general de un documento es:

    pedidos
    └── ID_DEL_PEDIDO
        ├── idPedido
        ├── mesa
        ├── fecha
        ├── estado
        ├── total
        └── productos

El campo `productos` contiene los productos incluidos en el pedido.

Cada producto almacena información como:

    nombre
    cantidad
    precio

Ejemplo conceptual:

    pedidos
    └── 23
        ├── idPedido: "23"
        ├── mesa: 1
        ├── fecha: "2026-08-06 15:51:52"
        ├── estado: "Pendiente"
        ├── total: ...
        └── productos:
            ├── nombre: "Chilaquiles Verdes"
            ├── cantidad: 1
            └── precio: 110

---

## 13. Implementación CRUD

La aplicación implementa las operaciones CRUD sobre los pedidos almacenados en Cloud Firestore.

### Create

Permite crear y almacenar un nuevo pedido.

El proceso consiste en:

    Seleccionar productos
            ↓
    Seleccionar mesa
            ↓
    Calcular total
            ↓
    Registrar pedido
            ↓
    Crear documento en Firestore

El pedido se registra inicialmente con el estado:

    Pendiente

---

### Read

Permite consultar los pedidos registrados y mostrar los pedidos pendientes en la aplicación.

Los datos son obtenidos y utilizados para mostrar información como:

- Número de pedido.
- Mesa.
- Fecha.
- Estado.
- Total.

---

### Update

Permite actualizar el estado de un pedido.

Cuando el usuario cobra un pedido, su estado se actualiza en Firestore de:

    Pendiente

a:

    Cobrado

De esta manera, el cambio realizado desde la aplicación se refleja en el documento correspondiente de Firestore.

---

### Delete

Permite eliminar un pedido.

Al utilizar la opción de eliminar, el documento correspondiente se elimina de la colección:

    pedidos

de Cloud Firestore.

---

## 14. Arquitectura del proyecto

El proyecto utiliza una estructura basada en el patrón MVC y separación mediante DAO.

La estructura principal es:

    com.example.menurestaurante3
    │
    ├── adapter
    │   ├── MenuAdapter
    │   ├── PedidoAdapter
    │   └── PedidoListadoAdapter
    │
    ├── controller
    │   └── PedidoController
    │
    ├── dao
    │   └── FirestoreDAO
    │
    ├── database
    │   ├── DBHelper
    │   ├── MesaDAO
    │   ├── PedidoDAO
    │   ├── PedidoListadoDAO
    │   └── ProductoDAO
    │
    ├── model
    │   ├── MenuItem
    │   ├── Mesa
    │   ├── Pedido
    │   ├── PedidoItem
    │   ├── Producto
    │   └── ProductoPedido
    │
    ├── repository
    │
    ├── ui
    │
    ├── LoginActivity
    ├── RegistroActivity
    └── MainActivity

### Model

Contiene las clases que representan los datos utilizados por la aplicación, como productos, mesas y pedidos.

### View

Está representada principalmente por las interfaces XML y las actividades que muestran y reciben información del usuario.

### Controller

Contiene lógica relacionada con la gestión del pedido, como agregar productos, quitar productos, limpiar el pedido y calcular el total.

### DAO

Los DAO permiten separar las operaciones de acceso a los datos de la lógica principal de la aplicación.

Se utilizan DAO para trabajar con SQLite y Cloud Firestore.

### Adapter

Los adaptadores permiten mostrar los datos en componentes como RecyclerView.

---

## 15. Principales clases del proyecto

### MainActivity

Es la pantalla principal de la aplicación.

Permite:

- Mostrar el menú.
- Seleccionar una mesa.
- Agregar productos.
- Modificar cantidades.
- Calcular el total.
- Registrar pedidos.
- Acceder a la lista de pedidos.
- Cerrar sesión.

### LoginActivity

Gestiona el inicio de sesión mediante Firebase Authentication.

### RegistroActivity

Gestiona el registro de nuevos usuarios mediante correo electrónico y contraseña.

### PedidoController

Gestiona los productos que forman parte del pedido actual y permite:

- Agregar productos.
- Quitar productos.
- Obtener el pedido.
- Limpiar el pedido.
- Calcular el total.

### FirestoreDAO

Se encarga de realizar operaciones relacionadas con los pedidos almacenados en Cloud Firestore.

Entre sus funciones se encuentran:

- Guardar pedidos.
- Actualizar estados.
- Eliminar pedidos.

### PedidoDAO

Gestiona operaciones de pedidos utilizando la base de datos SQLite local.

### PedidoListadoDAO

Permite obtener los pedidos pendientes y realizar operaciones relacionadas con el cobro de los pedidos.

---

## 16. Uso de la aplicación

### 1. Registrarse

Desde la pantalla de registro:

1. Introducir un correo electrónico.
2. Introducir una contraseña.
3. Presionar el botón de registro.

### 2. Iniciar sesión

Introducir:

- Correo electrónico.
- Contraseña.

Presionar el botón de inicio de sesión.

### 3. Seleccionar productos

Desde el menú principal:

1. Seleccionar un producto.
2. El producto aparecerá en el pedido.
3. Aumentar o disminuir la cantidad según sea necesario.

### 4. Seleccionar mesa

Seleccionar una mesa disponible mediante el selector de mesas.

### 5. Registrar pedido

Presionar:

    Realizar pedido

El sistema calcula el total y registra el pedido.

### 6. Consultar pedidos

Presionar:

    Pedidos

Se mostrará la lista de pedidos pendientes.

### 7. Cobrar un pedido

Seleccionar el pedido correspondiente y utilizar la opción de cobro.

El estado del pedido cambia a:

    Cobrado

El cambio también se refleja en Cloud Firestore.

### 8. Eliminar un pedido

Seleccionar la opción de eliminación correspondiente.

El documento del pedido se elimina de Cloud Firestore.

### 9. Cerrar sesión

Presionar:

    Cerrar sesión

La sesión actual se cierra y la aplicación regresa a la pantalla de inicio de sesión.

---

## 17. Base de datos local SQLite

La aplicación también utiliza SQLite para almacenar información local.

Entre las tablas utilizadas se encuentran estructuras relacionadas con:

- pedidos
- mesas
- productos
- producto_pedido

La base de datos local permite gestionar información necesaria para el funcionamiento de la aplicación incluso antes de sincronizar la información correspondiente con Firebase.

---

## 18. Solución de problemas

### La aplicación no inicia

Verificar que:

- Android Studio haya terminado la sincronización de Gradle.
- El emulador esté correctamente iniciado.
- El proyecto tenga configurado el SDK correspondiente.

### No funciona el inicio de sesión

Verificar en Firebase:

    Authentication
    → Sign-in method
    → Correo electrónico/contraseña

Debe encontrarse habilitado.

### No aparecen los pedidos en Firestore

Verificar:

- Conexión a Internet.
- Configuración de Firebase.
- Archivo `google-services.json`.
- Configuración de Cloud Firestore.

También verificar que se esté utilizando la colección:

    pedidos

### Las mesas aparecen ocupadas

La aplicación utiliza SQLite para gestionar parte del estado local de las mesas.

Si se está utilizando un emulador durante las pruebas, puede ser necesario borrar los datos locales de la aplicación para reiniciar el estado de SQLite.

---

## 19. Seguridad

El proyecto utiliza Firebase Authentication para controlar el acceso de los usuarios.

Las credenciales de los usuarios son gestionadas mediante Firebase Authentication y no se almacenan directamente como texto dentro de la aplicación.

El acceso a los datos almacenados en Cloud Firestore debe estar protegido mediante las reglas de seguridad correspondientes del proyecto de Firebase.

---

## 20. Evidencia del funcionamiento

El funcionamiento del proyecto fue comprobado mediante:

- Registro de usuarios.
- Inicio de sesión.
- Cierre de sesión.
- Registro de pedidos.
- Consulta de pedidos.
- Actualización del estado de pedidos.
- Eliminación de pedidos.
- Visualización de los pedidos en Cloud Firestore.
- Verificación de los cambios realizados desde la aplicación en Firebase.

Las evidencias visuales y el video de demostración se entregan como materiales complementarios al proyecto.

---

## 21. Autor

**Proyecto:** MenuRestaurante3

**Desarrollado por:** Victor Campos Sanchez

**Tecnología principal:** Kotlin / Android Studio

**Base de datos en la nube:** Firebase Cloud Firestore

**Autenticación:** Firebase Authentication

---

## 🎥 Demostración

El siguiente video muestra el funcionamiento de la aplicación durante la etapa en la que Firebase se encontraba activo.

[▶️ Ver video de demostración](https://drive.google.com/file/d/1bc3JY5Np1in5a4F90YNdf43dv4wbGQt4/view?usp=sharing)

## Estado de Firebase

> **Nota:** La integración con Firebase Authentication y Cloud Firestore fue utilizada durante el desarrollo y la demostración del proyecto. Actualmente, el servicio de Firebase utilizado para este proyecto no se encuentra activo, por lo que las funciones de autenticación y sincronización con la base de datos en la nube no están disponibles en la versión pública actual.

---

## 📄 Documentación

[Ver documentación completa del proyecto](./Documentacion_MenuRestaurante3.pdf)