import simpy
import random
import matplotlib.pyplot as plt

# Configuración inicial
RANDOM_SEED = 10
INTERVALO_LLEGADA = 5
TIEMPO_TRIAGE = 10
TIEMPO_ATENCION = {1: 30, 2: 20, 3: 15, 4: 10, 5: 5}
TIEMPO_RAYOS_X = 15
TIEMPO_LABORATORIO = 20

random.seed(RANDOM_SEED)

# Lista global para guardar los tiempos en el sistema
tiempos_en_sistema = []

def paciente(env, nombre, hospital):
    llegada = env.now
    # print(f"{nombre} llega en t={llegada:.2f}")

    # TRIAGE
    with hospital['enfermeras'].request() as req:
        yield req
        yield env.timeout(TIEMPO_TRIAGE)
        severidad = random.randint(1, 5)

    # DOCTOR
    with hospital['doctores'].request(priority=severidad) as req:
        yield req
        yield env.timeout(TIEMPO_ATENCION[severidad])

    # RAYOS X (30%)
    if random.random() < 0.3:
        with hospital['rayos_x'].request(priority=severidad) as req:
            yield req
            yield env.timeout(TIEMPO_RAYOS_X)

    # LABORATORIO (20%)
    if random.random() < 0.2:
        with hospital['laboratorio'].request(priority=severidad) as req:
            yield req
            yield env.timeout(TIEMPO_LABORATORIO)

    salida = env.now
    tiempo_total = salida - llegada
    tiempos_en_sistema.append(tiempo_total)
    # print(f"{nombre} sale en t={salida:.2f}, tiempo total: {tiempo_total:.2f}")

def generar_pacientes(env, hospital, num_pacientes):
    for i in range(1, num_pacientes + 1):
        yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADA))
        env.process(paciente(env, f"Paciente {i}", hospital))

def ejecutar_simulacion(tiempo_simulacion, num_pacientes, enfermeras, doctores, rayos_x, laboratorios):
    global tiempos_en_sistema
    tiempos_en_sistema = []  # Reiniciar antes de cada simulación

    env = simpy.Environment()
    hospital = {
        'enfermeras': simpy.Resource(env, capacity=enfermeras),
        'doctores': simpy.PriorityResource(env, capacity=doctores),
        'rayos_x': simpy.PriorityResource(env, capacity=rayos_x),
        'laboratorio': simpy.PriorityResource(env, capacity=laboratorios)
    }
    env.process(generar_pacientes(env, hospital, num_pacientes))
    env.run(until=tiempo_simulacion)

    return sum(tiempos_en_sistema) / len(tiempos_en_sistema)  # promedio

# ------------------------------------------------------------------------------------
# Simulación con diferentes cantidades de recursos (doctores en este ejemplo)
valores_doctores = [1, 2, 3, 4, 5]
promedios = []

for doctores in valores_doctores:
    promedio = ejecutar_simulacion(100, 50, enfermeras=2, doctores=doctores, rayos_x=1, laboratorios=1)
    promedios.append(promedio)
    print(f"Doctores: {doctores}, Tiempo promedio en sistema: {promedio:.2f}")

# Graficar resultados
plt.figure(figsize=(10, 5))
plt.plot(valores_doctores, promedios, marker='o', linestyle='-', color='blue')
plt.title('Impacto del número de doctores en el tiempo promedio de atención')
plt.xlabel('Cantidad de doctores')
plt.ylabel('Tiempo promedio en sala de emergencias')
plt.grid(True)
plt.show()
