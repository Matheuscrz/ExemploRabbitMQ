package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Scanner;

public class EmissorE2B {

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    Scanner scan = new Scanner(System.in);
    factory.setHost("35.168.34.82"); // Alterar
    factory.setUsername("admin"); // Alterar
    factory.setPassword("password"); // Alterar
    factory.setVirtualHost("/");    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    String message;
    
    while(true){
      message = scan.nextLine();
      if(message.equals("sair") || message.equals("Sair")){
        break;
      }
                          //  (exchange, routingKey, props, message-body             ); 
      channel.basicPublish("E2", "B", null,  message.getBytes("UTF-8"));
      System.out.println(" [x] Mensagem enviada: '" + message + "'");
    }

    channel.close();
    connection.close();
  }
}