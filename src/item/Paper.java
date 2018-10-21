package item;

public class Paper {

	String name;
	String text;

	public Paper(String name, String text) {
		super();
		this.name = name;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "Paper [name=" + name + ", text=" + text + "]";
	}

}
