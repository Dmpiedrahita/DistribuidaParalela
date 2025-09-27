┌─────────────────────────┐
│        Cliente           │
├─────────────────────────┤
│ - id : Integer          │
│ - cedula : String       │
│ - nombres : String      │
│ - apellidos : String    │
│ - telefono : String     │
│ - direccion : String    │
└───────────┬─────────────┘
│ 1
│
│ *
┌───────────▼─────────────┐
│        Porcino           │
├─────────────────────────┤
│ - id : Integer          │
│ - identificacion : Str  │
│ - raza : String         │
│ - edad : Integer        │
│ - peso : Double         │
│ - clienteId : Integer   │
│ - alimentacionId : Int  │
└───────────┬─────────────┘
│ 1
│
│ *
┌───────────▼─────────────┐
│      Alimentacion        │
├─────────────────────────┤
│ - id : Integer          │
│ - descripcion : String  │
│ - dosis : String        │
└─────────────────────────┘

┌─────────────────────────┐
│        Reporte          │   
├─────────────────────────┤
│ - nombres : String      │
│ - apellidos : String    │
│ - cedula : String       │
│ - porcinos : List<Porcino> │
└─────────────────────────┘

┌───────────────────────────────┐
│  <<interface>>                │
│  ClienteServiceInterface      │
├───────────────────────────────┤
│ + listarClientesConPorcinos() │
└───────────────────────────────┘
▲
│ implements
┌────────────┴───────────────┐
│       ClienteService       │
├────────────────────────────┤
│ - clienteRepository        │
├────────────────────────────┤
│ + listarClientesConPorcinos()│
│ + listar()                 │
└────────────────────────────┘

┌───────────────────────────────┐
│       ClienteRepository       │
├───────────────────────────────┤
│ + reporte() : List<Reporte>   │
└───────────────────────────────┘

┌───────────────────────────────┐
│       ReporteController       │
├───────────────────────────────┤
│ - clienteService : ClienteServiceInterface │
├───────────────────────────────┤
│ + reporteClientesPorcinos()   │
└───────────────────────────────┘
