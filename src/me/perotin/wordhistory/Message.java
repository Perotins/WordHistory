package me.perotin.wordhistory;

public class Message {

    private String msg;
    private String date;

    public Message(String msg, String date){
        this.msg = msg;
        this.date = date;
    }


    public String getMessage(){
        return this.msg;
    }

    public String getDate(){
        return this.date;
    }


    public boolean equals(Message message){
        return msg.equals(message.getMessage()) && date.equals(message.getDate());
    }


}
