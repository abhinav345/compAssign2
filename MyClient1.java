import java.net.*;
import java.io.*;
import java.util.*;

class MyClient1 {

    public static void main(String args[]) throws Exception {
        Socket s = new Socket("127.0.0.1", 50000);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader din = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String str;

        System.out.println("Client Says: HELO");
        dout.write(("HELO\n").getBytes());
        dout.flush();
        str = din.readLine();
        System.out.println("Server Says: " + str);

        System.out.println("Client Says: AUTH");
        String username = System.getProperty("user.name");
        dout.write(("AUTH " + username + "\n").getBytes());
        dout.flush();
        str = din.readLine();
        System.out.println("Server Says: " + str);

        System.out.println("Client Says: REDY ");
        dout.write(("REDY\n").getBytes());
        dout.flush();
        str = din.readLine();
        System.out.println("Server Says: " + str);
        String[] jobSplit = str.split(" ");
        String jobType = jobSplit[0];

      //  while(!(jobType.equalsIgnoreCase("none"))) {
            System.out.print("Client Says: ");
            System.out.println("GETS Capable " + jobSplit[4] + "  " + jobSplit[5] + " " + jobSplit[6]);
            dout.write(("GETS Capable " + jobSplit[4] + " " + jobSplit[5] + " " + jobSplit[6] + "\n").getBytes());
            dout.flush();
            str = din.readLine();
            System.out.println("Server says " + str);
            String[] data = str.split(" ");
            int numServers = Integer.parseInt(data[1]);

            dout.write(("OK\n").getBytes());
            dout.flush();

            System.out.println("Servers are: ");
            for(int i = 0;i < numServers; i++) {
                str = din.readLine();
                System.out.println("Server " + (i+1) + " is " + str);
            }

            dout.write(("OK\n").getBytes());
            dout.flush();
            str = din.readLine();
            System.out.println("Server Says: " + str);

            dout.write(("EJWT tiny 0 \n").getBytes());
            str = din.readLine();
            System.out.println("Server says(Time): " + str);

            dout.write(("SCHD 0 tiny 0 \n").getBytes());
            str = din.readLine();
            System.out.println("Server says(Schd Info): " + str);

            System.out.println("Client Says: REDY ");
            dout.write(("REDY\n").getBytes());
            dout.flush();
            str = din.readLine();
            System.out.println("Server Says: " + str);
            jobSplit = str.split(" ");
            jobType = jobSplit[0];

            System.out.print("Client Says: ");
            System.out.println("GETS Capable " + jobSplit[4] + "  " + jobSplit[5] + " " + jobSplit[6]);
            dout.write(("GETS Capable " + jobSplit[4] + " " + jobSplit[5] + " " + jobSplit[6] + "\n").getBytes());
            dout.flush();
            str = din.readLine();
            System.out.println("Server says " + str);
            data = str.split(" ");
            numServers = Integer.parseInt(data[1]);

            dout.write(("OK\n").getBytes());
            dout.flush();

            System.out.println("Servers are: ");
            for(int i = 0;i < numServers; i++) {
                str = din.readLine();
                System.out.println("Server " + (i+1) + " is " + str);
            }

            dout.write(("OK\n").getBytes());
            dout.flush();
            str = din.readLine();
            System.out.println("Server Says: " + str);

            dout.write(("EJWT tiny 0 \n").getBytes());
            str = din.readLine();
            System.out.println("Server says(Time): " + str);

            dout.write(("EJWT tiny 1 \n").getBytes());
            str = din.readLine();
            System.out.println("Server says(Time): " + str);

            dout.write("QUIT\n".getBytes());
            dout.flush();
            str = din.readLine();
            System.out.println("Server Says: " + str);

            din.close();
            dout.close();
            s.close();
       // }

    }
}