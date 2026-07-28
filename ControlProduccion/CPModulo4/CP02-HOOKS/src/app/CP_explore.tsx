import { Image } from 'expo-image';
import { SymbolView } from 'expo-symbols';
import { Platform, Pressable, ScrollView, StyleSheet } from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';

import { CP_ExternalLink } from '@/components/CP_external-link';
import { CP_ThemedText } from '@/components/CP_themed-text';
import { CP_ThemedView } from '@/components/CP_themed-view';
import { CP_Collapsible } from '@/components/ui/CP_collapsible';
import { CP_WebBadge } from '@/components/CP_web-badge';
import { BottomTabInset, MaxContentWidth, Spacing } from '@/constants/CP_theme';
import { useTheme } from '@/hooks/CP_use-theme';

export default function TabTwoScreen() {
  const safeAreaInsets = useSafeAreaInsets();
  const insets = {
    ...safeAreaInsets,
    bottom: safeAreaInsets.bottom + BottomTabInset + Spacing.three,
  };
  const theme = useTheme();

  const contentPlatformStyle = Platform.select({
    android: {
      paddingTop: insets.top,
      paddingLeft: insets.left,
      paddingRight: insets.right,
      paddingBottom: insets.bottom,
    },
    web: {
      paddingTop: Spacing.six,
      paddingBottom: Spacing.four,
    },
  });

  return (
    <ScrollView
      style={[styles.scrollView, { backgroundColor: theme.background }]}
      contentInset={insets}
      contentContainerStyle={[styles.contentContainer, contentPlatformStyle]}>
      <CP_ThemedView style={styles.container}>
        <CP_ThemedView style={styles.titleContainer}>
          <CP_ThemedText type="subtitle">Explorar Lineas</CP_ThemedText>
          <CP_ThemedText style={styles.centerText} themeColor="textSecondary">
            Panel de Control de Produccion{'\n'}Sistema de monitoreo de lineas.
          </CP_ThemedText>

          <CP_ExternalLink href="https://docs.expo.dev" asChild>
            <Pressable style={({ pressed }) => pressed && styles.pressed}>
              <CP_ThemedView type="backgroundElement" style={styles.linkButton}>
                <CP_ThemedText type="link">Documentacion SCADA</CP_ThemedText>
                <SymbolView
                  tintColor={theme.text}
                  name={{ ios: 'arrow.up.right.square', android: 'link', web: 'link' }}
                  size={12}
                />
              </CP_ThemedView>
            </Pressable>
          </CP_ExternalLink>
        </CP_ThemedView>

        <CP_ThemedView style={styles.sectionsWrapper}>
          <CP_Collapsible title="Enrutamiento por archivos">
            <CP_ThemedText type="small">
              Esta aplicacion tiene dos pantallas: <CP_ThemedText type="code">src/app/CP_index.tsx</CP_ThemedText> y{' '}
              <CP_ThemedText type="code">src/app/CP_explore.tsx</CP_ThemedText>
            </CP_ThemedText>
            <CP_ThemedText type="small">
              El archivo de layout en <CP_ThemedText type="code">src/app/CP__layout.tsx</CP_ThemedText> configura
              el navegador por modulos.
            </CP_ThemedText>
            <CP_ExternalLink href="https://docs.expo.dev/router/introduction">
              <CP_ThemedText type="linkPrimary">Saber mas</CP_ThemedText>
            </CP_ExternalLink>
          </CP_Collapsible>

          <CP_Collapsible title="Soporte Android, iOS y web">
            <CP_ThemedView type="backgroundElement" style={styles.collapsibleContent}>
              <CP_ThemedText type="small">
                Puede abrir este proyecto en Android, iOS y la web. Para abrir la version web,
                presione <CP_ThemedText type="smallBold">w</CP_ThemedText> en la terminal.
              </CP_ThemedText>
              <Image
                source={require('@/assets/images/tutorial-web.png')}
                style={styles.imageTutorial}
              />
            </CP_ThemedView>
          </CP_Collapsible>

          <CP_Collapsible title="Imagenes">
            <CP_ThemedText type="small">
              Para imagenes estaticas, puede usar los sufijos <CP_ThemedText type="code">@2x</CP_ThemedText> y{' '}
              <CP_ThemedText type="code">@3x</CP_ThemedText> para diferentes densidades de pantalla.
            </CP_ThemedText>
            <Image source={require('@/assets/images/react-logo.png')} style={styles.imageReact} />
            <CP_ExternalLink href="https://reactnative.dev/docs/images">
              <CP_ThemedText type="linkPrimary">Saber mas</CP_ThemedText>
            </CP_ExternalLink>
          </CP_Collapsible>

          <CP_Collapsible title="Componentes modo claro y oscuro">
            <CP_ThemedText type="small">
              Esta plantilla soporta modo claro y oscuro. El hook{' '}
              <CP_ThemedText type="code">useColorScheme()</CP_ThemedText> permite detectar el esquema
              de color del usuario para ajustar los colores de la interfaz.
            </CP_ThemedText>
            <CP_ExternalLink href="https://docs.expo.dev/develop/user-interface/color-themes/">
              <CP_ThemedText type="linkPrimary">Saber mas</CP_ThemedText>
            </CP_ExternalLink>
          </CP_Collapsible>

          <CP_Collapsible title="Animaciones">
            <CP_ThemedText type="small">
              Esta plantilla incluye un ejemplo de componente animado. El componente{' '}
              <CP_ThemedText type="code">src/components/ui/CP_collapsible.tsx</CP_ThemedText> usa
              la libreria <CP_ThemedText type="code">react-native-reanimated</CP_ThemedText> para
              animar la apertura de esta pista.
            </CP_ThemedText>
          </CP_Collapsible>
        </CP_ThemedView>
        {Platform.OS === 'web' && <CP_WebBadge />}
      </CP_ThemedView>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  scrollView: {
    flex: 1,
  },
  contentContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
  },
  container: {
    maxWidth: MaxContentWidth,
    flexGrow: 1,
  },
  titleContainer: {
    gap: Spacing.three,
    alignItems: 'center',
    paddingHorizontal: Spacing.four,
    paddingVertical: Spacing.six,
  },
  centerText: {
    textAlign: 'center',
  },
  pressed: {
    opacity: 0.7,
  },
  linkButton: {
    flexDirection: 'row',
    paddingHorizontal: Spacing.four,
    paddingVertical: Spacing.two,
    borderRadius: Spacing.five,
    justifyContent: 'center',
    gap: Spacing.one,
    alignItems: 'center',
  },
  sectionsWrapper: {
    gap: Spacing.five,
    paddingHorizontal: Spacing.four,
    paddingTop: Spacing.three,
  },
  collapsibleContent: {
    alignItems: 'center',
  },
  imageTutorial: {
    width: '100%',
    aspectRatio: 296 / 171,
    borderRadius: Spacing.three,
    marginTop: Spacing.two,
  },
  imageReact: {
    width: 100,
    height: 100,
    alignSelf: 'center',
  },
});
