# Spring Boot 2 Netbeans Getting Started

## Usage

Just clone the repository and you're ready to go (please see prerequisites section). You'll get a nice template for an empty Spring Boot 2 application.

## Features

Highly opinionated build with selection of Maven plugins made accessible via the Netbeans UI.
The two projects in `spring-boot-netbeans-single-module` and `spring-boot-netbeans-multi-module` can be used as templates.

 In order to speed up the IDE actions as much as possible **[Run]**, **[Debug]**, and **[Test]** are even skipping `resources:[test]Resources` and `compiler:[test]Compile` and simply use the current contents of the `target` directory.
This is done with the `maven-skip-execution-profile-extension` that automatically creates the (runtime) profiles needed for skipping certain plugins actions.
The profiles don't end up in the actual POM.

![Sample Web applcation](./nbactions.png)

- **[Build]** `install` with `resources:[test]Resources` and `compiler:[test]Compile` only
- **[Clean and Build]** `clean install` with `resources:[test]Resources` and `compiler:[test]Compile` only
- **[Run]** `spring-boot:run`
- **[Debug]** `spring-boot:run` with debugger attached
- Run Maven
  - **[Full Build]** a full `clean install` with verification
  - **[Format Code]** `formatter:format` according to `eclipse-formatter-config.xml` and `impsort:sort`  
  - **[Integration Test]** `failsafe:integration-test`
  - **[Static Code Analysis]** `spotbugs:check`, `checkstyle:check`, `pmd:check`, `pmd:cpd-check` and `arch-unit:arch-test`
  - **[SpotBugs]** `spotbugs:check`
  - **[SpotBugs GUI]** `spotbugs:gui`
  - **[Checkstyle]** `checkstyle:check` for metric checking  ([Cyclomatic Complexity](https://checkstyle.org/config_metrics.html#CyclomaticComplexity) and [NCSS](https://checkstyle.org/config_metrics.html#JavaNCSS))
  - **[PMD]** `pmd:check`
  - **[Clone Detection]** `pmd:cpd-check`
  - **[ArchUnit Maven plugin]** `arch-unit:arch-test` 
  - **[Dependency Tree]** `dependency:tree`
  - **[Dependency Graph]** `depgraph:graph`
  - **[POM Hierarchy Tree]** `hierarchy:tree`
  - **[Sortpom]** `sortpom:sorty`
  - **[Enforce Dependency Convergence]** `enforcer:enforce@dependency-convergence`
  - **[Available Dependency Updates]** `versions:display-dependency-updates`
  
## Prerequisites
1. recent JDK 8 (also works with Java 11, just set the property `java.version` to `11` to for example use the `var` keyword)
1. recent Maven (tested with 3.6.1)
1. Netbeans >= 8.2 (`nbactions.xml` tested with 8.2 and 11.1)
1. `mvn clean install` in `skip-execution-profile/maven-skip-execution-profile-extension` directory of the repository
1. `mvn clean install` of [Pocketsaw 1.3.2](https://github.com/janScheible/pocketsaw/tree/1.3.2)