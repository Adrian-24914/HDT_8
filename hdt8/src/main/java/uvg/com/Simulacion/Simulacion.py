import simpy
import random

# Configuración inicial
RANDOM_SEED = 10  # Fija la semilla para obtener resultados reproducibles
INTERVALO_LLEGADA = 5  # Tiempo medio entre llegadas de pacientes
NUM_ENFERMERAS = 2
NUM_DOCTORES = 3
NUM_RAYOS_X = 1
NUM_LABORATORIOS = 1
TIEMPO_TRIAGE = 10  # Tiempo que toma la enfermera en la evaluación inicial
TIEMPO_ATENCION = {1: 30, 2: 20, 3: 15, 4: 10, 5: 5}  # Tiempo de atención según severidad
TIEMPO_RAYOS_X = 15  # Tiempo que toma un estudio de rayos X
TIEMPO_LABORATORIO = 20  # Tiempo que toma un examen de laboratorio

random.seed(RANDOM_SEED)  # Fijamos la semilla para que las simulaciones sean comparables

def paciente(env, nombre, hospital):
    """ Simula el proceso de un paciente en la sala de emergencias """
    llegada = env.now
    print(f"{nombre} llega a la sala de emergencias en t={llegada:.2f}")

    # Etapa de triage
    with hospital['enfermeras'].request() as req:
        yield req  # Espera a que una enfermera esté disponible
        yield env.timeout(TIEMPO_TRIAGE)  # Simula el tiempo de evaluación
        severidad = random.randint(1, 5)  # Asigna una severidad aleatoria (1 es más urgente)
        print(f"{nombre} clasificado con severidad {severidad} en t={env.now:.2f}")

    # Espera por doctor
    with hospital['doctores'].request(priority=severidad) as req:
        yield req  # Espera a que un doctor esté disponible
        yield env.timeout(TIEMPO_ATENCION[severidad])  # Simula el tiempo de atención médica
        print(f"{nombre} atendido por doctor en t={env.now:.2f}")

    # Posible uso de rayos X
    if random.random() < 0.3:  # 30% de los pacientes requieren rayos X
        with hospital['rayos_x'].request(priority=severidad) as req:
            yield req  # Espera turno en la cola de prioridad de rayos X
            yield env.timeout(TIEMPO_RAYOS_X)  # Simula el tiempo del estudio
            print(f"{nombre} realizó rayos X en t={env.now:.2f}")

    # Posible uso de laboratorio
    if random.random() < 0.2:  # 20% de los pacientes requieren laboratorio
        with hospital['laboratorio'].request(priority=severidad) as req:
            yield req  # Espera turno en la cola de prioridad del laboratorio
            yield env.timeout(TIEMPO_LABORATORIO)  # Simula el tiempo del examen
            print(f"{nombre} realizó exámenes de laboratorio en t={env.now:.2f}")

    salida = env.now
    print(f"{nombre} sale de la sala de emergencias en t={salida:.2f}")

def generar_pacientes(env, hospital, num_pacientes):
    """ Genera pacientes a intervalos exponenciales hasta alcanzar el número especificado """
    for i in range(1, num_pacientes + 1):
        yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADA))  # Simula el tiempo entre llegadas
        env.process(paciente(env, f"Paciente {i}", hospital))  # Inicia el proceso de cada paciente

def ejecutar_simulacion(tiempo_simulacion, num_pacientes):
    """ Configura y ejecuta la simulación del hospital """
    env = simpy.Environment()
    hospital = {
        'enfermeras': simpy.Resource(env, capacity=NUM_ENFERMERAS),  # Recursos sin prioridad
        'doctores': simpy.PriorityResource(env, capacity=NUM_DOCTORES),  # Recursos con prioridad
        'rayos_x': simpy.PriorityResource(env, capacity=NUM_RAYOS_X),
        'laboratorio': simpy.PriorityResource(env, capacity=NUM_LABORATORIOS)
    }
    env.process(generar_pacientes(env, hospital, num_pacientes))
    env.run(until=tiempo_simulacion)  # Corre la simulación hasta el tiempo definido

# Ejecutar simulación con duración de 100 unidades de tiempo y 50 pacientes
ejecutar_simulacion(100, 50)
