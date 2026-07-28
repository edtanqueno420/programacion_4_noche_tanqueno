import { StyleSheet, Text, View } from 'react-native'

export function CP_Paso3() {
  return (
    <View style={styles.contenedor}>
      <Text style={styles.titulo}>Perfil de Operador</Text>
      <Text style={styles.subtitulo}>Edison Tanqueno</Text>
      <Text style={styles.detalle}>edison@espe.edu.ec · Supervisor de Produccion</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  contenedor: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f5f5f5',
    gap: 8,
  },
  titulo: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#1a1a1a',
  },
  subtitulo: {
    fontSize: 16,
    color: '#333',
  },
  detalle: {
    fontSize: 13,
    color: '#777',
  },
})
