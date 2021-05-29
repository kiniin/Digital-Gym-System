package sample.simpleMediaPlayer;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class SimpleMediaPlayer extends AnchorPane {

//TODO 修改player.fxml 使其为自适应的大小，使用AnchorBar或者修改底部工具栏高度


    private static SimpleMediaPlayer simpleMediaPlayer;   //创建实例保存到私有域中
    private PlayerController controller;     //储存每个实例的控制器对象


    protected PlayerController getController(){   //提供控制器对象的调用接口
        return this.controller;
    }


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

    //TODO setsSize失效
    //设置播放器大小:暂不支持 popup 产生的实例调用该方法
    public void setSize(int width,int height){
        if(simpleMediaPlayer.getController().getPopup())
            return ;
        simpleMediaPlayer.getController().setMediaPlayer(width,height);
    }

    public void setMediaHeight(double height) {
        simpleMediaPlayer.getController().setSizeHeight(height);
    }
    public void setMediaWidth(double width) {
        simpleMediaPlayer.getController().setSizeWidth(width);
    }

    //实例化调用:默认大小500*400
    public static SimpleMediaPlayer  newInstance(String mediaUrl){
        return newInstance(mediaUrl,500,400);
    }
    public static SimpleMediaPlayer newInstance(String mediaUrl,int width,int height){
        simpleMediaPlayer = new SimpleMediaPlayer(mediaUrl);
        simpleMediaPlayer.getController().start(mediaUrl,false,width,height);   //非窗口化启动播放器控件
        return simpleMediaPlayer;
    }
    public void destroy(){
        simpleMediaPlayer.getController().destroy();
        simpleMediaPlayer.getChildren().clear();
        simpleMediaPlayer = null;
        System.gc();
    }
    public void changeSource(String mediaUrl,int width,int height){
        simpleMediaPlayer.getController().changeSource(mediaUrl,width,height);
    }
}
