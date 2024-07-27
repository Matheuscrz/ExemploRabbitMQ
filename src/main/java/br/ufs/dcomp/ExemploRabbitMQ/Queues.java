package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Queues {

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("35.168.34.82"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("password"); // Alterar
    factory.setVirtualHost("/");    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    String QUEUE_NAME;
    for(int i = 1; i < 11; i++){
      QUEUE_NAME = "Queue" + i;
                           //(queue-name, durable, exclusive, auto-delete, params); 
      channel.queueDeclare(QUEUE_NAME, false,   false,     false,       null);
    }
  
    connection.close();
  }
}