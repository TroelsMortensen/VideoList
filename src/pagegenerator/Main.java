package pagegenerator;

import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {
        new Generator().run(new TreeStructureBuilder());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Finished: " + timestamp);
    }
}
