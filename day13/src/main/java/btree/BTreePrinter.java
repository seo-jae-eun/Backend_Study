package b;

import java.util.ArrayList;
import java.util.List;

public class BTreePrinter {
    public interface PrintableNode {
        PrintableNode getLeft();
        PrintableNode getMiddle(); // Added for 3rd child
        PrintableNode getRight();
        String getText();
    }

    public static void print(PrintableNode root) {
        List<List<String>> lines = new ArrayList<>();

        List<PrintableNode> level = new ArrayList<>();
        List<PrintableNode> next = new ArrayList<>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;

            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                    next.add(null); // Add null for the middle child
                } else {
                    String aa = n.getText();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getMiddle()); // Add middle child to the next level
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getMiddle() != null) nn++; // Count middle child as well
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (List<String> line : lines) {
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            for (String str : line) {
                if (str == null) {
                    System.out.print(" ");
                    continue;
                }

                int gap1 = (int) Math.ceil(perpiece / 2f - str.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - str.length() / 2f);

                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(str);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            perpiece /= 2;
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }
}