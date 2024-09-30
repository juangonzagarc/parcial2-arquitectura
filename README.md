
### Pasos para configurar y ejecutar la aplicación

### 1. Iniciar un contenedor de MySQL con Docker:

Para levantar la base de datos MySQL, se debe ejecutar un contenedor de Docker que contenga la base de datos y las credenciales necesarias. El contenedor utilizará el puerto `3306` y estará accesible en `localhost`. Se creará una base de datos denominada `testdb`, un usuario `test_user` y una contraseña `test_password`.

El comando a ejecutar para levantar el contenedor de MySQL es el siguiente:

```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root_password -e MYSQL_DATABASE=testdb -e MYSQL_USER=test_user -e MYSQL_PASSWORD=test_password -p 3306:3306 -d mysql:5.7
