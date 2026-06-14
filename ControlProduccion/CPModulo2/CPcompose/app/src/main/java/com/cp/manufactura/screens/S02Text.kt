package com.cp.manufactura.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun S02_TextScreen() {
    Column(
        modifier            = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Sección 2 · Etiquetas de producción",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        EtiquetaSeccion("1. ID de pieza")
        Text("Pieza: EN-001 - Engranaje recto")
        EtiquetaSeccion("2. Cantidad + prioridad")
        Text("Stock: 120 unidades",   fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Urgencia: ALTA",   fontSize = 18.sp, fontStyle  = FontStyle.Italic)
        Text("Lote: 45 unidades",     fontSize = 20.sp, fontWeight = FontWeight.Light)
        EtiquetaSeccion("3. Estado y advertencias")
        Text("Producción activa",
            color = Color(0xFF1976D2))
        Text("Inspección pendiente",
            textDecoration = TextDecoration.Underline)
        Text("Lote rechazado",
            textDecoration = TextDecoration.LineThrough,
            color          = MaterialTheme.colorScheme.onSurfaceVariant)
        EtiquetaSeccion("4. Notas técnicas")
        Text(
            text     = "Especificación: ISO 2768-mK para tolerancias generales. Material: Acero SAE 1045. Tratamiento térmico: revenido a 450°C.",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text     = "NOTA: Verificar dureza Rockwell C después del temple. Si HRC < 52, reprocesar. Registrar resultado en hoja de control.",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        EtiquetaSeccion("5. Escala tipográfica")
        Text("Órden de trabajo", style = MaterialTheme.typography.headlineMedium)
        Text("Línea de producción",     style = MaterialTheme.typography.titleLarge)
        Text("Detalle de pieza",      style = MaterialTheme.typography.bodyLarge)
        Text("Nota técnica",      style = MaterialTheme.typography.bodySmall)
        Text("Código interno",     style = MaterialTheme.typography.labelSmall)
        EtiquetaSeccion("6. Centro de texto")
        Text(
            text      = "CPManufactura - Control de Producción",
            textAlign = TextAlign.Center,
            modifier  = Modifier.fillMaxWidth()
        )
        Text(
            text      = "v2.1.0",
            textAlign = TextAlign.End,
            modifier  = Modifier.fillMaxWidth()
        )
    }
}

@Composable
internal fun EtiquetaSeccion(texto: String) {
    Text(
        text  = texto,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview(showBackground = true)
@Composable
fun S02_Preview() {
    MaterialTheme { S02_TextScreen() }
}
