# May 2023, CS639 Mobile Application Development, Final Project

## The topic this semester is: Pace Undergraduate Students’ Financial Literacy

### Proposal:
The goal of this project is to create an application that keeps track of money deductions (expenses) and additions. This type of application was listed at [Purdue Global](https://www.purdueglobal.edu/blog/student-life/budgeting-financial-literacy/) (part of Purdue University) as one of the five most important things to aid a student in keeping a budget:
```
3. List Your Expenses and Track Your Spending
Next, you’ll want to list out all of your expenses over the course of your budget’s time span. You can estimate expenses by looking at past bank statements and credit card statements. Anticipate the costs of tuition, textbooks, school supplies, transportation, and other costs.

Over the next month, record all of your purchases and add these to your budget, adjusting your original estimates as necessary. It can be helpful to organize your expenses into different categories, such as education, food, housing, and entertainment. Separating expenses into needs and wants can help you figure out where you can cut back.
```




<br>

## Application intial application rough draft:
![CS639-RoughDraft](https://user-images.githubusercontent.com/94663542/232889561-7593f186-8f36-4eeb-b7f7-a465c3a3b712.jpg)

<br>

## Appllication prototype done with Figma:
![FIGMA-prototype-v1](https://user-images.githubusercontent.com/94663542/232889751-70d7418a-305b-44af-9fb3-aafe278f467e.png)


<br>
<br>
<br>

### Internal Application Structure
<p>The application uses multi-fragment with navigation. The main fragment uses a custom listview by creating a BaseAdapter subclass so that each line within the listview can have its columns justified similar to an html table. The database uses Firebase to keep track of all transactions. The main fragment view shows the user's current balance. If the balance goes negative the balance will be shown in red.</p>


<br>
<br>
<br>

## Interesting things learned so far:
How to put a border around a TextView by creating a drawable.<br>
How to modify the format of a listview.<br>
<br>
<br>

## Things still TODO:
Allow for line items to be deleted or edited from the list view.<br>
Allow the fragments to serialze Java objects between them to make the saving of data easier.

