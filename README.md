# Caso Retail

Integrantes:
Alberto Castro Castro
Virginia Araujo Mercado

[Click para ver diseño de la solución](https://drive.google.com/file/d/1gG3YMTE4OLDu8MqmOu-91mECy0eOySsa/view?usp=sharing)

# Pre-requisitos
1. Instalar [MongoDB](https://pages.github.com/) y [Redis](https://redis.io/download)

2. Ejecutar proyectos spring-boot:run:
- Eureka
- Ventas
- Cartera
- Proveedores
- Crm
- Commons

3. Instalar dependencia Commons localmente: mvn compile install.

# Payload ejemplo:
- Venta: {"id":"123","cliente":"1234","descripcion":"Televisor","estado":"aprobada","total":"3000000","fecha":"9/12/2017"}

- Cliente nuevo: {"id":"1234","nombre":"Alberto","apellidos":"Castro","direccion":"laureles","telefono":"123456","email":"ac@gmail.com"}





