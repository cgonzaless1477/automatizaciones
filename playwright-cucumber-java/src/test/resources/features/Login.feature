# language: es
@login @smoke
Característica: Funcionalidad de Login
  Como usuario del sistema
  Quiero poder iniciar sesión
  Para acceder a mi cuenta

  Antecedentes:
    Dado el usuario navega a la página de login

  Escenario: Login exitoso con credenciales válidas
    Cuando el usuario ingresa el nombre de usuario "usuario_valido"
    Y el usuario ingresa la contraseña "password123"
    Y el usuario hace clic en el botón de login
    Entonces el usuario debería ser redirigido a la página principal

  Escenario: Login fallido con credenciales inválidas
    Cuando el usuario ingresa el nombre de usuario "usuario_invalido"
    Y el usuario ingresa la contraseña "password_incorrecta"
    Y el usuario hace clic en el botón de login
    Entonces el usuario debería ver un mensaje de error

  Esquema del escenario: Login con múltiples credenciales
    Cuando el usuario intenta iniciar sesión con "<usuario>" y "<password>"
    Entonces el usuario debería ver un mensaje de error

    Ejemplos:
      | usuario        | password  |
      | user1          |           |
      |                | pass123   |
      | usuario_malo   | pass_mala |

  Escenario: Verificar título de la página de login
    Entonces el título de la página debería ser "Login Page"
