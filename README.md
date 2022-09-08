
## Prerequisites
In order to be able to setup and run this application, the following need to be installed and setup
- [Android Studio](https://developer.android.com/studio)

## Project Setup

To setup the project in your machine:

- Open and Run Project in android studio
- Run the application.


### TASK 

SampleLoanApp is an app that displays a UserLoanProfile

## Architecture

The project design pattern is clean architecture MVVM:
- Data layer
- Domain layer
- Presentation layer
- Di
- Utils
  

### Data layer
- The data layer handles all logic to do with fetching data from the different data sources and provides it to the app.
- It  has all the repositories which the domain layer can use.
  
### Domain Layer
- The layer  acts as the mediator between the ViewModels and the data layer.
- It includes all the apps models *Loan* *Locale* *NetworkResponse* and and the data repository Interfaces *ILoanRepository* 
  - [NetworkResponse] is a class for displaying the different UI states i.e:
    -*Loading* when a data request is initiated to show the loading spinner.
    -*Success* when a data response is successful to display the loan data loaded.
    -*Error* when an exception occurs to show an error snack bar in the UI.

### Presentation layer
- This layer hold classes that display and present the data, it includes *LoanActivity* and viewModels *LoanViewModel*
- The activity gets data to display from the viewModel and the viewModel talks to the domain layer to perform actions.

### Di
- This layer has all the dependencyInjection logic:*AppModule* *RepositoryModule*


### Utils
- These are classes that define a set of methods that perform common, often re-used functions across the app.
    - [Utils] has functions such as *formatAmount* and *formatDate* that can be used across the app

### UtilsTest
- This class has the Utils class unit tests

## External Links/dependencies
- [Coroutines]for executing  asynchronous code
- [Hilt]for dependency injection


