package tree.trie;

public class TrieMain {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("TEST");
        trie.insert("TEAT");
        trie.insert("TEAT");
        trie.insert("TE");
        trie.insert("TOMCAT");
        trie.insert("TOMCAT");
        trie.insert("TOMCAT");
        trie.insert("TOMCAT");
        trie.insert("TEA");
        trie.printTrie();

        trie.search("TOMCAT");
        System.out.println(trie.count("TE?T"));
//        System.out.println(trie.search("TEST"));
    }
}
