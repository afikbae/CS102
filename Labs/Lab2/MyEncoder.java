import java.io.File;

public class MyEncoder {
    public static void main(String[] args) {
        File input = new File("./input_encoder/input.txt");
        File map = new File("./input_encoder/map.txt");
        File encoded = new File("./input_encoder/encoded.txt");
        

        Encoder encoder = new Encoder();

        encoder.setMap(map);
        encoder.setOutputFile(encoded);

        encoder.encode(input);        
    
        File inputAlt = new File("./input_encoder_alternative/input.txt");
        File mapAlt = new File("./input_encoder_alternative/map.txt");
        File encodedAlt = new File("./input_encoder_alternative/encoded.txt");
        

        EncoderAlternative encoderAlt = new EncoderAlternative();

        encoderAlt.setMapFile(mapAlt);
        encoderAlt.setOutputFile(encodedAlt);

        encoderAlt.encode(inputAlt);
    }
}
