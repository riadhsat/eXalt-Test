package com.exalt.katas.application;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.exalt.katas.application", importOptions = {ImportOption.DoNotIncludeTests.class})
class ArchitectureApplicationTest {

  @ArchTest
  public static final ArchRule domainRule =
      noClasses()
          .should().dependOnClassesThat()
          .resideInAnyPackage("..infrastructure..");
}
