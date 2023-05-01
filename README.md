# Pace University
# CS 639 Mobile Application Development
# May 2023, Final Project

<br>

## The topic this semester is: *Pace Undergraduate Students’ Financial Literacy*

### Proposal:
The goal of this project is to create an application that keeps track of money deductions (expenses) and additions. This type of application was listed at [Purdue Global](https://www.purdueglobal.edu/blog/student-life/budgeting-financial-literacy/) (part of Purdue University) as one of the five most important things to aid a student in keeping a budget:

<br>

> 3. List Your Expenses and Track Your Spending
> Next, you’ll want to list out all of your expenses over the course of your budget’s time span. You can estimate expenses
> by looking at past bank statements and credit card statements. Anticipate the costs of tuition, textbooks, school 
> supplies, transportation, and other costs.
> 
> Over the next month, record all of your purchases and add these to your budget, adjusting your original estimates as 
> necessary. It can be helpful to organize your expenses into different categories, such as education, food, housing, and 
> entertainment. Separating expenses into needs and wants can help you figure out where you can cut back.

<br>

## Sources for project paper:

[1]&emsp;2022 RCS Fact Sheet #3 Preparing for Retirement in America, 2022 EBRI/Greenwald Research Retirement Confidence Survey.</ul>

[2]&emsp;Brau, James C., et al., Financial Literacy among College Students: An Empirical Analysis, Journal of Financial Education, July 13, 2021.

[3]&emsp;Fay, Bill, Bankruptcy Statistics, (https://www.debt.org/bankruptcy/statistics/), April 13, 2023.

[4]&emsp;National Association of Student Financial Aid Administrators, Financial Literacy: What College Students Need to Know,<br>
&emsp;&emsp;&emsp;https://www.nasfaa.org/news-item/29609/Financial_Literacy_What_College_Students_Need_to_Know, December 8, 2022.

[5]&emsp;The U.S. Department of Education, Financial Literacy Education and Paying for College, HOMEROOM, The Official Blog of the U.S.<br>
&emsp;&emsp;&emsp;Department of Education, April 30, 2021.

[6]&emsp;Purdue Global, Budgeting and Financial Literacy for College Students,<br>
&emsp;&emsp;&emsp;https://www.purdueglobal.edu/blog/student-life/budgeting-financial-literacy/, May 13, 2022.

[7]&emsp;Gomez, E., Zhou, J., & Yu, J., Financial Literacy of College Students: Focus on First-Generation Students. Journal of Business Diversity,<br>
&emsp;&emsp;&emsp;23(1). 2023, https://doi.org/10.33423/jbd.v23i1.5777

[8]&emsp;Fox, Michelle, Half of Americans with Retirement Accounts Have Taken an Early Withdrawal,<br>
&emsp;&emsp;&emsp;https://www.cnbc.com/2021/11/22/half-of-americans-with-retirement-accounts-have-taken-early-withdrawals.html,<br>
&emsp;&emsp;&emsp;November 22, 2021.

<br>

## Project paper:
*Put link here when done*

<br>

## Application intial rough draft:
![CS639-RoughDraft](https://user-images.githubusercontent.com/94663542/232889561-7593f186-8f36-4eeb-b7f7-a465c3a3b712.jpg)

<br>

## Application prototype done with Figma:
![FIGMA-prototype-v1](https://user-images.githubusercontent.com/94663542/232889751-70d7418a-305b-44af-9fb3-aafe278f467e.png)


<br>
<br>
<br>

### Internal Application Structure:
<p>The application uses multi-fragments with navigation. The main fragment uses a custom listview by creating an ArrayAdapter subclass so that each line within the listview can have its columns justified similar to an html table. The database uses Firebase to keep track of all transactions. The main fragment view shows the user's current balance. If the balance goes negative the balance will be shown in red.

Transactions can be edited by clicking on the row and the child fragment will be prefilled.

The navigation uses safe-args and passes parcelable bundles by implementing the Parcelable interface. This was a difficult task because if your class is not implementing the Parcelable interface there is no error in Logcat to indicate this. A lot of digging was required to get passing Java objects between fragments working.
</p>

<br>
<br>
<br>

# **PROJECT SPRINTS:**
## Sprint #1: April 22 - April 28
### 1. Investigate and add horizontal left swipe to delete records: Rene and Rushda
### 2. Add opening animation screen to the app: Ray
### 3. Investige and add passing custom java objects between fragments to all for updating of records: Ray
### 4. Literature and survey review and start inital draft for project paper: Ray
<br>

## Sprint #1 retrospective:
### 1. Parcelable object type now being passed between fragments.
### 2. Add update (edit) functionality by clicking row in listview.
### 3. Added UI delete option.
### 4. Add intial animation screen.
### 5. Created new app launcher icon.
### 6. Project paper started. (continued into sprint#2)
### 7. Intial version of swipe left delete working. (continued into sprint#2)

<br>

## Sprint #2: April 29 - May 5
### 1. Continue working on left swipe delete to fix UI issues: Rene
### 2. Continue working on project paper: Ray
### 3. Investigate adding test case and code coverage: all
### 4. Add Google authenication login using Firebase: Rushda

<br>
<br>
<br>


## Application Test Environment:
https://github.com/raymondj-pace/CS639-Final/tree/main/TEST%20IMAGES

<br>
<br>
<br>



## Application version as of 2023-04-24:
![Screen Shot 2023-04-23 at 5 13 16 PM](https://user-images.githubusercontent.com/94663542/233866658-e454c3c2-d55b-48bc-bcf1-1cb418ad021d.png)
![Screen Shot 2023-04-24 at 6 16 36 PM](https://user-images.githubusercontent.com/94663542/234129236-8a8ce217-9003-4b43-9d76-60d5759354f0.png)
![Screen Shot 2023-04-24 at 6 16 50 PM](https://user-images.githubusercontent.com/94663542/234129258-8016ee39-465c-499c-ab32-321333050a03.png)
![Screen Shot 2023-04-23 at 3 32 01 PM](https://user-images.githubusercontent.com/94663542/233866692-ecb9ff3f-6260-4d10-98ff-bf8b2f859428.png)
<br>

## Application icon:
![Application Launch Icon](https://user-images.githubusercontent.com/94663542/234710407-480fe430-7cc7-440f-8a02-dd1d865cf891.png)
<br>
<br>

## Interesting things learned so far:
How to put a border around a TextView by creating a drawable.<br>
How to modify the format of a listview to put in column format.<br>
How to make a listview clickable (not easy!).<br>
How to pass custom Java objects between fragments with using host navigation AND implementing the Parcelable interface and creating bundles.<br>
How to update and delete a Firebase record by using the key value (have to save the key value in the Java object).<br>
How to create an opening animation screen.<br>
How to work in a team using github.<br>
How to add swipe-left support for delete action.<br>
<br>

## Things still *TODO*:
Setup app test cases.
