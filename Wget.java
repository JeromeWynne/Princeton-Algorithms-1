// Accesses URL and saves contents to a .txt file w/ filename == URL //
// J.Wynne, March 2017

import java.net.*;
import java.io.*;

public class Wget{
    
    public static void main(String[] args) throws Exception {
        URL resource = new URL(args[0]);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(resource.openStream()));
        
        String fp = args[0].replace('/', '_').replace(':', '-');
        PrintWriter writer = new PrintWriter(fp, "UTF-8");
        
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            writer.println(inputLine);
        in.close();
        writer.close();
    }
}