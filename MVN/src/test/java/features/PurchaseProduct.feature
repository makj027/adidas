@Adidas
Feature: Purchase Product Feature


  Scenario: Launch Web Store
    Given User launches chrome browser
    Given User open
    |  https://www.demoblaze.com/index.html |

  Scenario Outline: Add a product to cart
    When User clicks on Home button
    When User clicks on category<CATEGORY>
    And User clicks on Laptop <LAPTOPNAME>
    Then Header should be <LAPTOPNAME>
    When User clicks on AddToCart
    Then Product Added pop-up should appear
    And User accepts pop-up confirmation
    Examples:
      |CATEGORY|LAPTOPNAME|
      | Laptops | Sony vaio i5 |
      | Laptops | Dell i7 8gb  |

  Scenario: Navigate to cart
    When User clicks cart button
    Then Products should be added in cart
    |Sony vaio i5|
    |Dell i7 8gb|

  Scenario Outline: Delete product
    When User deletes <PRODUCT>

    Examples:
      |PRODUCT|
      |Dell i7 8gb|

  Scenario Outline: Place order and capture purchase details
    When User fetch total amount
    When User clicks on place order
    And Fill form details <NAME>,<COUNTRY>,<CITY>,<CREDITCARD>,<MONTH>,<YEAR>
    And User click on Purchase button
    Then Confirmation <MESSAGE> should be displayed

    Examples:
      |NAME|COUNTRY|CITY|CREDITCARD|MONTH|YEAR|MESSAGE|
      |MAYANK JAIN|INDIA|DELHI|123456789|DEC|2020|Thank you for your purchase!    |

  Scenario: Capture purchase details
    And Capture purchase details and log
    Then Verify purchase details
    And Click on OK button





