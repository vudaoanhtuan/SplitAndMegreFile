package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Spinner;

import function.*;

public class MainWindow {
	private static Text textInputFile;
	private static Text textOutputFolder;
	private static int splitBy;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlSplitMerge = new Shell();
		shlSplitMerge.setSize(689, 525);
		shlSplitMerge.setText("Split & Merge File");
		shlSplitMerge.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(shlSplitMerge, SWT.NONE);
		
		TabItem tabSplit = new TabItem(tabFolder, SWT.NONE);
		tabSplit.setText("Split");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabSplit.setControl(composite);
				
		Group grpChooseFile = new Group(composite, SWT.NONE);
		grpChooseFile.setText("Choose File");
		grpChooseFile.setBounds(10, 10, 643, 78);
		
		textInputFile = new Text(grpChooseFile, SWT.BORDER);
		textInputFile.setBounds(10, 42, 587, 26);
		
		Button buttonChooseFile = new Button(grpChooseFile, SWT.NONE);
		buttonChooseFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				FileDialog fileOpen = new FileDialog(shlSplitMerge);
				try {
					String part = fileOpen.open();
					textInputFile.setText(part);
				} catch (Exception e) {
					// do nothing
				}
			}
		});
		buttonChooseFile.setBounds(603, 40, 30, 30);
		buttonChooseFile.setText("...");
		
		Group grpChooseFolder = new Group(composite, SWT.NONE);
		grpChooseFolder.setText("Choose Folder");
		grpChooseFolder.setBounds(10, 94, 643, 78);
		
		textOutputFolder = new Text(grpChooseFolder, SWT.BORDER);
		textOutputFolder.setBounds(10, 42, 587, 26);
		
		Button buttonChooseFolder = new Button(grpChooseFolder, SWT.NONE);
		buttonChooseFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				DirectoryDialog dirOpen = new DirectoryDialog(shlSplitMerge);
				try {
					String part = dirOpen.open();
					textOutputFolder.setText(part);
				} catch (Exception e) {
					// do nothing
				}
			}
		});
		buttonChooseFolder.setBounds(603, 40, 30, 30);
		buttonChooseFolder.setText("...");
		
		Group grpChoose = new Group(composite, SWT.NONE);
		grpChoose.setText("Choose");
		grpChoose.setBounds(10, 178, 643, 119);
		
		Button radioSplitPart = new Button(grpChoose, SWT.RADIO);
		radioSplitPart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				splitBy = 1;
			}
		});
		radioSplitPart.setBounds(10, 33, 111, 20);
		radioSplitPart.setText("Split into");
		
		Button radioSplitSize = new Button(grpChoose, SWT.RADIO);
		radioSplitSize.setBounds(10, 78, 111, 20);
		radioSplitSize.setText("Split every");
		
		Spinner spinnerPart = new Spinner(grpChoose, SWT.BORDER);
		spinnerPart.setBounds(131, 33, 59, 26);
		
		Spinner spinneSize = new Spinner(grpChoose, SWT.BORDER);
		spinneSize.setBounds(131, 75, 128, 26);
		
		Label lblPart = new Label(grpChoose, SWT.NONE);
		lblPart.setBounds(196, 33, 70, 20);
		lblPart.setText("part");
		
		Label lblMbmegaByte = new Label(grpChoose, SWT.NONE);
		lblMbmegaByte.setBounds(265, 78, 140, 20);
		lblMbmegaByte.setText("MB (Mega Byte)");
		
		Button btnSplit = new Button(composite, SWT.NONE);
		
		
		// Split button
		btnSplit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				String filePath = textInputFile.getText();
				String folderPath = textOutputFolder.getText();
				int nPart = Integer.valueOf(spinnerPart.getText());
				System.out.println(nPart);
				System.out.println(splitBy);
				if (splitBy == 1) {
					try {
						System.out.println("Spliting");
						Split.splitByPart(filePath, folderPath, nPart);
						
					} catch (Exception e) {
						MessageBox mess = new MessageBox(shlSplitMerge, SWT.OK | SWT.ICON_WARNING);
						mess.setText("Error!");
						mess.open();
					}
				}
			}
		});
		
		
		btnSplit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnSplit.setBounds(290, 303, 90, 30);
		btnSplit.setText("Split");
		
		ProgressBar progressBar = new ProgressBar(composite, SWT.NONE);
		progressBar.setBounds(10, 414, 643, 21);
		
		TabItem tabMerge = new TabItem(tabFolder, SWT.NONE);
		tabMerge.setText("Merge");

		shlSplitMerge.open();
		shlSplitMerge.layout();
		
		
		
		while (!shlSplitMerge.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
