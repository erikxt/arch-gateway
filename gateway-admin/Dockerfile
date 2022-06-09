FROM registry.cn-hangzhou.aliyuncs.com/alicbim/openjdk:8-maven as builder

WORKDIR /app

COPY pom.xml ./
COPY openapi-gateway/pom.xml openapi-gateway/
COPY gateway-admin/pom.xml gateway-admin/

ARG SUB_MODULE=gateway-admin
RUN cd ${SUB_MODULE} && mvn dependency:resolve && mvn dependency:resolve-plugins
COPY ${SUB_MODULE} ${SUB_MODULE}
RUN mvn install -pl ${SUB_MODULE} -am -Dmaven.test.skip=true

FROM registry.cn-hangzhou.aliyuncs.com/alicbim/openjdk:8-jdk-alpine as prod
ARG SUB_MODULE=gateway-admin

COPY --from=builder /app/${SUB_MODULE}/target/*.jar /opt/

COPY ${SUB_MODULE}/docker-entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
