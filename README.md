# JUnit 5 demo project
JUnit 5 playground project to showcase following themes:
- Maven surefire junit5-tree reporter.
- Test cases as executable specifications using @DisplayName and @DisplayNameGeneration.
- Conditional Test execution.

## Junit5 tree reporter
Use [Maven Surefire JUnit5 TreeView Extension](https://github.com/fabriciorby/maven-surefire-junit5-tree-reporter) to add a tree view for the unit tests executed
using JUnit5.

## Display Name Generation
Use `@DisplayName` and `@DisplayNameGeneration` to improve test readability.

See [Junit5 User Guide -> Display Names](https://junit.org/junit5/docs/current/user-guide/#writing-tests-display-names).

run `mvn test`

Setting up default displayname generator:

`mvn test -Djunit.jupiter.displayname.generator.default=org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores`

Setting up default displayname generator to custom

`mvn test -Djunit.jupiter.displayname.generator.default=com.ibanfr.displayname.ReplaceCamelCase`

## Conditional test execution
Enable or disable a test based on a condition method configured via the `@EnabledIf` and `@DisabledIf` annotations:
- [Junit5 User Guide -> Custom conditions](https://junit.org/junit5/docs/current/user-guide/#writing-tests-conditional-execution)

Enable or disable a test or container implementing the `ExecutionCondition` extensions API:
- [Junit5 User Guide -> Conditional test execution](https://junit.org/junit5/docs/current/user-guide/#extensions-conditions)






