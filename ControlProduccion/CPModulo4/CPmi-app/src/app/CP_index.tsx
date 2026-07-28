import { Text, View } from 'react-native'
import { CP_Paso1 } from './CP_Paso1'
import { CP_Paso2 } from './CP_Paso2'
import { CP_Paso3 } from './CP_Paso3'

const PASO = 3

export default function Index() {
  switch (PASO) {
    case 1:
      return <CP_Paso1 />
    case 2:
      return <CP_Paso2 />
    case 3:
      return <CP_Paso3 />
    default:
      return (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <Text>Paso {PASO}: crea la pantalla primero</Text>
        </View>
      )
  }
}
