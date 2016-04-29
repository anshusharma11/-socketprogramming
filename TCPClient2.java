import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.Date;
class TCPClient2 {  
    public static void main(String argv[]) throws Exception  {
        String modifiedSentence;
        while(true){
            System.out.print("\nSend a new message to server : ");
            BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
            String input = inFromUser.readLine();

            Socket clientSocket = new Socket("localhost", 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.writeBytes(input + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("Received message from server: " + modifiedSentence);
            clientSocket.close();
        }
    }
}
























