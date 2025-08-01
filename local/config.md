# mysql

```shell
docker run -d `
  --name mysql `
  -p 33060:3306 `
  -e MYSQL_ROOT_PASSWORD=yuqiu `
  -v f:/docker/mysql8/data:/var/lib/mysql `
  mysql:8

```

# redis

> conf requirepass hottube

```shell
docker run -d --name redis `
  -p 63790:6379 `
  -v f:/docker/redis/conf/redis.conf:/usr/local/etc/redis/redis.conf `
  -v f:/docker/redis/data:/data `
  redis:latest
```

# minio

```shell
docker run -d --name minio `
  -p 9000:9000 `
  -p 9001:9001 `
  -e "MINIO_ROOT_USER=yuqiu" `
  -e "MINIO_ROOT_PASSWORD=yuqiu123" `
  -v f:\docker\minio\data:/data `
  -v f:\docker\minio\config:/root/.minio `
  minio/minio server /data --console-address ":9001"

```

# nacos

> nacos&yuqiu

```shell
docker run --name nacos-standalone-derby `
    -e MODE=standalone `
    -e NACOS_AUTH_TOKEN=SecretKey012345679810234567890123456789012345678901234567890123456789 `
    -e NACOS_AUTH_IDENTITY_KEY=hottube `
    -e NACOS_AUTH_IDENTITY_VALUE=hottube123 `
    -e TIME_ZONE='Asia/Shanghai' `
    -p 8848:8848 `
    -p 7848:8080 `
    -p 9848:9848 `
    -d nacos/nacos-server:latest

```