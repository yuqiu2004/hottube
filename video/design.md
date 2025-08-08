# 视频服务

## server

- 视频上传
- 视频转码
- 视频存储 - 视频的元数据应该由browse管理

### 设计

- 需要消息队列 还有视频处理的工具
- 码率：原画、720p、360p

#### 工具

最终方案

使用javacv-platform

JavaCPP Presets For FFmpeg

- github https://github.com/bytedeco/javacpp-presets/tree/master/ffmpeg
- api 文档 http://bytedeco.org/javacpp-presets/ffmpeg/apidocs/

Apache Commons CLI + FFmpeg

- 博客
  - https://segmentfault.com/a/1190000044568143
  - https://www.zhihu.com/question/1918800274035806859

## client

## 表设计

> 暂不用