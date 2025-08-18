# 🌍 API Spring Boot - Programación Orientada a Objetos

## 📖 Descripción del Proyecto

API REST desarrollada en **Spring Boot** para la gestión de **Continentes**, **Países** y **Provincias** como trabajo práctico de la materia Programación Orientada a Objetos. 

La aplicación implementa una arquitectura en capas (Controller → Service → Repository → Entity) siguiendo las mejores prácticas de Spring Boot y patrones de diseño empresariales.

## 🛠️ Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data MongoDB** 
- **Maven** (Gestión de dependencias)
- **MongoDB Atlas** (Base de datos en la nube)
- **Bean Validation** (Validaciones automáticas)
- **DTOs** (Data Transfer Objects)

## 📋 Requisitos Previos

Antes de ejecutar la aplicación, asegurate de tener instalado:

- ☕ **Java 17 o superior**
- 📦 **Maven 3.6+**
- 🍃 **MongoDB Atlas configurado** (o MongoDB local)
- 🔧 **IDE recomendado**: IntelliJ IDEA, Eclipse o VSCode

## ⚙️ Instalación y Configuración

### 1. 📥 Clonar el Repositorio
```bash
git clone https://github.com/Agustin29av/APISpringBootPOO.git
cd APISpringBootPOO
```

### 2. 🗂️ Navegar a la Carpeta Correcta
```bash
cd poo
```
> ⚠️ **IMPORTANTE**: El proyecto tiene una estructura anidada. Los comandos Maven deben ejecutarse desde `APISpringBootPOO/poo/`

### 3. 🔐 Configurar MongoDB Atlas
Editar el archivo `src/main/resources/application.properties`:

```properties
# MongoDB Atlas Configuration
spring.data.mongodb.uri=mongodb+srv://tu_usuario:tu_password@tu-cluster.mongodb.net/tu_database?retryWrites=true&w=majority

# Server Configuration  
server.port=9001
server.servlet.context-path=/poo

# Logging (Opcional)
logging.level.com.uader.poo=DEBUG
```

### 4. 🏗️ Compilar y Ejecutar

```bash
# Limpiar y compilar
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
```

### 5. ✅ Verificar que Funciona
La aplicación estará disponible en: `http://localhost:9001/poo`

Endpoint de prueba: `GET http://localhost:9001/poo/api/version`

## 🚀 Uso de la API

### 📍 Base URL
```
http://localhost:9001/poo
```

### 🌎 Endpoints - Continentes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/continentes` | Obtener todos los continentes |
| `GET` | `/api/continentes/{id}` | Obtener continente por ID |
| `GET` | `/api/continentes/nombre/{nombre}` | Obtener continente por nombre |
| `POST` | `/api/continentes` | Crear nuevo continente |
| `DELETE` | `/api/continentes/{id}` | Eliminar continente |
| `POST` | `/api/continentes/{nombre}/agregar-pais` | Agregar país a continente |

### 📝 Ejemplos de Uso

#### Crear un Continente
```bash
curl -X POST http://localhost:9001/poo/api/continentes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "América del Sur"
  }'
```

#### Obtener Todos los Continentes
```bash
curl -X GET http://localhost:9001/poo/api/continentes
```

#### Respuesta Típica
```json
{
  "id": "64f1a2b3c4d5e6f7g8h9i0j1",
  "nombre": "América del Sur", 
  "paises": ["arg123", "bra456", "chi789"]
}
```

## 🏗️ Arquitectura del Proyecto

```
src/main/java/com/uader/poo/
├── controller/tp4/          # 🎮 Controladores REST
│   └── ContinenteController.java
├── service/tp4/             # 🧠 Lógica de Negocio
│   ├── IContinenteService.java
│   └── ContinenteServiceImpl.java
├── repository/tp4/          # 💾 Acceso a Datos
│   └── ContinenteRepository.java
├── entity/tp4/              # 📊 Modelos de Datos
│   └── Continente.java
├── dto/tp4/                 # 📦 Data Transfer Objects
│   ├── ContinenteCreateDTO.java
│   └── ContinenteResponseDTO.java
└── exception/               # ⚠️ Manejo de Excepciones
    ├── ResourceNotFoundException.java
    └── InvalidInputException.java
```

### 🔄 Flujo de Datos
```
Cliente HTTP ➜ Controller ➜ Service ➜ Repository ➜ MongoDB
                    ⬇️              ⬇️           ⬇️
               Validaciones    Lógica de    Persistencia
                  DTOs         Negocio        de Datos
```

## 🧪 Testing

### Con cURL:
```bash
# Health Check
curl -X GET http://localhost:9001/poo/api/version

# Crear continente
curl -X POST http://localhost:9001/poo/api/continentes \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Europa"}'
```

### Con Postman:
1. Importar la colección desde: `postman/APISpringBootPOO.postman_collection.json` *(si existe)*
2. Configurar environment variable: `base_url = http://localhost:9001/poo`

## 🐛 Troubleshooting

### ❌ Error: "No plugin found for prefix 'spring-boot'"
**Solución:** Verificá que estás ejecutando Maven desde la carpeta `poo/`
```bash
cd APISpringBootPOO/poo
mvn spring-boot:run
```

### ❌ Error: "Timed out while waiting for MongoDB server"
**Solución:** 
1. Verificá tu connection string en `application.properties`
2. Asegurate que tu IP esté autorizada en MongoDB Atlas
3. Verificá usuario/password correcto

### ❌ Error 404 en endpoints
**Solución:** Recordá que la base URL incluye el context path `/poo`
```
❌ http://localhost:9001/api/continentes
✅ http://localhost:9001/poo/api/continentes
```

## 📚 Próximas Mejoras

- [ ] 🏗️ Implementar endpoints para **Países** y **Provincias**
- [ ] 🧪 Agregar tests unitarios e integración  
- [ ] 📖 Documentación con **Swagger/OpenAPI**
- [ ] 🔐 Sistema de autenticación y autorización
- [ ] 🐳 **Dockerización** de la aplicación
- [ ] ☁️ **Deploy** en cloud (Heroku, AWS, etc.)

## ⚠️ Notas para Desarrollo Futuro

### 📝 Git Best Practices
Este repositorio actualmente incluye archivos de logs y configuración que en un entorno profesional **NO deberían estar versionados**. 

Para proyectos futuros, agregar al `.gitignore`:
```gitignore
# Logs
*.log
logs/
log/

# IDE
.vscode/
.idea/
*.iml

# OS
.DS_Store
Thumbs.db

# Application specific
application-local.properties
```

### 🔒 Seguridad
- **NO commitear** credenciales reales de MongoDB
- Usar **variables de entorno** para configuración sensible
- Implementar **validation** robusta en todos los endpoints

## 👨‍💻 Autor

**Agustín** - Estudiante de Programación Orientada a Objetos  
📧 Contacto: [Tu email aquí]  
🎓 Universidad: UADER

## 📄 Licencia

Este proyecto es un trabajo práctico académico desarrollado para fines educativos.

---

### 🚀 ¡Listo para usar!

1. **Clona** el repositorio
2. **Navega** a la carpeta `poo/`  
3. **Configura** MongoDB Atlas
4. **Ejecuta** `mvn spring-boot:run`
5. **Testea** en `http://localhost:9001/poo/api/continentes`

Aclaración: README desarrollado con Claude💻