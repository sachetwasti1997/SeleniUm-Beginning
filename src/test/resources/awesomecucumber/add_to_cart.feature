Feature: dummy feature for adding items to cart
  Scenario Outline: dummy scenario socasing parameters
    Given I'm on the Store page
    When I add a "<product_name>" in the cart
    Then I see 1 "<product_name>" in the cart

    Examples:
    | product_name |
    | Blue Shoes   |
#    Selenium is the tool used to automate the browsers.
#  It has three main components WebDriver, DriverExecutable, and the Browser
#  WebDriver consists of Bindings and support classes
#