#!/bin/bash

# Script para ejecutar pruebas con diferentes configuraciones

function print_usage {
    echo "Uso: ./run.sh [opciones]"
    echo ""
    echo "Opciones:"
    echo "  -b, --browser <browser>    Especifica el navegador (chromium, firefox, webkit, edge)"
    echo "  -h, --headless             Ejecutar en modo headless"
    echo "  -t, --tags <tags>          Ejecutar pruebas con tags específicos"
    echo "  --help                     Mostrar esta ayuda"
    echo ""
    echo "Ejemplos:"
    echo "  ./run.sh                           # Ejecutar todas las pruebas"
    echo "  ./run.sh -b firefox                # Ejecutar con Firefox"
    echo "  ./run.sh -b chrome -h              # Ejecutar con Chrome en modo headless"
    echo "  ./run.sh -t @smoke                 # Ejecutar solo pruebas con tag @smoke"
}

# Variables por defecto
BROWSER=""
HEADLESS=""
TAGS=""

# Parsear argumentos
while [[ $# -gt 0 ]]; do
    case $1 in
        -b|--browser)
            BROWSER="-Dbrowser=$2"
            shift 2
            ;;
        -h|--headless)
            HEADLESS="-Dheadless=true"
            shift
            ;;
        -t|--tags)
            TAGS="-Dcucumber.filter.tags=$2"
            shift 2
            ;;
        --help)
            print_usage
            exit 0
            ;;
        *)
            echo "Opción desconocida: $1"
            print_usage
            exit 1
            ;;
    esac
done

echo "=========================================="
echo "Ejecutando Pruebas de Automatización"
echo "=========================================="
echo ""

# Ejecutar pruebas
mvn clean test $BROWSER $HEADLESS $TAGS

# Verificar resultado
if [ $? -eq 0 ]; then
    echo ""
    echo "=========================================="
    echo "✅ Pruebas completadas"
    echo "=========================================="
    echo ""
    echo "Reportes generados en:"
    echo "  - Extent Report: test-output/extent-reports/ExtentReport.html"
    echo "  - Cucumber HTML: test-output/cucumber-reports/cucumber.html"
    echo ""
else
    echo ""
    echo "=========================================="
    echo "❌ Las pruebas fallaron"
    echo "=========================================="
    echo ""
    echo "Revisa los logs y reportes para más detalles."
    exit 1
fi
