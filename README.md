# Digital-Gym-System
> This is the Group Project of EBU6304-Software Engineer
## The installation procedure
### use maven
1. download maven
2. new pom.xml
3. change the pom as
- jdk 1.9 and later
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>{self info}</groupId>
    <artifactId>{self info}</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>11</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>11</version>
        </dependency>
    </dependencies>
    <properties>
        <maven.compiler.source>13</maven.compiler.source>
        <maven.compiler.target>13</maven.compiler.target>
<!--        要改成utf-8否则不支持中文-->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <build>
        <resources>
            <resource>
                <!-- 这里是放在 src/main/java-->
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/fxml/*.fxml</include>
                    <include>**/css/*.css</include>
                    <include>**/pic/*.jpg</include>
                    <include>**/pic/*.png</include>
                    <!-- 如果想要弄个包名专门放fxml文件，像上一行这样添加设置 -->
                    <!-- 之后，使用getResource("fxml/xx.fxml")这样子 -->
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
        <finalName>testMaven.jar</finalName><!-- 导出jar的名字 -->
        <plugins>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.1</version>
                <configuration>
                    <mainClass>sample.Main</mainClass>
                </configuration>
            </plugin>

        </plugins>
    </build>
</project>
```
- jdk 1.8( already have javafx sdk)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>{self info}</groupId>
    <artifactId>{self info}</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
<!--        要改成utf-8否则不支持中文-->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <build>
        <resources>
            <resource>
                <!-- 这里是放在 src/main/java-->
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/fxml/*.fxml</include>
                    <include>**/css/*.css</include>
                    <include>**/pic/*.jpg</include>
                    <include>**/pic/*.png</include>
                    <!-- 如果想要弄个包名专门放fxml文件，像上一行这样添加设置 -->
                    <!-- 之后，使用getResource("fxml/xx.fxml")这样子 -->
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
        <finalName>Digital-Gym-System<</finalName><!-- 导出jar的名字 -->
        <plugins>
            <plugin>
                <groupId>com.zenjava</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>8.8.3</version>
                <configuration>
                    <mainClass>sample.Main</mainClass>
                    <vendor>Digital-Gym-System</vendor>
                </configuration>
            </plugin>

        </plugins>
    </build>
</project>
```
4. in command line run mvn javafx:run (1.8) mvn jfx:run(1.9 and later) or use idea to use maven plugins
### use sdk to run our project
1. download javafx sdk（already in javafx-sdk branch）
2. In project structure of idea, dd javafx sdk's lib package in libraries
![img.png](readmeResource/img.png)
3. run main in Main class
