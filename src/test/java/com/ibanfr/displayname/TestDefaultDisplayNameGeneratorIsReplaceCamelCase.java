package com.ibanfr.displayname;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.assertj.core.api.Assertions.assertThat;

@EnabledIfDefaultDisplayNameGenerator("com.ibanfr.displayname.ReplaceCamelCase")
class TestDefaultDisplayNameGeneratorIsReplaceCamelCase {

    @Test
    void shouldReplaceCamelCase(TestInfo testInfo){
        assertThat(testInfo.getDisplayName()).contains("should replace camel case");
    }
}
