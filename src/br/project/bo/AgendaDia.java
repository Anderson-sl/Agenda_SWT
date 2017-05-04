package br.project.bo;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.hibernate.intercept.cglib.CGLIBHelper;


public class AgendaDia extends Composite{
	
public AgendaDia(Composite arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	
	private Date dataEscolida;
	private int width,hight,sizeMes,sizeSemana;
	private LabelAgendaDia dataSelect;
	int cont = 50;
	private boolean isSelect = false;
		
	public void AgendaDiaConstrucao(int width,int height) {
		this.width = width;
		this.hight = height;
		iniciar();
		montarAgenda();
	}


	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public LabelAgendaDia getDataSelect() {
		return dataSelect;
	}

	public void setDataSelect(LabelAgendaDia dataSelect) {
		this.dataSelect = dataSelect;
	}

	public int getSizeMes() {
		if(sizeMes <= 0){
			sizeMes = (int) ((int) (getSize().x/17.5))/2;
		}
		return sizeMes;
	}

	public void setSizeMes(int sizeMes) {
		this.sizeMes = sizeMes;
	}

	public int getSizeSemana() {
		if(sizeSemana <= 0){
			sizeSemana = (int) ((int) (getSize().x/35)/ 1.66);
		}
		return sizeSemana;
	}

	public void setSizeSemana(int sizeSemana) {
		this.sizeSemana = sizeSemana;
	}

	private Date getDataEscolida() {
		if(dataEscolida == null){
			dataEscolida = Calendar.getInstance().getTime();
		}
		return dataEscolida;
	}



	public void setDataEscolida(Date dataEscolida) {
		this.dataEscolida = dataEscolida;
	}



	private void iniciar(){		
		
		setBounds(0,0,width,hight);
		setLayout(new RowLayout());
//		TITULO COM NOME DOS DIAS DA SEMANA, DIAS DO MÊS, BOTAO DE AVANÇAR E RECUAR
//		BOTAO DE AVANÇAR E RECUAR
		
		Button botaoAvancar = new Button(this,SWT.PUSH);
		botaoAvancar.setBounds((int) (getSize().x-( getSize().x/17.5)), 0, (int) (getSize().x/17.5), (int) (getSize().x/17.5));
		botaoAvancar.setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255, 255, 255)));
		botaoAvancar.setText(">>");
		botaoAvancar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(org.eclipse.swt.events.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent arg0) {
				getDataEscolida().setMonth(getDataEscolida().getMonth()+1);
				setDataSelect(null);
				setSelect(false);
				montarAgenda();	
			}
			
			@Override
			public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		botaoAvancar.addMouseTrackListener(new MouseTrackListener() {
			
			@Override
			public void mouseHover(org.eclipse.swt.events.MouseEvent arg0) {
				Button b = (Button) arg0.getSource();
				b.setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,255,255)));
			}
			
			@Override
			public void mouseExit(org.eclipse.swt.events.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEnter(org.eclipse.swt.events.MouseEvent arg0) {
				Button b = (Button) arg0.getSource();
				b.setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(130,180,255)));
			}
		});
	
					
		Button botaoRecuo = new Button(this, SWT.PUSH);
		botaoRecuo.setBounds(0, 0, (int) (getSize().x/17.5), (int) (getSize().x/17.5));
		botaoRecuo.setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,255,255)));
		botaoRecuo.setText("<<");
		botaoRecuo.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(org.eclipse.swt.events.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent arg0) {
				getDataEscolida().setMonth(getDataEscolida().getMonth()-1);
				setDataSelect(null);
				setSelect(false);
				montarAgenda();	
				
			}
			
			@Override
			public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		botaoRecuo.addMouseTrackListener(new MouseTrackListener() {
			
			@Override
			public void mouseHover(org.eclipse.swt.events.MouseEvent arg0) {
				Button b = (Button) arg0.getSource();
				b.setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,255,255)));	
			}
			
			@Override
			public void mouseExit(org.eclipse.swt.events.MouseEvent arg0) {
				
				
			}
			
			@Override
			public void mouseEnter(org.eclipse.swt.events.MouseEvent arg0) {
				Button b = (Button) arg0.getSource();
				b.setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(130,180,255)));	
			}
		});
		
//		TITULO NOME DO MÊS
			LabelNew lbTituloMes = new LabelNew(this,SWT.NONE,"lbMes");
			lbTituloMes.getLabel().setBounds((int) (getSize().x/17.5), 0, (getSize().x)-(((int) (getSize().x/17.5))*2), (int) (getSize().x/17.5));
			lbTituloMes.getLabel().setFont(new org.eclipse.swt.graphics.Font(getDisplay(), new FontData("ARIAL",getSizeMes(),SWT.BOLD)));
			lbTituloMes.getLabel().setAlignment(SWT.CENTER);
			lbTituloMes.getLabel().setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,255,255)));
			lbTituloMes.setName("lbMes");
//			lbTituloMes.setOpaque(true);
//			add(lbTituloMes);
			
//			TITULO NOME DOS DIAS DA SEMANA
		for(int i = 0 ; i < 7 ; i++){
			LabelNew lbTitulo = new LabelNew(this, SWT.NONE,"");
			lbTitulo.getLabel().setBounds(i*(getSize().x/7), (int) (getSize().x/17.5), (getSize().x/7), (int) (getSize().x/35));
			switch (i) {
			case 0:
				lbTitulo.getLabel().setText("DOMINGO");
				break;
		case 1:
			lbTitulo.getLabel().setText("SEGUNDA");
				break;
		case 2:
			lbTitulo.getLabel().setText("TERÇA");
			break;
		case 3:
			lbTitulo.getLabel().setText("QUARTA");
			break;
		case 4:
			lbTitulo.getLabel().setText("QUINTA");
			break;
		case 5:
			lbTitulo.getLabel().setText("SEXTA");
			break;
		case 6:
			lbTitulo.getLabel().setText("SÁBADO");
			break;
			
			default:
				break;
			}
			lbTitulo.getLabel().setAlignment(SWT.CENTER);
			lbTitulo.getLabel().setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(176,196,222)));
			lbTitulo.getLabel().setFont(new org.eclipse.swt.graphics.Font(getDisplay(), new FontData("Arial",getSizeSemana(),SWT.BOLD)));
//			lbTitulo.setOpaque(true);
//			add(lbTitulo);
		}

		for(int i = 0 ; i < 6 ; i++){
			for(int j = 0 ; j < 7 ; j++){
				LabelAgendaDia label = new LabelAgendaDia(this, SWT.BORDER);
				label.montar(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,255,255)),this);
				label.getLabel().setBounds(j*(getSize().x/7), (i*(getSize().x/7))+((int) (getSize().x/17.5) + (int) (getSize().x/35)), (getSize().x/7), (getSize().x/7));
				label.setName("lbAgenda"+i+j);
				label.getLabel().setAlignment(SWT.LEFT);
			
				label.getLabel().setText("");
//				add(label);
		
			}
		}
		
		
		
		}
	private void montarAgenda(){
//		IMPLEMENTAÇÃO DA AGENDA DOS DIA DA SEMANA
		Calendar c = Calendar.getInstance();
		c.setTime(getDataEscolida());
		c.set(Calendar.DAY_OF_MONTH, 1);
		int diaMes = c.get(Calendar.DAY_OF_MONTH);
		int diaSemana = c.get(Calendar.DAY_OF_WEEK);
		int mes = c.get(Calendar.MONTH);
		int ano = c.get(Calendar.YEAR);
		c.set(Calendar.DAY_OF_MONTH, (1+1)-c.get(Calendar.DAY_OF_WEEK));
//		BUSCA OS COMPONENTES DA JPANEL E TRANSFORMA EM ARRAY DE COMPONENTES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Control[] comp = getChildren();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
//		BUSCA OS LABELS NO PANEL PARA ADICIONAR OS DIAS
		for(int i = 0; i < comp.length; i++){
			if(comp[i] instanceof Label){
				Label label = (Label) comp[i];
				if(label.getData() != null && label.getData() instanceof LabelNew){
				if( ((LabelNew)label.getData()).getName() != null)
					
				if( ((LabelNew)label.getData()).getName().equals("lbMes")){
					switch (mes) {
					
				case 0:
					label.setText("JANEIRO de "+ano);
						break;
				case 1:
					label.setText("FEVEREIRO de "+ano);
					break;
				case 2:
					label.setText("MARÇO de "+ano);
					break;
				case 3:
					label.setText("ABRIL de "+ano);
					break;
				case 4:
					label.setText("MAIO de "+ano);
					break;
				case 5:
					label.setText("JUNHO de "+ano);
					break;
				case 6:
					label.setText("JULHO de "+ano);
					break;
				case 7:
					label.setText("AGOSTO de "+ano);
					break;
				case 8:
					label.setText("SETEMBRO de "+ano);
					break;
				case 9:
					label.setText("OUTUBRO de "+ano);
					break;
				case 10:
					label.setText("NOVEMBRO de "+ano);
					break;
				case 11:
					label.setText("DEZEMBRO de "+ano);
					break;
					default:
						break;
					}
				}
			}
		}
	}
//		BUSCA OS LABELS NO PANEL PARA ADICIONAR OS DIAS
		for(int i = 0; i < comp.length; i++){
			if(comp[i] instanceof Label){
				if(comp[i].getData() != null && comp[i].getData() instanceof LabelAgendaDia){
				LabelAgendaDia label = (LabelAgendaDia)((Label) comp[i]).getData();
				if(label.getName() != null)
				if(!label.getName().equals("lbMes")){
					label.getLabel().setText(c.get(Calendar.DAY_OF_MONTH)+"");
					label.getLabel().setForeground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(0,0,0)));
							Calendar cal = Calendar.getInstance();
							cal.set(Calendar.YEAR, c.get(Calendar.YEAR));
							cal.set(Calendar.MONTH, c.get(Calendar.MONTH));
							cal.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
							label.setCal(cal);
								if(c.get(Calendar.MONTH) != getDataEscolida().getMonth()){
									label.getLabel().setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(192,192,192)));
								}else
									
								if(getDataEscolida().getMonth() == Calendar.getInstance().get(Calendar.MONTH)
									&& Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH) &&
										Calendar.getInstance().get(Calendar.YEAR) == c.get(Calendar.YEAR)){
									label.setBackGroundColor(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,182,193)));
									label.getLabel().setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,182,193)));
//									label.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192),1));
								}else{
//									label.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192),1));
									label.setBackGroundColor(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,255,255)));
									label.getLabel().setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,255,255)));
								}
								
								c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+1);
						}
//					comp[i] = label;
					}
				}
		}

	}
	

	public Date getDataSelecionada(){
		Date result = Calendar.getInstance().getTime();
				if(isSelect()){
					result = getDataSelect().getCal().getTime();
				}
			return result;
	}
	
	public void setValorAgenda(int dia, String texto){
		Calendar c = Calendar.getInstance();
		c.setTime(dataEscolida);
		if(dia > c.getActualMaximum(Calendar.DAY_OF_MONTH)){
			return;
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Control comp[] = getChildren();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0 ; i < comp.length ; i++){
				if(comp[i].getClass() == Label.class){
					if(comp[i].getData() != null && comp[i].getData() instanceof LabelAgendaDia){
				LabelAgendaDia label = (LabelAgendaDia)((Label) comp[i]).getData();
					
						int verificador=0,pos = 0;
						if(label.getLabel().getText().length() > 1){
							for(int v = 0; v < 2; v++){
								
								if("0123456789".contains(label.getLabel().getText().subSequence(0+verificador,0+1+verificador))){
									pos = 0+1+verificador;
									verificador++;
								}
							}
						}else{
							for(int v = 0; v < 1; v++){
								
								if("0123456789".contains(label.getLabel().getText().subSequence(0+verificador,0+1+verificador))){
									pos = 0+1+verificador;
									verificador++;
								}
							}
						}
							
									if(Integer.parseInt(label.getLabel().getText().substring(0, pos).toString()) == dia && 
												label.getCal().get(Calendar.MONTH) == getDataEscolida().getMonth()){
									
										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+" \n "+texto);
//										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+" <br> "+texto+label.getLabel().getText().subSequence(label.getLabel().getText().length()-7, label.getLabel().getText().length()));
									}
					
				
				}
			}
		}
	}
	
	public void setValorAgendaSelecionada(String texto){
		if(isSelect()){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Control comp[] = getChildren();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0 ; i < comp.length ; i++){
				if(comp[i].getClass() == Label.class){
					if(comp[i].getData() != null && comp[i].getData() instanceof LabelAgendaDia){
				LabelAgendaDia label = (LabelAgendaDia)((Label) comp[i]).getData();
					if(label.getLabel().equals(getDataSelect().getLabel())){
						
						int verificador=0,pos = 0;
							for(int v = 0; v < 2; v++){
								if("0123456789".contains(label.getLabel().getText().subSequence(0+verificador,0+1+verificador))){
									pos = 0+1+verificador;
									verificador++;
								}
							}
							
									if(Integer.parseInt(label.getLabel().getText().substring(0, pos).toString()) == getDataSelect().getCal().get(Calendar.DAY_OF_MONTH) && 
												label.getCal().get(Calendar.MONTH) == getDataEscolida().getMonth()){
										
										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+" \n "+texto);		
//										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+" <br> "+texto+label.getLabel().getText().subSequence(label.getLabel().getText().length()-7, label.getLabel().getText().length()));
									}
					}
				}
			}
		  }
		}
	}
	
	public String getValorAgendaSelecionada(){
		String texto = "";
		if(isSelect()){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Control comp[] = getChildren();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0 ; i < comp.length ; i++){
				if(comp[i].getClass() == Label.class){
					if(comp[i].getData() != null && comp[i].getData() instanceof LabelAgendaDia){
				LabelAgendaDia label = (LabelAgendaDia)((Label) comp[i]).getData();
					if(label.getLabel().equals(getDataSelect().getLabel())){
						int verificador=0,pos = 0;
							for(int v = 0; v < 2; v++){
								if("0123456789".contains(label.getLabel().getText().subSequence(0+verificador,0+1+verificador))){
									pos = 0+1+verificador;
									verificador++;
								}
							}
									if(Integer.parseInt(label.getLabel().getText().substring(0, pos).toString()) == getDataSelect().getCal().get(Calendar.DAY_OF_MONTH) && 
												label.getCal().get(Calendar.MONTH) == getDataEscolida().getMonth()){
							
										texto = label.getLabel().getText().subSequence(pos,label.getLabel().getText().length()).toString();		
//										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+" <br> "+texto+label.getLabel().getText().subSequence(label.getLabel().getText().length()-7, label.getLabel().getText().length()));
									}
					}
				}
			}
		  }
		}
		return texto;
	}
	
	public void removeValorAgenda(int dia){
		Calendar c = Calendar.getInstance();
		c.setTime(dataEscolida);
		if(dia > c.getActualMaximum(Calendar.DAY_OF_MONTH)){
			return;
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Control comp[] = getChildren();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0 ; i < comp.length ; i++){
				if(comp[i].getClass() == Label.class){
					if(comp[i].getData() != null && comp[i].getData() instanceof LabelAgendaDia){
				LabelAgendaDia label = (LabelAgendaDia)((Label) comp[i]).getData();
						int verificador=0,pos = 0;
						if(label.getLabel().getText().length() > 1){
							for(int v = 0; v < 2; v++){
								
								if("0123456789".contains(label.getLabel().getText().subSequence(0+verificador,0+1+verificador))){
									pos = 0+1+verificador;
									verificador++;
								}
							}
						}else{
							for(int v = 0; v < 1; v++){
								
								if("0123456789".contains(label.getLabel().getText().subSequence(0+verificador,0+1+verificador))){
									pos = 0+1+verificador;
									verificador++;
								}
							}
						}
							
									if(Integer.parseInt(label.getLabel().getText().substring(0, pos).toString()) == dia && 
												label.getCal().get(Calendar.MONTH) == getDataEscolida().getMonth()){
										
										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+"");	
//										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+" <br> "+label.getLabel().getText().subSequence(label.getLabel().getText().length()-7, label.getLabel().getText().length()));
									}
				
				}
			}
		}
	}
	
	public void removeValorAgendaSelecionada(){
		if(isSelect()){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Control comp[] = getChildren();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0 ; i < comp.length ; i++){
				if(comp[i].getClass() == Label.class){
					if(comp[i].getData() != null && comp[i].getData() instanceof LabelAgendaDia){
				LabelAgendaDia label = (LabelAgendaDia)((Label) comp[i]).getData();
					if(label.getLabel().equals(getDataSelect().getLabel())){
						int verificador=0,pos = 0;
							for(int v = 0; v < 2; v++){
								if("0123456789".contains(label.getLabel().getText().subSequence(0+verificador,0+1+verificador))){
									pos = 0+1+verificador;
									verificador++;
								}
							}
							
									if(Integer.parseInt(label.getLabel().getText().substring(0, pos).toString()) == getDataSelect().getCal().get(Calendar.DAY_OF_MONTH) && 
												label.getCal().get(Calendar.MONTH) == getDataEscolida().getMonth()){
										
										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+"");
//										label.getLabel().setText(label.getLabel().getText().subSequence(0, pos)+" <br> "+label.getLabel().getText().subSequence(label.getLabel().getText().length()-7, label.getLabel().getText().length()));
									}
					}
				}
			}
		  }
		}
	}
	
	public void controleDeSelecao(){
		for(Control comp: getChildren()){
			if(comp instanceof Label){
					if(comp.getData() != null && comp.getData() instanceof LabelAgendaDia){
					LabelAgendaDia label = (LabelAgendaDia)((Label) comp).getData();
						
					if(!(label.getLabel().getBackground().getRGB().equals(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(192, 192, 192)).getRGB()))){
						if(label.getCal().get(Calendar.DAY_OF_MONTH) != getDataSelect().getCal().get(Calendar.DAY_OF_MONTH)){
		//					label.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192),1));
							
								if(label.getCal().get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
										label.getCal().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH) &&
											label.getCal().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)){
									label.getLabel().setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,182,193)));
								}else{
									label.getLabel().setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(255,255,255)));
									label.getLabel().setForeground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(0,0,0)));
								}
						}else{
		//					label.setBorder(BorderFactory.createLineBorder(Color.red,3));
								label.getLabel().setBackground(new org.eclipse.swt.graphics.Color(getDisplay(), new RGB(170,225,253)));
							
						}
					}
				}
			}
		}
	}
	
	public void setSizeFontData(int size){
		setSizeMes(size);
	}
	
	public void setSizeFontSemana(int size){
		setSizeSemana(size);
	}

	public void cleanAgenda(){
		montarAgenda();
	}
}
