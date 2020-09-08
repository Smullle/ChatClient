package client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client {

    public static void main(String[] args) {
        MulticastSocket socket;
        DatagramPacket outPacket;
        byte[] outBuf;
        final int PORT = 8888;
        try {
            //Start listener thread
            ClientListener clientThread = new ClientListener();
            clientThread.start();

            //Prepare to join multicast group
            socket = new MulticastSocket(8888);
            InetAddress address = InetAddress.getByName("224.2.2.3");
            socket.joinGroup(address);
            String msg;

            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                msg = br.readLine();
                outBuf = msg.getBytes();

                //Send to multicast IP address and port
                outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);

                socket.send(outPacket);
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
