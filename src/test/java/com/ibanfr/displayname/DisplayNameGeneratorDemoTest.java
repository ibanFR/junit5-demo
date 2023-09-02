package com.ibanfr.displayname;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Display Name Generation")
class DisplayNameGeneratorDemoTest {

    @Test
    void should_use_default_generation() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("should describe the behavior under test")
    void should_pass() {
        Assertions.assertTrue(true);
    }

    @Nested
    @DisplayName("When @DisplayName annotation is used")
    class WhenDisplayNameAnnotationIsUsed {

        @Test
        @DisplayName("should display the content of the @DisplayName annotation")
        void should_use_displayName_annotated_content(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());
            assertThat(testInfo.getDisplayName()).contains("should display the content of the @DisplayName annotation");
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class When_DisplayNameGeneration_is_ReplaceUnderscores {

        @Test
        void should_replace_underscores_with_spaces(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());
            assertThat(testInfo.getDisplayName()).contains("should replace underscores with spaces");
        }
    }

    @Nested
    @DisplayNameGeneration(ReplaceCamelCase.class)
    class WhenDisplayNameGenerationIsReplaceCamelCase {

        @Test
        void shouldReplaceCamelCase(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());
            assertThat(testInfo.getDisplayName()).contains("should replace camel case");
        }

    }

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = ReplaceCamelCase.class)
    class WhenIndicativeSentencesGenerationIsEnabled {
        @Test
        void shouldDisplayClassNameAndTestName(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());

            assertThat(testInfo.getDisplayName()).contains("When indicative sentences generation is enabled -> should display class name and test name");
        }
    }
    
}
