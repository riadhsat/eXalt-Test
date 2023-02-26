Feature: test all operations

  Scenario: test deposit scenario
    Given prepare request param avec '1500'
    When call endpoint '/deposit' with 'POST'
    Then successful operation with '{"message":"Votre depot de montant 1500 a ete effectué avec succès"}'

  Scenario: test withdrawal scenario
    Given prepare request param avec '1500'
    When call endpoint '/withdrawal' with 'POST'
    Then successful operation with '{"message":"Votre retrait de montant 1500 a ete effectué avec succès"}'

  Scenario: test consult scenario
    When call endpoint '' with 'GET'
    Then successful operation with '{"message":"Votre solde est :0.0"}'

  Scenario: test consult Transactions scenario
    Given prepare request param with page '0' and pageSize '10'
    When call endpoint '/transactions' with 'GET'
    Then successful operation with page 0 and totalElement 2


  Scenario: test failed deposit scenario
    Given prepare request param avec '-1500'
    When call endpoint '/deposit' with 'POST'
    Then failed operation with 400 and error code 4000 and message 'Le montant doit être supérieur à 0'

  Scenario: test failed withdrawal scenario when invalid amount
    Given prepare request param avec '-2500'
    When call endpoint '/withdrawal' with 'POST'
    Then failed operation with 400 and error code 4000 and message 'Le montant doit être supérieur à 0'

  Scenario: test failed withdrawal scenario when insufficient balance
    Given prepare request param avec '2500'
    When call endpoint '/withdrawal' with 'POST'
    Then failed operation with 400 and error code 4001 and message 'Votre solde est insuffisant'

  Scenario: check consult Transactions after all operation scenario
    Given prepare request param with page '0' and pageSize '10'
    When call endpoint '/transactions' with 'GET'
    Then successful operation with page 0 and totalElement 3