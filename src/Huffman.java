import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode> {
    int freq;
    char character;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char c, int i) {
        this.character = c;
        this.freq = i;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.freq - other.freq;
    }
}

public class Huffman {
    private HuffmanNode root;
    private Map<Character, String> codes;

    public String compress(String input) {
        Map<Character, Integer> charFrequencies = new HashMap<>();

        for (char c : input.toCharArray()) {
            charFrequencies.put(c, charFrequencies.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();
        for (char c : charFrequencies.keySet()) {
            minHeap.offer(new HuffmanNode(c, charFrequencies.get(c)));
        }

        while (minHeap.size() > 1) {
            HuffmanNode x = minHeap.poll();
            HuffmanNode y = minHeap.poll();

            HuffmanNode z = new HuffmanNode('-', x.freq + y.freq);
            z.left = x;
            z.right = y;

            minHeap.offer(z);
        }

        root = minHeap.poll();
        codes = new HashMap<>();
        buildCodes(root, new StringBuilder());
        
        StringBuilder compressed = new StringBuilder();
        for (char c : input.toCharArray()) {
            compressed.append(codes.get(c));
        }

        return compressed.toString();
    }

    public String decompress(String compressed) {
        StringBuilder decompressed = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : compressed.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decompressed.append(current.character);
                current = root;
            }
        }

        return decompressed.toString();
    }

    private void buildCodes(HuffmanNode node, StringBuilder code) {
        if (node == null) {
            return;
        }

        if (node.character != '-') {
            codes.put(node.character, code.toString());
        }

        StringBuilder leftCode = new StringBuilder(code);
        leftCode.append("0");
        buildCodes(node.left, leftCode);

        StringBuilder rightCode = new StringBuilder(code);
        rightCode.append("1");
        buildCodes(node.right, rightCode);
    }

    public static void main(String[] args) {
        String input = "Hello, World!";
        Huffman huffman = new Huffman();

        String compressed = huffman.compress(input);
        System.out.println("Compressed: " + compressed);

        String decompressed = huffman.decompress(compressed);
        System.out.println("Decompressed: " + decompressed);

        if (input.equals(decompressed)) {
            System.out.println("Compression and decompression successful.");
        } else {
            System.out.println("Compression and decompression failed.");
        }
    }
}
