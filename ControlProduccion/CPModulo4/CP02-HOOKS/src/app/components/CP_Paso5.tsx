import { useState, useEffect, useCallback } from 'react'
import { StyleSheet, Text, View, Pressable } from 'react-native'

type EstadoConexion = 'desconectado' | 'conectando' | 'conectado' | 'error'

interface ResultadoConexion {
  estado: EstadoConexion
  intentos: number
  latencia: number | null
  reconectar: () => void
  reiniciar: () => void
}

function useConexionSCADA(nombreMaquina: string): ResultadoConexion {
  const [estado, setEstado] = useState<EstadoConexion>('desconectado')
  const [intentos, setIntentos] = useState<number>(0)
  const [latencia, setLatencia] = useState<number | null>(null)
  const [disparador, setDisparador] = useState<number>(0)

  useEffect(() => {
    if (estado !== 'conectando') return

    const timeout = setTimeout(() => {
      const falla = Math.random() < 0.4
      setIntentos(n => n + 1)

      if (falla) {
        setEstado('error')
        setLatencia(null)
      } else {
        const ms = Math.floor(Math.random() * 150) + 20
        setLatencia(ms)
        setEstado('conectado')
      }
    }, 1200)

    return () => clearTimeout(timeout)
  }, [estado, disparador])

  const reconectar = useCallback(() => {
    setEstado('conectando')
    setDisparador(d => d + 1)
  }, [])

  const reiniciar = useCallback(() => {
    setEstado('desconectado')
    setIntentos(0)
    setLatencia(null)
  }, [])

  return { estado, intentos, latencia, reconectar, reiniciar }
}

interface PropsTarjeta {
  nombre: string
  hook: ReturnType<typeof useConexionSCADA>
}

function TarjetaMaquina({ nombre, hook }: PropsTarjeta) {
  const { estado, intentos, latencia, reconectar, reiniciar } = hook

  const colorEstado: Record<string, string> = {
    desconectado: '#757575',
    conectando:   '#1565c0',
    conectado:    '#2e7d32',
    error:        '#c62828',
  }

  const textoBoton: Record<string, string> = {
    desconectado: 'Conectar',
    conectando:   'Conectando...',
    conectado:    'Reconectar',
    error:        'Reintentar',
  }

  return (
    <View style={[estilos.tarjeta, { borderColor: colorEstado[estado], width: '100%' }]}>
      <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
        <Text style={estilos.nombreMaquina}>{nombre}</Text>
        <Text style={[estilos.etiqueta, { color: colorEstado[estado] }]}>
          {estado.toUpperCase()}
        </Text>
      </View>

      <Text style={estilos.detalle}>
        Intentos: {intentos}
        {latencia !== null ? `  ·  ${latencia} ms` : ''}
      </Text>

      <View style={{ flexDirection: 'row', gap: 8, marginTop: 8 }}>
        <Pressable
          style={({ pressed }) => [
            estilos.boton,
            estado === 'conectando' ? estilos.botonDeshabilitado : estilos.botonActivo,
            pressed && { opacity: 0.75 },
            { flex: 1 },
          ]}
          onPress={reconectar}
          disabled={estado === 'conectando'}
        >
          <Text style={estilos.textoBoton}>{textoBoton[estado]}</Text>
        </Pressable>

        <Pressable
          style={({ pressed }) => [
            { paddingHorizontal: 16, borderRadius: 8, borderWidth: 1, borderColor: '#1565c0', justifyContent: 'center' },
            pressed && { opacity: 0.75 },
          ]}
          onPress={reiniciar}
        >
          <Text style={[estilos.textoSecundario, { fontSize: 13 }]}>Reiniciar</Text>
        </Pressable>
      </View>
    </View>
  )
}

export function CP_Paso5() {
  const linea1Hook = useConexionSCADA('Linea-02')
  const linea2Hook = useConexionSCADA('Maquina-02')

  return (
    <View style={estilos.contenedor}>
      <Text style={estilos.titulo}>Estado de Maquinaria</Text>

      <TarjetaMaquina nombre="Linea-02" hook={linea1Hook} />
      <TarjetaMaquina nombre="Maquina-02" hook={linea2Hook} />
    </View>
  )
}

const estilos = StyleSheet.create({
  contenedor: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 24,
    backgroundColor: '#f5f5f5',
    gap: 14,
  },
  titulo: {
    fontSize: 20,
    fontWeight: '700',
    color: '#1a1a1a',
  },
  tarjeta: {
    padding: 16,
    borderRadius: 10,
    borderWidth: 2,
    backgroundColor: '#fff',
    gap: 6,
  },
  etiqueta: {
    fontSize: 12,
    fontWeight: '600',
    letterSpacing: 0.5,
  },
  nombreMaquina: {
    fontSize: 15,
    fontWeight: '600',
    color: '#1a1a1a',
  },
  detalle: {
    fontSize: 13,
    color: '#666',
  },
  boton: {
    paddingVertical: 12,
    borderRadius: 8,
    alignItems: 'center',
    paddingHorizontal: 20,
  },
  botonActivo: {
    backgroundColor: '#1565c0',
  },
  botonDeshabilitado: {
    backgroundColor: '#90a4ae',
  },
  textoBoton: {
    color: '#fff',
    fontWeight: '600',
    fontSize: 14,
  },
  textoSecundario: {
    color: '#1565c0',
    fontSize: 14,
  },
})
