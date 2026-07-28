import { View, Text } from 'react-native'
import { CP_Paso1 } from './components/CP_Paso1'
import { CP_Paso2 } from './components/CP_Paso2'
import { CP_Paso3 } from './components/CP_Paso3'
import { CP_Paso4 } from './components/CP_Paso4'
import { CP_Paso5 } from './components/CP_Paso5'
import { CP_Paso6 } from './components/CP_Paso6'

const PASO: number = 1

export default function Index() {
  switch (PASO) {
    case 1:
      return <CP_Paso1 />
    case 2:
      return <CP_Paso2 />
    case 3:
      return <CP_Paso3 />
    case 4:
      return <CP_Paso4 />
    case 5:
      return <CP_Paso5 />
    case 6:
      return <CP_Paso6 />
    default:
      return (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <Text>Paso {PASO}: crea la pantalla primero</Text>
        </View>
      )
  }
}
