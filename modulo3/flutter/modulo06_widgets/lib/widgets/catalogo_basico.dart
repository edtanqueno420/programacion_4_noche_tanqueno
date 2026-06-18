import 'package:flutter/material.dart';

class CatalogoBasicos extends StatelessWidget {
  const CatalogoBasicos({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Widgets básicos')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          // ── Text básico ───────────────────────────────────────────────────────
          const Text(
            'nginx-proxy: En línea',
            style: TextStyle(
              shadows: [
                Shadow(
                  color: Colors.black26,
                  blurRadius: 4,
                  offset: Offset(2, 2),
                ),
              ],
              fontSize: 20,
              fontWeight: FontWeight.bold, // .w100–.w900  ·  .bold = .w700
              color: Colors.green,
              letterSpacing: 0.5,
              fontStyle: FontStyle.normal, // .italic
              decoration: TextDecoration.none,
              //             .underline  .lineThrough  .overline
            ),
          ),
          const SizedBox(height: 8),

          // ── Alineación y desbordamiento ───────────────────────────────────────
          SizedBox(
            width: double.infinity,
            child: Text(
              'api-gateway-produccion-region-us-east → sin respuesta',
              textAlign:
                  TextAlign.justify, // .left  .right  .justify  .start  .end
              softWrap: false,
              maxLines: 2,
              overflow: TextOverflow.fade, // .clip  .fade  .visible
              style: TextStyle(
                decoration: TextDecoration.combine([
                  TextDecoration.underline,
                  TextDecoration.lineThrough,
                ]),
              ),
            ),
          ),
          const SizedBox(height: 8),

          // ── Text.rich — estilos distintos en un solo widget ───────────────────
          const Text.rich(
            TextSpan(
              children: [
                TextSpan(
                  text: 'Estado: ',
                  style: TextStyle(fontWeight: FontWeight.w600),
                ),
                TextSpan(
                  text: 'CRÍTICO',
                  style: TextStyle(
                    color: Colors.red,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                TextSpan(
                  text: ' — última revisión hace 5 min',
                  style: TextStyle(color: Colors.grey, fontSize: 12),
                ),
              ],
            ),
          ),
          const SizedBox(height: 8),

          // ── SelectableText — el usuario puede seleccionar y copiar ───────────
          const SelectableText(
            '10.0.0.12:5432',
            style: TextStyle(fontFamily: 'monospace', fontSize: 14),
          ),
          const Divider(height: 32),

          // Agrega a children: [ ... ]
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Icon(
                Icons.check_circle_outline,
                size: 80,
                color: Theme.of(context).colorScheme.primary,
              ),
              Icon(Icons.cancel, size: 80, color: Colors.red),
              Icon(Icons.warning_amber, size: 80, color: Colors.orange),
              Icon(Icons.dns, size: 80, color: Colors.indigo),
              Icon(Icons.wifi_off, size: 80, color: Colors.grey),
            ],
          ),
          const SizedBox(height: 8),
          Tooltip(
            message: 'Servidor activo',
            child: Icon(
              Icons.check_circle,
              size: 24,
              color: Colors.blueGrey,
              semanticLabel: 'Configuración',
            ),
          ),
          const Divider(height: 32),
          // Agrega a children: [ ... ]

          // ── Cuatro variantes ──────────────────────────────────────────────────
          Wrap(
            spacing: 8,
            runSpacing: 8,
            children: [
              ElevatedButton(
                onPressed: () {},
                child: const Text('ElevatedButton'),
              ),
              FilledButton(onPressed: () {}, child: const Text('FilledButton')),
              OutlinedButton(
                onPressed: () {},
                child: const Text('OutlinedButton'),
              ),
              TextButton(onPressed: () {}, child: const Text('TextButton')),
              ElevatedButton(onPressed: () {}, child: const Text('Desactivado')),
              //             ↑ onPressed: () {} → botón activo
            ],
          ),
          const SizedBox(height: 12),

          // ── Variantes .icon ───────────────────────────────────────────────────
          Wrap(
            spacing: 8,
            runSpacing: 8,
            children: [
              ElevatedButton.icon(
                onPressed: () {},
                icon: const Icon(Icons.refresh, size: 18),
                label: const Text('Reiniciar'),
              ),
              FilledButton.icon(
                onPressed: () {},
                icon: const Icon(Icons.stop, size: 18),
                label: const Text('Detener'),
              ),
              TextButton.icon(
                onPressed: () {},
                icon: const Icon(Icons.info, size: 18),
                label: const Text('Info'),
              ),
              OutlinedButton.icon(
                onPressed: () {},
                icon: const Icon(Icons.download, size: 18),
                label: const Text('Descargar'),
              ),
              Tooltip(
                message: 'Detiene todos los servicios',
                child: IconButton(
                  onPressed: () {},
                  icon: const Icon(Icons.settings),
                  color: Colors.indigo,
                  iconSize: 28,
                ),
              ),
            ],
          ),
          const SizedBox(height: 12),

          // ── Botón con estilo personalizado ────────────────────────────────────
          ElevatedButton(
            onPressed: () {},
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.red.shade600,
              foregroundColor: Colors.white,
              padding: const EdgeInsets.symmetric(horizontal: 32, vertical: 14),
              shape: const StadiumBorder(),
              elevation: 0,
            ),
            child: const Text(
              'Acción crítica (plana)',
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
          ),
          const SizedBox(height: 12),
          ElevatedButton(
            onPressed: () {},
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.red.shade600,
              foregroundColor: Colors.white,
              padding: const EdgeInsets.symmetric(horizontal: 32, vertical: 14),
              shape: const StadiumBorder(),
              elevation: 12,
            ),
            child: const Text(
              'Acción crítica (muy elevada)',
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
          ),
          const Divider(height: 32),

          // Agrega a children: [ ... ]

Card(
  elevation: 12,
  margin: const EdgeInsets.only(bottom: 8),
  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
  child: ListTile(
    isThreeLine: true,
    contentPadding: const EdgeInsets.symmetric(horizontal: 20, vertical: 12),
    leading:  const Icon(Icons.dns, color: Colors.indigo),
    title:    const Text('nginx-proxy'),
    subtitle: const Text('10.0.0.5 · 45ms'),
    trailing: const Icon(Icons.circle, color: Colors.green, size: 12),
    onTap:    () {},           // toda la fila queda tocable
  ),
),
Card(
  elevation: 1,
  child: ListTile(
    leading: CircleAvatar(
      backgroundColor: Colors.red.shade50,
      child: const Icon(Icons.cancel, color: Colors.red, size: 20),
    ),
    title:    const Text('backup-worker'),
    subtitle: const Text('¿La muerte de presunto cabecilla de Los Águilas coincide con la reconfiguración de los Choneros sin Fito ni Javi?'),
    trailing: TextButton(onPressed: () {}, child: const Text('Ver')),
  ),
),
Card(child: SwitchListTile(value: false, onChanged: (_){}, title: const Text('Modo mantenimiento'))),
const Divider(height: 32),

// Agrega a children: [ ... ]

Wrap(
  spacing: 8, runSpacing: 8,
  children: [
    const Chip(label: Text('nginx')),
    const Chip(
      avatar:          Icon(Icons.check, size: 16, color: Colors.white),
      label:           Text('TLS 1.3'),
      backgroundColor: Colors.green,
      labelStyle:      TextStyle(color: Colors.white, fontSize: 12),
    ),
    FilterChip(
      label:      const Text('HTTP/2'),
      selected:   true,
      onSelected: (_) {},
    ),
    ActionChip(
      label:     const Text('Ver logs'),
      avatar:    const Icon(Icons.open_in_new, size: 16),
      onPressed: () {},
    ),
  ],
),
const Divider(height: 32),

// Agrega a children: [ ... ]

// ── Circular ──────────────────────────────────────────────────────────
Row(
  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
  children: const [
    SizedBox(width: 48, height: 48,
      child: CircularProgressIndicator()),           // value: null → animación continua
    SizedBox(width: 48, height: 48,
      child: CircularProgressIndicator(
        value:       0.7,           // 70 %
        color:       Colors.green,
        strokeWidth: 6,
      )),
    SizedBox(width: 48, height: 48,
      child: CircularProgressIndicator(
        value:       0.3,
        color:       Colors.red,
        strokeWidth: 3,
        strokeCap:   StrokeCap.round,   // puntas redondeadas
      )),
  ],
),
const SizedBox(height: 16),

// ── Lineal ────────────────────────────────────────────────────────────
const LinearProgressIndicator(),                                  // indeterminado
const SizedBox(height: 8),
const LinearProgressIndicator(value: 0.6, color: Colors.indigo), // 60 %
const SizedBox(height: 8),
const LinearProgressIndicator(
  value:     1.0,
  color:     Colors.green,
  minHeight: 6,                     // barra más gruesa (default: 4)
),
const Divider(height: 32),
        ],
      ),
    );
  }
}
