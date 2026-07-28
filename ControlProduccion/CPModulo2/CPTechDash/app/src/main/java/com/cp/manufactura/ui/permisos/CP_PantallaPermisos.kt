package com.cp.manufactura.ui.permisos

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cp.manufactura.utils.CP_PermisosHelper

data class CP_ConfigPermiso(
    val permiso:      String,
    val nombre:       String,
    val descripcion:  String,
    val icono:        androidx.compose.ui.graphics.vector.ImageVector,
    val obligatorio:  Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CP_PantallaPermisos(onTodosConcedidos: () -> Unit = {}) {
    val context = LocalContext.current

    val configuraciones = remember {
        listOf(
            CP_ConfigPermiso(
                android.Manifest.permission.CAMERA,
                "Camara de Monitoreo",
                "Necesaria para supervisar maquinaria y detectar fallas.",
                Icons.Default.CameraAlt,
                obligatorio = true
            ),
            CP_ConfigPermiso(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                "Ubicacion de Planta",
                "Para localizar maquinarias en el plano de la planta.",
                Icons.Default.LocationOn,
                obligatorio = true
            ),
            CP_ConfigPermiso(
                android.Manifest.permission.RECORD_AUDIO,
                "Micfono de Planta",
                "Para grabar notificaciones de voz en planta.",
                Icons.Default.Mic,
                obligatorio = false
            ),
            CP_ConfigPermiso(
                android.Manifest.permission.READ_MEDIA_IMAGES,
                "Fotos de Maquinaria",
                "Para adjuntar imagenes de equipos desde la galeria.",
                Icons.Default.Photo,
                obligatorio = false
            )
        )
    }

    var estadoPermisos by remember {
        mutableStateOf(
            configuraciones.associate { cfg ->
                cfg.permiso to CP_PermisosHelper.tienePermiso(context, cfg.permiso)
            }
        )
    }

    val lanzador = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { resultados ->
        estadoPermisos = estadoPermisos + resultados
    }

    val obligatoriosConcedidos = configuraciones
        .filter { it.obligatorio }
        .all { estadoPermisos[it.permiso] == true }

    LaunchedEffect(obligatoriosConcedidos) {
        if (obligatoriosConcedidos) onTodosConcedidos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Permisos del Sistema de Produccion", fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier            = Modifier.weight(1f),
                contentPadding      = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Text(
                        "El sistema de control de produccion necesita los siguientes permisos.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(configuraciones) { cfg ->
                    val concedido = estadoPermisos[cfg.permiso] == true

                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (concedido)
                                            MaterialTheme.colorScheme.primaryContainer
                                        else
                                            MaterialTheme.colorScheme.surfaceVariant
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    cfg.icono,
                                    contentDescription = null,
                                    tint = if (concedido)
                                        MaterialTheme.colorScheme.onPrimaryContainer
                                    else
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(cfg.nombre, fontWeight = FontWeight.SemiBold)
                                    if (cfg.obligatorio) {
                                        Spacer(Modifier.width(4.dp))
                                        Text(
                                            "*",
                                            color = MaterialTheme.colorScheme.error,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Text(
                                    cfg.descripcion,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(Modifier.width(8.dp))

                            if (concedido) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = "Concedido",
                                    tint     = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else {
                                OutlinedButton(
                                    onClick = {
                                        lanzador.launch(arrayOf(cfg.permiso))
                                    },
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                                ) {
                                    Text("Permitir", style = MaterialTheme.typography.labelSmall)
                                }
                            }
                        }
                    }
                }
            }

            Surface(
                tonalElevation = 3.dp,
                modifier       = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "* Permisos obligatorios",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(Modifier.height(8.dp))

                    val pendientes = configuraciones.filter {
                        estadoPermisos[it.permiso] != true
                    }

                    AnimatedVisibility(visible = pendientes.isNotEmpty()) {
                        Button(
                            onClick  = {
                                lanzador.launch(
                                    pendientes.map { it.permiso }.toTypedArray()
                                )
                            },
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        ) {
                            Text("Conceder ${pendientes.size} permiso(s) pendiente(s)")
                        }
                    }

                    AnimatedVisibility(visible = obligatoriosConcedidos) {
                        Button(
                            onClick  = onTodosConcedidos,
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                        ) {
                            Text("Continuar al Panel de Control de Produccion")
                        }
                    }
                }
            }
        }
    }
}
