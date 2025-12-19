package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class TestLoggerExtension
        implements BeforeEachCallback, AfterEachCallback {

    private static final Logger log = LogManager.getLogger(TestLoggerExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("START TEST: {}#{} => {}",
                context.getRequiredTestClass().getSimpleName(),
                context.getRequiredTestMethod().getName(),
                context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        log.info("END TEST:   {}#{} => {} [{}]",
                context.getRequiredTestClass().getSimpleName(),
                context.getRequiredTestMethod().getName(),
                context.getDisplayName(),
                context.getExecutionException().isPresent()
                        ? "FAILED"
                        : "PASSED",
                context.getDisplayName());
    }
}

