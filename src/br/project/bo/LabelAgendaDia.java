package br.project.bo;

import java.util.Calendar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;



public class LabelAgendaDia{

	private Calendar cal;
	private Color backGroundColor; 
	private AgendaDia panel;
	private String name;
	private Label label;
	private Composite composite;
	private int argument;

	public LabelAgendaDia(Composite arg0, int arg1) {
		this.composite = arg0;
		this.argument = arg1;
		this.label = new Label(arg0, arg1);
		this.label.setData(this);
	}
	
	
	public Label getLabel() {
		if(label == null){
			label = new Label(composite, argument);
		}
		return label;
	}


	public void setLabel(Label label) {
		this.label = label;
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


	public void montar(Color color, AgendaDia panel) {
		this.panel = panel;
		this.backGroundColor = color;
		Iniciar();
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Color getBackGroundColor() {
		if(backGroundColor == null){
			backGroundColor = new Color(getLabel().getDisplay(), new RGB(255, 255, 255));
		}
		return backGroundColor;
	}

	public AgendaDia getPanel() {
		if(panel == null){
			panel = new AgendaDia(getComposite(),SWT.NONE);
		}
		return panel;
	}

	public void setPanel(AgendaDia panel) {
		this.panel = panel;
	}

	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	public Calendar getCal() {
		if(cal == null){
			cal = Calendar.getInstance();
		}
		return cal;
	}


	public void setCal(Calendar cal) {
		this.cal = cal;
	}



	private void Iniciar(){
		
		getLabel().setBackground(getBackGroundColor());
		getLabel().setAlignment(SWT.CENTER);
		getLabel().addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent arg0) {
				if(!getLabel().getBackground().toString().equals(new Color(getLabel().getDisplay(),new RGB(192, 192, 192)).toString())){
					addDataSelecionada();
					getPanel().setSelect(true);
					getLabel().forceFocus();
					getPanel().controleDeSelecao();
				}
			}
		});
		
		getLabel().addMouseTrackListener(new MouseTrackListener() {
			
			@Override
			public void mouseHover(org.eclipse.swt.events.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExit(org.eclipse.swt.events.MouseEvent arg0) {
				if(getLabel().getBackground().toString().equals(new Color(getLabel().getDisplay(),new RGB(192, 192, 192)).toString()) ||
						getLabel().getBackground().toString().equals(new Color(getLabel().getDisplay(),new RGB(255,182,193)).toString()) ||
							getLabel().getBackground().toString().equals(new Color(getLabel().getDisplay(),new RGB(170,225,253)).toString())){
					
				}else{
					getLabel().setBackground(getBackGroundColor());
					getLabel().setForeground(new Color(getLabel().getDisplay(), new RGB(0, 0, 0)));
				}
				
			}
			
			@Override
			public void mouseEnter(org.eclipse.swt.events.MouseEvent arg0) {
				if(getLabel().getBackground().toString().equals(new Color(getLabel().getDisplay(),new RGB(192, 192, 192)).toString()) || 
						getLabel().getBackground().toString().equals(new Color(getLabel().getDisplay(),new RGB(255,182,193)).toString()) ||
							getLabel().getBackground().toString().equals(new Color(getLabel().getDisplay(),new RGB(170,225,253)).toString())){
					
				}else{
					getLabel().setBackground(new Color(getLabel().getDisplay(),new RGB(130,180,255)));
					getLabel().setForeground(new Color(getLabel().getDisplay(),new RGB(255,255,255)));
				}
				
			}
		});
		
		
		
		
	}
	
	public void addDataSelecionada(){
		getPanel().setDataSelect(this);
	}
	
}
