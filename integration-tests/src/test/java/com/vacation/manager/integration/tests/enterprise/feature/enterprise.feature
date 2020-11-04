Feature: Enterprise Api

  Background:
    * url baseUrl
    * def initDatabase =
    """
    function() {
      var DbUtils = Java.type('com.vacation.manager.integration.tests.enterprise.WeatherDbUtils');
      var db = new DbUtils(dbConfig);
     return db;
    }
    """
    * def db = callonce initDatabase

    * json stScenario = read('classpath:json/scenario1.json')
    * json ndScenario = read('classpath:json/scenario2.json')

    * configure afterFeature =
    """
    function(){
      db.clearEnterprisesData();
    }
    """


  Scenario: add and verify new confirmed enterprise
    Given path '/enterprises/enterprise'
    And request stScenario.registerCompanyForm
    When method POST
    Then status 200

    Given path '/enterprises/confirm/' + stScenario.registerCompanyForm.email + '/' + stScenario.registerCompanyForm.enterpriseName
    And request {}
    When method PUT
    Then status 200

    Given path '/enterprises/' + stScenario.registerCompanyForm.enterpriseName
    When method GET
    Then status 200
    And match $ == stScenario.enterpriseApi


  Scenario: add and verify new confirmed employee
    * header Authorization = callonce read('basic-auth.js') {user: #(user), password: #(password)}
    Given path '/workers/CEO/add'
    And request ndScenario.RegisterEmployeeForm
    When method POST
    Then status 200

    Given path '/workers/confirm/' + ndScenario.RegisterEmployeeForm.email + '/' + ndScenario.RegisterEmployeeForm.enterpriseName
    And request {}
    When method PUT
    Then status 200

    Given path '/workers/' + ndScenario.RegisterEmployeeForm.email + '/' + ndScenario.RegisterEmployeeForm.enterpriseName
    When method GET
    Then status 200
    And match $ == ndScenario.containsWorkerApi


