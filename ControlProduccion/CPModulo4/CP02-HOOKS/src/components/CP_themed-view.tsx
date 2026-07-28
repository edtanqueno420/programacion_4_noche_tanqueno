import { View, type ViewProps } from 'react-native';

import { ThemeColor } from '@/constants/CP_theme';
import { useTheme } from '@/hooks/CP_use-theme';

export type CP_ThemedViewProps = ViewProps & {
  lightColor?: string;
  darkColor?: string;
  type?: ThemeColor;
};

export function CP_ThemedView({ style, lightColor, darkColor, type, ...otherProps }: CP_ThemedViewProps) {
  const theme = useTheme();

  return <View style={[{ backgroundColor: theme[type ?? 'background'] }, style]} {...otherProps} />;
}
