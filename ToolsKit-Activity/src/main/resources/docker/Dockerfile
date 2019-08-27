#1.FROM:指明当前镜像继承的基镜像,编译当前镜像时候会自动下载基镜像
#2.MAINTAINER:当前镜像的作者和邮箱,使用空格隔开
#3.VOLUME:挂载目录
#4.ADD:从当前工作目录复制文件到镜像目录中并重新命名
#5.RUN:在当前镜像上执行Linux命令
#6.EXPOSE:监听的端口号
#7.ENTRYPOINT:让容器像一个可执行程序一样运行
FROM java:8
VOLUME /tmp
ADD activiti-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 18847
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]