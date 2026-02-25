#!/bin/bash

echo "=========================================="
echo "Playwright + Cucumber + Java Setup"
echo "=========================================="
echo ""

# Verificar Java
echo "Verificando Java..."
if ! command -v java &> /dev/null; then
    echo "❌ Java no está instalado. Por favor instala Java 17 o superior."
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
echo "✅ Java version: $JAVA_VERSION"

if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "❌ Se requiere Java 17 o superior. Versión actual: $JAVA_VERSION"
    exit 1
fi

# Verificar Maven
echo ""
echo "Verificando Maven..."
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven no está instalado. Por favor instala Maven 3.6 o superior."
    exit 1
fi

MVN_VERSION=$(mvn -version | grep "Apache Maven" | awk '{print $3}')
echo "✅ Maven version: $MVN_VERSION"

# Limpiar e instalar dependencias
echo ""
echo "Instalando dependencias de Maven..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Dependencias instaladas correctamente"
else
    echo "❌ Error al instalar dependencias"
    exit 1
fi

# Instalar navegadores de Playwright
echo ""
echo "Instalando navegadores de Playwright..."
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"

if [ $? -eq 0 ]; then
    echo "✅ Navegadores de Playwright instalados correctamente"
else
    echo "❌ Error al instalar navegadores de Playwright"
    exit 1
fi

# Crear directorios de output si no existen
echo ""
echo "Creando directorios de output..."
mkdir -p test-output/screenshots
mkdir -p test-output/videos
mkdir -p test-output/cucumber-reports
mkdir -p test-output/extent-reports

echo "✅ Directorios creados"

echo ""
echo "=========================================="
echo "✅ Instalación completada exitosamente!"
echo "=========================================="
echo ""
echo "Para ejecutar las pruebas:"
echo "  mvn test"
echo ""
echo "Para ejecutar con un navegador específico:"
echo "  mvn test -Dbrowser=firefox"
echo ""
echo "Para ejecutar en modo headless:"
echo "  mvn test -Dheadless=true"
echo ""
