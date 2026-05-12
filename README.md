# 💪 GymTracker API

REST API para registrar y gestionar entrenamientos de gimnasio. Permite llevar un historial de workouts, ejercicios y sets con su peso y repeticiones.

## 🛠 Tecnologías

- Java 22
- Spring Boot 4
- Spring Data JPA
- H2 Database (persistencia en archivo local)
- Maven

## 🚀 Cómo correr el proyecto localmente

### Requisitos
- Java 22 o superior
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

La app levanta en `http://localhost:8080`

## 📋 Endpoints

### Workouts

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/workouts` | Obtener todos los workouts |
| GET | `/workouts/fecha/{fecha}` | Obtener workouts por fecha |
| POST | `/workouts` | Crear un nuevo workout |
| PUT | `/workouts/{id}` | Actualizar un workout |
| DELETE | `/workouts/{id}` | Eliminar un workout |

## 📦 Ejemplos de uso

### Crear un workout — POST /workouts

```json
{
  "date": "2025-01-15",
  "exercise": {
    "name": "Peso Muerto",
    "muscleGroup": "Espalda"
  },
  "sets": [
    {
      "reps": 5,
      "weight": 140.0
    },
    {
      "reps": 5,
      "weight": 140.0
    },
    {
      "reps": 3,
      "weight": 150.0
    }
  ]
}
```

### Obtener workouts por fecha — GET /workouts/fecha/2025-01-15

```json
[
  {
    "id": 1,
    "date": "2025-01-15",
    "exercise": {
      "id": 1,
      "name": "Peso Muerto",
      "muscleGroup": "Espalda"
    },
    "sets": [
      { "id": 1, "reps": 5, "weight": 140.0 },
      { "id": 2, "reps": 5, "weight": 140.0 },
      { "id": 3, "reps": 3, "weight": 150.0 }
    ]
  }
]
```

## 🏗 Estructura del proyecto

```
src/main/java/com/dbahen/GymTracker/
├── controller/
│   └── WorkoutController.java    # Endpoints HTTP
├── service/
│   └── WorkoutService.java       # Lógica de negocio
├── repository/
│   └── WorkoutRepository.java    # Acceso a base de datos
├── model/
│   ├── Workout.java              # Entidad principal
│   ├── Exercise.java             # Ejercicio
│   └── SetEntry.java             # Set (reps + peso)
├── exception/
│   └── GlobalExceptionHandler.java
└── GymTrackerApplication.java
```

## 👨‍💻 Autor

Diego Bahen — [GitHub](https://github.com/Dbahen)
