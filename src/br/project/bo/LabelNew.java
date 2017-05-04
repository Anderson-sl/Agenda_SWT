package br.project.bo;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LabelNew{
	private String name;
	private Label label;
	Composite composite;
	int argument;
	public LabelNew(Composite arg0, int arg1,String n) {
		this.name = n;
		this.composite = arg0;
		this.argument = arg1;
		this.label = new Label(arg0, arg1);
		this.label.setData(this);
	}
	
	public Label getLabel() {
		if(label == null){
			label = new Label(getComposite(), getArgument()); 
		}
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Composite getComposite() {
		return composite;
	}

	public void setComposite(Composite composite) {
		this.composite = composite;
	}

	public int getArgument() {
		return argument;
	}

	public void setArgument(int argument) {
		this.argument = argument;
	}

	
}
