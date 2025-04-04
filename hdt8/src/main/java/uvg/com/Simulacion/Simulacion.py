import simpy
import random
import matplotlib.pyplot as plt

# -------------------------------
# CONFIGURACIÓN INICIAL
# -------------------------------

RANDOM_SEED = 10  # Semilla para reproducibilidad
INTERVALO_LLEGADA = 5  # Tiempo medio entre llegadas de pacientes
TIEMPO_TRIAGE = 10  # Tiempo que tarda una enfermera en evaluar a un paciente
TIEMPO_ATENCION = {1: 30, 2: 20, 3: 15, 4: 10, 5: 5}  # Tiempo de atención médica por severidad
TIEMPO_RAYOS_X = 15  # Tiempo de uso de rayos X
TIEMPO_LABORATORIO = 20  # Tiempo para realizar exámenes de laboratorio

random.seed(RANDOM_SEED)
tiempos_en_sistema = []  # Lista global para almacenar el tiempo total de cada paciente en el sistema

# -------------------------------
# PROCESO DE UN PACIENTE
# -------------------------------
def paciente(env, nombre, hospital):
    llegada = env.now  # Hora en que el paciente llega

    # TRIAGE con enfermera
    with hospital['enfermeras'].request() as req:
        yield req
        yield env.timeout(TIEMPO_TRIAGE)
        severidad = random.randint(1, 5)  # Clasificación de emergencia (1 = mayor prioridad)

    # ATENCIÓN MÉDICA con doctor
    with hospital['doctores'].request(priority=severidad) as req:
        yield req
        yield env.timeout(TIEMPO_ATENCION[severidad])

    # RAYOS X (probabilidad del 30%)
    if random.random() < 0.3:
        with hospital['rayos_x'].request(priority=severidad) as req:
            yield req
            yield env.timeout(TIEMPO_RAYOS_X)

    # LABORATORIO (probabilidad del 20%)
    if random.random() < 0.2:
        with hospital['laboratorio'].request(priority=severidad) as req:
            yield req
            yield env.timeout(TIEMPO_LABORATORIO)

    salida = env.now  # Hora en que el paciente sale
    tiempos_en_sistema.append(salida - llegada)  # Se registra su tiempo total en el sistema

# -------------------------------
# GENERADOR DE PACIENTES
# -------------------------------
def generar_pacientes(env, hospital, num_pacientes):
    for i in range(num_pacientes):
        yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADA))  # Tiempo entre llegadas
        env.process(paciente(env, f"Paciente {i+1}", hospital))  # Lanza proceso de atención

# -------------------------------
# EJECUCIÓN DE LA SIMULACIÓN
# -------------------------------
def ejecutar_simulacion(tiempo_simulacion, num_pacientes, enfermeras, doctores, rayos_x, laboratorios):
    global tiempos_en_sistema
    tiempos_en_sistema = []  # Se limpia la lista antes de cada simulación

    # Crear entorno de simulación y recursos
    env = simpy.Environment()
    hospital = {
        'enfermeras': simpy.Resource(env, capacity=enfermeras),
        'doctores': simpy.PriorityResource(env, capacity=doctores),
        'rayos_x': simpy.PriorityResource(env, capacity=rayos_x),
        'laboratorio': simpy.PriorityResource(env, capacity=laboratorios)
    }

    # Iniciar generador de pacientes
    env.process(generar_pacientes(env, hospital, num_pacientes))
    env.run(until=tiempo_simulacion)  # Corre hasta que se alcanza el tiempo total

    # Retorna el tiempo promedio que los pacientes estuvieron en la sala de emergencias
    return sum(tiempos_en_sistema) / len(tiempos_en_sistema)

# -------------------------------
# VARIACIÓN DE DOCTORES
# -------------------------------
valores_doctores = [1, 2, 3, 4, 5]
promedios_doctores = []

for d in valores_doctores:
    promedio = ejecutar_simulacion(100, 50, enfermeras=2, doctores=d, rayos_x=1, laboratorios=1)
    promedios_doctores.append(promedio)

# -------------------------------
# VARIACIÓN DE ENFERMERAS
# -------------------------------
valores_enfermeras = [1, 2, 3, 4, 5]
promedios_enfermeras = []

for e in valores_enfermeras:
    promedio = ejecutar_simulacion(100, 50, enfermeras=e, doctores=3, rayos_x=1, laboratorios=1)
    promedios_enfermeras.append(promedio)

# -------------------------------
# VARIACIÓN DE LABORATORIOS
# -------------------------------
valores_laboratorios = [1, 2, 3, 4, 5]
promedios_laboratorios = []

for l in valores_laboratorios:
    promedio = ejecutar_simulacion(100, 50, enfermeras=2, doctores=3, rayos_x=1, laboratorios=l)
    promedios_laboratorios.append(promedio)

# -------------------------------
# GRAFICAR RESULTADOS
# -------------------------------
plt.figure(figsize=(15, 5))

# Subplot 1: Doctores
plt.subplot(1, 3, 1)
plt.plot(valores_doctores, promedios_doctores, marker='o', color='blue')
plt.title('Doctores vs Tiempo promedio')
plt.xlabel('Cantidad de doctores')
plt.ylabel('Tiempo promedio (unidades)')
plt.grid(True)

# Subplot 2: Enfermeras
plt.subplot(1, 3, 2)
plt.plot(valores_enfermeras, promedios_enfermeras, marker='o', color='green')
plt.title('Enfermeras vs Tiempo promedio')
plt.xlabel('Cantidad de enfermeras')
plt.ylabel('Tiempo promedio (unidades)')
plt.grid(True)

# Subplot 3: Laboratorios
plt.subplot(1, 3, 3)
plt.plot(valores_laboratorios, promedios_laboratorios, marker='o', color='orange')
plt.title('Laboratorios vs Tiempo promedio')
plt.xlabel('Cantidad de laboratorios')
plt.ylabel('Tiempo promedio (unidades)')
plt.grid(True)

plt.tight_layout()
plt.show()
