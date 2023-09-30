package manager;

import Tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    
    Node first;
    Node last;
    Map<Integer, Node> map = new HashMap<>();

    @Override
    public void removeNode(Node node) {
        if (node != null) {
            Task value = node.value;
            Node next = node.next;
            Node prev = node.prev;
            node.value = null;

            if (first == node && last == node) {
                first = null;
                last = null;
            }
            else if (first == node && (last != node)) {
                first = next;
                first.prev = null;
            }
            else if (first != node && last == node){
                last = prev;
                last.next = null;
            }
            else {
                prev.next = next;
                next.prev = prev;
            }
            node = null;
        }
    }

    public void remove(int id){
        removeNode(map.get(id));
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
    public void linkLast(Task task) {
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
        map.put(task.getId(), node);
    }

    public void add (Task task){
        if (task != null){
            remove(task.getId());
            linkLast(task);
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
