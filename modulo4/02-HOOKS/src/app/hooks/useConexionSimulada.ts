// hooks/useConexionSimulada.ts
import { useState, useEffect, useCallback } from 'react'

type EstadoConexion = 'desconectado' | 'conectando' | 'conectado' | 'error'

interface ResultadoConexion {
  estado: EstadoConexion
  intentos: number
  latencia: number | null
  reconectar: () => void
  reiniciar: () => void
}

export function useConexionSimulada(nombreServidor: string): ResultadoConexion {
  const [estado, setEstado] = useState<EstadoConexion>('desconectado')
  const [intentos, setIntentos] = useState<number>(0)
  const [latencia, setLatencia] = useState<number | null>(null)
  const [disparador, setDisparador] = useState<number>(0)  // fuerza re-ejecución del efecto

  useEffect(() => {
    if (estado !== 'conectando') return

    const timeout = setTimeout(() => {
      const falla = Math.random() < 0.4   // 40 % de probabilidad de fallo
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
    setDisparador(d => d + 1)   // garantiza que el efecto se re-ejecute
  }, [])

  const reiniciar = useCallback(() => {
    setEstado('desconectado')
    setIntentos(0)
    setLatencia(null)
  }, [])

  return { estado, intentos, latencia, reconectar, reiniciar }
}