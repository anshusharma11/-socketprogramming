import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

class UDPServer2 {
    public static void main(final String args[]) throws Exception {
        HashMap<String, String> hostnamesToIPAddressMap = new HashMap<String, String>();
        Scanner in = new Scanner(System.in);
        final DatagramSocket serverSocket = new DatagramSocket(9677);
        int i = 0;
        while (i < 100000) {
            final byte[] receiveData = new byte[2048];
            byte[] sendData = new byte[2048];
            final DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            final String request = new String(receivePacket.getData());
            System.out.println("\nReceived message from client: " + request);
            final InetAddress IPAddress = receivePacket.getAddress();
            final int port = receivePacket.getPort();
            String response = "Send message in one of the following formats:\n\n\t (1) ADD   <HostName>  <IP Address> \n\t (2) QUERY HOSTNAME <HostName>  \n\t (3) QUERY IPADDRESS <IP Address> ";
            if(request.toUpperCase().startsWith("ADD")){
                StringTokenizer st = new StringTokenizer(request);
                if (st.hasMoreTokens()) {
                    String option = st.nextToken();
                    if(option != null && option.equalsIgnoreCase("ADD")){
                        String hostName = null;
                        String ipAddress = null;
                        if (st.hasMoreTokens()) {
                            hostName = st.nextToken();
                        }
                        if (st.hasMoreTokens()) {
                            ipAddress = st.nextToken();
                        }
                        if(hostName != null && ipAddress != null){
                            hostnamesToIPAddressMap.put(hostName, ipAddress);
                            response = "Added "+hostName+" and "+ipAddress;
                        }
                    }
                }
            } else if(request.toUpperCase().startsWith("QUERY")){
                StringTokenizer st = new StringTokenizer(request);
                if (st.hasMoreTokens()) {
                    String option = st.nextToken();
                    if(option != null && option.equalsIgnoreCase("QUERY")){
                        String type = null;
                        if (st.hasMoreTokens()) {
                            type = st.nextToken();
                        }
                        if(type != null && type.equalsIgnoreCase("HOSTNAME")){
                            if (st.hasMoreTokens()) {
                                String hostName = null;
                                hostName = st.nextToken();
                                //System.out.println("hostName = "+hostName);
                                boolean found = false;
                                for(String host_Name : hostnamesToIPAddressMap.keySet()){
                                    if(hostName.startsWith(host_Name)){
                                        response = "IP Address is "+hostnamesToIPAddressMap.get(host_Name);
                                        found = true;
                                    }
                                }
                                if (found == false) response = "Hostname does not exist.";
                                //response = "IP Address is "+hostnamesToIPAddressMap.get(hostName);
                            }
                        } else if(type != null && type.equalsIgnoreCase("IPADDRESS")){
                            if (st.hasMoreTokens()) {
                                String ipAddress = st.nextToken();
                                //System.out.println("ipAddress = "+ipAddress);
                                boolean found = false;
                                for(String hostName : hostnamesToIPAddressMap.keySet()){
                                    if(hostnamesToIPAddressMap.get(hostName).startsWith(ipAddress)){
                                        response = "Hostname is "+hostName;
                                        found = true;
                                    }
                                }
                                if (found == false) response = "IP Address does not exist.";
                            }
                        }
                    }
                }
            }
            
            
            sendData = response.getBytes();
            System.out.println("Sent message back to  client: " + response);

            final DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            i++;
        }
        serverSocket.close();
    }
}