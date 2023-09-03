package com.ibanfr.displayname;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Display Name Generation")
class DisplayNameGeneratorDemoTest {

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
        void should_use_displayName_annotated_content(TestInfo testInfo) {
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

    @Nested
    @EnabledIfDefaultDisplayNameGenerator("com.ibanfr.displayname.ReplaceCamelCase")
    class WhenDefaultGenerationIsSetToReplaceCamelCase{
        @Test
        void shouldReplaceCamelCase(TestInfo testInfo){
            assertThat(testInfo.getDisplayName()).contains("should replace camel case");
        }
    }

    @Nested
    @EnabledIfDefaultDisplayNameGenerator("org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores")
    class When_default_generation_is_set_to_ReplaceUnderscores{

        @Test
        void should_replace_spaces_with_underscores(TestInfo testInfo){
            assertThat(testInfo.getDisplayName()).contains("should replace spaces with underscores");
        }
    }

    protected boolean defaultDisplayNameGeneratorIsSet(ExtensionContext extensionContext) {
        Optional<String> configurationParameter = extensionContext.getConfigurationParameter(
                "junit.jupiter.displayname.generator.default");

       return configurationParameter.isPresent();
    }


    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ExtendWith(DefaultDisplayNameGeneratorExecutionCondition.class)
    @interface EnabledIfDefaultDisplayNameGenerator {
        String value();
    }

     static class DefaultDisplayNameGeneratorExecutionCondition implements ExecutionCondition {

        @Override
        public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {

            Optional<String> configurationParameter = extensionContext.getConfigurationParameter(
                    "junit.jupiter.displayname.generator.default");

            if (configurationParameter.isPresent()) {

                boolean defaultGeneratorMatches = extensionContext.getRequiredTestClass()
                                                                  .getAnnotation(EnabledIfDefaultDisplayNameGenerator.class)
                                                                  .value()
                                                                  .equals(configurationParameter.get());

                return defaultGeneratorMatches ? ConditionEvaluationResult.enabled("Default displayname generator match") :
                        ConditionEvaluationResult.disabled("Default displayname generator does not match");

            }else {
                return ConditionEvaluationResult.disabled("Default displayname generator is not set");
            }
        }
    }

}
