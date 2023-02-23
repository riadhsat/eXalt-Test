package com.exalt.katas.domain;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.exalt.katas.domain", importOptions = {ImportOption.DoNotIncludeTests.class})
class ArchitectureDomainTest {

  @ArchTest
  public static final ArchRule domainRule =
      noClasses()
          .should().dependOnClassesThat()
          .resideInAnyPackage("..application..", "..infrastructure..");
}
