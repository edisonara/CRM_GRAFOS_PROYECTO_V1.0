package ejecucion;

public class Note {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters y setters (opcional)

    @Override
    public String toString() {
        return title;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void addLink(String string, Note note2) {
		// TODO Auto-generated method stub
		
	}
}
