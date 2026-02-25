# language: es
Característica: Funcionalidad de la Página Principal
  Como usuario autenticado
  Quiero interactuar con la página principal
  Para acceder a las funcionalidades del sistema

  Antecedentes:
    Dado el usuario navega a la página de login
    Y el usuario intenta iniciar sesión con "usuario_valido" y "password123"
    Y el usuario está en la página principal

  Escenario: Verificar mensaje de bienvenida
    Entonces el usuario debería ver el mensaje de bienvenida
    Y el mensaje de bienvenida debería contener "Bienvenido"

  Escenario: Buscar información en la página principal
    Cuando el usuario busca "Playwright"
    Entonces el usuario debería ver un mensaje de error

  Escenario: Verificar elementos del menú
    Entonces el usuario debería ver 5 elementos en el menú

  Escenario: Cerrar sesión
    Cuando el usuario hace clic en su perfil
    Y el usuario cierra sesión
    Entonces el usuario debería ver un mensaje de error
