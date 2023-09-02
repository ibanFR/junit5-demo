# JUnit 5 demo project
JUnit 5 playground project to showcase following themes:
- Test cases as executable specifications using @DisplayName and @DisplayNameGeneration.
- Maven surefire junit5-tree reporter.

## Display Name Generation
Use @DisplayName and @DisplayNameGeneration to improve test readability.

Setting up default displayname generator
`mvn test -Djunit.jupiter.displayname.generator.default=org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores`

## Junit5 tree reporter
Use https://github.com/fabriciorby/maven-surefire-junit5-tree-reporter to add a tree view for the unit tests executed 
using JUnit5.

run `mvn test`
