package com.exalt.katas.infrastructure;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.exalt.katas.infrastructure", importOptions = {ImportOption.DoNotIncludeTests.class})
class ArchitectureInfrastructureTest {

  @ArchTest
  public static final ArchRule domainRule =
      noClasses()
          .should().dependOnClassesThat()
          .resideInAnyPackage("..application..");
}
