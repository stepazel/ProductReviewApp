# Use an official OpenJDK runtime as a base image
FROM openjdk:21

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the compiled classes into the container
COPY target/classes /usr/src/app

# Specify the entry point
CMD ["java", "-cp", ".", "org.example.Main"]
