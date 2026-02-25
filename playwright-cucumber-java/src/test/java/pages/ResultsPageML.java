package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.*;

public class ResultsPageML {
    private final Page page;

    public ResultsPageML(Page page) {
        this.page = page;
    }

    public void seleccionarProductoMasBarato() {
        // Lista para guardar: Precio, Locator de la tarjeta, y un String con su ubicación
        List<Map.Entry<Integer, Map.Entry<Locator, String>>> listaCompleta = new ArrayList<>();
        boolean hayMasPaginas = true;
        int contadorPagina = 1;

        while (hayMasPaginas) {
            System.out.println("\n--- ESCANEANDO PÁGINA " + contadorPagina + " ---");

            for (int i = 0; i < 3; i++) {
                page.mouse().wheel(0, 1000);
                page.waitForTimeout(400);
            }

            Locator bloquesPrecio = page.locator(".poly-price__current");
            int totalEnPagina = bloquesPrecio.count();

            for (int i = 0; i < totalEnPagina; i++) {
                try {
                    Locator bloque = bloquesPrecio.nth(i);
                    boolean esSecundario = (boolean) bloque.evaluate(
                            "el => el.closest('.poly-buy-box__alternative-option') !== null"
                    );

                    if (!esSecundario) {
                        String texto = bloque.locator(".andes-money-amount__fraction").first().innerText();
                        int valor = Integer.parseInt(texto.replaceAll("[^0-9]", ""));

                        // Guardamos la ubicación: "Producto #X en la Página Y"
                        String ubicacion = "Producto #" + (i + 1) + " en la Página " + contadorPagina;
                        System.out.println(ubicacion + " | Precio: S/ " + valor);

                        Locator tarjeta = bloque.locator("xpath=ancestor::div[contains(@class, 'poly-card')]").first();

                        // Guardamos todo en una estructura anidada
                        Map.Entry<Locator, String> datosProducto = new AbstractMap.SimpleEntry<>(tarjeta, ubicacion);
                        listaCompleta.add(new AbstractMap.SimpleEntry<>(valor, datosProducto));
                    }
                } catch (Exception e) { continue; }
            }

            Locator botonSiguiente = page.locator("a.andes-pagination__link[title='Siguiente']");
            if (botonSiguiente.isVisible() && botonSiguiente.isEnabled()) {
                botonSiguiente.click();
                page.waitForLoadState();
                contadorPagina++;
            } else {
                hayMasPaginas = false;
            }
        }

        // Ordenamos por precio
        listaCompleta.sort(Map.Entry.comparingByKey());

        if (!listaCompleta.isEmpty()) {
            Map.Entry<Integer, Map.Entry<Locator, String>> ganador = listaCompleta.get(0);

            int precioFinal = ganador.getKey();
            String detalleUbicacion = ganador.getValue().getValue();
            Locator tarjetaGanadora = ganador.getValue().getKey();

            System.out.println("\n*****************************************************");
            System.out.println("RESULTADO FINAL:");
            System.out.println("El más barato es de S/ " + precioFinal);
            System.out.println("Ubicación: " + detalleUbicacion); // Aquí imprime "Producto #8 en la Página 2"
            System.out.println("*****************************************************");

            // Acción final
            tarjetaGanadora.scrollIntoViewIfNeeded();
            tarjetaGanadora.locator(".poly-component__title").first().click();
        }
    }

    public void manejarPopUpUbicacion() {
        try {
            Locator botonMasTarde = page.locator("button[data-js='onboarding-cp-close']");
            botonMasTarde.waitFor(new Locator.WaitForOptions().setTimeout(3000));
            if (botonMasTarde.isVisible()) {
                botonMasTarde.click();
            }
        } catch (Exception e) {}
    }
}