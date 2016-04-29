import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.Date;
class TCPClient1 {  
    public static void main(String argv[]) throws Exception  {
   
    //A data buffer of 1 KB size
    String oneKB = "This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB. This String is 1024 characters long and has size of 1KB";

    //Data buffers of different sizes
    int bufferSizes[] = {1, 2, 4, 8, 16, 32};

    System.out.println("\n\nWith fixed small data size and increasing buffer size.\n");
    //Loop for sending data buffers of different sizes
    for(int buffer=0; buffer < bufferSizes.length; buffer++){
        StringBuilder inputString = new StringBuilder();
        
        inputString.append(oneKB);
        inputString.append('\n');
        
        Date beforeTime = new Date();
        for(int timesToSend = 0; timesToSend < 500; timesToSend++){
            Socket clientSocket = new Socket("localhost", 6789);
            clientSocket.setSendBufferSize(bufferSizes[buffer]*1024);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(inputString.toString());
            clientSocket.close();
        }
        Date afterTime = new Date();
        long avgTime = ((afterTime.getTime()-beforeTime.getTime())/500);
        System.out.println("Data= "+humanReadableByteCount(inputString.toString().getBytes("UTF-8").length, false)+"\tBuffer="+humanReadableByteCount(bufferSizes[buffer]*1024, false)+"\tTime before sending 500 msgs= "+new Timestamp(beforeTime.getTime()).toString()+"\tTime after sending 500 msgs= " + new Timestamp(afterTime.getTime()).toString()+"\tAvg time/msg= "+avgTime+" msec.");
    }

    System.out.println("\n\nWith fixed large data size and increasing buffer size.\n");
    //Loop for sending data buffers of different sizes
    for(int buffer=0; buffer < bufferSizes.length; buffer++){
        StringBuilder inputString = new StringBuilder();
        
        //Build the input string for the specified size
        for(int j=0; j < bufferSizes[5]; j++){
            inputString.append(oneKB);
        }
        inputString.append('\n');
        
        Date beforeTime = new Date();
        for(int timesToSend = 0; timesToSend < 500; timesToSend++){
            Socket clientSocket = new Socket("localhost", 6789);
            clientSocket.setSendBufferSize(bufferSizes[buffer]*1024);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(inputString.toString());
            clientSocket.close();
        }
        Date afterTime = new Date();
        long avgTime = ((afterTime.getTime()-beforeTime.getTime())/500);
        System.out.println("Data= "+humanReadableByteCount(inputString.toString().getBytes("UTF-8").length, false)+"\tBuffer="+humanReadableByteCount(bufferSizes[buffer]*1024, false)+"\tTime before sending 500 msgs= "+new Timestamp(beforeTime.getTime()).toString()+"\tTime after sending 500 msgs= " + new Timestamp(afterTime.getTime()).toString()+"\tAvg time/msg= "+avgTime+" msec.");
    }
    
    System.out.println("\n\nWith increasing data size and increasing buffer size.\n");
    //Loop for sending data buffers of different sizes
    for(int buffer=0; buffer < bufferSizes.length; buffer++){
        StringBuilder inputString = new StringBuilder();
        
        //Build the input string for the specified size
        for(int j=0; j < bufferSizes[buffer]; j++){
            inputString.append(oneKB);
        }
        inputString.append('\n');
        
        Date beforeTime = new Date();
        for(int timesToSend = 0; timesToSend < 500; timesToSend++){
            Socket clientSocket = new Socket("localhost", 6789);
            clientSocket.setSendBufferSize(bufferSizes[buffer]*1024);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(inputString.toString());
            clientSocket.close();
        }
        Date afterTime = new Date();
        long avgTime = ((afterTime.getTime()-beforeTime.getTime())/500);
        System.out.println("Data= "+humanReadableByteCount(inputString.toString().getBytes("UTF-8").length, false)+"\tBuffer="+humanReadableByteCount(bufferSizes[buffer]*1024, false)+"\tTime before sending 500 msgs= "+new Timestamp(beforeTime.getTime()).toString()+"\tTime after sending 500 msgs= " + new Timestamp(afterTime.getTime()).toString()+"\tAvg time/msg= "+avgTime+" msec.");
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















