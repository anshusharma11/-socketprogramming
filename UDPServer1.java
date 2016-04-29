import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer1 {
    public static void main(final String args[]) throws Exception {
        final DatagramSocket serverSocket = new DatagramSocket(9876);
        int i = 0;
        while (i < 100000) {
            final byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            final DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            final String clientSentence = new String(receivePacket.getData());
            System.out.println("\nReceived message from client: " + clientSentence);

            final InetAddress IPAddress = receivePacket.getAddress();
            final int port = receivePacket.getPort();
            final String capitalizedSentence = clientSentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            System.out.println("Sent message back to  client: " + capitalizedSentence);

            final DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            i++;
        }
        serverSocket.close();
    }
}