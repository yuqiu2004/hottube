package org.ht;

import org.junit.jupiter.api.Test;

import java.io.File;

public class DirectoryLocationTest {

    @Test
    public void testDirLocation() {
        File file = new File("/ht/test");
        System.out.println(file.getAbsolutePath());
    }

}
