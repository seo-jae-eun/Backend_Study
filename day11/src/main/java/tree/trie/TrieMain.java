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
        trie.insert("TAMCAT");
        trie.insert("TEA");
        trie.printTrie();

        trie.search("TOMCAT");
        System.out.println(trie.count("T??T"));
//        System.out.println(trie.search("TEST"));
    }
}
