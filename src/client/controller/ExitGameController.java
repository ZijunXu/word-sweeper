package client.controller;


import client.view.Application;
import client.model.Model;
import xml.Message;
/**
 *@author Zijun Xu
 */
public class ExitGameController {

    Application app;
    Model model;
    public ExitGameController(Application app, Model model){
        this.app = app;
        this.model = model;
    }
    public void process() {

        String xmlString = Message.requestHeader() + String.format("<exitGameRequest name='%s' gameId='%s'/></request>",
                model.getGame().getMyName(),
                model.getGame().getRoomID());

        Message m = new Message (xmlString);
        System.out.println(m.toString());
        app.getServerAccess().sendRequest(m);
    }
}
