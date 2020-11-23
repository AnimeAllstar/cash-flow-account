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

## UML Diagram 

![uml diagram](https://github.students.cs.ubc.ca/CPSC210-2020W-T1/project_l7a2y/blob/master/UML_Design_Diagram.png?raw=true)

Above is the UML diagram for this project. It does **not** include classes from the Java library or from the external libraries used in this project.

### Phase 4: Task 2
 
I use the Map interface in the PieChartDialog class. It is used to store the total amount spent/earned (Double - value) from each category (String - key). The computeAmounts method uses a Map to compute to the total amount spent using a list of items, and the createPieDataSet uses the Map to create a DataSet for the pie chart.
 
I have implemented an inheritance relationship with Item as the super class, and ExpenseItem and IncomeItem as the subclasses. Each subclass has its own static arraylist of categories that are used to categorize the users' income/expense, and a static variable that stores the class name. Each subclass overrides the getClassName abstract method and the toJson method in the Item class by returning their unique static variables.

### Phase 4: Task 3

I feel like I did a good job in dividing the project into smaller classes leading to a high level of cohesion. I have also tried to reduce coupling as much as possible as I progressed from one phase to the other.

However, if I did have more time, I would consider the following improvements to the design:
 
 - Creating some sort of type hierarchy in my UI package to try to group common behaviour between classes like AddItemDialog and MainPanel.
 - Using iterators to improve the efficiency of existing code.
 - Splitting CustomVerifier into 2 classes, one to display error messages, and the other one to actually complete validation checks and throw exceptions.
 - Refactoring a DefaultTableCellRenderer subclass to format the JTable in MainPanel.