package hurricaneParser.util;


import hurricaneParser.IllegalDataException;
import hurricaneParser.data.Basin;
import hurricaneParser.data.Hurricane;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HurricaneParserTest {


    private static final String INVALID_HEADER = "EP021,            UNNAMED,     25,";
    private static final String VALID_HEADER = "EP011949,            UNNAMED,      7,";
    private static final String VALID_DATA = "19930615, 1200,  , HU, 11.8N, 119.0W,  65,  989, -999, -999, -999, -999, -999, -999, -999, -999, -999, -999, -999, -999,";
    private static final String INVALID_DATA = "0615, 1200,  , HU, 11.8N, 119.0W,  65,  989, -999, -999, -999, -999, -999, -999, -999, -999, -999, -999, -999, -999,";


    @Test(expected = IllegalDataException.class)
    public void parseInvalidHeader() throws IllegalDataException, IOException {
        HurricaneDataParser hurricaneDataParser = mockHurricaneDataParserInstance();
        hurricaneDataParser.parseHeader(INVALID_HEADER);
    }

    @Test
    public void parseValidHeader() throws IllegalDataException, IOException {
        HurricaneDataParser hurricaneDataParser = mockHurricaneDataParserInstance();
        Hurricane hurricane = hurricaneDataParser.parseHeader(VALID_HEADER);
        Assert.assertEquals(hurricane, mockHurricaneInstance());
    }

    @Test
    public void parseValidDataLine() throws IOException, IllegalDataException {
        HurricaneDataParser hurricaneDataParser = mockHurricaneDataParserInstance();
        hurricaneDataParser.parseData(VALID_DATA);
    }

    @Test(expected = IllegalDataException.class)
    public void parseInvalidDataLine() throws IOException, IllegalDataException {
        HurricaneDataParser hurricaneDataParser = mockHurricaneDataParserInstance();
        hurricaneDataParser.parseData(INVALID_DATA);
    }

    @Test
    public void parseData() throws IOException, IllegalDataException {
        HurricaneDataParser hurricaneDataParser = hurricaneDataParserInstance();
        List<Hurricane> hurricanes = hurricaneDataParser.parseToList();
        Assert.assertFalse(hurricanes.isEmpty());
    }

    @Test
    public void filteringOnYear() {
        Hurricane hurricaneOne = new Hurricane();
        hurricaneOne.setYear(1989);
        Hurricane hurricaneTwo = new Hurricane();
        hurricaneTwo.setYear(2009);
        List<Hurricane> hurricaneList = new ArrayList<Hurricane>();
        hurricaneList.add(hurricaneOne);
        hurricaneList.add(hurricaneTwo);
        Iterable<Hurricane> filteredHurricanes = Filter.onYear(hurricaneList, 2009, false);
        Iterator<Hurricane> hurricaneIterator = filteredHurricanes.iterator();
        Assert.assertTrue(hurricaneIterator.hasNext());
        Assert.assertEquals(2009, hurricaneIterator.next().getYear());
        Assert.assertFalse(hurricaneIterator.hasNext());
    }

    private HurricaneDataParser mockHurricaneDataParserInstance() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/regex.properties"));
        return HurricaneDataParser.fromFile(mockFileDataInstance(), properties);
    }

    private HurricaneDataParser hurricaneDataParserInstance() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/regex.properties"));
        File dataFile = new File("src/test/resources/hurdat-test.txt");
        return HurricaneDataParser.fromFile(dataFile, properties);
    }

    private File mockFileDataInstance() {
        File file = mock(File.class);
        when(file.isDirectory()).thenReturn(Boolean.FALSE);
        when(file.exists()).thenReturn(Boolean.TRUE);
        return file;
    }

    private Hurricane mockHurricaneInstance() {
        Hurricane hurricane = mock(Hurricane.class);
        when(hurricane.getBasin()).thenReturn(Basin.NORTH_EAST_PACIFIC);
        when(hurricane.getAtcfNumber()).thenReturn("01");
        when(hurricane.getName()).thenReturn("UNNAMED");
        when(hurricane.getYear()).thenReturn(1949);
        return hurricane;
    }
}
