import java.net.*;
import java.io.*;
import java.util.*;

class MyClient1 {

    public static void main(String args[]) throws Exception {
        Socket s = new Socket("127.0.0.1", 50000);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader din = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String str;

        dout.write(("HELO\n").getBytes());
        dout.flush();
        str = din.readLine();
        System.out.println("Server Says: " + str);

        String username = System.getProperty("user.name");
        dout.write(("AUTH " + username + "\n").getBytes());
        dout.flush();
        str = din.readLine();
        System.out.println("Server Says: " + str);

        dout.write(("REDY\n").getBytes());
        dout.flush();
        str = din.readLine();
        System.out.println("Server Says: " + str);
    }
}