import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Encoder
 */
public class Encoder {

    private HashMap<String, Integer> map;

    private File mapFile;
    private File outputFile;

    public void setMap (File mapFile)
    {
        this.mapFile = mapFile;
        if(!this.createMap())
        {
            this.mapFile = null;
        }
    }

    public void setOutputFile (File outputFile)
    {
        this.outputFile = outputFile;
    }

    public void encode (File inputFile)
    {
        try 
        {
            FileReader FR = new FileReader(inputFile);
            FileWriter FW = new FileWriter(this.outputFile);

            int i;
            String buffer = "";

            do 
            {
                i = FR.read();
                if (i == 32 || i == 10 || i == 13 || i == -1)
                {
                    if (map.containsKey(buffer))
                    {
                        FW.write("" + map.get(buffer));
                    }
                    else if (!buffer.isEmpty())
                    {
                        FW.write(this.addToMap(buffer));
                    }
                    if (i != -1)
                    {
                        FW.write(i);
                    }
                    buffer = "";
                }
                else
                {
                    buffer += (char) i;
                }
            } 
            while (i != -1);

            FR.close();
            FW.close();
        } 
        catch (IOException e) 
        {
            // TODO: handle exception
            System.out.println(e);
        }
    }

    private boolean createMap()
    {
        try
        {
            map = new HashMap<String, Integer>();
            FileReader FR = new FileReader(this.mapFile);

            int i;
            String buffer = "";
            do
            {
                i = FR.read();
                if (i == 10)
                {
                    String key = buffer.substring(buffer.indexOf(" ") + 1, buffer.length());
                    Integer value = Integer.valueOf(buffer.substring(0, buffer.indexOf(" ") - 1));
                    this.map.put(key, value);
                    buffer = "";
                }
                else
                {
                    buffer += (char) i;
                }
            }
            while (i != -1);

            FR.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println(e);
            return false;
        }
        
    }

    private int addToMap (String key)
    {
        int value = map.size();
        map.put(key, value);

        try
        {
            FileWriter FW = new FileWriter(this.mapFile ,true);
            FW.write(value + ": " + key + "\n");    
            FW.close();
        }
        catch (IOException e) 
        {
            return -1;
        }

        return value;
    }
}