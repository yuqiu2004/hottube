# mysql

```shell
docker run -d `
  --name mysql `
  -p 33060:3306 `
  -e MYSQL_ROOT_PASSWORD=yuqiu `
  -v f:/docker/mysql8/data:/var/lib/mysql `
  mysql:8

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