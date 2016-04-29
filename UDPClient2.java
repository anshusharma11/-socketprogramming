import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient2 {
    public static void main(final String args[]) throws Exception {
        while (true) {
            System.out.print("\nSend a new message to server : ");
            final BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            final DatagramSocket clientSocket = new DatagramSocket();
            final InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData = new byte[2048];
            final byte[] receiveData = new byte[2048];
            final String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            final DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, 9677);
            clientSocket.send(sendPacket);
            
            final DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            final String modifiedSentence = new String(receivePacket.getData());
            System.out.println("Received message from server : " + modifiedSentence);
            clientSocket.close();
        }
    }
}


