import { version } from 'expo/package.json';
import { Image } from 'expo-image';
import { useColorScheme, StyleSheet } from 'react-native';

import { CP_ThemedText } from './CP_themed-text';
import { CP_ThemedView } from './CP_themed-view';

import { Spacing } from '@/constants/CP_theme';

export function CP_WebBadge() {
  const scheme = useColorScheme();

  return (
    <CP_ThemedView style={styles.container}>
      <CP_ThemedText type="code" themeColor="textSecondary" style={styles.versionText}>
        v{version}
      </CP_ThemedText>
      <Image
        source={
          scheme === 'dark'
            ? require('@/assets/images/expo-badge-white.png')
            : require('@/assets/images/expo-badge.png')
        }
        style={styles.badgeImage}
      />
    </CP_ThemedView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: Spacing.five,
    alignItems: 'center',
    gap: Spacing.two,
  },
  versionText: {
    textAlign: 'center',
  },
  badgeImage: {
    width: 123,
    aspectRatio: 123 / 24,
  },
});
