package com.ibanfr.displayname;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

public class DisplayNameGeneratorExecutionCondition implements ExecutionCondition {

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
