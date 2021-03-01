package office;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import static java.lang.Thread.currentThread;

public class DocumentHandler {

    private Queue<Document> documents = new LinkedList<>();

    public void elaborateDocument(Document document) {
        System.out.println( "Elaborated document: " + document.toString());
        if(document != null) {
            documents.add(document);
        }
    }

    public Document printDocument() {
        Document document = documents.poll();
        System.out.println("Printed document " + document.toString());
        return document;
    }

    public boolean hasDocuments() {
        return !documents.isEmpty();
    }
}
