*****************************************************************************Expance Tracker Application*****************************************************************************

***********************************************************************Signup api***********************************************************************************
  Method:Post
  url:http://localhost:8080/eta/signup
  Authentication:Not Required
  data:{
          "name":"?",
          "password":"?",
          "confirmPassword":"?",
          "mobile":"?",
          "email":"?"
      }
*****************************************************************Verify api(for email verification after signup)*******************************************************
method:Get
url:http://localhost:8080/eta/verify?token=4a451ade-0a5e-446d-b25a-19091b54bd9e
Authentication:Not Required
data:{
        //token=4a451ade-0a5e-446d-b25a-19091b54bd9e
        "token":"?"
    }
  
*******************************************************************Login api******************************************************************************************
  Method:Post
  url:http://localhost:8080/eta/login
  Authentication:Not Required
  data:{
          "username":"?",
          "password":"?"
      }
******************************************************************ForgetPassword api**********************************************************************************
  method:Post
  Authentication:Not Required
  url:http://localhost:8080/eta/forgetPassword
  data:{
          "email":"?"
      }
  
*******************************************************************resetPassword api*********************************************************************************
  method:Post
  url:http://localhost:8080/eta/resetPassword?token=7d613e31-d86e-4cee-bf1c-8a7093b264a6If
  Authentication:Not Required
  data:{
          "newPassword":"?",
          //token=7d613e31-d86e-4cee-bf1c-8a7093b264a6If
          "token":"?"
      }
      
  
*******************************************************************Categories api***********************************************************************************
  Method:Get
  Authentication:Required
  url:http://localhost:8080/eta/categories

***********************************************************************Income api**********************************************************************************
  Method:Post
  url:http://localhost:8080/eta/income
  Authentication:Required
  data:{
          "user": { "userId":? },
           "incomeAmount": "?",
           "incomeSource":?,
           "description": "?"
      }
      //to get the user income
       Method:Get
              url:http://localhost:8080/eta/income/1
              Authentication:Required

***********************************************************************Expenses api***********************************************************************************
  Method:Post
  url:http://localhost:8080/eta/expenses
  Authentication:Required
  data:{
           "user": { "userId": ? },
           "category": { "categoryId": ? },
           "expenseAmount": "?",
           "description": "?"
      }

      //to get the expenses by user id
      Method:Get
        url:http://localhost:8080/eta/categoryExpenses/1
        Authentication:Required

      //to get the expenses by category id
      Method:Get
          url:http://localhost:8080/eta/userExpenses/1
          Authentication:Required

