# Account Manager Unit Tests

This project tests the functionality of an `AccountManager` system using JUnit 5.  
It covers scenarios for deposits, withdrawals, and edge cases like negative deposits and credit limits.

## ✅ Features Tested

- Depositing a positive amount updates balance.
- Negative deposit throws an exception.
- Withdrawals with sufficient balance.
- Withdrawals within allowed credit limits (VIP and non-VIP).
- Handling of over-credit conditions.

## 🧪 Test Results

All tests passed successfully ✅


![image](https://github.com/user-attachments/assets/fe6e1e1c-163c-4dcb-b482-844997d0917a)

## 📊 Code Coverage

- **Classes covered**: `AccountManagerImpl`, `Customer`
- **Tests**: 8 unit tests
- **Coverage Summary**:
  - **Lines covered**: 100%
  - **Branches covered**: 100%
  - **Methods covered**: 100%
    
 ![image](https://github.com/user-attachments/assets/0313186e-8f69-4c32-a72c-2240871e1199)


> This was measured using IntelliJ's built-in test coverage tool. You can generate a full report using [JaCoCo](https://www.jacoco.org/jacoco/) or [Cobertura](https://cobertura.github.io/cobertura/).


## Structure

- `AccountManagerTest`: Contains 8 unit tests.
- `AccountManagerImpl`: Logic for deposit and withdrawal.
- `Customer`: Model holding balance and account status.

---

