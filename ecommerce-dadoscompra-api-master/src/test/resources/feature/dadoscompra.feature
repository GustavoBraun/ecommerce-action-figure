Feature: DadosCompra Resource

  Background:
    Given dadosCompra codigo "c3ec1783-67b5-4924-b8d3-e57c8dd2dd08"

  Scenario: create dadosCompra
    When the client calls "/v1/dadosCompra/"
    Then the client receives status code of 201
    And response is "{\"codigo\":\"c3ec1783-67b5-4924-b8d3-e57c8dd2dd08\"}"