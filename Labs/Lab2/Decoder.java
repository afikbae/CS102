import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.io.IOException;


public class Decoder {

    private HashMap<Integer, String> map;

    private File mapFile;
    private File outputFile;

    public void setMapFile (File mapFile)
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

    public void decode (File inputFile)
    {
        try 
        {
            FileReader FR = new FileReader(inputFile);
            FileWriter FW = new FileWriter(outputFile);

            int i;
            String buffer = "";
            do
            {
                i = FR.read();
                if (i == 32 || i == 10 || i == 13 || i == -1)
                {
                    if (!buffer.isEmpty() && map.keySet().contains(Integer.valueOf(buffer)))
                    {
                        FW.write(map.get(Integer.valueOf(buffer)));
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

            FW.close();
            FR.close();

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
            map = new HashMap<Integer, String>();
            FileReader FR = new FileReader(this.mapFile);

            int i;
            String buffer = "";
            do
            {
                i = FR.read();
                if (i == 10)
                {
                    String value = buffer.substring(buffer.indexOf(" ") + 1, buffer.length());
                    Integer key = Integer.valueOf(buffer.substring(0, buffer.indexOf(" ") - 1));
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
}
