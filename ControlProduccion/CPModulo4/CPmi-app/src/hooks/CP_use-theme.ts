import { Colors } from '@/constants/CP_theme';
import { useColorScheme } from '@/hooks/CP_use-color-scheme';

export function useTheme() {
  const scheme = useColorScheme();
  const theme = scheme === 'unspecified' ? 'light' : scheme;

  return Colors[theme];
}
