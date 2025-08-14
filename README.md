# Checkout TDD Exercise (Java)

##  Overview
This project implements a simple checkout system for a shop that only sells **Apples** and **Oranges**.  
It is developed in **Java** using **Test-Driven Development (TDD)** principles.  
Unit tests are written using **JUnit 5**.

The problem is based on a two-step requirement:

### Step 1 – Basic Shopping Cart
- Apples cost £0.60
- Oranges cost £0.25
- Calculate the total cost given a list of items scanned at the till.
- Example:  
  `[Apple, Apple, Orange, Apple] => £2.05`

### Step 2 – Offers
The shop introduces special offers:
- **Apples**: Buy One, Get One Free  
  → Every 2 apples, you only pay for 1.
- **Oranges**: 3 for the Price of 2  
  → Every 3 oranges, you only pay for 2.

Example with offers:  
`[Apple, Apple, Orange, Apple, Orange, Orange]`
- Apples: 3 → pay for 2 → £1.20
- Oranges: 3 → pay for 2 → £0.50  
  **Total** = £1.70

---

## Project Structure

ShoppingCartCheckout/
├── pom.xml
├── src
│ ├── main
│ │ └── java
│ │ └── com/shoppingcart/checkout
│ │ └── Checkout.java
│ └── test
│ └── java
│ └── com/shoppingcart/checkout
│ ├── CheckoutTest.java # Step 1 tests
│ └── CheckoutOffersTest.java # Step 2 tests



---

##  Technologies Used
- Java 17+
- Maven
- JUnit 5

---

##  How to Run Tests
1. **Clone the repository**

   git clone <your-repo-url>
   cd <repo-name>

2** Run the tests**  
mvn test

## Test-Driven Development (TDD) Approach
Wrote failing tests for Step 1 (basic cart without offers).

Implemented minimal code to make the tests pass.


Wrote failing tests for Step 2 (offers applied).

Updated implementation to apply offers.


## Notes & Assumptions
Input list may contain item names in any case (e.g., apple, APPLE).

Any items other than "Apple" or "Orange" are ignored.

Prices are hardcoded for simplicity.

No currency formatting is applied — values are returned as double.
   