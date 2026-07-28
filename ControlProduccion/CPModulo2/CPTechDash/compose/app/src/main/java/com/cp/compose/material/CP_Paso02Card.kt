package com.cp.compose.material

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
import com.cp.compose.model.CP_Contacto
import com.cp.compose.model.CP_contactosDeMuestra

@Composable
fun CP_TarjetaContacto(
    contacto:  CP_Contacto,
    onClick:   () -> Unit = {},
    onLlamar:  () -> Unit = {},
    onFavorito: () -> Unit = {}
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
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text       = contacto.nombre.first().uppercase(),
                    style      = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color      = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = contacto.nombre,
                    style      = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text  = contacto.email,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(4.dp))
                AssistChip(
                    onClick = {},
                    label   = { Text(contacto.telefono,
                        style = MaterialTheme.typography.labelSmall) }
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onFavorito) {
                    Icon(
                        imageVector        = if (contacto.favorito) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = if (contacto.favorito) "Quitar favorito"
                        else "Marcar favorito",
                        tint               = if (contacto.favorito)
                            MaterialTheme.colorScheme.error
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = onLlamar) {
                    Icon(
                        imageVector        = Icons.Default.Phone,
                        contentDescription = "Llamar",
                        tint               = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun CP_Paso02_CardScreen() {
    Column(
        modifier            = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Paso 2 - Card y ElevatedCard",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()

        Text("ElevatedCard - interactivo",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary)

        CP_contactosDeMuestra.take(3).forEach { contacto ->
            CP_TarjetaContacto(
                contacto  = contacto,
                onClick   = { },
                onLlamar  = { },
                onFavorito = { }
            )
        }

        HorizontalDivider()
        Text("Comparacion de variantes",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary)

        Card(modifier = Modifier.fillMaxWidth()) {
            Text("Card - sin elevacion visible",
                Modifier.padding(16.dp))
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Text("ElevatedCard - con sombra",
                Modifier.padding(16.dp))
        }

        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Text("OutlinedCard - solo borde",
                Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_Paso02_Preview() {
    MaterialTheme { CP_Paso02_CardScreen() }
}
