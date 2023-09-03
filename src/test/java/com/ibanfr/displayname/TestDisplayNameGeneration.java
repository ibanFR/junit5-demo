package com.ibanfr.displayname;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@DisplayName("Display Name Generation")
class TestDisplayNameGeneration {

    @Test
    @DisabledIf("defaultDisplayNameGeneratorIsSet")
    void shouldDisplayMethodName(TestInfo testInfo) {
        assertThat(testInfo.getDisplayName()).contains("shouldDisplayMethodName");
    }

    @Nested
    @DisplayName("When @DisplayName annotation is used")
    class WhenDisplayNameAnnotationIsUsed {

        @Test
        @DisplayName("should display the content of the @DisplayName annotation")
        void shouldUseDisplayNameAnnotatedContent(TestInfo testInfo) {
            assertThat(testInfo.getDisplayName()).contains("should display the content of the @DisplayName annotation");
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class When_DisplayNameGeneration_is_ReplaceUnderscores {

        @Test
        void should_replace_underscores_with_spaces(TestInfo testInfo) {
            assertThat(testInfo.getDisplayName()).contains("should replace underscores with spaces");
        }
    }

    @Nested
    @DisplayNameGeneration(ReplaceCamelCase.class)
    class WhenDisplayNameGenerationIsReplaceCamelCase {

        @Test
        void shouldReplaceCamelCase(TestInfo testInfo) {
            assertThat(testInfo.getDisplayName()).contains("should replace camel case");
        }

    }

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = ReplaceCamelCase.class)
    class WhenIndicativeSentencesGenerationIsEnabled {
        @Test
        void shouldDisplayClassNameAndTestName(TestInfo testInfo) {
            assertThat(testInfo.getDisplayName()).contains(
                    "When indicative sentences generation is enabled -> should display class name and test name");
        }
    }

    protected boolean defaultDisplayNameGeneratorIsSet(ExtensionContext extensionContext) {
        Optional<String> configurationParameter = extensionContext.getConfigurationParameter(
                "junit.jupiter.displayname.generator.default");

       return configurationParameter.isPresent();
    }

}
