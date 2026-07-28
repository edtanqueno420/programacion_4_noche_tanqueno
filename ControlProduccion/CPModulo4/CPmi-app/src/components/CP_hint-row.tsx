import type { ReactNode } from 'react';
import { View, StyleSheet } from 'react-native';

import { CP_ThemedText } from './CP_themed-text';
import { CP_ThemedView } from './CP_themed-view';

import { Spacing } from '@/constants/CP_theme';

type HintRowProps = {
  title?: string;
  hint?: ReactNode;
};

export function CP_HintRow({ title = 'Try editing', hint = 'app/CP_index.tsx' }: HintRowProps) {
  return (
    <View style={styles.stepRow}>
      <CP_ThemedText type="small">{title}</CP_ThemedText>
      <CP_ThemedView type="backgroundSelected" style={styles.codeSnippet}>
        <CP_ThemedText themeColor="textSecondary">{hint}</CP_ThemedText>
      </CP_ThemedView>
    </View>
  );
}

const styles = StyleSheet.create({
  stepRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  codeSnippet: {
    borderRadius: Spacing.two,
    paddingVertical: Spacing.half,
    paddingHorizontal: Spacing.two,
  },
});
