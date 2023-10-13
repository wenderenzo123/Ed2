import java.util.PriorityQueue;
import java.util.HashMap;

class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public class HuffmanCompressor {
    public static byte[] compress(String input) {
        // Calcula a frequência de cada caractere no input
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Cria uma fila de prioridade (min-heap) para os nós Huffman
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char c : frequencyMap.keySet()) {
            HuffmanNode node = new HuffmanNode();
            node.character = c;
            node.frequency = frequencyMap.get(c);
            priorityQueue.add(node);
        }

        // Constrói a árvore de Huffman
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode();
            parent.frequency = left.frequency + right.frequency;
            parent.left = left;
            parent.right = right;
            priorityQueue.add(parent);
        }

        HuffmanNode root = priorityQueue.poll();
        // Agora, você tem a árvore de Huffman na variável "root".

        // Codifica os dados usando a árvore de Huffman
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);
        StringBuilder encodedData = new StringBuilder();
        for (char c : input.toCharArray()) {
            encodedData.append(huffmanCodes.get(c));
        }

        // Transforma os dados em bytes
        int numBytes = (encodedData.length() + 7) / 8;
        byte[] compressedData = new byte[numBytes];
        for (int i = 0; i < numBytes; i++) {
            int startIndex = i * 8;
            int endIndex = Math.min(startIndex + 8, encodedData.length());
            String byteStr = encodedData.substring(startIndex, endIndex);
            compressedData[i] = (byte) Integer.parseInt(byteStr, 2);
        }

        return compressedData;
    }

    public static String decompress(byte[] compressedData, HuffmanNode root) {
        // Decodifica os dados em bits
        StringBuilder bits = new StringBuilder();
        for (byte b : compressedData) {
            String binaryStr = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            bits.append(binaryStr);
        }

        // Decodifica os bits usando a árvore de Huffman
        StringBuilder decodedData = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : bits.toString().toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedData.append(current.character);
                current = root;
            }
        }

        return decodedData.toString();
    }

    private static void generateHuffmanCodes(HuffmanNode root, String code, HashMap<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }
        generateHuffmanCodes(root.left, code + "0", huffmanCodes);
        generateHuffmanCodes(root.right, code + "1", huffmanCodes);
    }

    public static HuffmanNode getHuffmanRoot() {
        // Calcula a frequência de cada caractere no input
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray()) {
            frequencyMap.put(c, 1);
        }

        // Cria uma fila de prioridade (min-heap) para os nós Huffman
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char c : frequencyMap.keySet()) {
            HuffmanNode node = new HuffmanNode();
            node.character = c;
            node.frequency = frequencyMap.get(c);
            priorityQueue.add(node);
        }

        // Constrói a árvore de Huffman
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode();
            parent.frequency = left.frequency + right.frequency;
            parent.left = left;
            parent.right = right;
            priorityQueue.add(parent);
        }

        HuffmanNode root = priorityQueue.poll();
        return root;
    }

    public static String decompress(String placa, HuffmanNode huffmanRoot) {
        // Decodifica os dados em bits
        StringBuilder bits = new StringBuilder();
        for (char c : placa.toCharArray()) {
            String binaryStr = String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0');
            bits.append(binaryStr);
        }

        // Decodifica os bits usando a árvore de Huffman
        StringBuilder decodedData = new StringBuilder();
        HuffmanNode current = huffmanRoot;
        for (char bit : bits.toString().toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedData.append(current.character);
                current = huffmanRoot;
            }
        }

        return decodedData.toString();
    }
}
