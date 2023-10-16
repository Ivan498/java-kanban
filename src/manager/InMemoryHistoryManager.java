package manager;

import Tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private Node first;
    private Node last;
    private Map<Integer, Node> map = new HashMap<>();


    public void removeNode(Node node) {
        if (node != null) {
            Task value = node.value;
            Node next = node.next;
            Node prev = node.prev;
            node.value = null;

            if (first == node && last == node) {
                first = null;
                last = null;
            } else if (first == node && (last != node)) {
                first = next;
                first.prev = null;
            } else if (first != node && last == node) {
                last = prev;
                last.next = null;
            } else {
                prev.next = next;
                next.prev = prev;
            }
            map.remove(value.getId());
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

    public void linkLast(Task value) {
        Node oldLast = last;
        Node node = new Node(oldLast, value, null);
        if (value != null && !map.containsKey(value.getId())) {
            map.put(value.getId(), node);

            if (oldLast != null) {
                oldLast.setNext(node);
            } else {
                first = node;
            }
            last = node;
        }
    }

    public void add (Task task){
        if (task != null){
            remove(task.getId());
            linkLast(task);
        }
    }

    static class Node {
        private Task value;
        private Node next;
        private Node prev;
        public Node(Node prev, Task value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
        public Task getValue() {
            return value;
        }

        public void setValue(Task value) {
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
