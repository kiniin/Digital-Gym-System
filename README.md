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
            <version>11</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>11</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.openjfx/javafx-media -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-media</artifactId>
            <version>11</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-graphics</artifactId>
            <version>11</version>
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


    </dependencies>
    <properties>
        <maven.compiler.source>13</maven.compiler.source>
        <maven.compiler.target>13</maven.compiler.target>
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
                    <include>**/pic/*.jpeg</include>
                    <include>**/pic/*.png</include>
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


                    <!-- 如果想要弄个包名专门放fxml文件，像上一行这样添加设置 -->
                    <!-- 之后，使用getResource("fxml/xx.fxml")这样子 -->
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
                    <stripDebug>true</stripDebug>
                    <compress>2</compress>
                    <noHeaderFiles>true</noHeaderFiles>
                    <noManPages>true</noManPages>
                    <launcher>hellofx</launcher>
                    <jlinkImageName>hello</jlinkImageName>
                    <jlinkZipName>hellozip</jlinkZipName>
                    <mainClass>sample.Main</mainClass>
                </configuration>
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
4. in command line run mvn javafx:run (1.8) mvn jfx:run(1.9 and later) or use idea to use maven plugins
### use sdk to run our project
1. download javafx sdk（already in javafx-sdk branch）
2. In project structure of idea, dd javafx sdk's lib package in libraries
![img.png](readmeResource/img.png)
3. run main in Main class
