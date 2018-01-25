# The Polytech Test Framework

  * Author: Sebastien Mosser [mosser@i3s.unice.fr](mosser@i3s.unice.fr)
  * Version: 2018.01

## Rationale

  * Understand the implementation principles hidden in classical testing frameworks
  * Experiment advanced mechanisms of Java such as class annotations and reflexivity

## Repository Organization

## Framework Contents

### Polytest: Unit tests (JUnit)

  - [] Validating assertions
    - [] failing
    - [] asserting True or False
    - [] asserting equality    
  - [] Annotating methods as Tests
  - [] Catching exceptions when relevant
  - [] Running Tests
  - [] Execution semantics (before/after, ignore, parallel)

### Polymock: Mocks & Spies (Mockito)

  - [] Intercepting calls to methods
  - [] spying on methods' executions
  - [] mocking methods answers
  - [] the "final" problem

### Polymber: Acceptance Scenarios (Cucumber)

  - [] Express a regular expression to trigger a test
  - [] Execution semantics: Given, When, Then
  - [] Using parameters to customize tests
