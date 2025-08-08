package org.ht;

import org.ht.util.VideoUtil;
import org.junit.jupiter.api.Test;

public class VideoUtilTest {

    private String videoPath = "C:/Users/SN/Desktop/example.mp4";
    private String outputPath = "C:/Users/SN/Desktop/transcode.mp4";
    private String imagePath = "C:/Users/SN/Desktop/frame.jpg";
    private String outputDir = "C:/Users/SN/Desktop/hls";
    private int width = 720;
    private int height = 480;
    private int bitrate = 1000;
    private int segmentTime = 5;

    @Test
    public void testCaptureFrame() throws Exception {
        VideoUtil.captureFrame(videoPath, imagePath, 0.1);
    }

    @Test
    public void testTranscodeVideo() throws Exception {
        VideoUtil.transcodeVideo(videoPath, outputPath, width, height, bitrate);
    }

    @Test
    public void testConvertToHLS() throws Exception {
        VideoUtil.convertToHLS(videoPath, outputDir, segmentTime);
    }

}
