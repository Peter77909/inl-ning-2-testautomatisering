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
      | Peter    | Muneer    | GENERATED_EMAIL  | GENERATED_EMAIL| Peter200510 | Peter200510   | 20/12/2005 | true  | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND     |
      | Peter    |          | petershafo9@gmail.com  | petershafo9@gmail.com| Peter200510 | Peter200510   | 20/12/2005 | true  | Last Name is required   |
      | Peter    | Muneer    | petershafo9@gmail.com  | petershafo9@gmail.com| Peter200510 | wrongpass       | 20/12/2005 | true  | Password did not match          |
      | Peter    | Muneer    | petershafo9@gmail.com  | petershafo9@gmail.com| Peter200510 | Peter200510   | 20/12/2005 | false | You must confirm that you have read and accepted our Terms and Conditions     |

