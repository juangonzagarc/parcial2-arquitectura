# 1.A 
## **Requisitos previos**
- Se debe tener instalado **Docker** en el sistema. 
- También es necesario contar con **Maven** o **Gradle** para ejecutar la aplicación, dependiendo de la configuración del proyecto.

## **Pasos para configurar y ejecutar la aplicación**

### **1. Iniciar un contenedor de MySQL con Docker:**

Para levantar la base de datos MySQL, se debe ejecutar un contenedor de Docker que contenga la base de datos y las credenciales necesarias. El contenedor utilizará el puerto `3306` y estará accesible en `localhost`. Se creará una base de datos denominada `testdb`, un usuario `test_user` y una contraseña `test_password`.

El comando a ejecutar para levantar el contenedor de MySQL es el siguiente:

```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root_password -e MYSQL_DATABASE=testdb -e MYSQL_USER=test_user -e MYSQL_PASSWORD=test_password -p 3306:3306 -d mysql:5.7
```

- **MYSQL_DATABASE**: Define el nombre de la base de datos.
- **MYSQL_USER** y **MYSQL_PASSWORD**: Se configuran para el acceso a la base de datos.
- **-p 3306:3306**: Expone el puerto `3306` en `localhost` para conectarse a la base de datos.

### **2. Configurar la aplicación para conectarse a MySQL:**

En el archivo de configuración `application.properties`, ya se tiene definida la conexión a la base de datos MySQL con las siguientes propiedades:

- **URL de conexión**: `jdbc:mysql://localhost:3306/testdb`
- **Usuario**: `test_user`
- **Contraseña**: `test_password`

No es necesario realizar ningún cambio adicional si ya se tiene esta configuración. Si es necesario ajustarla, puede hacerse en el archivo de propiedades de la aplicación.

### **3. Ejecutar la aplicación:**

Con el contenedor de MySQL corriendo, se debe levantar la aplicación localmente utilizando **Maven** o **Gradle**.

- Si el proyecto utiliza **Maven**, se debe ejecutar el siguiente comando:

```bash
./mvnw spring-boot:run
```

- Si el proyecto utiliza **Gradle**, se debe ejecutar el siguiente comando:

```bash
./gradlew bootRun
```

Esto iniciará la aplicación en el puerto `8081`. La aplicación estará conectada a la base de datos MySQL que corre en Docker.

### **4. Verificación de la conexión:**

Una vez que la aplicación esté corriendo, se puede verificar que la conexión a la base de datos MySQL fue exitosa revisando los logs de la aplicación o interactuando con la aplicación para realizar operaciones que afecten la base de datos (por ejemplo, crear un nuevo registro).

# 5 


