package org.ht.util;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class VideoUtil {

    /**
     * 截取视频某一帧保存为图片
     * @param videoPath 视频路径
     * @param imagePath 图片输出路径（支持 jpg/png）
     * @param frameTime 截取时间（秒）
     */
    public static void captureFrame(String videoPath, String imagePath, double frameTime) throws Exception {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath)) {
            grabber.start();
            grabber.setTimestamp((long) (frameTime * 1_000_000)); // 秒转微秒

            Frame frame = grabber.grabImage();
            if (frame != null) {
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage img = converter.getBufferedImage(frame);
                ImageIO.write(img, imagePath.substring(imagePath.lastIndexOf(".") + 1), new File(imagePath));
            }
            grabber.stop();
        }
    }

    /**
     * 转码视频（修改分辨率、码率）
     * @param inputPath 输入视频
     * @param outputPath 输出视频
     * @param width 目标宽
     * @param height 目标高
     * @param bitrate 视频码率（bps）
     */
    public static void transcodeVideo(String inputPath, String outputPath, int width, int height, int bitrate) throws Exception {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputPath)) {
            grabber.start();

            try (FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputPath, width, height, grabber.getAudioChannels())) {
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
                recorder.setFormat("mp4");
                recorder.setFrameRate(grabber.getFrameRate());
                recorder.setVideoBitrate(bitrate);

                recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
                recorder.setAudioBitrate(grabber.getAudioBitrate());

                recorder.start();

                Frame frame;
                while ((frame = grabber.grab()) != null) {
                    if (frame.image != null) {
                        recorder.record(frame);
                    } else if (frame.samples != null) {
                        recorder.recordSamples(frame.sampleRate, frame.audioChannels, frame.samples);
                    }
                }

                recorder.stop();
            }

            grabber.stop();
        }
    }


    /**
     * 转换视频为 HLS 格式
     * @param inputPath 输入视频路径
     * @param outputDir 输出目录（会生成 index.m3u8 + ts 文件）
     * @param segmentTime 每个 ts 分片的时长（秒）
     */
    public static void convertToHLS(String inputPath, String outputDir, int segmentTime) throws Exception {
        File outDir = new File(outputDir);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        String m3u8Path = outputDir + File.separator + "index.m3u8";

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputPath)) {
            grabber.start();

            try (FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(m3u8Path,
                    grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels())) {

                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
                recorder.setFormat("hls");

                recorder.setOption("hls_time", String.valueOf(segmentTime)); // 每片时长
                recorder.setOption("hls_list_size", "0"); // 列出所有分片
                recorder.setOption("hls_segment_filename", outputDir + File.separator + "segment_%03d.ts");

                recorder.setFrameRate(grabber.getFrameRate());
                recorder.setVideoBitrate(grabber.getVideoBitrate());

                recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
                recorder.setAudioBitrate(grabber.getAudioBitrate());

                recorder.start();

                Frame frame;
                while ((frame = grabber.grab()) != null) {
                    recorder.record(frame);
                }

                recorder.stop();
            }

            grabber.stop();
        }
    }

}

