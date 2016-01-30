// Clear all data from memory
clear

// Set the random number seed
set seed 7779311

// Set the number of observations in the dataset
set obs 27

// Letters used for extended missing values
loc letters a b c d e f g h i j k l m n o p q r s t u v w x y z

// Loop over numeric types
foreach t in byte int long float double {

	// First observation will be generic missing value
	qui: g `t' v_`t' = . in 1
	
	// For observations 2 - 27 each record will correspond to an individual 
	// extended missing value
	forv i = 1/26 {
	
		// Replace the record with the next extended missing value
		qui: replace v_`t' = .`: word `i' of `letters'' in `= `i' + 1'

		// Builds a value label with the missing value masks
		la def nummissing .`: word `i' of `letters'' ".`: word `i' of `letters''", modify

	} // End Loop over the extended missing values

	// Applies a variable label to the va
	la var v_`t' "Missing values of type `t'"
	
	// Add a characteristic for this variable
	char v_`t'[comment_`t'] "Contains extended missing values of type `t' for Java testing"

	// Apply the value labels to the numeric types
	la val v_`t' nummissing
	
} // End Loop over numeric types

// Create a string variable with all missing values
qui: g str12 stringvar = ""

// Creates a strL variable with all missing values
qui: g strL strlmissing = ""

// Attach variable labels to each of the two variables
la var stringvar "String variable with empty/missing values"
la var strlmissing "Blob variable with all empty/missing values"

// Create a strL variable with non-missing values
qui: g strL strlvar = "This is a strL " + strofreal(int(runiform(1, 10))) in 1/14
qui: replace strlvar = "This is still a strL " in 15/27

// Attach variable label to the non-missing strL
la var strlvar "A strL variable with non-missing values"

// Attach a characteristic to the dataset
char _dta[datacomment] "This is a comment on the dataset itself"

// Save the data set as a 118 release
qui: save ~/Desktop/Programs/Java/dta/src/test/data/stmissings_v14.dta, replace

// Save the same data set as a 117 release file
qui: saveold ~/Desktop/Programs/Java/dta/src/test/data/stmissings_v13.dta, replace v(13)

// Make the strL variables str244 for the older release version files
recast str244 strlmissing strlvar, force

// Save the dataset w/o strLs to a 115 release file
qui: saveold ~/Desktop/Programs/Java/dta/src/test/data/stmissings_v12.dta, replace v(12) 

// Save the dataset w/o strLs to a 114/115 release file (not sure since v11 isn't referenced in the .dta documentation)
qui: saveold ~/Desktop/Programs/Java/dta/src/test/data/stmissings_v11.dta, replace v(11) 

