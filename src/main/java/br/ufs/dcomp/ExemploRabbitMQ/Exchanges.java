package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Exchanges {

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("35.168.34.82"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("password"); // Alterar
    factory.setVirtualHost("/");    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.exchangeDeclare("E1", "fanout");
    channel.exchangeDeclare("E2", "direct");
    
    String QUEUE_NAME;
    int toggle = 0;
    for(int i = 1; i < 11; i++){
      QUEUE_NAME = "Queue" + i;
      if(i % 2 == 0){
        //Par
        if(toggle % 2 == 0){
          channel.queueBind(QUEUE_NAME, "E2", "A");
        }
        else{
          channel.queueBind(QUEUE_NAME, "E2", "B");
        }
        toggle++;
      }
      else{
        //Impar
        channel.queueBind(QUEUE_NAME, "E1", "");
      }
    }
  
    connection.close();
  }
}