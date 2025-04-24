Feature: Basketball England Registration

  As a new user
  I want to register on the Basketball England website
  So that I can become a supporter

  Scenario Outline: Register new user with various inputs
    Given the user is on the registration page
    When the user enters first name "<firstname>"
    And the user enters last name "<lastname>"
    And the user enters email "<email>"
    And the user enters confirm email "<confirmemail>"
    And the user enters password "<password>"
    And the user enters confirm password "<confirmpassword>"
    And the user selects date of birth "<dob>"
    And the user accepts terms "<terms>"
    And the user clicks the register button
    Then the user should see "<message>"

    Examples:
      | firstname | lastname | email                    | confirmemail           | password      | confirmpassword | dob        | terms | message                        |
      | Peter    | Muneer    | petershafo9+A@gmail.com  | petershafo9+A@gmail.com| Peter200510 | Peter200510   | 20/12/2005 | true  | Thank you for creating an account with Basketball England     |
      | Peter    |          | petershafo9@gmail.com  | petershafo9@gmail.com| Peter200510 | Peter200510   | 20/12/2005 | true  | Please enter your last name   |
      | Peter    | Muneer    | petershafo9@gmail.com  | petershafo9@gmail.com| Peter200510 | wrongpass       | 20/12/2005 | true  | Passwords must match          |
      | Peter    | Muneer    | petershafo9@gmail.com  | petershafo9@gmail.com| Peter200510 | Peter200510   | 20/12/2005 | false | You must accept the terms     |

