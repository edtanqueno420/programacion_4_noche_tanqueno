// app/index.tsx — agrega la función Paso6
import {
  useRef, useState, useEffect,
  
} from 'react'
import {
  StyleSheet, Text, TextInput, View, Pressable, ScrollView,
  TextInput as RNTextInput,useWindowDimensions, useColorScheme,
} from 'react-native'
import { useConexionSimulada } from '../hooks/useConexionSimulada'

function Paso6() {
  // ── Dimensiones y tema ───────────────────────────────────────────
  const { width } = useWindowDimensions()
  const esquema   = useColorScheme()
  const esModoOscuro = esquema === 'dark'

  const colores = {
    fondo:   esModoOscuro ? '#0d1b2a' : '#f0f4f8',
    tarjeta: esModoOscuro ? '#1a2a3a' : '#ffffff',
    texto:   esModoOscuro ? '#e8eaed' : '#1a1a1a',
    detalle: esModoOscuro ? '#9aa0a6' : '#666666',
    borde:   esModoOscuro ? '#2d3e50' : '#e0e0e0',
  }

  // ── Custom hook de conexión ──────────────────────────────────────
  const { estado, intentos, latencia, reconectar, reiniciar } =
    useConexionSimulada('postgres-01')

  // ── Ref: foco automático en el input al montar ───────────────────
  const inputFiltroRef = useRef<RNTextInput>(null)
  const [filtro, setFiltro] = useState<string>('')

  useEffect(() => {
    const timer = setTimeout(() => {
      inputFiltroRef.current?.focus()
    }, 300)   // pequeño delay para que el teclado no interrumpa la animación de entrada
    return () => clearTimeout(timer)
  }, [])

  // ── Ping periódico de logs (useEffect + setInterval) ─────────────
  const [logs, setLogs] = useState<string[]>([])

  useEffect(() => {
    if (estado !== 'conectado') return

    const intervalo = setInterval(() => {
      const ahora = new Date().toLocaleTimeString()
      const eventos = [
        `[${ahora}] Query completada en ${Math.floor(Math.random() * 50) + 5}ms`,
        `[${ahora}] Conexión aceptada desde 10.0.2.1`,
        `[${ahora}] Checkpoint completado — WAL sincronizado`,
        `[${ahora}] Vacío automático en tabla sessions`,
      ]
      const evento = eventos[Math.floor(Math.random() * eventos.length)]
      setLogs(prev => [evento, ...prev].slice(0, 20))   // máximo 20 entradas
    }, 1500)

    return () => clearInterval(intervalo)
  }, [estado])   // el intervalo de logs solo corre cuando estamos conectados

  // ── Logs filtrados (variable derivada, no estado) ─────────────────
  const logsFiltrados = filtro.trim()
    ? logs.filter(l => l.toLowerCase().includes(filtro.toLowerCase()))
    : logs

  // ── Colores por estado de conexión ───────────────────────────────
  const colorConexion: Record<string, string> = {
    desconectado: '#757575',
    conectando:   '#1565c0',
    conectado:    '#2e7d32',
    error:        '#c62828',
  }

  const esAncho = width > 600

  return (
    <View style={[styles.contenedor, { backgroundColor: colores.fondo }]}>
      {/* Encabezado */}
      <Text style={[styles.titulo, { color: colores.texto }]}>
        Estado del Servidor
      </Text>

      {/* Tarjeta principal de conexión */}
      <View style={[
        styles.tarjeta,
        {
          backgroundColor: colores.tarjeta,
          borderColor: colorConexion[estado],
          width: '100%',
        },
      ]}>
        <View style={{ flexDirection: 'row', justifyContent: 'space-between' }}>
          <Text style={[styles.nombreServidor, { color: colores.texto }]}>
            postgres-01
          </Text>
          <Text style={[styles.etiqueta, { color: colorConexion[estado] }]}>
            {estado.toUpperCase()}
          </Text>
        </View>

        <Text style={[styles.detalle, { color: colores.detalle }]}>
          10.0.2.50 · PostgreSQL 16 · Puerto 5432
        </Text>

        <Text style={[styles.detalle, { color: colores.detalle }]}>
          Intentos: {intentos}
          {latencia !== null ? `  ·  ${latencia} ms` : ''}
          {'  ·  '}Tema: {esquema ?? 'sin preferencia'}
        </Text>

        <View style={{ flexDirection: 'row', gap: 8, marginTop: 10 }}>
          <Pressable
            style={({ pressed }) => [
              styles.boton,
              estado === 'conectando'
                ? styles.botonDeshabilitado
                : styles.botonActivo,
              pressed && { opacity: 0.75 },
              { flex: 1 },
            ]}
            onPress={reconectar}
            disabled={estado === 'conectando'}
          >
            <Text style={styles.textoBoton}>
              {estado === 'conectando' ? 'Conectando…' : 'Conectar / Reconectar'}
            </Text>
          </Pressable>

          <Pressable
            style={({ pressed }) => [
              {
                paddingHorizontal: 14,
                borderRadius: 8,
                borderWidth: 1,
                borderColor: colores.borde,
                justifyContent: 'center',
              },
              pressed && { opacity: 0.6 },
            ]}
            onPress={reiniciar}
          >
            <Text style={{ color: colores.detalle, fontSize: 13 }}>Reset</Text>
          </Pressable>
        </View>
      </View>

      {/* Filtro de logs con foco automático (useRef) */}
      <TextInput
        ref={inputFiltroRef}
        style={[
          styles.input,
          {
            backgroundColor: colores.tarjeta,
            borderColor: colores.borde,
            color: colores.texto,
            width: '100%',
          },
        ]}
        value={filtro}
        onChangeText={setFiltro}
        placeholder="Filtrar logs…"
        placeholderTextColor={colores.detalle}
        autoCapitalize="none"
      />

      {/* Log en tiempo real */}
      <ScrollView
        style={[
          styles.logContenedor,
          {
            backgroundColor: esModoOscuro ? '#0a1520' : '#1e1e1e',
            width: '100%',
          },
        ]}
        showsVerticalScrollIndicator={false}
      >
        {estado !== 'conectado' ? (
          <Text style={styles.logVacio}>
            {estado === 'desconectado' || estado === 'error'
              ? 'Sin conexión — los logs aparecen al conectar'
              : 'Estableciendo conexión…'}
          </Text>
        ) : logsFiltrados.length === 0 ? (
          <Text style={styles.logVacio}>Sin entradas que coincidan con "{filtro}"</Text>
        ) : (
          logsFiltrados.map((log, i) => (
            <Text key={i} style={styles.logLinea}>{log}</Text>
          ))
        )}
      </ScrollView>

      {/* Info de dimensiones — útil durante desarrollo */}
      <Text style={[styles.detalle, { color: colores.detalle, fontSize: 11 }]}>
        {Math.round(width)} dp · {esAncho ? 'layout ancho' : 'layout normal'}
      </Text>
    </View>
  )
}

// Estilos adicionales para Paso 6 (incluidos en el bloque de referencia final)
const stylesLog = StyleSheet.create({
  logContenedor: {
    borderRadius: 8,
    padding: 12,
    maxHeight: 220,
  },
  logLinea: {
    fontSize: 11,
    fontFamily: 'monospace',   // en iOS usa 'Courier New'
    color: '#a8d8a8',
    lineHeight: 18,
  },
  logVacio: {
    fontSize: 12,
    color: '#666',
    fontStyle: 'italic',
  },
})