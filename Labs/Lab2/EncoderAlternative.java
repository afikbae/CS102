import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.IOException;


public class EncoderAlternative {

    private ArrayList<String> map;

    private File mapFile;
    private File outputFile;

    public EncoderAlternative ()
    {
        map = new ArrayList<String>();
    }
    
    public void setMapFile (File mapFile)
    {
        this.mapFile = mapFile;
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
                    if (!buffer.isEmpty())
                    {
                        addToMap(buffer);
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
            FR = new FileReader(inputFile);

            do 
            {
                i = FR.read();
                if (i == 32 || i == 10 || i == 13 || i == -1)
                {
                    if (!buffer.isEmpty() && this.map.contains(buffer))
                    {
                        FW.write(this.map.indexOf(buffer) + "");
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

    private int addToMap (String word)
    {
        if (!this.map.contains(word))
        {
            this.map.add(word);
            Collections.sort(this.map, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o2.length() - o1.length();
                }
            });
        }
        try 
        {
            FileWriter FW = new FileWriter(this.mapFile, false);

            for (int i = 0; i < this.map.size(); i++) 
            {
                FW.write(i + ": " + this.map.get(i) + "\n");
            }
            FW.close();
        }
        catch (Exception e) 
        {
            // TODO: handle exception
        }
        return this.map.indexOf(word);
    }
}
