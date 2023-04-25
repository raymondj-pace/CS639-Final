# Pace University
# CS 639 Mobile Application Development
# May 2023, Final Project

<br>

## The topic this semester is: Pace Undergraduate Students’ Financial Literacy

### Proposal:
The goal of this project is to create an application that keeps track of money deductions (expenses) and additions. This type of application was listed at [Purdue Global](https://www.purdueglobal.edu/blog/student-life/budgeting-financial-literacy/) (part of Purdue University) as one of the five most important things to aid a student in keeping a budget:
```
3. List Your Expenses and Track Your Spending
Next, you’ll want to list out all of your expenses over the course of your budget’s time span. You can estimate expenses
by looking at past bank statements and credit card statements. Anticipate the costs of tuition, textbooks, school 
supplies, transportation, and other costs.

Over the next month, record all of your purchases and add these to your budget, adjusting your original estimates as 
necessary. It can be helpful to organize your expenses into different categories, such as education, food, housing, and 
entertainment. Separating expenses into needs and wants can help you figure out where you can cut back.
```

Sources to be used for paper and presentation:

[1] https://www.nasfaa.org/news-item/29609/Financial_Literacy_What_College_Students_Need_to_Know

[2] https://blog.ed.gov/2021/04/financial-literacy-education-and-paying-for-college/

[3] https://www.purdueglobal.edu/blog/student-life/budgeting-financial-literacy/

[4] https://college.harvard.edu/guides/financial-literacy

[5] https://www.financialeducatorscouncil.org/why-is-financial-literacy-important-for-college-students/

[6] https://firstgen.naspa.org/scholarly-article/financial-literacy-of-college-students-focus-on-first-generation-students

[7] Costa Mendes, Filipa, "Financial Literacy of College Students Study Case: Students of the University of Porto", Dissertation of Master in Finance, Sept 2013 



<br>

## Application intial rough draft:
![CS639-RoughDraft](https://user-images.githubusercontent.com/94663542/232889561-7593f186-8f36-4eeb-b7f7-a465c3a3b712.jpg)

<br>

## Appllication prototype done with Figma:
![FIGMA-prototype-v1](https://user-images.githubusercontent.com/94663542/232889751-70d7418a-305b-44af-9fb3-aafe278f467e.png)


<br>
<br>
<br>

### Internal Application Structure
<p>The application uses multi-fragments with navigation. The main fragment uses a custom listview by creating an ArrayAdapter subclass so that each line within the listview can have its columns justified similar to an html table. The database uses Firebase to keep track of all transactions. The main fragment view shows the user's current balance. If the balance goes negative the balance will be shown in red.

Transactions can be edited by clicking on the row and the child fragment will be prefilled.

The navigation uses safe-args and passes parcelable bundles by implementing the Parcelable interface. This was a difficult task because if your class is not implementing the Parcelable interface there is no error in Logcat to indicate this. A lot of digging was required to get passing Java objects between fragments working.
</p>

<br>
<br>
<br>

# **PROJECT SPRINTS:**
## Sprint #1: April 22 - April 29
### 1. Investigate and add horizontal left swipe to delete records: Rene and Rushda
### 2. Add opening animation screen to the app: Ray
### 3. Investige and add passing custom java objects between fragments to all for updating of records: Ray
### 4. Literature and survey review and start inital draft for project paper: Ray

<br>
<br>
<br>
<br>



## Application version as of 2023-04-24
![Screen Shot 2023-04-23 at 5 13 16 PM](https://user-images.githubusercontent.com/94663542/233866658-e454c3c2-d55b-48bc-bcf1-1cb418ad021d.png)
![Screen Shot 2023-04-24 at 6 16 36 PM](https://user-images.githubusercontent.com/94663542/234129236-8a8ce217-9003-4b43-9d76-60d5759354f0.png)
![Screen Shot 2023-04-24 at 6 16 50 PM](https://user-images.githubusercontent.com/94663542/234129258-8016ee39-465c-499c-ab32-321333050a03.png)
![Screen Shot 2023-04-23 at 3 32 01 PM](https://user-images.githubusercontent.com/94663542/233866692-ecb9ff3f-6260-4d10-98ff-bf8b2f859428.png)

<br>
<br>
<br>

## Interesting things learned so far:
How to put a border around a TextView by creating a drawable.<br>
How to modify the format of a listview to put in column format.<br>
How to make a listview clickable (not easy!).<br>
How to pass custom Java objects between fragments with using host navigation AND implementing the Parcelable interface and creating bundles.<br>
How to update a Firebase record by using the key value (have to save the key value in the Java object).
How to create an opening animation screen.
<br>
<br>

## Things still TODO:
Allow for line items to be deleted from the list view using a horizontal swipe action across rows.<br>
