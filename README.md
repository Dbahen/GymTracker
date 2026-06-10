# 💪 GymTracker API

REST API para registrar y gestionar entrenamientos de gimnasio. Cada usuario tiene su propio historial de workouts, ejercicios y sets con su peso y repeticiones.

## 🌐 URL en producción
```
https://gymtracker-production-339b.up.railway.app
```

## 🛠 Tecnologías

- Java 21
- Spring Boot 4
- Spring Security + JWT
- Spring Data JPA / Hibernate
- PostgreSQL (Supabase)
- Maven

## 🚀 Cómo correr el proyecto localmente

### Requisitos
- Java 21 o superior
- Maven

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/Dbahen/GymTracker.git

# 2. Entrar a la carpeta
cd GymTracker

# 3. Correr la app
./mvnw spring-boot:run -DskipTests
```

La app levanta en `http://localhost:8080` usando H2 (base de datos en memoria).

## 🔐 Autenticación

La API usa JWT. Para acceder a los endpoints protegidos debes:

1. Registrarte o iniciar sesión para obtener un token
2. Incluir el token en cada request en el header:
```
Authorization: Bearer <tu_token>
```

Los tokens expiran después de 24 horas.

### Registro — POST /auth/register

```json
{
  "email": "usuario@gmail.com",
  "password": "MiPassword123"
}
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Login — POST /auth/login

```json
{
  "email": "usuario@gmail.com",
  "password": "MiPassword123"
}
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## 📋 Endpoints

Todos los endpoints excepto `/auth/**` requieren el header `Authorization: Bearer <token>`. Cada usuario solo puede ver y modificar sus propios workouts.

### Autenticación

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| POST | `/auth/register` | Registrar nuevo usuario | No |
| POST | `/auth/login` | Iniciar sesión | No |

### Workouts

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| GET | `/workouts` | Obtener todos los workouts del usuario | Sí |
| GET | `/workouts/fecha/{fecha}` | Obtener workouts por fecha | Sí |
| POST | `/workouts` | Crear un nuevo workout | Sí |
| PUT | `/workouts/{id}` | Actualizar un workout | Sí |
| DELETE | `/workouts/{id}` | Eliminar un workout | Sí |

## 📦 Ejemplo de uso completo

### 1. Registro
```bash
POST /auth/register
{
  "email": "usuario@gmail.com",
  "password": "MiPassword123"
}
```

### 2. Crear workout con el token
```bash
POST /workouts
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

{
  "date": "2025-06-09",
  "exercise": {
    "name": "Sentadilla",
    "muscleGroup": "Piernas"
  },
  "sets": [
    { "reps": 5, "weight": 120.0 },
    { "reps": 5, "weight": 120.0 },
    { "reps": 5, "weight": 125.0 }
  ]
}
```

### 3. Obtener workouts por fecha
```bash
GET /workouts/fecha/2025-06-09
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## 🏗 Estructura del proyecto

```
src/main/java/com/dbahen/GymTracker/
├── config/
│   └── SecurityConfig.java           # JWT filter y configuración de seguridad
├── controller/
│   ├── AuthController.java           # Endpoints de autenticación
│   └── WorkoutController.java        # Endpoints de workouts
├── service/
│   ├── JwtService.java               # Generación y validación de tokens
│   ├── UserService.java              # Lógica de registro y login
│   └── WorkoutService.java           # Lógica de negocio
├── repository/
│   ├── UserRepository.java           # Acceso a tabla users
│   └── WorkoutRepository.java        # Acceso a tabla workouts
├── model/
│   ├── User.java                     # Entidad usuario
│   ├── Workout.java                  # Entidad workout
│   ├── Exercise.java                 # Entidad ejercicio
│   └── SetEntry.java                 # Entidad set (reps + peso)
├── exception/
│   └── GlobalExceptionHandler.java   # Manejo global de errores
└── GymTrackerApplication.java
```

## 👨‍💻 Autor

Diego Bahen — [GitHub](https://github.com/Dbahen)