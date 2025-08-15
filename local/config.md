# mysql

navicat 访问

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

控制台：http://localhost:9001

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

设置桶权限 

参考 https://juejin.cn/post/7509891489692172351

```shell
mc alias set myminio http://localhost:9000 yuqiu yuqiu123

mc anonymous set download myminio/ht-user
mc anonymous set download myminio/ht-video

```

# nacos

> username=nacos & password=yuqiu
控制台：http://localhost:7848

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

# rabbitmq

控制台：http://localhost:15672

```shell
docker run -d `
  --name rabbitmq `
  -p 5672:5672 `
  -p 15672:15672 `
  bitnami/rabbitmq:latest

# set user & password
docker exec -it rabbitmq bash
rabbitmqctl add_user yuqiu yuqiu
rabbitmqctl set_user_tags yuqiu administrator

```

# mongodb

navicat连接

```shell
docker run -d `
  --name mongodb `
  -p 27017:27017 `
  -e MONGO_ROOT_USERNAME=root `
  -e MONGO_ROOT_PASSWORD=yuqiu123 `
  -v F:\docker\mongodb\data:/bitnami/mongodb/data/db `
  -v F:\docker\mongodb\conf\mongodb.conf:/opt/bitnami/mongodb/conf/mongodb.conf `
  bitnami/mongodb:latest
  
```

# ElasticSearch / ELK

```shell

```

# Nginx

```shell
docker run -d `
  --name nginx `
  -p 8090:80 `
  -v F:\docker\nginx\html:/usr/share/nginx/html `
  -v F:\docker\nginx\nginx.conf:/etc/nginx/nginx.conf `
  nginx:stable-perl

```
