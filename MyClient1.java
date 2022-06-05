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

        int blah = 0;
        ArrayList<ArrayList<String>> revServerList = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> serverList = new ArrayList<ArrayList<String>>();


        while((!(jobType.equalsIgnoreCase("none")))) {
           //while(blah < 4 ) {

            if(jobType.equalsIgnoreCase("JOBN")) {
                System.out.print("Client Says: ");
                System.out.println(Arrays.toString(jobSplit));
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
                    String[] serverInfo = str.split(" ");
                    String serverName = serverInfo[0];
                    String serverID = serverInfo[1];
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(serverName);
                    arr.add(serverID);
                    serverList.add(arr);
                    System.out.println("Server " + (i+1) + " is " + str);
                }

                dout.write(("OK\n").getBytes());
                dout.flush();
                str = din.readLine();
                System.out.println("Server Says: " + str);

                String strTime;
                double[] time = new double[serverList.size()];
                for(int i = 0 ;i < serverList.size(); i++) {
                    dout.write(("EJWT " + serverList.get(i).get(0) + " " + serverList.get(i).get(1) + " \n").getBytes());
                    strTime = din.readLine();
                    time[i] = Double.parseDouble(strTime);
                    System.out.println(strTime);
                }

                double[] revTime = new double[serverList.size()];
                int ind = 0;
                for(int y = time.length-1;y>=0;y--) {
                    revTime[ind] = time[y];
                    ind++;
                }

                for(int z = serverList.size() - 1 ;z >= 0; z--) {
                    revServerList.add(serverList.get(z));
                }
                double smallest = revTime[0];
                int index = 0;
                for(int x = 0; x < revTime.length; x++) {
                    double element = revTime[x];
                    System.out.println("Element comparsion: " + "  ->  " + smallest + " " + element);
                    if(smallest > element) {
                        smallest = element;
                        index = x;
                    }
                }

                System.out.println("Index is: " + index);


                if((jobType.equalsIgnoreCase("JOBN"))) {
                    System.out.println(("SCHD " + jobSplit[2] + " " + revServerList.get(index).get(0) + " " + revServerList.get(index).get(1) +"\n"));
                    dout.write(("SCHD " + jobSplit[2] + " " + revServerList.get(index).get(0) + " " + revServerList.get(index).get(1) +"\n").getBytes());
                    str = din.readLine();
                    System.out.println("Job information back from the server after SCHD is: " + str);
                }
            }
            System.out.println("Client Says: REDY ");
            dout.write(("REDY\n").getBytes());
            dout.flush();
            str = din.readLine();
            System.out.println("Server Says: " + str);
            jobSplit = str.split(" ");
            jobType = jobSplit[0];

            revServerList.clear();
            serverList.clear();
            //blah++;
        }

            // System.out.print("Client Says: ");
            // System.out.println("GETS Capable " + jobSplit[4] + "  " + jobSplit[5] + " " + jobSplit[6]);
            // dout.write(("GETS Capable " + jobSplit[4] + " " + jobSplit[5] + " " + jobSplit[6] + "\n").getBytes());
            // dout.flush();
            // str = din.readLine();
            // System.out.println("Server says " + str);
            // data = str.split(" ");
            // numServers = Integer.parseInt(data[1]);

            // dout.write(("OK\n").getBytes());
            // dout.flush();

            // System.out.println("Servers are: ");
            // for(int i = 0;i < numServers; i++) {
            //     str = din.readLine();
            //     System.out.println("Server " + (i+1) + " is " + str);
            // }

            // dout.write(("OK\n").getBytes());
            // dout.flush();
            // str = din.readLine();
            // System.out.println("Server Says: " + str);

            // dout.write(("EJWT tiny 0 \n").getBytes());
            // str = din.readLine();
            // System.out.println("Server says(Time): " + str);

            // dout.write(("EJWT tiny 1 \n").getBytes());
            // str = din.readLine();
            // System.out.println("Server says(Time): " + str);

            dout.write("QUIT\n".getBytes());
            dout.flush();
            str = din.readLine();
            System.out.println("Server Says: " + str);

            din.close();
            dout.close();
            s.close();

    }

}