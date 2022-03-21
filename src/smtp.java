import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class smtp {
    public static void main(String argv[])
    {
        try{
            Socket socketClient= new Socket("localhost",2525); //dist.bhsi.xyz -- we used our own server :)
            System.out.println("Client: "+"Connection Established");

            BufferedReader IN = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            BufferedWriter OUT= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            String serverMsg;

            /** Sends Message to TA*/
            OUT.write("HELO Localhost\r\n");
            OUT.write("AUTH LOGIN\r\n");
            OUT.write("Y3ZpbkBkdHUuZGsK\r\n"); // "cvin@dtu.dk"
            OUT.write("YW55dGhpbmcKCg==\r\n"); //"anything"
            OUT.write("MAIL FROM:<s171242@student.dtu.dk>\r\n");
            OUT.write("RCPT TO:<cvin@dtu.dk>\r\n");
            OUT.write("DATA\r\n");
            OUT.write("FROM: s171242@student.dtu.dk\r\n");
            OUT.write("TO: cvin@dtu.dk\r\n");
            OUT.write("Subject: SMTP!\r\n");
            OUT.write("\r\n");
            //OUT.write("MIME-Version: 1.0 \n");
            OUT.write("Hello,\r\n\r\nHereby the e-mail from made by our javacode.\r\n\r\nBest Regards\r\nGroup 23.");
            OUT.write("\r\n");
            OUT.write("\r\n");
            OUT.write(".\r\n");

            /** Sends reply!*/
            OUT.write("MAIL FROM:<cvin@dtu.dk>\r\n");
            OUT.write("RCPT TO:<s171242@student.dtu.dk>\r\n");
            OUT.write("DATA\r\n");
            OUT.write("FROM: cvin@dtu.dk\r\n");
            OUT.write("TO: s171242@student.dtu.dk\r\n");
            OUT.write("Subject: SMTP!\r\n");
            OUT.write("\r\n");
            //OUT.write("MIME-Version: 1.0 \n");
            OUT.write("Dear Group 23,\r\n\r\nThats is amazing, thank you for your work!\r\n\r\nBest Regards\r\nTA.");
            OUT.write("\r\n");
            OUT.write("\r\n");
            OUT.write(".\r\n");

            OUT.write("quit\r\n");
            OUT.flush();
            while((serverMsg = IN.readLine()) != null){
                System.out.println("Client: " + serverMsg);
            }
        }catch(Exception e){e.printStackTrace();}
    }
}


