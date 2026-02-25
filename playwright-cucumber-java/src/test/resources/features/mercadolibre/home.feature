#languaje: en
@mercadolibre
  Feature: ingresar a la pagina principal

    Scenario Outline: acceder correctamente a la pagina adecuada
      Given que el usuario accede a la pagina de mercadolibre
      When realiza la busqueda de un producto "<producto>"
      Then se muestra resultado del producto buscado


    Examples:
    | producto        |
    | red dragon horus |