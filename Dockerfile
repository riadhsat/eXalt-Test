FROM openjdk:17-jdk-alpine

ARG artifact_id="eXalt-Test"
ARG application_root_dir="/app"
COPY target/${ARTIFACT_ID}*.jar ${application_root_dir}/app.jar
WORKDIR ${application_root_dir}
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]