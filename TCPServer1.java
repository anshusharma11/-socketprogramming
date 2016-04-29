import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.Date;
class TCPServer1 {
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);
        int bufferSizes[] = {1, 2, 4, 8, 16, 32};
        int i=0, buffer=0;
        System.out.println("\n\n");
        boolean bufferChanged = true;
        while(true){
            if(bufferChanged == true){
                System.out.println("Buffer="+humanReadableByteCount(bufferSizes[buffer]*1024, false)+"\t From Time = "+new Timestamp(new Date().getTime()).toString());
            }
            ++i;
            bufferChanged = false;
            Socket connectionSocket = welcomeSocket.accept();
            connectionSocket.setReceiveBufferSize(bufferSizes[buffer]*1024);
            connectionSocket.setSendBufferSize(bufferSizes[buffer]*1024);
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            inFromClient.readLine();
            if (i % 500 == 0 && i < 9000){
                buffer++;
                bufferChanged = true;
            }
            if (i % 3000 == 0){
                buffer = 0;
                bufferChanged = true;
            }
        }
    }
    /*
    * Calculates human readable byte count. 
    */
    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
