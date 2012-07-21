Feature: On selection of a year and a month, appropriate days must be selectable
	As user
	I select an year and a month and a date
	So that I can select my date of birth
 
	Scenario Outline: User has filled in other relevant details
		Given the user goes to the login page
 			And the user selects the year <year_selected>
		When the user selects a month <month_selected>
		Then the user should have this day to select <result>
 	
 	Examples:
 		|year_selected |month_selected|result|
 		|1996          |February      |28    |
 		|2000          |February      |29    |
 		|2001          |January       |31    |
 
