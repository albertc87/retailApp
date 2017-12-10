# Caso Retail

Integrantes:
- Alberto Castro Castro
- Virginia Araujo Mercado

# Links diseño solución
- [Click para ver diseño de la solución](https://drive.google.com/file/d/1gG3YMTE4OLDu8MqmOu-91mECy0eOySsa/view?usp=sharing)
- [Exchanges](https://drive.google.com/open?id=1AVc1Aj9E88vmbpNWsMgATBfncq0xA_pD)
- [Queues](https://drive.google.com/open?id=1_2QiPwf96QyG5Dpect8k8cRBlBICcUmF)
- [Exchange ventarealizada](https://drive.google.com/open?id=1j2tM-AfthRSvGZ9Wqy2k922Gw-mb_lKP)
- [Exchange clienteregistrado](https://drive.google.com/open?id=1sFxKBc7iePgwnRgbrPq0RUh0cyOI9hlp)

# Pre-requisitos
1. Instalar [MongoDB](https://pages.github.com/) y [Redis](https://redis.io/download)

2. Ejecutar proyectos spring-boot:run
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

# Petición Servicio Rest Consultar Cliente
curl -X GET \ http://localhost:1118/cliente/12345 \ -H 'cache-control: no-cache' \ -H 'postman-token: e24cc3bc-4430-5336-aae4-bc7f29dab3e5'

# Known Issues
- Queda pendiente la implementación de Dead Letter






