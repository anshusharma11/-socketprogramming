import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.Date;
class TCPServer2 {
    public static void main(String argv[]) throws Exception       {
          String clientSentence;
          String capitalizedSentence;
          ServerSocket welcomeSocket = new ServerSocket(6789);
          
          while(true){
              
             Socket connectionSocket = welcomeSocket.accept();
             BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
             DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
             clientSentence = inFromClient.readLine();

             System.out.println("Received message from client: " + clientSentence);
             
             capitalizedSentence = clientSentence.toUpperCase() + '\n';
             System.out.println("Sent message back to  client: " + capitalizedSentence);
             outToClient.writeBytes(capitalizedSentence);
          }
     }
}


