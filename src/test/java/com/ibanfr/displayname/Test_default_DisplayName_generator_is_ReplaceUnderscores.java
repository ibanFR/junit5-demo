package com.ibanfr.displayname;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.assertj.core.api.Assertions.assertThat;

@EnabledIfDefaultDisplayNameGenerator("org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores")
class Test_default_DisplayName_generator_is_ReplaceUnderscores {

    @Test
    void should_replace_spaces_with_underscores(TestInfo testInfo){
        assertThat(testInfo.getDisplayName()).contains("should replace spaces with underscores");
    }

}
