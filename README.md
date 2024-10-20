**M2: Compras**

**Menú:** - PARCIALMENTE COMPLETADO
- Seguridad -NO HECHO
- Usuarios - HECHO
- Restablecer contraseña - HECHO

**Configuración / Maestros** - NO COMPLETADO
- Artículos / Productos 
    - Stock (campo, no tabla)
- Proveedores
- Compradores
- Transacciones
- Orden de compra _(De quien y a quien le estoy comprando)_
    - Estado _(*emitido / *recibido / *backorder o parcialmente recibido)_
- Ingreso (*depende de la orden de compra) **+ Stock**
- Devolución (indicar proveedor) **- Stock**

**Consultas** - NO EMPEZADO
- Stock (mostrar lista de artículos y campo de stock)
- Ordenes

**Reportes (Imprimir)** - NO EMPEZADO
- Stock (sin filtro)
- Ordenes (Rango de fechas)

**Consulta en ChatGPT**

Intenta ayudar en nuestro programa que es un modulo de compras que trata de emular de manera simplificada el proceso de compras de una tienda online, este es la estructura que hemos decidido (mostrar estructura)

La estructura de codigo y carpetas del source (esta estructura puede variar segun sea conveniente, nuestro proyecto esta en una fase muy temprana de desarrollo) es la siguiente:

src\main\java\com\compras\... {

carpeta controller: {
    ModuloConfiguracion.java
    ModuloMenu.java
}

carpeta db: {
ConexionDB.java}

carpeta model:{
Articulo.java
Comprador.java
Devolucion.java
OrdenCompra.java
Proveedor.java
Usuario.java}

carpeta service:{
ArticuloServicio.java
OrdenCompraServicio.java
UsuarioServicio.java}

Main.java}

Mi nivel actual es trainee, por lo que no entiendo algunos conceptos y es por eso que tal vez mi estructura no la plasme correctamente, hazmelo saber. Ademas, segun tengo pensado todas las clases de la carpeta o package model deben contener unicamente el constructor, los getters y los setter, verdad. O es un sesgo por parte de mi propia inexperiencia.

Mi clase ConexionDB.java contiene lo siguiente:

ademas mi clase UsuarioServicio.java tiene:

Y mi clase Main.java, que no se si la estoy sobrecargando de informacion o es correcta contiene lo siguiente:

Dicho esto es solo un avance lo que te muestro, probablemente no este alineado al estandar es por eso que necesito tus opiniones para ayudarme a apegarme al estandar, tal vez mi clase main sea incorrecta o tal vez otras cosas. Te escucho...