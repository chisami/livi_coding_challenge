Feature: Credit Assessment Calculator
  This is a simple credit assessment calculator feature
     
  Scenario Outline: Calculate Credit Assessment Score
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters
    Then The credit assessment score should match <creditScore>    
    
  Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | creditScore |
    | 6 | "Sole Proprietorship" | 5 | 7 |
    | 10 | "Limited Liability Company" | 8 | 13 |
    | 1 | "Others" | 0 | 2 |
    | 202 | "Partnership" | 13 | 29 |    
   
  Scenario Outline: Calculate Credit Assessment Score (Check Exception case: Invalid/ Missing argument(s)) 
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees1>, <companyType1>, <numberOfYearsOperated1> parameters and some parameters are invalid. 
    Then The client receives status code of 400
    And The client receives <failMessage1>    
    
    
  Examples:
    | numberOfEmployees1 | companyType1 | numberOfYearsOperated1 | failMessage1 |
    | 6 | "" | 5 | "Validation failed for : creditServiceRequestModel.companyType  :  Company Type is mandatory" |
    | -1 | "Limited Liability Company" | 8 | "Validation failed: Number of Employee should be larger than or equal to 1" |
    | 1 | "Others" | -1 | "Validation failed: Number of years operated should be larger than or equal to 0" |
    
  Scenario: Calculate Credit Assessment Score (Check Exception case: User is not authenticated) 
    When The calculateCreditAssessmentScore API is called and Token without 'Read' scope
    Then The client1 receives status code of 403  
    And The client1 receives 'Access denied.'
    
  Scenario: Calculate Credit Assessment Score (Check Exception case: User is not authorized) 
    When The calculateCreditAssessmentScore API is called without Bearer Token
    Then The client2 receives status code of 401  
    And The client2 receives "Full authentication is required to access this resource"    