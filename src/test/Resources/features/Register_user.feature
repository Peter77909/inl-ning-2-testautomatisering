Feature: Registrera användare på basketballengland.co.uk

  Scenario: Allt går som förväntat
    Given användaren är på registreringssidan
    When användaren fyller i alla fält korrekt
    Then ska ett konto skapas

  Scenario: Efternamn saknas
    Given användaren är på registreringssidan
    When användaren lämnar efternamn tomt
    Then ska ett felmeddelande visas

  Scenario: Lösenord matchar inte
    Given användaren är på registreringssidan
    When lösenorden inte matchar
    Then ska ett felmeddelande visas

  Scenario: Terms and conditions inte godkänns
    Given användaren är på registreringssidan
    When terms and conditions inte godkänns
    Then ska ett felmeddelande visas