@file:OptIn(ExperimentalMaterial3Api::class)

package com.cp.manufactura.material

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cp.manufactura.model.Maquina
import com.cp.manufactura.model.maquinasDeMuestra

@Composable
fun Paso06_DialogosScreen() {
    var maquinas        by remember { mutableStateOf(maquinasDeMuestra) }
    var busqueda         by remember { mutableStateOf("") }
    var filtro           by remember { mutableStateOf("Todas") }
    var destinoActual    by remember { mutableStateOf("maquinas") }

    var mostrarNuevo     by remember { mutableStateOf(false) }
    var maquinaAEliminar by remember { mutableStateOf<Maquina?>(null) }

    var mensajeSnack     by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(mensajeSnack) {
        mensajeSnack?.let {
            snackbarHostState.showSnackbar(it)
            mensajeSnack = null
        }
    }

    val maquinasFiltradas = maquinas
        .filter { c -> if (filtro == "Operativas") c.operativa else true }
        .filter { c -> busqueda.isBlank() || c.nombre.contains(busqueda, ignoreCase = true) }

    val destinos = listOf(
        DestinoNav("maquinas", "Máquinas", Icons.Filled.PrecisionManufacturing,       Icons.Outlined.PrecisionManufacturing),
        DestinoNav("operativas", "Operativas", Icons.Filled.CheckCircle,     Icons.Outlined.CheckCircle),
        DestinoNav("mantenimiento", "Mantenimiento", Icons.Filled.Build, Icons.Outlined.Build),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Máquinas (${maquinas.size})", fontWeight = FontWeight.Bold)
                },
                actions = {
                    IconButton(onClick = {
                        filtro = if (filtro == "Operativas") "Todas" else "Operativas"
                    }) {
                        Icon(
                            imageVector = if (filtro == "Operativas")
                                Icons.Default.CheckCircle else Icons.Default.Circle,
                            contentDescription = "Filtrar operativas",
                            tint = if (filtro == "Operativas")
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor    = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                destinos.forEach { destino ->
                    val sel = destinoActual == destino.ruta
                    NavigationBarItem(
                        selected = sel,
                        onClick  = { destinoActual = destino.ruta },
                        icon     = {
                            Icon(if (sel) destino.iconoActivo else destino.iconoInactivo,
                                destino.etiqueta)
                        },
                        label = { Text(destino.etiqueta) }
                    )
                }
            }
        },
        floatingActionButton = {
            if (destinoActual == "maquinas") {
                FloatingActionButton(onClick = { mostrarNuevo = true }) {
                    Icon(Icons.Default.Add, "Nueva máquina")
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }

    ) { paddingValues ->
        when (destinoActual) {
            "maquinas" -> ContenidoMaquinas(
                maquinas    = maquinasFiltradas,
                busqueda     = busqueda,
                filtro       = filtro,
                onBusqueda   = { busqueda = it },
                onFiltro     = { filtro = it },
                onActivar   = { id ->
                    maquinas = maquinas.map { c ->
                        if (c.id == id) c.copy(operativa = !c.operativa) else c
                    }
                },
                onMantenimiento = { nombre -> mensajeSnack = "🔧 Mantenimiento solicitado para $nombre..." },
                onEliminar   = { maquina -> maquinaAEliminar = maquina },
                modifier     = Modifier.padding(paddingValues)
            )
            "operativas" -> PantallaOperativasContent(
                operativas = maquinas.filter { it.operativa },
                modifier  = Modifier.padding(paddingValues)
            )
            "mantenimiento"    -> PantallaMantenimientoContent(
                modifier  = Modifier.padding(paddingValues)
            )
        }
    }

    if (mostrarNuevo) {
        DialogNuevaMaquina(
            onDismiss = { mostrarNuevo = false },
            onGuardar = { nuevo ->
                maquinas    = maquinas + nuevo
                mostrarNuevo = false
                mensajeSnack = "✅ ${nuevo.nombre} registrada"
            }
        )
    }

    maquinaAEliminar?.let { maquina ->
        AlertDialog(
            onDismissRequest = { maquinaAEliminar = null },
            icon    = {
                Icon(Icons.Default.Warning, null,
                    tint = MaterialTheme.colorScheme.error)
            },
            title   = { Text("Dar de baja máquina") },
            text    = {
                Text("¿Seguro que quieres dar de baja ${maquina.nombre}? " +
                        "Esta acción no se puede deshacer.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        maquinas         = maquinas.filter { it.id != maquina.id }
                        mensajeSnack      = "🗑 ${maquina.nombre} dada de baja"
                        maquinaAEliminar = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) { Text("Dar de baja") }
            },
            dismissButton = {
                OutlinedButton(onClick = { maquinaAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun ContenidoMaquinas(
    maquinas:  List<Maquina>,
    busqueda:   String,
    filtro:     String,
    onBusqueda: (String) -> Unit,
    onFiltro:   (String) -> Unit,
    onActivar: (Int) -> Unit,
    onMantenimiento:   (String) -> Unit,
    onEliminar: (Maquina) -> Unit,
    modifier:   Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value         = busqueda,
            onValueChange = onBusqueda,
            placeholder   = { Text("Buscar máquina...") },
            leadingIcon   = { Icon(Icons.Default.Search, null) },
            trailingIcon  = {
                if (busqueda.isNotEmpty())
                    IconButton(onClick = { onBusqueda("") }) {
                        Icon(Icons.Default.Clear, "Limpiar")
                    }
            },
            singleLine = true,
            modifier   = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding        = PaddingValues(horizontal = 16.dp)
        ) {
            items(listOf("Todas", "Operativas")) { opcion ->
                FilterChip(
                    selected    = filtro == opcion,
                    onClick     = { onFiltro(opcion) },
                    label       = { Text(opcion) },
                    leadingIcon = if (filtro == opcion) {{
                        Icon(Icons.Default.Check, null,
                            Modifier.size(FilterChipDefaults.IconSize))
                    }} else null
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        if (maquinas.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.SearchOff, null, Modifier.size(56.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(8.dp))
                    Text("Sin resultados",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        } else {
            LazyColumn(
                contentPadding      = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text("${maquinas.size} máquina(s)",
                        style    = MaterialTheme.typography.labelSmall,
                        color    = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 4.dp))
                }
                items(maquinas, key = { it.id }) { maquina ->
                    TarjetaMaquinaCompleta(
                        maquina   = maquina,
                        onActivar = { onActivar(maquina.id) },
                        onMantenimiento   = { onMantenimiento(maquina.nombre) },
                        onEliminar = { onEliminar(maquina) }
                    )
                }
                item { Spacer(Modifier.height(100.dp)) }
            }
        }
    }
}

@Composable
private fun TarjetaMaquinaCompleta(
    maquina:  Maquina,
    onActivar: () -> Unit,
    onMantenimiento:  () -> Unit,
    onEliminar: () -> Unit
) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier          = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(
                        if (maquina.operativa) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.errorContainer
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    maquina.nombre.first().uppercase(),
                    style      = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color      = if (maquina.operativa) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onErrorContainer
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(maquina.nombre, fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleSmall)
                Text(maquina.tipo,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(maquina.ubicacion,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = onActivar) {
                Icon(
                    if (maquina.operativa) Icons.Default.CheckCircle else Icons.Default.Cancel,
                    null,
                    tint = if (maquina.operativa) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.error
                )
            }
            IconButton(onClick = onMantenimiento) {
                Icon(Icons.Default.Build, null,
                    tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = onEliminar) {
                Icon(Icons.Default.Delete, null,
                    tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
private fun DialogNuevaMaquina(
    onDismiss: () -> Unit,
    onGuardar: (Maquina) -> Unit
) {
    var nombre   by remember { mutableStateOf("") }
    var tipo    by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }

    val nombreValido   = nombre.trim().length >= 2
    val tipoValido    = tipo.trim().length >= 2
    val ubicacionValido = ubicacion.trim().length >= 2
    val valido         = nombreValido && tipoValido && ubicacionValido

    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier            = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Nueva máquina",
                    style      = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold)

                OutlinedTextField(
                    value           = nombre,
                    onValueChange   = { nombre = it },
                    label           = { Text("Nombre") },
                    leadingIcon     = { Icon(Icons.Default.PrecisionManufacturing, null) },
                    isError         = nombre.isNotEmpty() && !nombreValido,
                    singleLine      = true,
                    modifier        = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                OutlinedTextField(
                    value           = tipo,
                    onValueChange   = { tipo = it },
                    label           = { Text("Tipo") },
                    leadingIcon     = { Icon(Icons.Default.Settings, null) },
                    isError         = tipo.isNotEmpty() && !tipoValido,
                    singleLine      = true,
                    modifier        = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction    = ImeAction.Next
                    )
                )

                OutlinedTextField(
                    value           = ubicacion,
                    onValueChange   = { ubicacion = it },
                    label           = { Text("Ubicación") },
                    leadingIcon     = { Icon(Icons.Default.LocationOn, null) },
                    isError         = ubicacion.isNotEmpty() && !ubicacionValido,
                    singleLine      = true,
                    modifier        = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction    = ImeAction.Done
                    )
                )

                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onDismiss) { Text("Cancelar") }
                    Spacer(Modifier.width(8.dp))
                    Button(
                        onClick  = {
                            onGuardar(
                                Maquina(
                                    id       = System.currentTimeMillis().toInt(),
                                    nombre   = nombre.trim(),
                                    tipo = tipo.trim(),
                                    ubicacion = ubicacion.trim()
                                )
                            )
                        },
                        enabled  = valido
                    ) { Text("Guardar") }
                }
            }
        }
    }
}

@Composable
fun PantallaOperativasContent(
    operativas: List<Maquina>,
    modifier:  Modifier = Modifier
) {
    if (operativas.isEmpty()) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.CheckCircle, null,
                    Modifier.size(56.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(12.dp))
                Text("Sin máquinas operativas",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Activa una desde la lista",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    } else {
        LazyColumn(
            modifier            = modifier,
            contentPadding      = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(operativas, key = { it.id }) { maquina ->
                TarjetaMaquina(maquina = maquina)
            }
        }
    }
}

@Composable
fun PantallaMantenimientoContent(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Build, null, Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(12.dp))
            Text("Mantenimiento", style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
            Text("Próximamente...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Paso06_Preview() {
    MaterialTheme { Paso06_DialogosScreen() }
}
