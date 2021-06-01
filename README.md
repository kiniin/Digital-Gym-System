# Digital Gym System
> This is the Group 82 Project of EBU6304-Software Engineer， Our program aimed at provide an efficient and multi-functional gymnasium management system 🏈, its functions include watching teaching videos📀, analyzing your study status&#x1F4C8;, booking private trainer courses, and private trainer can also use our coaching system to add required courses.
## The installation procedure

### The basic requirement of our software

1. maven-3.6.3
2. JDK 13

### use maven
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.jianjian</groupId>
    <artifactId>code01</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.9.6</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.9.6</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.6</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>16</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>16</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.openjfx/javafx-media -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-media</artifactId>
            <version>16</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-graphics</artifactId>
            <version>16</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.kordamp.ikonli/ikonli-javafx -->
        <dependency>
            <groupId>org.kordamp.ikonli</groupId>
            <artifactId>ikonli-javafx</artifactId>
            <version>12.2.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.kordamp.ikonli/ikonli-fontawesome-pack -->
        <dependency>
            <groupId>org.kordamp.ikonli</groupId>
            <artifactId>ikonli-fontawesome-pack</artifactId>
            <version>12.2.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.jfoenix/jfoenix -->
        <dependency>
            <groupId>com.jfoenix</groupId>
            <artifactId>jfoenix</artifactId>
            <version>9.0.10</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.testfx/testfx-junit -->
        <dependency>
            <groupId>org.testfx</groupId>
            <artifactId>testfx-junit</artifactId>
            <version>4.0.15-alpha</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.testfx/testfx-junit5 -->
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.13.2</version>
        </dependency>
    </dependencies>
    <properties>
        <maven.compiler.source>13</maven.compiler.source>
        <maven.compiler.target>13</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <!-- The file want to join when you compile -->
                    <include>**/*.properties</include>
                    <include>**/fxml/*.fxml</include>
                    <include>**/css/*.css</include>
                    <include>**/pic/*.jpg</include>
                    <include>**/pic/*.jpeg</include>
                    <include>**/pic/*.png</include>
                    <include>**/month/*.png</include>
                    <include>**/month/*.jpg</include>
                    <include>**/video/*.mp4</include>
                    <include>**/simpleMediaPlayer/*.fxml</include>
                    <include>**/simpleMediaPlayer/icon/*.png</include>
                    <include>**/videoListComponent/*.fxml</include>
                    <include>**/videoListComponent/*.css</include>
                    <include>**/coachListComponent/*.fxml</include>
                    <include>**/coachListComponent/*.css</include>
                    <include>**/orderListComponent/*.fxml</include>
                    <include>**/orderListComponent/*.css</include>
                    <include>**/timePickerComponent/*.css</include>
                    <include>**/timePickerComponent/*.fxml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <release>11</release>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.6</version>
                <executions>
                    <execution>
                        <!-- Default configuration for running -->
                        <id>default-cli</id>
                        <configuration>
                            <options>
                                <option>--add-opens</option>
                                <option>javafx.graphics/javafx.css=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.base/com.sun.javafx.runtime=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.base/com.sun.javafx.binding=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.base/com.sun.javafx.event=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED</option>
                                <option>--add-exports</option>
                                <option>javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix</option>
                                <option>--add-exports</option>
                                <option>javafx.controls/com.sun.javafx.scene.control=com.jfoenix</option>
                                <option>--add-exports</option>
                                <option>javafx.base/com.sun.javafx.binding=com.jfoenix</option>
                                <option>--add-exports</option>
                                <option>javafx.graphics/com.sun.javafx.stage=com.jfoenix</option>
                                <option>--add-exports</option>
                                <option>javafx.base/com.sun.javafx.event=com.jfoenix</option>
                            </options>
                            <mainClass>sample.Main</mainClass>
                        </configuration>
                    </execution>
                    <execution>
                        <!-- Configuration for debugging -->
                        <id>debug</id>
                        <configuration>
                            <options>
                                <option>-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:8000</option>
                                <option>--add-opens</option>
                                <option>javafx.graphics/javafx.css=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.base/com.sun.javafx.runtime=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.base/com.sun.javafx.binding=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.base/com.sun.javafx.event=ALL-UNNAMED</option>
                                <option>--add-opens</option>
                                <option>javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED</option>
                                <option>--add-exports</option>
                                <option>javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix</option>
                                <option>--add-exports</option>
                                <option>javafx.controls/com.sun.javafx.scene.control=com.jfoenix</option>
                                <option>--add-exports</option>
                                <option>javafx.base/com.sun.javafx.binding=com.jfoenix</option>
                                <option>--add-exports</option>
                                <option>javafx.graphics/com.sun.javafx.stage=com.jfoenix</option>
                                <option>--add-exports</option>
                                <option>javafx.base/com.sun.javafx.event=com.jfoenix</option>
                            </options>
                            <mainClass>sample.Main</mainClass>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer
                                implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <!-- 加载主类 -->
                                    <mainClass>sample.Main</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```
### The command line of our software

**Make sure your path of cmd in the root file our system and type the comamnd**

```
mvn clean javafx:run
```

Or you can run the running.bat to run our program directly

