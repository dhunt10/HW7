# HW7
### By Darin and Satwik

### Spreadsheets
#####
Data can be placed into the spreadsheets by loading in a static textfile as well as dynamically
when the composite graphical view is running. If the graphical view is running data can only be
inputted via a static file before running the program. Finally, a simpler textview is also available
in which you can view the data but cannot edit it as the data has been read in via a static textfile
before compile time. 

### ***Changes Made***
##### 
We have added in a single change function called updateCurrentView
we have added in a evaluate single cell function, which in turn evaluates one single cell and 
checks to see if any other cells rely on it. The way our design currently works is we 
have a builder which makes a spread sheet with or without an infile. Then once the file is
built we can then add to it with the newly made updateCurrentView function. We have obviously
added in a new dynamic view which we can manipulate after the creation of the spreadsheet. 
Every time we make changes to the spreadsheet we then update the dynamic view. 
Instead of throwing errors when illicit data is placed in one of the cells we have now
implemented certain handlers to warn or tell the user they have done so. These specific behaviors
are explained below. 

### Behavior
#####
If something is put into a cell that is believed to be a typo or an incomplete expression such 
as 
> SUM(1:) 
#####
Then the string-ified version of that expression will be placed into the cell, while the text box
will be filled with the string-ified version.

If something throws a bad entry such as 
> 10 + 3
#####
Then the cell will be filled with
````$xslt
NaN
````

while the text box will then be filled with what was originally filled in the cell,
in this example:
> 10 + 3

### Clicking and Choosing Textbox
#####
You just have to click a cell once to select it and a second time to deselect it.
You can also choose a different cell to deselect the first one and select a second
cell all in the same move. You can then enter information into the text box and press
the confirm button to add the data into the selected cell.

### Cancel Button
#####
The cancel button will delete what you had in the text box. If you wish to delete data inside
of a cell you must press the confirm button to then clear stuff in the cell itself.

### Confirm Button
#####
Any information you wish to place in a cell must be written in the text box and then
confirmed with the confirm button, this includes deleting information.

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
