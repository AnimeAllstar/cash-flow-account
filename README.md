# Cash Flow Account

## Overview 

**Cash Flow Account** is an application designed to track an individual's expenditures and income over time. It does **not** function as a bank account that restricts you from making purchasing that exceed your balance. Instead, the purpose of this application is to help users to understand their spending habits by categorizing their *income* and *expenditure*.

The application is meant to be used by users of all demographics who wish to better understand the sources of their income and what they spend their money on.

This project personally interested me as I wanted to way to record my expenses and understand where I spend my money. I did not want to wonder why I spent $50 two months ago and have miscellaneous expenses that I cannot recollect making in my bank statements. Also, by understanding where I spend my money, I would be able to curb my spending as being an international student at University is expensive.

## User Stories

- As a user, I want to be able to add an item to my account
- As a user, I want to be able to view a list of the expenses or earnings in my account
- As a user, I want to be able to edit an item in my account
- As a user, I want to be able to remove an item from my account

- As a user, I want to be able to save items in my account to a file
- As a user, I want to be able to load my account items from a file 

### Phase 4: Task 2

- I use the Map interface in the PieChartDialog class. It it used to store the total amount spent/earned (Double - value) from each category (String - key). The computeAmounts method uses a Map to compute to the total amount spent using a list of items and the createPieDataSet uses the Map to create a DataSet for the pie chart.
- I have implemented an inheritance relationship with Item as the super class, and ExpenseItem and IncomeItem as the subclasses. Each subclass has its own static arraylist of categories that are used to categorize the users income and expenses. Furthermore, each subclass overrides the getClassName abstract method in the Item class. This method is used in the CashFlowAccount class to filter items based on type, and in the TableModel class to display the item type to the user via the JTable.