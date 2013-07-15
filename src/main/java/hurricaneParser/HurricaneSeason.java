package hurricaneParser;


import hurricaneParser.util.HurricaneDataParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HurricaneSeason {

    public static void main(String[] args) throws IOException, IllegalDataException {

        File dataFile = new File(args[0]);
        Properties properties = new Properties();
        properties.load(new FileReader(args[1]));
        HurricaneDataParser.fromFile(dataFile, properties).parse();
    }
}
