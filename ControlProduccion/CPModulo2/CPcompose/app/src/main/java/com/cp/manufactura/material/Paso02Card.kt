package com.cp.manufactura.material

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.manufactura.model.Maquina
import com.cp.manufactura.model.maquinasDeMuestra

@Composable
fun TarjetaMaquina(
    maquina:  Maquina,
    onClick:   () -> Unit = {},
    onActivar:  () -> Unit = {},
    onMantenimiento: () -> Unit = {}
) {
    ElevatedCard(
        onClick  = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier          = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier         = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(
                        if (maquina.operativa) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.errorContainer
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text       = maquina.nombre.first().uppercase(),
                    style      = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color      = if (maquina.operativa) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onErrorContainer
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = maquina.nombre,
                    style      = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text  = maquina.tipo,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(4.dp))
                AssistChip(
                    onClick = {},
                    label   = { Text(maquina.ubicacion,
                        style = MaterialTheme.typography.labelSmall) }
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onMantenimiento) {
                    Icon(
                        imageVector        = if (maquina.operativa) Icons.Default.CheckCircle
                        else Icons.Default.Build,
                        contentDescription = if (maquina.operativa) "Operativa" else "En mantenimiento",
                        tint               = if (maquina.operativa)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                }
                IconButton(onClick = onActivar) {
                    Icon(
                        imageVector        = Icons.Default.PowerSettingsNew,
                        contentDescription = "Encender/Apagar",
                        tint               = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun Paso02_CardScreen() {
    Column(
        modifier            = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Paso 2 · Tarjetas de máquina",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        Text("ElevatedCard — estación de trabajo",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary)

        maquinasDeMuestra.take(3).forEach { maquina ->
            TarjetaMaquina(
                maquina  = maquina,
                onClick   = { },
                onActivar  = { },
                onMantenimiento = { }
            )
        }

        HorizontalDivider()
        Text("Variantes de tarjetas",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary)

        Card(modifier = Modifier.fillMaxWidth()) {
            Text("Card — operativa",
                Modifier.padding(16.dp))
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Text("ElevatedCard — en espera",
                Modifier.padding(16.dp))
        }

        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Text("OutlinedCard — fuera de línea",
                Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Paso02_Preview() {
    MaterialTheme { Paso02_CardScreen() }
}
