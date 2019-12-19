package pagegenerator;

import java.io.*;

public class Generator {

    public void run(TreeStructureBuilder tsb) {
        try {
            String treeStructureHTML = tsb.buildTree();
            generateIndexHTML(treeStructureHTML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateIndexHTML(String treeStructureHTML) throws IOException {
        File template = new File("pages/template.html");

        FileReader fr = new FileReader(template);
        BufferedReader br = new BufferedReader(fr);

        PrintWriter pw = new PrintWriter(new FileWriter("pages/index.html"));
        String line;

        while((line=br.readLine()) != null) {
            if(line.equals("##content##")) {
                System.out.println("Inserting tree");
                line = treeStructureHTML;
            }
            pw.write(line + "\n");
        }

        pw.flush();
        pw.close();
        br.close();
    }
}
