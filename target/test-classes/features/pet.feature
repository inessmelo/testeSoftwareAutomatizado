#language:pt
@pet
Funcionalidade: Cadastro de Pet
  Realizar testes no cadastro de pet

  @StatusPet
  Cenario: validar status code
    Dado que tenho um payload valido do pet
    Quando realizo uma requisicao de post do pet
    Entao valido o status code 200

  @ValidacaoPet
  Cenario: validar campo
    Dado que tenho um payload valido do pet
    Quando realizo uma requisicao de post do pet
    Entao valido o campo "name" com o valor "dog"
    E valido o campo "category.name" com o valor "poodle"
    E guardo o id gerado do pet

  @EsquemaCenario
  Esquema do Cenario: <Cenario>
    Dado que tenho um payload valido do pet
    Quando altero o valor do nome do pet para "<pet>"
    Quando realizo uma requisicao de post do pet
    Entao valido o campo "name" com o valor "<pet>"

    Exemplos: 
      | Cenario              | pet     |
      | Cadastro Pet Fiona   | Fiona   |
      | Cadastro Pet Flora   | Flora   |
      | Cadastro Pet Pandora | Pandora |

  @CrudPet
  Cenario: Crud Pet
    Dado que tenho um payload valido do pet
    Quando altero o valor do nome do pet
    E altero o valor do nome da categoria do pet
    E altero o valor do nome da tags do pet
    E realizo uma requisicao de post do pet
    Entao guardo o id gerado do pet
    Quando realizo uma requisicao de get do pet utilizando o id
    Entao valido o campo "category.name" com o valor "labrador"
    E valido o campo "name" com o valor "kinha"
    E valido o campo "tags.name" com o valor "lucas"
    Quando altero o valor da tags do pet para "everton"
    E altero o valor do id do pet para armazenamento
    E realizo uma requisicao de put do pet
