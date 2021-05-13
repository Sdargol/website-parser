package com.sdargol.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class StorageFolderUtil {
    private static final String mainDir;
    private static final String defaultDir;
    private static final Logger logger = LoggerFactory.getLogger(StorageFolderUtil.class);

    static {
        mainDir = System.getProperty("user.dir");
        defaultDir = "download";
    }

    private static String generatePath(String name) {
        return mainDir + "\\" + name;
    }

    public static String getDefaultPath() {
        return generatePath(defaultDir);
    }

    private static Path createDirHelper(String name) {
        String path = name.equals(defaultDir) ? getDefaultPath() : generatePath(name);
        File folder = new File(path);

        try {
            if (folder.mkdir()) {
                logger.info("Directory Created ");
            } else {
                logger.info("Directory is not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Paths.get(folder.getPath());
    }

    public static Path createDir() {
        return createDirHelper(defaultDir);
    }

    public static Path createDir(String folder) {
        return createDirHelper(folder);
    }
}
