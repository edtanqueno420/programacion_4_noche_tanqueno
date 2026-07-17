import { StyleSheet, Text, View } from 'react-native'

export function Paso3() {
  return (
    <View style={styles.contenedor}>
      <Text style={styles.titulo}>Perfil de Usuario</Text>
      <Text style={styles.subtitulo}>Edison Tanqueño</Text>
      <Text style={styles.detalle}>edison@espe.edu.ec · Administrador</Text>
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