# Use an official OpenJDK runtime as a base image
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the compiled classes into the container
COPY target/classes /app/classes

EXPOSE 8080

# Specify the entry point
CMD ["java", "-cp", "/app/classes", "com.isep.acme.ACMEApplication"]
