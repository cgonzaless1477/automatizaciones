# 1. Imagen oficial con Java y dependencias de sistema
FROM mcr.microsoft.com/playwright/java:v1.40.0-jammy

WORKDIR /app

# 2. Cache de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -q

# 3. Instalación de Chromium
RUN mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"

# 4. Copiar todo el código fuente (incluye src/test/resources)
COPY src ./src

# 5. Ejecución con override de propiedades
# Añadimos -Dheadless=true directamente al comando para que Maven lo priorice
# Forzamos la propiedad directamente en el comando
CMD mvn test -Dheadless=true -DforkCount=0 \
    -Dcucumber.filter.tags="${CUCUMBER_TAGS}" \
    -Dtest.user="${TEST_USER}" \
    -Dtest.pass="${TEST_PASS}" \
    -Dtest.env="${TEST_ENV}"