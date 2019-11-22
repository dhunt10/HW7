# HW7
### By Darin and Satwik

### ***Changes Made***
##### 
we have added in a single change function called updateCurrentView
we have added in a evaluate single cell function, which in turn evaluates one single cell and 
checks to see if any other cells rely on it. The way our design currently works is we 
have a builder which makes a spread sheet with or without an infile. Then once the file is
built we can then add to it with the newly made updateCurrentView function. We have obviously
added in a new dynamic view which we can manipulate after the creation of the spreadsheet. 
Every time we make changes to the spreadsheet we then update the dynamic view. 

##### Status

- [x] Model
- [x] Model Test
- [x] Text View
- [x] Text View Test
- [x] Graphical View
- [x] Graphical View Test
- [ ] Controller
- [ ] Controller Test
- [ ] Additional View 
- [ ] Additional View Test
------------------------------------------------------------------

##### 3 inputs are possible for cells
**Values** 
 - Values can be:
  ````
  numbers 
  booleans
  strings
  ````
**Formulas** 
 - Formulas can include:
  ````
  SUM
  PROD
  COMB
  <
  ````
**Reference**
 - A reference to an additional cell or block of cells
 > - A1:B7 (Block Reference)
 > - C2 (Single Reference)

------------------------------------------------------------------
##### The following is an example input file 
````
A1 3
A2 7
A3 =(SUM 10 2)
A4 =(SUM A1 A2)
A5 =(PROD (SUM 10 20) (PROD 10 A4))
A6 =(SUM A2:B2)
A7 =(< A7 3)
````
