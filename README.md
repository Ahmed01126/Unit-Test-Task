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

![Test Results](![image](https://github.com/user-attachments/assets/e0c59895-e257-4d70-b19d-805da61ed447)
)

## Structure

- `AccountManagerTest`: Contains 8 unit tests.
- `AccountManagerImpl`: Logic for deposit and withdrawal.
- `Customer`: Model holding balance and account status.

---

