# Proyecto de Automatización - Playwright + Cucumber + Java

Proyecto de automatización de pruebas utilizando Playwright con Cucumber y Java 17.

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Maven** - Gestión de dependencias
- **Playwright** - Framework de automatización
- **Cucumber** - BDD Framework
- **JUnit 5** - Framework de testing
- **Extent Reports** - Reportes de pruebas

## 📋 Prerequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Java JDK 17 o superior
- Maven 3.6 o superior
- Navegadores: Chrome, Firefox, Edge, Safari (según configuración)

## 🚀 Instalación

1. Clona el repositorio:
```bash
git clone <tu-repositorio>
cd playwright-cucumber-java
```

2. Instala las dependencias de Maven:
```bash
mvn clean install
```

3. Instala los navegadores de Playwright:
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

## 📁 Estructura del Proyecto

```
playwright-cucumber-java/
├── src/
│   ├── main/java/
│   │   ├── config/          # Configuraciones del proyecto
│   │   ├── pages/           # Page Object Models
│   │   └── utils/           # Utilidades y helpers
│   └── test/
│       ├── java/
│       │   ├── hooks/       # Hooks de Cucumber (Before/After)
│       │   ├── runners/     # Test Runners
│       │   └── steps/       # Step Definitions
│       └── resources/
│           ├── features/    # Archivos .feature (Gherkin)
│           ├── extent.properties
│           └── extent-config.xml
├── config.properties        # Configuración global
└── pom.xml
```

## ⚙️ Configuración

### config.properties

Modifica el archivo `config.properties` para ajustar la configuración:

```properties
# Navegador: chromium, firefox, webkit, edge
browser=chromium

# Modo headless
headless=false

# URL base de la aplicación
base.url=https://www.example.com

# Timeout en milisegundos
timeout=30000

# Screenshots en fallos
screenshot.on.failure=true
```

## 🏃 Ejecución de Pruebas

### Ejecutar todas las pruebas:
```bash
mvn test
```

### Ejecutar con un navegador específico:
```bash
mvn test -Dbrowser=firefox
```

### Ejecutar en modo headless:
```bash
mvn test -Dheadless=true
```

### Ejecutar pruebas específicas por tags:
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

## 📊 Reportes

Después de ejecutar las pruebas, los reportes se generan en:

- **Extent Report**: `test-output/extent-reports/ExtentReport.html`
- **Cucumber HTML**: `test-output/cucumber-reports/cucumber.html`
- **Cucumber JSON**: `test-output/cucumber-reports/cucumber.json`
- **Screenshots**: `test-output/screenshots/`

## 📝 Escribir Nuevas Pruebas

### 1. Crear un Feature File

Crea un archivo `.feature` en `src/test/resources/features/`:

```gherkin
# language: es
Característica: Nueva funcionalidad
  Escenario: Probar algo
    Dado el usuario está en la página
    Cuando el usuario hace algo
    Entonces el resultado debe ser correcto
```

### 2. Crear Page Object

Crea una clase en `src/main/java/pages/`:

```java
public class MiPagina extends BasePage {
    private final String miElemento = "#selector";
    
    public MiPagina(Page page) {
        super(page);
    }
    
    public void hacerAlgo() {
        click(miElemento);
    }
}
```

### 3. Crear Step Definitions

Crea una clase en `src/test/java/steps/`:

```java
public class MisSteps {
    @Dado("el usuario está en la página")
    public void elUsuarioEstaEnLaPagina() {
        // Implementación
    }
}
```

## 🌐 Navegadores Soportados

- **Chromium** (Chrome)
- **Firefox**
- **WebKit** (Safari)
- **Edge**

## 🔍 Características Principales

- ✅ Page Object Model (POM)
- ✅ BDD con Cucumber
- ✅ Soporte multi-navegador
- ✅ Screenshots automáticos en fallos
- ✅ Reportes detallados con Extent Reports
- ✅ Configuración flexible
- ✅ Hooks para setup y teardown
- ✅ Locators en español

## 🤝 Contribuir

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.

## 📧 Contacto

Para preguntas o sugerencias, contacta al equipo de automatización.

---

**Nota**: Este es un proyecto de ejemplo. Asegúrate de actualizar las URLs, selectores y configuraciones según tus necesidades específicas.
