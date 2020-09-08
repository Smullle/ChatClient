package server;


import java.io.IOException;
import java.net.*;

public class ChatServer {

    public static void main(String[] args) {
        MulticastSocket socket;
        DatagramPacket inPacket;
        byte[] inBuf = new byte[256];
        ;

        try {
            //Prepare to join multicast group
            socket = new MulticastSocket(8888);
            InetAddress address = InetAddress.getByName("224.2.2.3");
            socket.joinGroup(address);

            System.out.println("Server Log:");

            //Get client messages from multicast
            while (true) {
                inPacket = new DatagramPacket(inBuf, inBuf.length);
                socket.receive(inPacket);
                String msg_rec = new String(inBuf, 0, inPacket.getLength());
                System.out.println("From " + inPacket.getAddress() + " Msg : " + msg_rec);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
