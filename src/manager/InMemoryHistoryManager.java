package manager;

import Tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    Node first;
    Node last;
    Map<Integer, Node> map = new HashMap<>();


    @Override
    public void remove(int id) {
        Node nodeToRemove = map.get(id);
        if (nodeToRemove != null) {
            if (nodeToRemove.prev != null) {
                nodeToRemove.prev.next = nodeToRemove.next;
            } else {
                first = nodeToRemove.next;
            }
            if (nodeToRemove.next != null) {
                nodeToRemove.next.prev = nodeToRemove.prev;
            } else {
                last = nodeToRemove.prev;
            }
            map.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        List<Task> result = new ArrayList<>();

        if (first == null){
            return result;
        }
        Node current = first;

        while (current != null){
            result.add(current.value);
            current = current.next;
        }
        return result;
    }

    @Override
    public void add(Task task) {
        Node node = new Node(task);

        if (last != null) {
            last.next = node;
            node.prev = last;
            last = node;
        }
        else {
            first = node;
            last = node;
        }
    }
    public static class Node {
        Task value;
        Node next;
        Node prev;
        public Node(Task value) {
            this.value = value;
        }


        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
}
