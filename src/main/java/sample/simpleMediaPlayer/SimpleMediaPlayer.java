package sample.simpleMediaPlayer;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


/**
 * This class loads the player's controller and the player's FXML file,
 * with the goal of creating a well-encapsulated and initialized instance
 * of the player
 *
 * @author Xiaojian Qi
 * @iteration 2.0
 */
public class SimpleMediaPlayer extends AnchorPane {



    private static SimpleMediaPlayer simpleMediaPlayer;   //创建实例保存到私有域中
    private PlayerController controller;     //储存每个实例的控制器对象

    /**
     * Gets the constructor for a player
     */

    protected PlayerController getController(){   //提供控制器对象的调用接口
        return this.controller;
    }

    /**
     * The constructor is private, the instance is kept in the static domain,
     * and only static calls are made to the outside world. The controller
     * and FXML files can be loaded and added to the root node
     *
     * @param mediaUrl The URL for this player.
     */
    //构造函数私有，实例保存在静态域，只向外部提供静态调用
    private SimpleMediaPlayer(String mediaUrl){
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("player.fxml"));
            Parent root = fxmlloader.load();   //将fxml节点添加到根节点中
            controller = fxmlloader.getController();
            this.getChildren().add(root);   //主类节点加入根节点
            System.out.println("player add in the root");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Set the height of the player
     *
     * @param height The height for this player.
     */
    public void setMediaHeight(double height) {
        simpleMediaPlayer.getController().setSizeHeight(height);
    }
    public void setMediaWidth(double width) {
        simpleMediaPlayer.getController().setSizeWidth(width);
    }

    /**
     * The default size of the constructor call
     *
     * @param mediaUrl The URL for this player.
     */
    //实例化调用:默认大小500*400
    public static SimpleMediaPlayer  newInstance(String mediaUrl){
        return newInstance(mediaUrl,500,400);
    }

    /**
     * Initializes an instance object of the given size of the player
     *
     * @param mediaUrl The URL for this player.
     * @param height The height of this player.
     * @param width The width of this player.
     */
    public static SimpleMediaPlayer newInstance(String mediaUrl,int width,int height){
        simpleMediaPlayer = new SimpleMediaPlayer(mediaUrl);
        simpleMediaPlayer.getController().start(mediaUrl,width,height);   //非窗口化启动播放器控件
        return simpleMediaPlayer;
    }

    /**
     * Invokes the controller's destruction routine and clears all child nodes mounted on the root node
     */
    public void destroy(){
        simpleMediaPlayer.getController().destroy();
        simpleMediaPlayer.getChildren().clear();
        simpleMediaPlayer = null;
        System.gc();
    }
    /**
     * Invoke the changeSource progress of the media player
     *
     *
     * @param mediaUrl The video url for this player
     * @param width The width of this player
     * @param height The height of this player
     */
    public void changeSource(String mediaUrl,int width,int height){
        simpleMediaPlayer.getController().changeSource(mediaUrl,width,height);
    }
}
