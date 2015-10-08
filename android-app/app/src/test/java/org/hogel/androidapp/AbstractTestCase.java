package org.hogel.androidapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AbstractTestCase {

    private static final String MOCK_API_URL = "http://127.0.0.1:9292";

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws Exception {
        setupMockApiClient();
    }

    private void setupMockApiClient() throws ReflectiveOperationException {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == ApiClient.class) {
                field.set(this, new MockApiClient(MOCK_API_URL, findMockApiCassette()));
            }
        }
    }

    private String findMockApiCassette() throws NoSuchMethodException {
        Method testMethod = this.getClass().getDeclaredMethod(testName.getMethodName());
        VcrCassette annotation = testMethod.getAnnotation(VcrCassette.class);
        if (annotation == null) {
            annotation = getClass().getAnnotation(VcrCassette.class);
        }
        return annotation == null ? "default" : annotation.value();
    }
}
