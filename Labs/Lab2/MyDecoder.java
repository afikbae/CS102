import java.io.File;

public class MyDecoder {
    public static void main(String[] args) {
        File input = new File("./input_decoder/input.txt");
        File map = new File("./input_decoder/map.txt");
        File decoded = new File("./input_decoder/decoded.txt");
        
        Decoder decoder = new Decoder();
        
        decoder.setMapFile(map);
        decoder.setOutputFile(decoded);

        decoder.decode(input);
    }
}
