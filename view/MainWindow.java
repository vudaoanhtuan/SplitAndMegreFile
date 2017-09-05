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
	private static int percent;
	private static Text textMergeFileChoose;
	private static Text textMergeFolderChoose;
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
		
		Composite compositeSlpitTab = new Composite(tabFolder, SWT.NONE);
		tabSplit.setControl(compositeSlpitTab);
				
		Group grpChooseFile = new Group(compositeSlpitTab, SWT.NONE);
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
					String path = fileOpen.open();
					textInputFile.setText(path);
					textOutputFolder.setText(Function.getFolderNameFromPath(path));
				} catch (Exception e) {
					// do nothing
				}
			}
		});
		buttonChooseFile.setBounds(603, 40, 30, 30);
		buttonChooseFile.setText("...");
		
		Group grpChooseFolder = new Group(compositeSlpitTab, SWT.NONE);
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
		
		Group grpChoose = new Group(compositeSlpitTab, SWT.NONE);
		grpChoose.setText("Choose");
		grpChoose.setBounds(10, 178, 643, 119);
		
		Button radioSplitPart = new Button(grpChoose, SWT.RADIO);
		radioSplitPart.setSelection(true);
		splitBy = 1;
		radioSplitPart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				splitBy = 1;
			}
		});
		radioSplitPart.setBounds(10, 33, 111, 20);
		radioSplitPart.setText("Split into");
		
		Button radioSplitSize = new Button(grpChoose, SWT.RADIO);
		radioSplitSize.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				splitBy = 2;
			}
		});
		radioSplitSize.setBounds(10, 78, 111, 20);
		radioSplitSize.setText("Split every");
		
		Spinner spinnerPart = new Spinner(grpChoose, SWT.BORDER);
		spinnerPart.setBounds(131, 33, 59, 26);
		spinnerPart.setValues(3, 2, 999, 0, 1, 10);
		
		Spinner spinnerSize = new Spinner(grpChoose, SWT.BORDER);
		spinnerSize.setBounds(131, 75, 128, 26);
		spinnerSize.setValues(100, 1, 9999999, 0, 100, 1000);
		
		Label lblPart = new Label(grpChoose, SWT.NONE);
		lblPart.setBounds(196, 33, 70, 20);
		lblPart.setText("part");
		
		Label lblMbmegaByte = new Label(grpChoose, SWT.NONE);
		lblMbmegaByte.setBounds(265, 78, 140, 20);
		lblMbmegaByte.setText("MB (Mega Byte)");
		
		ProgressBar progressBar = new ProgressBar(compositeSlpitTab, SWT.NONE);
		progressBar.setMaximum(100);
		progressBar.setBounds(10, 414, 643, 21);
		
		Button btnSplit = new Button(compositeSlpitTab, SWT.NONE);
		
		
		// Split button
		btnSplit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				String filePath = textInputFile.getText();
				String folderPath = textOutputFolder.getText();
				if (splitBy == 1) {
					try {
						int nPart = spinnerPart.getSelection();
						System.out.println("Spliting");
						Split.splitByPart(filePath, folderPath, nPart, progressBar);
						
					} catch (Exception e) {
						MessageBox mess = new MessageBox(shlSplitMerge, SWT.OK | SWT.ICON_WARNING);
						mess.setText("Error!");
						mess.open();
					}
				} else if (splitBy == 2) {
					try {
						int partSize = spinnerSize.getSelection() * 1024 * 1024;
						System.out.println("Spliting");
						Split.splitBySize(filePath, folderPath, partSize, progressBar);
						
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
		btnSplit.setBounds(282, 303, 90, 30);
		btnSplit.setText("Split");
		

		
		
		
		
		TabItem tabMerge = new TabItem(tabFolder, SWT.NONE);
		tabMerge.setText("Merge");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabMerge.setControl(composite);
		
		Group grpMergeFile = new Group(composite, SWT.NONE);
		grpMergeFile.setText("Choose File");
		grpMergeFile.setBounds(10, 10, 643, 78);
		
		textMergeFileChoose = new Text(grpMergeFile, SWT.BORDER);
		textMergeFileChoose.setBounds(10, 42, 587, 26);
		
		Button buttonMergeFileChoose = new Button(grpMergeFile, SWT.NONE);
		buttonMergeFileChoose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				try {
					FileDialog fdl = new FileDialog(shlSplitMerge);
					String filename = fdl.open();
					textMergeFileChoose.setText(filename);
					textMergeFolderChoose.setText(Function.getFolderNameFromPath(filename));
				} catch (Exception exc) {
					
				}
			}
		});
		buttonMergeFileChoose.setText("...");
		buttonMergeFileChoose.setBounds(603, 40, 30, 30);
		
		Group grpMergeFolder = new Group(composite, SWT.NONE);
		grpMergeFolder.setText("Choose Folder");
		grpMergeFolder.setBounds(10, 94, 643, 78);
		
		textMergeFolderChoose = new Text(grpMergeFolder, SWT.BORDER);
		textMergeFolderChoose.setBounds(10, 42, 587, 26);
		
		Button buttonMergeFolderChoose = new Button(grpMergeFolder, SWT.NONE);
		buttonMergeFolderChoose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				try {
					DirectoryDialog ddl = new DirectoryDialog(shlSplitMerge);
					String folder = ddl.open();
					textMergeFolderChoose.setText(folder);
				} catch (Exception exc) {
					
				}
			}
		});
		buttonMergeFolderChoose.setText("...");
		buttonMergeFolderChoose.setBounds(603, 40, 30, 30);
		
		ProgressBar progressBar_1 = new ProgressBar(composite, SWT.NONE);
		progressBar_1.setMaximum(100);
		progressBar_1.setBounds(10, 414, 643, 21);
		
		Button buttonMerge = new Button(composite, SWT.NONE);
		buttonMerge.setText("Merge");
		buttonMerge.setBounds(279, 178, 90, 30);

		shlSplitMerge.open();
		shlSplitMerge.layout();
		
		
		
		while (!shlSplitMerge.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
