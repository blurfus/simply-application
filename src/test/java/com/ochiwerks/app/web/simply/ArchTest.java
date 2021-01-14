package com.ochiwerks.app.web.simply;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ochiwerks.app.web.simply");

        noClasses()
            .that()
            .resideInAnyPackage("com.ochiwerks.app.web.simply.service..")
            .or()
            .resideInAnyPackage("com.ochiwerks.app.web.simply.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ochiwerks.app.web.simply.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
