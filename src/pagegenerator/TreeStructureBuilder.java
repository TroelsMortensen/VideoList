package pagegenerator;

import java.io.*;

public class TreeStructureBuilder {
    private String[] fontSizes = {"xx-large", "x-large", "large", "medium", "small"};
    private StringBuilder result;

    public String buildTree() {
        result = new StringBuilder();
        File contentFolder = new File("content/nested");
        int indent = 0;
        writeRoot(contentFolder, indent);
        return result.toString();
    }

    private void append(String s) {
        result.append(s + "\n");
    }

    private void writeRoot(File contentFolder, int indent) {
        append("<ul id=\"ContentList\">");

        for (File folder : contentFolder.listFiles()) {
            if (folder.isDirectory()) {
                handleFolder(folder, indent);
            }
        }

        append("</ul>");
    }

    private void handleFolder(File root, int indent) {
        append("<li>");
        {
            append("<span class=\"caret\" style=\"font-size:" + fontSizes[indent] + "\">" + root.getName() + "</span>");
            {
                append("<ul class=\"nested\">");
                {
                    for (File fldr : root.listFiles()) {
                        if (fldr.isDirectory()) {
                            handleFolder(fldr, indent + 1);
                        }
                    }
                    for (File file : root.listFiles()) {
                        if (!file.isDirectory()) {
                            handleHTMLSnippet(file);
                        }
                    }
                }
            }
            append("</ul>");
        }
        append("</li>");
    }

    private void handleHTMLSnippet(File file) {
        append("<li>");
        {
            append("<span class=\"caret\" style='font-style:italic; font-size:medium'>Nested components as lists</span>");
            {
                append("<div class=\"nested\">");
                {
                    append("<div class=\"content\">");
                    {
                        append(loadHTML(file));
                    }
                    append("</div>");
                }
                append("</div>");
            }
        }
        append("</li>");
    }

    private String loadHTML(File file) {
        String result = "";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                append(line);
            }
        } catch (IOException e) {
            result = "Failed to load";
        }
        return result;
    }


}
