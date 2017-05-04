package br.project.gui;

import java.text.DecimalFormat;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;

import br.project.bo.AgendaDia;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

public class AgendaTeste {
	private static AgendaDia agendaDia;
	private static Text txtAgendar;
	private static Text txtRemover;
	private static Text txtAgData;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(957, 747);
		shell.setText("SWT Application");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 10, 571, 596);
		
		agendaDia = new AgendaDia(composite, SWT.NONE);
		agendaDia.setBounds(0, 0, 571, 457);
		agendaDia.setLayout(null);
		
		Button btnAgendar = new Button(shell, SWT.NONE);
		btnAgendar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				agendaDia.setValorAgendaSelecionada("Testando 1,2,3");
			}
		});
		btnAgendar.setBounds(670, 55, 198, 25);
		btnAgendar.setText("Agendar dia Selecionado");
		
		Button btnPegarData = new Button(shell, SWT.NONE);
		btnPegarData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button botao = (Button) arg0.getSource();
				botao.setText(agendaDia.getDataSelecionada()+"");
			}
		});
		btnPegarData.setBounds(670, 99, 198, 25);
		btnPegarData.setText("Pegar Data");
		
		Button btnPegarTexto = new Button(shell, SWT.NONE);
		btnPegarTexto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				txtAgendar.setText(agendaDia.getValorAgendaSelecionada());
			}
		});
		btnPegarTexto.setBounds(794, 142, 74, 25);
		btnPegarTexto.setText("Pegar");
		txtAgendar = new Text(shell, SWT.BORDER);
		txtAgendar.setBounds(670, 146, 118, 21);
		
		Button btnRemoverSel = new Button(shell, SWT.NONE);
		btnRemoverSel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				agendaDia.removeValorAgendaSelecionada();
			}
		});
		btnRemoverSel.setBounds(670, 187, 198, 25);
		btnRemoverSel.setText("Remover de Sele\u00E7\u00E3o");
		
		Button btnRemover = new Button(shell, SWT.NONE);
		btnRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				agendaDia.removeValorAgenda(Integer.parseInt(txtRemover.getText()));
			}
		});
		btnRemover.setBounds(793, 233, 75, 25);
		btnRemover.setText("Remover");
		
		txtRemover = new Text(shell, SWT.BORDER);
		txtRemover.setBounds(670, 237, 76, 21);
		
		Button btnAgendarData = new Button(shell, SWT.NONE);
		btnAgendarData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				agendaDia.setValorAgenda(Integer.parseInt(txtAgData.getText()), "Testando 1,2,3");
			}
		});
		btnAgendarData.setBounds(758, 24, 110, 25);
		btnAgendarData.setText("Agendar");
		
		txtAgData = new Text(shell, SWT.BORDER);
		txtAgData.setBounds(670, 24, 76, 21);
		
		agendaDia.AgendaDiaConstrucao(composite.getSize().x, composite.getSize().y);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
