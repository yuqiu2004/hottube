# HotTube

This is a video share platform which encourage people upload their original video and share with each other.

# About project

## Source

Original from JavaEE Course Design which I cooperated with friends.
Now for job interview as a project experience, I decide to remake it and enhance function ...
But from project architecture to function depth, it will be quite different from original one.

## Dev Environment

- Oracle Java17
- Intellij IDEA 2024
- Git DockerDesktop Maven etc.

## Project Architecture

### services

1. user: Unified authentication and authorization、 User management and friend management
2. video: Video uploading, transcoding, and storage services
3. search: Video search
4. interact: Video interaction, including likes, collections, and comments
5. browse: Website browsing, including video recommendations, video retrieval, and section retrieval

## Tech Selection

- service communication: Dubbo
- registry center & config center: nacos

## Build

## Deployment

## Other
