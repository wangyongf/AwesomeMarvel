package com.yongf.android.contactsapp;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * @author scottwang1996@qq.com
 * @since 2017/12/30.
 */

public class TestMyRobolectricRunner extends RobolectricTestRunner {

    /**
     * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file
     * and res directory by default. Use the {@link Config} annotation to configure.
     *
     * @param testClass the test class to be run
     * @throws InitializationError if junit says so
     */
    public TestMyRobolectricRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    // TODO: 2018/11/22 AndroidManifest: app/build/intermediates/bundles/debug/AndroidManifest.xml
    @Override
    protected Config buildGlobalConfig() {
        return new Config.Builder()
                .setApplication(TestMyApplication.class)
                .setConstants(BuildConfig.class)
                .setManifest("./AndroidManifest.xml")
                .setPackageName("com.yongf.android.contactsapp")
                .build();
    }
}
