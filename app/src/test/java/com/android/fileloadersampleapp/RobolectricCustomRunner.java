package com.android.fileloadersampleapp;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.res.FsFile;

public class RobolectricCustomRunner extends RobolectricTestRunner {

    public RobolectricCustomRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        AndroidManifest appManifest = super.getAppManifest(config);
        FsFile androidManifestFile = appManifest.getAndroidManifestFile();

        if (androidManifestFile.exists()) {
            return appManifest;
        } else {
            androidManifestFile = FileFsFile.from(getModuleRootPath(config), appManifest.getAndroidManifestFile().getPath().replace("manifests/full", "manifests/aapt"));
            return new AndroidManifest(androidManifestFile, appManifest.getResDirectory(), appManifest.getAssetsDirectory());
        }
    }

    private String getModuleRootPath(Config config) {
        String moduleRoot = config.constants().getResource("").toString().replace("file:", "");
        return moduleRoot.substring(0, moduleRoot.indexOf("/build"));
    }

}
